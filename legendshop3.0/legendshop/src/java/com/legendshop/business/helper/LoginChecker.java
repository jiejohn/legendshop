/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.helper;

import javax.servlet.http.HttpServletRequest;

import com.legendshop.core.AttributeKeys;
import com.legendshop.core.UserManager;
import com.legendshop.core.helper.Checker;

/**
 * 是否登录Checker
 */
public class LoginChecker implements Checker<UserManager> {

	/* (non-Javadoc)
	 * @see com.legendshop.core.helper.Checker#check(java.lang.Object, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public boolean check(UserManager userManager, HttpServletRequest request) {
		String userName = (String) request.getSession().getAttribute(AttributeKeys.USER_NAME);
		if (userName == null) {
			userName = UserManager.getUsername(request);
		}

		return userName != null;
	}

}
