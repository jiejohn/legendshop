/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;

import com.legendshop.business.dao.UserDetailDao;
import com.legendshop.business.search.facade.ShopDetailSearchFacade;
import com.legendshop.business.service.ShopDetailService;
import com.legendshop.core.AttributeKeys;
import com.legendshop.core.constant.LanguageEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.model.entity.Product;
import com.legendshop.model.entity.ShopDetail;
import com.legendshop.model.entity.ShopDetailView;
import com.legendshop.model.entity.UserDetail;
import com.legendshop.spi.constants.Constants;
import com.legendshop.spi.dao.ShopDetailDao;
import com.legendshop.util.AppUtils;

/**
 * 商城详细信息服务.
 */
public class ShopDetailServiceImpl   extends BaseServiceImpl  implements ShopDetailService {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(ShopDetailServiceImpl.class);
	
	/** The shop detail dao. */
	private ShopDetailDao shopDetailDao;
	
	/** The user detail dao. */
	private UserDetailDao userDetailDao;
	
	/** The shop detail search facade. */
	private ShopDetailSearchFacade shopDetailSearchFacade;

	/**
	 * Sets the shop detail dao.
	 * 
	 * @param shopDetailDao
	 *            the new shopDetailDao
	 */
	@Override
	public void setShopDetailDao(ShopDetailDao shopDetailDao) {
		this.shopDetailDao = shopDetailDao;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.ShopDetailService#findShopDetail(java.lang.Long)
	 */
	@Override
	public ShopDetail getShopDetailById(Long id) {
		return shopDetailDao.get(ShopDetail.class, id);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.ShopDetailService#findUserDetailByName(java.lang.String)
	 */
	@Override
	public UserDetail getShopDetailByName(String userName) {
		return userDetailDao.getUserDetailByName(userName);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.ShopDetailService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		shopDetailDao.deleteShopDetailById(id);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.ShopDetailService#delete(com.legendshop.model.entity.ShopDetail)
	 */
	@Override
	public void delete(ShopDetail shopDetail) {
		shopDetailDao.deleteShopDetail(shopDetail);
	}

	// UserId 一定不能为空
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.ShopDetailService#save(com.legendshop.model.entity.ShopDetail)
	 */
	@Override
	public void save(ShopDetail shopDetail) {
		
		shopDetailDao.saveShopDetail(shopDetail);

		shopDetailSearchFacade.create(shopDetail);
	}
	


	@Override
	public ShopDetailView getShopDetailView(String shopName, HttpServletRequest request, HttpServletResponse response) {

		String currentShopName = getShopName(request, response);
		// inShopDetail = true 表示第二次以上访问该商城，第一次访问时需要设置商城对应的Locale等参数，第二次则不需要
		if (shopName == null && currentShopName == null) {
			log.debug("shopName and currentShopName can not both NULL");
			return null;
		}
		boolean inShopDetail = true;
		if (currentShopName != null) {
			if (shopName != null && !shopName.equals(currentShopName)) {
				// 换商城,第一次进入其他商城
				log.debug("从商城 currentShopName = {} 进入另外一个商城 shopName = {}", currentShopName, shopName);
				currentShopName = shopName;
				request.getSession().setAttribute(Constants.SHOP_NAME, currentShopName);
				inShopDetail = false;
			}
		} else {
			// 表示第一次访问，需要商城初始化
			log.debug("第一次访问,currentShopName = {}, shopName = {}", currentShopName, shopName);
			currentShopName = shopName;
			request.getSession().setAttribute(Constants.SHOP_NAME, currentShopName);
			inShopDetail = false;
		}

		ShopDetailView shopDetail = getShopDetailView(currentShopName);
		
		if(!inShopDetail){
			//log.debug("initing shopDetail and set to session");
			//request.getSession().setAttribute(Constants.SHOP_DETAIL, shopDetail);
			setLocalByShopDetail(shopDetail, request, response);
		}
		return shopDetail;
	}
	
	@Cacheable(value = "ShopDetailView", key="#currentShopName")
	private ShopDetailView getShopDetailView(String currentShopName){
		return shopDetailDao.getShopDetailView(currentShopName);
	}


	/**
	 * Sets the local by shop detail.
	 * 
	 * @param shopDetail
	 *            the shop detail
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 */
	private void setLocalByShopDetail(ShopDetailView shopDetail, HttpServletRequest request, HttpServletResponse response) {
		log.debug("setLocalByShopDetail calling");
		if (shopDetail == null) {
			return;
		}

		String langStyle = shopDetail.getLangStyle();
		// 总配置，如果不是USERCHOICE则无需设置,直接覆盖配置文件即可
		if (AppUtils.isBlank(langStyle)
				|| LanguageEnum.USERCHOICE.equals(langStyle) // shop level
				|| !LanguageEnum.USERCHOICE.equals(request.getSession().getServletContext()
						.getAttribute(AttributeKeys.LANGUAGE_MODE))) { // system
																		// level
			return;
		}

		String[] language = shopDetail.getLangStyle().split("_");
		Locale locale = new Locale(language[0], language[1]);
		// HttpSession session = request.getSession();
		// if (session.getAttribute(AttributeKeys.LOCALE_KEY) != null) {
		// session.removeAttribute(AttributeKeys.LOCALE_KEY);
		// }
		//
		// Cookie cookie = new Cookie("LegendShopLanguage", langStyle);
		// cookie.setPath("/");
		// cookie.setMaxAge(100000); // -1为永不过期,或者指定过期时间
		// response.addCookie(cookie);// 在退出登录操作中删除cookie.
		// session.setAttribute(AttributeKeys.LOCALE_KEY, locale);
		log.debug("setLocal {}, By ShopDetail {}", locale, shopDetail.getSiteName());
		if(response != null){
			localeResolver.setLocale(request, response, locale);
		}
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.ShopDetailService#update(com.legendshop.model.entity.ShopDetail)
	 */
	@Override
	public void update(ShopDetail shopDetail) {
		shopDetailDao.updateShopDetail(shopDetail);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.ShopDetailService#getShopDetail(com.legendshop.core.dao.support.CriteriaQuery)
	 */
	@Override
	public PageSupport getShopDetail(CriteriaQuery cq) {
		return shopDetailDao.find(cq);
	}

	/**
	 * Sets the user detail dao.
	 * 
	 * @param userDetailDao
	 *            the new user detail dao
	 */
	public void setUserDetailDao(UserDetailDao userDetailDao) {
		this.userDetailDao = userDetailDao;
	}


	/**
	 * Sets the shop detail search facade.
	 * 
	 * @param shopDetailSearchFacade
	 *            the new shop detail search facade
	 */
	public void setShopDetailSearchFacade(ShopDetailSearchFacade shopDetailSearchFacade) {
		this.shopDetailSearchFacade = shopDetailSearchFacade;
	}

	@Override
	public ShopDetail getShopDetailByUserId(String userId) {
		return shopDetailDao.getShopDetailByUserId(userId);
	}

	@Override
	public void updateShopDetail(Product product) {
		ShopDetail shopdetail = shopDetailDao.getShopDetailForUpdate(product.getUserName());
		if (shopdetail == null) {
			throw new NotFoundException("ShopDetail is null, UserName = " + product.getUserName(),EntityCodes.PROD);
		}
		shopdetail.setProductNum(shopDetailDao.getProductNum(product.getUserName()));
		shopdetail.setOffProductNum(shopDetailDao.getOffProductNum(product.getUserName()));
		shopDetailDao.updateShopDetail(shopdetail);
	}

	@Override
	public boolean updateShop(String loginUserName, String userId, ShopDetail shopDetail, Integer status) {
		return shopDetailDao.updateShop(loginUserName, userId, shopDetail, status);
	}



}
