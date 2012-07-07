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

import com.legendshop.business.common.page.FrontPage;
import com.legendshop.business.common.page.TilesPage;
import com.legendshop.business.service.IndexService;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.ConfigPropertiesEnum;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.exception.ApplicationException;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.exception.ErrorCodes;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.ShopStatusChecker;
import com.legendshop.model.UserMessages;
import com.legendshop.model.entity.ShopDetailView;
import com.legendshop.spi.constants.Constants;
import com.legendshop.util.AppUtils;
import com.legendshop.util.FileConfig;

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

	/** The shop status checker. */
	@Autowired
	private ShopStatusChecker shopStatusChecker;
	
	
	

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
		try {
			String shopName = (String) request.getAttribute(Constants.SHOP_NAME);
			ShopDetailView shopDetail = indexService.getShopDetailView(shopName, request, response); // 得到当前商城
			if (shopDetail == null) {
				String defaultShop = PropertiesUtil.getObject(ParameterEnum.DEFAULT_SHOP, String.class);
				if (AppUtils.isBlank(defaultShop)) {
					throw new ApplicationException("Need to install first", EntityCodes.SYSTEM);
				}
				// 如果有默认店，则先到默认店去，默认店要先配置好
				shopDetail = indexService.getShopDetailView(defaultShop, request, response);
			} else {
				shopName = shopDetail.getUserName();
				if (!shopStatusChecker.check(shopDetail, request)) {
					return PathResolver.getPath(request, FrontPage.FAIL);
				}
			}

			// TODO service call
			logger.debug("Call the service...");

		} catch (Exception e) {
			// redirect to the install page
			redirectToInstallPage(request);
		}

		// TODO 多线程记录访问历史

		return PathResolver.getPath(request, TilesPage.HOME);
	}

	/**
	 * @param request
	 */
	private String redirectToInstallPage(HttpServletRequest request) {
		String version = PropertiesUtil.getProperties(FileConfig.GlobalFile,
				ConfigPropertiesEnum.LEGENDSHOP_VERSION.name());
		UserMessages uem = new UserMessages();
		uem.setTitle("系统还没有安装成功");
		uem.setDesc("System will be available until install operation is finished!");
		uem.setCode(ErrorCodes.SYSTEM_UNINSTALLED);
		uem.addCallBackList("安装系统", "LegendShop " + version, "install");
		request.setAttribute(UserMessages.MESSAGE_KEY, uem);

		return "redirect:install/index.jsp";
	}

}
