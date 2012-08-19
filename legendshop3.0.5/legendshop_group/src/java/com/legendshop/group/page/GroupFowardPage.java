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
 * The Enum BackPage.
 */
public enum GroupFowardPage implements PageDefinition {

	/** The VARIABLE. 可变路径 */
	VARIABLE(""),

	/** The PRO d_ lis t_ query. */
	PROD_LIST_QUERY("/admin/group/product/query"),

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
	public String getValue() {
		return getValue(value, templates);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.core.constant.PageDefinition#getValue(javax.servlet.http
	 * .HttpServletRequest, java.lang.String)
	 */
	@Override
	public String getValue(String path, List<String> templates) {
		return PagePathCalculator.calculateActionPath("forward:", path);
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
