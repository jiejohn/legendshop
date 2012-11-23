/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.legendshop.business.common.page.FrontPage;
import com.legendshop.core.UserManager;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.model.entity.ShopDetailView;
import com.legendshop.spi.constants.NewsPositionEnum;

/**
 * 默认模板的顶部数据收集器
 */
public class DefaultCommonPageServiceImpl extends AbstractCommonPageService {
	

	/**
	 * 默认模板的顶部数据
	 */
	@Override
	public String getTop(HttpServletRequest request, HttpServletResponse response) {
		String shopName = ThreadLocalContext.getCurrentShopName(request, response);
		ShopDetailView shopDetail = ThreadLocalContext.getShopDetailView(request,response,shopName);
		if (shopDetail == null) {
			return PathResolver.getPath(request,response,FrontPage.TOPALL);
		}
		//产品分类
		request.setAttribute("sortList", sortDao.getSort(shopName, true));

		// 分类新闻
		request.setAttribute("newsSortList", newsDao.getNews(shopName, NewsPositionEnum.NEWS_SORT, 8));
				
		//返回页面
		return PathResolver.getPath(request,response,FrontPage.TOP);
	}

	@Override
	public String getTopUserInfo(HttpServletRequest request, HttpServletResponse response) {
		String shopName = ThreadLocalContext.getCurrentShopName(request, response);
		String userName = UserManager.getUsername(request.getSession());
		
		// 顶部新闻
		request.setAttribute("newsTopList", newsDao.getNews(shopName, NewsPositionEnum.NEWS_TOP, 8));

		//是否是商家
		boolean shopExists = shopDetailDao.isShopExists(userName);
		//是否可以做为联盟商城
		request.setAttribute("canbeLeagueShop", shopDetailDao.isBeLeagueShop(shopExists, userName, shopName));
		
		//返回页面
		return PathResolver.getPath(request,response,FrontPage.TOP_USER_INFO);
	}


}
