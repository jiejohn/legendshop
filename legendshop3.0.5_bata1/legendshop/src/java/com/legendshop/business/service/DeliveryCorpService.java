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
import com.legendshop.model.entity.DeliveryCorp;

/**
 * The Class DeliveryCorpService.
 */
public interface DeliveryCorpService  {

    public List<DeliveryCorp> getDeliveryCorp(String userName);

    public DeliveryCorp getDeliveryCorp(Long id);
    
    public void deleteDeliveryCorp(DeliveryCorp deliveryCorp);
    
    public Long saveDeliveryCorp(DeliveryCorp deliveryCorp);

    public void updateDeliveryCorp(DeliveryCorp deliveryCorp);

    public PageSupport getDeliveryCorp(CriteriaQuery cq);
}

