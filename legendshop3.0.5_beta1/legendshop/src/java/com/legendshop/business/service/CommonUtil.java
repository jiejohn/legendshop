/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service;


/**
 * The Interface CommonUtil.
 */
public interface CommonUtil {

	// 保存后台管理员角色

	/**
	 * Save admin right.
	 * 
	 * @param userId
	 *            the user id
	 */
	public abstract void saveAdminRight(String userId);

	// 保存用户角色

	/**
	 * Save user right.
	 * 
	 * @param userId
	 *            the user id
	 */
	public abstract void saveUserRight(String userId);


	/**
	 * Removes the admin right.
	 * 
	 * @param userId
	 *            the user id
	 */
	public abstract void removeAdminRight(String userId);


	/**
	 * Removes the user right.
	 * 
	 * @param userId
	 *            the user id
	 */
	public abstract void removeUserRight(String userId);

}