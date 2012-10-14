/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao.impl;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.legendshop.business.dao.NewsDao;
import com.legendshop.core.OperationTypeEnum;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.event.impl.FireEvent;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.event.EventHome;
import com.legendshop.model.entity.News;
import com.legendshop.spi.constants.Constants;
import com.legendshop.spi.constants.NewsPositionEnum;
import com.legendshop.util.AppUtils;

/**
 * 新闻Dao.
 */
@SuppressWarnings("unchecked")
public class NewsDaoImpl extends BaseDaoImpl implements NewsDao {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(NewsDaoImpl.class);

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.NewsDao#getNews(java.lang.String, com.legendshop.business.common.NewsPositionEnum, java.lang.Integer)
	 */
	@Override
	@Cacheable(value="NewsList")
	public List<News> getNews(final String shopName, final NewsPositionEnum newsPositionEnum,
			final Integer num) {
		if (shopName == null) {
			log.warn("shopName is null");
			return null;
		}
		CriteriaQuery cq = new CriteriaQuery(News.class);
		cq.eq("userName", shopName);
		cq.eq("position", newsPositionEnum.value());
		cq.eq("status", Constants.ONLINE);
		cq.addOrder("desc", "newsDate");
		
		List<News> list = findListByCriteria(cq, 0, num);

		if (AppUtils.isBlank(list)) {
			cq = new CriteriaQuery(News.class);
			cq.eq("userName", Constants.COMMON_USER);
			cq.eq("status", newsPositionEnum.value());
			cq.addOrder("desc", "newsDate");
			
			list = findListByCriteria(cq, 0, num);
		}
		
		return list;
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
	@Cacheable(value="NewsList",condition="T(Integer).parseInt(#curPageNO) < 3")
	public PageSupport getNews(Locale locale, String curPageNO,String userName,Long newsCategoryId) {
		// Qbc查找方式
		CriteriaQuery cq = new CriteriaQuery(News.class, curPageNO);
		cq.setPageSize(PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE, Integer.class));
		cq.eq("status", Constants.ONLINE);
		cq.eq("position", NewsPositionEnum.NEWS_NEWS.value());
		cq.eq("userName", userName);
		cq.eq("newsCategory.newsCategoryId", newsCategoryId);
		cq.addOrder("desc", "newsDate");
		
		PageSupport ps = find(cq);
		ps.setToolBar(locale, Constants.SIMPLE_PAGE_PROVIDER);
		return ps;
	}

	@Override
	@CacheEvict(value = "News", key = "#news.newsId")
	public void updateNews(News news) {
		EventHome.publishEvent(new FireEvent(news, OperationTypeEnum.UPDATE));
		update(news);
	}

	@Override
	@CacheEvict(value = "News", key = "#id")
	public void deleteNewsById(Long id) {
		News news = getNewsById(id);
		if(news != null){
			EventHome.publishEvent(new FireEvent(news, OperationTypeEnum.DELETE));
			delete(news);
		}
	}

	@Override
	public Long getAllNews(String userName) {
		return findUniqueBy("select count(*) from News where userName = ?", Long.class, userName);
	}

}
