/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.permission.page;

import javax.servlet.http.HttpServletRequest;

import com.legendshop.core.constant.PageDefinition;
import com.legendshop.core.constant.PagePathCalculator;

/**
 * The Enum BackPage.
 */
public enum SecurityBackPage implements PageDefinition{
	
	/** The VARIABLE. 可变路径 */
	VARIABLE(""),
	/** The UPDAT e_ function. */
	UPDATE_FUNCTION("/member/right/updateFunction"),

	// FIND_FUNCTION_BY_ROLE("/member/right/findFunctionByRole",FOWARD),

	/** The ROL e_ function. */
	ROLE_FUNCTION("/member/right/findFunctionByRole"),

	/** The FIN d_ othe r_ functio n_ list. */
	FIND_OTHER_FUNCTION_LIST("/member/right/findOtherfunctionList"),

	/** The FUNCTIO n_ list. */
	FUNCTION_LIST("/member/right/functionList"),

	/** The ROL e_ list. */
	ROLE_LIST("/member/right/roleList"),

	/** The FIN d_ rol e_ b y_ function. */
	FIND_ROLE_BY_FUNCTION("/member/right/findRoleByFunction"),

	/** The FIN d_ rol e_ b y_ use r_ page. */
	FIND_ROLE_BY_USER_PAGE("/member/user/findRoleByUser"),

	/** The UPDAT e_ use r_ status. */
	UPDATE_USER_STATUS("/member/user/updateUserStatus"),

	/** The UPDAT e_ use r_ password. */
	UPDATE_USER_PASSWORD("/member/user/updateUserPassword"),

	/** The MODIF y_ user. */
	MODIFY_USER("/member/user/saveUser"),

	/** The FIN d_ othe r_ rol e_ b y_ user. */
	FIND_OTHER_ROLE_BY_USER("/member/user/findOtherRoleByUser"),

	/** The FIN d_ functio n_ b y_ user. */
	FIND_FUNCTION_BY_USER("/member/user/findFunctionByUser"),

	/** The USE r_ lis t_ page. */
	USER_LIST_PAGE("/member/user/userlist"),

	/** The SAV e_ role. */
	SAVE_ROLE("/member/right/saveRole")
	
	;
	
	
	/** The value. */
	private final String value;
	
	/**
	 * Instantiates a new back page.
	 * 
	 * @param value
	 *            the value
	 */
	private SecurityBackPage(String value) {
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.core.constant.PageDefinition#getValue(javax.servlet.http.HttpServletRequest)
	 */
	public String getValue(HttpServletRequest request) {
		return getValue(request,value);
	}
	
	/* (non-Javadoc)
	 * @see com.legendshop.core.constant.PageDefinition#getValue(javax.servlet.http.HttpServletRequest, java.lang.String)
	 */
	public String getValue(HttpServletRequest request, String path) {
		return PagePathCalculator.calculateBackendPath(request,path);
	}

	
}
