/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

/**
 * The Class AuthenticationFailureHandler.
 */
public class AuthenticationFailureHandlerImpl extends SimpleUrlAuthenticationFailureHandler {
	
    /** The Constant TRY_LOGIN_COUNT. */
    public static final String TRY_LOGIN_COUNT = "TRY_LOGIN_COUNT";
    
	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler#onAuthenticationFailure(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
	 */
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		increaseTryLoginCount(request);
		super.onAuthenticationFailure(request, response, exception);
	}
	
    /**
     * Increase try login count.
     *
     * @param request the request
     */
    private  void increaseTryLoginCount(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return;
        }

       Integer count = (Integer) session.getAttribute(TRY_LOGIN_COUNT);
       if(count == null){
    	   count = 0;
       }
       count ++;
       session.setAttribute(TRY_LOGIN_COUNT, count);
    } 
}
