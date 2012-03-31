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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.business.common.page.BackPage;
import com.legendshop.business.common.page.FowardPage;
import com.legendshop.business.service.LogoService;
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
import com.legendshop.model.entity.Logo;

/**
 * Logo.
 */
@Controller
@RequestMapping("/admin/logo")
public class LogoAdminController extends BaseController {
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(LogoAdminController.class);
	
	/** The logo service. */
	@Autowired
	private LogoService logoService;

	/**
	 * Query.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param curPageNO
	 *            the cur page no
	 * @param logo
	 *            the logo
	 * @return the string
	 */
	@RequestMapping("/query")
	public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, Logo logo) {
		CriteriaQuery cq = new CriteriaQuery(Logo.class, curPageNO, "javascript:pager");
		cq.setPageSize(PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE, Integer.class));
		cq = hasAllDataFunction(cq, request, StringUtils.trim(logo.getUserName()));
		cq.add();
		PageSupport ps = logoService.getLogoList(cq);
		savePage(ps, request);
		request.setAttribute("bean", logo);
		return PathResolver.getPath(request, BackPage.LOGO_LIST_PAGE);
	}

	/**
	 * Save.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param logo
	 *            the logo
	 * @return the string
	 */
	@RequestMapping(value = "/save")
	public String save(MultipartHttpServletRequest request, HttpServletResponse response, Logo logo) {
		Logo originLogo = null;
		String banner = null;
		String name = UserManager.getUsername(request.getSession());
		String subPath = name + "/logo/";
		if ((logo != null) && (logo.getId() != null)) { // update
			originLogo = logoService.getLogoById(logo.getId());
			if (originLogo == null) {
				throw new NotFoundException("Origin Logo is NULL",EntityCodes.LOGO);
			}
			String originBanner = originLogo.getBanner();
			if (!CommonServiceUtil.haveViewAllDataFunction(request)) {
				if (!name.equals(originLogo.getUserName())) {
					throw new PermissionException(name + " can't edit Logo does not own to you!",EntityCodes.LOGO);
				}
			}
			originLogo.setUrl(logo.getUrl());
			originLogo.setMemo(logo.getMemo());
			if (logo.getFile().getSize() > 0) {
				banner = FileProcessor.uploadFileAndCallback(logo.getFile(), subPath, "lo" + name);
				originLogo.setBanner(banner);
				String url = RealPathUtil.getBigPicRealPath() + "/" + originBanner;
				FileProcessor.deleteFile(url);
			}
			logoService.update(originLogo);

		} else {
			banner = FileProcessor.uploadFileAndCallback(logo.getFile(), subPath, "lo" + name);
			logo.setBanner(banner);
			logo.setUserId(UserManager.getUserId(request.getSession()));
			logo.setUserName(UserManager.getUsername(request.getSession()));
			logoService.save(logo);
		}
		saveMessage(request, ResourceBundleHelper.getSucessfulString());
		return PathResolver.getPath(request, FowardPage.LOGO_LIST_QUERY);
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
		Logo logo = logoService.getLogoById(id);
		String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), logo.getUserName());
		if(result!=null){
			return result;
		}
		log.info("{}, delete Logo Url {}", logo.getUserName(), logo.getUrl());
		logoService.delete(id);
		String url = RealPathUtil.getBigPicRealPath() + "/" + logo.getBanner();
		FileProcessor.deleteFile(url);
		saveMessage(request, ResourceBundleHelper.getDeleteString());
		return PathResolver.getPath(request, FowardPage.LOGO_LIST_QUERY);
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
		Logo logo = logoService.getLogoById(id);
		String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), logo.getUserName());
		if(result!=null){
			return result;
		}
		request.setAttribute("bean", logo);
		return PathResolver.getPath(request, BackPage.LOGO_EDIT_PAGE);
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
		return PathResolver.getPath(request, BackPage.LOGO_EDIT_PAGE);
	}

	/**
	 * Update.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param logo
	 *            the logo
	 * @return the string
	 */
	@RequestMapping(value = "/update")
	public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable
	Logo logo) {
		Logo origin = logoService.getLogoById(logo.getId());
		String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), origin.getUserName());
		if(result!=null){
			return result;
		}
		logo.setUserId(origin.getUserId());
		logo.setUserName(origin.getUserName());
		logoService.update(logo);
		return PathResolver.getPath(request, FowardPage.LOGO_LIST_QUERY);
	}

}
