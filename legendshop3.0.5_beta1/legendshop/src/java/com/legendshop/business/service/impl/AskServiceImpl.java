/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import java.util.List;

import com.legendshop.business.dao.AskDao;
import com.legendshop.business.service.AskService;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Ask;
import com.legendshop.util.AppUtils;

/**
 * The Class AskServiceImpl.
 */
public class AskServiceImpl  implements AskService{
    private AskDao askDao;

    public void setAskDao(AskDao askDao) {
        this.askDao = askDao;
    }

    public List<Ask> getAsk(String userName) {
        return askDao.getAsk(userName);
    }

    public Ask getAsk(Long id) {
        return askDao.getAsk(id);
    }

    public void deleteAsk(Ask ask) {
        askDao.deleteAsk(ask);
    }

    public Long saveAsk(Ask ask) {
        if (!AppUtils.isBlank(ask.getAskId())) {
            updateAsk(ask);
            return ask.getAskId();
        }
        return (Long) askDao.save(ask);
    }

    public void updateAsk(Ask ask) {
        askDao.updateAsk(ask);
    }

    public PageSupport getAsk(CriteriaQuery cq) {
        return askDao.find(cq);
    }
}

