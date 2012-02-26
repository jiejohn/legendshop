package com.legendshop.business.dao;

import java.util.List;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.model.entity.ExternalLink;

public interface ExternalLinkDao extends BaseDao{

	/**
	 * Gets the external link orderbybs.
	 * 
	 * @param shopName
	 *            the shop name
	 * @return the external link orderbybs
	 */
	public abstract List<ExternalLink> getExternalLink(final String shopName);

}