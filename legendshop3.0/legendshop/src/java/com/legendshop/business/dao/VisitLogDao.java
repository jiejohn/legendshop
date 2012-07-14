/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.model.entity.VisitLog;

/**
 * The Interface VisitLogDao.
 */
public interface VisitLogDao extends BaseDao{

	/**
	 * Update visit log.
	 * 
	 * @param visitLog
	 *            the visit log
	 */
	void updateVisitLog(VisitLog visitLog);

	/**
	 * Delete visit log by id.
	 * 
	 * @param id
	 *            the id
	 */
	void deleteVisitLogById(Long id);
	
	/**
	 * Gets the visited index log.
	 * 
	 * @param visitLog
	 *            the visit log
	 * @return the visited index log
	 */
	VisitLog getVisitedIndexLog(VisitLog visitLog);
	
	/**
	 * Gets the visited prod log.
	 * 
	 * @param visitLog
	 *            the visit log
	 * @return the visited prod log
	 */
	VisitLog getVisitedProdLog(VisitLog visitLog);

}
