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
import com.legendshop.business.dao.LogoDao;
import com.legendshop.business.dao.NewsDao;
import com.legendshop.business.dao.SortDao;
import com.legendshop.business.service.CommonPageService;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.spi.constants.Constants;
import com.legendshop.spi.constants.NewsPositionEnum;
import com.legendshop.spi.service.impl.AbstractService;
import com.legendshop.util.AppUtils;

/**
 * 公共页面的service基类
 * The Class AbstractCommonPageService.
 */
public abstract class AbstractCommonPageService extends AbstractService implements CommonPageService {
	
	/** The news dao. */
	protected NewsDao newsDao;
	
	/** The sort dao. */
	protected SortDao sortDao;
	
	/** The logo dao. */
	protected LogoDao logoDao;
	/**
	 * Gets the copy all.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the copy all
	 */
	@Override
	public String getCopy(HttpServletRequest request, HttpServletResponse response){
		// 采用公用帐号的信息
		String shopName = PropertiesUtil.getDefaultShopName();
		if (AppUtils.isBlank(shopName)) {
			shopName = Constants.COMMON_USER;
		}
		request.setAttribute("newsBottomList", newsDao.getNews(shopName, NewsPositionEnum.NEWS_BOTTOM, 8));
		return PathResolver.getPath(FrontPage.COPY);
	}


	/**
	 * Sets the news dao.
	 * 
	 * @param newsDao
	 *            the new news dao
	 */
	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}


	/**
	 * Sets the sort dao.
	 * 
	 * @param sortDao
	 *            the new sort dao
	 */
	public void setSortDao(SortDao sortDao) {
		this.sortDao = sortDao;
	}


	/**
	 * Sets the logo dao.
	 * 
	 * @param logoDao
	 *            the new logo dao
	 */
	public void setLogoDao(LogoDao logoDao) {
		this.logoDao = logoDao;
	}
}
