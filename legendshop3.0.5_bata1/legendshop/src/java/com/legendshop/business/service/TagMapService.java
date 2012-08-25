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
import com.legendshop.model.entity.TagMap;

/**
 * The Class TagMapService.
 */
public interface TagMapService  {

    public List<TagMap> getTagMap(String userName);

    public TagMap getTagMap(Long id);
    
    public void deleteTagMap(TagMap tagMap);
    
    public Long saveTagMap(TagMap tagMap);

    public void updateTagMap(TagMap tagMap);

    public PageSupport getTagMap(CriteriaQuery cq);
}

