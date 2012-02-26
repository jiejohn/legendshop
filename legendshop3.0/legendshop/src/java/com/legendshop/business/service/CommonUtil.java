/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service;

import com.legendshop.core.dao.BaseDao;

public interface CommonUtil {

	// 保存后台管理员角色
	/**
	 * Save admin right.
	 * 
	 * @param baseDaoImpl
	 *            the base dao
	 * @param userId
	 *            the user id
	 */
	public abstract void saveAdminRight(BaseDao baseDao, String userId);

	// 保存用户角色
	/**
	 * Save user right.
	 * 
	 * @param baseDaoImpl
	 *            the base dao
	 * @param userId
	 *            the user id
	 */
	public abstract void saveUserRight(BaseDao baseDao, String userId);

	/**
	 * Removes the admin right.
	 * 
	 * @param baseDaoImpl
	 *            the base dao
	 * @param userId
	 *            the user id
	 */
	public abstract void removeAdminRight(BaseDao baseDao, String userId);

	/**
	 * Removes the user right.
	 * 
	 * @param baseDaoImpl
	 *            the base dao
	 * @param userId
	 *            the user id
	 */
	public abstract void removeUserRight(BaseDao baseDao, String userId);

}