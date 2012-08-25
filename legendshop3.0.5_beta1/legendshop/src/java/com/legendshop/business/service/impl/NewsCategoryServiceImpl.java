/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import java.util.List;

import com.legendshop.business.dao.NewsCategoryDao;
import com.legendshop.business.dao.impl.NewsCategoryDaoImpl;
import com.legendshop.business.service.NewsCategoryService;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.NewsCategory;
import com.legendshop.util.AppUtils;

/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public class NewsCategoryServiceImpl implements NewsCategoryService  {
    
    /** The base dao. */
    private NewsCategoryDao newsCategoryDao;

    /**
	 * Sets the base dao.
	 * 
	 * @param newsCategoryDao
	 *            the new base dao
	 */
    public void setNewsCategoryDao(NewsCategoryDaoImpl newsCategoryDao) {
        this.newsCategoryDao = newsCategoryDao;
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.NewsCategoryService#list(java.lang.String)
	 */
    @Override
	public List<NewsCategory> getNewsCategoryList(String userName) {
        return newsCategoryDao.findByHQL("from NewsCategory where userName = ?",  userName);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.NewsCategoryService#load(java.lang.Long)
	 */
    @Override
	public NewsCategory getNewsCategoryById(Long id) {
        return newsCategoryDao.get(NewsCategory.class, id);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.NewsCategoryService#delete(java.lang.Long)
	 */
    @Override
	public void delete(Long id) {
        newsCategoryDao.deleteNewsCategoryById(id);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.NewsCategoryService#save(com.legendshop.model.entity.NewsCategory)
	 */
    @Override
	public Long save(NewsCategory newsCategory) {
        if (!AppUtils.isBlank(newsCategory.getNewsCategoryId())) {
            update(newsCategory);
            return newsCategory.getNewsCategoryId();
        }
        return (Long) newsCategoryDao.save(newsCategory);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.NewsCategoryService#update(com.legendshop.model.entity.NewsCategory)
	 */
    @Override
	public void update(NewsCategory newsCategory) {
        newsCategoryDao.updateNewsCategory(newsCategory);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.NewsCategoryService#getDataByCriteriaQuery(com.legendshop.core.dao.support.CriteriaQuery)
	 */
    @Override
	public PageSupport getNewsCategoryList(CriteriaQuery cq) {
        return newsCategoryDao.find(cq);
    }
}

