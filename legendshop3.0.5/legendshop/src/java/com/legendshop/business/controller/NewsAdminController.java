/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
import com.legendshop.business.common.page.FowardPage;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.model.entity.News;
import com.legendshop.spi.constants.Constants;
import com.legendshop.spi.service.NewsService;
import com.legendshop.util.AppUtils;
import com.legendshop.util.CodeFilter;
import com.legendshop.util.sql.ConfigCode;

/**
 * 新闻控制器.
 */
@Controller
@RequestMapping("/admin/news")
public class NewsAdminController extends BaseController {
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(NewsAdminController.class);
	
	/** The news service. */
	@Autowired
	private NewsService newsService;

	/**
	 * 采用hql.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param curPageNO
	 *            the cur page no
	 * @param news
	 *            the news
	 * @return the string
	 */
	@RequestMapping("/query")
	public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, News news) {
		String userName = UserManager.getUsername(request.getSession());
		Map<String, String> map = new HashMap<String, String>();
		HqlQuery hql = new HqlQuery(PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE, Integer.class), curPageNO);
		// 条件,注意条件顺序跟SQL对应
		if (!CommonServiceUtil.haveViewAllDataFunction(request)) {
			map.put("userName", userName);
			hql.addParams(userName);
		} else {
			// 管理员
			if (AppUtils.isNotBlank(news.getUserName())) {
				map.put("userName", news.getUserName());
				hql.addParams(news.getUserName());
			}
		}

		if (!AppUtils.isBlank(news.getNewsCategoryId())) {
			map.put("newsCategoryId", String.valueOf(news.getNewsCategoryId()));
			hql.addParams(news.getNewsCategoryId());
		}
		
		if (!AppUtils.isBlank(news.getSortId())) {
			map.put("sortId", String.valueOf(news.getSortId()));
			hql.addParams(news.getSortId());
		}

		if (!AppUtils.isBlank(news.getNewsTitle())) {
			map.put("newsTitle", news.getNewsTitle());
			hql.addParams("%" + news.getNewsTitle() + "%");
		}
		if (!AppUtils.isBlank(news.getStatus())) {
			map.put("status", String.valueOf(news.getStatus()));
			hql.addParams(news.getStatus());
		}
		
		if (!AppUtils.isBlank(news.getPosition())) {
			map.put("position", String.valueOf(news.getPosition()));
			hql.addParams(news.getPosition());
		}
		
		if (!CommonServiceUtil.isDataForExport(hql, request)) {// 非导出情况
			hql.setPageSize(PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE, Integer.class));
		}
		if (!CommonServiceUtil.isDataSortByExternal(hql, request, map)) {
			map.put(Constants.ORDER_INDICATOR, "order by n.newsDate desc");
		}

		String QueryNsortCount = ConfigCode.getInstance().getCode("biz.QueryNewsCount", map);
		String QueryNsort = ConfigCode.getInstance().getCode("biz.QueryNews", map);
		hql.setAllCountString(QueryNsortCount);
		hql.setQueryString(QueryNsort);

		PageSupport ps = newsService.getNewsList(hql);
		ps.savePage(request);
		request.setAttribute("bean", news);
		return PathResolver.getPath(BackPage.NEWS_LIST_PAGE);
	}

	/**
	 * Save.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param news
	 *            the news
	 * @return the string
	 */
	@RequestMapping(value = "/save")
	public String save(HttpServletRequest request, HttpServletResponse response, News news) {
		if (news.getNewsId() != null) {
			return update(request, response, news);
		}
		news.setNewsDate(new Date());
		news.setUserId(UserManager.getUserId(request.getSession()));
		news.setUserName(UserManager.getUsername(request.getSession()));
		// if(AppUtils.isBlank(news.getNewsBrief())){
		// setNewsBrief(news);
		// }
		newsService.save(news);
		saveMessage(request, ResourceBundleHelper.getSucessfulString());
		return PathResolver.getPath(FowardPage.NEWS_LIST_QUERY);
	}

	/**
	 * Sets the news brief.
	 * 
	 * @param news
	 *            the new news brief
	 */
	private void setNewsBrief(News news) {
		String newsContent = news.getNewsContent();
		if (newsContent != null && newsContent.length() > 0) {
			Integer len = newsContent.length();
			int maxLength = 100;
			boolean max = len > maxLength;
			if (max) {
				news.setNewsBrief(CodeFilter.unHtml(news.getNewsContent().substring(0, maxLength)) + "...");
			} else {
				news.setNewsBrief(CodeFilter.unHtml(newsContent));
			}

		}

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
		News news = newsService.getNewsById(id);
		String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), news.getUserName());
		if(result!=null){
			return result;
		}
		log.info("{},delete News Title{}", news.getUserName(), news.getNewsTitle());
		newsService.delete(id);
		saveMessage(request, ResourceBundleHelper.getDeleteString());
		return PathResolver.getPath(FowardPage.NEWS_LIST_QUERY);
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
		News news = newsService.getNewsById(id);
		String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), news.getUserName());
		if(result!=null){
			return result;
		}
		request.setAttribute("news", news);
		return PathResolver.getPath(BackPage.NEWS_EDIT_PAGE);
	}

	
	@RequestMapping(value = "/load")
	public String load(HttpServletRequest request, HttpServletResponse response) {
		return PathResolver.getPath(BackPage.NEWS_EDIT_PAGE);
	}
	
	/**
	 * Update.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param news
	 *            the news
	 * @return the string
	 */
	public String update(HttpServletRequest request, HttpServletResponse response, News news) {
		News origin = newsService.getNewsById(news.getNewsId());
		String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), origin.getUserName());
		if(result!=null){
			return result;
		}
		log.info("{} update News Title{}", origin.getUserName(), origin.getNewsTitle());
		news.setUserId(origin.getUserId());
		news.setUserName(origin.getUserName());
		// if(AppUtils.isBlank(news.getNewsBrief())){
		// setNewsBrief(news);
		// }
		newsService.update(news);
		saveMessage(request, ResourceBundleHelper.getSucessfulString());
		return PathResolver.getPath(FowardPage.NEWS_LIST_QUERY);
	}
}
