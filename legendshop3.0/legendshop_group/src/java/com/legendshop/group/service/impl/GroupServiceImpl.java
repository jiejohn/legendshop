package com.legendshop.group.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.legendshop.core.AttributeKeys;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.constant.ProductTypeEnum;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.group.page.GroupBackPage;
import com.legendshop.group.page.GroupFrontPage;
import com.legendshop.model.entity.GroupProduct;
import com.legendshop.model.entity.Partner;
import com.legendshop.model.entity.Product;
import com.legendshop.model.entity.Sort;
import com.legendshop.spi.service.GroupProductService;
import com.legendshop.spi.service.GroupService;
import com.legendshop.spi.service.PartnerService;
import com.legendshop.spi.service.SortService;
import com.legendshop.spi.service.impl.AbstractService;
import com.legendshop.util.AppUtils;
import com.legendshop.util.sql.ConfigCode;

public class GroupServiceImpl extends AbstractService implements GroupService  {

	private SortService sortService;
	private GroupProductService groupProductService;
	private PartnerService partnerService;
	
	public String index(HttpServletRequest request, HttpServletResponse response, String curPageNO, String order,
			String seq, Product product) {

		String shopName = getShopName(request, response);
		List<Sort> groupSortList=sortService.getSort(shopName, ProductTypeEnum.GROUP.value(),null , null, false);
		for(Sort s:groupSortList){
			System.out.println(s.getSortName());
		}
		
		HqlQuery hql = new HqlQuery(PropertiesUtil.getObject(ParameterEnum.FRONT_PAGE_SIZE, Integer.class), curPageNO);
		Map<String, String> map = new HashMap<String, String>();
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

		return "/frontend/default/group/index";
	}
	
	public String view(HttpServletRequest request, HttpServletResponse response, Long id) {
		GroupProduct groupProduct=groupProductService.getGroupProduct(id);
		
		Long partnerId=groupProduct.getPartnerId();
		if(partnerId!=null){
			Partner partner=partnerService.getPartner(partnerId);
			request.setAttribute("partner", partner);
		}
		request.setAttribute("groupProduct", groupProduct);
		return PathResolver.getPath(request, GroupFrontPage.VIEW);
	}

	private void fillParameter(HqlQuery hql,Map<String, String> map,String key,Object value){
		if (!AppUtils.isBlank(value)) {
			map.put(key, String.valueOf(value));
			hql.addParams(value);
		}
	}
	

	public void setSortService(SortService sortService) {
		this.sortService = sortService;
	}

	public void setGroupProductService(GroupProductService groupProductService) {
		this.groupProductService = groupProductService;
	}

	public void setPartnerService(PartnerService partnerService) {
		this.partnerService = partnerService;
	}

}
