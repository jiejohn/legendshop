/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SimpleHqlQuery;
import com.legendshop.model.entity.ShopDetail;

/**
 * The Interface LogoService.
 */
public interface LogoService {

	/**
	 * Delete logo.
	 *
	 * @param userId the user id
	 */
	public abstract void deleteLogo(ShopDetail shopDetail);

	/**
	 * Update.
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