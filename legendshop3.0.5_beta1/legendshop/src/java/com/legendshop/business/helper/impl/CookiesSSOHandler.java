package com.legendshop.business.helper.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import com.legendshop.business.form.UserForm;
import com.legendshop.business.helper.AbstractHandler;
import com.legendshop.business.service.DefaultLoginServiceImpl;
import com.legendshop.business.service.UserDetailService;
import com.legendshop.core.UserManager;
import com.legendshop.core.exception.ApplicationException;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.helper.Handler;
import com.legendshop.spi.constants.Constants;
import com.legendshop.spi.service.LoginService;
import com.legendshop.util.ContextServiceLocator;
import com.legendshop.util.CookieUtil;

public class CookiesSSOHandler extends AbstractHandler implements Handler {
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(CookiesSSOHandler.class);
	
	private  LoginService loginService;
	
	private UserDetailService userDetailService;
	 
	private boolean invalidateHttpSession = true;
	
	public static String LOGINED_USER = "LEGENDSHOP_LOGINED_USER";
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			String loginedUserName = CookieUtil.getCookieValue(request, LOGINED_USER);
			String userName = UserManager.getUsername(request);
			if(requiresLogin(loginedUserName,userName)){
				
				//如果用户不存在则重新建立用户
				if(!userDetailService.isUserExist(loginedUserName)){
					UserForm form = new UserForm();
					form.setName(loginedUserName);
					form.setUserName(loginedUserName);
					form.setEnabled("1");
					form.setPassword(loginedUserName);
					form.setNickName(loginedUserName);
					form.setUserMail(loginedUserName + "@legendshop.cn");
					userDetailService.saveUserReg(request, response, form);
				}
				
				getLoginService().onAuthentication(request, response, loginedUserName, loginedUserName);
				
			}else if(requiresLogout(loginedUserName,userName)){
				logout(request,response); 
			}
		} catch (Exception e) {
			throw new ApplicationException(e, "CookiesSSOHandler Fail", EntityCodes.SYSTEM);
		}

	}
	
	
	protected void logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute(Constants.USER_NAME,null);
        if (invalidateHttpSession) {
            HttpSession session = request.getSession(false);
            if (session != null) {
            	log.debug("Invalidating session: " + session.getId());
                session.invalidate();
            }
        }

        SecurityContextHolder.clearContext();
	}
	
	/**
	 * 是否需要登出
	 * @param loginedUserName
	 * @param userName
	 * @return
	 */
	   protected boolean requiresLogout(String loginedUserName,String userName) {
		   if(loginedUserName == null && userName!=null){
			   //外部系统已经退出，发现本系统已经登录，立即退出
			   return true;
		   }
		   return false;
	   }
	   
		/**
		 * 是否需要登录
		 * @param loginedUserName
		 * @param userName
		 * @return
		 */
		   protected boolean requiresLogin(String loginedUserName,String userName) {
			   if(loginedUserName != null && (userName == null  || !loginedUserName.equals(userName)) ){
				   //外部系统已经登录，发现本系统没有登录，立即登录
				   return true;
			   }
			   return false;
		   }

			public void setInvalidateHttpSession(boolean invalidateHttpSession) {
				this.invalidateHttpSession = invalidateHttpSession;
			}

		    public void setUserDetailService(UserDetailService userDetailService) {
				this.userDetailService = userDetailService;
			}

			private LoginService getLoginService(){
				if(loginService==null){
					if(ContextServiceLocator.getInstance().containsBean("loginService")){
						loginService = (LoginService)ContextServiceLocator.getInstance().getBean("loginService");
					}
					if(loginService == null){
						loginService = new DefaultLoginServiceImpl();
					}
				}
				return loginService;
			}
}
