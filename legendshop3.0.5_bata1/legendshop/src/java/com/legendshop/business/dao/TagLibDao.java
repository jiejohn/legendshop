/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao;

import java.util.List;

import com.legendshop.core.dao.BaseDao;

/**
 * The Interface TagLibDao.
 */
public interface TagLibDao extends BaseDao{
	
	/**
	 * Find by hql.
	 * 
	 * @param hql
	 *            the hql
	 * @return the list
	 */
	public List<Object[]> findDataByHQL(String hql);
	
	/**
	 * Find by sql.
	 * 
	 * @param sql
	 *            the sql
	 * @return the list
	 */
	public List<Object[]> findDataBySQL(String sql);
}
