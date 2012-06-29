/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao.impl;

import java.util.List;

import com.legendshop.business.dao.TagLibDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;

/**
 * The Class TagLibDaoImpl.
 */
public class TagLibDaoImpl extends BaseDaoImpl implements TagLibDao {

	/**
	 * Find by hql.
	 * 
	 * @param hql
	 *            the hql
	 * @return the list
	 */
	@Override
	public List findDataByHQL(String hql){
		return super.findByHQL(hql);
	}
	
	/**
	 * Find by sql.
	 * 
	 * @param sql
	 *            the sql
	 * @return the list
	 */
	@Override
	public List findDataBySQL(String sql){
		return super.findBySQL(sql);
	}

}
