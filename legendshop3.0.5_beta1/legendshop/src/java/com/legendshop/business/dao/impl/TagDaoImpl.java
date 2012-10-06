/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao.impl;
 
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.legendshop.business.dao.TagDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SimpleHqlQuery;
import com.legendshop.model.entity.Tag;
/**
 * The Class TagDaoImpl.
 */

public class TagDaoImpl extends BaseDaoImpl implements TagDao {
    private static Logger log = LoggerFactory.getLogger(TagDaoImpl.class);
     
    public List<Tag> getTag(String userName){
   		return findByHQL("from Tag where userName = ?", userName);
    }

	public Tag getTag(Long id){
		return get(Tag.class, id);
	}
	
    public void deleteTag(Tag tag){
    	delete(tag);
    }
	
	public Long saveTag(Tag tag){
		return (Long)save(tag);
	}
	
	public void updateTag(Tag tag){
		 update(tag);
	}
	
	public PageSupport getTag(CriteriaQuery cq){
		return find(cq);
	}

	@Override
	public PageSupport getTag(SimpleHqlQuery hql) {
    	hql.initSQL("biz.QueryTag", "biz.QueryTagCount");
		return find(hql);
	}

	@Override
	public Tag getTag(String name, String userName) {
		List<Tag> result =  findByHQL("from Tag where name = ? and userName = ?", name,userName);
		if(result !=null){
			return result.get(0);
		}
		return null;
	}
	
 }

