/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao.impl;

import java.util.List;

import com.legendshop.business.dao.CommonUtilDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.model.entity.UserRole;
import com.legendshop.model.entity.UserRoleId;

/**
 * The Class CommonUtilDaoImpl.
 */
public class CommonUtilDaoImpl extends BaseDaoImpl implements CommonUtilDao {

	/** The admin right sql. */
	private static String adminRightSQL = "select id from ls_role where name = 'ROLE_ADMIN' or name = 'ROLE_OPERATOR'";

	/** The user right sql. */
	private static String userRightSQL = "select id from ls_role where name = 'ROLE_USER' or name = 'ROLE_OPERATOR'";
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.CommonUtilDao#removeRole(java.util.List, java.lang.String)
	 */
	@Override
	public void removeRole(List<String> roles, String userId) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Save role.
	 * 
	 * @param roles
	 *            the roles
	 * @param userId
	 *            the user id
	 */
	private void saveRole(List<String> roles, String userId) {
		for (String roleId : roles) {
			UserRole userRole = new UserRole();
			UserRoleId id = new UserRoleId();
			id.setRoleId(roleId);
			id.setUserId(userId);
			userRole.setId(id);
			if (get(UserRole.class, id) == null) {
				save(userRole);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.CommonUtilDao#saveAdminRight(java.lang.String)
	 */
	@Override
	public void saveAdminRight(String userId) {
		List<String> roles = findBySQL(adminRightSQL);
		saveRole(roles, userId);
		
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.CommonUtilDao#saveUserRight(java.lang.String)
	 */
	@Override
	public void saveUserRight(String userId) {
		List<String> roles = findBySQL(userRightSQL);
		saveRole(roles, userId);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.CommonUtilDao#removeAdminRight(java.lang.String)
	 */
	@Override
	public void removeAdminRight(String userId) {
		List<String> roles = findBySQL(adminRightSQL);
		removeRole(roles, userId);
		saveUserRight(userId);
		
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.CommonUtilDao#removeUserRight(java.lang.String)
	 */
	@Override
	public void removeUserRight(String userId) {
		List<String> roles = findBySQL(userRightSQL);
		removeRole(roles, userId);
	}
	

}
