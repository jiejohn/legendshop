/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.permission.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.legendshop.business.permission.common.ErrorCode;
import com.legendshop.business.permission.common.ServiceConsts;
import com.legendshop.business.permission.service.RightDelegate;
import com.legendshop.command.framework.JCFException;
import com.legendshop.command.framework.Request;
import com.legendshop.command.framework.Response;
import com.legendshop.command.framework.State;
import com.legendshop.command.framework.facade.AbstractBizDelegate;
import com.legendshop.command.framework.facade.DelegateUtil;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.ClientException;
import com.legendshop.model.entity.Function;
import com.legendshop.model.entity.LoginHistory;
import com.legendshop.model.entity.Permission;
import com.legendshop.model.entity.Role;
import com.legendshop.model.entity.User;
import com.legendshop.model.entity.UserRole;
import com.legendshop.util.AppUtils;
/**
 * 
 * 权限管理代理
 */
public class RightDelegateImpl extends AbstractBizDelegate implements RightDelegate {
	
	/**
	 * Instantiates a new right delegate impl.
	 */
	public RightDelegateImpl() {
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.permission.RightDelegate#findWorkflow(com.legendshop.command.framework.State)
	 */
	@Override
	public boolean findWorkflow(State state) {
		Request req = new Request();
		req.setServiceName("RunByResultProcessor");
		try {
			Response resp = getDelegate().execute(req);
			DelegateUtil.setState(state, resp);
			return true;
		} catch (Exception e) {
			DelegateUtil.handleException(e, "workflow", state);
		}
		return false;
	}

	/**
	 * 从数据库中查找该角色Role所拥有的Function.
	 * 
	 * @param authority
	 *            the authority
	 * @param state
	 *            the state
	 * @return the role
	 */
	@Override
	public Role findgrantedAuthorityFromDataBase(String authority, State state) {
		try {
			return (Role)getDelegate().execute("authority", authority, 
					ServiceConsts.FindgrantedAuthorityFromDataBaseProcessor, "resultObject", state);
		} catch (Exception e) {
			DelegateUtil.handleException(e, "findgrantedAuthorityFromDataBase", state);
			return null;
		}
	}
	
	/**
	 * 从数据库中查找名为protectFunction的Function以及拥有这个function的角色.
	 * 
	 * @param protectfunction
	 *            the protectfunction
	 * @param state
	 *            the state
	 * @return the function
	 */
	@Override
	public Function findFunctionFromDataBase(String protectfunction,
			State state) {
		try {
			return (Function)getDelegate().execute("protectfunction", protectfunction, 
					ServiceConsts.FindFunctionFromDataBaseProcessor, "resultObject", state);
		} catch (Exception e) {
			DelegateUtil.handleException(e, "findFunctionFromDataBase", state);
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.permission.RightDelegate#findRoleByFunction(java.lang.String, com.legendshop.command.framework.State)
	 */
	@Override
	public List findRoleByFunction(String functionId, State state){
		
		try {
			return (List)getDelegate().execute("functionId", functionId, 
					ServiceConsts.FindRoleByFunctionProcessor, "roles", state);
		} catch (Exception e) {
			throw new ClientException(e,"findRoleByFunction error");
		}
	}
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.permission.RightDelegate#findRoleByUser(java.lang.String, com.legendshop.command.framework.State)
	 */
	@Override
	public List findRoleByUser(String userId, State state){
		try {
			return (List)getDelegate().execute("userId", userId, 
					ServiceConsts.FindRoleByUserProcessor, "roles", state);
		} catch (Exception e) {
			DelegateUtil.handleException(e, "findRoleByUser", state);
			return null;
		}
	}
	
	/**
	 * 从数据库中查找该用户User以及所拥有的role.
	 * 
	 * @param name
	 *            the name
	 * @param state
	 *            the state
	 * @return the user
	 */
	@Override
	public User findUserByNameFromDataBase(String name, State state) {	
		try {
			return (User)getDelegate().execute("name", name, 
					ServiceConsts.FindUserByNameFromDataBaseProcessor, "resultObject", state);
		} catch (Exception e) {
			DelegateUtil.handleException(e, "findUserByNameFromDataBase", state);
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.permission.RightDelegate#saveFunction(com.legendshop.model.entity.Function, com.legendshop.command.framework.State)
	 */
	@Override
	public String saveFunction(Function function, State state) {
		try {
			return (String)getDelegate().execute("function", function, 
					ServiceConsts.SaveFunctionProcessor, "id", state);
		} catch (Exception e) {
			DelegateUtil.handleException(e, "saveFunction", state);
			return null;
		}
		
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.permission.RightDelegate#saveFunctionToRole(com.legendshop.model.entity.Permission, com.legendshop.command.framework.State)
	 */
	@Override
	public boolean saveFunctionToRole(Permission permission, State state) {
		try {
			getDelegate().execute("permission", permission, 
					ServiceConsts.SaveFunctionToRoleProcessor, "id", state);
			return true;
		} catch (Exception e) {
			DelegateUtil.handleException(e, "saveFunctionToRole", state);
			return false;
		}
	}
	

	/* (non-Javadoc)
	 * @see com.legendshop.business.permission.RightDelegate#saveFunctionsToRole(java.util.List, com.legendshop.command.framework.State)
	 */
	@Override
	public void saveFunctionsToRole(List permissions, State state) {
		if (DelegateUtil.isNullParam(permissions, "permissions", state)) {
			return;
		}
		Request req = new Request();
		req.setServiceName(ServiceConsts.SaveFunctionsToRoleProcessor);
		req.setValue("permissions", permissions);
		try {
			Response resp = getDelegate().execute(req);
			DelegateUtil.setState(state, resp);
		} catch (Exception e) {
			DelegateUtil.handleException(e, "saveFunctionsToRole", state);
		}
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.permission.RightDelegate#updateFunction(com.legendshop.model.entity.Function, com.legendshop.command.framework.State)
	 */
	@Override
	public void updateFunction(Function function, State state) {
		if (DelegateUtil.isNullParam(function, "function", state)) {
			return;
		}
		Request req = new Request();
		req.setServiceName(ServiceConsts.UpdateFunctionProcessor);
		req.setValue("function", function);
		try {
			Response resp = getDelegate().execute(req);
			DelegateUtil.setState(state, resp);
		} catch (Exception e) {
			DelegateUtil.handleException(e, "updateFunction", state);
		}
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.permission.RightDelegate#isUserExist(java.lang.String, com.legendshop.command.framework.State)
	 */
	@Override
	public boolean isUserExist(String name, State state) {
		if (DelegateUtil.isNullParam(name, "name", state)) {
			return false;
		}
		Request req = new Request();
		req.setServiceName(ServiceConsts.IsUserExistProcessor);
		req.setValue("name", name);

		try {
			Response resp = getDelegate().execute(req);
			DelegateUtil.setState(state, resp);
			return ((Boolean) resp.getValue("resultBoolean")).booleanValue();
		} catch (Exception e) {
			DelegateUtil.handleException(e, "isUserExist", state);
			return false;
		}
	}


	/* (non-Javadoc)
	 * @see com.legendshop.business.permission.RightDelegate#saveUser(com.legendshop.model.entity.User, com.legendshop.command.framework.State)
	 */
	@Override
	public String saveUser(User user, State state) {
		try {
			return (String)getDelegate().execute("user", user, 
					ServiceConsts.SaveUserProcessor,"result", state);
		} catch (Exception e) {
			DelegateUtil.handleException(e, "saveUser", state);
			return null;
		}
		
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.permission.RightDelegate#findAllUser(com.legendshop.core.dao.support.CriteriaQuery, com.legendshop.command.framework.State)
	 */
	@Override
	public PageSupport findAllUser(CriteriaQuery cq, State state) {
		if (DelegateUtil.isNullParam(cq, "CriteriaQuery", state)) {
			return null;
		}
		Request req = new Request();
		req.setServiceName(ServiceConsts.FindAllUserProcessor);
		req.setValue("CriteriaQuery", cq);
		try {
			Response resp = getDelegate().execute(req);
			DelegateUtil.setState(state, resp);
			return (PageSupport) (resp.getValue("PageSupport"));
		} catch (Exception e) {
			DelegateUtil.handleException(e, "findAllUser", state);
			return null;
		}
		
	}
	
	/**
	 * 根据FunctionId删除功能点，同时删除function和role的对应关系 deletePermissionByFunctionId.
	 * 
	 * @param functionId
	 *            the function id
	 * @param state
	 *            the state
	 * @return true, if successful
	 */
	@Override
	public boolean deleteFunctionById(String functionId, State state) {
		if (DelegateUtil.isNullParam(functionId, "functionId", state)) {
			return false;
		}
		Request req = new Request();
		req.setServiceName(ServiceConsts.DeleteFunctionByIdProcessor);
		req.setValue("functionId", functionId);
		try {
			Response resp = getDelegate().execute(req);
			DelegateUtil.setState(state, resp);
			return ((Boolean) resp.getValue("resultBoolean")).booleanValue();
		} catch (Exception e) {
			DelegateUtil.handleException(e, "deleteFunctionById", state);
			return false;
		}		
	}
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.permission.RightDelegate#deleteFunction(com.legendshop.model.entity.Function, com.legendshop.command.framework.State)
	 */
	@Override
	public void deleteFunction(Function function, State state) {
		if (DelegateUtil.isNullParam(function, "function", state)) {
			return;
		}
		Request req = new Request();
		req.setServiceName(ServiceConsts.DeleteFunctionProcessor);
		req.setValue("function", function);
		try {
			Response resp = getDelegate().execute(req);
			DelegateUtil.setState(state, resp);
		} catch (Exception e) {
			DelegateUtil.handleException(e, "deleteFunction", state);
		}		
	}
	
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.permission.RightDelegate#findAllFunction(com.legendshop.core.dao.support.CriteriaQuery, com.legendshop.command.framework.State)
	 */
	@Override
	public PageSupport findAllFunction(CriteriaQuery cq, State state) {
		if (DelegateUtil.isNullParam(cq, "CriteriaQuery", state)) {
			return null;
		}
		Request req = new Request();
		req.setServiceName(ServiceConsts.FindAllFunctionProcessor);
		req.setValue("CriteriaQuery", cq);
		try {
			Response resp = getDelegate().execute(req);
			DelegateUtil.setState(state, resp);
			return (PageSupport) (resp.getValue("PageSupport"));
		} catch (Exception e) {
			DelegateUtil.handleException(e, "findAllFunction", state);
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.permission.RightDelegate#findFunctionById(java.lang.String, com.legendshop.command.framework.State)
	 */
	@Override
	public Function findFunctionById(String id, State state) {
		if (DelegateUtil.isNullParam(id, "id", state)) {
			return null;
		}
		Request req = new Request();
		req.setServiceName(ServiceConsts.FindFunctionByIdProcessor);
		req.setValue("id", id);
		try {
			Response resp = getDelegate().execute(req);
			DelegateUtil.setState(state, resp);
			Function function = (Function) resp.getValue("function");
			DelegateUtil.setState(state, resp);
			return function;
		} catch (Exception e) {
			DelegateUtil.handleException(e, "findFunctionById", state);
			return null;
		}
	}
	
	/**
	 * 查找该用户的所有权限.
	 * 
	 * @param userId
	 *            the user id
	 * @param state
	 *            the state
	 * @return the list
	 */
	@Override
	public List findFunctionByUser(String userId, State state) {
		try {
			return (List)getDelegate().execute("userId", userId, 
					ServiceConsts.FindFunctionByUserProcessor,"functions", state);
		} catch (Exception e) {
			DelegateUtil.handleException(e, "findFunctionByUser", state);
			return null;
		}
	}
	
	/**
	 * 查找该用户的还没有授权的角色，用来增加角色.
	 * 
	 * @param hqlQuery
	 *            the hql query
	 * @param userId
	 *            the user id
	 * @param state
	 *            the state
	 * @return the page support
	 */
	@Override
	public PageSupport findOtherRoleByUser(HqlQuery hqlQuery,String userId, State state) {	
		if (DelegateUtil.isNullParam(hqlQuery, "hqlQuery", state)||DelegateUtil.isNullParam(userId, "userId", state)) {
			return null;
		}
		Request req = new Request();
		req.setServiceName(ServiceConsts.FindOtherRoleByUserProcessor);
		req.setValue("hqlQuery", hqlQuery);
		req.setValue("userId", userId);
		try {
			Response resp = getDelegate().execute(req);
			DelegateUtil.setState(state, resp);
			return(PageSupport)resp.getValue("roles");
		} catch (Exception e) {
			DelegateUtil.handleException(e, "findOtherRoleByUser", state);
			return null;
		}
	}
	
	
    /* (non-Javadoc)
     * @see com.legendshop.business.permission.RightDelegate#saveRole(com.legendshop.model.entity.Role, com.legendshop.command.framework.State)
     */
    @Override
	public String saveRole(Role role, State state) {
		if (DelegateUtil.isNullParam(role, "role", state)) {
			return null;
		}
		Request req = new Request();
		req.setServiceName(ServiceConsts.SaveRoleProcessor);
		req.setValue("role", role);
		try {
			Response resp = getDelegate().execute(req);
			DelegateUtil.setState(state, resp);
			return ((String) resp.getValue("result"));
		} catch (Exception e) {
			DelegateUtil.handleException(e, "saveRole", state);
			return null;
		}
	}
	
	/**
	 * 删除角色，同时删除该角色对应的权限关系.deletePermissionByRoleId
	 * 
	 * @param roleId
	 *            the role id
	 * @param state
	 *            the state
	 */
	@Override
	public void deleteRoleById(String roleId, State state) {
		if (DelegateUtil.isNullParam(roleId, "roleId", state)) {
			return;
		}
		Request req = new Request();
		req.setServiceName(ServiceConsts.DeleteRoleByIdProcessor);
		req.setValue("roleId",roleId);
		try {
			Response resp = getDelegate().execute(req);
			DelegateUtil.setState(state, resp);
		} catch (Exception e) {
			DelegateUtil.handleException(e, "deleteRoleById", state);
		}		
	}
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.permission.RightDelegate#deleteRole(com.legendshop.model.entity.Role, com.legendshop.command.framework.State)
	 */
	@Override
	public void deleteRole(Role role, State state) {
		if (DelegateUtil.isNullParam(role, "role", state)) {
			return;
		}
		Request req = new Request();
		req.setServiceName(ServiceConsts.DeleteRoleProcessor);
		req.setValue("role", role);
		try {
			Response resp = getDelegate().execute(req);
			DelegateUtil.setState(state, resp);
		} catch (Exception e) {
			DelegateUtil.handleException(e, "deleteRole", state);
		}		
	}
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.permission.RightDelegate#updateRole(com.legendshop.model.entity.Role, com.legendshop.command.framework.State)
	 */
	@Override
	public void updateRole(Role role, State state) {
		if (DelegateUtil.isNullParam(role, "role", state)) {
			return;
		}
		Request req = new Request();
		req.setServiceName(ServiceConsts.UpdateRoleProcessor);
		req.setValue("role", role);
		try {
			Response resp = getDelegate().execute(req);
			DelegateUtil.setState(state, resp);
		} catch (Exception e) {
			DelegateUtil.handleException(e, "updateRole", state);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.permission.RightDelegate#updateUser(com.legendshop.model.entity.User, com.legendshop.command.framework.State)
	 */
	@Override
	public void updateUser(User user, State state) {
		try {
			getDelegate().execute("user", user, 
					ServiceConsts.UpdateUserProcessor, state);
		} catch (Exception e) {
			DelegateUtil.handleException(e, "updateUser", state);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.permission.RightDelegate#findAllRole(com.legendshop.core.dao.support.CriteriaQuery, com.legendshop.command.framework.State)
	 */
	@Override
	public PageSupport findAllRole(CriteriaQuery cq, State state) {
		if (DelegateUtil.isNullParam(cq, "CriteriaQuery", state)) {
			return null;
		}
		Request req = new Request();
		req.setServiceName(ServiceConsts.FindAllRoleProcessor);
		req.setValue("CriteriaQuery", cq);
		try {
			Response resp = getDelegate().execute(req);
			DelegateUtil.setState(state, resp);
			return (PageSupport) (resp.getValue("PageSupport"));
		} catch (Exception e) {
			DelegateUtil.handleException(e, "findAllRole", state);
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.permission.RightDelegate#findRoleById(java.lang.String, com.legendshop.command.framework.State)
	 */
	@Override
	public Role findRoleById(String id, State state) {
		if (DelegateUtil.isNullParam(id, "id", state)) {
			return null;
		}
		Request req = new Request();
		req.setServiceName(ServiceConsts.FindRoleByIdProcessor);
		req.setValue("id", id);
		try {
			Response resp = getDelegate().execute(req);
			Role role = (Role) resp.getValue("role");
			DelegateUtil.setState(state, resp);
			return role;
		} catch (Exception e) {
			DelegateUtil.handleException(e, "findRoleById", state);
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.permission.RightDelegate#findFunctionByRoleId(java.lang.String, com.legendshop.command.framework.State)
	 */
	@Override
	public List findFunctionByRoleId(String roleId,State state){
		if (DelegateUtil.isNullParam(roleId, "roleId", state)) {
			return null;
		}
		Request req = new Request();
		req.setServiceName(ServiceConsts.FindFunctionByRoleIdProcessor);
		req.setValue("roleId", roleId);
		try {
			Response resp = getDelegate().execute(req);
			DelegateUtil.setState(state, resp);
			return(List)resp.getValue("functions");
		} catch (Exception e) {
			DelegateUtil.handleException(e, "findFunctionByRoleId", state);
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.permission.RightDelegate#findOtherFunctionByRoleId(java.lang.String, com.legendshop.command.framework.State)
	 */
	@Override
	public List findOtherFunctionByRoleId(String roleId,State state){
		if (DelegateUtil.isNullParam(roleId, "roleId", state)) {
			return null;
		}
		Request req = new Request();
		req.setServiceName(ServiceConsts.FindOtherFunctionByRoleIdProcessor);
		req.setValue("roleId", roleId);
		try {
			Response resp = getDelegate().execute(req);
			DelegateUtil.setState(state, resp);
			return(List)resp.getValue("functions");
		} catch (Exception e) {
			DelegateUtil.handleException(e, "findOtherFunctionByRoleId", state);
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.permission.RightDelegate#findOtherFunctionByHql(com.legendshop.core.dao.support.HqlQuery, java.lang.String, com.legendshop.command.framework.State)
	 */
	@Override
	public PageSupport findOtherFunctionByHql(HqlQuery hqlQuery,String roleId,State state){
		if (DelegateUtil.isNullParam(hqlQuery, "hqlQuery", state)) {
			return null;
		}
		Request req = new Request();
		req.setServiceName(ServiceConsts.FindOtherFunctionByHqlProcessor);
		req.setValue("hqlQuery", hqlQuery);
		req.setValue("roleId", roleId);
		try {
			Response resp = getDelegate().execute(req);
			DelegateUtil.setState(state, resp);
			return(PageSupport)resp.getValue("functions");
		} catch (Exception e) {
			DelegateUtil.handleException(e, "findOtherFunctionByHql", state);
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.permission.RightDelegate#deleteFunctionsFromRole(java.util.List, com.legendshop.command.framework.State)
	 */
	@Override
	public void deleteFunctionsFromRole(List<Permission> permissions, State state) {
		try {
			getDelegate().execute("permissions", permissions, 
					ServiceConsts.DeleteFunctionsFromRoleProcessor, state);
		} catch (Exception e) {
			DelegateUtil.handleException(e, "deleteFunctionsFromRole", state);
		}
	}


	/* (non-Javadoc)
	 * @see com.legendshop.business.permission.RightDelegate#deleteRoleFromUser(java.util.List, com.legendshop.command.framework.State)
	 */
	@Override
	public void deleteRoleFromUser(List userRoles, State state) {
		try {
			getDelegate().execute("userRoles", userRoles, 
					ServiceConsts.DeleteRoleFromUserProcessor, state);
		} catch (Exception e) {
			DelegateUtil.handleException(e, "deleteRoleFromUser", state);
		}
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.permission.RightDelegate#findUserById(java.lang.String, com.legendshop.command.framework.State)
	 */
	@Override
	public User findUserById(String userId, State state) {
		try {
			return (User)getDelegate().execute("userId", userId, 
					ServiceConsts.FindUserByIdProcessor,"user", state);
		} catch (Exception e) {
			DelegateUtil.handleException(e, "findUserById", state);
			return null;
		}
	}


	/* (non-Javadoc)
	 * @see com.legendshop.business.permission.RightDelegate#saveRoleToUser(com.legendshop.model.entity.UserRole, com.legendshop.command.framework.State)
	 */
	@Override
	public void saveRoleToUser(UserRole userRole, State state) {
		try {
			if(!AppUtils.isBlank(userRole)&&!AppUtils.isBlank(userRole.getId().getUserId())&&!AppUtils.isBlank(userRole.getId().getRoleId()))
			{
				 List userRoles=new ArrayList();
				 userRoles.add(userRole);
				 saveRolesToUser(userRoles,state);
			}else{
				throw new JCFException(state.getErrCode(),"saveRoleToUser userRole is not validated!");
			}
		} catch (Exception e) {
			DelegateUtil.handleException(e, "saveRoleToUser", state);
		}

	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.permission.RightDelegate#updateUserPassowrd(java.lang.String, java.lang.String, com.legendshop.command.framework.State)
	 */
	@Override
	public void updateUserPassowrd(String userId, String password, State state) {
		if (DelegateUtil.isNullParam(userId, "userId", state)||DelegateUtil.isNullParam(password, "password", state)) {
			return;
		}
		Request req = new Request();
		req.setServiceName(ServiceConsts.UpdateUserPassowrdProcessor);
		req.setValue("userId", userId);
		req.setValue("password", password);
		try {
			Response resp = getDelegate().execute(req);
			DelegateUtil.setState(state, resp);
		} catch (Exception e) {
			DelegateUtil.handleException(e, "updateUserPassowrd", state);
		}
	}


	/* (non-Javadoc)
	 * @see com.legendshop.business.permission.RightDelegate#deletePermissionByFunctionId(java.lang.String, com.legendshop.command.framework.State)
	 */
	@Override
	public void deletePermissionByFunctionId(String functionId, State state) {
		try {
			getDelegate().execute("functionId", functionId, 
					ServiceConsts.DeletePermissionByFunctionIdProcessor, state);
		} catch (Exception e) {
			DelegateUtil.handleException(e, "deletePermissionByFunctionId", state);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.permission.RightDelegate#deleteUserRoleByUserId(java.lang.String, com.legendshop.command.framework.State)
	 */
	@Override
	public void deleteUserRoleByUserId(String userId, State state) {
		try {
			getDelegate().execute("userId", userId, 
					ServiceConsts.DeleteUserRoleByUserIdProcessor, state);
		} catch (Exception e) {
			DelegateUtil.handleException(e, "deleteUserRoleByUserId", state);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.permission.RightDelegate#deleteRoleFromUser(com.legendshop.model.entity.UserRole, com.legendshop.command.framework.State)
	 */
	@Override
	public void deleteRoleFromUser(UserRole userRole, State state) {
		if(AppUtils.isBlank(userRole))
			throw new ClientException(ErrorCode.PARAMETER_ERROR,"deleteRoleFromUser");
		List userRoles=new ArrayList();
		userRoles.add(userRole);
		deleteRoleFromUser(userRoles,state);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.permission.RightDelegate#deleteUserRoleByRoleId(java.lang.String, com.legendshop.command.framework.State)
	 */
	@Override
	public void deleteUserRoleByRoleId(String roleId, State state) {
		try {
			getDelegate().execute("roleId", roleId, 
					ServiceConsts.DeleteUserRoleByRoleIdProcessor, state);
		} catch (Exception e) {
			DelegateUtil.handleException(e, "deleteUserRoleByRoleId", state);
		}
	}



	/* (non-Javadoc)
	 * @see com.legendshop.business.permission.RightDelegate#deletePermissionByRoleId(java.lang.String, com.legendshop.command.framework.State)
	 */
	@Override
	public void deletePermissionByRoleId(String roleId, State state) {
		try {
			getDelegate().execute("roleId", roleId, 
					ServiceConsts.DeletePermissionByRoleIdProcessor, state);
		} catch (Exception e) {
			DelegateUtil.handleException(e, "deletePermissionByRoleId", state);
		}
	}
	
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.permission.RightDelegate#saveRolesToUser(java.util.List, com.legendshop.command.framework.State)
	 */
	@Override
	public void saveRolesToUser(List userRoles, State state){
		try {
			getDelegate().execute("userRoles", userRoles, 
					ServiceConsts.SaveRolesToUserProcessor, state);
		} catch (Exception e) {
			DelegateUtil.handleException(e, "saveRolesToUser", state);
		}
	}
	
	
	  // ablout File. Create in 2007-06-30 17:23:25.
    /* (non-Javadoc)
  	 * @see com.legendshop.business.permission.RightDelegate#saveFile(java.io.File, com.legendshop.command.framework.State)
  	 */
  	@Override
	public String saveFile(File file, State state) {
		try {
			return (String)getDelegate().execute("file", file, 
					ServiceConsts.SaveFileProcessor,"result", state);
		} catch (Exception e) {
			DelegateUtil.handleException(e, "saveFile", state);
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.permission.RightDelegate#deleteFileById(java.lang.String, com.legendshop.command.framework.State)
	 */
	@Override
	public void deleteFileById(String id, State state) {
		try {
			getDelegate().execute("id", id, 
					ServiceConsts.DeleteFileByIdProcessor, state);
		} catch (Exception e) {
			DelegateUtil.handleException(e, "deleteFileById", state);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.permission.RightDelegate#deleteFile(java.io.File, com.legendshop.command.framework.State)
	 */
	@Override
	public void deleteFile(File file, State state) {
		try {
			getDelegate().execute("file", file, 
					ServiceConsts.DeleteFileProcessor, state);
		} catch (Exception e) {
			DelegateUtil.handleException(e, "deleteUserRoleByUserId", state);
		}	
	}
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.permission.RightDelegate#updateFile(java.io.File, com.legendshop.command.framework.State)
	 */
	@Override
	public void updateFile(File file, State state) {
		try {
			getDelegate().execute("file", file, 
					ServiceConsts.UpdateFileProcessor, state);
		} catch (Exception e) {
			DelegateUtil.handleException(e, "updateFile", state);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.permission.RightDelegate#findAllFile(com.legendshop.core.dao.support.CriteriaQuery, com.legendshop.command.framework.State)
	 */
	@Override
	public PageSupport findAllFile(CriteriaQuery cq, State state) {
		try {
			return (PageSupport)getDelegate().execute("CriteriaQuery", cq, 
					ServiceConsts.FindAllFileProcessor, "PageSupport", state);
		} catch (Exception e) {
			DelegateUtil.handleException(e, "findAllFile", state);
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.permission.RightDelegate#findFileById(java.lang.String, com.legendshop.command.framework.State)
	 */
	@Override
	public File findFileById(String id, State state) {
		try {
			return (File)getDelegate().execute("id", id, 
					ServiceConsts.FindFileByIdProcessor,"file", state);
		} catch (Exception e) {
			DelegateUtil.handleException(e, "findFileById", state);
			return null;
		}
	}
 // ablout File. Create in 2007-06-30 17:23:25.
	/* (non-Javadoc)
  * @see com.legendshop.business.permission.RightDelegate#saveLoginHistory(com.legendshop.model.entity.LoginHistory, com.legendshop.command.framework.State)
  */
 @Override
public String saveLoginHistory(LoginHistory loginHistory, State state) {
		try {
			return (String)getDelegate().execute("loginHistory", loginHistory, 
					ServiceConsts.SaveLoginHistoryProcessor,"result", state);
		} catch (Exception e) {
			DelegateUtil.handleException(e, "saveLoginHistory", state);
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.permission.RightDelegate#testCircle(com.legendshop.command.framework.State)
	 */
	@Override
	public String testCircle(State state) {
		try {
			return (String)getDelegate().execute("testCircleProcessor","next", state);
		} catch (Exception e) {
			DelegateUtil.handleException(e, "testCircle", state);
			return null;
		}
	}
	
	
}
