/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.group.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.annotations.SortType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legendshop.core.AttributeKeys;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.constant.ProductTypeEnum;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.AuthorizationException;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.helper.FunctionUtil;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.service.GroupProductService;
import com.legendshop.core.service.SortService;
import com.legendshop.model.entity.GroupProduct;
import com.legendshop.model.entity.Product;
import com.legendshop.model.entity.Sort;
import com.legendshop.util.AppUtils;
import com.legendshop.util.sql.ConfigCode;

/**
 * The Class GroupController.
 */
@Controller
@RequestMapping("/group")
public class GroupController extends BaseController {
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(GroupController.class);
	

	@Autowired
	private SortService sortService;

	@Autowired
	private GroupProductService groupProductService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request, HttpServletResponse response,String curPageNO,String order,GroupProduct groupProduct) {
		log.debug("Index starting calling");
		
		String shopName = getShopName(request, response);
		
		List<Sort> groupSortList=sortService.getSort(shopName, ProductTypeEnum.GROUP.value(),null , null, false);
		
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

		fillParameter(hql,map,"userName",shopName);
		

		if (!FunctionUtil.isDataForExport(hql, request)) {// 非导出情况
			hql.setPageSize(PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE, Integer.class));
		}
		
		//TODO order by xxx
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
		request.setAttribute("groupSortList", groupSortList);
		return "/group/default/index";
//		return PathResolver.getPath(request, null);		
	}

	private void fillParameter(HqlQuery hql,Map<String, String> map,String key,Object value){
		if (!AppUtils.isBlank(value)) {
			map.put(key, String.valueOf(value));
			hql.addParams(value);
		}
	}
	@RequestMapping(value = "/view/{id}")
	public String view(HttpServletRequest request, HttpServletResponse response,@PathVariable String id) {
		log.debug("view starting calling");
		return "/group/default/view";
	}
	
	@RequestMapping("/sort")
	public String sort(HttpServletRequest request, HttpServletResponse response) {
		log.debug("sort starting calling");
		return "/group/default/sort";
	}
	
	@RequestMapping("/question")
	public String question(HttpServletRequest request, HttpServletResponse response) {
		log.debug("question starting calling");
		return "/group/default/question";
	}
	

}
