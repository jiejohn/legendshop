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

import com.legendshop.command.framework.JCFException;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
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

public class RoleDao extends BaseDaoImpl {
	
	/** The Constant logger. */
	private static final Log logger = LogFactory.getLog(RoleDao.class);
	
	/** The find role by function. */
	private String findRoleByFunction;
	
	/** The find role by user id. */
	private	String findRoleByUserId;
	
	/** The find other role by user. */
	private	String findOtherRoleByUser;
	
	/** The find other role by user count. */
	private String findOtherRoleByUserCount;
 	
	
	/**
	 * Sets the find other role by user.
	 * 
	 * @param findOtherRoleByUser
	 *            the new find other role by user
	 */
	public void setFindOtherRoleByUser(String findOtherRoleByUser) {
		this.findOtherRoleByUser = findOtherRoleByUser;
	}

	/**
	 * Sets the find role by function.
	 * 
	 * @param findRoleByFunction
	 *            the new find role by function
	 */
	public void setFindRoleByFunction(String findRoleByFunction) {
		this.findRoleByFunction = findRoleByFunction;
	}

	/**
	 * Sets the find role by user id.
	 * 
	 * @param findRoleByUserId
	 *            the new find role by user id
	 */
	public void setFindRoleByUserId(String findRoleByUserId) {
		this.findRoleByUserId = findRoleByUserId;
	}

	/**
	 * Find role by function.
	 * 
	 * @param functionId
	 *            the function id
	 * @return the list
	 * @throws JCFException
	 *             the jCF exception
	 */
	public List FindRoleByFunction(String functionId) throws JCFException
	{
 		logger.info(StringUtil.convert(findRoleByFunction, new String[]{functionId}));
		return find(findRoleByFunction, functionId);
	}
 	
 	/**
		 * Delete permission by role id.
		 * 
		 * @param permissions
		 *            the permissions
		 */
	 public void DeletePermissionByRoleId(List permissions){
		 deleteAll(permissions);
	}
 	
	 /**
		 * Find role by user id.
		 * 
		 * @param userId
		 *            the user id
		 * @return the list
		 * @throws JCFException
		 *             the jCF exception
		 */
	 public List FindRoleByUserId(String userId) throws JCFException
	{
 		logger.info(StringUtil.convert(findRoleByUserId, new String[]{userId}));
		return find(findRoleByUserId, userId);
	}
 	
 	/**
		 * Find other role by user.
		 * 
		 * @param hqlQuery
		 *            the hql query
		 * @param userId
		 *            the user id
		 * @return the page support
		 * @throws JCFException
		 *             the jCF exception
		 */
	 public PageSupport FindOtherRoleByUser(final HqlQuery hqlQuery,String userId) throws JCFException
	{
 		logger.info(StringUtil.convert(findOtherRoleByUser, new String[]{userId}));
 		hqlQuery.setQueryString(findOtherRoleByUser);
 		hqlQuery.setAllCountString(findOtherRoleByUserCount);
 		hqlQuery.addParams(userId);
 		//hqlQuery.setParam(new Object[]{userId});
 		//hqlQuery.setTypes(new Type[]{Hibernate.STRING});

 		return find(hqlQuery);
     }

	/**
	 * Sets the find other role by user count.
	 * 
	 * @param findOtherRoleByUserCount
	 *            the new find other role by user count
	 */
	public void setFindOtherRoleByUserCount(String findOtherRoleByUserCount) {
		this.findOtherRoleByUserCount = findOtherRoleByUserCount;
	}
	
 }

