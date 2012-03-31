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

import com.legendshop.business.service.ParnterService;
import com.legendshop.model.entity.Parnter;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.constant.ParameterEnum;

/**
 * The Class ParnterController
 *
 */
@Controller
@RequestMapping("/admin/parnter")
public class ParnterController extends BaseController implements AdminController<Parnter, Long> {
    @Autowired
    private ParnterService parnterService;

    @RequestMapping("/query")
    public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, Parnter parnter) {
        CriteriaQuery cq = new CriteriaQuery(Parnter.class, curPageNO);
        cq.setPageSize(PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE, Integer.class));
        cq = hasAllDataFunction(cq, request, StringUtils.trim(parnter.getUserName()));
        /*
           //TODO add your condition
        */
        cq.add();
        PageSupport ps = parnterService.getParnter(cq);
        savePage(ps, request);
        request.setAttribute("parnter", parnter);
        return "/parnter/parnterList";
    }

    @RequestMapping(value = "/save")
    public String save(HttpServletRequest request, HttpServletResponse response, Parnter parnter) {
        parnterService.saveParnter(parnter);
        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
        return "forward:/admin/parnter/query.htm";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Long id) {
        Parnter parnter = parnterService.getParnter(id);
        String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), parnter.getUserName());
		if(result!=null){
			return result;
		}
		parnterService.deleteParnter(parnter);
        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
        return "forward:/admin/parnter/query.htm";
    }

    @RequestMapping(value = "/load/{id}")
    public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Long id) {
        Parnter parnter = parnterService.getParnter(id);
        String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), parnter.getUserName());
		if(result!=null){
			return result;
		}
        request.setAttribute("#entityClassInstance", parnter);
        return "/parnter/parnter";
    }
    
	@RequestMapping(value = "/load")
	public String load(HttpServletRequest request, HttpServletResponse response) {
		return "/parnter/parnter";
	}

    @RequestMapping(value = "/update")
    public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {        
        Parnter parnter = parnterService.getParnter(id);
		String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), parnter.getUserName());
		if(result!=null){
			return result;
		}
		request.setAttribute("parnter", parnter);
		return "forward:/admin/parnter/query.htm";
    }

}

