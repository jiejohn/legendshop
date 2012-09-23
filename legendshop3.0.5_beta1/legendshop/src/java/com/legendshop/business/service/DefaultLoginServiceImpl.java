/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;

import com.legendshop.spi.service.LoginService;

/**
 * 默认的登录服务，for cas， do nothing
 */
public class DefaultLoginServiceImpl implements LoginService {

	/* (non-Javadoc)
	 * @see com.legendshop.spi.service.LoginService#onAuthentication(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.String, java.lang.String)
	 */
	@Override
	public Authentication onAuthentication(HttpServletRequest request, HttpServletResponse response, String username,
			String password) {
		return null;
	}

}
