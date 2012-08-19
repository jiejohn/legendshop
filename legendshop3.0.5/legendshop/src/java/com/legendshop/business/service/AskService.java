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
import com.legendshop.model.entity.Ask;

/**
 * The Class AskService.
 */
public interface AskService  {

    public List<Ask> getAsk(String userName);

    public Ask getAsk(Long id);
    
    public void deleteAsk(Ask ask);
    
    public Long saveAsk(Ask ask);

    public void updateAsk(Ask ask);

    public PageSupport getAsk(CriteriaQuery cq);
}

