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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legendshop.business.service.TagMapService;
import com.legendshop.core.base.AdminController;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.TagMap;

/**
 * The Class TagMapController
 *
 */
@Controller
@RequestMapping("/admin/tagMap")
public class TagMapController extends BaseController implements AdminController<TagMap, Long> {
    @Autowired
    private TagMapService tagMapService;

    @Override
	@RequestMapping("/query")
    public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, TagMap tagMap) {
        CriteriaQuery cq = new CriteriaQuery(TagMap.class, curPageNO);
        cq.setPageSize(PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE, Integer.class));
       // cq = hasAllDataFunction(cq, request, StringUtils.trim(tagMap.getUserName()));
        /*
           //TODO add your condition
        */
        
        PageSupport ps = tagMapService.getTagMap(cq);
        savePage(ps, request);
        request.setAttribute("tagMap", tagMap);
        return "/tagMap/tagMapList";
    }

    @Override
	@RequestMapping(value = "/save")
    public String save(HttpServletRequest request, HttpServletResponse response, TagMap tagMap) {
        tagMapService.saveTagMap(tagMap);
        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
        return "forward:/admin/tagMap/query.htm";
    }

    @Override
	@RequestMapping(value = "/delete/{id}")
    public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Long id) {
        TagMap tagMap = tagMapService.getTagMap(id);
       /// String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), tagMap.getUserName());
//		if(result!=null){
//			return result;
//		}
		tagMapService.deleteTagMap(tagMap);
        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
        return "forward:/admin/tagMap/query.htm";
    }

    @RequestMapping(value = "/load/{id}")
    public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Long id) {
        TagMap tagMap = tagMapService.getTagMap(id);
//        String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), tagMap.getUserName());
//		if(result!=null){
//			return result;
//		}
        request.setAttribute("#entityClassInstance", tagMap);
        return "/tagMap/tagMap";
    }
    
	@Override
	@RequestMapping(value = "/load")
	public String load(HttpServletRequest request, HttpServletResponse response) {
		return "/tagMap/tagMap";
	}

    @Override
	@RequestMapping(value = "/update")
    public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {        
        TagMap tagMap = tagMapService.getTagMap(id);
//		String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), tagMap.getUserName());
//		if(result!=null){
//			return result;
//		}
		request.setAttribute("tagMap", tagMap);
		return "forward:/admin/tagMap/query.htm";
    }

}

