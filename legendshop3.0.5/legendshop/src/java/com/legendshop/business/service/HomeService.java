/*
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * 官方网站：http://www.legendesign.net
 */
package com.legendshop.business.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author George Guo
 * 
 */
public interface HomeService {

	/**
	 * Gets the home page.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the home
	 */
	public String getHome(HttpServletRequest request, HttpServletResponse response);
}
