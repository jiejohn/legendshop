/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service;

import java.util.List;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.VisitLog;

/**
 * The Interface VisitLogService.
 */
public interface VisitLogService {

	/**
	 * List.
	 * 
	 * @param userName
	 *            the user name
	 * @return the list
	 */
	public abstract List<VisitLog> getVisitLogList(String userName);

	/**
	 * Load.
	 * 
	 * @param id
	 *            the id
	 * @return the visit log
	 */
	public abstract VisitLog getVisitLogById(Long id);

	/**
	 * Delete.
	 * 
	 * @param id
	 *            the id
	 */
	public abstract void delete(Long id);

	/**
	 * Save.
	 * 
	 * @param visitLog
	 *            the visit log
	 * @return the long
	 */
	public abstract Long save(VisitLog visitLog);

	/**
	 * Update.
	 * 
	 * @param visitLog
	 *            the visit log
	 */
	public abstract void update(VisitLog visitLog);

	/**
	 * Gets the data by criteria query.
	 * 
	 * @param cq
	 *            the cq
	 * @return the data by criteria query
	 */
	public abstract PageSupport getVisitLogList(CriteriaQuery cq);
	
	/**
	 * Process.
	 * 
	 * @param visitLog
	 *            the visit log
	 */
	public abstract void process(VisitLog visitLog);

}