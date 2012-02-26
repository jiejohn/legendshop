package com.legendshop.business.service;

import java.util.List;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.News;

public interface NewsService {

	/**
	 * List.
	 * 
	 * @param userName
	 *            the user name
	 * @return the list
	 */
	public abstract List<News> getNewsList(String userName);

	/**
	 * 查找新闻.
	 * 
	 * @param id
	 *            the id
	 * @return the news
	 */
	public abstract News getNewsById(Long id);

	/**
	 * 根据用户名和ID查找新闻.
	 * 
	 * @param id
	 *            the id
	 * @param userName
	 *            the user name
	 * @return the news
	 */
	public abstract News getNewsByIdAndName(Long id, String userName);

	/**
	 * 删除新闻.
	 * 
	 * @param id
	 *            the id
	 */
	public abstract void delete(Long id);

	/**
	 * 保存新闻.
	 * 
	 * @param news
	 *            the news
	 * @return the long
	 */
	public abstract Long save(News news);

	/**
	 * Update.
	 * 
	 * @param news
	 *            the news
	 */
	public abstract void update(News news);

	/**
	 * Gets the data by criteria query.
	 * 
	 * @param cq
	 *            the cq
	 * @return the data by criteria query
	 */
	public abstract PageSupport getNewsList(CriteriaQuery cq);

	/**
	 * Gets the data by criteria query.
	 * 
	 * @param hql
	 *            the hql
	 * @return the data by criteria query
	 */
	public abstract PageSupport getNewsList(HqlQuery hql);

}