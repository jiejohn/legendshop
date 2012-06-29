/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SqlQuery;

/**
 * The Interface LoginHistoryDao.
 */
public interface LoginHistoryDao extends BaseDao{
	
	/**
	 * Gets the login history by sql.
	 * 
	 * @param query
	 *            the query
	 * @return the login history by sql
	 */
	public PageSupport getLoginHistoryBySQL(SqlQuery query);
	
	/**
	 * Gets the login history.
	 * 
	 * @param cq
	 *            the cq
	 * @return the login history
	 */
	public PageSupport getLoginHistory(CriteriaQuery cq);
}
