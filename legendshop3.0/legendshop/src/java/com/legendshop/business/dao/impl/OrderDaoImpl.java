/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.legendshop.business.dao.OrderDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;

/**
 * OrderDaoImpl.
 */
public class OrderDaoImpl extends BaseDaoImpl implements OrderDao {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(OrderDaoImpl.class);

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.OrderDao#findOrder(com.legendshop.core.dao.support.CriteriaQuery)
	 */
	@Override
	public PageSupport getOrder(CriteriaQuery cq) {
		return find(cq);
	}

}
