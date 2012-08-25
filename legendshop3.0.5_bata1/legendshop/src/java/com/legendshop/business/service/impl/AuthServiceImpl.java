/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.legendshop.business.service.AuthService;
import com.legendshop.core.security.GrantedAuthorityImpl;
import com.legendshop.core.security.GrantedFunction;
import com.legendshop.core.security.GrantedFunctionImpl;
import com.legendshop.core.security.model.UserDetail;
import com.legendshop.model.entity.Function;
import com.legendshop.model.entity.Role;
import com.legendshop.model.entity.UserEntity;
import com.legendshop.util.AppUtils;

/**
 * 权限管理服务类
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * 官方网站：http://www.legendesign.net
 * 
 */
public class AuthServiceImpl implements  AuthService {

	/** The log. */
	Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

	/** The jdbc template. */
	private JdbcTemplate jdbcTemplate;

	/**
	 * 从数据库中查找该用户User以及所拥有的角色和权限.
	 * 
	 * @param username
	 *            the username
	 * @return the user details
	 * @throws UsernameNotFoundException
	 *             the username not found exception
	 * @throws DataAccessException
	 *             the data access exception
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		
		//获取用户信息
		UserEntity user = findUserByName(username);
		if (log.isDebugEnabled()) {
			log.debug("getUserByName calling with name {}, result {}", username, user);
		}
		if (AppUtils.isBlank(user)) {
			return null;
		}
		//获取角色信息
		Collection<GrantedAuthority> roles = findRolesByUser(user.getId());
		if (AppUtils.isBlank(roles)) {
			throw new UsernameNotFoundException("User has no GrantedAuthority");
		}
		
		//获取权限信息
		Collection<GrantedFunction> functoins = findFunctionsByUser(user.getId());
		User minuser = new UserDetail(username, user.getPassword(), getBoolean(user.getEnabled()), true, true, true,
				roles, functoins, user.getId());
		return minuser;
	}

	/**
	 * 
	 * 拿到角色集合对应的权限集合
	 *
	 */
	@Override
	@Cacheable(value="GrantedFunction")
	public Collection<GrantedFunction> getFunctionsByRoles(Collection<? extends GrantedAuthority> roles) {
		log.debug("getFunctionsByRoles calling {}" , roles);
		if (null == roles) {
			throw new IllegalArgumentException("Granted Roles cannot be null");
		}
		Collection<GrantedFunction> grantedFunctions = new HashSet<GrantedFunction>();
		for (GrantedAuthority grantedAuthority : roles) {
			Role role = getGrantedAuthority(grantedAuthority.getAuthority());
			if (role != null) {
				List<Function> functions = role.getFunctions();
				for (Function function : functions) {
					grantedFunctions.add(new GrantedFunctionImpl(function.getName()));
				}
			}
		}
		return grantedFunctions;
	}

	/**
	 * Gets the boolean.
	 * 
	 * @param b
	 *            the b
	 * @return the boolean
	 */
	private boolean getBoolean(String b) {
		return "1".endsWith(b) ? true : false;
	}



	/**
	 * Find functions by user.
	 * 
	 * @param user
	 *            the user
	 * @return the list
	 */
	private List<GrantedFunction> findFunctionsByUser(String userId) {
		String sql = "select f.name from ls_usr_role ur ,ls_role r,ls_perm p, ls_func f where r.enabled = '1' and ur.user_id= ? and ur.role_id=r.id and r.id=p.role_id and p.function_id=f.id";
		log.debug("findFunctionsByUser,run sql {}, userId {}" ,sql, userId);
		return jdbcTemplate.query(sql,new Object[] { userId }, new RowMapper<GrantedFunction>() {
							@Override
							public GrantedFunction mapRow(ResultSet rs, int index) throws SQLException {
								return new GrantedFunctionImpl(rs.getString("name"));
							}
						});
	}


	/**
	 * 找到用户
	 */
	private UserEntity findUserByName(String name) {
		String sql = "select * from ls_user where enabled = '1' and name = ?";
		log.debug("findUserByName, run sql {}, name {}" , sql,name);
		return jdbcTemplate.queryForObject(sql, new Object[] { name },
				new RowMapper<UserEntity>() {
					@Override
					public UserEntity mapRow(ResultSet rs, int index) throws SQLException {
						UserEntity user = new UserEntity();
						user.setEnabled(rs.getString("enabled"));
						user.setId(rs.getString("id"));
						user.setName(rs.getString("name"));
						user.setNote(rs.getString("note"));
						user.setPassword(rs.getString("password"));
						return user;
					}
				});
	}

	/**
	 * 根据用户ID取得对应的角色
	 */
	private List<GrantedAuthority> findRolesByUser(String userId) {
		String sql = "select distinct r.name from ls_usr_role ur ,ls_role r where r.enabled ='1' and ur.user_id= ? and ur.role_id=r.id";
		log.debug("findRolesByUser,run sql {}, userId {}" , sql,userId);
		return jdbcTemplate.query(sql,new Object[] {userId }, new RowMapper<GrantedAuthority>() {
					@Override
					public GrantedAuthority mapRow(ResultSet rs, int index) throws SQLException {
						return new GrantedAuthorityImpl(rs.getString("name"));
					}
				});
	}


	

	/**
	 * 根据角色名拿到角色对象，包括角色对应的权限
	 */
	private Role getGrantedAuthority(String authority) {
		log.debug("getgrantedAuthority calling {}" , authority);
		List<Role> roles = findRoleByName(authority);
		if (AppUtils.isBlank(roles)) {
			log.warn("authority {} can not get Role", authority);
			return null;
		}else{
			Role role = roles.iterator().next();
			if (role != null) {
				role.setFunctions(findFunctionsByRole(role));
				return role;
			} else {
				return null;
			}
		}
	}

	/**
	 * 根据用户名拿到对应的角色
	 */
	private List<Role> findRoleByName(String authority) {
		String sql = "select * from ls_role where enabled = '1' and name = ?";
		log.debug("findRoleByName run sql {}, authority {}" , authority);
		return jdbcTemplate.query(sql, new Object[] { authority },
				new RowMapper<Role>() {
					@Override
					public Role mapRow(ResultSet rs, int index) throws SQLException {
						Role role = new Role();
						role.setEnabled(rs.getString("enabled"));
						role.setId(rs.getString("id"));
						role.setName(rs.getString("name"));
						role.setNote(rs.getString("note"));
						role.setRoleType(rs.getString("role_type"));
						return role;
					}
				});
	}
	

	/**
	 * 根据角色拿到对应的权限
	 */
	private List<Function> findFunctionsByRole(Role role) {
		String sql = "select f.* from ls_perm p ,ls_func f where p.role_id= ? and p.function_id=f.id";
	    log.debug("findFunctionsByRole,run sql {}, role {}" ,sql,role.getName());
		return jdbcTemplate.query(sql,new Object[] { role.getId() }, new RowMapper<Function>() {
					@Override
					public Function mapRow(ResultSet rs, int index) throws SQLException {
						Function function = new Function();
						function.setId(rs.getString("id"));
						function.setName(rs.getString("name"));
						function.setNote(rs.getString("note"));
						function.setProtectFunction(rs.getString("protect_function"));
						function.setUrl(rs.getString("url"));
						return function;
					}
				});
	}

	/**
	 * Sets the jdbc template.
	 * 
	 * @param jdbcTemplate
	 *            the new jdbc template
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
