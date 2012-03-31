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
import com.legendshop.business.dao.ParnterDao;
import com.legendshop.model.entity.Parnter;
import com.legendshop.business.service.ParnterService;

/**
 * The Class ParnterServiceImpl.
 */
public class ParnterServiceImpl  implements ParnterService{
    private ParnterDao parnterDao;

    public void setParnterDao(ParnterDao parnterDao) {
        this.parnterDao = parnterDao;
    }

    public List<Parnter> getParnter(String userName) {
        return parnterDao.getParnter(userName);
    }

    public Parnter getParnter(Long id) {
        return parnterDao.getParnter(id);
    }

    public void deleteParnter(Parnter parnter) {
        parnterDao.deleteParnter(parnter);
    }

    public Long saveParnter(Parnter parnter) {
        if (!AppUtils.isBlank(parnter.getPartnerId())) {
            updateParnter(parnter);
            return parnter.getPartnerId();
        }
        return (Long) parnterDao.save(parnter);
    }

    public void updateParnter(Parnter parnter) {
        parnterDao.updateParnter(parnter);
    }

    public PageSupport getParnter(CriteriaQuery cq) {
        return parnterDao.find(cq);
    }
}

