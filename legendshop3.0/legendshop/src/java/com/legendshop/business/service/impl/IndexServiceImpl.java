/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import org.springframework.beans.factory.annotation.Required;

import com.legendshop.business.dao.NewsDao;
import com.legendshop.business.dao.ShopDetailDao;
import com.legendshop.business.dao.SubDao;
import com.legendshop.business.dao.UserCommentDao;
import com.legendshop.business.dao.UserDetailDao;
import com.legendshop.business.service.IndexService;
import com.legendshop.model.UserInfo;
import com.legendshop.model.entity.ShopDetailView;

/**
 * 首页相关服务.
 */
public class IndexServiceImpl implements IndexService {

	/** The base dao. */
	private NewsDao newsDao;
	
	/** The sub dao. */
	private SubDao subDao;
	
	/** The user comment dao. */
	private UserCommentDao userCommentDao;
	
	/** The user detail dao. */
	private UserDetailDao userDetailDao;
	
	/** The shop detail dao. */
	private ShopDetailDao shopDetailDao;
	
	

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.IndexService#indexAdmin(java.lang.String, com.legendshop.model.entity.ShopDetailView)
	 */
	@Override
	public UserInfo getAdminIndex(String userName, ShopDetailView shopDetail) {
		UserInfo userInfo = new UserInfo();

		if (shopDetail != null) { // 已有商城的用户
			
			userInfo.setArticleNum(newsDao.getAllNews(userName));

			userInfo.setProcessingOrderNum(subDao.getTotalProcessingOrder(userName));
			userInfo.setUnReadMessageNum(userCommentDao.getTotalUnReadMessage(userName));

			userInfo.setShopDetail(shopDetail);
		} else {// 管理员
			userInfo.setUserTotalNum(userDetailDao.getAllUserCount());

			userInfo.setShopTotalNum(shopDetailDao.getAllShopCount());
		}

		return userInfo;
	}



	/**
	 * Sets the news dao.
	 * 
	 * @param newsDao
	 *            the new news dao
	 */
	@Required
	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}



	/**
	 * Sets the sub dao.
	 * 
	 * @param subDao
	 *            the new sub dao
	 */
	@Required
	public void setSubDao(SubDao subDao) {
		this.subDao = subDao;
	}



	/**
	 * Sets the user comment dao.
	 * 
	 * @param userCommentDao
	 *            the new user comment dao
	 */
	@Required
	public void setUserCommentDao(UserCommentDao userCommentDao) {
		this.userCommentDao = userCommentDao;
	}



	/**
	 * Sets the user detail dao.
	 * 
	 * @param userDetailDao
	 *            the new user detail dao
	 */
	@Required
	public void setUserDetailDao(UserDetailDao userDetailDao) {
		this.userDetailDao = userDetailDao;
	}



	/**
	 * Sets the shop detail dao.
	 * 
	 * @param shopDetailDao
	 *            the new shop detail dao
	 */
	@Required
	public void setShopDetailDao(ShopDetailDao shopDetailDao) {
		this.shopDetailDao = shopDetailDao;
	}



}
