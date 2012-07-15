/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao.impl;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.model.entity.Tag;
import com.legendshop.business.dao.TagDao;
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
	
 }

