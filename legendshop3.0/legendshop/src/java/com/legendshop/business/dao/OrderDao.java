package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;

public interface OrderDao extends BaseDao{

	/**
	 * Find order.
	 * 
	 * @param cq
	 *            the cq
	 * @return the page support
	 */
	public abstract PageSupport getOrder(CriteriaQuery cq);

}