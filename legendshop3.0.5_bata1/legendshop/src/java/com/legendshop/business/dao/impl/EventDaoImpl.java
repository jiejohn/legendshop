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

import com.legendshop.business.dao.EventDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Event;
/**
 * The Class EventDaoImpl.
 */

public class EventDaoImpl extends BaseDaoImpl implements EventDao {
    private static Logger log = LoggerFactory.getLogger(EventDaoImpl.class);
     
    public List<Event> getEvent(String userName){
   		return findByHQL("from Event where userName = ?", userName);
    }

	public Event getEvent(Long id){
		return get(Event.class, id);
	}
	
    public void deleteEvent(Event event){
    	delete(event);
    }
	
	public Long saveEvent(Event event){
		return (Long)save(event);
	}
	
	public void updateEvent(Event event){
		 update(event);
	}
	
	public PageSupport getEvent(CriteriaQuery cq){
		return find(cq);
	}
	
 }

