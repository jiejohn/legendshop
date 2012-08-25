/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oro.text.regex.MalformedPatternException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.business.common.page.FrontPage;
import com.legendshop.business.common.page.TilesPage;
import com.legendshop.business.dao.UserDetailDao;
import com.legendshop.business.form.UserForm;
import com.legendshop.business.helper.TaskThread;
import com.legendshop.business.helper.impl.SendMailTask;
import com.legendshop.business.service.UserDetailService;
import com.legendshop.core.UserManager;
import com.legendshop.core.constant.FunctionEnum;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.constant.ShopStatusEnum;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SqlQuery;
import com.legendshop.core.exception.ApplicationException;
import com.legendshop.core.exception.BusinessException;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.exception.ErrorCodes;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.RealPathUtil;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.model.UserMessages;
import com.legendshop.model.entity.ShopDetail;
import com.legendshop.model.entity.User;
import com.legendshop.model.entity.UserDetail;
import com.legendshop.spi.constants.Constants;
import com.legendshop.spi.constants.RegisterEnum;
import com.legendshop.util.AppUtils;
import com.legendshop.util.BeanHelper;
import com.legendshop.util.MD5Util;
import com.legendshop.util.SafeHtml;

/**
 * 用户服务.
 */
public class UserDetailServiceImpl  extends BaseServiceImpl  implements UserDetailService {
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(UserDetailServiceImpl.class);
	
	/** The user detail dao. */
	private UserDetailDao userDetailDao;

	
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.UserDetailService#queryScore(java.lang.String)
	 */
	@Override
	public Long getScore(String userName) {
		return userDetailDao.getUserScore(userName);
	}
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.UserDetailService#getUserDetailList(com.legendshop.core.dao.support.HqlQuery)
	 */
	@Override
	public PageSupport getUserDetailList(HqlQuery hqlQuery) {
		return userDetailDao.getUserDetailList(hqlQuery);
	}
	

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.UserDetailService#findUserDetail(java.lang.String)
	 */
	@Override
	public UserDetail getUserDetail(String userName) {
		return userDetailDao.getUserDetail(userName);
	}
	
	@Override
	public String updateAccount(HttpServletRequest request, HttpServletResponse response, UserForm form) {
		ShopDetail shopDetail = form.getShopDetail();
		String userName = UserManager.getUsername(request);
		if (CommonServiceUtil.haveViewAllDataFunction(request)) {// admin
			if (AppUtils.isNotBlank(form.getUserName())) {
				userName = form.getUserName();
			}
		}
		User user = userDetailDao.getUserByName(userName);
		Locale locale = localeResolver.resolveLocale(request);
		if (user == null) {
			UserMessages messages = new UserMessages(ErrorCodes.SAVE_ERROR, ResourceBundleHelper.getString(locale,
					"update.myaccount.fail"), ResourceBundleHelper.getString(locale, "check.parameter"));
			messages.addCallBackList(ResourceBundleHelper.getString(locale, "reupdate.myaccount"), null,
					"updateAccount.do");
			request.setAttribute(UserMessages.MESSAGE_KEY, messages);
			return PathResolver.getPath(request,response,TilesPage.AFTER_OPERATION);
		}
		UserDetail userDetail = userDetailDao.getUserDetail(userName);
		if (!AppUtils.isBlank(form.getPassword())) {
			if (!user.getPassword().equals(MD5Util.Md5Password(userName, form.getPasswordOld()))) {
				log.warn("old password does not match!");
				UserMessages messages = new UserMessages(ErrorCodes.SAVE_ERROR, ResourceBundleHelper.getString(
						locale, "error.old.password"), ResourceBundleHelper.getString(locale, "check.parameter"));
				messages.addCallBackList(ResourceBundleHelper.getString(locale, "reupdate.myaccount"),
						ResourceBundleHelper.getString(locale, "notmatch.old.password"), "myaccount.do");
				request.setAttribute(UserMessages.MESSAGE_KEY, messages);

				return PathResolver.getPath(request,response,TilesPage.AFTER_OPERATION);
			}
		}
		boolean update = true;
		if (userDetail == null) {
			update = false;
			userDetail = new UserDetail();
		}
		Date date = new Date();
		SafeHtml safeHtml = new SafeHtml();
		userDetail.setNickName(safeHtml.makeSafe(form.getNickName()));
		userDetail.setSex(safeHtml.makeSafe(form.getSex()));
		userDetail.setBirthDate(form.getBirthDate());
		if (UserManager.hasFunction(request,new String[]{FunctionEnum.FUNCTION_VIEW_ALL_DATA.value(),FunctionEnum.FUNCTION_F_OPERATOR.value()})) {
			userDetail.setUserMail(form.getUserMail());
		}

		userDetail.setUserAdds(safeHtml.makeSafe(form.getUserAdds()));
		userDetail.setUserTel(safeHtml.makeSafe(form.getUserTel()));
		userDetail.setUserPostcode(safeHtml.makeSafe(form.getUserPostcode()));
		userDetail.setPassword(form.getPassword());
		userDetail.setFax(safeHtml.makeSafe(form.getFax()));
		userDetail.setModifyTime(date);
		userDetail.setUserId(user.getId());
		userDetail.setUserMobile(safeHtml.makeSafe(form.getUserMobile()));
		userDetail.setMsn(safeHtml.makeSafe(form.getMsn()));

		userDetail.setQq(safeHtml.makeSafe(form.getQq()));
		// 生日设定
		String year = form.getUserBirthYear();
		String month = form.getUserBirthMonth();
		String day = form.getUserBirthDay();
		if (year != null && form.getUserBirthMonth() != null && form.getUserBirthDay() != null) {
			if (month.length() < 2) {
				month = "0" + month;
			}
			if (day.length() < 2) {
				day = "0" + day;
			}
			userDetail.setBirthDate(year + month + day);
		}

		boolean openshop = request.getParameter("openShop") != null;
		if (update) {
			userDetailDao.updateUser(userDetail);
			userDetailDao.updateShopDetail(userDetail, shopDetail, openshop);
		} else {
			userDetail.setUserRegip(request.getRemoteAddr());
			userDetail.setUserRegtime(date);
			userDetail.setUserId(user.getId());
			userDetail.setUserName(userName);
			userDetailDao.saveUerDetail(userDetail, shopDetail, openshop);
			userDetailDao.updatePassword(userDetail);
		}

		UserMessages messages = new UserMessages(ErrorCodes.NORMAL_STAUTS, "", "");
		messages.addCallBackList(ResourceBundleHelper.getString(locale, "myaccount"),
				ResourceBundleHelper.getString(locale, "reupdate.myaccount"), "p/myaccount" );
		request.setAttribute(UserMessages.MESSAGE_KEY, messages);
		return PathResolver.getPath(request,response,TilesPage.AFTER_OPERATION);
	}

	@Override
	public String saveUserReg(HttpServletRequest request, HttpServletResponse response, UserForm form) {
		// 过滤特殊字符
		SafeHtml safeHtml = new SafeHtml();
		form.setUserName(safeHtml.makeSafe(form.getUserName()));
		form.setUserMemo(safeHtml.makeSafe(form.getUserMemo()));
		form.setUserMobile(safeHtml.makeSafe(form.getUserMobile()));
		form.setUserPostcode(safeHtml.makeSafe(form.getUserPostcode()));
		form.setUserTel(safeHtml.makeSafe(form.getUserTel()));
		form.setUserMail(safeHtml.makeSafe(form.getUserMail()));
		form.setUserAdds(safeHtml.makeSafe(form.getUserAdds()));
		form.setMsn(safeHtml.makeSafe(form.getMsn()));
		form.setNote(safeHtml.makeSafe(form.getNote()));
		form.setQq(safeHtml.makeSafe(form.getQq()));
		form.setName(safeHtml.makeSafe(form.getName()));
		form.setNickName(safeHtml.makeSafe(form.getNickName()));

		ShopDetail shopDetail = form.getShopDetail();
		if (shopDetail != null) {
			shopDetail.setRealPath(RealPathUtil.getBigPicRealPath());
			shopDetail.setIp(request.getRemoteAddr());
			shopDetail.setSiteName(safeHtml.makeSafe(shopDetail.getSiteName()));
			shopDetail.setPostAddr(safeHtml.makeSafe(shopDetail.getPostAddr()));
			shopDetail.setIdCardNum(safeHtml.makeSafe(shopDetail.getIdCardNum()));
		}
		if (isUserInfoValid(form, request)) {
			return PathResolver.getPath(request,response,FrontPage.FAIL);
		}
		
		User user = new User();
		UserDetail userDetail = new UserDetail();
		BeanHelper.copyProperties(user, form, true);
		BeanHelper.copyProperties(userDetail, form, true);
		Date date = new Date();
		String plaintPassword = user.getPassword();
		user.setPassword(MD5Util.Md5Password(user.getName(), plaintPassword));
		userDetail.setUserRegtime(date);
		userDetail.setModifyTime(date);
		userDetail.setUserRegip(request.getRemoteAddr());
		userDetail.setTotalCash(0d);
		userDetail.setTotalConsume(0d);
		boolean isOpenShop = request.getParameter("openShop") != null;

		userDetailDao.saveUser(user, userDetail, shopDetail, isOpenShop);
		
		//提醒语
		UserMessages uem = new UserMessages();
		Locale locale = localeResolver.resolveLocale(request);
		uem.setTitle(ResourceBundleHelper.getString(locale, "regFree") + " " + form.getName() + " "
				+ ResourceBundleHelper.getString(locale, "success.hint"));
		if (userDetail.getRegisterCode() == null) {
			uem.setDesc(ResourceBundleHelper.getString(locale, "after.reg.success"));
		} else {
			uem.setDesc(ResourceBundleHelper.getString(locale, "reg.success.acknowledgement"));
		}

		uem.addCallBackList(ResourceBundleHelper.getString(locale, "login"),
				ResourceBundleHelper.getString(locale, "logon.hint.desc"), "login");
		request.setAttribute(UserMessages.MESSAGE_KEY, uem);
		userDetailDao.flush();
		
		
		// 发送通知注册成功邮件
		if (PropertiesUtil.sendMail()) {
			try {
				String filePath = request.getSession().getServletContext().getRealPath("/")
						+ "/WEB-INF/template/mail/registersuccess.jsp";
				// String text = FileProcessor.readFile(new File(filePath));
				Map<String, String> values = new HashMap<String, String>();
				values.put("#nickName#", userDetail.getNickName());
				values.put("#userName#", userDetail.getUserName());
				values.put("#password#", userDetail.getPassword());
				if (AppUtils.isNotBlank(userDetail.getRegisterCode())) {
					StringBuffer buffer = new StringBuffer();
					buffer.append("<p>你的帐号尚未开通，<a href=\"").append(Constants.LEGENDSHOP_DOMAIN_NAME)
							.append("/userRegSuccess" + "?userName=").append(user.getName()).append("&registerCode=")
							.append(userDetail.getRegisterCode()).append("\">点击开通我的帐号</a></p><br>");
					values.put("#registerCode#", buffer.toString());
				} else {
					StringBuffer buffer = new StringBuffer();
					buffer.append("<p>你的帐号已经开通成功!</p><br>");
					values.put("#registerCode#", buffer.toString());
				}
				String text = AppUtils.convertTemplate(filePath, values);
				threadPoolExecutor.execute(new TaskThread(new SendMailTask(javaMailSender, userDetail.getUserMail(),
						"恭喜您，注册商城成功", text)));
				log.info("{} 注册成功，发送通知邮件", userDetail.getUserMail());
			} catch (Exception e) {
				log.info("{}，发送通知邮件失败，请检查邮件配置", userDetail.getUserMail());
				throw new ApplicationException(e, "发送通知邮件失败，请检查邮件配置",EntityCodes.PROD);
			}

		}
		return PathResolver.getPath(request,response,TilesPage.AFTER_OPERATION);

	}
	
	@Override
	public String saveShop(HttpServletRequest request, HttpServletResponse response, ShopDetail shopDetail) {
		Locale locale = localeResolver.resolveLocale(request);
		try {
			if (shopDetail != null) {
				shopDetail.setRealPath(RealPathUtil.getBigPicRealPath());
			}
			UserDetail userDetail = new UserDetail();
			userDetail.setUserId(UserManager.getUserId(request.getSession()));
			userDetail.setUserName(UserManager.getUsername(request.getSession()));
			Integer status = userDetailDao.saveShopDetailAndRole(userDetail, shopDetail);
			String openResultDesc = null;
			
			if (ShopStatusEnum.AUDITING.value().equals(status)) {
				openResultDesc = ResourceBundleHelper.getString(locale, "apply.shop.auditing");
			} else {
				openResultDesc = ResourceBundleHelper.getString(locale, "apply.shop.success.relogin");
			}

			UserMessages messages = new UserMessages(ErrorCodes.NORMAL_STAUTS, ResourceBundleHelper.getString(locale,
					"apply.shop.success"), openResultDesc);
			request.setAttribute(UserMessages.MESSAGE_KEY, messages);
			return PathResolver.getPath(request,response,TilesPage.AFTER_OPERATION);

		} catch (Exception e) {
			log.error("addShop ", e);
			UserMessages messages = new UserMessages(ErrorCodes.SAVE_ERROR, ResourceBundleHelper.getString(
					locale, "apply.shop.failed"), ResourceBundleHelper.getString(locale, "check.parameter"));
			messages.addCallBackList(ResourceBundleHelper.getString(locale, "try.again"), null, "openShop.do");
			request.setAttribute(UserMessages.MESSAGE_KEY, messages);
			return PathResolver.getPath(request,response,FrontPage.ERROR_PAGE);
		}

	}
	
	/**
	 * 用户用registerCode激活帐号.
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
	@Override
	public String updateUserReg(HttpServletRequest request, HttpServletResponse response, String userName,
			String registerCode) {

		RegisterEnum result = userDetailDao.getUserRegStatus(userName, registerCode);
		if (!RegisterEnum.REGISTER_SUCCESS.equals(result)) {
			throw new BusinessException(ResourceBundleHelper.getString(result.value()),EntityCodes.USER);
		}
		Locale locale = localeResolver.resolveLocale(request);
		UserMessages messages = new UserMessages(ErrorCodes.NORMAL_STAUTS, ResourceBundleHelper.getString(locale,
				"reg.success.actived"), "");
		messages.addCallBackList(ResourceBundleHelper.getString(locale, "login"),
				ResourceBundleHelper.getString(locale, "logon.hint.desc"), "login" );
		request.setAttribute(UserMessages.MESSAGE_KEY, messages);
		return PathResolver.getPath(request,response,TilesPage.AFTER_OPERATION);
	}
	
	/**
	 * Sets the user detail dao.
	 * 
	 * @param userDetailDao
	 *            the new user detail dao
	 */
	@Required
	public void setUserDetailDao(UserDetailDao userDetailDao) {
		this.userDetailDao = userDetailDao;
	}

	@Override
	public PageSupport getUserDetailList(SqlQuery sqlQuery) {
		return userDetailDao.getUserDetailList(sqlQuery);
	}

	@Override
	public String deleteUserDetail(String userId, String userName, String realPicPath, String smallPicPath) {
		return userDetailDao.deleteUserDetail(userId, userName, realPicPath, smallPicPath);
	}

	@Override
	public boolean updatePassword(String userName, String mail, String templateFilePath)
			throws MalformedPatternException, MessagingException {
		return userDetailDao.updatePassword(userName, mail, templateFilePath);
		
	}
	
	private boolean isUserInfoValid(UserForm form, HttpServletRequest request) {
		boolean result = false;
		// 检查是否重名
		UserMessages messages = new UserMessages();
		Locale locale = localeResolver.resolveLocale(request);
		if (AppUtils.isBlank(form.getName())) {
			messages.addCallBackList(ResourceBundleHelper.getString(locale, "username.required"));
			result = true;
		}
		if (!result) {
			if (form.getName().length() < 4) {
				messages.addCallBackList(ResourceBundleHelper.getString(locale, "username.minlength"));
				result = true;
			}
			if (userDetailDao.isUserExist(form.getName())) {
				messages.addCallBackList(ResourceBundleHelper.getString(locale, "error.User.IsExist"));
			}
			if (AppUtils.isBlank(form.getUserMail())) {
				messages.addCallBackList(ResourceBundleHelper.getString(locale, "user.email.required"));
			} else {
				if (userDetailDao.isEmailExist(form.getUserMail())) {
					messages.addCallBackList("Email <b>" + form.getUserMail() + "</b> "
							+ ResourceBundleHelper.getString(locale, "user.email.exists"));
				}
			}

		}
		result = messages.hasError();
		if (result) {
			request.setAttribute(UserMessages.MESSAGE_KEY, messages);
			request.setAttribute("userForm", form);
		}
		return result;
	}

}
