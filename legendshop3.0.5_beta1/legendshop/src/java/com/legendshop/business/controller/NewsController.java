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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legendshop.business.common.page.FrontPage;
import com.legendshop.business.common.page.TilesPage;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.model.entity.News;
import com.legendshop.spi.constants.Constants;
import com.legendshop.spi.constants.NewsPositionEnum;
import com.legendshop.spi.service.NewsService;
import com.legendshop.util.AppUtils;

/**
 * 产品分类控制器。.
 */
@Controller
public class NewsController extends BaseController{
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(NewsController.class);
	
	@Autowired
	private NewsService newsService;

	/**
	 * Topnews.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping("/topnews")
	public String topnews(HttpServletRequest request, HttpServletResponse response) {
		String name = ThreadLocalContext.getCurrentShopName(request,response);

		String topsortnews = request.getParameter("topsortnews");
		if ((topsortnews != null)) {
			request.setAttribute(
					"newList",
					newsService.getNews(name, NewsPositionEnum.NEWS_NEWS,
							PropertiesUtil.getObject(SysParameterEnum.FRONT_PAGE_SIZE, Integer.class)));
			return PathResolver.getPath(request,response,FrontPage.TOPSORTNEWS);
		} else {
			request.setAttribute("newList", newsService.getNews(name, NewsPositionEnum.NEWS_NEWS, 6));
			return PathResolver.getPath(request,response,FrontPage.TOPNEWS);
		}
	}
	
	
	/**
	 * News.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param newsId
	 *            the news id
	 * @return the string
	 */
	@RequestMapping("/news/{newsId}")
	public String news(HttpServletRequest request, HttpServletResponse response,@PathVariable Long newsId) {
		if (newsId != null) {
			News news = newsService.getNewsById(newsId);
			if (news != null) {
				ThreadLocalContext.setCurrentShopName(request, response, news.getUserName());
				newsService.getAndSetAdvertisement(request,response,news.getUserName());
				request.setAttribute("news", news);
			}

		}
		newsService.getAndSetOneAdvertisement(request,response,ThreadLocalContext.getCurrentShopName(request,response), Constants.USER_REG_ADV_740);
		return PathResolver.getPath(request,response,TilesPage.NEWS);
	}
	
	/**
	 * All news.
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
	@RequestMapping("/allNews")
	public String allNews(HttpServletRequest request, HttpServletResponse response,String curPageNO,Long newsCategoryId) {
		if(AppUtils.isBlank(curPageNO)){
			curPageNO = "1";
		}
		String shopName = ThreadLocalContext.getCurrentShopName(request,response);

		PageSupport ps = newsService.getNews(localeResolver.resolveLocale(request), curPageNO,shopName,newsCategoryId);
		ps.savePage(request);
		request.setAttribute("newsCategoryId", newsCategoryId);
		newsService.getAndSetOneAdvertisement(request,response,shopName, Constants.USER_REG_ADV_740);
		return PathResolver.getPath(request,response,TilesPage.ALL_NEWS);
	}

}
