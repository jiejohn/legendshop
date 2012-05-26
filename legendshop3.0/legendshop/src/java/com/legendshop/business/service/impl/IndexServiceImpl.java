/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import org.springframework.beans.factory.annotation.Required;

import com.legendshop.business.service.IndexService;
import com.legendshop.core.dao.BaseDao;
import com.legendshop.model.UserInfo;
import com.legendshop.model.entity.ShopDetailView;
import com.legendshop.spi.constants.Constants;

/**
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * ----------------------------------------------------------------------------
 * 
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * 
 * ----------------------------------------------------------------------------.
 */
public class IndexServiceImpl implements IndexService {

	/** The base dao. */
	private BaseDao baseDao;

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.IndexService#indexAdmin(java.lang.String, com.legendshop.model.entity.ShopDetailView)
	 */
	@Override
	public UserInfo getAdminIndex(String userName, ShopDetailView shopDetail) {
		UserInfo userInfo = new UserInfo();

		if (shopDetail != null) { // 已有商城的用户
			Long totalNews = baseDao.findUniqueBy("select count(*) from News where userName = ?", Long.class, userName);
			userInfo.setArticleNum(totalNews);

			Long totalProcessingOrder = baseDao.findUniqueBy(
					"select count(*) from Sub where subCheck = ? and  shopName = ?", Long.class,
					Constants.FALSE_INDICATOR, userName);
			userInfo.setProcessingOrderNum(totalProcessingOrder);

			Long totalUnReadMessage = baseDao.findUniqueBy(
					"select count(*) from UserComment where status = ? and toUserName = ?", Long.class, 0, userName);

			userInfo.setUnReadMessageNum(totalUnReadMessage);

			userInfo.setShopDetail(shopDetail);
		} else {// 管理员
			Long userTotalNum = baseDao.findUniqueBy("select count(*) from UserDetail", Long.class);
			userInfo.setUserTotalNum(userTotalNum);

			Long shopTotalNum = baseDao.findUniqueBy("select count(*) from ShopDetail ", Long.class);
			userInfo.setShopTotalNum(shopTotalNum);
		}

		return userInfo;
	}

	/**
	 * Sets the base dao.
	 * 
	 * @param baseDao
	 *            the new base dao
	 */
	@Required
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

}
