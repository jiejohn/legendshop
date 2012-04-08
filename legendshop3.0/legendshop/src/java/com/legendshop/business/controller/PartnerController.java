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
import com.legendshop.business.service.PartnerService;
import com.legendshop.business.service.ShopDetailService;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.AdminController;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.exception.PermissionException;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.Partner;
import com.legendshop.model.entity.ShopDetail;
import com.legendshop.util.AppUtils;

/**
 * The Class PartnerController
 *
 */
@Controller
@RequestMapping("/admin/partner")
public class PartnerController extends BaseController implements AdminController<Partner, Long> {
    @Autowired
    private PartnerService partnerService;
    
    @Autowired
    private ShopDetailService shopDetailService;

    @RequestMapping("/query")
    public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, Partner partner) {
        CriteriaQuery cq = new CriteriaQuery(Partner.class, curPageNO);
        cq.setPageSize(PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE, Integer.class));
        cq = hasAllDataFunction(cq, request, StringUtils.trim(partner.getUserName()));
        /*
           //TODO add your condition
        */
        cq.add();
        PageSupport ps = partnerService.getPartner(cq);
        savePage(ps, request);
        request.setAttribute("partner", partner);
        return PathResolver.getPath(request, BackPage.PARTNER_LIST_PAGE);
    }

    @RequestMapping(value = "/save")
    public String save(HttpServletRequest request, HttpServletResponse response, Partner partner) {
    	Partner p=null;
    	
    	if (AppUtils.isNotBlank(partner.getPartnerId())) {
    		p=partnerService.getPartner(partner.getPartnerId());
    		//set some value
    	}else{
    		p=partner;
    		p.setCreateTime(new Date());
    		p.setCommentBad(0);
    		p.setCommentGood(0);
    		p.setCommentNone(0);
    	}
    	

    	p.setUserId(UserManager.getUserId(request));
    	p.setUserName(UserManager.getUsername(request));
    	p.setModifyTime(new Date());
    	
    	ShopDetail shopDetail=shopDetailService.getShopDetailByUserId(UserManager.getUserId(request));
    	
    	if(shopDetail==null){
    		throw new PermissionException("Can't find shopDetail by userId!",EntityCodes.SYSTEM);
    	}
    	
    	p.setShopId(shopDetail.getShopId());
    	
    	
    	
    	
        partnerService.savePartner(p);
        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
        return PathResolver.getPath(request, FowardPage.PARTNER_LIST_QUERY);
    }


    @RequestMapping(value = "/delete/{id}")
    public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Long id) {
        Partner partner = partnerService.getPartner(id);
        String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), partner.getUserName());
		if(result!=null){
			return result;
		}
		partnerService.deletePartner(partner);
        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
        return PathResolver.getPath(request, FowardPage.PARTNER_LIST_QUERY);
    }

    @RequestMapping(value = "/load/{id}")
    public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Long id) {
        Partner partner = partnerService.getPartner(id);
        String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), partner.getUserName());
		if(result!=null){
			return result;
		}
        request.setAttribute("partner", partner);
        return PathResolver.getPath(request, BackPage.PARTNER_EDIT_PAGE);
    }
    
	@RequestMapping(value = "/load")
	public String load(HttpServletRequest request, HttpServletResponse response) {
        return PathResolver.getPath(request, BackPage.PARTNER_EDIT_PAGE);
	}

    @RequestMapping(value = "/update")
    public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {        
        Partner partner = partnerService.getPartner(id);
		String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), partner.getUserName());
		if(result!=null){
			return result;
		}
		request.setAttribute("partner", partner);
        return PathResolver.getPath(request, FowardPage.PARTNER_LIST_QUERY);
    }

}

