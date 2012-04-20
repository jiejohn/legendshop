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
import com.legendshop.model.entity.Event;

/**
 * The Class EventDao.
 */

public interface EventDao extends BaseDao {
     
    public abstract List<Event> getEvent(String shopName);

	public abstract Event getEvent(Long id);
	
    public abstract void deleteEvent(Event event);
	
	public abstract Long saveEvent(Event event);
	
	public abstract void updateEvent(Event event);
	
	public abstract PageSupport getEvent(CriteriaQuery cq);
	
 }

