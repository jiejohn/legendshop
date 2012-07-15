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
import com.legendshop.business.dao.TagMapDao;
import com.legendshop.model.entity.TagMap;
import com.legendshop.business.service.TagMapService;

/**
 * The Class TagMapServiceImpl.
 */
public class TagMapServiceImpl  implements TagMapService{
    private TagMapDao tagMapDao;

    public void setTagMapDao(TagMapDao tagMapDao) {
        this.tagMapDao = tagMapDao;
    }

    public List<TagMap> getTagMap(String userName) {
        return tagMapDao.getTagMap(userName);
    }

    public TagMap getTagMap(Long id) {
        return tagMapDao.getTagMap(id);
    }

    public void deleteTagMap(TagMap tagMap) {
        tagMapDao.deleteTagMap(tagMap);
    }

    public Long saveTagMap(TagMap tagMap) {
        if (!AppUtils.isBlank(tagMap.getTagMapId())) {
            updateTagMap(tagMap);
            return tagMap.getTagMapId();
        }
        return (Long) tagMapDao.save(tagMap);
    }

    public void updateTagMap(TagMap tagMap) {
        tagMapDao.updateTagMap(tagMap);
    }

    public PageSupport getTagMap(CriteriaQuery cq) {
        return tagMapDao.find(cq);
    }
}

