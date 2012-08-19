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
 * The Interface CommonUtilDao.
 */
public interface CommonUtilDao extends BaseDao{

	/**
	 * Removes the role.
	 * 
	 * @param roles
	 *            the roles
	 * @param userId
	 *            the user id
	 */
	public void removeRole(List<String> roles, String userId);
	
	
	
	/**
	 * Save admin right.
	 * 
	 * @param userId
	 *            the user id
	 */
	public void saveAdminRight(String userId);
	
	/**
	 * Save user right.
	 * 
	 * @param userId
	 *            the user id
	 */
	public void saveUserRight(String userId);
	
	/**
	 * Removes the admin right.
	 * 
	 * @param userId
	 *            the user id
	 */
	public void removeAdminRight(String userId);
	
	/**
	 * Removes the user right.
	 * 
	 * @param userId
	 *            the user id
	 */
	public void removeUserRight(String userId);

}
