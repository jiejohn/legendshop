/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.business.common.SubForm;
import com.legendshop.business.common.page.FrontPage;
import com.legendshop.business.common.page.TilesPage;
import com.legendshop.business.service.UserDetailService;
import com.legendshop.business.service.timer.SubService;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.exception.ErrorCodes;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.model.UserMessages;
import com.legendshop.model.entity.Sub;
import com.legendshop.spi.constants.Constants;

/**
 * 购物车控制器。.
 */
@Controller
@RequestMapping("/sub")
public class SubController extends BaseController {
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(SubController.class);

	/** The sub service. */
	@Autowired
	private SubService subService;

	/** The user detail service. */
	@Autowired
	private UserDetailService userDetailService;

	/**
	 * Save.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param sub
	 *            the sub
	 * @return the string
	 */
	@RequestMapping(value = "/save")
	public String save(HttpServletRequest request, HttpServletResponse response, SubForm sub) {
		String userName = UserManager.getUsername(request);
		String result = checkLogin(userName);
		if(result != null){
			return result;
		}
		log.debug("{} save sub ", userName);
		Integer i = (Integer) request.getSession().getAttribute(Constants.TOKEN); // 取session中保存的token
		Integer i2 = Integer.parseInt(request.getParameter(Constants.TOKEN)) ; // 取用户提交过来的token进行对比
		if (i.equals(i2)) {// 这个“暗号”用过后约定用新的暗号, 在cash.do中加入
			request.getSession().setAttribute(Constants.TOKEN, CommonServiceUtil.generateRandom());
			List<Sub> subList = subService.saveSub(sub);
			request.setAttribute("member", sub);
			request.setAttribute("subList", subList);
			request.setAttribute("availableScore", userDetailService.getScore(userName));
		} else { 
			// 如果暗号不对的话（用户重复提交）提示“不能重复提交”
			UserMessages uem = new UserMessages();
			Locale locale = localeResolver.resolveLocale(request);
			uem.setTitle(ResourceBundleHelper.getString(locale, "webpage.timeout"));
			uem.setCode(ErrorCodes.TOKEN_UNVALIDATE);
			uem.addCallBackList(ResourceBundleHelper.getString(locale, "myorder"),
					ResourceBundleHelper.getString(locale, "Query.Order.Status"), "order.do");
			request.setAttribute(UserMessages.MESSAGE_KEY, uem);
			return PathResolver.getPath(FrontPage.ERROR_PAGE);
		}
		return PathResolver.getPath(TilesPage.PAGE_SUB);
	}

}
