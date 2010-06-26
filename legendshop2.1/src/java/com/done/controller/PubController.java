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
import com.done.model.Pub;
import com.done.service.PubService;
import com.done.util.SessionUserManagement;

/**
 * @author He-WenQiang. Create in 2009-10-11 00:17:57.
 * 
 */
@Controller
public class PubController extends BaseSpringController {
    private Logger log = LoggerFactory.getLogger(PubController.class);
    @Autowired
    private PubService pubService;

    @RequestMapping("/admin/pub/query")
    public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, Pub pub) {
        CriteriaQuery cq = new CriteriaQuery(Pub.class, curPageNO, "javascript:pager");
        cq.setPageSize(Constants.PAGE_SIZE);
        cq = hasAllDataFunction(cq, request, StringUtils.trim(pub.getUserName()));
        cq.addOrder("desc", "date");
        cq.add();
        PageSupport ps = pubService.getDataByCriteriaQuery(cq);
        savePage(ps, request);
        request.setAttribute("bean", pub);
        return "/pub/pubList";
    }

    @RequestMapping(value = "/admin/pub/save")
    public String save(HttpServletRequest request, HttpServletResponse response, Pub pub) {
        String name = SessionUserManagement.getUsername(request.getSession());
        pub.setDate(new Date());
        pub.setUserId(SessionUserManagement.getUserId(request.getSession()));
        pub.setUserName(name);
        pubService.save(pub, name, SessionUserManagement.hasFunction(request.getSession(),
                Constants.FUNCTION_VIEW_ALL_DATA));
        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
        return "forward:/admin/pub/query.c";
    }

    @RequestMapping(value = "/admin/pub/delete/{id}")
    public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Integer id) {
        Pub pub = pubService.load(id);
        checkPrivilege(request, SessionUserManagement.getUsername(request.getSession()), pub.getUserName());
        log.info("{} delete Pub Title {}", pub.getUserName(), pub.getTitle());
        pubService.delete(id);
        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
        return "forward:/admin/pub/query.c";
    }

    @RequestMapping(value = "/admin/pub/load/{id}")
    public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Integer id) {
        Pub pub = pubService.load(id);
        checkPrivilege(request, SessionUserManagement.getUsername(request.getSession()), pub.getUserName());
        request.setAttribute("bean", pub);
        return "/pub/pub";
    }

}
