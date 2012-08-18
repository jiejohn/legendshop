/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legendshop.business.common.page.FrontPage;
import com.legendshop.business.common.page.TilesPage;
import com.legendshop.business.event.EventId;
import com.legendshop.business.form.UserForm;
import com.legendshop.business.service.BasketService;
import com.legendshop.business.service.LoginService;
import com.legendshop.business.service.UserDetailService;
import com.legendshop.business.service.timer.SubService;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.FunctionEnum;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.event.EventContext;
import com.legendshop.event.EventHome;
import com.legendshop.event.GenericEvent;
import com.legendshop.model.entity.ShopDetail;
import com.legendshop.model.entity.UserDetail;
import com.legendshop.spi.constants.Constants;
import com.legendshop.util.AppUtils;

/**
 * 产品分类控制器。.
 */
@Controller
public class UserController extends BaseController{
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserDetailService userDetailService;
	
	@Autowired
	private SubService subService;
	
	@Autowired
	private BasketService basketService;
	
	@Autowired
	private  LoginService loginService;
	/**
	 * Myaccount.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param curPageNO
	 *            the cur page no
	 * @param newsCategory
	 *            the news category
	 * @return the string
	 */
	@RequestMapping("/p/myaccount")
	public String myaccount(HttpServletRequest request, HttpServletResponse response,String curPageNO,String newsCategory) {
		String userName = UserManager.getUsername(request);
		if (userName == null) {
			return PathResolver.getPath(TilesPage.LOGIN);
		}
		String viewName = request.getParameter("userName");
		if (AppUtils.isNotBlank(viewName)) {
			if (UserManager.hasFunction(request.getSession(),FunctionEnum.FUNCTION_SECUREST.value())) { // 保留，只能超级管理员可以看
				userName = viewName;
				request.setAttribute("isAdmin", true); // 管理员不可操作
			}
		}
		UserDetail userDetail = userDetailService.getUserDetail(userName);
		if (userDetail == null) {
			log.error("userDetail not found, userName = " + userName);
			throw new NotFoundException("userDetail not found",EntityCodes.USER);
		}
		// 如果加入即会返回当前用户的当铺
		ShopDetail shopDetail = userDetail.getShopDetail();
		if (shopDetail != null) {
			request.setAttribute("myShopDetail", shopDetail);
		}

		if (userDetail.getBirthDate() != null) {
			setBirthDate(userDetail.getBirthDate(), request);
		}
		if (userDetail.getScore() == null) {
			userDetail.setScore(0l);// 默认
		}
		request.setAttribute("user", userDetail);
		
		EventContext eventContext = new EventContext(request);
		EventHome.publishEvent(new GenericEvent(eventContext,EventId.CAN_ADD_SHOPDETAIL_EVENT));
		
		request.setAttribute("supportOpenShop", eventContext.getBooleanResponse());
		
		request.setAttribute("totalProcessingOrder", subService.getTotalProcessingOrder(userName));
		request.setAttribute("totalBasketByuserName", basketService.getTotalBasketByuserName(userName));
		userDetailService.getAndSetOneAdvertisement(getShopName(request, response), Constants.USER_REG_ADV_740);
		return PathResolver.getPath(TilesPage.MYACCOUNT);
	}
	
	
	/**
	 * Shopcontact.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping("/shopcontact")
	public String shopcontact(HttpServletRequest request, HttpServletResponse response) {
		String shopName = request.getParameter("shop");
		if (shopName == null) {
			shopName = getShopName(request, response);
		}
		if (shopName == null) {
			return PathResolver.getPath(TilesPage.SEARCHALL);
		}
		UserDetail userDetail = userDetailService.getUserDetail(shopName);
		// 如果加入即会返回当前用户的当铺
		request.setAttribute("user", userDetail);
		return PathResolver.getPath(TilesPage.SHOPCONTACT);
	}
	
	/**
	 * Login.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping("/p/login")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		return PathResolver.getPath(TilesPage.LOGIN);
	}
	
	/**
	 * Update account.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param userForm
	 *            the user form
	 * @return the string
	 */
	@RequestMapping("/updateAccount")
	public String updateAccount(HttpServletRequest request, HttpServletResponse response,UserForm userForm) {
		return userDetailService.updateAccount(request, response,userForm);
	}
	
	
	/**
	 * 用户注册动作
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param userForm
	 *            the user form
	 * @return the string
	 */
	@RequestMapping("/userReg")
	public String userReg(HttpServletRequest request, HttpServletResponse response,UserForm userForm) {
		String result =  userDetailService.saveUserReg(request, response,userForm);
		//用户注册即登录
		if(loginService!=null){
			Authentication authentication = loginService.onAuthentication(request, response, userForm.getName(), userForm.getPassword());
			SecurityContextHolder.getContext().setAuthentication(authentication);
			request.getSession().setAttribute(Constants.SPRING_SECURITY_CONTEXT, SecurityContextHolder.getContext());
		}
		return result;
	}
	
	/**
	 * 加载用户注册页面.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping("/reg")
	public String reg(HttpServletRequest request, HttpServletResponse response) {
		userDetailService.getAndSetOneAdvertisement(getShopName(request, response), Constants.USER_REG_ADV_950);
		
		EventContext eventContext = new EventContext(request);
		EventHome.publishEvent(new GenericEvent(eventContext,EventId.CAN_ADD_SHOPDETAIL_EVENT));
		
		request.setAttribute("supportOpenShop", eventContext.getBooleanResponse());
		
		request.setAttribute("validationOnOpenShop",
				PropertiesUtil.getObject(ParameterEnum.VALIDATION_ON_OPEN_SHOP, Boolean.class));
		return PathResolver.getPath(TilesPage.REG);
	}
	
	/**
	 * Adds the shop.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param shopDetail
	 *            the shop detail
	 * @return the string
	 */
	@RequestMapping("/addShop")
	public String addShop(HttpServletRequest request, HttpServletResponse response,ShopDetail shopDetail) {
		// 用户需要登录
		String userName = UserManager.getUsername(request);
		if (AppUtils.isBlank(userName)) {
			return PathResolver.getPath(TilesPage.NO_LOGIN);
		}
		return userDetailService.saveShop(request, response,shopDetail);
	}	
	
	/**
	 * User reg success.
	 * 
	 * 用户用registerCode激活帐号
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param userName
	 *            the user name
	 * @param registerCode
	 *            the register code
	 * @return the string
	 */
	@RequestMapping("/userRegSuccess")
	public String userRegSuccess(HttpServletRequest request, HttpServletResponse response,String userName, String registerCode) {
		return userDetailService.updateUserReg(request, response,userName,registerCode);
	}
	
	/**
	 * Resetpassword.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping("/resetpassword")
	public String resetpassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return PathResolver.getPath(FrontPage.RESETPASSWORD);
	}
	
	@RequestMapping("/openShop")
	public String openShop(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return PathResolver.getPath(TilesPage.OPENSHOP);
	}
	
	
	
	/**
	 * Sets the birth date.
	 * 
	 * @param birthDate
	 *            the birth date
	 * @param request
	 *            the request
	 */
	private void setBirthDate(String birthDate, HttpServletRequest request) {
		try {
			String year = birthDate.substring(0, 4);
			String month = birthDate.substring(4, 6);
			String day = birthDate.substring(6, 8);
			request.setAttribute("userBirthYear", year);
			request.setAttribute("userBirthMonth", month);
			request.setAttribute("userBirthDay", day);
		} catch (Exception e) {

		}

	}
	
}
