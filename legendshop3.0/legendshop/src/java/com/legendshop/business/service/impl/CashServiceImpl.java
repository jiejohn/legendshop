/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import java.util.List;

import com.legendshop.business.dao.CashDao;
import com.legendshop.business.service.CashService;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Cash;
import com.legendshop.util.AppUtils;

/**
 * The Class CashServiceImpl.
 */
public class CashServiceImpl  implements CashService{
    private CashDao cashDao;

    public void setCashDao(CashDao cashDao) {
        this.cashDao = cashDao;
    }

    public List<Cash> getCash(String userName) {
        return cashDao.getCash(userName);
    }

    public Cash getCash(Long id) {
        return cashDao.getCash(id);
    }

    public void deleteCash(Cash cash) {
        cashDao.deleteCash(cash);
    }

    public Long saveCash(Cash cash) {
        if (!AppUtils.isBlank(cash.getCashId())) {
            updateCash(cash);
            return cash.getCashId();
        }
        return (Long) cashDao.save(cash);
    }

    public void updateCash(Cash cash) {
        cashDao.updateCash(cash);
    }

    public PageSupport getCash(CriteriaQuery cq) {
        return cashDao.find(cq);
    }
}

