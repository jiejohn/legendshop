/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.group.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.group.page.GroupFrontPage;
import com.legendshop.model.entity.Product;
import com.legendshop.spi.constants.NewsPositionEnum;
import com.legendshop.spi.service.GroupProductService;
import com.legendshop.spi.service.GroupService;
import com.legendshop.spi.service.NewsService;
import com.legendshop.spi.service.PartnerService;
import com.legendshop.spi.service.SortService;

/**
 * The Class GroupController.
 */
@Controller
@RequestMapping("/group")
public class GroupController extends BaseController {
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(GroupController.class);
		
	@Autowired
	private NewsService newsService;

	@Autowired
	private GroupService groupService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request, HttpServletResponse response,String curPageNO,String order,String seq,Product product) {
		log.debug("Index starting calling");
		return groupService.getIndex(request, response, curPageNO, order, seq, product);
	}
	
	@RequestMapping(value = "/clientServicePanel")
	public String clientServicePannel(HttpServletRequest request, HttpServletResponse response) {
		String shopName = getShopName(request, response);
		request.setAttribute("groupNewsTopList", newsService.getNews(shopName, NewsPositionEnum.NEWS_GROUP_TOP, 10));
		return PathResolver.getPath(request, GroupFrontPage.CLIENT_SERVICE_PANEL);
	}
	
	
	
	@RequestMapping(value = "/view/{id}")
	public String view(HttpServletRequest request, HttpServletResponse response,@PathVariable Long id) {
		log.debug("view starting calling");
		return groupService.getView(request, response, id);
	}
	
//	@RequestMapping("/sort")
//	public String sort(HttpServletRequest request, HttpServletResponse response) {
//		log.debug("sort starting calling");
//		return "/group/default/sort";
//	}
	
	@RequestMapping("/questionPanel")
	public String questionPanel(HttpServletRequest request, HttpServletResponse response) {
		log.debug("question starting calling");
		String shopName = getShopName(request, response);
		request.setAttribute("groupNewsBottomList", newsService.getNews(shopName, NewsPositionEnum.NEWS_GROUP_BOTTOM, 10));
		return PathResolver.getPath(request, GroupFrontPage.QUESTION_PANEL);
	}
	
	@RequestMapping("/question")
	public String question(HttpServletRequest request, HttpServletResponse response) {
		log.debug("question starting calling");
		return PathResolver.getPath(request, GroupFrontPage.QUESTION);
	}
	

}
