/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.common.page;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.legendshop.core.constant.PageDefinition;
import com.legendshop.core.constant.PagePathCalculator;
import com.legendshop.util.AppUtils;

/**
 * The Enum RedirectPage.
 */
public enum RedirectPage implements PageDefinition{
	/** The VARIABLE. 可变路径 */
	VARIABLE(""),;
	
	
	/** The value. */
	private final String value;
	
	private List<String> templates;
	

	private RedirectPage(String value,String ... template) {
		this.value = value;
		if(AppUtils.isNotBlank(template)){
			this.templates = new ArrayList<String>();
			for (String temp : template) {
				templates.add(temp);
			}
		}
		
	}

	/* (non-Javadoc)
	 * @see com.legendshop.core.constant.PageDefinition#getValue(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String getValue(HttpServletRequest request) {
		return getValue(request,value,templates);
	}
	
	@Override
	public String getValue(HttpServletRequest request, String path,List<String> templates) {
		return PagePathCalculator.calculateActionPath("redirect:", path);
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


	@Override
	public String getValue() {
		return value;
	}
}
