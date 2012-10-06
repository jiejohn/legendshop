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

    /**
     * Gets the tag.
     *
     * @param hql the hql
     * @return the tag
     */
    public PageSupport getTag(SimpleHqlQuery hql);

    /**
     * Gets the tag.
     *
     * @param id the id
     * @return the tag
     */
    public Tag getTag(Long id);
    
    /**
     * Delete tag.
     *
     * @param tag the tag
     */
    public void deleteTag(Tag tag);
    
    /**
     * Save tag.
     *
     * @param tag the tag
     * @return the long
     */
    public Long saveTag(Tag tag);

    /**
     * Update tag.
     *
     * @param tag the tag
     */
    public void updateTag(Tag tag);

    /**
     * Gets the tag.
     *
     * @param cq the cq
     * @return the tag
     */
    public PageSupport getTag(CriteriaQuery cq);

	/**
	 * Gets the tag.
	 *
	 * @param name the name
	 * @param userName the user name
	 * @return the tag
	 */
	public Tag getTag(String name, String userName);
}

