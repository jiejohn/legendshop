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

import com.legendshop.business.common.page.BackPage;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.plugins.PluginManager;

/**
 * 系统缓存控制器.
 */
@Controller
@RequestMapping("/system/plugin")
public class PluginController extends BaseController {
	
	@Autowired
	private PluginManager pluginManager;

	/**
	 * Query.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param curPageNO
	 *            the cur page no
	 * @param systemParameter
	 *            the system parameter
	 * @return the string
	 */
	@RequestMapping("/query")
	public String query(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("pluginList", pluginManager.getPlugins());
		return PathResolver.getPath(request,response,BackPage.PLUGIN_LIST_PAGE);
	}
	


	

}
