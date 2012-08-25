/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import java.util.List;

import com.legendshop.business.dao.TagDao;
import com.legendshop.business.service.TagService;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Tag;
import com.legendshop.util.AppUtils;

/**
 * The Class TagServiceImpl.
 */
public class TagServiceImpl  implements TagService{
    private TagDao tagDao;

    public void setTagDao(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    public List<Tag> getTag(String userName) {
        return tagDao.getTag(userName);
    }

    public Tag getTag(Long id) {
        return tagDao.getTag(id);
    }

    public void deleteTag(Tag tag) {
        tagDao.deleteTag(tag);
    }

    public Long saveTag(Tag tag) {
        if (!AppUtils.isBlank(tag.getTagId())) {
            updateTag(tag);
            return tag.getTagId();
        }
        return (Long) tagDao.save(tag);
    }

    public void updateTag(Tag tag) {
        tagDao.updateTag(tag);
    }

    public PageSupport getTag(CriteriaQuery cq) {
        return tagDao.find(cq);
    }
}

