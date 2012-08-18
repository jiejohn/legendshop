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
 * The user's favorite page controller.
 * 
 * @author George Guo
 * 
 */
@Controller
public class MyFavouriteController extends BaseController {
	/** The logger. */
	private final Logger logger = LoggerFactory.getLogger(MyFavouriteController.class);

	@RequestMapping("/p/favourite")
	public String myFavourite(HttpServletRequest request, HttpServletResponse response) {
		// TODO
		logger.debug("To my favourite page11...");

		return PathResolver.getPath(TilesPage.MY_FAVOURITE_MAIN);
	}
}
