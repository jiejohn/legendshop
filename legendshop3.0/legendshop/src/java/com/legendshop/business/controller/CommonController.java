/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.ProductTypeEnum;
import com.legendshop.core.service.SortService;
import com.legendshop.model.entity.Sort;

/**
 * The Class GroupController.
 */
@Controller
@RequestMapping("/common")
public class CommonController extends BaseController {
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(CommonController.class);

	@Autowired
	private SortService sortService;

	@RequestMapping("/top")
	public String top(HttpServletRequest request, HttpServletResponse response) {
		log.debug("Top starting calling");
		Long sortId=ServletRequestUtils.getLongParameter(request, "sortId",-1);
		String shopName = getShopName(request, response);
		List<Sort> sortList = sortService.getSort(shopName, ProductTypeEnum.PRODUCT.value(), 1, null, false);
		request.setAttribute("sortList", sortList);
		request.setAttribute("currentSortId", sortId);
		return "/common/top";
		// return PathResolver.getPath(request, null);
	}

}
