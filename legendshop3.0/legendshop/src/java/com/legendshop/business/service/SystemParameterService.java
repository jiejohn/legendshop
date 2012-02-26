package com.legendshop.business.service;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.SystemParameter;

public interface SystemParameterService {

	/**
	 * Load.
	 * 
	 * @param id
	 *            the id
	 * @return the system parameter
	 */
	public abstract SystemParameter getSystemParameter(String id);

	/**
	 * Delete.
	 * 
	 * @param id
	 *            the id
	 */
	public abstract void delete(String id);

	/**
	 * Save.
	 * 
	 * @param systemParameter
	 *            the system parameter
	 */
	public abstract void save(SystemParameter systemParameter);

	/**
	 * Update.
	 * 
	 * @param systemParameter
	 *            the system parameter
	 */
	public abstract void update(SystemParameter systemParameter);

	/**
	 * Gets the data by criteria query.
	 * 
	 * @param cq
	 *            the cq
	 * @return the data by criteria query
	 */
	public abstract PageSupport getSystemParameterList(CriteriaQuery cq);

	// 初始化System parameter
	/**
	 * Inits the system parameter.
	 */
	public abstract void initSystemParameter();

}