/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.group.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.legendshop.core.AttributeKeys;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.AuthorizationException;
import com.legendshop.core.exception.BusinessException;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.exception.ErrorCodes;
import com.legendshop.core.helper.FileProcessor;
import com.legendshop.core.helper.FunctionUtil;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.RealPathUtil;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.group.page.GroupBackPage;
import com.legendshop.group.page.GroupFowardPage;
import com.legendshop.model.entity.GroupProduct;
import com.legendshop.model.entity.Product;
import com.legendshop.spi.service.GroupProductService;
import com.legendshop.util.AppUtils;
import com.legendshop.util.SafeHtml;
import com.legendshop.util.sql.ConfigCode;

/**
 * The Class GroupController.
 */
@Controller
@RequestMapping("/admin/group/product")
public class GroupProductController extends BaseController{
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(GroupProductController.class);
	
	/** The product service. */
	@Autowired
	private GroupProductService groupProductService;
	
	
	
	@RequestMapping("/query")
	public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, GroupProduct groupProduct) {
		// Qbc查找方式
				HqlQuery hql = new HqlQuery(PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE, Integer.class), curPageNO);
				Map<String, String> map = new HashMap<String, String>();
				
				Product product = groupProduct.getProduct();
				if(product != null){
					fillParameter(hql,map,"sortId",product.getSortId());
					fillParameter(hql,map,"nsortId",product.getNsortId());
					fillParameter(hql,map,"subNsortId", product.getSubNsortId() );
					fillParameter(hql,map,"status",product.getStatus());
					fillParameter(hql,map,"brandId",product.getBrandId());
					if(AppUtils.isNotBlank(product.getName())){
						fillParameter(hql,map,"name", "%" + product.getName().trim() + "%");
					}
				}

				//数据权限
				if (!FunctionUtil.haveViewAllDataFunction(request)) {
					String userName = UserManager.getUsername(request.getSession());
					if (userName == null) {
						throw new AuthorizationException("you are not logon yet!",EntityCodes.PROD);
					}
					fillParameter(hql,map,"userName",userName);
				} else {
					// 管理员
					if (product!=null &&  AppUtils.isNotBlank(product.getUserName())) {
						fillParameter(hql,map,"userName", product.getUserName());
					}
				}
				

				if (!FunctionUtil.isDataForExport(hql, request)) {// 非导出情况
					hql.setPageSize(PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE, Integer.class));
				}
				
				if (!FunctionUtil.isDataSortByExternal(hql, request, map)) {
					map.put(AttributeKeys.ORDER_INDICATOR, "order by p.modifyDate desc");
				}
				
				String QueryGroupProdCount = ConfigCode.getInstance().getCode("group.getGroupProdCount", map);
				String QueryGroupProd = ConfigCode.getInstance().getCode("group.getGroupProdList", map);
				hql.setAllCountString(QueryGroupProdCount);
				hql.setQueryString(QueryGroupProd);

				PageSupport ps = groupProductService.getGroupProductList(hql);
				
				savePage(ps, request);
				request.setAttribute("prod", product);
				
				return PathResolver.getPath(request,response,GroupBackPage.PROD_LIST_PAGE);
	}
	
	private void fillParameter(HqlQuery hql,Map<String, String> map,String key,Object value){
		if (!AppUtils.isBlank(value)) {
			map.put(key, String.valueOf(value));
			hql.addParams(value);
		}
	}

	
	@RequestMapping(value = "/save")
	public String save(HttpServletRequest request, HttpServletResponse response, GroupProduct entity) {
		String name = UserManager.getUsername(request);
		String result = checkLogin(request,response,name);
		if(result != null){
			return result;
		}
		Product product = entity.getProduct();
		entity.setProdId(product.getProdId());
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
				Product origin = groupProductService.getProductById(product.getProdId());
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

				groupProductService.updateProduct(product, origin,entity);
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
				groupProductService.saveProduct(product,entity);
			}
		} catch (Exception e) {
			// delete file uploaded
			if ((formFile != null) && (formFile.getSize() > 0)) {
				FileProcessor.deleteFile(RealPathUtil.getBigPicRealPath()  + File.separator +  filename);
			}
			throw new BusinessException(e, "save group product error",EntityCodes.GROUP_PROD, ErrorCodes.BUSINESS_ERROR);
		}

		saveMessage(request, ResourceBundleHelper.getSucessfulString());
		return PathResolver.getPath(request,response,GroupFowardPage.PROD_LIST_QUERY);
	}

	
	@RequestMapping(value = "/load")
	public String load(HttpServletRequest request, HttpServletResponse response) {
		return PathResolver.getPath(request,response,GroupBackPage.PROD_EDIT_PAGE);
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
	@RequestMapping(value = "/load/{prodId}")
	public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable Long prodId) {
		checkNullable("prodId", prodId);
		GroupProduct product = groupProductService.getGroupProduct(prodId);
		String result = checkPrivilege(request, UserManager.getUsername(request), product.getProduct().getUserName());
		if(result!=null){
			return result;
		}
		request.setAttribute("prod", product);
		return PathResolver.getPath(request,response,GroupBackPage.PROD_EDIT_PAGE);
	}

	 
}
