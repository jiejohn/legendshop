/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.common.page;

import javax.servlet.http.HttpServletRequest;

import com.legendshop.core.AttributeKeys;
import com.legendshop.core.constant.PathEnum;

/**
 * The Enum RedirectPage.
 */
public enum RedirectPage implements PathEnum{
	;
	
	
	/** The value. */
	private final String value;

	/* (non-Javadoc)
	 * @see com.legendshop.core.constant.PathEnum#getValue(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String getValue(HttpServletRequest request) {
		//TODO
		return new StringBuffer("redirect:").append(value).append(AttributeKeys.WEB_SUFFIX).toString();
	}
	
	/**
	 * Instantiates a new tiles page.
	 * 
	 * @param value
	 *            the value
	 */
	private RedirectPage(String value) {
		this.value = value;
	}
	
	/* (non-Javadoc)
	 * @see com.legendshop.core.constant.PathEnum#getType()
	 */
	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.core.constant.PathEnum#getValue()
	 */
	@Override
	public String getValue() {
		return value;
	}

}
