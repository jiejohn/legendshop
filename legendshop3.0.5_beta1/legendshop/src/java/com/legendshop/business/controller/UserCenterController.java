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

import com.legendshop.business.common.page.TilesPage;
import com.legendshop.business.service.timer.SubService;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.Sub;
import com.legendshop.util.AppUtils;
import com.legendshop.util.DateUtil;

/**
 * The user center controller.
 * 
 * @author George
 * 
 */

@Controller
public class UserCenterController extends BaseController {

	/** The log. */
	private final Logger logger = LoggerFactory.getLogger(IndexController.class);

	private String DEAULT_CURRENT_PAGE_NO = "1";

	/** The order service. */
	@Autowired
	private SubService subService;

	@RequestMapping("/p/usercenter")
	public String toUserCenterPage(HttpServletRequest request, HttpServletResponse response) {
		try {

			logger.debug("toUserCenterPage calling...");

			String userName = UserManager.getUsername(request.getSession());

			// Query the order list during the latest month
			queryOrdersLatestMonth(userName, DEAULT_CURRENT_PAGE_NO, request);

			// TODO

		} catch (Exception e) {
			logger.error("invoking toUserCenterPage", e);
		}
		return PathResolver.getPath(request, response, TilesPage.USER_CENTER_MAIN);
	}

	@RequestMapping("/p/user")
	public String toPersonalInfo(HttpServletRequest request, HttpServletResponse response) {
		try {

			logger.debug("toPersonalInfo calling...");

			String userName = UserManager.getUsername(request.getSession());

			// Query the order list during the latest month

			// TODO

		} catch (Exception e) {
			logger.error("invoking toPersonalInfo", e);
		}
		return PathResolver.getPath(request, response, TilesPage.USER_INFO);
	}

	private void queryOrdersLatestMonth(String userName, String curPageNO, HttpServletRequest request) {

		// Create criteria query
		CriteriaQuery cq = new CriteriaQuery(Sub.class, curPageNO);
		cq.setPageSize(PropertiesUtil.getObject(ParameterEnum.FRONT_PAGE_SIZE, Integer.class));
		cq.eq("userName", userName);
		cq.gt("subDate", DateUtil.getTimeMonthsAgo(1));
		cq.addOrder("desc", "subDate");

		PageSupport ps = subService.getOrderList(cq);

		savePage(ps, request);
	}
}
