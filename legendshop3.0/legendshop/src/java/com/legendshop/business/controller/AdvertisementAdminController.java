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
import com.legendshop.business.service.AdvertisementService;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.exception.LimitationException;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.core.exception.PermissionException;
import com.legendshop.core.helper.FileProcessor;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.RealPathUtil;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.model.entity.Advertisement;

/**
 * 后台广告控制器。.
 */
@Controller
@RequestMapping("/admin/advertisement")
public class AdvertisementAdminController extends BaseController {
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(AdvertisementAdminController.class);
		
	/** The advertisement service. */
	@Autowired
	private AdvertisementService advertisementService;
	

	/**
	 * Query.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param curPageNO
	 *            the cur page no
	 * @param advertisement
	 *            the advertisement
	 * @return the string
	 */
	@RequestMapping("/query")
	public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO,
			Advertisement advertisement) {
		CriteriaQuery cq = new CriteriaQuery(Advertisement.class, curPageNO);
		cq.setPageSize(PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE, Integer.class));
		cq = hasAllDataFunction(cq, request, StringUtils.trim(advertisement.getUserName()));
		// cq.eq("enabled", ENABLED);
		cq.addOrder("asc", "type");
		cq.add();
		PageSupport ps = advertisementService.getDataByCriteriaQuery(cq);
		savePage(ps, request);
		request.setAttribute("bean", advertisement);
		return PathResolver.getPath(BackPage.ADV_LIST_PAGE);
	}

	/**
	 * Save.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param advertisement
	 *            the advertisement
	 * @return the string
	 */
	@RequestMapping(value = "/save")
	public String save(HttpServletRequest request, HttpServletResponse response, Advertisement advertisement) {
		Advertisement origin = null;
		String picUrl = null;
		String name = UserManager.getUsername(request.getSession());
		String subPath = name + "/advertisement/";
		if ((advertisement != null) && (advertisement.getId() != null)) { // update
			origin = advertisementService.getAdvertisement(advertisement.getId());
			if (origin == null) {
				throw new NotFoundException("Origin Advertisement is NULL",EntityCodes.ADV);
			}
			String originPicUrl = origin.getPicUrl();
			if (!CommonServiceUtil.haveViewAllDataFunction(request)) {
				if (!name.equals(origin.getUserName())) {
					throw new PermissionException("Can't edit Advertisement does not own to you!",EntityCodes.ADV);
				}
			}
			origin.setLinkUrl(advertisement.getLinkUrl());
			origin.setType(advertisement.getType());
			origin.setSourceInput(advertisement.getSourceInput());
			origin.setEnabled(advertisement.getEnabled());
			origin.setTitle(advertisement.getTitle());
			if (advertisement.getFile().getSize() > 0) {
				picUrl = FileProcessor.uploadFileAndCallback(advertisement.getFile(), subPath, "adv" + name);
				origin.setPicUrl(picUrl);
				String url = RealPathUtil.getBigPicRealPath() + "/" + originPicUrl;
				FileProcessor.deleteFile(url);
			}
			advertisementService.update(origin);

		} else {
			// check it first
			if (!advertisementService.isMaxNum(name, advertisement.getType())) {
				throw new LimitationException("您已经达到广告上限，不能再增加",EntityCodes.ADV);
			}
			picUrl = FileProcessor.uploadFileAndCallback(advertisement.getFile(), subPath, "adv" + name);
			advertisement.setPicUrl(picUrl);
			advertisement.setUserId(UserManager.getUserId(request.getSession()));
			advertisement.setUserName(UserManager.getUsername(request.getSession()));
			advertisementService.save(advertisement);
		}
		saveMessage(request, ResourceBundleHelper.getSucessfulString());
		return PathResolver.getPath(FowardPage.ADV_LIST_QUERY);
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
		Advertisement advertisement = advertisementService.getAdvertisement(id);
		String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), advertisement.getUserName());
		if(result!=null){
			return result;
		}
		log.info("{}, delete Advertisement Url {}", advertisement.getUserName(), advertisement.getLinkUrl());
		advertisementService.delete(id);
		String url = RealPathUtil.getBigPicRealPath() + "/" + advertisement.getPicUrl();
		FileProcessor.deleteFile(url);
		saveMessage(request, ResourceBundleHelper.getDeleteString());
		return PathResolver.getPath(FowardPage.ADV_LIST_QUERY);
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
		Advertisement advertisement = advertisementService.getAdvertisement(id);
		String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), advertisement.getUserName());
		if(result!=null){
			return result;
		}
		request.setAttribute("bean", advertisement);
		return PathResolver.getPath(BackPage.ADV_EDIT_PAGE);
		
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
		return PathResolver.getPath(BackPage.ADV_EDIT_PAGE);
		
	}

	/**
	 * Update.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param advertisement
	 *            the advertisement
	 * @return the string
	 */
	@RequestMapping(value = "/update")
	public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable
	Advertisement advertisement) {
		Advertisement origin = advertisementService.getAdvertisement(advertisement.getId());
		String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), origin.getUserName());
		if(result!=null){
			return result;
		}
		advertisement.setUserId(origin.getUserId());
		advertisement.setUserName(origin.getUserName());
		advertisementService.update(advertisement);
		return PathResolver.getPath(FowardPage.ADV_LIST_QUERY);
	}

}
