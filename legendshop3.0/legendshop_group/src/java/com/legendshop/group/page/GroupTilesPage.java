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
import com.legendshop.spi.constants.TemplateEnum;
import com.legendshop.util.AppUtils;

/**
 * The Enum TilesPage.
 */
public enum GroupTilesPage implements PageDefinition{
	/** The VARIABLE. 可变路径 ,如果不设置Templates默认支持所有的template*/
	VARIABLE(""),
	
	/** The Index. */
	GINDEX("gindex.",TemplateEnum.RED);
	
	
	/** The value. */
	private  String value;
	
	private List<String> templates;

	/* (non-Javadoc)
	 * @see com.legendshop.core.constant.PageDefinition#getValue(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String getValue(HttpServletRequest request) { 
		return getValue(request,value,templates);
	}
	
	
	@Override
	public String getValue(HttpServletRequest request, String path,List<String> templates) {
		return PagePathCalculator.calculateTilesPath(request,path,templates);
	}

	
	/**
	 * Instantiates a new tiles page.
	 * 
	 * @param value
	 *            the value
	 */
	private GroupTilesPage(String value,String ... template) {
		this.value = value;
		if(AppUtils.isNotBlank(template)){
			this.templates = new ArrayList<String>();
			for (String temp : template) {
				templates.add(temp);
			}
		}
		
	}
	

	@Override
	public String getValue() {
		return value;
	}
}
