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
import com.legendshop.spi.constants.Constants;

/**
 * The Enum TilesPage.
 */
public enum GroupTilesPage implements PageDefinition{
	/** The VARIABLE. 可变路径 */
	VARIABLE(""),
	
	/** The Index. */
	INDEX("index.");
	
	
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
		return PagePathCalculator.calculateTilesPath(path, Constants.DEFAULT_PAGE);
	}

	
	/**
	 * Instantiates a new tiles page.
	 * 
	 * @param value
	 *            the value
	 */
	private GroupTilesPage(String value) {
		this.value = value;
	}
	

	@Override
	public String getValue() {
		return value;
	}
}
