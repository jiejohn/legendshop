/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import com.legendshop.business.dao.ShopDetailDao;
import com.legendshop.business.dao.UserDetailDao;
import com.legendshop.business.search.facade.ShopDetailSearchFacade;
import com.legendshop.business.service.CommonUtil;
import com.legendshop.business.service.ShopDetailService;
import com.legendshop.central.license.LSResponse;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.ShopDetail;
import com.legendshop.model.entity.UserDetail;

/**
 * 商城详细信息服务.
 */
public class ShopDetailServiceImpl implements ShopDetailService {
	
	/** The shop detail dao. */
	private ShopDetailDao shopDetailDao;
	
	/** The user detail dao. */
	private UserDetailDao userDetailDao;
	
	/** The common util. */
	private CommonUtil commonUtil;
	
	/** The shop detail search facade. */
	private ShopDetailSearchFacade shopDetailSearchFacade;

	/**
	 * Sets the shop detail dao.
	 * 
	 * @param baseDao
	 *            the new shop detail dao
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
		shopDetailDao.deleteById(ShopDetail.class, id);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.ShopDetailService#delete(com.legendshop.model.entity.ShopDetail)
	 */
	@Override
	public void delete(ShopDetail shopDetail) {
		shopDetailDao.delete(shopDetail);
	}

	// UserId 一定不能为空
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.ShopDetailService#save(com.legendshop.model.entity.ShopDetail)
	 */
	@Override
	public void save(ShopDetail shopDetail) {
		shopDetailDao.save(shopDetail);
		// save right
		// 保存管理员角色
		commonUtil.saveAdminRight(shopDetailDao, shopDetail.getUserId());

		shopDetailSearchFacade.create(shopDetail);
	}
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.ShopDetailService#isSupportOpenShop(com.legendshop.central.license.LSResponse)
	 */
	@Override
	public Boolean isSupportOpenShop(LSResponse lsResponse) {
		return shopDetailDao.isCanAddShopDetail(lsResponse);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.ShopDetailService#update(com.legendshop.model.entity.ShopDetail)
	 */
	@Override
	public void update(ShopDetail shopDetail) {
		shopDetailDao.update(shopDetail);
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
	 * Sets the common util.
	 * 
	 * @param commonUtil
	 *            the new common util
	 */
	public void setCommonUtil(CommonUtil commonUtil) {
		this.commonUtil = commonUtil;
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


}
