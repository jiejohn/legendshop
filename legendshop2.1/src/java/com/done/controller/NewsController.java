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
import com.done.model.News;
import com.done.service.NewsService;
import com.done.util.SessionUserManagement;

/**
 * @author He-WenQiang. Create in 2009-10-09 09:48:47.
 * 
 */
@Controller
public class NewsController extends BaseSpringController {
    private Logger log = LoggerFactory.getLogger(NewsController.class);
    @Autowired
    private NewsService newsService;

    @RequestMapping("/admin/news/query")
    public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, News news) {
        CriteriaQuery cq = new CriteriaQuery(News.class, curPageNO, "javascript:pager");
        cq.setPageSize(Constants.PAGE_SIZE);
        cq = hasAllDataFunction(cq, request, StringUtils.trim(news.getUserName()));
        if (!AppUtils.isBlank(news.getNewsTitle())) {
            cq.like("newsTitle", "%" + news.getNewsTitle() + "%");
        }
        if (!AppUtils.isBlank(news.getStatus())) {
            cq.eq("status", news.getStatus());
        }
        cq.addOrder("desc", "newsDate");
        cq.add();
        PageSupport ps = newsService.getDataByCriteriaQuery(cq);
        savePage(ps, request);
        request.setAttribute("bean", news);
        return "/news/newsList";
    }

    @RequestMapping(value = "/admin/news/save")
    public String save(HttpServletRequest request, HttpServletResponse response, News news) {
        news.setNewsDate(new Date());
        news.setUserId(SessionUserManagement.getUserId(request.getSession()));
        news.setUserName(SessionUserManagement.getUsername(request.getSession()));
        newsService.save(news);
        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
        return "forward:/admin/news/query.c";
    }

    @RequestMapping(value = "/admin/news/delete/{id}")
    public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Integer id) {
        News news = newsService.load(id);
        checkPrivilege(request, SessionUserManagement.getUsername(request.getSession()), news.getUserName());
        log.info("{},delete News Title{}", news.getUserName(), news.getNewsTitle());
        newsService.delete(id);
        saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
        return "forward:/admin/news/query.c";
    }

    @RequestMapping(value = "/admin/news/load/{id}")
    public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable
    Integer id) {
        News news = newsService.load(id);
        checkPrivilege(request, SessionUserManagement.getUsername(request.getSession()), news.getUserName());
        request.setAttribute("bean", news);
        return "/news/news";
    }

    @RequestMapping(value = "/admin/news/update")
    public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable
    News news) {
        News origin = newsService.load(news.getNewsId());
        checkPrivilege(request, SessionUserManagement.getUsername(request.getSession()), origin.getUserName());
        log.info("{} update News Title{}", origin.getUserName(), origin.getNewsTitle());
        news.setUserId(origin.getUserId());
        news.setUserName(origin.getUserName());
        newsService.update(news);
        return "forward:/admin/news/query.c";
    }

}
