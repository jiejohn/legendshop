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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.legendshop.business.service.AuthService;
import com.legendshop.core.security.GrantedFunction;
import com.legendshop.core.security.GrantedFunctionImpl;
import com.legendshop.core.security.cache.AuthorityBasedUserCache;
import com.legendshop.core.security.cache.FunctionCache;
import com.legendshop.core.security.cache.RoleByNameCache;
import com.legendshop.core.security.model.UserDetail;
import com.legendshop.model.entity.Function;
import com.legendshop.model.entity.Role;
import com.legendshop.model.entity.UserEntity;
import com.legendshop.util.AppUtils;

/**
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * 官方网站：http://www.legendesign.net
 * 
 */
public class AuthServiceImpl implements  AuthService {

	/** The log. */
	Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

	/** The function cache. */
	private FunctionCache functionCache;

	/** The role cache. */
	private RoleByNameCache roleCache;

	// 用户有哪些Role的cache
	/** The authority user cache. */
	private AuthorityBasedUserCache authorityUserCache;

	/** The jdbc template. */
	private JdbcTemplate jdbcTemplate;

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.AuthService#loadUserByUsername(java.lang.String)
	 */
	/**
	 * 从数据库中查找该用户User以及所拥有的role.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		log.debug("loadUserByUsername calling {}", username);
		UserEntity user = getUserByName(username);
		if (AppUtils.isBlank(user)) {
			return null;
		}
		Collection<GrantedAuthority> roles = getAuthoritiesByUsernameQuery(user);
		if (AppUtils.isBlank(roles)) {
			throw new UsernameNotFoundException("User has no GrantedAuthority");
		}
		Collection<GrantedFunction> functoins = getFunctionByUsernameQuery(user);
		User minuser = new UserDetail(username, user.getPassword(), getBoolean(user.getEnabled()), true, true, true,
				roles, functoins, user.getId());
		return minuser;
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

	// 得到该用户的角色
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.AuthService#getAuthoritiesByUsernameQuery(com.legendshop.model.entity.UserEntity)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Collection<GrantedAuthority> getAuthoritiesByUsernameQuery(UserEntity user) throws DataAccessException {
		Collection<GrantedAuthority> grantedAuthoritys = new ArrayList<GrantedAuthority>();
		if (user != null) {

			List roles = user.getRoles();
			Iterator it = roles.iterator();
			while (it.hasNext()) {
				GrantedAuthorityImpl gai = new GrantedAuthorityImpl(((Role) it.next()).getName());
				grantedAuthoritys.add(gai);
			}
		}
		log.debug("{} have roles number {}", user.getName(), grantedAuthoritys.size());
		return grantedAuthoritys;
	}

	// 得到该用户的权限
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.AuthService#getFunctionByUsernameQuery(com.legendshop.model.entity.UserEntity)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Collection<GrantedFunction> getFunctionByUsernameQuery(UserEntity user) throws DataAccessException {
		Collection<GrantedFunction> grantedFunctions = new ArrayList<GrantedFunction>();
		if (user != null) {
			log.debug("{} have functions number {}", user.getName(), grantedFunctions.size());
			List functions = user.getFunctions();
			Iterator it = functions.iterator();
			while (it.hasNext()) {
				GrantedFunctionImpl gfi = new GrantedFunctionImpl(((Function) it.next()).getName());
				grantedFunctions.add(gfi);
			}
		}

		return grantedFunctions;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.AuthService#getUserByName(java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public UserEntity getUserByName(String name) {
		if (log.isDebugEnabled()) {
			log.debug("getUserByName calling, name {}", name);
		}

		UserEntity user = findUserByName(name);
		if (user != null) {
			// 查找有该权限的角色和权限
			user.setRoles(findRolesByUser(user));
			user.setFunctions(findFunctionsByUser(user));
		}
		if (log.isDebugEnabled()) {
			log.debug("getUserByName calling with param {}, result {}", name, user);
		}
		return user;
	}

	/**
	 * Find functions by user.
	 * 
	 * @param user
	 *            the user
	 * @return the list
	 */
	private List<Function> findFunctionsByUser(UserEntity user) {
		return jdbcTemplate
				.query(
						"select f.* from ls_usr_role ur ,ls_role r,ls_perm p, ls_func f where ur.user_id= ? and ur.role_id=r.id and r.id=p.role_id and p.function_id=f.id",
						new Object[] { user.getId() }, new RowMapper<Function>() {
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

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.AuthService#findUserByName(java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public UserEntity findUserByName(String name) {
		return (UserEntity) jdbcTemplate.queryForObject("select * from ls_user where name = ?", new Object[] { name },
				new RowMapper() {
					@Override
					public Object mapRow(ResultSet rs, int index) throws SQLException {
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

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.AuthService#findRolesByUser(com.legendshop.model.entity.UserEntity)
	 */
	@Override
	public List<Role> findRolesByUser(UserEntity user) {
		// log.debug("findRolesByUser calling {}" , user);
		return jdbcTemplate.query("select r.* from ls_usr_role ur ,ls_role r where ur.user_id= ? and ur.role_id=r.id",
				new Object[] { user.getId() }, new RowMapper<Role>() {
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

	/* (non-Javadoc)
	 * @see com.legendshop.core.security.SecurityManager#loadUrlAuthorities()
	 */
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.AuthService#getUrlAuthorities()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Map<String, String> getUrlAuthorities() {
		Map<String, String> urlAuthorities = new HashMap<String, String>();
		// urlAuthorities = jdbcTemplate.queryForMap("select * from leg_function");
		return urlAuthorities;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.core.security.FunctionService#getFunctionsByRoles(java.util.Collection)
	 */
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.AuthService#getFunctionsByRoles(java.util.Collection)
	 */
	@Override
	public Collection<GrantedFunction> getFunctionsByRoles(Collection<? extends GrantedAuthority> roles) {
		// log.debug("getFunctionsByRoles calling {}" , roles);
		if (null == roles) {
			throw new IllegalArgumentException("Granted Roles cannot be null");
		}
		Collection<GrantedFunction> grantedFunctions = new HashSet<GrantedFunction>();
		for (GrantedAuthority grantedAuthority : roles) {
			Role role = roleCache.getRoleByRoleNameCache(grantedAuthority.getAuthority()); //
			if (role == null) {
				role = getgrantedAuthority(grantedAuthority.getAuthority());
				if (role != null) {
					roleCache.putRoleInCache(role);
				} else {
					return grantedFunctions;
				}
			}
			if (role != null) {
				List<Function> functions = role.getFunctions();
				for (Function function : functions) {
					grantedFunctions.add(new GrantedFunctionImpl(function.getName()));
				}
			}
		}
		return grantedFunctions;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.core.security.FunctionService#getFunctionsByUser(com.legendshop.model.entity.UserEntity)
	 */
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.AuthService#getFunctionsByUser(com.legendshop.model.entity.UserEntity)
	 */
	@Override
	public Collection<GrantedFunction> getFunctionsByUser(UserEntity user) {
		// log.debug("getFunctionsByUser calling {}" , user);
		if (null == user)
			throw new IllegalArgumentException("User Entity cannot be null");
		Collection<GrantedFunction> grantedFunctions = new HashSet<GrantedFunction>();
		List<Function> functions = user.getFunctions();
		for (Iterator<Function> it = functions.iterator(); it.hasNext();) {
			Function function = it.next();
			GrantedFunction grantedFunction = new GrantedFunctionImpl(function.getName());
			grantedFunctions.add(grantedFunction);
		}
		return grantedFunctions;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.AuthService#getgrantedAuthority(java.lang.String)
	 */

	@Override
	public Role getgrantedAuthority(String authority) {
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

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.AuthService#findRoleByName(java.lang.String)
	 */
	@Override
	public List<Role> findRoleByName(String authority) {
		return jdbcTemplate.query("select * from ls_role where name = ?", new Object[] { authority },
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

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.AuthService#findFunctionsByRole(com.legendshop.model.entity.Role)
	 */
	@Override
	public List<Function> findFunctionsByRole(Role role) {
		// log.debug("findFunctionsByRole calling {}" , role);
		return jdbcTemplate.query("select f.* from ls_perm p ,ls_func f where p.role_id= ? and p.function_id=f.id",
				new Object[] { role.getId() }, new RowMapper<Function>() {
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

	/**
	 * Sets the role cache.
	 * 
	 * @param roleCache
	 *            the new role cache
	 */
	public void setRoleCache(RoleByNameCache roleCache) {
		this.roleCache = roleCache;
	}

	/**
	 * Sets the authority user cache.
	 * 
	 * @param authorityUserCache
	 *            the new authority user cache
	 */
	public void setAuthorityUserCache(AuthorityBasedUserCache authorityUserCache) {
		this.authorityUserCache = authorityUserCache;
	}

	/**
	 * Sets the function cache.
	 * 
	 * @param functionCache
	 *            the new function cache
	 */
	public void setFunctionCache(FunctionCache functionCache) {
		this.functionCache = functionCache;
	}

}
