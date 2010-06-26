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
import bingosoft.jcf.util.AppUtils;

import com.done.base.BaseSpringController;
import com.done.common.Constants;
import com.done.model.ShopDetail;
import com.done.service.ShopDetailService;
import com.done.util.SessionUserManagement;

/**
 * @author He-WenQiang. Create in 2009-10-07 19:54:19.
 * 
 */
@Controller
public class ShopDetailController extends BaseSpringController {
    private Logger log = LoggerFactory.getLogger(ShopDetailController.class);
    @Autowired
    private ShopDetailService shopDetailService;

    @RequestMapping("/admin/shopDetail/query")
    public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO,
            ShopDetail shopDetail) throws Exception {
        CriteriaQuery cq = new CriteriaQuery(ShopDetail.class, curPageNO, "javascript:pager");
        cq.setPageSize(Constants.PAGE_SIZE);
        cq = hasAllDataFunction(cq, request, "storeName", StringUtils.trim(shopDetail.getStoreName()));
        cq.addOrder("desc", "modifyTime");
        cq.add();
        PageSupport ps = shopDetailService.getDataByCriteriaQuery(cq);
        savePage(ps, request);
        request.setAttribute("bean", shopDetail);
        return "/shopDetail/shopDetailList";
    }

    @RequestMapping(value = "/admin/shopDetail/save")
    public String save(HttpServletRequest request, HttpServletResponse response, ShopDetail shopDetail) {
        if ((shopDetail != null) && (!AppUtils.isBlank(shopDetail.getUserId()))) {
            return update(request, response, shopDetail);
        }
        String id = (String) request.getAttribute("id");
        if (id == null) {
            id = request.getParameter("id");
        }
        if (AppUtils.isBlank(id)) {
            shopDetail.setUserId(SessionUserManagement.getUserId(request.getSession()));
        } else {
            shopDetail.setUserId(request.getParameter("id"));
        }

        Date date = new Date();
        shopDetail.setModifyTime(date);
        shopDetail.setAddtime(date);
        shopDetail.setVisitTimes(0);
        shopDetail.setWeb(shopDetail.getStoreName());
        shopDetail.setStatus(Constants.SHOP_ONLINE);
        shopDetail.setStoreStatus(Constants.SHOP_ONLINE);
        shopDetailService.save(shopDetail);
        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
        return "forward:/admin/shopDetail/query.c";
    }

    @RequestMapping(value = "/admin/shopDetail/delete/{id}")
    public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable
    String id) {
        shopDetailService.delete(id);
        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
        return "forward:/admin/shopDetail/query.c";
    }

    @RequestMapping(value = "/admin/shopDetail/load/{id}")
    public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable
    String id) {
        ShopDetail shopDetail = shopDetailService.load(id);
        if (shopDetail != null) {
            checkPrivilege(request, SessionUserManagement.getUsername(request.getSession()), shopDetail.getStoreName());
        }
        request.setAttribute("bean", shopDetail);
        request.setAttribute("id", id);
        return "/shopDetail/shopDetail";
    }

    private String update(HttpServletRequest request, HttpServletResponse response, ShopDetail shopDetail) {
        ShopDetail origin = shopDetailService.load(shopDetail.getUserId());
        checkPrivilege(request, SessionUserManagement.getUsername(request.getSession()), shopDetail.getStoreName());
        shopDetail.setStoreName(origin.getStoreName());
        shopDetail.setModifyTime(new Date());
        shopDetail.setStatus(origin.getStatus());
        shopDetail.setStoreStatus(origin.getStatus());
        shopDetail.setVisitTimes(origin.getVisitTimes());
        shopDetail.setAddtime(origin.getAddtime());
        shopDetailService.update(shopDetail);
        return "forward:/admin/shopDetail/query.c";
    }

}
