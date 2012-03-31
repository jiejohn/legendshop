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
import com.legendshop.business.dao.CashFlowDao;
import com.legendshop.model.entity.CashFlow;
import com.legendshop.business.service.CashFlowService;

/**
 * The Class CashFlowServiceImpl.
 */
public class CashFlowServiceImpl  implements CashFlowService{
    private CashFlowDao cashFlowDao;

    public void setCashFlowDao(CashFlowDao cashFlowDao) {
        this.cashFlowDao = cashFlowDao;
    }

    public List<CashFlow> getCashFlow(String userName) {
        return cashFlowDao.getCashFlow(userName);
    }

    public CashFlow getCashFlow(Long id) {
        return cashFlowDao.getCashFlow(id);
    }

    public void deleteCashFlow(CashFlow cashFlow) {
        cashFlowDao.deleteCashFlow(cashFlow);
    }

    public Long saveCashFlow(CashFlow cashFlow) {
        if (!AppUtils.isBlank(cashFlow.getFlowId())) {
            updateCashFlow(cashFlow);
            return cashFlow.getFlowId();
        }
        return (Long) cashFlowDao.save(cashFlow);
    }

    public void updateCashFlow(CashFlow cashFlow) {
        cashFlowDao.updateCashFlow(cashFlow);
    }

    public PageSupport getCashFlow(CriteriaQuery cq) {
        return cashFlowDao.find(cq);
    }
}

