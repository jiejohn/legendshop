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
import com.legendshop.model.entity.TagMap;

/**
 * The Class TagMapDao.
 */

public interface TagMapDao extends BaseDao {
     
    public abstract List<TagMap> getTagMap(String shopName);

	public abstract TagMap getTagMap(Long id);
	
    public abstract void deleteTagMap(TagMap tagMap);
	
	public abstract Long saveTagMap(TagMap tagMap);
	
	public abstract void updateTagMap(TagMap tagMap);
	
	public abstract PageSupport getTagMap(CriteriaQuery cq);
	
 }

