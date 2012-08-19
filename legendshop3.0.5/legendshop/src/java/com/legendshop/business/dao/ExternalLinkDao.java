/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao;

import java.util.List;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.model.entity.ExternalLink;

/**
 * The Interface ExternalLinkDao.
 */
public interface ExternalLinkDao extends BaseDao{

	/**
	 * Gets the external link orderbybs.
	 * 
	 * @param shopName
	 *            the shop name
	 * @return the external link orderbybs
	 */
	public abstract List<ExternalLink> getExternalLink(final String shopName);

	/**
	 * Delete external link by id.
	 * 
	 * @param id
	 *            the id
	 */
	public abstract void deleteExternalLinkById(Long id);

	/**
	 * Update external link.
	 * 
	 * @param externalLink
	 *            the external link
	 */
	public abstract void updateExternalLink(ExternalLink externalLink);

}