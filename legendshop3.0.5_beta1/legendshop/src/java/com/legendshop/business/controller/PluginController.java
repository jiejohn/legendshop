/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.controller;

import java.io.Serializable;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.Ehcache;
import net.spy.memcached.MemcachedClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legendshop.business.common.page.BackPage;
import com.legendshop.business.common.page.FowardPage;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.cache.LegendCache;
import com.legendshop.core.cache.MemCachedManager;
import com.legendshop.core.cache.MemcachedCache;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.model.KeyValueEntity;
import com.legendshop.plugins.PluginManager;
import com.legendshop.util.AppUtils;

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
