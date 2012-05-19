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
public enum GroupFowardPage implements PageDefinition{
	
	/** The VARIABLE. 可变路径 */
	VARIABLE(""),
	
	PROD_LIST_QUERY("/admin/group/product/query"),
	
	;
	
	
	/** The value. */
	private final String value;
	
	/**
	 * Instantiates a new back page.
	 * 
	 * @param value
	 *            the value
	 */
	private GroupFowardPage(String value) {
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
		return PagePathCalculator.calculateActionPath("forward:", path);
	}

	public String getValue() {
		return value;
	}
}
