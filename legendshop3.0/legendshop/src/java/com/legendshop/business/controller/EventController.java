/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.controller;


import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.legendshop.core.UserManager;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;

import com.legendshop.core.base.BaseController;
import com.legendshop.core.base.AdminController;

import com.legendshop.business.service.EventService;
import com.legendshop.model.entity.Event;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.constant.ParameterEnum;

/**
 * The Class EventController
 *
 */
@Controller
@RequestMapping("/admin/event")
public class EventController extends BaseController implements AdminController<Event, Long> {
    @Autowired
    private EventService eventService;

    @RequestMapping("/query")
    public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, Event event) {
        CriteriaQuery cq = new CriteriaQuery(Event.class, curPageNO);
        cq.setPageSize(PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE, Integer.class));
        cq = hasAllDataFunction(cq, request, StringUtils.trim(event.getUserName()));
        /*
           //TODO add your condition
        */
        cq.add();
        PageSupport ps = eventService.getEvent(cq);
        savePage(ps, request);
        request.setAttribute("event", event);
        return "/event/eventList";
    }

    @RequestMapping(value = "/save")
    public String save(HttpServletRequest request, HttpServletResponse response, Event event) {
        eventService.saveEvent(event);
        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
        return "forward:/admin/event/query.htm";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Long id) {
        Event event = eventService.getEvent(id);
        String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), event.getUserName());
		if(result!=null){
			return result;
		}
		eventService.deleteEvent(event);
        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
        return "forward:/admin/event/query.htm";
    }

    @RequestMapping(value = "/load/{id}")
    public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Long id) {
        Event event = eventService.getEvent(id);
        String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), event.getUserName());
		if(result!=null){
			return result;
		}
        request.setAttribute("#entityClassInstance", event);
        return "/event/event";
    }
    
	@RequestMapping(value = "/load")
	public String load(HttpServletRequest request, HttpServletResponse response) {
		return "/event/event";
	}

    @RequestMapping(value = "/update")
    public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {        
        Event event = eventService.getEvent(id);
		String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), event.getUserName());
		if(result!=null){
			return result;
		}
		request.setAttribute("event", event);
		return "forward:/admin/event/query.htm";
    }

}

