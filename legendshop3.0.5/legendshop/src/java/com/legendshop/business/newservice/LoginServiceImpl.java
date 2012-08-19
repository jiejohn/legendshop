/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.newservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;

import com.legendshop.business.service.ValidateCodeUsernamePasswordAuthenticationFilter;

/**
 * 用户登录服务
 * The Class LoginServiceImpl.
 */
public class LoginServiceImpl extends ValidateCodeUsernamePasswordAuthenticationFilter{
	Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.LoginService#onAuthentication(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.String, java.lang.String)
	 */
	@Override
	public Authentication onAuthentication(HttpServletRequest request, HttpServletResponse response, String username,
			String password) {
		log.debug("userName {} register and login",username);
		return super.onAuthentication(request, response, username, password);
	}

}
