/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SimpleHqlQuery;
import com.legendshop.model.entity.Tag;

/**
 * The Class TagService.
 */
public interface TagService  {

    public PageSupport getTag(SimpleHqlQuery hql);

    public Tag getTag(Long id);
    
    public void deleteTag(Tag tag);
    
    public Long saveTag(Tag tag);

    public void updateTag(Tag tag);

    public PageSupport getTag(CriteriaQuery cq);
}

