package com.done.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import bingosoft.jcf.dao.support.HqlQuery;
import bingosoft.jcf.dao.support.PageSupport;
import bingosoft.jcf.sql.ConfigCode;
import bingosoft.jcf.util.AppUtils;

import com.done.base.BaseSpringController;
import com.done.common.Constants;
import com.done.model.Nsort;
import com.done.model.Sort;
import com.done.service.AdminService;
import com.done.service.NsortService;
import com.done.util.SessionUserManagement;

/**
 * @author He-WenQiang. Create in 2009-11-18 22:45:39.
 * 
 */
@Controller
public class NsortController extends BaseSpringController {
    private Logger log = LoggerFactory.getLogger(NsortController.class);
    @Autowired
    private NsortService nsortService;
    @Autowired
    private AdminService adminService;

    @RequestMapping("/admin/nsort/query")
    public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, Nsort nsort) {
        Sort sort = nsortService.loadSort(nsort.getSortId());
        if (AppUtils.isBlank(sort)) {
            throw new RuntimeException("sort is null!");
        }
        HqlQuery hql = new HqlQuery();
        hql.setPageSize(Constants.PAGE_SIZE);
        hql.setCurPage(curPageNO);
        Map<String, String> map = new HashMap<String, String>();
        hql.addParams(sort.getSortId());
        String userName = SessionUserManagement.getUsername(request.getSession());
        if (!SessionUserManagement.hasFunction(request.getSession(), Constants.FUNCTION_VIEW_ALL_DATA)) {
            map.put("userName", userName);
            hql.addParams(userName);
        }
        String name = request.getParameter("name");
        if (!AppUtils.isBlank(name)) {
            map.put("nsortName", name);
            hql.addParams(name);
        }
        String QueryNsortCount = ConfigCode.getInstance().getCode("biz.QueryNsortCount", map);
        String QueryNsort = ConfigCode.getInstance().getCode("biz.QueryNsort", map);
        hql.setAllCountString(QueryNsortCount);
        hql.setQueryString(QueryNsort);
        PageSupport ps = nsortService.getDataByCriteriaQuery(hql);
        List<Nsort> list = ps.getResultList();//2级分类
        List<Nsort> subNsort = nsortService.listBySort(nsort.getSortId());//3级分类
        if (!AppUtils.isBlank(list)) {
            for (Nsort n : list) {
                n.addSubSort(subNsort);
            }
        }
        savePage(ps, request);
        request.setAttribute("bean", nsort);
        return "/nsort/nsortList";
    }

    @RequestMapping(value = "/admin/nsort/save")
    public String save(HttpServletRequest request, HttpServletResponse response, Nsort nsort) {
        String name = SessionUserManagement.getUsername(request.getSession());
        Sort sort = nsortService.loadSort(nsort.getSortId());
        checkPrivilege(request, SessionUserManagement.getUsername(request.getSession()), sort.getUserName());
        nsortService.save(nsort);
        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
        Nsort bean = (Nsort) request.getAttribute("bean");
        if (bean != null) {
            bean.setNsortName(null);
            request.setAttribute("bean", bean);
        }
        return "forward:/admin/nsort/query.c";
    }
    
    @RequestMapping("/business/saveNSort")
    public String saveNSort(HttpServletRequest request, HttpServletResponse response, String curPageNO,Map map) throws ServletException, IOException {
    	        String nsortName = (String) map.get("nsortName");
    	        String sortId = (String) map.get("sortId");
    	        String nsortId = (String) map.get("nsortId");
    	        Nsort nsort = new Nsort();
    	        nsort.setNsortName(nsortName);
    	        if (!AppUtils.isBlank(nsortId)) {
    	            nsort.setNsortId(Integer.valueOf(nsortId));
    	        }
    	        if (!AppUtils.isBlank(sortId)) {
    	            nsort.setSortId(Integer.valueOf(sortId));
    	        }
    	        adminService.saveNsort(nsort);
    	        request.setAttribute("sortId", sortId);
    	        return "/admin/nsortList.do";
    	    }

    @RequestMapping(value = "/admin/nsort/delete/{id}")
    public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Integer id) {
        Nsort nsort = nsortService.load(id);
        Sort sort = nsortService.loadSort(nsort.getSortId());
        checkPrivilege(request, SessionUserManagement.getUsername(request.getSession()), sort.getUserName());
        log.info("{} delete Pub NsortName {}", sort.getUserName(), nsort.getNsortName());
        List childNsort = nsortService.hasChildNsort(id);
        if (AppUtils.isBlank(childNsort)) {
            nsortService.delete(id);
        } else {
            throw new RuntimeException("发现三级分类，请先删除三级分类再删除二级分类！");
        }
        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
        return "forward:/admin/nsort/query.c?sortId=" + nsort.getSortId();
    }

    @RequestMapping(value = "/admin/nsort/load/{id}")
    public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Integer id) {
        Nsort nsort = nsortService.load(id);
        request.setAttribute("bean", nsort);
        return "/nsort/nsort";
    }

    @RequestMapping(value = "/admin/nsort/update")
    public String update(@PathVariable
    Nsort nsort) {
        nsortService.update(nsort);
        return "forward:/admin/nsort/query.c";
    }

}
