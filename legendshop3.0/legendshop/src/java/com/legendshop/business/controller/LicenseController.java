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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legendshop.business.common.page.BackPage;
import com.legendshop.central.license.LicenseEnum;
import com.legendshop.central.license.LicenseHelper;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;

/**
 * License控制器.
 */
@Controller
@RequestMapping("/admin/license")
public class LicenseController extends BaseController {
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(LicenseController.class);
	
	/**
	 * Upgrade.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping("/upgrade")
	public String upgrade(HttpServletRequest request, HttpServletResponse response) {
		return PathResolver.getPath(request, BackPage.UPGRADE_PAGE);
	}

	/**
	 * Post upgrade.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping("/postUpgrade")
	public String postUpgrade(HttpServletRequest request, HttpServletResponse response) {
		String liensekey = request.getParameter("liensekey");
		String license = LicenseHelper.updateLicense(request.getSession().getServletContext(), liensekey);
		if (LicenseEnum.isNormal(license)) {
			request.setAttribute("postUpgrade", true);
		}
		return PathResolver.getPath(request, BackPage.UPGRADE_PAGE);
	}

}
