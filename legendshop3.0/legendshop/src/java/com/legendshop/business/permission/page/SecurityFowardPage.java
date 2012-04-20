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
 * The Enum FowardPage.
 */
public enum SecurityFowardPage implements PageDefinition{
	/** The VARIABLE. 可变路径 */
	VARIABLE(""),
	
	/** The FUNCTIO n_ lis t_ query. */
	FUNCTION_LIST_QUERY("/member/right/listFunction"),

	/** The FIN d_ functio n_ b y_ role. */
	FIND_FUNCTION_BY_ROLE("/member/role/functions"),

	/** The FIN d_ al l_ role. */
	FIND_ALL_ROLE("/member/right/findAllRole"), 
	
	/** The FIN d_ rol e_ b y_ user. */
	FIND_ROLE_BY_USER("/member/user/findRoleByUser"),

	/** The FIN d_ use r_ roles. */
	FIND_USER_ROLES("/member/user/roles"),

	/** The AL l_ role. */
	ALL_ROLE("/member/role/query"),
	
	/** The USER s_ list. */
	USERS_LIST("/member/user/query")
	;
	
	
	/** The value. */
	private final String value;

	/* (non-Javadoc)
	 * @see com.legendshop.core.constant.PageDefinition#getValue(javax.servlet.http.HttpServletRequest)
	 */
	public String getValue(HttpServletRequest request) {
		return getValue(request,value);
	}
	
	public String getValue(HttpServletRequest request, String path) {
		return PagePathCalculator.calculateActionPath("forward:", path);
	}

	
	/**
	 * Instantiates a new tiles page.
	 * 
	 * @param value
	 *            the value
	 */
	private SecurityFowardPage(String value) {
		this.value = value;
	}
	


}
