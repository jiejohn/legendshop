package com.legendshop.business.service;

import java.util.List;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.NewsCategory;

public interface NewsCategoryService {

	/**
	 * List.
	 * 
	 * @param userName
	 *            the user name
	 * @return the list
	 */
	public abstract List<NewsCategory> getNewsCategoryList(String userName);

	/**
	 * Load.
	 * 
	 * @param id
	 *            the id
	 * @return the news category
	 */
	public abstract NewsCategory getNewsCategoryById(Long id);

	/**
	 * Delete.
	 * 
	 * @param id
	 *            the id
	 */
	public abstract void delete(Long id);

	/**
	 * Save.
	 * 
	 * @param newsCategory
	 *            the news category
	 * @return the long
	 */
	public abstract Long save(NewsCategory newsCategory);

	/**
	 * Update.
	 * 
	 * @param newsCategory
	 *            the news category
	 */
	public abstract void update(NewsCategory newsCategory);

	/**
	 * Gets the data by criteria query.
	 * 
	 * @param cq
	 *            the cq
	 * @return the data by criteria query
	 */
	public abstract PageSupport getNewsCategoryList(CriteriaQuery cq);

}