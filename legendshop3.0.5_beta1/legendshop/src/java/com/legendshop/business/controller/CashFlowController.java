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

import com.legendshop.business.service.CashFlowService;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.AdminController;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.CashFlow;

/**
 * The Class CashFlowController
 *
 */
@Controller
@RequestMapping("/admin/cashFlow")
public class CashFlowController extends BaseController implements AdminController<CashFlow, Long> {
    @Autowired
    private CashFlowService cashFlowService;

    @RequestMapping("/query")
    public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, CashFlow cashFlow) {
        CriteriaQuery cq = new CriteriaQuery(CashFlow.class, curPageNO);
        cq.setPageSize(PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE, Integer.class));
        cq = hasAllDataFunction(cq, request, StringUtils.trim(cashFlow.getUserName()));
        /*
           //TODO add your condition
        */
        
        PageSupport ps = cashFlowService.getCashFlow(cq);
        savePage(ps, request);
        request.setAttribute("cashFlow", cashFlow);
        return "/cashFlow/cashFlowList";
    }

    @RequestMapping(value = "/save")
    public String save(HttpServletRequest request, HttpServletResponse response, CashFlow cashFlow) {
        cashFlowService.saveCashFlow(cashFlow);
        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
        return "forward:/admin/cashFlow/query.htm";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Long id) {
        CashFlow cashFlow = cashFlowService.getCashFlow(id);
        String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), cashFlow.getUserName());
		if(result!=null){
			return result;
		}
		cashFlowService.deleteCashFlow(cashFlow);
        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
        return "forward:/admin/cashFlow/query.htm";
    }

    @RequestMapping(value = "/load/{id}")
    public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Long id) {
        CashFlow cashFlow = cashFlowService.getCashFlow(id);
        String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), cashFlow.getUserName());
		if(result!=null){
			return result;
		}
        request.setAttribute("#entityClassInstance", cashFlow);
        return "/cashFlow/cashFlow";
    }
    
	@RequestMapping(value = "/load")
	public String load(HttpServletRequest request, HttpServletResponse response) {
		return "/cashFlow/cashFlow";
	}

    @RequestMapping(value = "/update")
    public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {        
        CashFlow cashFlow = cashFlowService.getCashFlow(id);
		String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), cashFlow.getUserName());
		if(result!=null){
			return result;
		}
		request.setAttribute("cashFlow", cashFlow);
		return "forward:/admin/cashFlow/query.htm";
    }

}

