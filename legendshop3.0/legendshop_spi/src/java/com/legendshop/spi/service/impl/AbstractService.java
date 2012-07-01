/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.spi.service.impl;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.LocaleResolver;

import com.legendshop.core.AttributeKeys;
import com.legendshop.core.constant.LanguageEnum;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.ProductDetail;
import com.legendshop.model.entity.ShopDetailView;
import com.legendshop.model.visit.VisitHistory;
import com.legendshop.spi.constants.Constants;
import com.legendshop.spi.dao.ShopDetailDao;
import com.legendshop.spi.service.BaseService;
import com.legendshop.util.AppUtils;

/**
 * The Class AbstractService.
 */
public abstract class AbstractService  implements BaseService{
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(AbstractService.class);
	
	protected ShopDetailDao shopDetailDao;
	
	/** The locale resolver. */
	protected LocaleResolver localeResolver;
	
	/* (non-Javadoc)
	 * @see com.legendshop.spi.service.BaseService#getShopDetailView(java.lang.String, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
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

		ShopDetailView shopDetail = shopDetailDao.getShopDetailView(currentShopName);
		log.debug("getShopDetailView currentShopName = {}, shopDetail = {}",currentShopName, shopDetail);

		if(!inShopDetail){
			//log.debug("initing shopDetail and set to session");
			//request.getSession().setAttribute(Constants.SHOP_DETAIL, shopDetail);
			setLocalByShopDetail(shopDetail, request, response);
		}
		return shopDetail;
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
		localeResolver.setLocale(request, response, locale);

	}

	
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BaseService#getSessionAttribute(javax.servlet.http.HttpServletRequest, java.lang.String)
	 */
	
	public Object getSessionAttribute(HttpServletRequest request, String name) {
		Object obj = null;
		HttpSession session = request.getSession();
		if (session != null) {
			obj = session.getAttribute(name);
		}
		return obj;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BaseService#getShopName(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	
	public String getShopName(HttpServletRequest request, HttpServletResponse response) {
		String shopName = (String) getSessionAttribute(request, Constants.SHOP_NAME);
		if(AppUtils.isBlank(shopName)){
			String defaultShopName =  PropertiesUtil.getObject(ParameterEnum.DEFAULT_SHOP, String.class);
			setSessionAttribute(request, Constants.SHOP_NAME, defaultShopName);
			return defaultShopName;
		}else{
			return shopName;
		}
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BaseService#setSessionAttribute(javax.servlet.http.HttpServletRequest, java.lang.String, java.lang.Object)
	 */
	
	public void setSessionAttribute(HttpServletRequest request, String name, Object obj) {
		HttpSession session = request.getSession();
		if (session != null) {
			session.setAttribute(name, obj);
		}
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BaseService#setShopName(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.String)
	 */
	
	public void setShopName(HttpServletRequest request, HttpServletResponse response, String shopName) {
		// Session
		String name = getShopName(request,response);
		if(name == null  || !name.equals(shopName)){
			setSessionAttribute(request, Constants.SHOP_NAME, shopName);
		}
	
		// Cookie
		// Cookie cookie = new Cookie(Constants.SHOP_NAME, shopName);
		// //生命周期
		// cookie.setMaxAge(60 * 60 * 24 * 365);
		// cookie.setPath("/");
		// response.addCookie(cookie);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BaseService#convertStringToInteger(java.lang.String)
	 */
	
	public Long convertStringToInteger(String id) {
		try {
			Long result = Long.valueOf(id);
			if (result == 0) {
				return null;
			} else {
				return result;
			}
		} catch (Exception e) {
			log.error("can not convert id " + id);
			return null;
		}
	}

	/**
	 * Visit.
	 * 
	 * @param prod
	 *            the prod
	 * @param request
	 *            the request
	 */
	protected void visit(ProductDetail prod, HttpServletRequest request) {
		VisitHistory visitHistory = (VisitHistory) request.getSession().getAttribute(Constants.VISIT_HISTORY);
		if (visitHistory == null) {
			visitHistory = new VisitHistory();
		}
		visitHistory.visit(prod);
		request.getSession().setAttribute(Constants.VISIT_HISTORY, visitHistory);
	}

	/**
	 * Visit.
	 * 
	 * @param shopDetail
	 *            the shop detail
	 * @param request
	 *            the request
	 */
	public void visit(ShopDetailView shopDetail, HttpServletRequest request) {
		VisitHistory visitHistory = (VisitHistory) request.getSession().getAttribute(Constants.VISIT_HISTORY);
		if (visitHistory == null) {
			visitHistory = new VisitHistory();
		}
		visitHistory.visit(shopDetail);
		request.getSession().setAttribute(Constants.VISIT_HISTORY, visitHistory);
	}


	public void setShopDetailDao(ShopDetailDao shopDetailDao) {
		this.shopDetailDao = shopDetailDao;
	}


	public void setLocaleResolver(LocaleResolver localeResolver) {
		this.localeResolver = localeResolver;
	}
}
