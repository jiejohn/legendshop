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
import com.legendshop.business.service.BrandService;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.AdminController;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.AuthorizationException;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.core.helper.FileProcessor;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.RealPathUtil;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.model.entity.Brand;

/**
 * 产品品牌控制器。.
 */
@Controller
@RequestMapping("/admin/brand")
public class BrandAdminController extends BaseController implements AdminController<Brand, Long>{
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(BrandAdminController.class);
	
	/** The brand service. */
	@Autowired
	private BrandService brandService;

	/* (non-Javadoc)
	 * @see com.legendshop.core.base.AdminController#query(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.String, java.lang.Object)
	 */
	@Override
	@RequestMapping("/query")
	public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, Brand brand) {
		CriteriaQuery cq = new CriteriaQuery(Brand.class, curPageNO);
		cq.setPageSize(PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE, Integer.class));
		cq = hasAllDataFunction(cq, request, StringUtils.trim(brand.getUserName()));
		cq.add();
		PageSupport ps = brandService.getDataByCriteriaQuery(cq);
		savePage(ps, request);
		request.setAttribute("bean", brand);
		return PathResolver.getPath(BackPage.BRAND_LIST_PAGE);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.core.base.AdminController#save(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	@RequestMapping(value = "/save")
	public String save(HttpServletRequest request, HttpServletResponse response, Brand brand) {
		Brand originbrand = null;
		String brandPic = null; //
		String name = UserManager.getUsername(request.getSession());
		String subPath = name + "/brand/";
		if ((brand != null) && (brand.getBrandId() != null)) { // update
			originbrand = brandService.getBrand(brand.getBrandId());
			if (originbrand == null) {
				throw new NotFoundException("Origin Brand is NULL",EntityCodes.BRAND);
			}
			String originBrandPic = originbrand.getBrandPic();
			if (!CommonServiceUtil.haveViewAllDataFunction(request)) {
				if (!name.equals(originbrand.getUserName())) {
					throw new AuthorizationException("Can't edit Brand does not own to you!",EntityCodes.BRAND);
				}
			}
			originbrand.setMemo(brand.getMemo());
			originbrand.setBrandName(brand.getBrandName());
			if (brand.getFile() != null && brand.getFile().getSize() > 0) {
				brandPic = FileProcessor.uploadFileAndCallback(brand.getFile(), subPath, "bra" + name);
				originbrand.setBrandPic(brandPic);
				String url = RealPathUtil.getBigPicRealPath() + "/" + originBrandPic;
				FileProcessor.deleteFile(url);
			}
			brandService.update(originbrand);

		} else {
			if (brand.getFile() != null && brand.getFile().getSize() > 0) {
				brandPic = FileProcessor.uploadFileAndCallback(brand.getFile(), subPath, "bra" + name);
				brand.setBrandPic(brandPic);
			}
			brand.setUserId(UserManager.getUserId(request.getSession()));
			brand.setUserName(UserManager.getUsername(request.getSession()));
			brandService.save(brand);
		}
		saveMessage(request, ResourceBundleHelper.getSucessfulString());
		return PathResolver.getPath(FowardPage.BRAND_LIST_QUERY);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.core.base.AdminController#delete(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	@RequestMapping(value = "/delete/{id}")
	public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable
	Long id) {
		Brand brand = brandService.getBrand(id);
		String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), brand.getUserName());
		if(result!=null){
			return result;
		}
		log.info("{}, delete Brand Picture {}", brand.getUserName(), brand.getBrandPic());
		brandService.delete(id);
		String url = RealPathUtil.getBigPicRealPath() + "/" + brand.getBrandPic();
		FileProcessor.deleteFile(url);
		saveMessage(request, ResourceBundleHelper.getDeleteString());
		return PathResolver.getPath(FowardPage.BRAND_LIST_QUERY);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.core.base.AdminController#load(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@RequestMapping(value = "/load")
	public String load(HttpServletRequest request, HttpServletResponse response) {
		return PathResolver.getPath(BackPage.BRAND_EDIT_PAGE);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.core.base.AdminController#update(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	@RequestMapping(value = "/update/{id}")
	public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
		Brand brand = brandService.getBrand(id);
		String result = checkPrivilege(request, UserManager.getUsername(request.getSession()), brand.getUserName());
		if(result!=null){
			return result;
		}
		request.setAttribute("bean", brand);
		return PathResolver.getPath(BackPage.BRAND_EDIT_PAGE);
	}

}
