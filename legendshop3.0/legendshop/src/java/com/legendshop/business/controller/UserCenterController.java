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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legendshop.business.common.page.TilesPage;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;

/**
 * The user center controller.
 * 
 * @author George
 * 
 */

@Controller
@RequestMapping("/p")
public class UserCenterController extends BaseController {

	/** The log. */
	private final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@RequestMapping("/usercenter")
	public String toUserCenterPage(HttpServletRequest request, HttpServletResponse response) {
		try {
			// TODO
			logger.debug("toUserCenterPage calling...");
		} catch (Exception e) {
			logger.error("invoking toUserCenterPage", e);
		}
		return PathResolver.getPath(TilesPage.USER_CENTER_MAIN);
	}
}
