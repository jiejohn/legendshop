/**
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import com.legendshop.business.dao.OrderDao;
import com.legendshop.business.service.OrderService;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;

/**
 * Order服务.
 */
public class OrderServiceImpl implements OrderService {
	
    /** The order dao. */
    private OrderDao orderDao;

	/**
	 * Sets the order dao.
	 * 
	 * @param orderDao
	 *            the new order dao
	 */
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.OrderService#findOrder(com.legendshop.core.dao.support.CriteriaQuery)
	 */
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.OrderService#findOrder(com.legendshop.core.dao.support.CriteriaQuery)
	 */
	@Override
	public PageSupport getOrderList(CriteriaQuery cq) {
		return orderDao.getOrder(cq);
	}

}
