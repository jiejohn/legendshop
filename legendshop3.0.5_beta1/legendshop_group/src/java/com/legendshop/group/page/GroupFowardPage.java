/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.group.page;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.legendshop.core.constant.PageDefinition;
import com.legendshop.core.constant.PagePathCalculator;
import com.legendshop.util.AppUtils;

/**
 * The Enum BackPage.
 */
public enum GroupFowardPage implements PageDefinition {

	/** The VARIABLE. 可变路径 */
	VARIABLE(""),

	/** The prod list query. */
	PROD_LIST_QUERY("/admin/group/product/query"),

	/** The gsort list query. */
	GSORT_LIST_QUERY("/admin/gsort/query"),
	;

	/** The value. */
	private final String value;

	/** The templates. */
	private List<String> templates;

	/**
	 * Instantiates a new group foward page.
	 * 
	 * @param value
	 *            the value
	 * @param template
	 *            the template
	 */
	private GroupFowardPage(String value, String... template) {
		this.value = value;
		if (AppUtils.isNotBlank(template)) {
			this.templates = new ArrayList<String>();
			for (String temp : template) {
				templates.add(temp);
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.core.constant.PageDefinition#getValue(javax.servlet.http
	 * .HttpServletRequest)
	 */
	@Override
	public String getValue(HttpServletRequest request, HttpServletResponse response) {
		return getValue(request,response,value, templates);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.core.constant.PageDefinition#getValue(javax.servlet.http
	 * .HttpServletRequest, java.lang.String)
	 */
	@Override
	public String getValue(HttpServletRequest request, HttpServletResponse response,String path, List<String> templates) {
		return PagePathCalculator.calculateActionPath("forward:", path);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.core.constant.PageDefinition#getNativeValue()
	 */
	@Override
	public String getNativeValue() {
		return value;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.core.constant.PageDefinition#getTemplates()
	 */
	@Override
	public List<String> getTemplates() {
		return this.templates;
	}
}
