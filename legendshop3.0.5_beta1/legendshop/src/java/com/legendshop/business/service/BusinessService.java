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

import com.legendshop.business.form.SearchForm;
import com.legendshop.spi.service.BaseService;

public interface BusinessService extends BaseService{

	/**
	 * Search.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param searchForm
	 *            the search form
	 * @return the string
	 */
	public abstract String search(HttpServletRequest request, HttpServletResponse response, SearchForm searchForm);

	/**
	 * Searchall.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param keyword
	 *            the keyword
	 * @param entityType
	 *            the entity type
	 * @return the string
	 */
	public abstract String searchall(HttpServletRequest request, HttpServletResponse response, String keyword,
			Integer entityType);



	/**
	 * Friendlink.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	public abstract String getFriendlink(HttpServletRequest request, HttpServletResponse response);

	/**
	 * Ipsearch.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param ipAddress
	 *            the ip address
	 * @return the string
	 */
	public abstract String getIpAddress(HttpServletRequest request, HttpServletResponse response, String ipAddress);

	/**
	 * League.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	public abstract String getLeague(HttpServletRequest request, HttpServletResponse response);


	/**
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	public abstract String getNewsforCommon(HttpServletRequest request, HttpServletResponse response);



}