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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.business.common.page.BackPage;
import com.legendshop.business.service.BusinessService;
import com.legendshop.business.service.IndexService;
import com.legendshop.central.license.LSResponse;
import com.legendshop.central.license.LicenseEnum;
import com.legendshop.core.AttributeKeys;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.model.UserInfo;
import com.legendshop.model.entity.ShopDetailView;

/**
 * 后台首页功能.
 */
@Controller
public class IndexAdminController extends BaseController {
	
	public  String ADMIN_MENU = "/frame/menu";
	
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(IndexAdminController.class);
	
	/** The index service. */
	@Autowired
	private IndexService indexService;

	/** The business service. */
	@Autowired
	private BusinessService businessService;
	
	/**
	 * 后台首页，加载用户相关资料并显示.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping("/admin/dashboard")
	public String load(HttpServletRequest request, HttpServletResponse response) {
		log.debug("adminIndex starting");
		String userName = UserManager.getUsername(request.getSession());
		ShopDetailView shopDetail = businessService.getSimpleInfoShopDetail(userName);
		UserInfo userInfo = indexService.getAdminIndex(userName, shopDetail);
		request.setAttribute("userInfo", userInfo);

		LSResponse lsResponse = (LSResponse) request.getSession().getServletContext().getAttribute(
				AttributeKeys.LEGENSHOP_LICENSE);
		if (lsResponse != null) {
			String license = lsResponse.getLicense();
			if (LicenseEnum.instance(license)) {
				if (LicenseEnum.needUpgrade(license) && CommonServiceUtil.haveViewAllDataFunction(request)) {
					request.setAttribute("needUpgrade", true);
				}
			}
		}
		return PathResolver.getPath(request, BackPage.DASH_BOARD);
	}
	
	@RequestMapping("/admin/index")
	public String home(HttpServletRequest request, HttpServletResponse response) {
		return PathResolver.getPath(request, BackPage.ADMIN_HOME);
	}
	
	@RequestMapping("/admin/menu/{id}")
	public String menu(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
		String path = ADMIN_MENU;
		if(id != null && id != 0){
			path = ADMIN_MENU + id;
		}
		return PathResolver.getPath(request,path,BackPage.VARIABLE);
	}
	
	
	@RequestMapping("/admin/top")
	public String top(HttpServletRequest request, HttpServletResponse response) {
		return PathResolver.getPath(request, BackPage.ADMIN_TOP);
	}
	
}
