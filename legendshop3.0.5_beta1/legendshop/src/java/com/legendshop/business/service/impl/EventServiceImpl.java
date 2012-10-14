/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import java.util.List;

import com.legendshop.core.dao.EventDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.newservice.EventService;
import com.legendshop.model.entity.UserEvent;
import com.legendshop.util.AppUtils;

/**
 * The Class EventServiceImpl.
 */
public class EventServiceImpl  implements EventService{
    private EventDao eventDao;

    public void setEventDao(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    public List<UserEvent> getEvent(String userName) {
        return eventDao.getEvent(userName);
    }

    public UserEvent getEvent(Long id) {
        return eventDao.getEvent(id);
    }

    public void deleteEvent(UserEvent userEvent) {
        eventDao.deleteEvent(userEvent);
    }

    public Long saveEvent(UserEvent userEvent) {
        if (!AppUtils.isBlank(userEvent.getEventId())) {
            updateEvent(userEvent);
            return userEvent.getEventId();
        }
        return (Long) eventDao.save(userEvent);
    }

    public void updateEvent(UserEvent userEvent) {
        eventDao.updateEvent(userEvent);
    }

    public PageSupport getEvent(CriteriaQuery cq) {
        return eventDao.find(cq);
    }
}

