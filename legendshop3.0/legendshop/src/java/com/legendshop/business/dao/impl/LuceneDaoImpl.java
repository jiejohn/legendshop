/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.legendshop.business.dao.LuceneDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.search.LuceneIndexer;
import com.legendshop.util.AppUtils;

/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。 ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ---------------------------------------------------------------------------- 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public class LuceneDaoImpl extends BaseDaoImpl implements LuceneDao {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(LuceneDaoImpl.class);

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.LuceneDao#firstPostIdByDate(int, java.util.Date)
	 */
	@Override
	public long getFirstPostIdByDate(int entityType, Date fromDate) {
		log.debug("firstPostIdByDate, entityType = {},fromDate = {} ", entityType, fromDate);
		if (fromDate != null) {
			if (LuceneIndexer.SEARCH_ENTITY_PROD.equals(entityType)) {
				Long result = findUniqueByHQLLimit("select prodId from Product where prodDate > ?", Long.class, 0, 1, fromDate);
				if (AppUtils.isNotBlank(result)) {
					return result;
				}
			} else if (LuceneIndexer.SEARCH_ENTITY_SHOPDETAIL.equals(entityType)) {
				Long result = findUniqueByHQLLimit("select shopId from ShopDetail where addtime > ?", Long.class, 0, 1,
						fromDate);
				if (AppUtils.isNotBlank(result)) {
					return result;
				}
			}
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.LuceneDao#getPostsToIndex(int, long, long)
	 */
	@Override
	public List getPostsToIndex(int entityType, long firstPostId, long toPostId) {
		List list = null;
		if (LuceneIndexer.SEARCH_ENTITY_PROD.equals(entityType)) {
			list = findByHQL("from Product where prodId > ? and prodId < ?", firstPostId, toPostId);
		} else if (LuceneIndexer.SEARCH_ENTITY_SHOPDETAIL.equals(entityType)) {
			list = findByHQL("from ShopDetail where shopId > ? and shopId < ?", firstPostId, toPostId);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.LuceneDao#lastPostIdByDate(int, java.util.Date)
	 */
	@Override
	public long getLastPostIdByDate(int entityType, Date toDate) {
		if (toDate != null) {
			if (LuceneIndexer.SEARCH_ENTITY_PROD.equals(entityType)) {
				Long result = findUniqueByHQLLimit("select prodId from Product where prodDate < ? order by prodId desc",
						Long.class, 0, 1, toDate);
				if (AppUtils.isNotBlank(result)) {
					return result;
				}
			} else if (LuceneIndexer.SEARCH_ENTITY_SHOPDETAIL.equals(entityType)) {
				Long result = findUniqueByHQLLimit(
						"select shopId from ShopDetail where addtime < ? order by shopId desc", Long.class, 0, 1,
						toDate);
				if (AppUtils.isNotBlank(result)) {
					return result;
				}
			}
		}
		return 0;
	}

}
