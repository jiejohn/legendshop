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

import com.legendshop.business.common.page.BackPage;
import com.legendshop.business.common.page.FowardPage;
import com.legendshop.business.service.ImgFileService;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.ApplicationException;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.helper.FileProcessor;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.RealPathUtil;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.model.entity.ImgFile;
import com.legendshop.model.entity.Product;
import com.legendshop.spi.constants.Constants;
import com.legendshop.util.AppUtils;

/**
 * 产品图片控制器.
 */
@Controller
@RequestMapping("/admin/imgFile")
public class ImgFileAdminController extends BaseController {
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(ImgFileAdminController.class);
	
	/** The img file service. */
	@Autowired
	private ImgFileService imgFileService;

	/**
	 * Query.
	 *
	 * @param request the request
	 * @param response the response
	 * @param curPageNO the cur page no
	 * @param imgFile the img file
	 * @return the string
	 */
	@RequestMapping("/query")
	public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, ImgFile imgFile) {
		Long productId = (Long) request.getAttribute("productId");
		if (productId == null) {
			String productIdStr = request.getParameter("productId");
			if (AppUtils.isNotBlank(productIdStr))
				productId = Long.valueOf(productIdStr);
		}
		if (productId == null) {
			throw new ApplicationException("miss productId",EntityCodes.IMG);
		}
		CriteriaQuery cq = new CriteriaQuery(ImgFile.class, curPageNO, "javascript:pager");
		cq.setPageSize(PropertiesUtil.getObject(SysParameterEnum.PAGE_SIZE, Integer.class));
		cq = hasAllDataFunction(cq, request, StringUtils.trim(imgFile.getUserName()));
		if (productId != null) {
			cq.eq("productId", productId);
			imgFile.setProductId(productId);
			Product product = imgFileService.getProd(productId);
			request.setAttribute("prod", product);

		}
		
		PageSupport ps = imgFileService.getImgFileList(cq);
		savePage(ps, request);
		request.setAttribute("productId", productId);
		return PathResolver.getPath(request,response,BackPage.IMG_LIST_PAGE);
	}

	/**
	 * Save.
	 *
	 * @param request the request
	 * @param response the response
	 * @param imgFile the img file
	 * @return the string
	 */
	@RequestMapping(value = "/save")
	public String save(HttpServletRequest request, HttpServletResponse response, ImgFile imgFile) {
		if (imgFile.getFile() != null) {
			String name = UserManager.getUsername(request.getSession());
			String subPath = name + "/imgFile/";
			String filePath = FileProcessor.uploadFileAndCallback(imgFile.getFile(), subPath, "imf" + name);
			imgFile.setFilePath(filePath);
			imgFile.setFileSize(new Integer((int) imgFile.getFile().getSize()));
			imgFile.setFileType(filePath.substring(filePath.lastIndexOf(".") + 1).toLowerCase());
			imgFile.setProductType((short) 1);
			imgFile.setStatus(Constants.ONLINE.shortValue());
			imgFile.setUpoadTime(new Date());
			imgFile.setUserName(name);
			imgFileService.save(imgFile);
			saveMessage(request, ResourceBundleHelper.getSucessfulString());
		}
		request.setAttribute("productId", imgFile.getProductId());
		return PathResolver.getPath(request,response,FowardPage.IMG_LIST_QUERY);

	}

	/**
	 * Delete.
	 *
	 * @param request the request
	 * @param response the response
	 * @param id the id
	 * @return the string
	 */
	@RequestMapping(value = "/delete/{id}")
	public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
		ImgFile imgFile = imgFileService.getImgFileById(id);
		String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), imgFile.getUserName());
		if(result!=null){
			return result;
		}
		log.info("{}, delete ImgFile filePath {}", imgFile.getUserName(), imgFile.getFilePath());
		imgFileService.delete(id);
		String url = RealPathUtil.getBigPicRealPath() + "/" + imgFile.getFilePath();
		FileProcessor.deleteFile(url);
		request.setAttribute("productId", imgFile.getProductId());
		saveMessage(request, ResourceBundleHelper.getSucessfulString());
		return PathResolver.getPath(request,response,FowardPage.IMG_LIST_QUERY);
	}

	/**
	 * Load.
	 *
	 * @param request the request
	 * @param response the response
	 * @param id the id
	 * @return the string
	 */
	@RequestMapping(value = "/load/{id}")
	public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
		ImgFile imgFile = imgFileService.getImgFileById(id);
		request.setAttribute("bean", imgFile);
		return PathResolver.getPath(request,response,BackPage.IMG_EDIT_PAGE);
	}

	/**
	 * Update.
	 *
	 * @param imgFile the img file
	 * @return the string
	 */
	@RequestMapping(value = "/update")
	public String update(HttpServletRequest request,HttpServletResponse response,@PathVariable ImgFile imgFile) {
		imgFileService.update(imgFile);
		return PathResolver.getPath(request,response,FowardPage.IMG_LIST_QUERY);
	}

}
