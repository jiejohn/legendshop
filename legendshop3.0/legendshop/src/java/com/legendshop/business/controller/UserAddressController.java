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

import com.legendshop.business.service.UserAddressService;
import com.legendshop.model.entity.UserAddress;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.constant.ParameterEnum;

/**
 * The Class UserAddressController
 *
 */
@Controller
@RequestMapping("/admin/userAddress")
public class UserAddressController extends BaseController implements AdminController<UserAddress, Long> {
    @Autowired
    private UserAddressService userAddressService;

    @RequestMapping("/query")
    public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, UserAddress userAddress) {
        CriteriaQuery cq = new CriteriaQuery(UserAddress.class, curPageNO);
        cq.setPageSize(PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE, Integer.class));
        cq = hasAllDataFunction(cq, request, StringUtils.trim(userAddress.getUserName()));
        /*
           //TODO add your condition
        */
        cq.add();
        PageSupport ps = userAddressService.getUserAddress(cq);
        savePage(ps, request);
        request.setAttribute("userAddress", userAddress);
        return "/userAddress/userAddressList";
    }

    @RequestMapping(value = "/save")
    public String save(HttpServletRequest request, HttpServletResponse response, UserAddress userAddress) {
        userAddressService.saveUserAddress(userAddress);
        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
        return "forward:/admin/userAddress/query.htm";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Long id) {
        UserAddress userAddress = userAddressService.getUserAddress(id);
        String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), userAddress.getUserName());
		if(result!=null){
			return result;
		}
		userAddressService.deleteUserAddress(userAddress);
        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
        return "forward:/admin/userAddress/query.htm";
    }

    @RequestMapping(value = "/load/{id}")
    public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Long id) {
        UserAddress userAddress = userAddressService.getUserAddress(id);
        String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), userAddress.getUserName());
		if(result!=null){
			return result;
		}
        request.setAttribute("#entityClassInstance", userAddress);
        return "/userAddress/userAddress";
    }
    
	@RequestMapping(value = "/load")
	public String load(HttpServletRequest request, HttpServletResponse response) {
		return "/userAddress/userAddress";
	}

    @RequestMapping(value = "/update")
    public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {        
        UserAddress userAddress = userAddressService.getUserAddress(id);
		String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), userAddress.getUserName());
		if(result!=null){
			return result;
		}
		request.setAttribute("userAddress", userAddress);
		return "forward:/admin/userAddress/query.htm";
    }

}

