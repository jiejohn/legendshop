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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legendshop.business.common.DynamicPropertiesHelper;
import com.legendshop.business.common.page.BackPage;
import com.legendshop.business.common.page.FowardPage;
import com.legendshop.business.service.AdminService;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.model.entity.Product;
import com.legendshop.model.entity.ProductDetail;
import com.legendshop.util.AppUtils;

/**
 * 产品动态属性，动态参数控制器.
 */
@Controller
public class XmlController extends BaseController {
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(XmlController.class);

	/** The admin service. */
	@Autowired
	private AdminService adminService;

	/**
	 * Query attribute.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param prodId
	 *            the prod id
	 * @return the string
	 */
	@RequestMapping("/dynamic/attribute/{prodId}")
	public String queryAttribute(HttpServletRequest request, HttpServletResponse response, @PathVariable
	Long prodId) {
		ProductDetail prod = (ProductDetail) request.getAttribute("prod");
		String attribute = null;
		if (prod != null) {
			attribute = prod.getAttribute();
		} else {
			attribute = adminService.getAttributeprodAttribute(prodId);
		}

		// if (AppUtils.isNotBlank(attribute)) {
		// List<JSONObject> modelList = JSONArray.fromObject(attribute);
		// DynamicPropertiesHelper helper = new DynamicPropertiesHelper();
		// request.setAttribute("dynamicProperties", helper.gerenateHTML(modelList));
		// }

		if (AppUtils.isNotBlank(attribute)) {
			List<JSONObject> modelList = JSONArray.fromObject(attribute);

			request.setAttribute("list", modelList);

		}
		return PathResolver.getPath(request, BackPage.SHOW_DYNAMIC_ATTRIBUTE);
	}

	/**
	 * Query parameter.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param prodId
	 *            the prod id
	 * @return the string
	 */
	@RequestMapping("/dynamic/parameter/{prodId}")
	public String queryParameter(HttpServletRequest request, HttpServletResponse response, @PathVariable
	Long prodId) {
		ProductDetail prod = (ProductDetail) request.getAttribute("prod");
		String parameter = null;
		if (prod != null) {
			parameter = prod.getParameter();
		} else {
			parameter = adminService.getProdParameter(prodId);
		}

		if (AppUtils.isNotBlank(parameter)) {
			List<JSONObject> modelList = JSONArray.fromObject(parameter);
			DynamicPropertiesHelper helper = new DynamicPropertiesHelper();
			request.setAttribute("dynamicProperties", "<table class='goodsAttributeTable'>"
					+ helper.gerenateHTML(modelList) + "</table>");
		}
		return PathResolver.getPath(request, BackPage.SHOW_DYNAMIC);
	}

	/**
	 * Save.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping(value = "/dynamic/save")
	public String save(HttpServletRequest request, HttpServletResponse response) {
		return PathResolver.getPath(request, FowardPage.DYNAMIC_QUERY);
		
	}

	/**
	 * Load attributeprod attribute.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param prodId
	 *            the prod id
	 * @return the string
	 */
	@RequestMapping(value = "/admin/dynamic/loadAttribute/{prodId}")
	public String loadAttributeprodAttribute(HttpServletRequest request, HttpServletResponse response, @PathVariable
	Long prodId) {
		String userName = UserManager.getUsername(request.getSession());
		Product product = adminService.getProd(prodId, userName);
		if (AppUtils.isNotBlank(product)) {
			request.setAttribute("prod", product);
			if (AppUtils.isNotBlank(product.getAttribute())) {
				JSONArray jsonArray = JSONArray.fromObject(product.getAttribute());
				request.setAttribute("imgFileJSON", jsonArray);
			}
		}
		request.setAttribute("DYNAMIC_TYPE", 1);
		return PathResolver.getPath(request, BackPage.DYNAMIC_ATTRIBUTE);
	}

	/**
	 * Load parameter.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param prodId
	 *            the prod id
	 * @return the string
	 */
	@RequestMapping(value = "/admin/dynamic/loadParameter/{prodId}")
	public String loadParameter(HttpServletRequest request, HttpServletResponse response, @PathVariable
	Long prodId) {
		String userName = UserManager.getUsername(request.getSession());
		Product product = adminService.getProd(prodId, userName);
		if (AppUtils.isNotBlank(product)) {
			request.setAttribute("prod", product);
			if (AppUtils.isNotBlank(product.getAttribute())) {
				JSONArray jsonArray = JSONArray.fromObject(product.getParameter());
				request.setAttribute("imgFileJSON", jsonArray);
			}
		}
		request.setAttribute("DYNAMIC_TYPE", 2);
		return PathResolver.getPath(request, BackPage.DYNAMIC_ATTRIBUTE);
	}

}
