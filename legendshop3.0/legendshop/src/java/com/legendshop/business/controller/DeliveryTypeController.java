/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.controller;


import java.util.Date;
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
import com.legendshop.business.service.DeliveryCorpService;
import com.legendshop.business.service.DeliveryTypeService;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.AdminController;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.DeliveryCorp;
import com.legendshop.model.entity.DeliveryType;
import com.legendshop.util.AppUtils;

/**
 * The Class DeliveryTypeController
 *
 */
@Controller
@RequestMapping("/admin/deliveryType")
public class DeliveryTypeController extends BaseController implements AdminController<DeliveryType, Long> {
    @Autowired
    private DeliveryTypeService deliveryTypeService;

    @Override
	@RequestMapping("/query")
    public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, DeliveryType deliveryType) {
        CriteriaQuery cq = new CriteriaQuery(DeliveryType.class, curPageNO);
        cq.setPageSize(PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE, Integer.class));
        cq = hasAllDataFunction(cq, request, StringUtils.trim(deliveryType.getUserName()));

		if (!AppUtils.isBlank(deliveryType.getName())) {
			cq.like("name", "%" + deliveryType.getName() + "%");
		}
        /*
           //TODO add your condition
        */
        cq.add();
        PageSupport ps = deliveryTypeService.getDeliveryType(cq);
        savePage(ps, request);
        request.setAttribute("deliveryType", deliveryType);
        return PathResolver.getPath(request, BackPage.DELIVERYTYPE_LIST_PAGE);
    }

    @Override
	@RequestMapping(value = "/save")
    public String save(HttpServletRequest request, HttpServletResponse response, DeliveryType deliveryType) {
    	DeliveryType dt=null;
    	if (AppUtils.isNotBlank(deliveryType.getDvyTypeId())) {
    		dt=deliveryTypeService.getDeliveryType(deliveryType.getDvyTypeId());
    		dt.setName(deliveryType.getName());
    		dt.setDvyId(deliveryType.getDvyId());
    		dt.setNotes(deliveryType.getNotes());
    	}else{
    		dt=deliveryType;
    		dt.setCreateTime(new Date());
    	}
    	System.out.println("---------"+dt.getDvyId());
    	dt.setUserId(UserManager.getUserId(request));
    	dt.setUserName(UserManager.getUsername(request));
    	dt.setModifyTime(new Date());
    	
        deliveryTypeService.saveDeliveryType(dt);
        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
        return PathResolver.getPath(request, FowardPage.DELIVERYTYPE_LIST_QUERY);
    }
    

    @Override
	@RequestMapping(value = "/delete/{id}")
    public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Long id) {
        DeliveryType deliveryType = deliveryTypeService.getDeliveryType(id);
        String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), deliveryType.getUserName());
		if(result!=null){
			return result;
		}
		deliveryTypeService.deleteDeliveryType(deliveryType);
        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
        return PathResolver.getPath(request, FowardPage.DELIVERYTYPE_LIST_QUERY);
    }

    @RequestMapping(value = "/load/{id}")
    public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Long id) {
        DeliveryType deliveryType = deliveryTypeService.getDeliveryType(id);
        String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), deliveryType.getUserName());
		if(result!=null){
			return result;
		}
        request.setAttribute("deliveryType", deliveryType);
        return PathResolver.getPath(request, BackPage.DELIVERYTYPE_EDIT_PAGE);
    }
    
	@Override
	@RequestMapping(value = "/load")
	public String load(HttpServletRequest request, HttpServletResponse response) {
		return PathResolver.getPath(request, BackPage.DELIVERYTYPE_EDIT_PAGE);
	}

    @Override
	@RequestMapping(value = "/update")
    public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {        
        DeliveryType deliveryType = deliveryTypeService.getDeliveryType(id);
		String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), deliveryType.getUserName());
		if(result!=null){
			return result;
		}
		request.setAttribute("deliveryType", deliveryType);
		return PathResolver.getPath(request, FowardPage.DELIVERYTYPE_LIST_QUERY);
    }

}

