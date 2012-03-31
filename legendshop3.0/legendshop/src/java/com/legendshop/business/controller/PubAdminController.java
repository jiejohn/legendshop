/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.business.common.page.BackPage;
import com.legendshop.business.common.page.FowardPage;
import com.legendshop.business.service.PubService;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.model.entity.Pub;

/**
 * 商城公告控制器.
 */
@Controller
@RequestMapping("/admin/pub")
public class PubAdminController extends BaseController {
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(PubAdminController.class);

	/** The pub service. */
	@Autowired
	private PubService pubService;

	/**
	 * Query.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param curPageNO
	 *            the cur page no
	 * @param pub
	 *            the pub
	 * @return the string
	 */
	@RequestMapping("/query")
	public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, Pub pub) {
		CriteriaQuery cq = new CriteriaQuery(Pub.class, curPageNO, "javascript:pager");
		cq.setPageSize(PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE, Integer.class));
		cq = hasAllDataFunction(cq, request, StringUtils.trim(pub.getUserName()));
		cq.addOrder("desc", "date");
		cq.add();
		PageSupport ps = pubService.getPubList(cq);
		savePage(ps, request);
		request.setAttribute("bean", pub);
		return PathResolver.getPath(request, BackPage.PUB_LIST_PAGE);
	}

	/**
	 * Save.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param pub
	 *            the pub
	 * @return the string
	 */
	@RequestMapping(value = "/save")
	public String save(HttpServletRequest request, HttpServletResponse response, Pub pub) {
		String name = UserManager.getUsername(request.getSession());
		pub.setDate(new Date());
		pub.setUserId(UserManager.getUserId(request.getSession()));
		pub.setUserName(name);
		pubService.save(pub, name, CommonServiceUtil.haveViewAllDataFunction(request));
		saveMessage(request, ResourceBundleHelper.getSucessfulString());
		return PathResolver.getPath(request, FowardPage.PUB_LIST_QUERY);
	}

	/**
	 * Delete.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param id
	 *            the id
	 * @return the string
	 */
	@RequestMapping(value = "/delete/{id}")
	public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable
	Long id) {
		Pub pub = pubService.getPubById(id);
		String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), pub.getUserName());
		if(result!=null){
			return result;
		}
		log.info("{} delete Pub Title {}", pub.getUserName(), pub.getTitle());
		pubService.delete(id);
		saveMessage(request, ResourceBundleHelper.getDeleteString());
		return PathResolver.getPath(request, FowardPage.PUB_LIST_QUERY);
	}

	/**
	 * Load.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param id
	 *            the id
	 * @return the string
	 */
	@RequestMapping(value = "/load/{id}")
	public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable
	Long id) {
		Pub pub = pubService.getPubById(id);
		String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), pub.getUserName());
		if(result!=null){
			return result;
		}
		request.setAttribute("bean", pub);
		return PathResolver.getPath(request, BackPage.PUB_EDIT_PAGE);
	}
	
	/**
	 * Load.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping(value = "/load")
	public String load(HttpServletRequest request, HttpServletResponse response) {
		return PathResolver.getPath(request, BackPage.PUB_EDIT_PAGE);
	}

}
