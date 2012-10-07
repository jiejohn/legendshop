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

import com.legendshop.business.service.MessageService;
import com.legendshop.model.entity.Message;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.constant.ParameterEnum;

/**
 * The Class MessageController
 *
 */
@Controller
@RequestMapping("/message")
public class MessageController extends BaseController implements AdminController<Message, String> {
    @Autowired
    private MessageService messageService;

    @RequestMapping("/query")
    public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, Message message) {
        CriteriaQuery cq = new CriteriaQuery(Message.class, curPageNO);
        cq.setPageSize(PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE, Integer.class));
        cq = hasAllDataFunction(cq, request, StringUtils.trim(message.getSender()));
        /*
           //TODO add your condition
        */
        cq.add();
        PageSupport ps = messageService.getMessage(cq);
        savePage(ps, request);
        request.setAttribute("message", message);
        return "/message/messageList";
    }

    @RequestMapping(value = "/save")
    public String save(HttpServletRequest request, HttpServletResponse response, Message message) {
        messageService.saveMessage(message);
        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
        return "forward:/admin/message/query.htm";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable
    String id) {
        Message message = messageService.getMessage(id);
        String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), message.getSender());
		if(result!=null){
			return result;
		}
		messageService.deleteMessage(message);
        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
        return "forward:/admin/message/query.htm";
    }

    @RequestMapping(value = "/load/{id}")
    public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable
    String id) {
        Message message = messageService.getMessage(id);
        String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), message.getSender());
		if(result!=null){
			return result;
		}
        request.setAttribute("#entityClassInstance", message);
        return "/message/message";
    }
    
	@RequestMapping(value = "/load")
	public String load(HttpServletRequest request, HttpServletResponse response) {
		return "/message/message";
	}

    @RequestMapping(value = "/update")
    public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable String id) {        
        Message message = messageService.getMessage(id);
		String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), message.getSender());
		if(result!=null){
			return result;
		}
		request.setAttribute("message", message);
		return "forward:/admin/message/query.htm";
    }

}

