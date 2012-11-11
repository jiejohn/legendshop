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
import com.legendshop.business.service.ShopDetailService;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SimpleHqlQuery;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.core.exception.PermissionException;
import com.legendshop.core.helper.FileProcessor;
import com.legendshop.core.helper.RealPathUtil;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.model.entity.Logo;
import com.legendshop.model.entity.ShopDetail;
import com.legendshop.util.AppUtils;

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
	
	@Autowired
	private ShopDetailService shopDetailService;

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
    	SimpleHqlQuery hql = new SimpleHqlQuery(curPageNO);
    	//配置参数
    	hql.hasAllDataFunction(request, logo.getUserName());
    	//每页大小
		hql.fillPageSize(request);
    	PageSupport ps = logoService.getLogo(hql);
    	ps.savePage(request);
        request.setAttribute("logo", logo);		
		return PathResolver.getPath(request,response,BackPage.LOGO_LIST_PAGE);
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
		String logoPic = null;
		String name = UserManager.getUsername(request.getSession());
		String subPath = name + "/logo/";
		String userId = logo.getId();
		ShopDetail shopDetail = null;
		if(AppUtils.isBlank(userId)){
			//save
			userId = UserManager.getUserId(request);
			shopDetail = shopDetailService.getShopDetailByUserId(userId);
			if(shopDetail == null){
				throw new NotFoundException("ShopDetail is NULL",EntityCodes.SHOP);
			}
		}else{
			//update
			shopDetail = shopDetailService.getShopDetailByUserId(userId);
			if(shopDetail == null){
				throw new NotFoundException("ShopDetail is NULL",EntityCodes.SHOP);
			}
			if (!CommonServiceUtil.haveViewAllDataFunction(request)) {
				if (!name.equals(shopDetail.getUserName())) {
					throw new PermissionException(name + " can't edit Logo does not own to you!",EntityCodes.LOGO);
				}
			}
		}


		String originlogoPic = shopDetail.getLogoPic();
		if (logo.getFile().getSize() > 0) {
			logoPic = FileProcessor.uploadFileAndCallback(logo.getFile(), subPath, "" + name);
			shopDetail.setLogoPic(logoPic);
			logoService.updateLogo(shopDetail);
			if(AppUtils.isNotBlank(originlogoPic)){
				String url = RealPathUtil.getBigPicRealPath() + "/" + originlogoPic;
				FileProcessor.deleteFile(url);
			}
		}
		
		saveMessage(request, ResourceBundleHelper.getSucessfulString());
		return PathResolver.getPath(request,response,FowardPage.LOGO_LIST_QUERY);
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
	@RequestMapping(value = "/delete/{userId}")
	public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable
	String userId) {
		ShopDetail shopDetail = shopDetailService.getShopDetailByUserId(userId);
		if(shopDetail == null){
			throw new NotFoundException("ShopDetail is NULL",EntityCodes.SHOP);
		}
		
		String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), shopDetail.getUserName());
		if(result!=null){
			return result;
		}
		
		log.info("{}, delete Logo Url {}", shopDetail.getUserName(), shopDetail.getLogoPic());
		logoService.deleteLogo(shopDetail);
		if(shopDetail.getLogoPic() != null){
			String url = RealPathUtil.getBigPicRealPath() + "/" + shopDetail.getLogoPic();
			FileProcessor.deleteFile(url);
		}

		saveMessage(request, ResourceBundleHelper.getDeleteString());
		return PathResolver.getPath(request,response,FowardPage.LOGO_LIST_QUERY);
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
	@RequestMapping(value = "/load/{userId}")
	public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable
	String userId) {
		ShopDetail shopDetail = shopDetailService.getShopDetailByUserId(userId);
		if(shopDetail == null){
			throw new NotFoundException("ShopDetail is NULL",EntityCodes.SHOP);
		}
		String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), shopDetail.getUserName());
		if(result!=null){
			return result;
		}
		request.setAttribute("bean", shopDetail);
		return PathResolver.getPath(request,response,BackPage.LOGO_EDIT_PAGE);
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
		return PathResolver.getPath(request,response,BackPage.LOGO_EDIT_PAGE);
	}

}
