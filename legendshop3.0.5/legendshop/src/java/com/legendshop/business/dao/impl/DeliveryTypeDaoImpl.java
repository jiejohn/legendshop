/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao.impl;
 
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.legendshop.business.dao.DeliveryTypeDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.DeliveryType;
/**
 * The Class DeliveryTypeDaoImpl.
 */

public class DeliveryTypeDaoImpl extends BaseDaoImpl implements DeliveryTypeDao {
    private static Logger log = LoggerFactory.getLogger(DeliveryTypeDaoImpl.class);
     
    public List<DeliveryType> getDeliveryType(String userName){
   		return findByHQL("from DeliveryType where userName = ?", userName);
    }

	public DeliveryType getDeliveryType(Long id){
		return get(DeliveryType.class, id);
	}
	
    public void deleteDeliveryType(DeliveryType deliveryType){
    	delete(deliveryType);
    }
	
	public Long saveDeliveryType(DeliveryType deliveryType){
		return (Long)save(deliveryType);
	}
	
	public void updateDeliveryType(DeliveryType deliveryType){
		 update(deliveryType);
	}
	
	public PageSupport getDeliveryType(CriteriaQuery cq){
		return find(cq);
	}
	
 }

