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
import com.legendshop.model.entity.Parnter;

/**
 * The Class ParnterService.
 */
public interface ParnterService  {

    public List<Parnter> getParnter(String userName);

    public Parnter getParnter(Long id);
    
    public void deleteParnter(Parnter parnter);
    
    public Long saveParnter(Parnter parnter);

    public void updateParnter(Parnter parnter);

    public PageSupport getParnter(CriteriaQuery cq);
}

