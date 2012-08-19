/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.model.entity.SystemParameter;

/**
 * The Interface SystemParameterDao.
 */
public interface SystemParameterDao extends BaseDao{

	/**
	 * Delete system parameter by id.
	 * 
	 * @param id
	 *            the id
	 */
	void deleteSystemParameterById(String id);

	/**
	 * Update system parameter.
	 * 
	 * @param systemParameter
	 *            the system parameter
	 */
	void updateSystemParameter(SystemParameter systemParameter);

}