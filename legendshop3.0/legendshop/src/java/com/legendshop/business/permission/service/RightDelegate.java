/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.permission.service;

import java.io.File;
import java.util.List;

import com.legendshop.command.framework.State;
import com.legendshop.command.framework.facade.BizDelegate;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Function;
import com.legendshop.model.entity.LoginHistory;
import com.legendshop.model.entity.Permission;
import com.legendshop.model.entity.Role;
import com.legendshop.model.entity.User;
import com.legendshop.model.entity.UserRole;
/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public interface RightDelegate extends BizDelegate {

	/**
	 * Find workflow.
	 * 
	 * @param state
	 *            the state
	 * @return true, if successful
	 */
	public abstract boolean findWorkflow(State state);
	
	/**
	 * Test circle.
	 * 
	 * @param state
	 *            the state
	 * @return the string
	 */
	public abstract String testCircle(State state);

	/**
	 * Findgranted authority from data base.
	 * 
	 * @param authority
	 *            the authority
	 * @param state
	 *            the state
	 * @return the role
	 */
	public Role findgrantedAuthorityFromDataBase(String authority, State state);

	/**
	 * Find function from data base.
	 * 
	 * @param protectfunction
	 *            the protectfunction
	 * @param state
	 *            the state
	 * @return the function
	 */
	public Function findFunctionFromDataBase(String protectfunction,State state);
	
	/**
	 * Find function by role id.
	 * 
	 * @param roleId
	 *            the role id
	 * @param state
	 *            the state
	 * @return the list
	 */
	public List findFunctionByRoleId(String roleId,State state);

	/**
	 * Find user by name from data base.
	 * 
	 * @param name
	 *            the name
	 * @param state
	 *            the state
	 * @return the user
	 */
	public User findUserByNameFromDataBase(String name, State state);
	
	/**
	 * Find role by function.
	 * 
	 * @param functionId
	 *            the function id
	 * @param state
	 *            the state
	 * @return the list
	 */
	public List findRoleByFunction(String functionId, State state);
	
	/**
	 * Save user.
	 * 
	 * @param user
	 *            the user
	 * @param state
	 *            the state
	 * @return the string
	 */
	public String saveUser(User user, State state);

	/**
	 * Checks if is user exist.
	 * 
	 * @param name
	 *            the name
	 * @param state
	 *            the state
	 * @return true, if is user exist
	 */
	public boolean isUserExist(String name, State state);

	// 查找用户
	/**
	 * Find user by id.
	 * 
	 * @param userId
	 *            the user id
	 * @param state
	 *            the state
	 * @return the user
	 */
	public User findUserById(String userId, State state);
	
	/**
	 * Find other function by role id.
	 * 
	 * @param roleId
	 *            the role id
	 * @param state
	 *            the state
	 * @return the list
	 */
	public List findOtherFunctionByRoleId(String roleId,State state);
	
	/**
	 * Find other function by hql.
	 * 
	 * @param hqlQuery
	 *            the hql query
	 * @param roleId
	 *            the role id
	 * @param state
	 *            the state
	 * @return the page support
	 */
	public PageSupport findOtherFunctionByHql(HqlQuery hqlQuery,String roleId,State state);

	/**
	 * Find all user.
	 * 
	 * @param cq
	 *            the cq
	 * @param state
	 *            the state
	 * @return the page support
	 */
	public PageSupport findAllUser(CriteriaQuery cq, State state);

	// 密码要MD5加密再传到数据库中
	/**
	 * Update user passowrd.
	 * 
	 * @param userId
	 *            the user id
	 * @param password
	 *            the password
	 * @param state
	 *            the state
	 */
	public void updateUserPassowrd(String userId, String password, State state);

	// 权限管理
	/**
	 * Save function.
	 * 
	 * @param function
	 *            the function
	 * @param state
	 *            the state
	 * @return the string
	 */
	public String saveFunction(Function function, State state);

	/**
	 * Delete function by id.
	 * 
	 * @param id
	 *            the id
	 * @param state
	 *            the state
	 * @return true, if successful
	 */
	public boolean deleteFunctionById(String id, State state);

	/**
	 * Delete function.
	 * 
	 * @param function
	 *            the function
	 * @param state
	 *            the state
	 */
	public void deleteFunction(Function function, State state) ;
	
	/**
	 * Find function by id.
	 * 
	 * @param id
	 *            the id
	 * @param state
	 *            the state
	 * @return the function
	 */
	public Function findFunctionById(String id, State state);

	/**
	 * Find all function.
	 * 
	 * @param cq
	 *            the cq
	 * @param state
	 *            the state
	 * @return the page support
	 */
	public PageSupport findAllFunction(CriteriaQuery cq,State state);

	/**
	 * Update function.
	 * 
	 * @param function
	 *            the function
	 * @param state
	 *            the state
	 */
	public void updateFunction(Function function, State state);

	// 为角色增加权限
	/**
	 * Save function to role.
	 * 
	 * @param permission
	 *            the permission
	 * @param state
	 *            the state
	 * @return true, if successful
	 */
	public boolean saveFunctionToRole(Permission permission, State state);
	
	/**
	 * Save functions to role.
	 * 
	 * @param permissions
	 *            the permissions
	 * @param state
	 *            the state
	 */
	public void saveFunctionsToRole(List permissions, State state);

	/**
	 * Delete functions from role.
	 * 
	 * @param permissions
	 *            the permissions
	 * @param state
	 *            the state
	 */
	public void deleteFunctionsFromRole(List<Permission> permissions, State state);
	
	/**
	 * Delete role.
	 * 
	 * @param role
	 *            the role
	 * @param state
	 *            the state
	 */
	public void deleteRole(Role role, State state) ;
	
	/**
	 * Find role by id.
	 * 
	 * @param id
	 *            the id
	 * @param state
	 *            the state
	 * @return the role
	 */
	public Role findRoleById(String id, State state) ;

	// 角色
	/**
	 * Save role.
	 * 
	 * @param role
	 *            the role
	 * @param state
	 *            the state
	 * @return the string
	 */
	public String saveRole(Role role, State state);

	/**
	 * Delete role by id.
	 * 
	 * @param roleId
	 *            the role id
	 * @param state
	 *            the state
	 */
	public void deleteRoleById(String roleId, State state);

	/**
	 * Find all role.
	 * 
	 * @param cq
	 *            the cq
	 * @param state
	 *            the state
	 * @return the page support
	 */
	public PageSupport findAllRole(CriteriaQuery cq, State state);

	/**
	 * Update role.
	 * 
	 * @param role
	 *            the role
	 * @param state
	 *            the state
	 */
	public void updateRole(Role role, State state);

	// 为用户增加角色
	/**
	 * Save role to user.
	 * 
	 * @param userRole
	 *            the user role
	 * @param state
	 *            the state
	 */
	public void saveRoleToUser(UserRole userRole, State state);

	/**
	 * Save roles to user.
	 * 
	 * @param userRoles
	 *            the user roles
	 * @param state
	 *            the state
	 */
	public void saveRolesToUser(List userRoles, State state);

	/**
	 * Delete role from user.
	 * 
	 * @param userRoles
	 *            the user roles
	 * @param state
	 *            the state
	 */
	public void deleteRoleFromUser(List userRoles, State state);

	/**
	 * Delete permission by role id.
	 * 
	 * @param roleId
	 *            the role id
	 * @param state
	 *            the state
	 */
	public void deletePermissionByRoleId(String roleId, State state);
	
	/**
	 * Delete permission by function id.
	 * 
	 * @param functionId
	 *            the function id
	 * @param state
	 *            the state
	 */
	public void deletePermissionByFunctionId(String functionId, State state);
	
	/**
	 * Delete user role by user id.
	 * 
	 * @param userId
	 *            the user id
	 * @param state
	 *            the state
	 */
	public void deleteUserRoleByUserId(String userId, State state);
	
	/**
	 * Delete user role by role id.
	 * 
	 * @param roleId
	 *            the role id
	 * @param state
	 *            the state
	 */
	public void deleteUserRoleByRoleId(String roleId, State state) ;
	
	/**
	 * Update user.
	 * 
	 * @param user
	 *            the user
	 * @param state
	 *            the state
	 */
	public void updateUser(User user, State state);
	
	/**
	 * Find role by user.
	 * 
	 * @param userId
	 *            the user id
	 * @param state
	 *            the state
	 * @return the list
	 */
	public List findRoleByUser(String userId, State state);
	
	/**
	 * Find function by user.
	 * 
	 * @param userId
	 *            the user id
	 * @param state
	 *            the state
	 * @return the list
	 */
	public List findFunctionByUser(String userId, State state);
	
	/**
	 * Delete role from user.
	 * 
	 * @param userRole
	 *            the user role
	 * @param state
	 *            the state
	 */
	public void deleteRoleFromUser(UserRole userRole, State state) ;
	
	/**
	 * Find other role by user.
	 * 
	 * @param hqlQuery
	 *            the hql query
	 * @param userId
	 *            the user id
	 * @param state
	 *            the state
	 * @return the page support
	 */
	public PageSupport findOtherRoleByUser(HqlQuery hqlQuery,String userId, State state);

    /**
	 * Save file.
	 * 
	 * @param file
	 *            the file
	 * @param state
	 *            the state
	 * @return the string
	 */
    public String saveFile(File file, State state);

	/**
	 * Delete file by id.
	 * 
	 * @param id
	 *            the id
	 * @param state
	 *            the state
	 */
	public void deleteFileById(String id, State state);
	
	/**
	 * Delete file.
	 * 
	 * @param file
	 *            the file
	 * @param state
	 *            the state
	 */
	public void deleteFile(File file, State state);
	
	/**
	 * Update file.
	 * 
	 * @param file
	 *            the file
	 * @param state
	 *            the state
	 */
	public void updateFile(File file, State state);
	
	/**
	 * Find all file.
	 * 
	 * @param cq
	 *            the cq
	 * @param state
	 *            the state
	 * @return the page support
	 */
	public PageSupport findAllFile(CriteriaQuery cq, State state);
	
	/**
	 * Find file by id.
	 * 
	 * @param id
	 *            the id
	 * @param state
	 *            the state
	 * @return the file
	 */
	public File findFileById(String id, State state);
	
	/**
	 * Save login history.
	 * 
	 * @param loginHistory
	 *            the login history
	 * @param state
	 *            the state
	 * @return the string
	 */
	public String saveLoginHistory(LoginHistory loginHistory, State state);
	
}