/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao;
 
import java.util.List;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Tag;

/**
 * The Class TagDao.
 */

public interface TagDao extends BaseDao {
     
    /**
	 * Gets the tag.
	 * 
	 * @param shopName
	 *            the shop name
	 * @return the tag
	 */
    public abstract List<Tag> getTag(String shopName);

	/**
	 * Gets the tag.
	 * 
	 * @param id
	 *            the id
	 * @return the tag
	 */
	public abstract Tag getTag(Long id);
	
    /**
	 * Delete tag.
	 * 
	 * @param tag
	 *            the tag
	 */
    public abstract void deleteTag(Tag tag);
	
	/**
	 * Save tag.
	 * 
	 * @param tag
	 *            the tag
	 * @return the long
	 */
	public abstract Long saveTag(Tag tag);
	
	/**
	 * Update tag.
	 * 
	 * @param tag
	 *            the tag
	 */
	public abstract void updateTag(Tag tag);
	
	/**
	 * Gets the tag.
	 * 
	 * @param cq
	 *            the cq
	 * @return the tag
	 */
	public abstract PageSupport getTag(CriteriaQuery cq);
	
 }

