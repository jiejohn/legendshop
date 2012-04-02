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
import com.legendshop.model.entity.ShopDetail;
import com.legendshop.model.entity.ShopDetailView;
import com.legendshop.model.entity.UserDetail;

/**
 * The Interface ShopDetailService.
 */
public interface ShopDetailService {

	/**
	 * Find shop detail.
	 * 
	 * @param id
	 *            the id
	 * @return the shop detail
	 */
	public abstract ShopDetail getShopDetailById(Long id);

	/**
	 * Find user detail by name.
	 * 
	 * @param userName
	 *            the user name
	 * @return the user detail
	 */
	public abstract UserDetail getShopDetailByName(String userName);
	
	/**
	 * Gets the shop detail view.
	 * 
	 * @param currentShopName
	 *            the current shop name
	 * @return the shop detail view
	 */
	public abstract ShopDetailView getShopDetailView(String currentShopName);

	/**
	 * Delete.
	 * 
	 * @param id
	 *            the id
	 */
	public abstract void delete(Long id);

	/**
	 * Delete.
	 * 
	 * @param shopDetail
	 *            the shop detail
	 */
	public abstract void delete(ShopDetail shopDetail);

	// UserId 一定不能为空
	/**
	 * Save.
	 * 
	 * @param shopDetail
	 *            the shop detail
	 */
	public abstract void save(ShopDetail shopDetail);

	/**
	 * Update.
	 * 
	 * @param shopDetail
	 *            the shop detail
	 */
	public abstract void update(ShopDetail shopDetail);

	/**
	 * Gets the data by criteria query.
	 * 
	 * @param cq
	 *            the cq
	 * @return the data by criteria query
	 */
	public abstract PageSupport getShopDetail(CriteriaQuery cq);

}