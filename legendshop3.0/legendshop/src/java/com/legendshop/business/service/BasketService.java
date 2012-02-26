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

public interface BasketService {

	/**
	 * Addtocart.
	 * 
	 * @param prodId
	 *            the prod id
	 * @param addtoCart
	 *            the addto cart
	 * @param shopName
	 *            the shop name
	 * @param prodattr
	 *            the prodattr
	 * @param userName
	 *            the user name
	 * @param count
	 *            the count
	 */
	public abstract void saveToCart(Long prodId, String addtoCart, String shopName, String prodattr, String userName,
			Integer count);

	/**
	 * Addtocart.
	 * 
	 * @param prodId
	 *            the prod id
	 * @param pic
	 *            the pic
	 * @param userName
	 *            the user name
	 * @param shopName
	 *            the shop name
	 * @param count
	 *            the count
	 * @param attribute
	 *            the attribute
	 * @param prodName
	 *            the prod name
	 * @param cash
	 *            the cash
	 * @param carriage
	 *            the carriage
	 */
	public abstract void saveToCart(Long prodId, String pic, String userName, String shopName, Integer count,
			String attribute, String prodName, Double cash, Double carriage);

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

}