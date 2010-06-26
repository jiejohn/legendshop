package com.done.controller;


import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.done.util.SessionUserManagement;
import bingosoft.jcf.dao.support.CriteriaQuery;
import bingosoft.jcf.dao.support.PageSupport;
import bingosoft.jcf.util.AppUtils;

import com.done.base.BaseSpringController;
import com.done.common.Constants;

import com.done.service.NewsCategoryService;
import com.done.model.NewsCategory;

/**
 * @author He-WenQiang. Create in 2010-06-25 22:17:19.
 *
 */
@Controller
public class NewsCategoryController extends BaseSpringController {
    @Autowired
    private NewsCategoryService newsCategoryService;

    @RequestMapping("/admin/newsCategory/query")
    public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, NewsCategory newsCategory) {
        CriteriaQuery cq = new CriteriaQuery(NewsCategory.class, curPageNO, "javascript:pager");
        cq.setPageSize(Constants.PAGE_SIZE);
        /*
        if (SessionUserManagement.hasFunction(request.getSession(), Constants.FUNCTION_VIEW_ALL_DATA)) {
            if (!AppUtils.isBlank(newsCategory.getUserName())) {
                cq.eq("userName", StringUtils.trim(newsCategory.getUserName()));
            }
        } else {
            cq.eq("userName", SessionUserManagement.getUsername(request.getSession()));
        }
        */
        cq.add();
        PageSupport ps = newsCategoryService.getDataByCriteriaQuery(cq);
        savePage(ps, request);
        request.setAttribute("bean", newsCategory);
        return "/newsCategory/newsCategoryList";
    }

    @RequestMapping(value = "/admin/newsCategory/save")
    public String save(HttpServletRequest request, HttpServletResponse response, NewsCategory newsCategory) {
        newsCategoryService.save(newsCategory);
        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
        return "forward:/admin/newsCategory/query.c";
    }

    @RequestMapping(value = "/admin/newsCategory/delete/{id}")
    public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Integer id) {
        newsCategoryService.delete(id);
        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
        return "forward:/admin/newsCategory/query.c";
    }

    @RequestMapping(value = "/admin/newsCategory/load/{id}")
    public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Integer id) {
        NewsCategory newsCategory = newsCategoryService.load(id);
        request.setAttribute("bean", newsCategory);
        return "/newsCategory/newsCategory";
    }

    @RequestMapping(value = "/admin/newsCategory/update")
    public String update(@PathVariable
    NewsCategory newsCategory) {
        newsCategoryService.update(newsCategory);
        return "forward:/admin/newsCategory/query.c";
    }

}

