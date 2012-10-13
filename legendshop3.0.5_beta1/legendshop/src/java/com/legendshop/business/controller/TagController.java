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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legendshop.business.common.page.BackPage;
import com.legendshop.business.common.page.FowardPage;
import com.legendshop.business.service.TagService;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.AdminController;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.constant.TagTypeEnum;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SimpleHqlQuery;
import com.legendshop.core.exception.ConflictException;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.model.entity.Tag;

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
    	//配置参数
    	hql.fillLikeParameter("name", tag.getName());
    	hql.fillParameter("status", tag.getStatus());
    	hql.hasAllDataFunction(request, tag.getUserName());
    	//排序
    	hql.fillOrder(request, "order by t.createTime desc");//作为SQL的一个片段，所以需要跟SQL相互配合
    	//每页大小
		hql.fillPageSize(request);
    	PageSupport ps = tagService.getTag(hql);
    	ps.savePage(request);
        request.setAttribute("tag", tag);
        return PathResolver.getPath(request,response,BackPage.TAG_LIST);
    }

    @Override
	@RequestMapping(value = "/save")
    public String save(HttpServletRequest request, HttpServletResponse response, Tag tag) {
    	if(tag.getTagId() == null){
    		//save
    		Tag origin = tagService.getTag(tag.getName(),UserManager.getUsername(request));
    		if(origin != null){
    			throw new ConflictException("标签名称'" + tag.getName() +"'已经存在", EntityCodes.TAG);
    		}
        	tag.setCreateTime(new Date());
        	tag.setType(TagTypeEnum.INDEX.value());
        	tag.setUserId(UserManager.getUserId(request));
        	tag.setUserName(UserManager.getUsername(request));
            tagService.saveTag(tag);
    	}else{
    		//update
    		Tag origin = tagService.getTag(tag.getTagId());
    		if(origin == null){
    			throw new NotFoundException("没有该标签", EntityCodes.TAG);
    		}
    		origin.setName(tag.getName());
    		origin.setNewsCategoryId(tag.getNewsCategoryId());
    		origin.setSortId(tag.getSortId());
    		origin.setStatus(tag.getStatus());
    		origin.setCreateTime(new Date());
    		tagService.updateTag(origin);
    	}

        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
        return PathResolver.getPath(request, response, FowardPage.TAG_QUERY);
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
        return PathResolver.getPath(request,response,FowardPage.TAG_QUERY);
        
    }

	@Override
	@RequestMapping(value = "/load")
	public String load(HttpServletRequest request, HttpServletResponse response) {
		 return PathResolver.getPath(request,response,BackPage.TAG);
	}

    @Override
	@RequestMapping(value = "/update/{id}")
    public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
        Tag tag = tagService.getTag(id);
        checkNullable("TAG_QUERY", tag);
		String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), tag.getUserName());
		if(result!=null){
			return result;
		}
		request.setAttribute("tag", tag);
		return PathResolver.getPath(request,response,BackPage.TAG);
    }

}

