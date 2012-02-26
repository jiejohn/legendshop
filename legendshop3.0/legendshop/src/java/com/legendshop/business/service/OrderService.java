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

/**
 * The Interface OrderService.
 */
public interface OrderService {
	
	/**
	 * Gets the order list.
	 * 
	 * @param cq
	 *            the cq
	 * @return the order list
	 */
	public PageSupport getOrderList(CriteriaQuery cq);
}
