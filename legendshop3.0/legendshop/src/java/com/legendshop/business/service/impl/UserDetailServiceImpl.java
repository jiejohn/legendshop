/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import javax.mail.MessagingException;

import org.apache.oro.text.regex.MalformedPatternException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.legendshop.business.dao.UserDetailDao;
import com.legendshop.business.service.UserDetailService;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SqlQuery;
import com.legendshop.model.entity.UserDetail;

/**
 * 用户服务.
 */
public class UserDetailServiceImpl  extends BaseServiceImpl  implements UserDetailService {
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(UserDetailServiceImpl.class);
	
	/** The user detail dao. */
	private UserDetailDao userDetailDao;

	
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.UserDetailService#queryScore(java.lang.String)
	 */
	@Override
	public Long getScore(String userName) {
		return userDetailDao.getUserScore(userName);
	}
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.UserDetailService#getUserDetailList(com.legendshop.core.dao.support.HqlQuery)
	 */
	@Override
	public PageSupport getUserDetailList(HqlQuery hqlQuery) {
		return userDetailDao.getUserDetailList(hqlQuery);
	}
	

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.UserDetailService#findUserDetail(java.lang.String)
	 */
	@Override
	public UserDetail getUserDetail(String userName) {
		return userDetailDao.getUserDetail(userName);
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

	@Override
	public PageSupport getUserDetailList(SqlQuery sqlQuery) {
		return userDetailDao.getUserDetailList(sqlQuery);
	}

	@Override
	public String deleteUserDetail(String userId, String userName, String realPicPath, String smallPicPath) {
		return userDetailDao.deleteUserDetail(userId, userName, realPicPath, smallPicPath);
	}

	@Override
	public boolean updatePassword(String userName, String mail, String templateFilePath)
			throws MalformedPatternException, MessagingException {
		return userDetailDao.updatePassword(userName, mail, templateFilePath);
		
	}
}
