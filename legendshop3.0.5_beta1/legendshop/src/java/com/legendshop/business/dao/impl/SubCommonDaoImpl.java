/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.legendshop.business.dao.SubCommonDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.model.entity.Sub;
import com.legendshop.model.entity.SubHistory;
import com.legendshop.util.BeanHelper;

/**
 * The Class SubCommonDaoImpl.
 */
public class SubCommonDaoImpl extends BaseDaoImpl implements SubCommonDao {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(SubCommonDaoImpl.class);

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.SubCommonDao#saveSubHistory(com.legendshop.model.entity.Sub, java.lang.String)
	 */
	@Override
	public void saveSubHistory(Sub sub, String subAction) {
		SubHistory history = new SubHistory();
		try {
			BeanHelper.copyProperties(history, sub);
		} catch (Exception e) {
			log.error("saveSubHistory", e);
		}
		history.setUpdateTime(new Date());
		history.setSubAction(subAction);
		save(history);

	}
}
