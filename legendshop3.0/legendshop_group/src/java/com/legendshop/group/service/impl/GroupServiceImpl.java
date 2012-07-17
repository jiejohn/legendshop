package com.legendshop.group.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.LocaleResolver;

import com.legendshop.core.AttributeKeys;
import com.legendshop.core.constant.CommonPage;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.constant.ProductTypeEnum;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.ErrorCodes;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.group.page.GroupFrontPage;
import com.legendshop.group.page.GroupTilesPage;
import com.legendshop.model.UserMessages;
import com.legendshop.model.entity.GroupProduct;
import com.legendshop.model.entity.Partner;
import com.legendshop.model.entity.Product;
import com.legendshop.model.entity.Sort;
import com.legendshop.spi.constants.Constants;
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

	private LocaleResolver localeResolver;
	

	@Override
	public String getIndex(HttpServletRequest request, HttpServletResponse response, String curPageNO, String order,
			String seq, Product product) {

		String shopName = getCurrentShopName(request, response);
		List<Sort> groupSortList=sortService.getSort(shopName, ProductTypeEnum.GROUP.value(),null , null, false);
		
		//TODO place into service
		HqlQuery hql = new HqlQuery(PropertiesUtil.getObject(ParameterEnum.PAGE_SIZE, Integer.class), curPageNO);
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
		ps.setToolBar(localeResolver.resolveLocale(request),Constants.SIMPLE_PAGE_PROVIDER);
		ps.savePage(request);
		request.setAttribute("prod", product);
		request.setAttribute("order", order);
		request.setAttribute("seq", seq);
		request.setAttribute("groupSortList", groupSortList);

		//return PathResolver.getPath(request, GroupFrontPage.INDEX);
		//TODO
		return PathResolver.getPath(request, GroupTilesPage.GINDEX);
	}
	
	@Override
	public String getView(HttpServletRequest request, HttpServletResponse response, Long id) {
		GroupProduct groupProduct=groupProductService.getGroupProduct(id);
		if(groupProduct!=null){
			Long partnerId=groupProduct.getPartnerId();
			if(partnerId!=null){
				Partner partner=partnerService.getPartner(partnerId);
				request.setAttribute("partner", partner);
			}
			request.setAttribute("groupProduct", groupProduct);
			return PathResolver.getPath(request, GroupFrontPage.VIEW);
		}else{
			UserMessages uem = new UserMessages();
			Locale locale = localeResolver.resolveLocale(request);
			uem.setTitle(ResourceBundleHelper.getString(locale, "product.not.found"));
			uem.setDesc(ResourceBundleHelper.getString(locale, "product.status.check"));
			uem.setCode(ErrorCodes.PRODUCT_NO_FOUND);
			request.setAttribute(UserMessages.MESSAGE_KEY, uem);
			return PathResolver.getPath(request, CommonPage.ERROR_PAGE);
		}

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

	@Override
	public void setLocaleResolver(LocaleResolver localeResolver) {
		this.localeResolver = localeResolver;
	}

}
