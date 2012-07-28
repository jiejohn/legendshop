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

import com.legendshop.core.constant.PageDefinition;
import com.legendshop.core.constant.PagePathCalculator;
import com.legendshop.util.AppUtils;

/**
 * The Enum FrontPage.
 */
public enum GroupFrontPage implements PageDefinition {
	/** The VARIABLE. 可变路径 */
	VARIABLE(""),

	/** The INDEX. */
	INDEX("/group/index"),

	/** The VIEWS. */
	VIEW("/group/view"),

	/** The CLIEN t_ servic e_ panel. */
	CLIENT_SERVICE_PANEL("/group/clientServicePanel"),

	/** The QUESTION. */
	QUESTION("/group/question"),

	/** The QUESTIO n_ panel. */
	QUESTION_PANEL("/group/questionPanel");

	/** The value. */
	private final String value;

	/** The templates. */
	private List<String> templates;

	/**
	 * Instantiates a new group front page.
	 * 
	 * @param value
	 *            the value
	 * @param template
	 *            the template
	 */
	private GroupFrontPage(String value, String... template) {
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
	public String getValue() {
		return getValue(value, templates);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.core.constant.PageDefinition#getValue(javax.servlet.http
	 * .HttpServletRequest, java.lang.String, java.util.List)
	 */
	@Override
	public String getValue(String path, List<String> templates) {
		return PagePathCalculator.calculateFronendPath(path, templates);
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
	public String getNativeValue() {
		return value;
	}

	@Override
	public List<String> getTemplates() {
		return this.templates;
	}
}
