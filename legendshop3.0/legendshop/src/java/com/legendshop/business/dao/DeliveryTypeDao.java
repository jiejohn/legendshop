/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao;
 
import com.legendshop.core.dao.BaseDao;
import java.util.List;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.DeliveryType;

/**
 * The Class DeliveryTypeDao.
 */

public interface DeliveryTypeDao extends BaseDao {
     
    public abstract List<DeliveryType> getDeliveryType(String shopName);

	public abstract DeliveryType getDeliveryType(Long id);
	
    public abstract void deleteDeliveryType(DeliveryType deliveryType);
	
	public abstract Long saveDeliveryType(DeliveryType deliveryType);
	
	public abstract void updateDeliveryType(DeliveryType deliveryType);
	
	public abstract PageSupport getDeliveryType(CriteriaQuery cq);
	
 }

