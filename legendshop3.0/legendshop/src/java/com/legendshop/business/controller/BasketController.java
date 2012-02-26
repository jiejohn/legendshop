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

import com.legendshop.business.common.Constants;
import com.legendshop.business.common.PageLetEnum;
import com.legendshop.business.service.BasketService;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.Basket;

/**
 * 购物车控制器。.
 */
@Controller
@RequestMapping("/basket")
public class BasketController extends BaseController {
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(BasketController.class);
	
	/** The basket service. */
	@Autowired
	private BasketService basketService;

	/**
	 * Query.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param curPageNO
	 *            the cur page no
	 * @param basket
	 *            the basket
	 * @return the string
	 */
	@RequestMapping("/query")
	public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO,
			Basket basket) {
		String userName = UserManager.getUsername(request);
		String prodId = request.getParameter("prodId");
		String addtoCart = request.getParameter("addtoCart");
		String count = request.getParameter("count");
		if (count == null) {
			count = "1";
		}
		request.getSession().setAttribute(Constants.BASKET_HW_COUNT, count);
		String prodattr = request.getParameter("prodattr");
		request.getSession().setAttribute(Constants.BASKET_HW_ATTR, prodattr);
		if (userName == null) {
			String destView = "/views/";// 默认是重新回到产品介绍页面
			if ("added".equals(addtoCart)) {// 如果是采用购物车订购的话直接进入付款页面
				destView = "/basket/";
			}
			request.setAttribute(Constants.RETURN_URL, PropertiesUtil.getDomainName() + destView + prodId + Constants.WEB_SUFFIX);
			return PathResolver.getPath(request, PageLetEnum.NO_LOGIN);
		}
		String shopName = getShopName(request, response);
		basketService.saveToCart( Long.valueOf(prodId), addtoCart, shopName, prodattr, userName,  Integer.valueOf(count));
		return PathResolver.getPath(request, PageLetEnum.PAGE_CASH);
	}

}
