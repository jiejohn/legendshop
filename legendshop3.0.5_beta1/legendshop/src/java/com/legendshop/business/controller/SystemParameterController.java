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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legendshop.business.common.page.BackPage;
import com.legendshop.business.common.page.FowardPage;
import com.legendshop.business.service.SystemParameterService;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.helper.PropertiesUpdater;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.model.entity.SystemParameter;
import com.legendshop.util.ContextServiceLocator;

/**
 * 系统参数控制器.
 */
@Controller
@RequestMapping("/system/systemParameter")
public class SystemParameterController extends BaseController {

	/** The system parameter service. */
	@Autowired
	private SystemParameterService systemParameterService;

	/**
	 * Gets the properties updater.
	 * 
	 * @return the properties updater
	 */
	private PropertiesUpdater getPropertiesUpdater() {
		return (PropertiesUpdater) ContextServiceLocator.getInstance().getBean("propertiesUpdater");
	}

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
	public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO,
			SystemParameter systemParameter) {
		CriteriaQuery cq = new CriteriaQuery(SystemParameter.class, curPageNO, "javascript:pager");
		// cq.setPageSize((Integer)PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE.name()));
		cq.setPageSize(30);
		cq.eq("name", systemParameter.getName());
		cq.eq("changeOnline", "Y");
		cq.addOrder("asc", "displayOrder");
		
		PageSupport ps = systemParameterService.getSystemParameterList(cq);
		savePage(ps, request);
		request.setAttribute("bean", systemParameter);
		return PathResolver.getPath(request,response,BackPage.PARAM_LIST_PAGE);
	}

	/**
	 * Save.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param systemParameter
	 *            the system parameter
	 * @return the string
	 */
	@RequestMapping(value = "/save")
	public String save(HttpServletRequest request, HttpServletResponse response, SystemParameter systemParameter) {
		SystemParameter parameter = systemParameterService.getSystemParameter(systemParameter.getName());
		if (parameter != null) {
			parameter.setValue(systemParameter.getValue());
			PropertiesUtil.setParameter(parameter, getPropertiesUpdater());
			systemParameterService.update(parameter);
			saveMessage(request, ResourceBundleHelper.getSucessfulString());
		} else {
			saveMessage(request, ResourceBundleHelper.getErrorString());
		}
		return PathResolver.getPath(request,response,FowardPage.PARAM_LIST_QUERY);
	}

	/**
	 * Delete.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param id
	 *            the id
	 * @return the string
	 */
	@RequestMapping(value = "/delete/{id}")
	public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable
	String id) {

		// systemParameterService.delete(id);
		saveMessage(request, ResourceBundleHelper.getErrorString());
		return PathResolver.getPath(request,response,FowardPage.PARAM_LIST_QUERY);
	}

	/**
	 * Load.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param id
	 *            the id
	 * @return the string
	 */
	@RequestMapping(value = "/load/{id}")
	public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable
	String id) {
		SystemParameter systemParameter = systemParameterService.getSystemParameter(id);
		request.setAttribute("bean", systemParameter);
		return PathResolver.getPath(request,response,BackPage.PARAM_EDIT_PAGE);
	}

	/**
	 * Update.
	 * 
	 * @param systemParameter
	 *            the system parameter
	 * @return the string
	 */
	@RequestMapping(value = "/update")
	public String update(HttpServletRequest request, HttpServletResponse response,@PathVariable SystemParameter systemParameter) {
		systemParameterService.update(systemParameter);
		return PathResolver.getPath(request,response,FowardPage.PARAM_LIST_QUERY);
	}

}
