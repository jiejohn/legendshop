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

import com.legendshop.business.service.AskService;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.AdminController;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.Ask;

/**
 * The Class AskController
 *
 */
@Controller
@RequestMapping("/admin/ask")
public class AskController extends BaseController implements AdminController<Ask, Long> {
    @Autowired
    private AskService askService;

    @RequestMapping("/query")
    public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, Ask ask) {
        CriteriaQuery cq = new CriteriaQuery(Ask.class, curPageNO);
        cq.setPageSize(PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE, Integer.class));
        cq = hasAllDataFunction(cq, request, StringUtils.trim(ask.getUserName()));
        /*
           //TODO add your condition
        */
        
        PageSupport ps = askService.getAsk(cq);
        savePage(ps, request);
        request.setAttribute("ask", ask);
        return "/ask/askList";
    }

    @RequestMapping(value = "/save")
    public String save(HttpServletRequest request, HttpServletResponse response, Ask ask) {
        askService.saveAsk(ask);
        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
        return "forward:/admin/ask/query.htm";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Long id) {
        Ask ask = askService.getAsk(id);
        String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), ask.getUserName());
		if(result!=null){
			return result;
		}
		askService.deleteAsk(ask);
        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
        return "forward:/admin/ask/query.htm";
    }

    @RequestMapping(value = "/load/{id}")
    public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Long id) {
        Ask ask = askService.getAsk(id);
        String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), ask.getUserName());
		if(result!=null){
			return result;
		}
        request.setAttribute("#entityClassInstance", ask);
        return "/ask/ask";
    }
    
	@RequestMapping(value = "/load")
	public String load(HttpServletRequest request, HttpServletResponse response) {
		return "/ask/ask";
	}

    @RequestMapping(value = "/update")
    public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {        
        Ask ask = askService.getAsk(id);
		String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), ask.getUserName());
		if(result!=null){
			return result;
		}
		request.setAttribute("ask", ask);
		return "forward:/admin/ask/query.htm";
    }

}

