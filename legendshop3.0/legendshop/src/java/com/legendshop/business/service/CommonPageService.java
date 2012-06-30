/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The Interface CommonPageService.
 */
public interface CommonPageService {
	
	/**
	 * Gets the top. 顶部
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the top
	 */
	public String getTop(HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * Topall. 顶部
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	public String topall(HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * Gets the copy. 底部
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the copy
	 */
	public String getCopy(HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * Gets the copy all. 底部
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the copy all
	 */
	public String getCopyAll(HttpServletRequest request, HttpServletResponse response);
	
}
