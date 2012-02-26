package com.legendshop.business.service;

import com.legendshop.central.license.LSResponse;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.ShopDetail;
import com.legendshop.model.entity.UserDetail;

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
	 * Checks if is support open shop.
	 * 
	 * @param lsResponse
	 *            the ls response
	 * @return the boolean
	 */
	public abstract Boolean isSupportOpenShop(LSResponse lsResponse);

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