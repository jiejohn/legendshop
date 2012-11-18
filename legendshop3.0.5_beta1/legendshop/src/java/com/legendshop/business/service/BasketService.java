/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service;

import java.util.List;

import com.legendshop.model.entity.Basket;

/**
 * The Interface BasketService.
 */
public interface BasketService {


	/**
	 * Save to cart.
	 *
	 * @param prodId the prod id
	 * @param shopName the shop name
	 * @param prodattr the prodattr
	 * @param userName the user name
	 * @param count the count
	 */
	public abstract void saveToCart(Long prodId,String shopName, String prodattr, String userName,
			Integer count);

	/**
	 * Delete basket by user name.
	 * 
	 * @param userName
	 *            the user name
	 */
	public abstract void deleteBasketByUserName(String userName);

	/**
	 * Delete basket by id.
	 * 
	 * @param id
	 *            the id
	 */
	public abstract void deleteBasketById(Long id);

	/**
	 * Gets the basket byuser name.
	 * 
	 * @param userName
	 *            the user name
	 * @return the basket byuser name
	 */
	public abstract List<Basket> getBasketByuserName(String userName);
	
	// 得到有效订单总数
	/**
	 * Gets the total basket byuser name.
	 * 
	 * @param userName
	 *            the user name
	 * @return the total basket byuser name
	 */
	public abstract Long getTotalBasketByuserName(String userName);

}