/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legendshop.business.common.page.TilesPage;
import com.legendshop.business.form.SearchForm;
import com.legendshop.business.service.BusinessService;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;

// TODO: Auto-generated Javadoc
/**
 * 前台主要功能.
 */
@Controller
public class BusinessController extends BaseController {
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(BusinessController.class);

	/** The business service. */
	@Autowired
	private BusinessService businessService;

	
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
	@RequestMapping("/ipsearch")
	public String ipsearch(HttpServletRequest request, HttpServletResponse response,String ipAddress) {
		return businessService.getIpAddress(request, response,ipAddress);
	}
	
	/**
	 * Leaveword.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param ipAddress
	 *            the ip address
	 * @return the string
	 */
	@RequestMapping("/leaveword")
	public String leaveword(HttpServletRequest request, HttpServletResponse response,String ipAddress) {
		return PathResolver.getPath(request,response,TilesPage.LEAVEWORD);
	}
	
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
	@RequestMapping("/search")
	public String search(HttpServletRequest request, HttpServletResponse response,SearchForm searchForm) {
		return businessService.search(request, response,searchForm);
	}
	
	/**
	 * Searchall.
	 *
	 * @param request the request
	 * @param response the response
	 * @return the string
	 */
	@RequestMapping("/searchall")
	public String searchall(HttpServletRequest request, HttpServletResponse response) {
		String keyword = request.getParameter("keyword");
		String entityType = request.getParameter("entityType");
		int type = 0;
		try {
			type =Integer.parseInt(entityType);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return businessService.searchall(request, response,keyword,type);
	}

	/**
	 * League.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping("/league")
	public String league(HttpServletRequest request, HttpServletResponse response) {
		return  businessService.getLeague(request, response);
	}
	
	
	/**
	 * Friendlink.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping("/friendlink")
	public String friendlink(HttpServletRequest request, HttpServletResponse response) {
		return businessService.getFriendlink(request, response);
	}
	
	/**
	 * Visited history.
	 *
	 * @param request the request
	 * @param response the response
	 * @return the string
	 */
	@RequestMapping("/visitedProd")
	public String visitedProd(HttpServletRequest request, HttpServletResponse response) {
		return businessService.getVisitedProd(request, response);
	}
	
	@RequestMapping("/visitedShop")
	public String visitedShop(HttpServletRequest request, HttpServletResponse response) {
		return businessService.getVisitedShop(request, response);
	}

	/**
	 * After operation.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping("/afterOperation")
	public String afterOperation(HttpServletRequest request, HttpServletResponse response) {
		return PathResolver.getPath(request,response,TilesPage.AFTER_OPERATION);
	}
	
	
}
