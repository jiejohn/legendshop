/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.permission.controller;

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

import com.legendshop.command.framework.State;
import com.legendshop.command.framework.StateImpl;
import com.legendshop.core.AttributeKeys;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.AdminController;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.CommonPage;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.BusinessException;
import com.legendshop.core.exception.ErrorCodes;
import com.legendshop.core.helper.FunctionChecker;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.StateChecker;
import com.legendshop.model.entity.User;
import com.legendshop.model.entity.UserRole;
import com.legendshop.model.entity.UserRoleId;
import com.legendshop.permission.form.UserRoleForm;
import com.legendshop.permission.form.UsersForm;
import com.legendshop.permission.page.SecurityBackPage;
import com.legendshop.permission.page.SecurityFowardPage;
import com.legendshop.permission.service.RightDelegate;
import com.legendshop.util.AppUtils;
import com.legendshop.util.StringUtil;

/**
 * UserController.
 */
@Controller
@RequestMapping("/member/user")
public class UserController extends BaseController implements AdminController<User, String>{

	/** The log. */
	private final Logger log = LoggerFactory.getLogger(UserController.class);

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
		State state = new StateImpl();

			rightDelegate.deleteRoleFromUser(userRoles, state);
			stateChecker.check(state, request);

		request.setAttribute("userId", userId);
		return  PathResolver.getPath(request, SecurityFowardPage.FIND_USER_ROLES.getValue() +"/" + userId, SecurityFowardPage.FIND_USER_ROLES);
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
				return PathResolver.getPath(request, CommonPage.FAIL);
			}

			String id = rightDelegate.saveUser(Md5Password(user), state);
			logger.info("success saveUser,id = " + id);
			stateChecker.check(state, request);

		return PathResolver.getPath(request, SecurityFowardPage.USERS_LIST);
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
			stateChecker.check(state, request);

			return PathResolver.getPath(request, SecurityFowardPage.USERS_LIST);
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
			stateChecker.check(state, request);
			request.setAttribute("user", user);
		return PathResolver.getPath(request, SecurityBackPage.UPDATE_USER_STATUS);
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
			return PathResolver.getPath(request, CommonPage.FAIL);
		}
		if (!passwordag.endsWith(user.getPassword())) {// 2次密码要相等
			//return handleException(mapping, request, ActionMessages.GLOBAL_MESSAGE, "error.Password.NotEqual");
			return PathResolver.getPath(request, CommonPage.FAIL);
		}
		State state = new StateImpl();
		Md5PasswordEncoder coder = new Md5PasswordEncoder();
		coder.setEncodeHashAsBase64(false);
			rightDelegate.updateUserPassowrd(user.getId(), coder.encodePassword(user.getPassword(), user.getName()),
					state);
			stateChecker.check(state, request);
			return PathResolver.getPath(request, SecurityFowardPage.USERS_LIST);
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
			stateChecker.check(state, request);
			request.setAttribute("user", user);

		return PathResolver.getPath(request, SecurityBackPage.UPDATE_USER_PASSWORD);
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
			stateChecker.check(state, request);
			return  PathResolver.getPath(request, SecurityFowardPage.FIND_USER_ROLES.getValue() +"/" + userId, SecurityFowardPage.FIND_USER_ROLES);
		//return "redirect:"+ .FIND_USER_ROLES.getValue()+"/"+userId+AttributeKeys.WEB_SUFFIX;
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
			stateChecker.check(state, request);
			ps.setToolBar(localeResolver.resolveLocale(request));
			ps.savePage(request);
			request.setAttribute("user", user);

		return PathResolver.getPath(request, SecurityBackPage.FIND_OTHER_ROLE_BY_USER);
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
			stateChecker.check(state, request);
			request.setAttribute("functions", functions);
			request.setAttribute("user", user);

		return PathResolver.getPath(request, SecurityBackPage.FIND_FUNCTION_BY_USER);
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
		//权限检查
		functionChecker.check(userName, request);
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

			savePage(ps, request);
		return PathResolver.getPath(request, SecurityBackPage.USER_LIST_PAGE);
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
			stateChecker.check(state, request);
			request.setAttribute("roles", roles);
			request.setAttribute("user", user);

		return PathResolver.getPath(request, SecurityBackPage.FIND_ROLE_BY_USER_PAGE);
	}
	


	//new interface
	
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
		return PathResolver.getPath(request, SecurityBackPage.MODIFY_USER);
	}


	
	@RequestMapping(value = "/delete/{id}")
	public String delete(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String id) {
		//TODO didn't have this function
		return null;
	}


	
	@RequestMapping("/query")
	public String query(HttpServletRequest request,
			HttpServletResponse response, String curPageNO, User user) {

		String userName = UserManager.getUsername(request);
		if (userName == null) {
			throw new BusinessException("not login yet",
					ErrorCodes.UN_LOGIN);
		}
		//权限检查
		functionChecker.check(userName, request);
		String name = user.getName();
		String enabled = user.getEnabled();
		CriteriaQuery cq = new CriteriaQuery(User.class, curPageNO);
		cq.setPageSize(PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE,Integer.class));
		if (AppUtils.isNotBlank(name)) {
			cq.like("name", name + "%");// 1
		}
		if (AppUtils.isNotBlank(enabled)) {
			cq.eq("enabled", enabled);
		}
		cq.add();
		State state = new StateImpl();
		PageSupport ps = rightDelegate.findAllUser(cq, state);

		request.setAttribute("bean", user);

		savePage(ps, request);
		return PathResolver.getPath(request, SecurityBackPage.USER_LIST_PAGE);
	}

	
	@RequestMapping(value = "/save")
	public String save(HttpServletRequest request,
			HttpServletResponse response, User user) {
		State state = new StateImpl();

		if (rightDelegate.isUserExist(user.getName(), state)) {
			// return handleException(mapping, request,
			// ActionMessages.GLOBAL_MESSAGE, "error.User.IsExist");
			return PathResolver.getPath(request, CommonPage.FAIL);
		}
		
		String id=user.getId();
		
		if(StringUtil.isEmpty(id)){
			id = rightDelegate.saveUser(Md5Password(user), state);			
		}else{
			rightDelegate.updateUser(Md5Password(user), state);
		}
		logger.info("success saveUser,id = " + id);
		stateChecker.check(state, request);

		return PathResolver.getPath(request, SecurityFowardPage.USERS_LIST);
	}
	
	@RequestMapping(value = "/updatePassword")
	public String updatePassword(HttpServletRequest request, HttpServletResponse response, User user) {

		logger.info("Struts UserAction updateUserById");
		String passwordag = user.getPasswordag();
		if ((user.getPassword() == null) || (user.getPassword() == "")) {
			return PathResolver.getPath(request, CommonPage.ERROR_PAGE);
		}
		if (!passwordag.endsWith(user.getPassword())) {// 2次密码要相等
			return PathResolver.getPath(request, CommonPage.FAIL);
		}
		State state = new StateImpl();
		Md5PasswordEncoder coder = new Md5PasswordEncoder();
		coder.setEncodeHashAsBase64(false);
		rightDelegate.updateUserPassowrd(user.getId(), coder.encodePassword(user.getPassword(), user.getName()), state);
		stateChecker.check(state, request);
		return PathResolver.getPath(request, SecurityFowardPage.USERS_LIST);
	}

	
	@RequestMapping(value = "/update/{id}")
	public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable String id) {
		logger.info("Action findUserById with id  " + id);		
		State state = new StateImpl();
			User user = rightDelegate.findUserById(id, state);
			stateChecker.check(state, request);
			request.setAttribute("bean", user);
		return PathResolver.getPath(request, SecurityBackPage.UPDATE_USER_PASSWORD);
	}
	
	@RequestMapping(value = "/roles/{id}")
	public String roles(HttpServletRequest request, HttpServletResponse response, @PathVariable String id) {
		logger.info("UserAction findOtherRoleByUser : " + id);
		State state = new StateImpl();
		User user = rightDelegate.findUserById(id, state);
		List roles = rightDelegate.findRoleByUser(id, state);
		stateChecker.check(state, request);
		request.setAttribute("list", roles);
		request.setAttribute("bean", user);
		return PathResolver.getPath(request, SecurityBackPage.FIND_ROLE_BY_USER_PAGE);
	}

	@RequestMapping(value = "/otherRoles/{id}")
	public String otherRoles(HttpServletRequest request, HttpServletResponse response, String curPageNO,
			@PathVariable String id) {

		// HQL查找方式
		HqlQuery hq = new HqlQuery();
		hq.setCurPage(curPageNO);
		hq.setPageSize(PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE, Integer.class));
		State state = new StateImpl();
		User user = rightDelegate.findUserById(id, state);
		PageSupport ps = rightDelegate.findOtherRoleByUser(hq, id, state);
		stateChecker.check(state, request);
		savePage(ps, request);
		request.setAttribute("bean", user);

		return PathResolver.getPath(request, SecurityBackPage.FIND_OTHER_ROLE_BY_USER);
	}
	@RequestMapping(value = "/deleteRoles/{id}")
	public String deleteRoles(HttpServletRequest request, HttpServletResponse response, String[] strArray,@PathVariable String id) {

		logger.info("Action deleteUserRoleByUserId with userId  " + id);
		List<UserRole> userRoles = new ArrayList<UserRole>();
		for (String element : strArray) {
			UserRole userRole = new UserRole();
			UserRoleId userRoleId = new UserRoleId();
			userRoleId.setUserId(id);
			userRoleId.setRoleId(element);
			userRole.setId(userRoleId);
			userRoles.add(userRole);
		}
			State state = new StateImpl();
			rightDelegate.deleteRoleFromUser(userRoles, state);
			stateChecker.check(state, request);
		return PathResolver.getPath(request, SecurityFowardPage.FIND_ROLE_BY_USER);
	}

	
	@RequestMapping(value = "/functions/{id}")
	public String functions(HttpServletRequest request, HttpServletResponse response, @PathVariable String id) {		
		logger.info("UserAction findFunctionByUser : " + id);
		State state = new StateImpl();
			User user = rightDelegate.findUserById(id, state);
			List functions = rightDelegate.findFunctionByUser(id, state);
			stateChecker.check(state, request);
			request.setAttribute("list", functions);
			request.setAttribute("bean", user);

		return PathResolver.getPath(request, SecurityBackPage.FIND_FUNCTION_BY_USER);
	}

}
