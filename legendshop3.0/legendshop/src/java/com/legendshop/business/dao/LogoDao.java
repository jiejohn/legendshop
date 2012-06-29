/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.model.entity.Logo;

/**
 * The Interface LogoDao.
 */
public interface LogoDao extends BaseDao{

	/**
	 * Gets the logo.
	 * 
	 * @param shopName
	 *            the shop name
	 * @return the logo
	 */
	public abstract Logo getLogo(final String shopName);


	/**
	 * Delete logo.
	 * 
	 * @param logo
	 *            the logo
	 */
	public abstract void deleteLogo(Logo logo);

	/**
	 * Update logo.
	 * 
	 * @param logo
	 *            the logo
	 */
	public abstract void updateLogo(Logo logo);

}