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
import java.util.Locale;

import com.legendshop.business.dao.NewsDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.BusinessException;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.model.entity.News;
import com.legendshop.model.entity.NewsCategory;
import com.legendshop.model.entity.Sort;
import com.legendshop.spi.constants.NewsPositionEnum;
import com.legendshop.spi.service.NewsService;
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
public class NewsServiceImpl extends BaseServiceImpl implements NewsService {
	
	/** The base dao. */
	private NewsDao newsDao;

	/**
	 * Sets the base dao.
	 * 
	 * @param newsDao
	 *            the new base dao
	 */
	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.NewsService#list(java.lang.String)
	 */
	@Override
	public List<News> getNewsList(String userName) {
		return newsDao.findByHQL("from News where userName = ?", new Object[] { userName });
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.NewsService#load(java.lang.Long)
	 */
	@Override
	public News getNewsById(Long id) {
		News news = newsDao.get(News.class, id);
		if (news.getSort() != null)
			news.setSortId(news.getSort().getSortId());
		if (news.getNewsCategory() != null)
			news.setNewsCategoryId(news.getNewsCategory().getNewsCategoryId());
		return news;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.NewsService#load(java.lang.Long, java.lang.String)
	 */
	@Override
	public News getNewsByIdAndName(Long id, String userName) {
		News news = newsDao.findUniqueBy("from News where newsId = ? and userName = ?", News.class, id, userName);
		if (news == null) {
			throw new BusinessException("no News record",EntityCodes.NEWS);
		}
		return news;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.NewsService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		newsDao.deleteNewsById(id);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.NewsService#save(com.legendshop.model.entity.News)
	 */
	@Override
	public Long save(News news) {
		if (!AppUtils.isBlank(news.getNewsId())) {
			News entity = newsDao.get(News.class, news.getNewsId());
			if (entity != null) {
				news.setUserId(entity.getUserId());
				news.setUserName(entity.getUserName());
				update(news);
				return news.getNewsId();
			}
			return null;
		}
		preUpdateNews(news);
		return (Long) newsDao.save(news);
	}

	/**
	 * Pre update news.
	 * 
	 * @param news
	 *            the news
	 */
	private void preUpdateNews(News news) {
		if (news.getNewsCategoryId() != null) {
			NewsCategory nc = new NewsCategory();
			nc.setNewsCategoryId(news.getNewsCategoryId());
			news.setNewsCategory(nc);
		}
		if (news.getSortId() != null) {
			Sort sort = new Sort();
			sort.setSortId(news.getSortId());
			news.setSort(sort);
		}
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.NewsService#update(com.legendshop.model.entity.News)
	 */
	@Override
	public void update(News news) {
		preUpdateNews(news);
		news.setNewsDate(new Date());
		newsDao.updateNews(news);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.NewsService#getDataByCriteriaQuery(com.legendshop.core.dao.support.CriteriaQuery)
	 */
	@Override
	public PageSupport getNewsList(CriteriaQuery cq) {
		return newsDao.find(cq);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.NewsService#getDataByCriteriaQuery(com.legendshop.core.dao.support.HqlQuery)
	 */
	@Override
	public PageSupport getNewsList(HqlQuery hql) {
		return newsDao.find(hql);
	}

	@Override
	public List<News> getNews(String shopName, NewsPositionEnum newsPositionEnum, Integer num) {
		return newsDao.getNews(shopName, newsPositionEnum, num);
	}

	@Override
	public PageSupport getNews(Locale locale, String curPageNO, String shopName, Long newsCategoryId) {
		return newsDao.getNews(locale, curPageNO, shopName, newsCategoryId);
	}
}
