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
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * ------------------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ------------------------------------------------------------------------------------
 * 
 * 官方网站：http://www.legendesign.net
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
