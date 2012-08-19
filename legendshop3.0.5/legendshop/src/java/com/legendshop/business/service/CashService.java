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
import com.legendshop.model.entity.Cash;

/**
 * The Class CashService.
 */
public interface CashService  {

    public List<Cash> getCash(String userName);

    public Cash getCash(Long id);
    
    public void deleteCash(Cash cash);
    
    public Long saveCash(Cash cash);

    public void updateCash(Cash cash);

    public PageSupport getCash(CriteriaQuery cq);
}

