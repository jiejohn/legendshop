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
 * The Class DefaultCommonPageServiceImpl.
 */
public class DefaultCommonPageServiceImpl extends AbstractCommonPageService {
	

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.CommonPageService#getTop(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String getTop(HttpServletRequest request, HttpServletResponse response) {
		String shopName = getCurrentShopName(request, response);
		String userName = UserManager.getUsername(request.getSession());
		ShopDetailView shopDetail = ThreadLocalContext.getShopDetailView(shopName, request, response);
		if (shopDetail == null) {
			return PathResolver.getPath(request, FrontPage.TOPALL);
		}

		// set Locale
		//setLocalByShopDetail(shopDetail, request, response);

		request.setAttribute("logo", logoDao.getLogo(shopName));
		request.setAttribute("sortList", sortDao.getSort(shopName, true));

		// 顶部新闻
		request.setAttribute("newsTopList", newsDao.getNews(shopName, NewsPositionEnum.NEWS_TOP, 8));

		// 分类新闻
		request.setAttribute("newsSortList", newsDao.getNews(shopName, NewsPositionEnum.NEWS_SORT, 8));

		boolean shopExists = shopDetailDao.isShopExists(userName);
		request.setAttribute("shopExists", shopExists);
		request.setAttribute("canbeLeagueShop", shopDetailDao.isBeLeagueShop(shopExists, userName, shopName));
		return PathResolver.getPath(request, FrontPage.TOP);
	}


}
