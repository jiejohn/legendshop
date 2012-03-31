/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import java.util.List;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.util.AppUtils;
import com.legendshop.business.dao.DeliveryTypeDao;
import com.legendshop.model.entity.DeliveryType;
import com.legendshop.business.service.DeliveryTypeService;

/**
 * The Class DeliveryTypeServiceImpl.
 */
public class DeliveryTypeServiceImpl  implements DeliveryTypeService{
    private DeliveryTypeDao deliveryTypeDao;

    public void setDeliveryTypeDao(DeliveryTypeDao deliveryTypeDao) {
        this.deliveryTypeDao = deliveryTypeDao;
    }

    public List<DeliveryType> getDeliveryType(String userName) {
        return deliveryTypeDao.getDeliveryType(userName);
    }

    public DeliveryType getDeliveryType(Long id) {
        return deliveryTypeDao.getDeliveryType(id);
    }

    public void deleteDeliveryType(DeliveryType deliveryType) {
        deliveryTypeDao.deleteDeliveryType(deliveryType);
    }

    public Long saveDeliveryType(DeliveryType deliveryType) {
        if (!AppUtils.isBlank(deliveryType.getDvyTypeId())) {
            updateDeliveryType(deliveryType);
            return deliveryType.getDvyTypeId();
        }
        return (Long) deliveryTypeDao.save(deliveryType);
    }

    public void updateDeliveryType(DeliveryType deliveryType) {
        deliveryTypeDao.updateDeliveryType(deliveryType);
    }

    public PageSupport getDeliveryType(CriteriaQuery cq) {
        return deliveryTypeDao.find(cq);
    }
}

