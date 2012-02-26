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
import com.legendshop.business.dao.PubDao;
import com.legendshop.core.cache.CacheCallBack;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.model.entity.Pub;
import com.legendshop.util.AppUtils;

/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * ------------------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ------------------------------------------------------------------------------------
 * 
 * 官方网站：http://www.legendesign.net
 */
@SuppressWarnings("unchecked")
public class PubDaoImpl extends BaseDaoImpl implements PubDao {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(PubDaoImpl.class);

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.PubDao#getPub(java.lang.String)
	 */
	@Override
	public List<Pub> getPub(final String shopName) {
		log.debug("getPub, shopName = {}", shopName);
		if (shopName == null)
			return null;
		return (List<Pub>) getObjectFromCache(getKey(CacheKeys.PUBDAO_GETPUB, shopName),
				new CacheCallBack<List<Pub>>() {
					public List<Pub> doInCache(String cahceName, Cache cache) {
						CriteriaQuery cq = new CriteriaQuery(Pub.class);
						cq.eq("userName", shopName);
						cq.addOrder("desc", "date");
						cq.add();
						List<Pub> list = findListByCriteria(cq, 0, 4);
						if (AppUtils.isBlank(list)) {
							cq = new CriteriaQuery(Pub.class);
							cq.eq("userName", Constants.COMMON_USER);
							cq.addOrder("desc", "date");
							cq.add();
							list = findListByCriteria(cq, 0, 4);
						}
						return list;
					}
				});
	}

}
