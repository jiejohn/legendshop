/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.permission.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.legendshop.core.OperationTypeEnum;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.event.impl.FireEvent;
import com.legendshop.event.EventHome;
import com.legendshop.model.entity.User;
import com.legendshop.util.StringUtil;
/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public class UserDao extends BaseDaoImpl {
	
	/** The Constant logger. */
	private static final Log logger = LogFactory.getLog(UserDao.class);
	
	/** The find by name. */
	private	String findByName;
	
	/** The find user role by role id. */
	private	String findUserRoleByRoleId;
	
	/** The find user role by user id. */
	private	String findUserRoleByUserId;
	
	/**
	 * Sets the find by name.
	 * 
	 * @param findByName
	 *            the new find by name
	 */
	public void setFindByName(String findByName) {
		this.findByName = findByName;
	}

	/**
	 * Sets the find user role by role id.
	 * 
	 * @param findUserRoleByRoleId
	 *            the new find user role by role id
	 */
	public void setFindUserRoleByRoleId(String findUserRoleByRoleId) {
		this.findUserRoleByRoleId = findUserRoleByRoleId;
	}

	/**
	 * Sets the find user role by user id.
	 * 
	 * @param findUserRoleByUserId
	 *            the new find user role by user id
	 */
	public void setFindUserRoleByUserId(String findUserRoleByUserId) {
		this.findUserRoleByUserId = findUserRoleByUserId;
	}

	/**
	 * Delete user role.
	 * 
	 * @param userRoles
	 *            the user roles
	 */
	public void DeleteUserRole(List userRoles) {
		logger.info("DeleteUserRole with size "+userRoles.size());
		deleteAll(userRoles);
	}
	
	/**
	 * Find by name.
	 * 
	 * @param name
	 *            the name
	 * @return the list
	 */
	public List findByName(String name) {
		logger.info(StringUtil.convert(findByName, new String[]{name}));
		return find(findByName, name);
	}
	
	/**
	 * Find user role by role id.
	 * 
	 * @param roleId
	 *            the role id
	 * @return the list
	 */
	public List findUserRoleByRoleId(String roleId) {
		logger.info(StringUtil.convert(findUserRoleByRoleId, new String[]{roleId}));
		return find(findUserRoleByRoleId, roleId);
	}
	
	/**
	 * Find user role by user id.
	 * 
	 * @param userId
	 *            the user id
	 * @return the list
	 */
	public List findUserRoleByUserId(String userId) {
		logger.info(StringUtil.convert(findUserRoleByUserId, new String[]{userId}));
		return find(findUserRoleByUserId, userId);
	}

	/**
	 * Update user.
	 * 
	 * @param user
	 *            the user
	 */
	public void updateUser(User user) {
		EventHome.publishEvent(new FireEvent(user, OperationTypeEnum.UPDATE));
		update(user);
	}
}
