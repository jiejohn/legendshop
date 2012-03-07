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
import com.legendshop.business.permission.form.FunctionForm;
import com.legendshop.business.permission.form.PermissionForm;
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
import com.legendshop.core.exception.PermissionException;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.UserMessages;
import com.legendshop.model.entity.Permission;
import com.legendshop.model.entity.PerssionId;
import com.legendshop.model.entity.Role;
import com.legendshop.model.entity.User;
import com.legendshop.util.AppUtils;
import com.legendshop.util.StringUtil;

/**
 * RoleController.
 */
@Controller
@RequestMapping("/member/role")
public class RoleController extends BaseController implements AdminController<Role, String> {

	/** The log. */
	private final Logger log = LoggerFactory.getLogger(RoleController.class);

	/** The basket service. */
	@Autowired
	private RightDelegate rightDelegate;

	/** The function checker. */
	@Autowired
	private FunctionChecker functionChecker;

	@Override
	@RequestMapping(value = "/delete/{id}")
	public String delete(HttpServletRequest request, HttpServletResponse response,@PathVariable String id) {

		logger.info("Struts Action deleteRoleById with id  " + id);
		State state = new StateImpl();

		rightDelegate.deleteRoleById(id, state);
		if (!state.isOK()){
			return PathResolver.getPath(request, PageLetEnum.FAIL);
		}

		return PathResolver.getPath(request, PageLetEnum.ALL_ROLE);
	}

	@Override
	@RequestMapping("/load")
	public String load(HttpServletRequest request, HttpServletResponse response) {
		return PathResolver.getPath(request, PageLetEnum.SAVE_ROLE);
	}

	@Override
	@RequestMapping("/query")
	public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, Role role) {
		String userName = UserManager.getUsername(request);
		if (userName == null) {
			throw new BusinessException("not login yet", ErrorCodes.BUSINESS_ERROR);
		}
		// 检查不通过
		if (!functionChecker.check(userName, request)) {
			UserMessages uem = new UserMessages();
			uem.setTitle("免费版不提供该功能");
			uem.setCode(ErrorCodes.UN_AUTHORIZATION);

			request.setAttribute(UserMessages.MESSAGE_KEY, uem);
			throw new PermissionException("UN_AUTHORIZATION", ErrorCodes.UN_AUTHORIZATION);
		}

		// Qbc查找方式
		CriteriaQuery cq = new CriteriaQuery(Role.class, curPageNO);
		cq.setPageSize(PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE, Integer.class));
		String name = role.getName();
		String enabled = role.getEnabled();

		if (!AppUtils.isBlank(name)) {
			cq.like("name", "%" + name + "%");
		}
		if (AppUtils.isNotBlank(enabled)) {
			cq.eq("enabled", enabled);
		}
		cq.add();

		State state = new StateImpl();
		PageSupport ps = rightDelegate.findAllRole(cq, state);

		request.setAttribute("bean", role);

		savePage(ps, request);

		return PathResolver.getPath(request, PageLetEnum.ROLE_LIST);
	}

	@Override
	@RequestMapping(value = "/save")
	public String save(HttpServletRequest request, HttpServletResponse response, Role role) {

		State state = new StateImpl();

			String id=role.getId();
			
			if(StringUtil.isEmpty(id)){
				id=rightDelegate.saveRole(role, state);
			}else{
				rightDelegate.updateRole(role, state);
			}
			
			
			logger.info("success saveRole,id = " + id);
			if (!state.isOK()){
				return PathResolver.getPath(request, PageLetEnum.FAIL);
			}

			return PathResolver.getPath(request, PageLetEnum.ALL_ROLE);
	}

	@Override
	@RequestMapping(value = "/update/{id}")
	public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable String id) {
		logger.info("Action update with id  " + id);
		State state = new StateImpl();
		Role role= rightDelegate.findRoleById(id, state);
		if (!state.isOK()) {
			return PathResolver.getPath(request, PageLetEnum.FAIL);
		}
		request.setAttribute("bean", role);

		return PathResolver.getPath(request, PageLetEnum.SAVE_ROLE);
	}

	@RequestMapping(value = "/functions/{id}")
	public String functions(HttpServletRequest request, HttpServletResponse response, @PathVariable String id) {
		logger.info("Struts FunctionAction findFunctionByRole with roleId  " + id);
		State state = new StateImpl();
		Role role = rightDelegate.findRoleById(id, state);
		List list = rightDelegate.findFunctionByRoleId(id, state);
		if (!state.isOK()) {
			return PathResolver.getPath(request, PageLetEnum.FAIL);
		}
		request.setAttribute("list", list);
		request.setAttribute("bean", role);

		return PathResolver.getPath(request, PageLetEnum.ROLE_FUNCTION);
	}
	
	@RequestMapping(value = "/otherFunctions/{id}")
	public String otherFunctions(HttpServletRequest request, HttpServletResponse response, String curPageNO,
			@PathVariable String id) {
		
		// HQL查找方式
		HqlQuery hq = new HqlQuery();
		hq.setCurPage(curPageNO);
		hq.setPageSize(PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE, Integer.class));

		State state = new StateImpl();
		Role role = rightDelegate.findRoleById(id, state);
		PageSupport ps = rightDelegate.findOtherFunctionByHql(hq, id, state);
		if (!state.isOK()) {
			return PathResolver.getPath(request, PageLetEnum.FAIL);
		}
		savePage(ps, request);
		request.setAttribute("bean", role);

		return PathResolver.getPath(request, PageLetEnum.FIND_OTHER_FUNCTION_LIST);
	}
	
	@RequestMapping("/saveFunctionsToRole")
	public String saveFunctionsToRole(HttpServletRequest request, HttpServletResponse response, String roleId,
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
		if (!state.isOK()) {
			return PathResolver.getPath(request, PageLetEnum.FAIL);
		}

//		return PathResolver.getPath(request, PageLetEnum.FIND_FUNCTION_BY_ROLE);
		return "redirect:"+PageLetEnum.FIND_FUNCTION_BY_ROLE.getValue()+"/"+roleId+AttributeKeys.WEB_SUFFIX;
	}
	
	@RequestMapping("/deletePermissionByRoleId")
	public String deletePermissionByRoleId(HttpServletRequest request, HttpServletResponse response, 
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
		if (!state.isOK()) {
			return PathResolver.getPath(request, PageLetEnum.FAIL);
		}
//		return PathResolver.getPath(request, PageLetEnum.FIND_FUNCTION_BY_ROLE);
		return "redirect:"+PageLetEnum.FIND_FUNCTION_BY_ROLE.getValue()+"/"+roleId+AttributeKeys.WEB_SUFFIX;
	}


}
