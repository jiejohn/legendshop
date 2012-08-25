package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.model.entity.Sub;

public interface SubCommonDao extends BaseDao{

	/**
	 * Save sub history.
	 * 
	 * @param sub
	 *            the sub
	 * @param subAction
	 *            the sub action
	 */
	public abstract void saveSubHistory(Sub sub, String subAction);

}