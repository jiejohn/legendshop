/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao;

import java.util.Date;
import java.util.List;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Basket;
import com.legendshop.model.entity.Sub;

/**
 * The Interface SubDao.
 */
public interface SubDao extends SubCommonDao{

	/**
	 * Save sub.
	 * 
	 * @param sub
	 *            the sub
	 */
	public abstract void saveSub(Sub sub);

	/**
	 * Delete sub.
	 * 
	 * @param sub
	 *            the sub
	 * @return true, if successful
	 */
	public abstract boolean deleteSub(Sub sub);

	/**
	 * Admin change sub price.
	 * 
	 * @param sub
	 *            the sub
	 * @param userName
	 *            the user name
	 * @param totalPrice
	 *            the total price
	 * @return true, if successful
	 */
	public abstract boolean updateSubPrice(Sub sub, String userName, Double totalPrice);

	/**
	 * Gets the sub by id.
	 * 
	 * @param subId
	 *            the sub id
	 * @return the sub by id
	 */
	public abstract Sub getSubById(Long subId);

	/**
	 * Find sub by sub number.
	 * 
	 * @param subNumber
	 *            the sub number
	 * @return the sub
	 */
	public abstract Sub getSubBySubNumber(String subNumber);

	/**
	 * Update sub.
	 * 
	 * @param sub
	 *            the sub
	 * @param status
	 *            the status
	 * @param userName
	 *            the user name
	 * @return true, if successful
	 */
	public abstract boolean updateSub(Sub sub, Integer status, String userName);

	// basket_check = Y 表示已经下单了，形成了一条订单
	/**
	 * Gets the basket by sub number.
	 * 
	 * @param subNumber
	 *            the sub number
	 * @return the basket by sub number
	 */
	public abstract List<Basket> getBasketBySubNumber(String subNumber);

	/**
	 * 超时不付款的订单.
	 * 
	 * @param maxNum
	 *            the max num
	 * @param expireDate
	 *            the expire date
	 * @return the list
	 */
	public abstract List<Sub> getFinishUnPay(int maxNum, Date expireDate);

	/**
	 * 超时不确认收货的自动收货 此时卖家已经发货.
	 * 
	 * @param maxNum
	 *            the max num
	 * @param expireDate
	 *            the expire date
	 * @return the list
	 */
	public abstract List<Sub> getUnAcklodgeSub(int maxNum, Date expireDate);

	/**
	 * 移除已经过期的购物车，保留30天.
	 * 
	 * @param date
	 *            the date
	 */
	public abstract void deleteOverTimeBasket(Date date);

	public abstract void updateSub(Sub sub);
	
	/**
	 * Find order.
	 * 
	 * @param cq
	 *            the cq
	 * @return the page support
	 */
	public abstract PageSupport getOrder(CriteriaQuery cq);
	
	/**
	 * Gets the total processing order.
	 * 
	 * @param userName
	 *            the user name
	 * @return the total processing order
	 */
	public abstract Long getTotalProcessingOrder(String userName);
	
	// 判断用户是否已经订购产品，条件:订单状态为Y
	/**
	 * Checks if is user order product.
	 * 
	 * @param prodId
	 *            the prod id
	 * @param userName
	 *            the user name
	 * @return true, if is user order product
	 */
	public abstract boolean isUserOrderProduct(Long prodId, String userName);


}