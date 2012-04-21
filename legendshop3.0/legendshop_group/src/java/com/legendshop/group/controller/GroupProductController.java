/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.group.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legendshop.core.AttributeKeys;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.AdminController;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.AuthorizationException;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.helper.FunctionUtil;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.group.page.GroupBackPage;
import com.legendshop.group.service.GroupProductService;
import com.legendshop.model.entity.Product;
import com.legendshop.model.entity.group.GroupProduct;
import com.legendshop.util.AppUtils;
import com.legendshop.util.sql.ConfigCode;

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
	
	
	
	@RequestMapping("/query")
	public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, GroupProduct groupProduct) {
		// Qbc查找方式
				HqlQuery hql = new HqlQuery(PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE, Integer.class), curPageNO);
				Map<String, String> map = new HashMap<String, String>();
				
				Product product = groupProduct.getProduct();
				if(product != null){
					fillParameter(hql,map,"prodName", "%" + product.getName().trim() + "%");
					fillParameter(hql,map,"sortId",String.valueOf(product.getSortId()) );
					fillParameter(hql,map,"nsortId",String.valueOf(product.getNsortId()) );
					fillParameter(hql,map,"subNsortId", String.valueOf(product.getSubNsortId()) );
					fillParameter(hql,map,"status",String.valueOf(product.getStatus()) );
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
				
				String QueryGroupProdCount = ConfigCode.getInstance().getCode("biz.getGroupProdCount", map);
				String QueryGroupProd = ConfigCode.getInstance().getCode("biz.getGroupProdList", map);
				hql.setAllCountString(QueryGroupProdCount);
				hql.setQueryString(QueryGroupProd);

				PageSupport ps = groupProductService.getGroupProductList(hql);
				savePage(ps, request);
				request.setAttribute("prod", product);
				
				return PathResolver.getPath(request, GroupBackPage.PROD_LIST_PAGE);
	}
	
	private void fillParameter(HqlQuery hql,Map<String, String> map,String key,String value){
		if (!AppUtils.isBlank(value)) {
			map.put(key,value);
			hql.addParams(value);
		}
	}

	
	@RequestMapping(value = "/save")
	public String save(HttpServletRequest request, HttpServletResponse response, GroupProduct entity) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@RequestMapping(value = "/delete/{id}")
	public String delete(HttpServletRequest request, HttpServletResponse response, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@RequestMapping(value = "/load")
	public String load(HttpServletRequest request, HttpServletResponse response) {
		return PathResolver.getPath(request, GroupBackPage.PROD_EDIT_PAGE);
	}

	
	@RequestMapping(value = "/update/{prodId}")
	public String update(HttpServletRequest request, HttpServletResponse response, Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	 
}
