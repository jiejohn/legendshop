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
import org.springframework.web.bind.annotation.RequestMapping;

import com.legendshop.business.common.page.FrontPage;
import com.legendshop.business.service.IndexService;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.ConfigPropertiesEnum;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.exception.ErrorCodes;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.UserMessages;
import com.legendshop.util.FileConfig;

/**
 * The Class GroupController.
 */
@Controller
public class IndexController extends BaseController {
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(IndexController.class);
		
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
	@RequestMapping("/index")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		log.debug("Index starting calling");
		try {
			//test
//			EventHome.publishEvent(new UserRegEvent("testObject"));
//			EventHome.publishEvent(new SendMailEvent("mail content"));
			
			
			return indexService.getIndex(request, response);
		} catch (Exception e) {
			log.error("invoking index", e);
			if (!PropertiesUtil.isSystemInstalled()) {
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
			return PathResolver.getPath(request, FrontPage.FAIL);
		}
	}

}
