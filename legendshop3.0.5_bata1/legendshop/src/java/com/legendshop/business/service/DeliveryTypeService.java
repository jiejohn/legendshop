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
import com.legendshop.model.entity.DeliveryType;

/**
 * The Class DeliveryTypeService.
 */
public interface DeliveryTypeService  {

    public List<DeliveryType> getDeliveryType(String userName);

    public DeliveryType getDeliveryType(Long id);
    
    public void deleteDeliveryType(DeliveryType deliveryType);
    
    public Long saveDeliveryType(DeliveryType deliveryType);

    public void updateDeliveryType(DeliveryType deliveryType);

    public PageSupport getDeliveryType(CriteriaQuery cq);
}

