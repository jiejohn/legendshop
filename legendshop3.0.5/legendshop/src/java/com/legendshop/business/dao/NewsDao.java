/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao;

import java.util.List;
import java.util.Locale;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.News;
import com.legendshop.spi.constants.NewsPositionEnum;

/**
 * The Interface NewsDao.
 */
public interface NewsDao extends BaseDao{

	/**
	 * Gets the news.
	 * 
	 * @param shopName
	 *            the shop name
	 * @param newsPositionEnum
	 *            the news category status enum
	 * @param num
	 *            the num
	 * @return the news
	 */
	public abstract List<News> getNews(final String shopName, final NewsPositionEnum newsPositionEnum,
			final Integer num);

	/**
	 * Gets the news by id.
	 * 
	 * @param newsId
	 *            the news id
	 * @return the news by id
	 */
	public abstract News getNewsById(Long newsId);

	/**
	 * Gets the news.
	 * 
	 * @param locale
	 *            the locale
	 * @param curPageNO
	 *            the cur page no
	 * @param userName
	 *            the user name
	 * @param newsCategoryId
	 *            the news category id
	 * @return the news
	 */
	public abstract PageSupport getNews(Locale locale, String curPageNO,String userName,Long newsCategoryId);

	/**
	 * Update news.
	 * 
	 * @param news
	 *            the news
	 */
	public abstract void updateNews(News news);

	/**
	 * Delete news by id.
	 * 
	 * @param id
	 *            the id
	 */
	public abstract void deleteNewsById(Long id);

	/**
	 * Gets the all news.
	 * 
	 * @param userName
	 *            the user name
	 * @return the all news
	 */
	public abstract Long getAllNews(String userName);

}