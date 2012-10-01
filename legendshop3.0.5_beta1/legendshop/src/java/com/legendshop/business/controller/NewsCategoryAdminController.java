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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legendshop.business.common.page.BackPage;
import com.legendshop.business.common.page.FowardPage;
import com.legendshop.business.service.NewsCategoryService;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.model.entity.NewsCategory;
import com.legendshop.util.AppUtils;

/**
 * 新闻分类控制器.
 */
@Controller
@RequestMapping("/admin/newsCategory")
public class NewsCategoryAdminController extends BaseController {
	
	/** The LIS t_ page. */
	public static String LIST_PAGE = "/newsCategory/newsCategoryList";
	
	/** The EDI t_ page. */
	public static String EDIT_PAGE = "/newsCategory/newsCategory";
	
	/** The LIS t_ query. */
	public static String LIST_QUERY = "/admin/newsCategory/query";
	

	/** The news category service. */
	@Autowired
	private NewsCategoryService newsCategoryService;

	/**
	 * Query.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param curPageNO
	 *            the cur page no
	 * @param newsCategory
	 *            the news category
	 * @return the string
	 */
	@RequestMapping("/query")
	public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO,
			NewsCategory newsCategory) {
		CriteriaQuery cq = new CriteriaQuery(NewsCategory.class, curPageNO);
		cq.setPageSize(PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE, Integer.class));
		cq = hasAllDataFunction(cq, request, StringUtils.trim(newsCategory.getUserName()));
		if (!AppUtils.isBlank(newsCategory.getNewsCategoryName())) {
			cq.like("newsCategoryName", "%" + newsCategory.getNewsCategoryName() + "%");

		}
		cq.eq("status", newsCategory.getStatus());
		
		PageSupport ps = newsCategoryService.getNewsCategoryList(cq);
		ps.savePage(request);
		request.setAttribute("bean", newsCategory);
		return PathResolver.getPath(request,response,BackPage.NEWSCAT_LIST_PAGE);
	}

	/**
	 * Save.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param newsCategory
	 *            the news category
	 * @return the string
	 */
	@RequestMapping(value = "/save")
	public String save(HttpServletRequest request, HttpServletResponse response, NewsCategory newsCategory) {
		newsCategory.setNewsCategoryDate(new Date());
		newsCategory.setUserId(UserManager.getUserId(request.getSession()));
		newsCategory.setUserName(UserManager.getUsername(request.getSession()));
		newsCategoryService.save(newsCategory);
		saveMessage(request, ResourceBundleHelper.getSucessfulString());
		return PathResolver.getPath(request,response,FowardPage.NEWSCAT_LIST_QUERY);
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
		newsCategoryService.delete(id);
		saveMessage(request, ResourceBundleHelper.getDeleteString());
		return PathResolver.getPath(request,response,FowardPage.NEWSCAT_LIST_QUERY);
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
		NewsCategory newsCategory = newsCategoryService.getNewsCategoryById(id);
		request.setAttribute("bean", newsCategory);
		return PathResolver.getPath(request,response,BackPage.NEWSCAT_EDIT_PAGE);
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
		return PathResolver.getPath(request,response,BackPage.NEWSCAT_EDIT_PAGE);
	}

	/**
	 * Update.
	 * 
	 * @param request
	 *            the request
	 * @param newsCategory
	 *            the news category
	 * @return the string
	 */
	@RequestMapping(value = "/update")
	public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable NewsCategory newsCategory) {
		newsCategoryService.update(newsCategory);
		return PathResolver.getPath(request,response,FowardPage.NEWSCAT_LIST_QUERY);
	}

}
