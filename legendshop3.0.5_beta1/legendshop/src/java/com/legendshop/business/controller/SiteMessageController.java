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
 * The short message (such as leave message) in the site scope controller.
 * 
 * @author George Guo
 * 
 */
@Controller
public class SiteMessageController extends BaseController {
	/** The logger. */
	private final Logger logger = LoggerFactory.getLogger(SiteMessageController.class);

	@RequestMapping("/p/inbox")
	public String toInbox(HttpServletRequest request, HttpServletResponse response) {
		// TODO
		logger.debug("To inbox page...");

		return PathResolver.getPath(request,response,TilesPage.SITE_MESSAGE_INBOX);
	}

	@RequestMapping("/p/outbox")
	public String toOutbox(HttpServletRequest request, HttpServletResponse response) {
		// TODO
		logger.debug("To outbox page...");

		return PathResolver.getPath(request,response,TilesPage.SITE_MESSAGE_OUTBOX);
	}
}
