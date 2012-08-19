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
import com.legendshop.model.entity.Event;

/**
 * The Class EventService.
 */
public interface EventService  {

    public List<Event> getEvent(String userName);

    public Event getEvent(Long id);
    
    public void deleteEvent(Event event);
    
    public Long saveEvent(Event event);

    public void updateEvent(Event event);

    public PageSupport getEvent(CriteriaQuery cq);
}

