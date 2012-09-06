/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.spi.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;

/**
 * 用户登录服务
 * The Interface LoginService.
 */
public interface LoginService {
	
	/**
	 * On authentication.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param username
	 *            the username
	 * @param password
	 *            the password
	 * @return the authentication
	 */
	public Authentication onAuthentication(HttpServletRequest request, HttpServletResponse response, String username,
			String password) ;
}
