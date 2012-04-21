/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.group.page;

import javax.servlet.http.HttpServletRequest;

import com.legendshop.core.constant.PageDefinition;
import com.legendshop.core.constant.PagePathCalculator;

/**
 * The Enum BackPage.
 */
public enum GroupBackPage implements PageDefinition{
	
	/** The VARIABLE. 可变路径 */
	VARIABLE(""),
	/** The UPDAT e_ function. */
	PROD_LIST_PAGE("/group/gprodList"),

	/** The PRO d_ edi t_ page. */
	PROD_EDIT_PAGE("/group/gprod"),
	
	;
	
	
	/** The value. */
	private final String value;
	
	/**
	 * Instantiates a new back page.
	 * 
	 * @param value
	 *            the value
	 */
	private GroupBackPage(String value) {
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
