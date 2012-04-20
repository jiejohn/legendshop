/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.group.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legendshop.core.UserManager;
import com.legendshop.core.base.AdminController;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.AuthorizationException;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.helper.FunctionUtil;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.group.service.GroupProductService;
import com.legendshop.model.entity.Product;
import com.legendshop.model.group.GroupProduct;
import com.legendshop.util.AppUtils;

/**
 * The Class GroupController.
 */
@Controller
@RequestMapping("/admin/group/product")
public class GroupProductController extends BaseController implements AdminController<GroupProduct,Long>{
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(GroupProductController.class);
	
	/** The product service. */
	@Autowired
	private GroupProductService groupProductService;
	
	
	@Override
	@RequestMapping("/query")
	public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, GroupProduct groupProduct) {
		// Qbc查找方式
				CriteriaQuery cq = new CriteriaQuery(Product.class, curPageNO);
				Product product = groupProduct.getProduct();
				if (!AppUtils.isBlank(groupProduct.getProduct().getName())) {
					cq.like("name", "%" + groupProduct.getProduct().getName().trim() + "%");
				}

				cq.eq("sortId", groupProduct.getProduct().getSortId());
				cq.eq("nsortId", groupProduct.getProduct().getNsortId());
				cq.eq("subNsortId", groupProduct.getProduct().getSubNsortId());
				cq.eq("commend", groupProduct.getProduct().getCommend());
				cq.eq("status", groupProduct.getProduct().getStatus());
				cq.eq("brandId", groupProduct.getProduct().getBrandId());
				if (FunctionUtil.haveViewAllDataFunction(request)) {
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

				if (!FunctionUtil.isDataForExport(cq, request)) {// 非导出情况
					cq.setPageSize(PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE, Integer.class));
				}
				if (!FunctionUtil.isDataSortByExternal(cq, request)) {
					cq.addOrder("desc", "recDate");
				}
				cq.add();
				PageSupport ps = groupProductService.getGroupProductList(cq);
				savePage(ps, request);
				request.setAttribute("prod", product);
				//return PathResolver.getPath(request, BackPage.PROD_LIST_PAGE);
				return null;
	}

	@Override
	@RequestMapping(value = "/save")
	public String save(HttpServletRequest request, HttpServletResponse response, GroupProduct entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RequestMapping(value = "/delete/{id}")
	public String delete(HttpServletRequest request, HttpServletResponse response, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RequestMapping(value = "/load")
	public String load(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RequestMapping(value = "/update/{prodId}")
	public String update(HttpServletRequest request, HttpServletResponse response, Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	 
}
