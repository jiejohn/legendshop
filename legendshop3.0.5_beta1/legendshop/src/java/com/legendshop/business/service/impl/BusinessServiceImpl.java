/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.legendshop.business.common.page.FowardPage;
import com.legendshop.business.common.page.FrontPage;
import com.legendshop.business.common.page.TilesPage;
import com.legendshop.business.dao.ExternalLinkDao;
import com.legendshop.business.dao.MyleagueDao;
import com.legendshop.business.dao.NewsDao;
import com.legendshop.business.dao.NsortDao;
import com.legendshop.business.dao.ProductDao;
import com.legendshop.business.dao.SortDao;
import com.legendshop.business.form.SearchForm;
import com.legendshop.business.service.BusinessService;
import com.legendshop.core.constant.LuceneIndexerEnum;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.core.helper.VisitHistoryHelper;
import com.legendshop.core.page.PagerUtil;
import com.legendshop.model.entity.ExternalLink;
import com.legendshop.model.entity.Nsort;
import com.legendshop.model.entity.Product;
import com.legendshop.model.entity.ProductDetail;
import com.legendshop.model.entity.ShopDetail;
import com.legendshop.model.entity.Sort;
import com.legendshop.search.SearchArgs;
import com.legendshop.search.SearchFacade;
import com.legendshop.search.SearchResult;
import com.legendshop.spi.constants.Constants;
import com.legendshop.spi.constants.NewsPositionEnum;
import com.legendshop.spi.dao.ShopDetailDao;
import com.legendshop.util.AppUtils;
import com.legendshop.util.ip.IPSeeker;

/**
 * 
 * 业务Service，主要对前台
 * 
 * 官方网站：http://www.legendesign.net
 */
public class BusinessServiceImpl extends BaseServiceImpl implements BusinessService {

	/** The log. */
	private static Logger log = LoggerFactory.getLogger(BusinessServiceImpl.class);

	/** The default int. */
	private final Long defaultInt = 0l;

	/** The sort dao. */
	private SortDao sortDao;

	/** The news dao. */
	private NewsDao newsDao;

	/** The nsort dao. */
	private NsortDao nsortDao;

	/** The external link dao. */
	private ExternalLinkDao externalLinkDao;

	/** The search facade. */
	private SearchFacade searchFacade;

	/** The product dao. */
	private ProductDao productDao;

	/** The myleague dao. */
	private MyleagueDao myleagueDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.service.impl.BusinessService#search(javax.servlet
	 * .http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * com.legendshop.business.action.form.SearchForm)
	 */
	@Override
	public String search(HttpServletRequest request, HttpServletResponse response, SearchForm searchForm) {
		Sort sort = null;
		List<Nsort> nsortList = null;
		// 1、关键字不能为空
		if (AppUtils.isBlank(searchForm.getKeyword())) {
			log.error("search keyword can't be null!");
			return PathResolver.getPath(request, response, FowardPage.INDEX_QUERY);
		}
		// 2、查找对应的Sort,defaultValue=0表示没有选择类型
		if (!AppUtils.isBlank(searchForm.getSortId()) && !defaultInt.equals(searchForm.getSortId())) {
			sort = sortDao.getSort(searchForm.getSortId());
			if (sort != null) {
				setShopName(request, response, sort.getUserName());
				request.setAttribute("sort", sort);
				request.setAttribute("CurrentSortId", sort.getSortId());
				nsortList = nsortDao.getNsortBySortId(searchForm.getSortId());
				request.setAttribute("nsortList", nsortList);
			}
		}

		try {
			// Qbc查找方式
			CriteriaQuery cq = new CriteriaQuery(Product.class, searchForm.getCurPageNOTop());
			cq.setPageSize(PropertiesUtil.getObject(ParameterEnum.FRONT_PAGE_SIZE, Integer.class) * 2);
			cq.eq("userName", ThreadLocalContext.getCurrentShopName(request, response));
			Criterion c = null;
			if (!AppUtils.isBlank(searchForm.getKeyword())) {
				String[] keywords = AppUtils.searchByKeyword(searchForm.getKeyword());
				for (String word : keywords) {
					Criterion temp = Restrictions.like("name", "%" + word + "%");
					if (c == null) {
						c = temp;
					} else {
						c = Restrictions.or(c, temp);
					}
				}
			}
			if (!AppUtils.isBlank(searchForm.getSortId()) && !defaultInt.equals(searchForm.getSortId())) {
				if (c == null) {
					c = Restrictions.eq("sortId", searchForm.getSortId());
				} else {
					c = Restrictions.and(c, Restrictions.eq("sortId", searchForm.getSortId()));
				}

			}
			if (c != null) {
				cq.add(c);
			}

			cq.addOrder("desc", "prodId");
			
			PageSupport ps = productDao.getProdDetail(cq);
			request.setAttribute("curPageNO", new Integer(ps.getCurPageNO()));
			request.setAttribute("offset", new Integer(ps.getOffset() + 1));
			request.setAttribute("prodDetailList", ps.getResultList());
			request.setAttribute("searchForm", searchForm);
			if (ps.hasMutilPage()) {
				request.setAttribute("toolBar",
						ps.getToolBar(localeResolver.resolveLocale(request), Constants.SIMPLE_PAGE_PROVIDER));
			}
		} catch (Exception e) {
			log.error("getProdDetail", e);
			return PathResolver.getPath(request, response, FowardPage.INDEX_QUERY);
		}
		return PathResolver.getPath(request, response, TilesPage.PRODUCTSORT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.service.impl.BusinessService#searchall(javax.
	 * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public String searchall(HttpServletRequest request, HttpServletResponse response, String keyword, Integer entityType) {
		if (entityType == null) {
			entityType = 0;
		}

		String priceStartValue = request.getParameter("priceStartValue");
		String priceEndValue = request.getParameter("priceEndValue");

		String curPageNOStr = request.getParameter("curPageNO");
		if (AppUtils.isBlank(curPageNOStr)) {
			curPageNOStr = "1";
		}
		log.debug("search by keyword {}", keyword);
		if (AppUtils.isBlank(keyword)) {
			return PathResolver.getPath(request, response, FrontPage.ALL);
		}
		int curPageNO = PagerUtil.getCurPageNO(curPageNOStr);// 当前页
		SearchArgs args = new SearchArgs();
		args.setKeywords(keyword);
		if (AppUtils.isNotBlank(priceStartValue)) {
			try {
				args.setPriceStart(Double.valueOf(priceStartValue));
				request.setAttribute("priceStart", priceStartValue);
			} catch (Exception e) {
				log.error("error number of priceStart {}", priceStartValue);
			}

		}
		if (AppUtils.isNotBlank(priceEndValue)) {
			try {
				args.setPriceEnd(Double.valueOf(priceEndValue));
				request.setAttribute("priceEnd", priceEndValue);
			} catch (Exception e) {
				log.error("error number of priceEnd {}", priceEndValue);
			}

		}
		args.setEntityType(entityType);
		if (LuceneIndexerEnum.SEARCH_ENTITY_SHOPDETAIL.equals(args.getEntityType())) {
			String provinceid = request.getParameter("provinceidValue");
			if (AppUtils.isNotBlank(provinceid)) {
				args.setProvinceid(provinceid);
				request.setAttribute("provinceid", provinceid);
			}
			String cityid = request.getParameter("cityidValue");
			if (AppUtils.isNotBlank(cityid)) {
				args.setCityid(cityid);
				request.setAttribute("cityid", cityid);
			}
			String areaid = request.getParameter("areaidValue");
			if (AppUtils.isNotBlank(areaid)) {
				args.setAreaid(areaid);
				request.setAttribute("areaid", areaid);
			}
		}
		args.startFetchingAtRecord((curPageNO - 1) * args.fetchCount());
		// /////search ////////
		SearchResult result = searchFacade.search(args);
		request.setAttribute("keyword", keyword);
		request.setAttribute("entityType", args.getEntityType());
		int allCounts = result.getNumberOfHits();
		if (AppUtils.isNotBlank(result.getRecords())) {
			int offset = PagerUtil.getOffset(allCounts, curPageNO, args.fetchCount());
			PageSupport ps = new PageSupport(result.getRecords(), "javascript:pager", offset, curPageNO, allCounts,
					args.fetchCount());
			request.setAttribute("curPageNO", new Integer(ps.getCurPageNO()));
			request.setAttribute("offset", new Integer(ps.getOffset() + 1));
			request.setAttribute("searchResult", ps.getResultList());
			if (ps.hasMutilPage()) {
				request.setAttribute("toolBar",
						ps.getToolBar(localeResolver.resolveLocale(request), Constants.SIMPLE_PAGE_PROVIDER));
			}
		}
		return PathResolver.getPath(request, response, TilesPage.SEARCHALL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.service.impl.BusinessService#friendlink(javax
	 * .servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String getFriendlink(HttpServletRequest request, HttpServletResponse response) {
		String name = ThreadLocalContext.getCurrentShopName(request, response);
		List<ExternalLink> adList = externalLinkDao.getExternalLink(name);
		if (!AppUtils.isBlank(adList)) {
			request.setAttribute("adList", adList);
		}
		return PathResolver.getPath(request, response, FrontPage.FRIENDLINK);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.service.impl.BusinessService#ipsearch(javax.servlet
	 * .http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.String)
	 */
	@Override
	public String getIpAddress(HttpServletRequest request, HttpServletResponse response, String ipAddress) {
		String address = null;
		if (AppUtils.isNotBlank(ipAddress)) {
			address = IPSeeker.getInstance().getAddress(ipAddress);
			log.debug("{} search {} ", request.getRemoteAddr(), address);
		}
		request.setAttribute("ipAddress", ipAddress);
		request.setAttribute("address", address);
		return PathResolver.getPath(request, response, FrontPage.IPSEARCH);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.service.impl.BusinessService#league(javax.servlet
	 * .http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String getLeague(HttpServletRequest request, HttpServletResponse response) {
		String shopName = (String) getSessionAttribute(request, Constants.SHOP_NAME);// 当前商城
		if (shopName == null) {// 如果找不到就找默认商城
			shopName = PropertiesUtil.getObject(ParameterEnum.DEFAULT_SHOP, String.class);
			this.setShopName(request, response, shopName);
		}
		String curPageNO = request.getParameter("curPageNO");
		PageSupport ps = myleagueDao.getLeague(shopName, curPageNO);
		request.setAttribute("curPageNO", new Integer(ps.getCurPageNO()));
		request.setAttribute("leagues", ps.getResultList());
		if (ps.hasMutilPage()) {
			request.setAttribute("toolBar",
					ps.getToolBar(localeResolver.resolveLocale(request), Constants.SIMPLE_PAGE_PROVIDER));
		}
		getAndSetOneAdvertisement(request, response, ThreadLocalContext.getCurrentShopName(request, response),
				Constants.USER_REG_ADV_740);
		return PathResolver.getPath(request, response, TilesPage.LEAGUE);
	}

	/**
	 * 产品查看历史
	 */
	@Override
	public String getVisitedProd(HttpServletRequest request, HttpServletResponse response) {
		visitedProd(request, response);
		return PathResolver.getPath(request, response, FrontPage.VISITED_PROD);
	}

	/**
	 *  产品查看历史
	 */
	private void visitedProd(HttpServletRequest request, HttpServletResponse response) {
		List<Object> prodIds = VisitHistoryHelper.getVisitedProd(request);
		List<ProductDetail> products = new ArrayList<ProductDetail>();
		for (Object prodId : prodIds) {
			products.add(productDao.getProdDetail(Long.parseLong(prodId.toString())));
		}
		request.setAttribute("visitedProd", products);
	}

	/**
	 * 包括：
	 * 1. 商城查看历史
	 * 2.  产品查看历史
	 */
	@Override
	public String getVisitedShop(HttpServletRequest request, HttpServletResponse response) {
		List<Object> shopIds = VisitHistoryHelper.getVisitedShopDetail(request);
		List<ShopDetail> shopDetails = new ArrayList<ShopDetail>();
		for (Object userName : shopIds) {
			shopDetails.add(shopDetailDao.getShopDetail((String) userName));
		}
		request.setAttribute("visitedShop", shopDetails);

		visitedProd(request, response);
		return PathResolver.getPath(request, response, FrontPage.VISITED_SHOP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.service.impl.BusinessService#setShopDetailDao
	 * (com.legendshop.business.dao.ShopDetailDao)
	 */
	/**
	 * Sets the shop detail dao.
	 * 
	 * @param shopDetailDao
	 *            the new shop detail dao
	 */
	@Override
	@Required
	public void setShopDetailDao(ShopDetailDao shopDetailDao) {
		this.shopDetailDao = shopDetailDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.legendshop.business.service.impl.BusinessService#setSortDao(com.
	 * legendshop.business.dao.SortDao)
	 */
	/**
	 * Sets the sort dao.
	 * 
	 * @param sortDao
	 *            the new sort dao
	 */
	@Required
	public void setSortDao(SortDao sortDao) {
		this.sortDao = sortDao;
	}

	/**
	 * Sets the news dao.
	 * 
	 * @param newsDao
	 *            the new news dao
	 */
	@Required
	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.service.impl.BusinessService#setNsortDao(com.
	 * legendshop.business.dao.NsortDao)
	 */
	/**
	 * Sets the nsort dao.
	 * 
	 * @param nsortDao
	 *            the new nsort dao
	 */
	@Required
	public void setNsortDao(NsortDao nsortDao) {
		this.nsortDao = nsortDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.service.impl.BusinessService#setExternalLinkDao
	 * (com.legendshop.business.dao.ExternalLinkDao)
	 */
	/**
	 * Sets the external link dao.
	 * 
	 * @param externalLinkDao
	 *            the new external link dao
	 */
	@Required
	public void setExternalLinkDao(ExternalLinkDao externalLinkDao) {
		this.externalLinkDao = externalLinkDao;
	}

	/**
	 * Sets the search facade.
	 * 
	 * @param searchFacade
	 *            the new search facade
	 */
	@Required
	public void setSearchFacade(SearchFacade searchFacade) {
		this.searchFacade = searchFacade;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.service.impl.BusinessService#copyAll(javax.servlet
	 * .http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String getNewsforCommon(HttpServletRequest request, HttpServletResponse response) {
		// 采用公用帐号的信息
		String shopName = PropertiesUtil.getDefaultShopName();
		if (AppUtils.isBlank(shopName)) {
			shopName = Constants.COMMON_USER;
		}
		request.setAttribute("newsBottomList", newsDao.getNews(shopName, NewsPositionEnum.NEWS_BOTTOM, 8));
		return PathResolver.getPath(request, response, FrontPage.COPY);
	}

	/**
	 * Sets the product dao.
	 * 
	 * @param productDao
	 *            the new product dao
	 */
	@Required
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	/**
	 * Sets the myleague dao.
	 * 
	 * @param myleagueDao
	 *            the new myleague dao
	 */
	@Required
	public void setMyleagueDao(MyleagueDao myleagueDao) {
		this.myleagueDao = myleagueDao;
	}

}
