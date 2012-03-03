/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.business.common.PageLetEnum;
import com.legendshop.business.service.HotsearchService;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.model.entity.Hotsearch;

/**
 * 热门搜索控制器.
 */
@Controller
@RequestMapping("/admin/hotsearch")
public class HotsearchAdminController extends BaseController {
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(HotsearchAdminController.class);
	
	/** The hotsearch service. */
	@Autowired
	private HotsearchService hotsearchService;

	/**
	 * Query.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param curPageNO
	 *            the cur page no
	 * @param hotsearch
	 *            the hotsearch
	 * @return the string
	 */
	@RequestMapping("/query")
	public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, Hotsearch hotsearch) {
		CriteriaQuery cq = new CriteriaQuery(Hotsearch.class, curPageNO, "javascript:pager");
		cq.setPageSize(PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE, Integer.class));
		cq = hasAllDataFunction(cq, request, StringUtils.trim(hotsearch.getUserName()));
		cq.eq("sort", hotsearch.getSort());
		cq.eq("title", hotsearch.getTitle());
		cq.addOrder("desc", "date");
		cq.add();
		PageSupport ps = hotsearchService.getDataByCriteriaQuery(cq);
		savePage(ps, request);
		request.setAttribute("bean", hotsearch);
		return PathResolver.getPath(request, PageLetEnum.HOT_LIST_PAGE);
	}

	/**
	 * Save.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param hotsearch
	 *            the hotsearch
	 * @return the string
	 */
	@RequestMapping(value = "/save")
	public String save(HttpServletRequest request, HttpServletResponse response, Hotsearch hotsearch) {
		String name = UserManager.getUsername(request);
		hotsearch.setUserId(UserManager.getUserId(request.getSession()));
		hotsearch.setUserName(name);
		hotsearch.setDate(new Date());
		hotsearchService.save(hotsearch, name, CommonServiceUtil.haveViewAllDataFunction(request));
		saveMessage(request, ResourceBundleHelper.getSucessfulString());
		return PathResolver.getPath(request, PageLetEnum.HOT_LIST_QUERY);
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
	Long id) {
		Hotsearch hotsearch = hotsearchService.getHotsearchById(id);
		checkPrivilege(request, UserManager.getUsername(request.getSession()), hotsearch.getUserName());
		log.info("{} delete Hotsearch Title {}, Msg {}", new Object[] { hotsearch.getUserName(), hotsearch.getTitle(),
				hotsearch.getMsg() });
		hotsearchService.delete(id);
		saveMessage(request, ResourceBundleHelper.getDeleteString());
		return PathResolver.getPath(request, PageLetEnum.HOT_LIST_QUERY);
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
	Long id) {
		Hotsearch hotsearch = hotsearchService.getHotsearchById(id);
		checkPrivilege(request, UserManager.getUsername(request.getSession()), hotsearch.getUserName());
		request.setAttribute("bean", hotsearch);
		return PathResolver.getPath(request, PageLetEnum.HOT_EDIT_PAGE);
	}

	/**
	 * Update.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param hotsearch
	 *            the hotsearch
	 * @return the string
	 */
	@RequestMapping(value = "/update")
	public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable
	Hotsearch hotsearch) {
		Hotsearch origin = hotsearchService.getHotsearchById(hotsearch.getId());
		checkPrivilege(request, UserManager.getUsername(request.getSession()), origin.getUserName());
		log.info("{} update Hotsearch Title{}", origin.getUserName(), origin.getTitle());
		hotsearch.setUserId(origin.getUserId());
		hotsearch.setUserName(origin.getUserName());
		hotsearchService.update(hotsearch);
		return PathResolver.getPath(request, PageLetEnum.HOT_LIST_QUERY);
	}
	
	/**
	 * Load.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping(value = "/load")
	public String load(HttpServletRequest request, HttpServletResponse response) {
		return  PathResolver.getPath(request, PageLetEnum.HOT_EDIT_PAGE);
	}

}
