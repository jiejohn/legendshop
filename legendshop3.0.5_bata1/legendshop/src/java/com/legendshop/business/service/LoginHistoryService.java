/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SqlQuery;

/**
 * The Interface LoginHistoryService.
 */
public interface LoginHistoryService {

	/**
	 * 保存登录历史.
	 * 
	 * @param userName
	 *            the user name
	 * @param ip
	 *            the ip
	 */
	public abstract void saveLoginHistory(String userName, String ip);

	/**
	 * Find login history.
	 * 
	 * @param cq
	 *            the cq
	 * @return the page support
	 */
	public abstract PageSupport getLoginHistory(CriteriaQuery cq);

	/**
	 * Find login history.
	 * 
	 * @param query
	 *            the query
	 * @return the page support
	 */
	public abstract PageSupport getLoginHistoryBySQL(SqlQuery query);

}