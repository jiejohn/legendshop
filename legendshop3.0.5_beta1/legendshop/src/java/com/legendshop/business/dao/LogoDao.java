/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SimpleHqlQuery;
import com.legendshop.model.entity.ShopDetail;

/**
 * The Interface LogoDao.
 */
public interface LogoDao extends BaseDao{


	/**
	 * Delete logo.
	 * 
	 * @param logo
	 *            the logo
	 */
	public abstract void deleteLogo(ShopDetail shopDetail);

	/**
	 * Update logo.
	 * 
	 * @param logo
	 *            the logo
	 */
	public abstract void updateLogo(ShopDetail shopDetail);


	/**
	 * Gets the logo.
	 *
	 * @param hql the hql
	 * @return the logo
	 */
	public abstract PageSupport getLogo(SimpleHqlQuery hql);

}