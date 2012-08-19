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
import com.legendshop.model.entity.CashFlow;

/**
 * The Class CashFlowService.
 */
public interface CashFlowService  {

    public List<CashFlow> getCashFlow(String userName);

    public CashFlow getCashFlow(Long id);
    
    public void deleteCashFlow(CashFlow cashFlow);
    
    public Long saveCashFlow(CashFlow cashFlow);

    public void updateCashFlow(CashFlow cashFlow);

    public PageSupport getCashFlow(CriteriaQuery cq);
}

