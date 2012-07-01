/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.legendshop.business.dao.UserDetailDao;
import com.legendshop.business.search.facade.ShopDetailSearchFacade;
import com.legendshop.business.service.ShopDetailService;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.model.entity.Product;
import com.legendshop.model.entity.ShopDetail;
import com.legendshop.model.entity.ShopDetailView;
import com.legendshop.model.entity.UserDetail;
import com.legendshop.spi.dao.ShopDetailDao;

/**
 * 商城详细信息服务.
 */
public class ShopDetailServiceImpl implements ShopDetailService {
	
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
	public ShopDetailView getShopDetailView(String currentShopName) {
		return shopDetailDao.getShopDetailView(currentShopName);
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
