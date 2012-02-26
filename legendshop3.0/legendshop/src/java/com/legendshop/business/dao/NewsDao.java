/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao;

import java.util.List;

import com.legendshop.business.common.NewsCategoryStatusEnum;
import com.legendshop.core.dao.BaseDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.News;

/**
 * The Interface NewsDao.
 */
public interface NewsDao extends BaseDao{

	/**
	 * Gets the news.
	 * 
	 * @param shopName
	 *            the shop name
	 * @param newsCategoryStatusEnum
	 *            the news category status enum
	 * @param num
	 *            the num
	 * @return the news
	 */
	public abstract List<News> getNews(final String shopName, final NewsCategoryStatusEnum newsCategoryStatusEnum,
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
	 * @param cq
	 *            the cq
	 * @return the news
	 */
	public abstract PageSupport getNews(CriteriaQuery cq);

}