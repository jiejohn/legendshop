/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service;

import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SqlQuery;
import com.legendshop.model.entity.UserDetail;

/**
 * The Interface UserDetailService.
 */
public interface UserDetailService {


	/**
	 * Query score.
	 * 
	 * @param userName
	 *            the user name
	 * @return the long
	 */
	public abstract Long getScore(String userName);

	/**
	 * Gets the user detail list.
	 * 
	 * @param hqlQuery
	 *            the hql query
	 * @return the user detail list
	 */
	public abstract PageSupport getUserDetailList(HqlQuery hqlQuery);
	
	
	/**
	 * Gets the user detail list.
	 * 
	 * @param sqlQuery
	 *            the sql query
	 * @return the user detail list
	 */
	public abstract PageSupport getUserDetailList(SqlQuery sqlQuery);

	/**
	 * Find user detail.
	 * 
	 * @param userName
	 *            the user name
	 * @return the user detail
	 */
	public abstract UserDetail getUserDetail(String userName);

}