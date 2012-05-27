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
 * The Enum FrontPage.
 */
public enum GroupFrontPage  implements PageDefinition{
	/** The VARIABLE. 可变路径 */
	VARIABLE(""),
	/** The VIEWS. */
	VIEW("/group/view"),
	CLIENT_SERVICE_PANEL("/group/clientServicePanel"),
	QUESTION("/group/question"),
	QUESTION_PANEL("/group/questionPanel");
	
	
	/** The value. */
	private final String value;

	/* (non-Javadoc)
	 * @see com.legendshop.core.constant.PageDefinition#getValue(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String getValue(HttpServletRequest request) {
		return getValue(request,value);
	}
	
	@Override
	public String getValue(HttpServletRequest request, String path) {
		return PagePathCalculator.calculateFronendPath(request,path);
	}

	
	/**
	 * Instantiates a new front page.
	 * 
	 * @param value
	 *            the value
	 */
	private GroupFrontPage(String value) {
		this.value = value;
	}
	

	@Override
	public String getValue() {
		return value;
	}
}
