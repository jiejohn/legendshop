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

import com.legendshop.core.constant.PageDefinition;
import com.legendshop.core.constant.PagePathCalculator;
import com.legendshop.util.AppUtils;

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
	
	private List<String> templates;
	

	private GroupBackPage(String value,String ... template) {
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
	
	/* (non-Javadoc)
	 * @see com.legendshop.core.constant.PageDefinition#getValue(javax.servlet.http.HttpServletRequest, java.lang.String)
	 */
	@Override
	public String getValue(HttpServletRequest request, String path,List<String> templates) {
		return PagePathCalculator.calculateBackendPath(request,path,templates);
	}


	@Override
	public String getValue() {
		return value;
	}
}
