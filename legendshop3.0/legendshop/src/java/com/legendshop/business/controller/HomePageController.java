/*
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * 官方网站：http://www.legendesign.net
 */
package com.legendshop.business.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legendshop.business.service.IndexService;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.exception.BusinessException;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.helper.PropertiesUtil;

/**
 * To define the C2C index page.
 * 
 * @author George Guo
 * 
 */
@Controller
public class HomePageController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(HomePageController.class);

	@Autowired
	private IndexService indexService;

	/**
	 * 前台首页.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping("/home")
	public String home(HttpServletRequest request, HttpServletResponse response) {

		logger.debug("Index starting calling");
		try {
			return indexService.getHome(request, response);
		} catch (Exception e) {
			logger.error("invoking index", e);
			if (!PropertiesUtil.isSystemInstalled()) {
				// redirect to the install page
				redirectToInstallPage(request);
			}
			throw new BusinessException("/index", EntityCodes.SYSTEM);
		}
	}

}
