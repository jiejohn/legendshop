/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.model.entity.NewsCategory;

/**
 * The Interface NewsCategoryDao.
 */
public interface NewsCategoryDao extends BaseDao{

	/**
	 * Delete news category by id.
	 * 
	 * @param id
	 *            the id
	 */
	void deleteNewsCategoryById(Long id);

	/**
	 * Update news category.
	 * 
	 * @param newsCategory
	 *            the news category
	 */
	void updateNewsCategory(NewsCategory newsCategory);

}