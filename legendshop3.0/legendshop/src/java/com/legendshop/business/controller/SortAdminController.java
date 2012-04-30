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
import org.springframework.web.multipart.MultipartFile;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.business.common.page.BackPage;
import com.legendshop.business.common.page.FowardPage;
import com.legendshop.business.service.SortService;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.AdminController;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.constant.ProductTypeEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.BusinessException;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.exception.ErrorCodes;
import com.legendshop.core.helper.FileProcessor;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.RealPathUtil;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.model.entity.Sort;
import com.legendshop.util.AppUtils;

/**
 * 产品分类控制器。.
 */
@Controller
@RequestMapping("/admin/sort")
public class SortAdminController extends BaseController implements AdminController<Sort,Long>{
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(SortAdminController.class);
	
	/** The sort service. */
	@Autowired
	private SortService sortService;

	/* (non-Javadoc)
	 * @see com.legendshop.core.base.AdminController#query(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.String, java.lang.Object)
	 */
	@Override
	@RequestMapping("/query")
	public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, Sort sort) {
			// Qbc查找方式
			CriteriaQuery cq = new CriteriaQuery(Sort.class, curPageNO);
			if (AppUtils.isNotBlank(sort.getSortName())) {
				cq.like("sortName", "%" + sort.getSortName() + "%");
			}
			if (AppUtils.isNotBlank(sort.getSortType())) {
				cq.eq("sortType", sort.getSortType());				
			}
			if (AppUtils.isNotBlank(sort.getHeaderMenu())) {
				cq.eq("headerMenu", sort.getHeaderMenu());
			}
			if (AppUtils.isNotBlank(sort.getNavigationMenu())) {
				cq.eq("navigationMenu", sort.getNavigationMenu());
			}
			 hasAllDataFunction(cq, request, "userName", sort.getUserName());
			if (!CommonServiceUtil.isDataForExport(cq, request)) {// 非导出情况
				cq.setPageSize(PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE, Integer.class));
			}
			if (!CommonServiceUtil.isDataSortByExternal(cq, request)) {
				cq.addOrder("asc", "seq");
			}
			cq.add();
			PageSupport ps =sortService.getSortList(cq);
			savePage(ps, request);
			request.setAttribute("sort", sort);
		return PathResolver.getPath(request, BackPage.SORT_LIST_PAGE);
	}
	
	

	/* (non-Javadoc)
	 * @see com.legendshop.core.base.AdminController#save(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	@RequestMapping("/save")
	public String save(HttpServletRequest request, HttpServletResponse response, Sort entity) {
		MultipartFile formFile = entity.getFile();// 取得上传的文件
		String userName = UserManager.getUsername(request);
		String subPath = userName + "/sort/";
		String filename = null;
		try {
			if(entity.getSortId() != null){
				//update sort
				String orginPicture = null;
				Sort origin = sortService.getSortById(entity.getSortId());
				if ((formFile != null) && (formFile.getSize() > 0)) {
					orginPicture = RealPathUtil.getBigPicRealPath() + "/" + origin.getPicture();
					// upload file
					filename = FileProcessor.uploadFileAndCallback(formFile, subPath, "" + userName);
					origin.setPicture(filename);
				}
				
				updateSort(request, response, entity, origin);
				// delete file after update success
				if ((formFile != null) && (formFile.getSize() > 0)) {
					FileProcessor.deleteFile(orginPicture);
					FileProcessor.deleteFile(orginPicture);
				}
			}else{
				//save sort
				// save product
				entity.setUserId(UserManager.getUserId(request));
				entity.setUserName(userName);
				// upload file
				if ((formFile != null) && (formFile.getSize() > 0)) {
					filename = FileProcessor.uploadFileAndCallback(formFile, subPath, "" + userName);
					entity.setPicture(filename);
				}
				saveSort(request, response, entity);
			}
			
		} catch (Exception e) {
			// delete file uploaded
			if ((formFile != null) && (formFile.getSize() > 0)) {
				FileProcessor.deleteFile(RealPathUtil.getBigPicRealPath() + "/" + subPath + filename);
			}
			throw new BusinessException(e,  "save sort error",EntityCodes.SORT,ErrorCodes.BUSINESS_ERROR);
		}
		saveMessage(request, ResourceBundleHelper.getSucessfulString());
		//TODO
		//request.setAttribute(PageLet.CALL_BACK, "admin/sortList.do");
		return PathResolver.getPath(request, FowardPage.SORT_LIST_QUERY);
	}

	/**
	 * Save sort.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param entity
	 *            the entity
	 */
	private void saveSort(HttpServletRequest request, HttpServletResponse response, Sort entity) {
		sortService.save(entity);
	}

	/**
	 * Update sort.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param entity
	 *            the entity
	 * @param origin
	 *            the origin
	 */
	private void updateSort(HttpServletRequest request, HttpServletResponse response, Sort entity, Sort origin) {
		origin.setSeq(entity.getSeq());
		origin.setSortName(entity.getSortName());
		origin.setSortType(entity.getSortType());
		origin.setHeaderMenu(entity.getHeaderMenu());
		origin.setNavigationMenu(entity.getNavigationMenu());
		sortService.updateSort(origin);
		
	}

	/* (non-Javadoc)
	 * @see com.legendshop.core.base.AdminController#delete(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	@RequestMapping(value = "/delete/{id}")
	public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
		Sort sort = sortService.getSortById(id);
		String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), sort.getUserName());
		if(result!=null){
			return result;
		}
		log.info("{} delete SortName {}", new Object[] { sort.getUserName(), sort.getSortName()});
		sortService.delete(sort);
		FileProcessor.deleteFile(RealPathUtil.getBigPicRealPath() + "/" + sort.getPicture());
		saveMessage(request, ResourceBundleHelper.getDeleteString());
		return PathResolver.getPath(request, FowardPage.SORT_LIST_QUERY);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.core.base.AdminController#load(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value = "/load")
	public String load(HttpServletRequest request, HttpServletResponse response) {
		return PathResolver.getPath(request, BackPage.SORT_EDIT_PAGE);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.core.base.AdminController#update(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	@RequestMapping(value = "/update/{id}")
	public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
		checkNullable("sortId", id);
		Sort sort = sortService.getSortById(id);
		String result = checkPrivilege(request, UserManager.getUsername(request), sort.getUserName());
		if(result!=null){
			return result;
		}
		request.setAttribute("sort", sort);
		return PathResolver.getPath(request, BackPage.SORT_EDIT_PAGE);
	}

}
