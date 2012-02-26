package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.model.entity.Logo;

public interface LogoDao extends BaseDao{

	/**
	 * Gets the logo.
	 * 
	 * @param shopName
	 *            the shop name
	 * @return the logo
	 */
	public abstract Logo getLogo(final String shopName);

}