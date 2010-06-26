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
import bingosoft.jcf.util.AppUtils;

import com.done.base.BaseSpringController;
import com.done.common.Constants;

import com.done.model.Myleague;
import com.done.service.MyleagueService;
import com.done.util.SessionUserManagement;

/**
 * @author He-WenQiang. Create in 2010-05-17 21:19:36.
 *
 */
@Controller
public class MyleagueController extends BaseSpringController {
	private Logger log = LoggerFactory.getLogger(NewsController.class);
    @Autowired
    private MyleagueService myleagueService;

    @RequestMapping("/admin/myleague/query")
    public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, Myleague myleague) {
        CriteriaQuery cq = new CriteriaQuery(Myleague.class, curPageNO, "javascript:pager");
        cq.setPageSize(Constants.PAGE_SIZE);
        if (SessionUserManagement.hasFunction(request.getSession(), Constants.FUNCTION_VIEW_ALL_DATA)) {
            if (!AppUtils.isBlank(myleague.getUserId())) {
                cq.like("userId", "%"+StringUtils.trim(myleague.getUserId())+"%");
            }
        } else {
            cq.eq("userId", SessionUserManagement.getUsername(request.getSession()));
        }
        if(!AppUtils.isBlank(myleague.getFriendId())){
        	cq.like("friendId", "%" + myleague.getFriendId()+"%");
        }
        cq.add();
        PageSupport ps = myleagueService.getDataByCriteriaQuery(cq);
        savePage(ps, request);
        request.setAttribute("bean", myleague);
        return "/myleague/myleagueList";
    }

    @RequestMapping(value = "/admin/myleague/save")
    public String save(HttpServletRequest request, HttpServletResponse response, Myleague myleague) {
        myleagueService.save(myleague);
        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
        return "forward:/admin/myleague/query.c";
    }

    @RequestMapping(value = "/admin/myleague/delete/{id}")
    public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Integer id) {
        myleagueService.delete(id);
        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
        return "forward:/admin/myleague/query.c";
    }

    @RequestMapping(value = "/admin/myleague/load/{id}")
    public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Integer id) {
        Myleague myleague = myleagueService.load(id);
        request.setAttribute("bean", myleague);
        return "/myleague/myleague";
    }
    
    @RequestMapping(value = "/admin/myleague/update")
    public String update(HttpServletRequest request, HttpServletResponse response, Myleague myleague) {
    	Myleague origin = myleagueService.load(myleague.getId());
    	if(origin == null){
    		throw new RuntimeException(" can not find this object : " +origin.getId());
    	}
        checkPrivilege(request, SessionUserManagement.getUsername(request.getSession()), origin.getUserId());
        log.info("{} update Myleague UserId{}", origin.getUserId(), origin.getFriendId());
        origin.setDisplayOrder(myleague.getDisplayOrder());
        origin.setFriendName(myleague.getFriendName());
        myleagueService.update(origin);
        return "forward:/admin/myleague/query.c";
    }

}

