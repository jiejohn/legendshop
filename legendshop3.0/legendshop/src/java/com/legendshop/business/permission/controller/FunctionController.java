/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.permission.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legendshop.business.common.PageLetEnum;
import com.legendshop.business.helper.FunctionChecker;
import com.legendshop.business.helper.StateChecker;
import com.legendshop.business.permission.form.FunctionForm;
import com.legendshop.business.permission.form.PermissionForm;
import com.legendshop.business.permission.form.RoleForm;
import com.legendshop.business.permission.service.RightDelegate;
import com.legendshop.command.framework.State;
import com.legendshop.command.framework.StateImpl;
import com.legendshop.core.AttributeKeys;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.AdminController;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.BusinessException;
import com.legendshop.core.exception.ErrorCodes;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.Function;
import com.legendshop.model.entity.Permission;
import com.legendshop.model.entity.PerssionId;
import com.legendshop.model.entity.Role;
import com.legendshop.util.AppUtils;
import com.legendshop.util.StringUtil;

/**
 * FunctionController.
 */
@Controller
@RequestMapping("/member/right")
public class FunctionController extends BaseController implements AdminController<Function, String> {

	/** The log. */
	private final Logger log = LoggerFactory.getLogger(FunctionController.class);

	/** The basket service. */
	@Autowired
	private RightDelegate rightDelegate;

	/** The function checker. */
	@Autowired
	private FunctionChecker functionChecker;
	
	/** The state checker. */
	@Autowired
	private StateChecker stateChecker;

	/**
	 * Delete function by id.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param curPageNO
	 *            the cur page no
	 * @param roleId
	 *            the role id
	 * @param functionForm
	 *            the function form
	 * @return the string
	 */
	@RequestMapping("/deleteFunctionById")
	public String deleteFunctionById(HttpServletRequest request, HttpServletResponse response, String curPageNO,
			String roleId, FunctionForm functionForm) {
		String id = request.getParameter("id");
		logger.info("Struts Action deleteFunctionById with id  " + id);
		State state = new StateImpl();

		boolean result = rightDelegate.deleteFunctionById(id, state);
		logger.debug("deleteFunctionById result  = " + result);
		stateChecker.check(state, request);

		return PathResolver.getPath(request, PageLetEnum.FUNCTION_LIST_QUERY);
	}

	/**
	 * Find function by id.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param id
	 *            the id
	 * @return the string
	 */
	@RequestMapping("/findFunctionById")
	public String findFunctionById(HttpServletRequest request, HttpServletResponse response, String id) {
		logger.info("Struts Action findFunctionById with id  " + id);
		State state = new StateImpl();
		Function function = rightDelegate.findFunctionById(id, state);
		stateChecker.check(state, request);
		request.setAttribute("function", function);

		return PathResolver.getPath(request, PageLetEnum.UPDATE_FUNCTION);
	}

	/**
	 * Find function by role.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param roleId
	 *            the role id
	 * @return the string
	 */
	@RequestMapping("/findFunctionByRole")
	public String findFunctionByRole(HttpServletRequest request, HttpServletResponse response, String roleId) {
		logger.info("Struts FunctionAction findFunctionByRole with roleId  " + roleId);
		State state = new StateImpl();
		Role role = rightDelegate.findRoleById(roleId, state);
		List list = rightDelegate.findFunctionByRoleId(roleId, state);
		stateChecker.check(state, request);
		request.setAttribute("list", list);
		request.setAttribute("role", role);

		return PathResolver.getPath(request, PageLetEnum.FIND_FUNCTION_BY_ROLE);
	}

	/**
	 * Find other function by hql.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param curPageNO
	 *            the cur page no
	 * @param roleId
	 *            the role id
	 * @return the string
	 */
	@RequestMapping("/findOtherFunctionByHql")
	public String findOtherFunctionByHql(HttpServletRequest request, HttpServletResponse response, String curPageNO,
			String roleId) {
		String myAction = "findOtherFunctionByHql" + AttributeKeys.WEB_SUFFIX;
		if (roleId != null)
			myAction = "findOtherFunctionByHql.do?roleId=" + roleId;
		// HQL查找方式
		HqlQuery hq = new HqlQuery(myAction);
		hq.setCurPage(curPageNO);
		hq.setPageSize(PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE, Integer.class));

		State state = new StateImpl();
		Role role = rightDelegate.findRoleById(roleId, state);
		PageSupport ps = rightDelegate.findOtherFunctionByHql(hq, roleId, state);
		stateChecker.check(state, request);
		request.setAttribute("curPageNO", new Integer(ps.getCurPageNO()));
		request.setAttribute("offset", new Integer(ps.getOffset() + 1));
		if (ps.hasMutilPage())
			request.setAttribute("toolBar", ps.getToolBar());
		request.setAttribute("functionList", ps.getResultList());
		request.setAttribute("role", role);

		return PathResolver.getPath(request, PageLetEnum.FIND_OTHER_FUNCTION_LIST);
	}

	/**
	 * List function.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param curPageNO
	 *            the cur page no
	 * @param roleId
	 *            the role id
	 * @param functionForm
	 *            the function form
	 * @return the string
	 */
	@RequestMapping("/listFunction")
	public String listFunction(HttpServletRequest request, HttpServletResponse response, String curPageNO,
			String roleId, FunctionForm functionForm) {
		String userName = UserManager.getUsername(request);
		if (userName == null) {
			throw new BusinessException("not login yet", ErrorCodes.BUSINESS_ERROR);
		}
		//权限检查
		functionChecker.check(userName, request);

		String myaction = "listFunction" + AttributeKeys.WEB_SUFFIX;
		String search = request.getParameter("search");
		if (search == null) {
			search = "";
		} else {
			myaction = myaction + "?search=" + search;
		}

		// Qbc查找方式
		CriteriaQuery cq = new CriteriaQuery(Function.class, curPageNO, myaction);
		cq.setPageSize(PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE, Integer.class));
		if (!AppUtils.isBlank(search)) {
			cq.like("name", "%" + search + "%");// 1
			cq.add();
		}
		State state = new StateImpl();
		PageSupport ps = rightDelegate.findAllFunction(cq, state);
		request.setAttribute("search", search);
		request.setAttribute("curPageNO", new Integer(ps.getCurPageNO()));
		request.setAttribute("offset", new Integer(ps.getOffset() + 1));
		request.setAttribute("list", ps.getResultList());
		if (ps.hasMutilPage())
			request.setAttribute("toolBar", ps.getToolBar());

		return PathResolver.getPath(request, PageLetEnum.FUNCTION_LIST);
	}

	/**
	 * Save function.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param functionForm
	 *            the function form
	 * @return the string
	 */
	@RequestMapping("/saveFunction")
	public String saveFunction(HttpServletRequest request, HttpServletResponse response, FunctionForm functionForm) {
		State state = new StateImpl();
		String id = rightDelegate.saveFunction(functionForm.getFunction(), state);
		logger.info("success saveFunction,id = " + id);
		stateChecker.check(state, request);
		return PathResolver.getPath(request, PageLetEnum.FUNCTION_LIST_QUERY);
	}

	/**
	 * Save functions to role list.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param roleId
	 *            the role id
	 * @param permissionForm
	 *            the permission form
	 * @return the string
	 */
	@RequestMapping("/saveFunctionsToRoleList")
	public String saveFunctionsToRoleList(HttpServletRequest request, HttpServletResponse response, String roleId,
			PermissionForm permissionForm) {
		List<Permission> permissions = new ArrayList<Permission>();
		String[] strArray = permissionForm.getStrArray();
		for (int i = 0; i < strArray.length; i++) {
			Permission permission = new Permission();
			PerssionId perssionId = new PerssionId();
			perssionId.setRoleId(roleId);
			perssionId.setFunctionId(strArray[i]);
			permission.setId(perssionId);
			permissions.add(permission);
		}
		State state = new StateImpl();
		rightDelegate.saveFunctionsToRole(permissions, state);
		stateChecker.check(state, request);

		return PathResolver.getPath(request, PageLetEnum.FIND_FUNCTION_BY_ROLE);
	}

	/**
	 * Update function by id.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param functionForm
	 *            the function form
	 * @return the string
	 */
	@RequestMapping("/updateFunctionById")
	public String updateFunctionById(HttpServletRequest request, HttpServletResponse response, FunctionForm functionForm) {
		logger.debug("Struts FunctionAction updateFunctionById");
		State state = new StateImpl();
		rightDelegate.updateFunction(functionForm.getFunction(), state);
		stateChecker.check(state, request);
		return PathResolver.getPath(request, PageLetEnum.FUNCTION_LIST_QUERY);
	}

	// ////////////////////////////Role Controller
	/**
	 * Query.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param curPageNO
	 *            the cur page no
	 * @param roleId
	 *            the role id
	 * @param functionForm
	 *            the function form
	 * @return the string
	 */
	@RequestMapping("/deletePermissionByRoleId")
	public String deletePermissionByRoleId(HttpServletRequest request, HttpServletResponse response, String curPageNO,
			String roleId, FunctionForm functionForm) {
		log.info("Action deletePermissionByRoleId with roleId {}", roleId);
		List<Permission> permissions = new ArrayList<Permission>();
		String[] strArray = functionForm.getStrArray();
		for (int i = 0; i < strArray.length; i++) {
			Permission permission = new Permission();
			PerssionId perssionId = new PerssionId();
			perssionId.setRoleId(roleId);
			perssionId.setFunctionId(strArray[i]);
			permission.setId(perssionId);
			permissions.add(permission);
		}
		State state = new StateImpl();
		rightDelegate.deleteFunctionsFromRole(permissions, state);
		stateChecker.check(state, request);
		request.setAttribute("roleId", roleId);
		return PathResolver.getPath(request, PageLetEnum.FIND_FUNCTION_BY_ROLE);
	}

	/**
	 * Find role by id.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param id
	 *            the id
	 * @return the string
	 */
	@RequestMapping("/findRoleById")
	public String findRoleById(HttpServletRequest request, HttpServletResponse response, String id) {
		logger.info("Action findRoleById with id  " + id);
		State state = new StateImpl();
		Role role = rightDelegate.findRoleById(id, state);
		stateChecker.check(state, request);
		request.setAttribute("role", role);

		return PathResolver.getPath(request, PageLetEnum.FIND_ALL_ROLE);
	}

	/**
	 * Delete role by id.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param id
	 *            the id
	 * @return the string
	 */
	@RequestMapping("/deleteRoleById")
	public String deleteRoleById(HttpServletRequest request, HttpServletResponse response, String id) {
		logger.info("Struts Action deleteRoleById with id  " + id);
		State state = new StateImpl();

		rightDelegate.deleteRoleById(id, state);
		stateChecker.check(state, request);

		return PathResolver.getPath(request, PageLetEnum.FIND_ALL_ROLE);
	}

	/**
	 * Find all role.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping("/findAllRole")
	public String findAllRole(HttpServletRequest request, HttpServletResponse response) {
		String userName = UserManager.getUsername(request);
		if (userName == null) {
			throw new BusinessException("not login yet", ErrorCodes.BUSINESS_ERROR);
		}
		//权限检查
		functionChecker.check(userName, request);
		String curPageNO = request.getParameter("curPageNO");
		String myaction;
		String search = request.getParameter("search");
		// String myform="forms[0]";
		String action = "findAllRole" + AttributeKeys.WEB_SUFFIX;
		if (search == null) {
			search = "";
			myaction = action;
		} else {
			myaction = action + "?search=" + search;
		}

		// Qbc查找方式
		CriteriaQuery cq = new CriteriaQuery(Role.class, curPageNO, myaction);
		cq.setPageSize(PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE, Integer.class));
		if (!AppUtils.isBlank(search)) {
			cq.like("name", "%" + search + "%");
			cq.add();
		}

		State state = new StateImpl();
		PageSupport ps = rightDelegate.findAllRole(cq, state);
		request.setAttribute("search", search);
		request.setAttribute("curPageNO", new Integer(ps.getCurPageNO()));
		request.setAttribute("offset", new Integer(ps.getOffset() + 1));
		request.setAttribute("list", ps.getResultList());
		if (ps.hasMutilPage())
			request.setAttribute("toolBar", ps.getToolBar());

		return PathResolver.getPath(request, PageLetEnum.ROLE_LIST);
	}

	/**
	 * Find role by function.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param functionId
	 *            the function id
	 * @return the string
	 */
	@RequestMapping("/findRoleByFunction")
	public String findRoleByFunction(HttpServletRequest request, HttpServletResponse response, String functionId) {
		logger.info("Struts Action findRoleByFunction with function id  " + functionId);
		State state = new StateImpl();

		Function function = rightDelegate.findFunctionById(functionId, state);
		List list = rightDelegate.findRoleByFunction(functionId, state);
		stateChecker.check(state, request);

		request.setAttribute("list", list);
		request.setAttribute("function", function);

		return PathResolver.getPath(request, PageLetEnum.FIND_ROLE_BY_FUNCTION);
	}

	/**
	 * Save role.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param roleForm
	 *            the role form
	 * @return the string
	 */
	@RequestMapping("/saveRole")
	public String saveRole(HttpServletRequest request, HttpServletResponse response, RoleForm roleForm) {
		State state = new StateImpl();

		String id = rightDelegate.saveRole(roleForm.getRole(), state);
		logger.info("success saveRole,id = " + id);
		stateChecker.check(state, request);

		return PathResolver.getPath(request, PageLetEnum.FIND_ALL_ROLE);
	}

	/**
	 * Update role by id.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param roleForm
	 *            the role form
	 * @return the string
	 */
	@RequestMapping("/updateRoleById")
	public String updateRoleById(HttpServletRequest request, HttpServletResponse response, RoleForm roleForm) {
		logger.info("Struts Action updateRoleById");
		State state = new StateImpl();
		rightDelegate.updateRole(roleForm.getRole(), state);
		stateChecker.check(state, request);

		return PathResolver.getPath(request, PageLetEnum.FIND_ALL_ROLE);
	}

	@Override
	@RequestMapping(value = "/delete/{id}")
	public String delete(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String id) {
		logger.info("Struts Action deleteFunctionById with id  " + id);
		State state = new StateImpl();
		boolean result = rightDelegate.deleteFunctionById(id, state);
		logger.debug("deleteFunctionById result  = " + result);
		if (!result) {
			stateChecker.check(state, request);
		}
		return PathResolver.getPath(request, PageLetEnum.FUNCTION_LIST_QUERY);
	}

	@Override
	@RequestMapping("/load")
	public String load(HttpServletRequest request, HttpServletResponse arg1) {

		return PathResolver.getPath(request, PageLetEnum.UPDATE_FUNCTION);
	}

	@Override
	@RequestMapping("/query")
	public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, Function function) {
		String userName = UserManager.getUsername(request);
		if (userName == null) {
			throw new BusinessException("not login yet", ErrorCodes.BUSINESS_ERROR);
		}
		//权限检查
		functionChecker.check(userName, request);

		// Qbc查找方式
		CriteriaQuery cq = new CriteriaQuery(Function.class, curPageNO);

		cq.setPageSize(PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE, Integer.class));
		String name = function.getName();

		if (!AppUtils.isBlank(name)) {
			cq.like("name", "%" + name + "%");// 1
			cq.add();
		}
		State state = new StateImpl();
		PageSupport ps = rightDelegate.findAllFunction(cq, state);
		request.setAttribute("bean", function);
		savePage(ps, request);

		return PathResolver.getPath(request, PageLetEnum.FUNCTION_LIST);
	}

	@Override
	@RequestMapping(value = "/save")
	public String save(HttpServletRequest request, HttpServletResponse response, Function function) {
		State state = new StateImpl();
		String id=function.getId();
		if(StringUtil.isEmpty(id)){
			id = rightDelegate.saveFunction(function, state);
		}else{
			rightDelegate.updateFunction(function, state);
		}
		logger.info("success saveFunction,id = " + id);
		stateChecker.check(state, request);
		return PathResolver.getPath(request, PageLetEnum.FUNCTION_LIST_QUERY);
	}

	@Override
	@RequestMapping(value = "/update/{id}")
	public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable String id) {
		logger.info("Struts Action findFunctionById with id  " + id);
		State state = new StateImpl();
		Function function = rightDelegate.findFunctionById(id, state);
		stateChecker.check(state, request);
		request.setAttribute("bean", function);

		return PathResolver.getPath(request, PageLetEnum.UPDATE_FUNCTION);
	}
	
	@RequestMapping(value = "/roles/{id}")
	public String roles(HttpServletRequest request, HttpServletResponse response, @PathVariable String id) {
		logger.info("Struts Action findRoleByFunction with function id  " + id);
		State state = new StateImpl();

		Function function = rightDelegate.findFunctionById(id, state);
		List list = rightDelegate.findRoleByFunction(id, state);
		stateChecker.check(state, request);

		request.setAttribute("list", list);
		request.setAttribute("bean", function);

		return PathResolver.getPath(request, PageLetEnum.FIND_ROLE_BY_FUNCTION);
	}
}
