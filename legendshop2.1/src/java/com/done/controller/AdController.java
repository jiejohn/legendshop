package com.done.controller;

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
import com.done.model.Ad;
import com.done.service.AdService;
import com.done.util.SessionUserManagement;

/**
 * @author He-WenQiang. Create in 2009-10-08 20:40:06.
 * 
 */
@Controller
public class AdController extends BaseSpringController {
    private Logger log = LoggerFactory.getLogger(AdController.class);
    @Autowired
    private AdService adService;

    @RequestMapping("/admin/ad/query")
    public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, Ad ad) {
        CriteriaQuery cq = new CriteriaQuery(Ad.class, curPageNO, "javascript:pager");
        cq.setPageSize(Constants.PAGE_SIZE);
        cq = hasAllDataFunction(cq, request, StringUtils.trim(ad.getUserName()));
        cq.addOrder("desc", "bs");
        cq.add();
        PageSupport ps = adService.getDataByCriteriaQuery(cq);
        savePage(ps, request);
        request.setAttribute("bean", ad);
        return "/ad/adList";
    }

    @RequestMapping(value = "/admin/ad/save")
    public String save(HttpServletRequest request, HttpServletResponse response, Ad ad) {
        ad.setUserId(SessionUserManagement.getUserId(request.getSession()));
        String userName = SessionUserManagement.getUsername(request.getSession());
        ad.setUserName(userName);
        log.info("{} save Ad Url {} ", userName, ad.getUrl());
        adService.save(ad);
        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
        return "forward:/admin/ad/query.c";
    }

    @RequestMapping(value = "/admin/ad/delete/{id}")
    public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Integer id) {
        Ad ad = adService.load(id);
        checkPrivilege(request, SessionUserManagement.getUsername(request.getSession()), ad.getUserName());
        if (ad != null) {
            log.info("{} delete Ad Url{}", ad.getUserName(), ad.getUrl());
            adService.delete(id);
        }

        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
        return "forward:/admin/ad/query.c";
    }

    @RequestMapping(value = "/admin/ad/load/{id}")
    public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Integer id) {
        Ad ad = adService.load(id);
        checkPrivilege(request, SessionUserManagement.getUsername(request.getSession()), ad.getUserName());
        request.setAttribute("bean", ad);
        return "/ad/ad";
    }

    @RequestMapping(value = "/admin/ad/update")
    public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Ad ad) {
        Ad origin = adService.load(ad.getId());
        checkPrivilege(request, SessionUserManagement.getUsername(request.getSession()), origin.getUserName());
        ad.setUserId(origin.getUserId());
        ad.setUserName(origin.getUserName());
        log.info("{} update Ad Url{}", origin.getUserName(), ad.getUrl());
        adService.update(ad);
        return "forward:/admin/ad/query.c";
    }

}
