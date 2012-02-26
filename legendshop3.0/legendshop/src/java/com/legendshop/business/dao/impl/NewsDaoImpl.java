/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao.impl;

import java.util.List;

import net.sf.ehcache.Cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.legendshop.business.common.CacheKeys;
import com.legendshop.business.common.Constants;
import com.legendshop.business.common.NewsCategoryStatusEnum;
import com.legendshop.business.dao.NewsDao;
import com.legendshop.core.cache.CacheCallBack;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.News;
import com.legendshop.util.AppUtils;

/**
 * 新闻Dao.
 */
@SuppressWarnings("unchecked")
public class NewsDaoImpl extends BaseDaoImpl implements NewsDao {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(NewsDaoImpl.class);

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.NewsDao#getNews(java.lang.String, com.legendshop.business.common.NewsCategoryStatusEnum, java.lang.Integer)
	 */
	@Override
	public List<News> getNews(final String shopName, final NewsCategoryStatusEnum newsCategoryStatusEnum,
			final Integer num) {
		if (shopName == null) {
			log.warn("shopName is null");
			return null;
		}
		return (List<News>) getObjectFromCache(getKey(CacheKeys.NEWSDAO_GETNEWS, shopName, newsCategoryStatusEnum
				.value(), num == null ? "" : num), new CacheCallBack<List<News>>() {
			@Override
			public List<News> doInCache(String cahceName, Cache cache) {
				CriteriaQuery cq = new CriteriaQuery(News.class);
				cq.eq("userName", shopName);
				cq.eq("status", newsCategoryStatusEnum.value());
				cq.addOrder("desc", "newsDate");
				cq.add();
				List<News> list = findListByCriteria(cq, 0, num);

				if (AppUtils.isBlank(list)) {
					cq = new CriteriaQuery(News.class);
					cq.eq("userName", Constants.COMMON_USER);
					cq.eq("status", newsCategoryStatusEnum.value());
					cq.addOrder("desc", "newsDate");
					cq.add();
					list = findListByCriteria(cq, 0, num);
				}

				return list;
			}
		});
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.NewsDao#getNewsById(java.lang.Long)
	 */
	@Override
	public News getNewsById(Long newsId) {
		return get(News.class, newsId);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.NewsDao#getNews(com.legendshop.core.dao.support.CriteriaQuery)
	 */
	@Override
	public PageSupport getNews(CriteriaQuery cq) {
		return find(cq);
	}

}
