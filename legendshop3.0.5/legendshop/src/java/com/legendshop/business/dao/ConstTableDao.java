/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao;

import java.util.List;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.model.entity.ConstTable;

/**
 * The Interface ConstTableDao.
 */
public interface ConstTableDao  extends BaseDao {
	
	/**
	 * Load all const table.
	 * 
	 * @return the list
	 */
	public List<ConstTable> loadAllConstTable();
}
