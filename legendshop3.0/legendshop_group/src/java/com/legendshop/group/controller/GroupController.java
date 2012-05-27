/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.group.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legendshop.core.AttributeKeys;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.constant.ProductTypeEnum;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.GroupProduct;
import com.legendshop.model.entity.Partner;
import com.legendshop.model.entity.Product;
import com.legendshop.model.entity.Sort;
import com.legendshop.spi.constants.NewsPositionEnum;
import com.legendshop.spi.service.GroupProductService;
import com.legendshop.spi.service.NewsService;
import com.legendshop.spi.service.PartnerService;
import com.legendshop.spi.service.SortService;
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

	@Autowired
	private PartnerService partnerService;
	
	@Autowired
	private NewsService newsService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request, HttpServletResponse response,String curPageNO,String order,String seq,Product product) {
		log.debug("Index starting calling");
		
		String shopName = getShopName(request, response);
		
		List<Sort> groupSortList=sortService.getSort(shopName, ProductTypeEnum.GROUP.value(),null , null, false);
		
		HqlQuery hql = new HqlQuery(PropertiesUtil.getObject(ParameterEnum.FRONT_PAGE_SIZE, Integer.class), curPageNO);
		Map<String, String> map = new HashMap<String, String>();
//		Product product = groupProduct.getProduct();
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

		
		//TODO order by xxx
//		if (!FunctionUtil.isDataSortByExternal(hql, request, map)) {
		if("hot".equals(order)){
			map.put(AttributeKeys.ORDER_INDICATOR, "order by p.buys desc");			
		}else if("price".equals(order)){
			if("desc".equalsIgnoreCase(seq)){
				seq="asc";
			}else{
				seq="desc";
			}
			map.put(AttributeKeys.ORDER_INDICATOR, "order by p.cash "+seq);
			
		}else if("time".equals(order)){
			map.put(AttributeKeys.ORDER_INDICATOR, "order by p.endDate asc");
			
		}else{
			map.put(AttributeKeys.ORDER_INDICATOR, "order by p.modifyDate desc");			
		}
		
//		}
		
		String QueryGroupProdCount = ConfigCode.getInstance().getCode("group.getGroupProdCount", map);
		String QueryGroupProd = ConfigCode.getInstance().getCode("group.getGroupProdList", map);
		hql.setAllCountString(QueryGroupProdCount);
		hql.setQueryString(QueryGroupProd);

		PageSupport ps = groupProductService.getGroupProductList(hql);
		ps.savePage(request);
		request.setAttribute("prod", product);
		request.setAttribute("order", order);
		request.setAttribute("seq", seq);
		request.setAttribute("groupSortList", groupSortList);
		return "/group/default/index";
	}

	private void fillParameter(HqlQuery hql,Map<String, String> map,String key,Object value){
		if (!AppUtils.isBlank(value)) {
			map.put(key, String.valueOf(value));
			hql.addParams(value);
		}
	}
	
	
	
	@RequestMapping(value = "/clientServicePanel")
	public String clientServicePannel(HttpServletRequest request, HttpServletResponse response) {
		String shopName = getShopName(request, response);
		request.setAttribute("groupNewsTopList", newsService.getNews(shopName, NewsPositionEnum.NEWS_GROUP_TOP, 10));
		return "/group/default/clientServicePanel";
	}
	
	
	
	@RequestMapping(value = "/view/{id}")
	public String view(HttpServletRequest request, HttpServletResponse response,@PathVariable Long id) {
		log.debug("view starting calling");
		GroupProduct groupProduct=groupProductService.getGroupProduct(id);
		
		Long partnerId=groupProduct.getPartnerId();
		if(partnerId!=null){
			Partner partner=partnerService.getPartner(partnerId);
			request.setAttribute("partner", partner);
		}
		request.setAttribute("groupProduct", groupProduct);
		return "/group/default/view";
	}
	
	@RequestMapping("/sort")
	public String sort(HttpServletRequest request, HttpServletResponse response) {
		log.debug("sort starting calling");
		return "/group/default/sort";
	}
	
	@RequestMapping("/questionPanel")
	public String questionPanel(HttpServletRequest request, HttpServletResponse response) {
		log.debug("question starting calling");
		String shopName = getShopName(request, response);
		request.setAttribute("groupNewsBottomList", newsService.getNews(shopName, NewsPositionEnum.NEWS_GROUP_BOTTOM, 10));
		return "/group/default/questionPanel";
	}
	
	@RequestMapping("/question")
	public String question(HttpServletRequest request, HttpServletResponse response) {
		log.debug("question starting calling");
		return "/group/default/question";
	}
	

}
