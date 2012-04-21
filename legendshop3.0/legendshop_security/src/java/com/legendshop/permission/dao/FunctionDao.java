/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.permission.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.legendshop.command.framework.JCFException;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Permission;
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
public class FunctionDao extends BaseDaoImpl {
	
	/** The Constant logger. */
	private static final Log logger = LogFactory.getLog(FunctionDao.class);
	
	/** The find function by role id. */
	private  String findFunctionByRoleId;
	
	/** The find other function by role id. */
	private	String findOtherFunctionByRoleId;
	
	/** The find other function by role id hql. */
	private	String findOtherFunctionByRoleIdHQL;
	
	/** The find other function by role id hql count. */
	private String findOtherFunctionByRoleIdHQLCount;
	
	
	/**
	 * Sets the find function by role id.
	 * 
	 * @param findFunctionByRoleId
	 *            the new find function by role id
	 */
	public void setFindFunctionByRoleId(String findFunctionByRoleId) {
		this.findFunctionByRoleId = findFunctionByRoleId;
	}

	/**
	 * Sets the find other function by role id.
	 * 
	 * @param findOtherFunctionByRoleId
	 *            the new find other function by role id
	 */
	public void setFindOtherFunctionByRoleId(String findOtherFunctionByRoleId) {
		this.findOtherFunctionByRoleId = findOtherFunctionByRoleId;
	}

	/**
	 * Sets the find other function by role id hql.
	 * 
	 * @param findOtherFunctionByRoleIdHQL
	 *            the new find other function by role id hql
	 */
	public void setFindOtherFunctionByRoleIdHQL(String findOtherFunctionByRoleIdHQL) {
		this.findOtherFunctionByRoleIdHQL = findOtherFunctionByRoleIdHQL;
	}

	/**
	 * Find function by role id.
	 * 
	 * @param roleId
	 *            the role id
	 * @return the list
	 */
	public List FindFunctionByRoleId(String roleId)
	{
 		logger.info(StringUtil.convert(findFunctionByRoleId, new String[]{roleId}));
		return find(findFunctionByRoleId, roleId);
	}
	
 	/**
		 * Find other function by role id.
		 * 
		 * @param roleId
		 *            the role id
		 * @return the list
		 */
	 public List FindOtherFunctionByRoleId(String roleId)
	{
 		logger.info(StringUtil.convert(findOtherFunctionByRoleId, new String[]{roleId}));
		return find(findOtherFunctionByRoleId, roleId);
	}
 	
 	/**
		 * Find other function by role id.
		 * 
		 * @param hqlQuery
		 *            the hql query
		 * @param roleId
		 *            the role id
		 * @return the page support
		 * @throws JCFException
		 *             the jCF exception
		 */
	 public PageSupport FindOtherFunctionByRoleId(final HqlQuery hqlQuery,String roleId) throws JCFException
	{
 		logger.info(StringUtil.convert(findOtherFunctionByRoleIdHQL, new String[]{roleId}));
 		hqlQuery.setQueryString(findOtherFunctionByRoleIdHQL);
 		hqlQuery.setAllCountString(findOtherFunctionByRoleIdHQLCount);
 		hqlQuery.setParam(new Object[]{roleId});
 		//hqlQuery.setTypes(new Type[]{Hibernate.STRING});
 		return find(hqlQuery);
     }
 	 	
 	/**
		 * Save permissions.
		 * 
		 * @param permissions
		 *            the permissions
		 * @throws Exception
		 *             the exception
		 */
	 @SuppressWarnings("unchecked")
	public void savePermissions(final List permissions) throws Exception{
		getHibernateTemplate().execute(
				new HibernateCallback() {
			    	public Object doInHibernate(Session session) throws HibernateException, SQLException {
			    		Connection con=session.connection();
			    		PreparedStatement pstmt = null;
			     		for(int i=0;i<permissions.size();i++){
			     			Permission p=(Permission)permissions.get(i);
			     	 			pstmt = con.prepareStatement("insert into ls_perm (role_id,function_id) values (?,?)");
			     	 			logger.info(StringUtil.convert("insert into ls_perm (role_id,function_id) values (?,?)",
			     	 					new String[]{p.getId().getRoleId(),p.getId().getFunctionId()}));
			     	 			pstmt.setString(1, p.getId().getRoleId());
			     	 			pstmt.setString(2, p.getId().getFunctionId());
			     				pstmt.executeUpdate();	
			     		}
						return Boolean.TRUE;
							}		
			          }
				);
 		
 	}
// 	public int DeletePermissionByFunctionId(String functionId){
//			return getHibernateTemplate().bulkUpdate("delete  from Permission where id.functionId = "+functionId);
// 	}
 	
 	/**
 * Delete permission by function id.
 * 
 * @param permissions
 *            the permissions
 */
public void DeletePermissionByFunctionId(List permissions){
 		logger.info("DeletePermissionByFunctionId with size "+permissions.size());
		 deleteAll(permissions);
	}

	/**
	 * Sets the find other function by role id hql count.
	 * 
	 * @param findOtherFunctionByRoleIdHQLCount
	 *            the new find other function by role id hql count
	 */
	public void setFindOtherFunctionByRoleIdHQLCount(
			String findOtherFunctionByRoleIdHQLCount) {
		this.findOtherFunctionByRoleIdHQLCount = findOtherFunctionByRoleIdHQLCount;
	}
 	}