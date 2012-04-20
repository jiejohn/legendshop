/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import java.util.List;

import com.legendshop.business.dao.EventDao;
import com.legendshop.business.service.EventService;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Event;
import com.legendshop.util.AppUtils;

/**
 * The Class EventServiceImpl.
 */
public class EventServiceImpl  implements EventService{
    private EventDao eventDao;

    public void setEventDao(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    public List<Event> getEvent(String userName) {
        return eventDao.getEvent(userName);
    }

    public Event getEvent(Long id) {
        return eventDao.getEvent(id);
    }

    public void deleteEvent(Event event) {
        eventDao.deleteEvent(event);
    }

    public Long saveEvent(Event event) {
        if (!AppUtils.isBlank(event.getEventId())) {
            updateEvent(event);
            return event.getEventId();
        }
        return (Long) eventDao.save(event);
    }

    public void updateEvent(Event event) {
        eventDao.updateEvent(event);
    }

    public PageSupport getEvent(CriteriaQuery cq) {
        return eventDao.find(cq);
    }
}

