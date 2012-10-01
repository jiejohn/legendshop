/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.controller;

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
import com.legendshop.business.service.ExternalLinkService;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.core.exception.PermissionException;
import com.legendshop.core.helper.FileProcessor;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.RealPathUtil;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.model.entity.ExternalLink;

/**
 * 友情链接控制器.
 */
@Controller
@RequestMapping("/admin/externallink")
public class ExternalLinkAdminController extends BaseController {

	/** The log. */
	private final Logger log = LoggerFactory.getLogger(ExternalLinkAdminController.class);
		
	/** The external link service. */
	@Autowired
	private ExternalLinkService externalLinkService;

	/**
	 * Query.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param curPageNO
	 *            the cur page no
	 * @param externalLink
	 *            the external link
	 * @return the string
	 */
	@RequestMapping("/query")
	public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO,
			ExternalLink externalLink) {

		CriteriaQuery cq = new CriteriaQuery(ExternalLink.class, curPageNO, "javascript:pager");
		cq = hasAllDataFunction(cq, request, StringUtils.trim(externalLink.getUserName()));
		if (!CommonServiceUtil.isDataForExport(cq, request)) {// 非导出情况
			cq.setPageSize(PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE, Integer.class));
		}
		if (!CommonServiceUtil.isDataSortByExternal(cq, request)) {// 非外部排序
			cq.addOrder("desc", "bs");
		}
		
		PageSupport ps = externalLinkService.getDataByCriteriaQuery(cq);
		savePage(ps, request);
		request.setAttribute("bean", externalLink);
		return PathResolver.getPath(request,response,BackPage.LINK_LIST_PAGE);
	}

	/**
	 * Save.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param externalLink
	 *            the external link
	 * @return the string
	 */
	@RequestMapping(value = "/save")
	public String save(HttpServletRequest request, HttpServletResponse response, ExternalLink externalLink) {
		ExternalLink origin = null;
		String picUrl = null;
		externalLink.setUserId(UserManager.getUserId(request.getSession()));
		String userName = UserManager.getUsername(request.getSession());
		String subPath = userName + "/frendlink/";
		externalLink.setUserName(userName);

		if ((externalLink != null) && (externalLink.getId() != null)) { // update
			origin = externalLinkService.getExternalLinkById(externalLink.getId());
			if (origin == null) {
				throw new NotFoundException("Origin ExternalLink is NULL",EntityCodes.LINK);
			}

			if (!CommonServiceUtil.haveViewAllDataFunction(request)) {
				if (!userName.equals(origin.getUserName())) {
					throw new PermissionException("Can't edit ExternalLink does not own to you!",EntityCodes.LINK);
				}
			}

			String originPicUrl = origin.getPicture();
			origin.setUrl(externalLink.getUrl());
			origin.setWordlink(externalLink.getWordlink());
			origin.setContent(externalLink.getContent());
			origin.setBs(externalLink.getBs());
			if (externalLink.getFile() != null && externalLink.getFile().getSize() > 0) {
				picUrl = FileProcessor.uploadFileAndCallback(externalLink.getFile(), subPath, "ad" + userName);
				origin.setPicture(picUrl);
				String url = RealPathUtil.getBigPicRealPath() + "/" + originPicUrl;
				FileProcessor.deleteFile(url);
			}
			externalLinkService.update(origin);
		} else { // save
			if (externalLink.getFile() != null && externalLink.getFile().getSize() > 0) {
				picUrl = FileProcessor.uploadFileAndCallback(externalLink.getFile(), subPath, "ad" + userName);
				externalLink.setPicture(picUrl);
			}
			log.info("{} save ExternalLink Url {} ", userName, externalLink.getUrl());
			externalLinkService.save(externalLink);
		}

		saveMessage(request, ResourceBundleHelper.getString("operation.successful"));
		return PathResolver.getPath(request,response,FowardPage.LINK_LIST_QUERY);
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
		ExternalLink externalLink = externalLinkService.getExternalLinkById(id);
		String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), externalLink.getUserName());
		if(result!=null){
			return result;
		}
		if (externalLink != null) {
			log.info("{} delete ExternalLink Url{}", externalLink.getUserName(), externalLink.getUrl());
			externalLinkService.delete(id);
			String picUrl = RealPathUtil.getBigPicRealPath() + "/" + externalLink.getPicture();
			// 删除文件
			log.debug("delete ExternalLink Image file {}", picUrl);
			FileProcessor.deleteFile(picUrl);
		}

		saveMessage(request, ResourceBundleHelper.getDeleteString());
		return PathResolver.getPath(request,response,FowardPage.LINK_LIST_QUERY);
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
		ExternalLink externalLink = externalLinkService.getExternalLinkById(id);
		String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), externalLink.getUserName());
		if(result!=null){
			return result;
		}
		request.setAttribute("bean", externalLink);
		return PathResolver.getPath(request,response,BackPage.LINK_EDIT_PAGE);
	}
	
	@RequestMapping(value = "/load")
	public String load(HttpServletRequest request, HttpServletResponse response) {
		return PathResolver.getPath(request,response,BackPage.LINK_EDIT_PAGE);
	}

	/**
	 * Update.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param externalLink
	 *            the external link
	 * @return the string
	 */
	@RequestMapping(value = "/update")
	public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable
	ExternalLink externalLink) {
		ExternalLink origin = externalLinkService.getExternalLinkById(externalLink.getId());
		String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), origin.getUserName());
		if(result!=null){
			return result;
		}
		externalLink.setUserId(origin.getUserId());
		externalLink.setUserName(origin.getUserName());
		log.info("{} update ExternalLink Url{}", origin.getUserName(), externalLink.getUrl());
		externalLinkService.update(externalLink);
		return PathResolver.getPath(request,response,FowardPage.LINK_LIST_QUERY);
	}

}
