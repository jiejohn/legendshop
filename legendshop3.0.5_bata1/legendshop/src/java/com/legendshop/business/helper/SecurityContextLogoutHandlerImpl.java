/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.helper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import com.legendshop.spi.constants.Constants;
/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public class SecurityContextLogoutHandlerImpl extends
		SecurityContextLogoutHandler {
	
	/** The support sso. */
	private boolean supportSSO = false;

	/**
	 * Sets the support sso.
	 * 
	 * @param supportSSO
	 *            the new support sso
	 */
	public void setSupportSSO(boolean supportSSO) {
		this.supportSSO = supportSSO;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler#logout(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	 */
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) {
		request.getSession().setAttribute(Constants.USER_NAME,null);
		
		if(supportSSO){
			//删除Cookies
			//增加BBS的登录用户 SSO to club
			Cookie cookie = new Cookie(Constants.CLUB_COOIKES_NAME, null);
			cookie.setPath("/");
			cookie.setMaxAge(0); // -1为永不过期,或者指定过期时间
			response.addCookie( cookie );//在退出登录操作中删除cookie.
		}
		
		//request.getSession().invalidate();
		super.logout(request, response, authentication);
	}

}
