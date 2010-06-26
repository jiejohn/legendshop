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
import bingosoft.jcf.util.FileProcessor;

import com.done.base.BaseSpringController;
import com.done.common.Constants;
import com.done.model.Advertisement;
import com.done.service.AdvertisementService;
import com.done.util.RealPathUtil;
import com.done.util.SessionUserManagement;

/**
 * @author He-WenQiang. Create in 2009-11-12 20:48:45.
 * 
 */
@Controller
public class AdvertisementController extends BaseSpringController {
    private Logger log = LoggerFactory.getLogger(AdvertisementController.class);
    private Integer ENABLED = 1;
    @Autowired
    private AdvertisementService advertisementService;

    @RequestMapping("/admin/advertisement/query")
    public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO,
            Advertisement advertisement) {
        CriteriaQuery cq = new CriteriaQuery(Advertisement.class, curPageNO, "javascript:pager");
        cq.setPageSize(Constants.PAGE_SIZE);
        cq = hasAllDataFunction(cq, request, StringUtils.trim(advertisement.getUserName()));
        cq.eq("enabled", ENABLED);
        cq.add();
        PageSupport ps = advertisementService.getDataByCriteriaQuery(cq);
        savePage(ps, request);
        request.setAttribute("bean", advertisement);
        return "/advertisement/advertisementList";
    }

    @RequestMapping(value = "/admin/advertisement/save")
    public String save(HttpServletRequest request, HttpServletResponse response, Advertisement advertisement) {

        Advertisement origin = null;
        String picUrl = null;
        String name = SessionUserManagement.getUsername(request.getSession());
        String subPath = name + "/couplet/";
        if ((advertisement != null) && (advertisement.getId() != null)) { //update
            origin = advertisementService.load(advertisement.getId());
            if (origin == null) {
                throw new RuntimeException("Origin Advertisement is NULL");
            }
            String originPicUrl = origin.getPicUrl();
            if (!SessionUserManagement.hasFunction(request.getSession(), Constants.FUNCTION_VIEW_ALL_DATA)) {
                if (!name.equals(origin.getUserName())) {
                    throw new RuntimeException("Can't edit Advertisement does not own to you!");
                }
            }
            origin.setLinkUrl(advertisement.getLinkUrl());
            origin.setType(advertisement.getType());
            if (advertisement.getFile().getSize() > 0) {
                picUrl = uploadFileAndCallback(request, advertisement.getFile(), subPath);
                origin.setPicUrl(picUrl);
                String url = RealPathUtil.getRealPath(getServletContext()) + originPicUrl;
                FileProcessor.deleteFile(url);
            }
            advertisementService.update(origin);

        } else {
            //check it first
            if (advertisementService.load(name, advertisement.getType()) != null) {
                throw new RuntimeException("您已经有一个对联广告，不能再增加");
            }
            picUrl = uploadFileAndCallback(request, advertisement.getFile(), subPath);
            advertisement.setPicUrl(picUrl);
            advertisement.setEnabled(ENABLED);
            advertisement.setUserId(SessionUserManagement.getUserId(request.getSession()));
            advertisement.setUserName(SessionUserManagement.getUsername(request.getSession()));
            advertisementService.save(advertisement);
        }
        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
        return "forward:/admin/advertisement/query.c";
    }

    @RequestMapping(value = "/admin/advertisement/delete/{id}")
    public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Integer id) {
        Advertisement advertisement = advertisementService.load(id);
        checkPrivilege(request, SessionUserManagement.getUsername(request.getSession()), advertisement.getUserName());
        log.info("{}, delete Advertisement Url {}", advertisement.getUserName(), advertisement.getLinkUrl());
        advertisementService.delete(id);
        String url = RealPathUtil.getRealPath(getServletContext()) + advertisement.getPicUrl();
        FileProcessor.deleteFile(url);
        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
        return "forward:/admin/advertisement/query.c";
    }

    @RequestMapping(value = "/admin/advertisement/load/{id}")
    public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Integer id) {
        Advertisement advertisement = advertisementService.load(id);
        checkPrivilege(request, SessionUserManagement.getUsername(request.getSession()), advertisement.getUserName());
        request.setAttribute("bean", advertisement);
        return "/advertisement/advertisement";
    }

    @RequestMapping(value = "/admin/advertisement/update")
    public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Advertisement advertisement) {
        Advertisement origin = advertisementService.load(advertisement.getId());
        checkPrivilege(request, SessionUserManagement.getUsername(request.getSession()), origin.getUserName());
        advertisement.setUserId(origin.getUserId());
        advertisement.setUserName(origin.getUserName());
        advertisementService.update(advertisement);
        return "forward:/admin/advertisement/query.c";
    }

}
