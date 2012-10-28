/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legendshop.business.common.page.FowardPage;
import com.legendshop.business.common.page.FrontPage;
import com.legendshop.business.common.page.TilesPage;
import com.legendshop.business.service.HotsearchService;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.ErrorCodes;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.model.entity.Hotsearch;
import com.legendshop.model.entity.Nsort;
import com.legendshop.model.entity.Sort;
import com.legendshop.spi.constants.Constants;
import com.legendshop.spi.service.NsortService;
import com.legendshop.spi.service.ProductService;
import com.legendshop.spi.service.SortService;
import com.legendshop.util.AppUtils;

/**
 * 产品分类控制器。.
 */
@Controller
public class SortController extends BaseController {

	/** The log. */
	private final Logger log = LoggerFactory.getLogger(SortController.class);

	@Autowired
	private SortService sortService;

	@Autowired
	private NsortService nsortService;

	@Autowired
	private HotsearchService hotsearchService;

	@Autowired
	private ProductService productService;

	/**
	 * Topsort.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping("/topsort")
	public String topsort(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("sortList", sortService.getSort(ThreadLocalContext.getCurrentShopName(request,response), true));
		return PathResolver.getPath(request,response,FrontPage.TOPSORT);
	}

	/**
	 * Nsort.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param sortId
	 *            the sort id
	 * @param nsortId
	 *            the nsort id
	 * @return the string
	 */
	@RequestMapping("/nsort/{sortId}-{nsortId}")
	public String nsort(HttpServletRequest request, HttpServletResponse response, @PathVariable Long sortId,
			@PathVariable Long nsortId) {
		if (nsortId == null || sortId == null) {
			log.error("sortId or nsortId is null! ");
			return PathResolver.getPath(request,response,FowardPage.INDEX_QUERY);
		}
		return sortService.getSecSort(request, response, sortId, nsortId, null);
	}

	/**
	 * Nsort.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param sortId
	 *            the sort id
	 * @param nsortId
	 *            the nsort id
	 * @param subNsortId
	 *            the sub nsort id
	 * @return the string
	 */
	@RequestMapping("/nsort")
	public String nsort(HttpServletRequest request, HttpServletResponse response, Long sortId, Long nsortId,
			Long subNsortId) {
		if (nsortId == null || sortId == null) {
			log.error("sortId or nsortId is null! ");
			return PathResolver.getPath(request,response,FowardPage.INDEX_QUERY);
		}
		return sortService.getSecSort(request, response, sortId, nsortId, subNsortId);
	}

	/**
	 * Sort.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param curPageNO
	 *            the cur page no
	 * @param sortId
	 *            the sort id
	 * @return the string
	 */
	@RequestMapping("/sort/{sortId}")
	public String sort(HttpServletRequest request, HttpServletResponse response, String curPageNO,
			@PathVariable Long sortId,String order,String seq) {
		return parseSort(request, response,curPageNO, sortId, order, seq);
	}

	/**
	 * Sort by id.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param curPageNO
	 *            the cur page no
	 * @param sortId
	 *            the sort id
	 * @return the string
	 */
	@RequestMapping("/sort")
	public String sortById(HttpServletRequest request, HttpServletResponse response, String curPageNO, Long sortId,String order,String seq) {
		return parseSort(request, response,curPageNO, sortId, order, seq);
	}

	/**
	 * @param request
	 * @param curPageNO
	 * @param sortId
	 * @return
	 */
	private String parseSort(HttpServletRequest request, HttpServletResponse response,String curPageNO, Long sortId,String order,String seq) {
		if (curPageNO == null) {
			curPageNO = "1";
		}
		if (sortId == null) {
			return PathResolver.getPath(request,response,FowardPage.INDEX_QUERY);
		}
		Sort sort = sortService.getSortById(sortId);
		if (sort == null) {
			throw new NotFoundException("sort can not be null", ErrorCodes.ENTITY_NO_FOUND);
		}
		ThreadLocalContext.setCurrentShopName(request, response, sort.getUserName());
		sortService.getAndSetAdvertisement(request,response,sort.getUserName());
		// 热门搜索
		List<Hotsearch> searchList = hotsearchService.getHotsearch(sort.getUserName(), sortId);
		if (!AppUtils.isBlank(searchList)) {
			request.setAttribute("searchList", searchList);
		}

		List<Nsort> nsortList = nsortService.getNsortBySortId(sortId);
		request.setAttribute("sort", sort);
		request.setAttribute("nsortList", nsortList);
		String userName = UserManager.getUsername(request.getSession());
		log.info("[{}],{},{},sort", new Object[] { request.getRemoteAddr(), userName == null ? "" : userName,
				ThreadLocalContext.getCurrentShopName(request, response) });

		PageSupport ps = productService.getProdDetail(localeResolver.resolveLocale(request), curPageNO, sortId);
		ps.savePage(request);
		String result = PathResolver.getPath(request,response,TilesPage.PRODUCTSORT);
		return result;
	}

	@RequestMapping("/allproductsort")
	public String toAllProductSorts(HttpServletRequest request, HttpServletResponse response, String curPageNO,
			Long sortId) {
		// TODO

		return PathResolver.getPath(request,response,TilesPage.ALL_PRODUCT_SORT);
	}

}
