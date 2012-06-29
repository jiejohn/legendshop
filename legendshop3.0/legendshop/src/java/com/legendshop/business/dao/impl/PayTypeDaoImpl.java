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

import com.legendshop.business.dao.PayTypeDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.model.entity.PayType;

/**
 * 付费类型Dao.
 */
public class PayTypeDaoImpl extends BaseDaoImpl implements PayTypeDao {
     
     /** The log. */
     private static Logger log = LoggerFactory.getLogger(PayTypeDaoImpl.class);

	@Override
	public void updatePayType(PayType payType) {
		update(payType);
		
	}

	@Override
	public void deletePayTypeById(Long id) {
		deleteById(PayType.class, id);
		
	}
     
	
 }

