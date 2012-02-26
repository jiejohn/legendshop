/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.business.service.CommonUtil;
import com.legendshop.core.dao.BaseDao;
import com.legendshop.model.entity.UserRole;
import com.legendshop.model.entity.UserRoleId;
/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public class CommonUtilImpl implements CommonUtil {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(CommonServiceUtil.class);

	/** The admin right sql. */
	private static String adminRightSQL = "select id from ls_role where name = 'ROLE_ADMIN' or name = 'ROLE_OPERATOR'";

	/** The user right sql. */
	private static String userRightSQL = "select id from ls_role where name = 'ROLE_USER' or name = 'ROLE_OPERATOR'";

	// 保存后台管理员角色
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.CommonUtil#saveAdminRight(com.legendshop.core.dao.impl.BaseDaoImpl, java.lang.String)
	 */
	@Override
	public void saveAdminRight(BaseDao baseDao, String userId) {
		List<String> roles = baseDao.findBySQL(adminRightSQL);
		saveRole(baseDao, roles, userId);
	}

	// 保存用户角色
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.CommonUtil#saveUserRight(com.legendshop.core.dao.impl.BaseDaoImpl, java.lang.String)
	 */
	@Override
	public void saveUserRight(BaseDao baseDao, String userId) {
		List<String> roles = baseDao.findBySQL(userRightSQL);
		saveRole(baseDao, roles, userId);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.CommonUtil#removeAdminRight(com.legendshop.core.dao.impl.BaseDaoImpl, java.lang.String)
	 */
	@Override
	public void removeAdminRight(BaseDao baseDao, String userId) {
		List<String> roles = baseDao.findBySQL(adminRightSQL);
		removeRole(baseDao, roles, userId);
		saveUserRight(baseDao, userId);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.CommonUtil#removeUserRight(com.legendshop.core.dao.impl.BaseDaoImpl, java.lang.String)
	 */
	@Override
	public void removeUserRight(BaseDao baseDao, String userId) {
		List<String> roles = baseDao.findBySQL(userRightSQL);
		removeRole(baseDao, roles, userId);
	}

	/**
	 * Save role.
	 * 
	 * @param baseDaoImpl
	 *            the base dao
	 * @param roles
	 *            the roles
	 * @param userId
	 *            the user id
	 */
	private void saveRole(BaseDao baseDao, List<String> roles, String userId) {
		for (String roleId : roles) {
			UserRole userRole = new UserRole();
			UserRoleId id = new UserRoleId();
			id.setRoleId(roleId);
			id.setUserId(userId);
			userRole.setId(id);
			if (baseDao.get(UserRole.class, id) == null) {
				baseDao.save(userRole);
			}
		}
	}

	/**
	 * Removes the role.
	 * 
	 * @param baseDaoImpl
	 *            the base dao
	 * @param roles
	 *            the roles
	 * @param userId
	 *            the user id
	 */
	private void removeRole(BaseDao baseDao, List<String> roles, String userId) {
		for (String roleId : roles) {
			UserRole userRole = new UserRole();
			UserRoleId id = new UserRoleId();
			id.setRoleId(roleId);
			id.setUserId(userId);
			userRole.setId(id);
			UserRole entity = baseDao.get(UserRole.class, id);
			if (entity != null) {
				baseDao.delete(entity);
			}
		}
	}

}
