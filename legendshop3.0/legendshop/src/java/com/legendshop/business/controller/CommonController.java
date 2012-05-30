/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legendshop.business.service.HotsearchService;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.ProductTypeEnum;
import com.legendshop.model.entity.Hotsearch;
import com.legendshop.model.entity.Nsort;
import com.legendshop.model.entity.Sort;
import com.legendshop.spi.service.NsortService;
import com.legendshop.spi.service.SortService;

/**
 * The Class GroupController.
 */
@Controller
@RequestMapping("/common")
public class CommonController extends BaseController {
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(CommonController.class);

	@Autowired
	private SortService sortService;
	@Autowired
	private NsortService nsortService;
	@Autowired
	private HotsearchService hotsearchService; 

	@RequestMapping("/top")
	public String top(HttpServletRequest request, HttpServletResponse response) {
		log.debug("Top starting calling");
		Long sortId=ServletRequestUtils.getLongParameter(request, "sortId",-1);
		String shopName = getShopName(request, response);
		List<Hotsearch> searchList=hotsearchService.getHotsearch(shopName);
		
		List<Sort> headerSortList = sortService.getSort(shopName, ProductTypeEnum.PRODUCT.value(), 1, null, false);
		List<Sort> navigationSortList = sortService.getSort(shopName, ProductTypeEnum.PRODUCT.value(), null, 1, false);
		
		List<Nsort> nsortList=nsortService.getNavigationNsort(shopName);
		
		Map<Long,List<Nsort>> deputyMap=new HashMap<Long, List<Nsort>>();		
		Map<Long,List<Nsort>> sTreeMap=new HashMap<Long, List<Nsort>>();
		Map<Long,List<Nsort>> tTreeMap=new HashMap<Long, List<Nsort>>();
		
		for(Nsort nsort:nsortList){
			Integer sortDeputy=nsort.getSortDeputy();
			Long sid=nsort.getSortId();
			Long pid=nsort.getParentNsortId();
			if(sortDeputy!=null&&sortDeputy.longValue()==1){
				
				List<Nsort> sList=deputyMap.get(sid);
				if(sList==null){
					sList=new ArrayList<Nsort>();
					deputyMap.put(sid, sList);
				}
				sList.add(nsort);
			}
			
			if(pid==null){
				List<Nsort> nsList=sTreeMap.get(sid.longValue());
				if(nsList==null){
					nsList=new ArrayList<Nsort>();
					sTreeMap.put(sid.longValue(), nsList);
				}
				nsList.add(nsort);
			}else{
				List<Nsort> nsList=tTreeMap.get(pid.longValue());
				if(nsList==null){
					nsList=new ArrayList<Nsort>();
					tTreeMap.put(pid.longValue(), nsList);
				}
				nsList.add(nsort);				
			}
			
		}
		request.setAttribute("searchList", searchList);
		request.setAttribute("deputyMap", deputyMap);
		request.setAttribute("sTreeMap", sTreeMap);
		request.setAttribute("tTreeMap", tTreeMap);
		request.setAttribute("headerSortList", headerSortList);
		request.setAttribute("navigationSortList", navigationSortList);
		request.setAttribute("currentSortId", sortId);
		return "/common/top";
	}

	@RequestMapping("/foot")
	public String foot(HttpServletRequest request, HttpServletResponse response){
		
		return "/common/foot";
	}

}
