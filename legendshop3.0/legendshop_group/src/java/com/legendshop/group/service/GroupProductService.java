/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.group.service;

import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.group.GroupProduct;


/**
 * The Interface GroupProductService.
 */
public interface GroupProductService {


	/**
	 * Gets the group product list.
	 * 
	 * @param hql
	 *            the hql
	 * @return the group product list
	 */
	public abstract PageSupport getGroupProductList(HqlQuery hql);


	/**
	 * Gets the group group product by id.
	 * 
	 * @param groupProdId
	 *            the group prod id
	 * @return the group group product by id
	 */
	public abstract GroupProduct getGroupGroupProductById(Long groupProdId);

	/**
	 * Update group product.
	 * 
	 * @param groupProduct
	 *            the group product
	 */
	public abstract void updateGroupProduct(GroupProduct groupProduct);


	/**
	 * Save group product.
	 * 
	 * @param groupProduct
	 *            the group product
	 * @return the long
	 */
	public abstract Long saveGroupProduct(GroupProduct groupProduct);
}
