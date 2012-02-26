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
import com.legendshop.business.dao.ExternalLinkDao;
import com.legendshop.core.cache.CacheCallBack;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.model.entity.ExternalLink;
import com.legendshop.util.AppUtils;

/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * -----------------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * -----------------------------------------------------------------------------------
 * 
 * 官方网站：http://www.legendesign.net
 */
@SuppressWarnings("unchecked")
public class ExternalLinkDaoImpl extends BaseDaoImpl implements ExternalLinkDao {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(ExternalLinkDaoImpl.class);

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ExternalLinkDao#getExternalLinkOrderbybs(java.lang.String)
	 */
	@Override
	public List<ExternalLink> getExternalLink(final String shopName) {
		log.debug("getExternalLink, shopName = {}", shopName);
		if (shopName == null) {
			return null;
		}
		return (List<ExternalLink>) getObjectFromCache(getKey(CacheKeys.EXTERNALLINKDAO_GETEXTERNALLINKORDERBYBS,
				shopName), new CacheCallBack() {
			@Override
			public List<ExternalLink> doInCache(String cahceName, Cache cache) {
				List list = findByHQL("from ExternalLink where userName = ? order by bs", shopName);
				if (AppUtils.isBlank(list)) {
					list = findByHQL("from ExternalLink where userName = ? order by bs", Constants.COMMON_USER);
				}
				return list;
			}

		});
	}

}
