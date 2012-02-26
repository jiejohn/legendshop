/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.legendshop.core.security.GrantedFunction;
import com.legendshop.core.security.SecurityManager;
import com.legendshop.core.security.service.FunctionService;
import com.legendshop.model.entity.Function;
import com.legendshop.model.entity.Role;
import com.legendshop.model.entity.UserEntity;

/**
 * The Interface AuthService.
 */
public interface AuthService extends  UserDetailsService, SecurityManager, FunctionService{


	// 得到该用户的角色
	/**
	 * Gets the authorities by username query.
	 * 
	 * @param user
	 *            the user
	 * @return the authorities by username query
	 * @throws DataAccessException
	 *             the data access exception
	 */
	@SuppressWarnings("unchecked")
	public abstract Collection<GrantedAuthority> getAuthoritiesByUsernameQuery(UserEntity user)
			throws DataAccessException;

	// 得到该用户的权限
	/**
	 * Gets the function by username query.
	 * 
	 * @param user
	 *            the user
	 * @return the function by username query
	 * @throws DataAccessException
	 *             the data access exception
	 */
	@SuppressWarnings("unchecked")
	public abstract Collection<GrantedFunction> getFunctionByUsernameQuery(UserEntity user) throws DataAccessException;

	/**
	 * Gets the user by name.
	 * 
	 * @param name
	 *            the name
	 * @return the user by name
	 */
	@SuppressWarnings("unchecked")
	public abstract UserEntity getUserByName(String name);

	/**
	 * Find user by name.
	 * 
	 * @param name
	 *            the name
	 * @return the user entity
	 */
	@SuppressWarnings("unchecked")
	public abstract UserEntity findUserByName(String name);

	/**
	 * Find roles by user.
	 * 
	 * @param user
	 *            the user
	 * @return the list
	 */
	public abstract List<Role> findRolesByUser(UserEntity user);

	/* (non-Javadoc)
	 * @see com.legendshop.core.security.SecurityManager#loadUrlAuthorities()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public abstract Map<String, String> getUrlAuthorities();


	/**
	 * 从数据库中查找该角色Role和所拥有的Function.
	 * 
	 * @param authority
	 *            the authority
	 * @return the granted authority
	 */

	public abstract Role getgrantedAuthority(String authority);

	/**
	 * Find role by name.
	 * 
	 * @param authority
	 *            the authority
	 * @return the list
	 */
	public abstract List<Role> findRoleByName(String authority);

	/**
	 * Find functions by role.
	 * 
	 * @param role
	 *            the role
	 * @return the list
	 */
	public abstract List<Function> findFunctionsByRole(Role role);

}