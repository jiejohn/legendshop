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

import org.springframework.stereotype.Controller;

import com.legendshop.business.common.page.FrontPage;
import com.legendshop.business.service.CommonPageService;
import com.legendshop.business.service.locator.CommonPageServiceLocator;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;

/**
 * The Class CommonPageController.
 */
@Controller
public class CommonPageController  extends BaseController {
	
	/** The locator. */
	private CommonPageServiceLocator locator;
	
	/**
	 * Top.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	//@RequestMapping("/top")
	public String top(HttpServletRequest request, HttpServletResponse response) {
		String shopName = getShopName(request, response);
		CommonPageService commonPageService = locator.getCommonPageService(shopName);
		return commonPageService.getTop(request, response);
	}
	
	
	/**
	 * Topall.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	//@RequestMapping("/topall")
	public String topall(HttpServletRequest request, HttpServletResponse response) {
		return PathResolver.getPath(request, FrontPage.TOPALL);
	}
	
	//@RequestMapping("/copyAll")
	public String copyAll(HttpServletRequest request, HttpServletResponse response,String curPageNO,String newsCategory) {
		return null;
	}
	
	//@RequestMapping("/copy")
	public String copy(HttpServletRequest request, HttpServletResponse response,String curPageNO,String newsCategory) {
		String shopName = getShopName(request, response);
		CommonPageService commonPageService = locator.getCommonPageService(shopName);
		return commonPageService.getCopy(request, response);
	}

	/**
	 * Sets the locator.
	 * 
	 * @param locator
	 *            the new locator
	 */
	public void setLocator(CommonPageServiceLocator locator) {
		this.locator = locator;
	}
}
