/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.legendshop.business.dao.LuceneDao;
import com.legendshop.business.service.LuceneService;

/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public class LuceneServiceImpl implements LuceneService {
	
	/** The lucene dao. */
	private LuceneDao luceneDao;

	/**
	 * Sets the lucene dao.
	 * 
	 * @param luceneDao
	 *            the new lucene dao
	 */
	@Required
	public void setLuceneDao(LuceneDao luceneDao) {
		this.luceneDao = luceneDao;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.LuceneService#firstPostIdByDate(int, java.util.Date)
	 */
	@Override
	public long getFirstPostIdByDate(int entityType, Date fromDate) {
		return luceneDao.getFirstPostIdByDate(entityType, fromDate);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.LuceneService#getPostsToIndex(int, long, long)
	 */
	@Override
	public List getPostsToIndex(int entityType, long firstPostId, long toPostId) {
		return luceneDao.getPostsToIndex(entityType, firstPostId, toPostId);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.LuceneService#lastPostIdByDate(int, java.util.Date)
	 */
	@Override
	public long getLastPostIdByDate(int entityType, Date toDate) {
		return luceneDao.getLastPostIdByDate(entityType, toDate);
	}

}
