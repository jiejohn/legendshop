package com.done.controller;

import java.util.Date;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import bingosoft.jcf.dao.support.CriteriaQuery;
import bingosoft.jcf.dao.support.PageSupport;

import com.done.base.BaseSpringController;
import com.done.common.Constants;
import com.done.model.Hotsearch;
import com.done.service.HotsearchService;
import com.done.util.SessionUserManagement;

/**
 * @author He-WenQiang. Create in 2009-10-11 00:21:44.
 * 
 */
@Controller
public class HotsearchController extends BaseSpringController {
    private Logger log = LoggerFactory.getLogger(HotsearchService.class);
    @Autowired
    private HotsearchService hotsearchService;

    @RequestMapping("/admin/hotsearch/query")
    public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, Hotsearch hotsearch) {
        CriteriaQuery cq = new CriteriaQuery(Hotsearch.class, curPageNO, "javascript:pager");
        cq.setPageSize(Constants.PAGE_SIZE);
        cq = hasAllDataFunction(cq, request, StringUtils.trim(hotsearch.getUserName()));
        cq.addOrder("desc", "date");
        cq.add();
        PageSupport ps = hotsearchService.getDataByCriteriaQuery(cq);
        savePage(ps, request);
        request.setAttribute("bean", hotsearch);
        return "/hotsearch/hotsearchList";
    }

    @RequestMapping(value = "/admin/hotsearch/save")
    public String save(HttpServletRequest request, HttpServletResponse response, Hotsearch hotsearch) {
        String name = SessionUserManagement.getUsername(request.getSession());
        hotsearch.setUserId(SessionUserManagement.getUserId(request.getSession()));
        hotsearch.setUserName(name);
        hotsearch.setDate(new Date());
        hotsearchService.save(hotsearch, name, SessionUserManagement.hasFunction(request.getSession(),
                Constants.FUNCTION_VIEW_ALL_DATA));
        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
        return "forward:/admin/hotsearch/query.c";
    }

    @RequestMapping(value = "/admin/hotsearch/delete/{id}")
    public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Integer id) {
        Hotsearch hotsearch = hotsearchService.load(id);
        checkPrivilege(request, SessionUserManagement.getUsername(request.getSession()), hotsearch.getUserName());
        log.info("{} delete Hotsearch Title {}, Msg {}", new Object[] { hotsearch.getUserName(), hotsearch.getTitle(),
                hotsearch.getMsg() });
        hotsearchService.delete(id);
        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
        return "forward:/admin/hotsearch/query.c";
    }

    @RequestMapping(value = "/admin/hotsearch/load/{id}")
    public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Integer id) {
        Hotsearch hotsearch = hotsearchService.load(id);
        checkPrivilege(request, SessionUserManagement.getUsername(request.getSession()), hotsearch.getUserName());
        request.setAttribute("bean", hotsearch);
        return "/hotsearch/hotsearch";
    }

    @RequestMapping(value = "/admin/hotsearch/update")
    public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Hotsearch hotsearch) {
        Hotsearch origin = hotsearchService.load(hotsearch.getId());
        checkPrivilege(request, SessionUserManagement.getUsername(request.getSession()), origin.getUserName());
        log.info("{} update Hotsearch Title{}", origin.getUserName(), origin.getTitle());
        hotsearch.setUserId(origin.getUserId());
        hotsearch.setUserName(origin.getUserName());
        hotsearchService.update(hotsearch);
        return "forward:/admin/hotsearch/query.c";
    }

}
