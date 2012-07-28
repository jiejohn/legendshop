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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legendshop.business.common.page.FrontPage;
import com.legendshop.business.service.CommonPageService;
import com.legendshop.business.service.locator.CommonPageServiceLocator;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;

/**
 * The Class CommonPageController.
 */
@Controller
public class CommonPageController extends BaseController {

	/** The locator. */
	@Autowired
	private CommonPageServiceLocator commonPageServiceLocator;

	/**
	 * Top.页面顶部
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping("/top")
	public String top(HttpServletRequest request, HttpServletResponse response) {
		String shopName = getShopName(request, response);
		CommonPageService commonPageService = commonPageServiceLocator.getCommonPageService(shopName, FrontPage.TOP);
		return commonPageService.getTop(request, response);
	}

	@RequestMapping("/home/top")
	public String homeTop(HttpServletRequest request, HttpServletResponse response) {
		return PathResolver.getPath(FrontPage.HOME_TOP);
	}

	@RequestMapping("/bottom")
	public String bottom(HttpServletRequest request, HttpServletResponse response) {
		return PathResolver.getPath(FrontPage.BOTTOM);
	}

	/**
	 * Topall. 页面顶部
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping("/topall")
	public String topall(HttpServletRequest request, HttpServletResponse response) {
		return PathResolver.getPath(FrontPage.TOPALL);
	}

	/**
	 * 页面底部
	 * 
	 * @param request
	 * @param response
	 * @param curPageNO
	 * @param newsCategory
	 * @return
	 */
	@RequestMapping("/copyAll")
	public String copyAll(HttpServletRequest request, HttpServletResponse response, String curPageNO,
			String newsCategory) {
		String shopName = getShopName(request, response);
		CommonPageService commonPageService = commonPageServiceLocator.getCommonPageService(shopName,
				FrontPage.COPY_ALL);
		return commonPageService.getCopyAll(request, response);
	}

}
