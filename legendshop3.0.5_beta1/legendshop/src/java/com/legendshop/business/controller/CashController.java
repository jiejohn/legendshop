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

import com.legendshop.business.service.CashService;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.AdminController;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.Cash;

/**
 * The Class CashController
 *
 */
@Controller
@RequestMapping("/admin/cash")
public class CashController extends BaseController implements AdminController<Cash, Long> {
    @Autowired
    private CashService cashService;

    @RequestMapping("/query")
    public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, Cash cash) {
        CriteriaQuery cq = new CriteriaQuery(Cash.class, curPageNO);
        cq.setPageSize(PropertiesUtil.getObject(SysParameterEnum.PAGE_SIZE, Integer.class));
        cq = hasAllDataFunction(cq, request, StringUtils.trim(cash.getUserName()));
        /*
           //TODO add your condition
        */
        
        PageSupport ps = cashService.getCash(cq);
        savePage(ps, request);
        request.setAttribute("cash", cash);
        return "/cash/cashList";
    }

    @RequestMapping(value = "/save")
    public String save(HttpServletRequest request, HttpServletResponse response, Cash cash) {
        cashService.saveCash(cash);
        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
        return "forward:/admin/cash/query.htm";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Long id) {
        Cash cash = cashService.getCash(id);
        String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), cash.getUserName());
		if(result!=null){
			return result;
		}
		cashService.deleteCash(cash);
        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
        return "forward:/admin/cash/query.htm";
    }

    @RequestMapping(value = "/load/{id}")
    public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Long id) {
        Cash cash = cashService.getCash(id);
        String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), cash.getUserName());
		if(result!=null){
			return result;
		}
        request.setAttribute("#entityClassInstance", cash);
        return "/cash/cash";
    }
    
	@RequestMapping(value = "/load")
	public String load(HttpServletRequest request, HttpServletResponse response) {
		return "/cash/cash";
	}

    @RequestMapping(value = "/update")
    public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {        
        Cash cash = cashService.getCash(id);
		String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), cash.getUserName());
		if(result!=null){
			return result;
		}
		request.setAttribute("cash", cash);
		return "forward:/admin/cash/query.htm";
    }

}

