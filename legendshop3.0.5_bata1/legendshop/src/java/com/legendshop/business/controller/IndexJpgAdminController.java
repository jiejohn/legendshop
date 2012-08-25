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
import com.legendshop.business.service.IndexJpgService;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.BusinessException;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.exception.ErrorCodes;
import com.legendshop.core.helper.FileProcessor;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.RealPathUtil;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.model.UserMessages;
import com.legendshop.model.entity.Indexjpg;
import com.legendshop.util.AppUtils;

/**
 * 首页轮换图片.
 */
@Controller
@RequestMapping("/admin/indexjpg")
public class IndexJpgAdminController extends BaseController {
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(IndexJpgAdminController.class);
		
	/** The index jpg service. */
	@Autowired
	private IndexJpgService indexJpgService;

	/**
	 * Query.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param curPageNO
	 *            the cur page no
	 * @param indexjpg
	 *            the indexjpg
	 * @return the string
	 */
	@RequestMapping("/query")
	public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, Indexjpg indexjpg) {
		// Qbc查找方式
		CriteriaQuery cq = new CriteriaQuery(Indexjpg.class, curPageNO);

		if (CommonServiceUtil.haveViewAllDataFunction(request)) {
			if (!AppUtils.isBlank(indexjpg.getUserName())) {
				cq.like("userName", "%" + indexjpg.getUserName() + "%");
			}
		} else {
			cq.eq("userName", UserManager.getUsername(request));
		}
		if (!CommonServiceUtil.isDataForExport(cq, request)) {// 非导出情况
			cq.setPageSize(PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE, Integer.class));
		}
		if (!CommonServiceUtil.isDataSortByExternal(cq, request)) {
			cq.addOrder("desc", "id");
		}
		cq.add();
		PageSupport ps = indexJpgService.getIndexJpg(cq);
		savePage(ps, request);
		request.setAttribute("indexJpg", indexjpg);
		return PathResolver.getPath(request,response,BackPage.IJPG_LIST_PAGE);
	}

	/**
	 * Save.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param indexjpg
	 *            the indexjpg
	 * @return the string
	 */
	@RequestMapping(value = "/save")
	public String save(HttpServletRequest request, HttpServletResponse response, Indexjpg indexjpg) {
		String name = UserManager.getUsername(request);
		Long num = indexJpgService.getIndexJpgNum(name);
		String result = checkMaxJpgNum(request, response,num);
		if(result != null){
			return result;
		}
		String subPath = name+ "/indexjpg/";
		String filename = null;
		MultipartFile formFile = indexjpg.getFile();// 取得上传的文件
		try {
			if(indexjpg.getId() != null){
				//update indexjpg
				String orginImg = null;
				Indexjpg origin = indexJpgService.getIndexJpgById(indexjpg.getId());
				String checkPrivilegeResult = checkPrivilege(request, name, origin.getUserName());
				if(checkPrivilegeResult!=null){
					return checkPrivilegeResult;
				}
				if ((formFile != null) && (formFile.getSize() > 0)) {
					orginImg = RealPathUtil.getBigPicRealPath() + "/" + origin.getImg();
					// upload file
					filename = FileProcessor.uploadFileAndCallback(formFile, subPath, "" + name);
					origin.setImg(filename);
				}
				updateIndexjpg(request, response, indexjpg, origin);
				// delete orgin file after update success
				if ((formFile != null) && (formFile.getSize() > 0) && orginImg !=null) {
					FileProcessor.deleteFile(orginImg);
				}
				
			}else{
				//save indexjpg
				indexjpg.setUserId(UserManager.getUserId(request));
				indexjpg.setUserName(name);
				// upload file
				if ((formFile != null) && (formFile.getSize() > 0)) {
					filename = FileProcessor.uploadFileAndCallback(formFile, subPath, "" + name);
					indexjpg.setImg(filename);
				}
				
				saveIndexjpg(request, response, indexjpg);
			}
			
		} catch (Exception e) {
			// delete file uploaded
			if ((formFile != null) && (formFile.getSize() > 0)) {
				FileProcessor.deleteFile(RealPathUtil.getBigPicRealPath() + "/" + subPath + filename);
			}
			throw new BusinessException(e, "save Indexjpg error", EntityCodes.IMG,ErrorCodes.BUSINESS_ERROR);
		}
		saveMessage(request, ResourceBundleHelper.getSucessfulString());
		return PathResolver.getPath(request,response,FowardPage.IJPG_LIST_QUERY);
	}

	/**
	 * Save indexjpg.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param indexjpg
	 *            the indexjpg
	 */
	private void saveIndexjpg(HttpServletRequest request, HttpServletResponse response, Indexjpg indexjpg) {
		indexJpgService.saveIndexjpg(indexjpg);
	}

	/**
	 * Update indexjpg.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param indexjpg
	 *            the indexjpg
	 * @param origin
	 *            the origin
	 */
	private void updateIndexjpg(HttpServletRequest request, HttpServletResponse response, Indexjpg indexjpg,
			Indexjpg origin) {	
		origin.setAlt(indexjpg.getAlt());
		origin.setHref(indexjpg.getHref());
		origin.setId(indexjpg.getId());
		origin.setLink(indexjpg.getLink());
		origin.setStitle(indexjpg.getStitle());
		origin.setTitle(indexjpg.getTitle());
		origin.setTitleLink(indexjpg.getTitleLink());
		indexJpgService.updateIndexjpg(origin);
	}

	/**
	 * Check max jpg num.
	 * 
	 * @param request
	 *            the request
	 * @param num
	 *            the num
	 * @return the string
	 */
	private String  checkMaxJpgNum(HttpServletRequest request, HttpServletResponse response,Long num) {
		String result = null;
		Integer maxNum = PropertiesUtil.getObject(ParameterEnum.MAX_INDEX_JPG, Integer.class);
		if ((num != null) && (num >= maxNum)) {
			UserMessages uem = new UserMessages();
			uem.setTitle("系统设置不能上传多于" + maxNum + "张图片");
			uem.setCode(ErrorCodes.MAX_INDEX_JPG_ERROR);
			uem.addCallBackList("重新上传", "",  PathResolver.getPath(request,response,BackPage.IJPG_EDIT_PAGE));
			request.setAttribute(UserMessages.MESSAGE_KEY, uem);
			result =  handleException(request, uem);
		}
		return result;
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
	public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
		Indexjpg indexjpg = indexJpgService.getIndexJpgById(id);
		checkNullable("indexjpg",indexjpg);
		String userName = UserManager.getUsername(request);
		String result = checkPrivilege(request, userName, indexjpg.getUserName());
		if(result!=null){
			return result;
		}
		log.debug("{} delete indexjpg {}",userName,id);
		indexJpgService.deleteIndexJpg(indexjpg);
		FileProcessor.deleteFile(RealPathUtil.getBigPicRealPath() + "/" + indexjpg.getImg());
		saveMessage(request, ResourceBundleHelper.getSucessfulString());
		return PathResolver.getPath(request,response,FowardPage.IJPG_LIST_QUERY);
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
		return  PathResolver.getPath(request,response,BackPage.IJPG_EDIT_PAGE);
	}

	/**
	 * Update.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param id
	 *            the id
	 * @return the string
	 */
	@RequestMapping(value = "/update/{id}")
	public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
		if (AppUtils.isBlank(id)) {
			throw new BusinessException("indexjpg Id is non nullable", ErrorCodes.NON_NULLABLE);
		}
		Indexjpg indexjpg = indexJpgService.getIndexJpgById(id);
		String result = checkPrivilege(request, UserManager.getUsername(request), indexjpg.getUserName());
		if(result!=null){
			return result;
		}
		request.setAttribute("index", indexjpg);
		return  PathResolver.getPath(request,response,BackPage.IJPG_EDIT_PAGE);
	}

}
