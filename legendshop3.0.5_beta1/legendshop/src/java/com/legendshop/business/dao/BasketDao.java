/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao;

import java.util.List;
import java.util.Map;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.model.entity.Basket;
import com.legendshop.model.entity.Product;

/**
 * The Interface BasketDao.
 */
public interface BasketDao extends BaseDao{

	/**
	 * Delete basket by id.
	 * 
	 * @param basketId
	 *            the basket id
	 */
	public abstract void deleteBasketById(Long basketId);

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

	// group by shopName
	/**
	 * Gets the basket byuser name group by shop name.
	 * 
	 * @param userName
	 *            the user name
	 * @return the basket byuser name group by shop name
	 */
	public abstract Map<String, List<Basket>> getBasketByuserNameGroupByShopName(String userName);

	/**
	 * Gets the basket by id.
	 * 
	 * @param id
	 *            the id
	 * @return the basket by id
	 */
	public abstract Basket getBasketById(Long id);

	/**
	 * Gets the basket by id name.
	 * 
	 * @param prodId
	 *            the prod id
	 * @param userName
	 *            the user name
	 * @param shopName
	 *            the shop name
	 * @param attribute
	 *            the attribute
	 * @return the basket by id name
	 */
	public abstract Basket getBasketByIdName(Long prodId, String userName, String shopName, String attribute);

	// 用户在shopName的订购数
	/**
	 * Gets the basket by user name.
	 * 
	 * @param userName
	 *            the user name
	 * @param shopName
	 *            the shop name
	 * @return the basket by user name
	 */
	public abstract Long getBasketByUserName(String userName, String shopName);

	/**
	 * Save basket.
	 * 
	 * @param basket
	 *            the basket
	 * @return the long
	 */
	public abstract Long saveBasket(Basket basket);

	/**
	 * Update basket.
	 * 
	 * @param basket
	 *            the basket
	 */
	public abstract void updateBasket(Basket basket);

	/**
	 * Hold stocks.
	 * 
	 * @param prodId
	 *            商品ID
	 * @param basketCount
	 *            购买数量
	 * 
	 *            hold住库存量
	 */
	// 下订单时，增加商品购买数,TODO 应该在订单成功时进行
	public abstract void saveStocks(Long prodId, Integer basketCount);

	/**
	 * Gets the basket.
	 * 
	 * @param prodId
	 *            the prod id
	 * @param userName
	 *            the user name
	 * @return the basket
	 */
	public abstract List<Basket> getBasket(String prodId, String userName);

	/**
	 * Delete basket by user name.
	 * 
	 * @param userName
	 *            the user name
	 */
	public abstract void deleteBasketByUserName(String userName);

	/**
	 * Delete basket by sub number.
	 * 
	 * @param subNumber
	 *            the sub number
	 */
	public abstract void deleteBasketBySubNumber(String subNumber);


	/**
	 * Save to cart.
	 *
	 * @param userName the user name
	 * @param prodId the prod id
	 * @param count the count
	 * @param attribute the attribute
	 * @return true, if successful
	 */
	public boolean saveToCart(String userName,Long prodId,  Integer count, String attribute);


}