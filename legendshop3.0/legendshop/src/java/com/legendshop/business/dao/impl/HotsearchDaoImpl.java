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
import com.legendshop.business.dao.HotsearchDao;
import com.legendshop.core.cache.CacheCallBack;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.model.entity.Hotsearch;

/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * ------------------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ------------------------------------------------------------------------------------
 * 
 * 
 * 官方网站：http://www.legendesign.net
 */
public class HotsearchDaoImpl extends BaseDaoImpl implements HotsearchDao {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(HotsearchDaoImpl.class);

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.HotsearchDao#getSearch(java.lang.String)
	 */
	@Override
	public List<Hotsearch> getSearch(String shopName) {
		log.debug("getSearch, shopName = {}", shopName);
		return findByHQL("from Hotsearch where userName = ? order by Id DESC", new Object[] { shopName });
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.HotsearchDao#getSearch(java.lang.String, java.lang.Long)
	 */
	@Override
	public List<Hotsearch> getSearch(final String userName, final Long isortId) {
		return (List<Hotsearch>) getObjectFromCache(getKey(CacheKeys.HOTSEARCHDAO_GETSEARCH, userName, isortId),
				new CacheCallBack<List<Hotsearch>>() {
					public List<Hotsearch> doInCache(String cahceName, Cache cache) {
						return findByHQL("from Hotsearch where userName = ?  and sort = ?", userName, isortId);
					}
				});
	}

}
