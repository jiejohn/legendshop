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

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.business.common.page.BackPage;
import com.legendshop.business.common.page.FowardPage;
import com.legendshop.business.service.TagService;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.AdminController;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.constant.TagTypeEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SimpleHqlQuery;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.Tag;
import com.legendshop.spi.constants.Constants;

/**
 * The Class TagController
 *
 */
@Controller
@RequestMapping("/admin/tag")
public class TagController extends BaseController implements AdminController<Tag, Long> {
    @Autowired
    private TagService tagService;

    @Override
	@RequestMapping("/query")
    public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, Tag tag) {
    	SimpleHqlQuery hql = new SimpleHqlQuery(curPageNO);
    	hql.fillLikeParameter("name", tag.getName());
    	hql.fillParameter("status", tag.getStatus());
    	hql.hasAllDataFunction(request, tag.getUserName());
    	hql.fillOrder(request, "order by t.createTime desc");
		hql.fillPageSize(request);
    	hql.initSQL("biz.QueryTag", "biz.QueryTagCount");
    	PageSupport ps = tagService.getTag(hql);
    	ps.savePage(request);
        request.setAttribute("tag", tag);
        return PathResolver.getPath(request,response,BackPage.TAG_LIST);
    }

    @Override
	@RequestMapping(value = "/save")
    public String save(HttpServletRequest request, HttpServletResponse response, Tag tag) {
    	tag.setCreateTime(new Date());
    	tag.setType(TagTypeEnum.INDEX.value());
    	tag.setUserId(UserManager.getUserId(request));
    	tag.setUserName(UserManager.getUsername(request));
        tagService.saveTag(tag);
        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
        return PathResolver.getPath(request, response, FowardPage.TAG);
    }

    @Override
	@RequestMapping(value = "/delete/{id}")
    public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Long id) {
        Tag tag = tagService.getTag(id);
        String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), tag.getUserName());
		if(result!=null){
			return result;
		}
		tagService.deleteTag(tag);
        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
        return PathResolver.getPath(request,response,FowardPage.TAG);
        
    }

    @RequestMapping(value = "/load/{id}")
    public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Long id) {
        Tag tag = tagService.getTag(id);
        String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), tag.getUserName());
		if(result!=null){
			return result;
		}
        request.setAttribute("tag", tag);
        return PathResolver.getPath(request,response,BackPage.TAG);
    }
    
	@Override
	@RequestMapping(value = "/load")
	public String load(HttpServletRequest request, HttpServletResponse response) {
		 return PathResolver.getPath(request,response,BackPage.TAG);
	}

    @Override
	@RequestMapping(value = "/update")
    public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {        
        Tag tag = tagService.getTag(id);
		String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), tag.getUserName());
		if(result!=null){
			return result;
		}
		request.setAttribute("tag", tag);
		return PathResolver.getPath(request,response,FowardPage.TAG);
    }

}

