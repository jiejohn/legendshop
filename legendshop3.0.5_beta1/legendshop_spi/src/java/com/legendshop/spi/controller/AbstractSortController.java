/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.spi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.BusinessException;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.exception.ErrorCodes;
import com.legendshop.core.helper.FileProcessor;
import com.legendshop.core.helper.FoundationUtil;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.RealPathUtil;
import com.legendshop.model.entity.Sort;
import com.legendshop.spi.service.SortService;
import com.legendshop.util.AppUtils;

/**
 * The Class AbstractSortController.
 */
public class AbstractSortController extends BaseController {
	
	/** The log. */
	protected final Logger log = LoggerFactory.getLogger(AbstractSortController.class);
	/** The sort service. */
	@Autowired(required=false)
	protected SortService sortService;

	public PageSupport querySort(HttpServletRequest request, HttpServletResponse response, String curPageNO, Sort sort) {
		// Qbc查找方式
		CriteriaQuery cq = new CriteriaQuery(Sort.class, curPageNO);
		if (AppUtils.isNotBlank(sort.getSortName())) {
			cq.like("sortName", "%" + sort.getSortName() + "%");
		}
		cq.eq("sortType", sort.getSortType());
		if (AppUtils.isNotBlank(sort.getHeaderMenu())) {
			cq.eq("headerMenu", sort.getHeaderMenu());
		}
		if (AppUtils.isNotBlank(sort.getNavigationMenu())) {
			cq.eq("navigationMenu", sort.getNavigationMenu());
		}
		hasAllDataFunction(cq, request, "userName", sort.getUserName());
		if (!FoundationUtil.isDataForExport(cq, request)) {// 非导出情况
			cq.setPageSize(PropertiesUtil.getObject(SysParameterEnum.PAGE_SIZE, Integer.class));
		}
		if (!FoundationUtil.isDataSortByExternal(cq, request)) {
			cq.addOrder("asc", "seq");
		}
		
		return sortService.getSortList(cq);
	}
	
	public void parseSort(HttpServletRequest request, HttpServletResponse response, Sort entity) {
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

	public void deleteSort(HttpServletRequest request, HttpServletResponse response,  Long id) {
		Sort sort = sortService.getSortById(id);
		String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), sort.getUserName());
		if(result!=null){
			return;
		}
		log.info("{} delete SortName {}", new Object[] { sort.getUserName(), sort.getSortName()});
		sortService.delete(sort);
		FileProcessor.deleteFile(RealPathUtil.getBigPicRealPath() + "/" + sort.getPicture());
	}
}
