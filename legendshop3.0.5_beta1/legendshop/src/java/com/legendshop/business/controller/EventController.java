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

import com.legendshop.business.common.page.BackPage;
import com.legendshop.business.common.page.FowardPage;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.AdminController;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.newservice.EventService;
import com.legendshop.model.entity.UserEvent;
import com.legendshop.util.AppUtils;

/**
 * The Class EventController
 *
 */
@Controller
@RequestMapping("/admin/event")
public class EventController extends BaseController implements AdminController<UserEvent, Long> {
    @Autowired
    private EventService eventService;

    @RequestMapping("/query")
    public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, UserEvent userEvent) {
        CriteriaQuery cq = new CriteriaQuery(UserEvent.class, curPageNO);
        cq.setPageSize(PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE, Integer.class));

		if (!AppUtils.isBlank(userEvent.getUserName())) {
			cq.like("userName", "%" + StringUtils.trim(userEvent.getUserName()) + "%");
		}
		if (!AppUtils.isBlank(userEvent.getModifyUser())) {
			cq.eq("modifyUser", userEvent.getModifyUser());
		}
		cq.ge("createTime", userEvent.getStartTime());
		cq.le("createTime", userEvent.getEndTime());
		cq.addOrder("desc", "createTime");

        PageSupport ps = eventService.getEvent(cq);
        savePage(ps, request);
        request.setAttribute("event", userEvent);
        return  PathResolver.getPath(request, response, BackPage.EVENT_LIST_PAGE);
    }

    @RequestMapping(value = "/save")
    public String save(HttpServletRequest request, HttpServletResponse response, UserEvent userEvent) {
        eventService.saveEvent(userEvent);
        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
        return PathResolver.getPath(request, response, FowardPage.EVENT_QUERY);
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Long id) {
        UserEvent userEvent = eventService.getEvent(id);
        String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), userEvent.getUserName());
		if(result!=null){
			return result;
		}
		eventService.deleteEvent(userEvent);
        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
        return PathResolver.getPath(request, response, FowardPage.EVENT_QUERY);
    }

    @RequestMapping(value = "/load/{id}")
    public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Long id) {
        UserEvent userEvent = eventService.getEvent(id);
        String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), userEvent.getUserName());
		if(result!=null){
			return result;
		}
        request.setAttribute("event", userEvent);
        return PathResolver.getPath(request, response, BackPage.EVENT_EDIT_PAGE);
    }
    
	@RequestMapping(value = "/load")
	public String load(HttpServletRequest request, HttpServletResponse response) {
        return PathResolver.getPath(request, response, BackPage.EVENT_EDIT_PAGE);
	}

    @RequestMapping(value = "/update")
    public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {        
        UserEvent userEvent = eventService.getEvent(id);
		String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), userEvent.getUserName());
		if(result!=null){
			return result;
		}
		request.setAttribute("event", userEvent);
		  return PathResolver.getPath(request, response, FowardPage.EVENT_QUERY);
    }

}

