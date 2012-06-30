/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.legendshop.business.common.page.FrontPage;
import com.legendshop.core.constant.PathResolver;

/**
 * The Class AbstractCommonPageService.
 */
public abstract class AbstractCommonPageService {
	
	/**
	 * Gets the copy all.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the copy all
	 */
	public String getCopyAll(HttpServletRequest request, HttpServletResponse response){
		return PathResolver.getPath(request, FrontPage.TOPALL);
	}
}
