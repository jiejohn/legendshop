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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import bingosoft.jcf.dao.support.CriteriaQuery;
import bingosoft.jcf.dao.support.PageSupport;
import bingosoft.jcf.util.FileProcessor;

import com.done.base.BaseSpringController;
import com.done.common.Constants;
import com.done.model.Logo;
import com.done.service.LogoService;
import com.done.util.RealPathUtil;
import com.done.util.SessionUserManagement;

/**
 * @author hewq 2009-10-5
 */
@Controller
public class LogoController extends BaseSpringController {
    private Logger log = LoggerFactory.getLogger(LogoController.class);
    @Autowired
    private LogoService logoService;

    @RequestMapping("/admin/logo/query")
    public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, Logo logo) {
        CriteriaQuery cq = new CriteriaQuery(Logo.class, curPageNO, "javascript:pager");
        cq.setPageSize(Constants.PAGE_SIZE);
        cq = hasAllDataFunction(cq, request, StringUtils.trim(logo.getUserName()));
        cq.add();
        PageSupport ps = logoService.getDataByCriteriaQuery(cq);
        savePage(ps, request);
        request.setAttribute("bean", logo);
        return "/logo/logoList";
    }

    @RequestMapping(value = "/admin/logo/save")
    public String save(MultipartHttpServletRequest request, HttpServletResponse response, Logo logo) {
        Logo originLogo = null;
        String banner = null;
        String name = SessionUserManagement.getUsername(request.getSession());
        String subPath = name + "/logo/";
        if ((logo != null) && (logo.getId() != null)) { //update
            originLogo = logoService.load(logo.getId());
            if (originLogo == null) {
                throw new RuntimeException("Origin Logo is NULL");
            }
            String originBanner = originLogo.getBanner();
            if (!SessionUserManagement.hasFunction(request.getSession(), Constants.FUNCTION_VIEW_ALL_DATA)) {
                if (!name.equals(originLogo.getUserName())) {
                    throw new RuntimeException("Can't edit Logo does not own to you!");
                }
            }
            originLogo.setUrl(logo.getUrl());
            originLogo.setMemo(logo.getMemo());
            if (logo.getFile().getSize() > 0) {
                banner = uploadFileAndCallback(request, logo.getFile(), subPath);
                originLogo.setBanner(banner);
                String url = RealPathUtil.getRealPath(getServletContext()) + originBanner;
                FileProcessor.deleteFile(url);
            }
            logoService.update(originLogo);

        } else {
            banner = uploadFileAndCallback(request, logo.getFile(), subPath);
            logo.setBanner(banner);
            logo.setUserId(SessionUserManagement.getUserId(request.getSession()));
            logo.setUserName(SessionUserManagement.getUsername(request.getSession()));
            logoService.save(logo);
        }
        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
        return "forward:/admin/logo/query.c";
    }

    @RequestMapping(value = "/admin/logo/delete/{id}")
    public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Integer id) {
        Logo logo = logoService.load(id);
        checkPrivilege(request, SessionUserManagement.getUsername(request.getSession()), logo.getUserName());
        log.info("{}, delete Logo Url {}", logo.getUserName(), logo.getUrl());
        logoService.delete(id);
        String url = RealPathUtil.getRealPath(getServletContext()) + logo.getBanner();
        FileProcessor.deleteFile(url);
        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
        return "forward:/admin/logo/query.c";
    }

    @RequestMapping(value = "/admin/logo/load/{id}")
    public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Integer id) {
        Logo logo = logoService.load(id);
        checkPrivilege(request, SessionUserManagement.getUsername(request.getSession()), logo.getUserName());
        request.setAttribute("bean", logo);
        return "/logo/logo";
    }

    @RequestMapping(value = "/admin/logo/update")
    public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Logo logo) {
        Logo origin = logoService.load(logo.getId());
        checkPrivilege(request, SessionUserManagement.getUsername(request.getSession()), origin.getUserName());
        logo.setUserId(origin.getUserId());
        logo.setUserName(origin.getUserName());
        logoService.update(logo);
        return "forward:/admin/logo/query.c";
    }

}
