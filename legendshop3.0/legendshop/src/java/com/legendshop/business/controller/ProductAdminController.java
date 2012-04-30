/**
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
import org.springframework.web.multipart.MultipartFile;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.business.common.page.BackPage;
import com.legendshop.business.common.page.FowardPage;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.constant.ProductTypeEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.AuthorizationException;
import com.legendshop.core.exception.BusinessException;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.exception.ErrorCodes;
import com.legendshop.core.helper.FileProcessor;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.RealPathUtil;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.core.service.ProductService;
import com.legendshop.model.entity.Product;
import com.legendshop.util.AppUtils;
import com.legendshop.util.SafeHtml;

/**
 * 产品控制器.
 */
@Controller
@RequestMapping("/admin/product")
public class ProductAdminController extends BaseController {
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(ProductAdminController.class);
	
	/** The product service. */
	@Autowired
	private ProductService productService;

	/**
	 * Query.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param curPageNO
	 *            the cur page no
	 * @param product
	 *            the product
	 * @return the string
	 */
	@RequestMapping("/query")
	public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, Product product) {
		// Qbc查找方式
		CriteriaQuery cq = new CriteriaQuery(Product.class, curPageNO);

		if (!AppUtils.isBlank(product.getName())) {
			cq.like("name", "%" + product.getName().trim() + "%");
		}

		cq.eq("sortId", product.getSortId());
		cq.eq("nsortId", product.getNsortId());
		cq.eq("subNsortId", product.getSubNsortId());
		cq.eq("commend", product.getCommend());
		cq.eq("status", product.getStatus());
		cq.eq("brandId", product.getBrandId());
		cq.eq("prodType", "P");
		if (CommonServiceUtil.haveViewAllDataFunction(request)) {
			if (!AppUtils.isBlank(product.getUserName())) {
				cq.eq("userName", StringUtils.trim(product.getUserName()));
			}
		} else {
			String name = UserManager.getUsername(request);
			if (name == null) {
				throw new AuthorizationException("you are not logon yet!",EntityCodes.PROD);
			}
			cq.eq("userName", name);
		}

		if (!CommonServiceUtil.isDataForExport(cq, request)) {// 非导出情况
			cq.setPageSize(PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE, Integer.class));
		}
		if (!CommonServiceUtil.isDataSortByExternal(cq, request)) {
			cq.addOrder("desc", "recDate");
		}
		cq.add();
		PageSupport ps = productService.getProductList(cq);
		savePage(ps, request);
		request.setAttribute("prod", product);
		return PathResolver.getPath(request, BackPage.PROD_LIST_PAGE);
	}

	/**
	 * Save.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param product
	 *            the product
	 * @return the string
	 */
	@RequestMapping(value = "/save")
	public String save(HttpServletRequest request, HttpServletResponse response, Product product) {
		String name = UserManager.getUsername(request);
		String result = checkLogin(request,name);
		if(result != null){
			return result;
		}
		// 过滤特殊字符
		SafeHtml safeHtml = new SafeHtml();
		product.setName(safeHtml.makeSafe(product.getName()));
		product.setKeyWord(safeHtml.makeSafe(product.getKeyWord()));
		product.setBrief(safeHtml.makeSafe(product.getBrief()));
		MultipartFile formFile = product.getFile();// 取得上传的文件
		String subPath = name + "/prod/";
		String filename = null;
		try {
			if (product.getProdId() != null) {
				String orginProdPic = null;
				String smallProdPic = null;
				// update product
				Product origin = productService.getProductById(product.getProdId());
				if(origin == null){
					throw new BusinessException("can not found product by id " + product.getProdId(), EntityCodes.PROD);
				}
				String checkPrivilegeResult = checkPrivilege(request, name, origin.getUserName());
				if(checkPrivilegeResult!=null){
					return checkPrivilegeResult;
				}
				if ((formFile != null) && (formFile.getSize() > 0)) {
					orginProdPic = RealPathUtil.getBigPicRealPath() + "/" + origin.getPic();
					smallProdPic = RealPathUtil.getSmallPicRealPath() + "/" + origin.getPic();
					// upload file
					filename = FileProcessor.uploadFileAndCallback(formFile, subPath, "" + name);
					origin.setPic(filename);
				}

				productService.updateProduct(product, origin);
				// delete file after update success
				if ((formFile != null) && (formFile.getSize() > 0)) {
					FileProcessor.deleteFile(orginProdPic);
					FileProcessor.deleteFile(smallProdPic);
				}

			} else {
				// save product
				product.setUserId(UserManager.getUserId(request));
				product.setUserName(name);
				// upload file
				if ((formFile != null) && (formFile.getSize() > 0)) {
					filename = FileProcessor.uploadFileAndCallback(formFile, subPath, "" + name);
					product.setPic(filename);
				}
				productService.saveProduct(product,ProductTypeEnum.PRODUCT.value());
			}
		} catch (Exception e) {
			// delete file uploaded
			if ((formFile != null) && (formFile.getSize() > 0)) {
				FileProcessor.deleteFile(RealPathUtil.getBigPicRealPath() + "/" + subPath + filename);
			}
			throw new BusinessException(e, "save product error",EntityCodes.PROD, ErrorCodes.BUSINESS_ERROR);
		}

		String nextAction = request.getParameter("nextAction");
		if (nextAction != null && nextAction.equals("next")) {
			request.setAttribute("productId", product.getProdId());
			return PathResolver.getPath(request, FowardPage.IMG_LIST_QUERY);
		} else {
			saveMessage(request, ResourceBundleHelper.getSucessfulString());
			return PathResolver.getPath(request, FowardPage.PROD_LIST_QUERY);
		}
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
		return null;
	}
	
	/**
	 * 通过控制器到页面.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping(value = "/load")
	public String load(HttpServletRequest request, HttpServletResponse response) {
		return PathResolver.getPath(request, BackPage.PROD_EDIT_PAGE);
	}

	/**
	 * 通过控制器到页面.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param prodId
	 *            the prod id
	 * @return the string
	 */
	@RequestMapping(value = "/append/{prodId}")
	public String append (HttpServletRequest request, HttpServletResponse response,@PathVariable Long prodId) {
		 request.setAttribute("prodId",prodId) ;
		return PathResolver.getPath(request, BackPage.APPEND_PRODUCT);
	}
	
	/**
	 * Createsetp.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping(value = "/createsetp")
	public String createsetp (HttpServletRequest request, HttpServletResponse response) {
		return PathResolver.getPath(request, BackPage.CREATEP_RODUCT_STEP);
	}
	

	/**
	 * Update.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param prodId
	 *            the prod id
	 * @return the string
	 */
	@RequestMapping(value = "/update/{prodId}")
	public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable Long prodId) {
		checkNullable("prodId", prodId);
		Product product = productService.getProductById(prodId);
		String result = checkPrivilege(request, UserManager.getUsername(request), product.getUserName());
		if(result!=null){
			return result;
		}
		request.setAttribute("prod", product);
		return PathResolver.getPath(request, BackPage.PROD_EDIT_PAGE);
	}

}
