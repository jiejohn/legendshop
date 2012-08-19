/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.legendshop.business.dao.ExternalLinkDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.model.entity.ExternalLink;
import com.legendshop.spi.constants.Constants;
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
	@Cacheable(value="ExternalLinkList",key="#shopName")
	public List<ExternalLink> getExternalLink(final String shopName) {
		log.debug("getExternalLink, shopName = {}", shopName);
		if (shopName == null) {
			return null;
		}
		List list = findByHQL("from ExternalLink where userName = ? order by bs", shopName);
		if (AppUtils.isBlank(list)) {
			list = findByHQL("from ExternalLink where userName = ? order by bs", Constants.COMMON_USER);
		}
		return list;
	}

	@Override
	@CacheEvict(value = "ExternalLink", key = "#id")
	public void deleteExternalLinkById(Long id) {
		deleteById(ExternalLink.class, id);
	}

	@Override
	@CacheEvict(value = "ExternalLink", key = "#externalLink.id")
	public void updateExternalLink(ExternalLink externalLink) {
		update(externalLink);
		
	}

}
