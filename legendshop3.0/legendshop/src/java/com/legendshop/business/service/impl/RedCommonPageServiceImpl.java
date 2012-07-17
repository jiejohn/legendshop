/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;

import com.legendshop.business.common.page.FrontPage;
import com.legendshop.business.dao.HotsearchDao;
import com.legendshop.business.dao.NsortDao;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.constant.ProductTypeEnum;
import com.legendshop.model.entity.Hotsearch;
import com.legendshop.model.entity.Nsort;
import com.legendshop.model.entity.Sort;

/**
 * The Class DefaultCommonPageServiceImpl.
 */
public class RedCommonPageServiceImpl extends AbstractCommonPageService {

	/** The hotsearch dao. */
	private HotsearchDao hotsearchDao;
	
	/** The nsort dao. */
	private NsortDao nsortDao;
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.CommonPageService#getTop(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String getTop(HttpServletRequest request, HttpServletResponse response) {

		Long sortId=ServletRequestUtils.getLongParameter(request, "sortId",-1);
		String shopName = getCurrentShopName(request, response);
		List<Hotsearch> searchList = hotsearchDao.getHotsearch(shopName);
		
		List<Sort> headerSortList = sortDao.getSort(shopName, ProductTypeEnum.PRODUCT.value(), 1, null, false);
		List<Sort> navigationSortList = sortDao.getSort(shopName, ProductTypeEnum.PRODUCT.value(), null, 1, false);
		
		List<Nsort> nsortList = nsortDao.getNavigationNsort(shopName);
		
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

		return PathResolver.getPath(request, FrontPage.TOP);
	}

	/**
	 * Sets the hotsearch dao.
	 * 
	 * @param hotsearchDao
	 *            the new hotsearch dao
	 */
	public void setHotsearchDao(HotsearchDao hotsearchDao) {
		this.hotsearchDao = hotsearchDao;
	}

	/**
	 * Sets the nsort dao.
	 * 
	 * @param nsortDao
	 *            the new nsort dao
	 */
	public void setNsortDao(NsortDao nsortDao) {
		this.nsortDao = nsortDao;
	}


}
