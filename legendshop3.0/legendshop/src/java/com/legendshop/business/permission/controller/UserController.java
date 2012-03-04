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
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legendshop.business.common.PageLetEnum;
import com.legendshop.business.helper.FunctionChecker;
import com.legendshop.business.permission.form.UserRoleForm;
import com.legendshop.business.permission.form.UsersForm;
import com.legendshop.business.permission.service.RightDelegate;
import com.legendshop.command.framework.State;
import com.legendshop.command.framework.StateImpl;
import com.legendshop.core.AttributeKeys;
import com.legendshop.core.UserManager;
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
import com.legendshop.model.entity.User;
import com.legendshop.model.entity.UserRole;
import com.legendshop.model.entity.UserRoleId;
import com.legendshop.util.AppUtils;

/**
 * UserController.
 */
@Controller
@RequestMapping("/member/user")
public class UserController extends BaseController {

	/** The log. */
	private final Logger log = LoggerFactory.getLogger(UserController.class);

	/** The basket service. */
	@Autowired
	private RightDelegate rightDelegate;
	
	/** The function checker. */
	@Autowired
	private FunctionChecker functionChecker;
	
	
	
	/**
	 * Delete user role by user id.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param userRoleForm
	 *            the user role form
	 * @return the string
	 */
	@RequestMapping("/deleteUserRoleByUserId")
	public String deleteUserRoleByUserId(HttpServletRequest request, HttpServletResponse response, UserRoleForm userRoleForm) {
		String userId = request.getParameter("userId");
		logger.info("Action deleteUserRoleByUserId with userId  " + userId);
		List<UserRole> userRoles = new ArrayList<UserRole>();
		String[] strArray = userRoleForm.getStrArray();
		for (String element : strArray) {
			UserRole userRole = new UserRole();
			UserRoleId userRoleId = new UserRoleId();
			userRoleId.setUserId(userId);
			userRoleId.setRoleId(element);
			userRole.setId(userRoleId);
			userRoles.add(userRole);
		}
		// removeRequestAttribute(mapping,request);
		State state = new StateImpl();

			rightDelegate.deleteRoleFromUser(userRoles, state);
			if (!state.isOK()){
				return PathResolver.getPath(request, PageLetEnum.FAIL);
			}

		request.setAttribute("userId", userId);
		return PathResolver.getPath(request, PageLetEnum.FIND_ROLE_BY_USER);
	}

	
	/**
	 * Save user.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param usersForm
	 *            the users form
	 * @return the action forward
	 */
	@RequestMapping("/saveUser")
	public String saveUser(HttpServletRequest request,
			HttpServletResponse response,UsersForm usersForm) {
		State state = new StateImpl();
		User user = usersForm.getUser();
			if (rightDelegate.isUserExist(user.getName(), state)) {
				//return handleException(mapping, request, ActionMessages.GLOBAL_MESSAGE, "error.User.IsExist");
				return PathResolver.getPath(request, PageLetEnum.FAIL);
			}

			String id = rightDelegate.saveUser(Md5Password(user), state);
			logger.info("success saveUser,id = " + id);
			if (!state.isOK()) {
				return PathResolver.getPath(request, PageLetEnum.FAIL);
			}

		return PathResolver.getPath(request, PageLetEnum.USERS_LIST);
	}

	/**
	 * MD5加盐（私钥是name,参数是password,name).
	 * 
	 * @param user
	 *            the user
	 * @return the user
	 */
	private User Md5Password(User user) {
		Md5PasswordEncoder coder = new Md5PasswordEncoder();
		coder.setEncodeHashAsBase64(false);
		if (user.getPassword() != null) {
			user.setPassword(coder.encodePassword(user.getPassword(), user.getName()));
			// System.out.println("userAciotn user.getPassword()
			// "+user.getPassword());
			return user;
		}
		return user;
	}

	/**
	 * Update user status.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param usersForm
	 *            the users form
	 * @return the action forward
	 */
	@RequestMapping("/updateUserStatus")
	public String updateUserStatus(HttpServletRequest request,
			HttpServletResponse response,UsersForm usersForm) {
		logger.debug("Struts UserAction updateUserStatus");
		User user = usersForm.getUser();
		String enabled = user.getEnabled();
		String userId = user.getId();

		State state = new StateImpl();

			User olduser = rightDelegate.findUserById(userId, state);
			olduser.setEnabled(enabled);
			olduser.setNote(user.getNote());
			rightDelegate.updateUser(olduser, state);
			if (!state.isOK()) {
				return PathResolver.getPath(request, PageLetEnum.FAIL);
			}

			return PathResolver.getPath(request, PageLetEnum.USERS_LIST);
	}

	/**
	 * Preupdate status.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the action forward
	 */
	@RequestMapping("/preupdateStatus")
	public String preupdateStatus(HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		logger.info("Action preupdateStatus with id  " + id);
		State state = new StateImpl();
			User user = rightDelegate.findUserById(id, state);
			if (!state.isOK()) {
				return PathResolver.getPath(request, PageLetEnum.FAIL);
			}
			request.setAttribute("user", user);
		return PathResolver.getPath(request, PageLetEnum.UPDATE_USER_STATUS);
	}

	/**
	 * Update user passowrd.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param usersForm
	 *            the users form
	 * @return the action forward
	 */
	@RequestMapping("/updateUserPassowrd")
	public String updateUserPassowrd(HttpServletRequest request,
			HttpServletResponse response,UsersForm usersForm) {
		logger.info("Struts UserAction updateUserById");
		User user = usersForm.getUser();
		String passwordag = user.getPasswordag();
		if ((user.getPassword() == null) || (user.getPassword() == "")) {
			//return handleException(mapping, request, ActionMessages.GLOBAL_MESSAGE, "error.Password.required");
			return PathResolver.getPath(request, PageLetEnum.FAIL);
		}
		if (!passwordag.endsWith(user.getPassword())) {// 2次密码要相等
			//return handleException(mapping, request, ActionMessages.GLOBAL_MESSAGE, "error.Password.NotEqual");
			return PathResolver.getPath(request, PageLetEnum.FAIL);
		}
		State state = new StateImpl();
		Md5PasswordEncoder coder = new Md5PasswordEncoder();
		coder.setEncodeHashAsBase64(false);
			rightDelegate.updateUserPassowrd(user.getId(), coder.encodePassword(user.getPassword(), user.getName()),
					state);
			logger.info("updateFunctionById result  = " + state.isOK());
			if (!state.isOK()) {
				return PathResolver.getPath(request, PageLetEnum.FAIL);
			}
			return PathResolver.getPath(request, PageLetEnum.USERS_LIST);
	}

	/**
	 * Find user by id.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the action forward
	 */
	@RequestMapping("/findUserById")
	public String findUserById(HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		logger.info("Action findUserById with id  " + id);
		
		State state = new StateImpl();
			User user = rightDelegate.findUserById(id, state);
			if (!state.isOK()) {
				return PathResolver.getPath(request, PageLetEnum.FAIL);
			}
			request.setAttribute("user", user);

		return PathResolver.getPath(request, PageLetEnum.UPDATE_USER_PASSWORD);
	}

	/**
	 * Save role to user.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param usersForm
	 *            the users form
	 * @return the action forward
	 */
	@RequestMapping("/saveRoleToUser")
	public String saveRoleToUser(HttpServletRequest request,
			HttpServletResponse response,UsersForm usersForm) {
		String userId = request.getParameter("userId");
		List<UserRole> userRoles = new ArrayList<UserRole>();
		String[] strArray = usersForm.getStrArray();
		for (String element : strArray) {
			UserRole userRole = new UserRole();
			UserRoleId userRoleId = new UserRoleId();
			userRoleId.setRoleId(element);
			userRoleId.setUserId(userId);
			userRole.setId(userRoleId);
			userRoles.add(userRole);
		}
		
		State state = new StateImpl();
			rightDelegate.saveRolesToUser(userRoles, state);
			if (!state.isOK()) {
				return PathResolver.getPath(request, PageLetEnum.FAIL);
			}

		return PathResolver.getPath(request, PageLetEnum.FIND_ROLE_BY_USER);

	}

	/**
	 * Find other role by user.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the action forward
	 */
	@RequestMapping("/findOtherRoleByUser")
	public String findOtherRoleByUser(HttpServletRequest request,
			HttpServletResponse response) {
		String curPageNO = request.getParameter("curPageNO");
		String userId = request.getParameter("userId");
		String myAction = "findOtherRoleByUser" + AttributeKeys.WEB_SUFFIX;
		if (userId != null) {
			myAction = myAction + "?userId=" + userId;
		}
			// HQL查找方式
			HqlQuery hq = new HqlQuery(myAction);
			hq.setCurPage(curPageNO);
			hq.setPageSize(PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE, Integer.class));
			State state = new StateImpl();
			User user = rightDelegate.findUserById(userId, state);
			PageSupport ps = rightDelegate.findOtherRoleByUser(hq, userId, state);
			if (!state.isOK()) {
				return PathResolver.getPath(request, PageLetEnum.FAIL);
			}
			request.setAttribute("curPageNO", new Integer(ps.getCurPageNO()));
			request.setAttribute("offset", new Integer(ps.getOffset() + 1));
			if (ps.hasMutilPage()) {
				request.setAttribute("toolBar", ps.getToolBar());
			}
			request.setAttribute("roles", ps.getResultList());
			request.setAttribute("user", user);

		return PathResolver.getPath(request, PageLetEnum.FIND_OTHER_ROLE_BY_USER);
	}

	/**
	 * Find function by user.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the action forward
	 */
	@RequestMapping("/findFunctionByUser")
	public String findFunctionByUser(HttpServletRequest request,
			HttpServletResponse response) {
		String userId = request.getParameter("userId");
		logger.info("UserAction findFunctionByUser : " + userId);
		State state = new StateImpl();
			User user = rightDelegate.findUserById(userId, state);
			List functions = rightDelegate.findFunctionByUser(userId, state);
			if (!state.isOK()) {
				return PathResolver.getPath(request, PageLetEnum.FAIL);
			}
			request.setAttribute("functions", functions);
			request.setAttribute("user", user);

		return PathResolver.getPath(request, PageLetEnum.FIND_FUNCTION_BY_USER);
	}

	/**
	 * Users list.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param curPageNO
	 *            the cur page no
	 * @return the action forward
	 */
	@RequestMapping("/usersList")
	public String usersList(HttpServletRequest request, HttpServletResponse response,String curPageNO ) {
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
		String search = request.getParameter("search") == null ? "" : request.getParameter("search");
		String enabled = request.getParameter("enabled") == null ? "" : request.getParameter("enabled");
			// Qbc查找方式
			CriteriaQuery cq = new CriteriaQuery(User.class, curPageNO);
			cq.setPageSize(1);
//			cq.setPageSize(PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE, Integer.class));
			// cq.eq("rightId","002");//0条件

			// cq.like("rightUrl","u%");//2
			// cq.add(cq.and(cq.or(cq,0,1),cq,2));
			if (AppUtils.isNotBlank(search)) {
				cq.like("name", search + "%");// 1
			}
			if (AppUtils.isNotBlank(enabled)) {
				cq.eq("enabled", enabled);
			}
			cq.add();
			State state = new StateImpl();
			PageSupport ps = rightDelegate.findAllUser(cq, state);

			
			request.setAttribute("search", search);
			request.setAttribute("enabled", enabled);
//			request.setAttribute("curPageNO", new Integer(ps.getCurPageNO()));
//			request.setAttribute("offset", new Integer(ps.getOffset() + 1));
//			if (ps.hasMutilPage()) {
//				request.setAttribute("toolBar", ps.getToolBar());
//			}
//			if (!AppUtils.isBlank(ps.getResultList())) {
//				request.setAttribute("list", ps.getResultList());
//			}
			savePage(ps, request);
		return PathResolver.getPath(request, PageLetEnum.USER_LIST_PAGE);
	}

	/**
	 * Find role by user.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the action forward
	 */
	@RequestMapping("/findRoleByUser")
	public String findRoleByUser(HttpServletRequest request,
			HttpServletResponse response) {
		String userId = request.getParameter("userId");
		logger.info("UserAction findOtherRoleByUser : " + userId);
		State state = new StateImpl();
			User user = rightDelegate.findUserById(userId, state);
			List roles = rightDelegate.findRoleByUser(userId, state);
			if (!state.isOK()) {
				return PathResolver.getPath(request, PageLetEnum.FAIL);
			}
			request.setAttribute("roles", roles);
			request.setAttribute("user", user);

		return PathResolver.getPath(request, PageLetEnum.FIND_ROLE_BY_USER_PAGE);
	}

	@RequestMapping(value = "/load/{id}")
	public String load(HttpServletRequest request,
			HttpServletResponse response, @PathVariable String id) {
		

		logger.info("Action findUserById with id  " + id);
		
		State state = new StateImpl();
			User user = rightDelegate.findUserById(id, state);
			if (!state.isOK()) {
				return PathResolver.getPath(request, PageLetEnum.FAIL);
			}
			request.setAttribute("user", user);

		return PathResolver.getPath(request, PageLetEnum.UPDATE_USER_PASSWORD);
		
	}
	
	/**
	 * Load.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping(value = "/load")
	public String load(HttpServletRequest request, HttpServletResponse response) {
		return PathResolver.getPath(request, PageLetEnum.UPDATE_USER_PASSWORD);
	}

}
