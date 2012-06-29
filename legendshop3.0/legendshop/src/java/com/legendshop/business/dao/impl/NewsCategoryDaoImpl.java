/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao.impl;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.legendshop.business.dao.NewsCategoryDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.model.entity.NewsCategory;

/**
 * 新闻分类Dao.
 */

public class NewsCategoryDaoImpl extends BaseDaoImpl implements NewsCategoryDao {
     
     /** The log. */
     private static Logger log = LoggerFactory.getLogger(NewsCategoryDaoImpl.class);

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.NewsCategoryDao#deleteNewsCategoryById(java.lang.Long)
	 */
	@Override
	public void deleteNewsCategoryById(Long id) {
		deleteById(NewsCategory.class, id);
		
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.NewsCategoryDao#updateNewsCategory(com.legendshop.model.entity.NewsCategory)
	 */
	@Override
	public void updateNewsCategory(NewsCategory newsCategory) {
		update(newsCategory);
		
	}
	
 }

