/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.core.service;

import java.util.List;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Partner;

/**
 * The Class PartnerService.
 */
public interface PartnerService  {

    public List<Partner> getPartner(String userName);

    public Partner getPartner(Long id);
    
    public void deletePartner(Partner partner);
    
    public Long savePartner(Partner partner);

    public void updatePartner(Partner partner);

    public PageSupport getPartner(CriteriaQuery cq);
}

