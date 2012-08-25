/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao.impl;

import java.util.List;

import com.legendshop.business.dao.ConstTableDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.model.entity.ConstTable;

/**
 * The Class ConstTableDaoImpl.
 */
public class ConstTableDaoImpl extends BaseDaoImpl implements ConstTableDao{

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.ConstTableDao#loadAllConstTable()
	 */
	@Override
	public List<ConstTable> loadAllConstTable() {
		return findByHQL("from ConstTable c order by c.id.type, c.seq");
	}

}
