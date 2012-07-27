/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.business.common.page.FowardPage;
import com.legendshop.business.common.page.FrontPage;
import com.legendshop.business.common.page.TilesPage;
import com.legendshop.business.dao.BasketDao;
import com.legendshop.business.dao.ExternalLinkDao;
import com.legendshop.business.dao.HotsearchDao;
import com.legendshop.business.dao.ImgFileDao;
import com.legendshop.business.dao.LogoDao;
import com.legendshop.business.dao.MyleagueDao;
import com.legendshop.business.dao.NewsDao;
import com.legendshop.business.dao.NsortDao;
import com.legendshop.business.dao.ProductDao;
import com.legendshop.business.dao.PubDao;
import com.legendshop.business.dao.SortDao;
import com.legendshop.business.dao.SubDao;
import com.legendshop.business.dao.UserDetailDao;
import com.legendshop.business.event.EventId;
import com.legendshop.business.event.impl.VisitLogEvent;
import com.legendshop.business.form.MemberForm;
import com.legendshop.business.form.SearchForm;
import com.legendshop.business.form.UserForm;
import com.legendshop.business.helper.TaskThread;
import com.legendshop.business.helper.impl.SendMailTask;
import com.legendshop.business.service.BusinessService;
import com.legendshop.business.service.PayTypeService;
import com.legendshop.core.AttributeKeys;
import com.legendshop.core.UserManager;
import com.legendshop.core.constant.FunctionEnum;
import com.legendshop.core.constant.LuceneIndexerEnum;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.constant.ProductTypeEnum;
import com.legendshop.core.constant.ShopStatusEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.ApplicationException;
import com.legendshop.core.exception.BusinessException;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.exception.ErrorCodes;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.RealPathUtil;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.core.page.PagerUtil;
import com.legendshop.event.EventContext;
import com.legendshop.event.EventHome;
import com.legendshop.event.GenericEvent;
import com.legendshop.model.UserMessages;
import com.legendshop.model.entity.Advertisement;
import com.legendshop.model.entity.Basket;
import com.legendshop.model.entity.ExternalLink;
import com.legendshop.model.entity.Hotsearch;
import com.legendshop.model.entity.ImgFile;
import com.legendshop.model.entity.News;
import com.legendshop.model.entity.Nsort;
import com.legendshop.model.entity.Product;
import com.legendshop.model.entity.ProductDetail;
import com.legendshop.model.entity.ShopDetail;
import com.legendshop.model.entity.ShopDetailView;
import com.legendshop.model.entity.Sort;
import com.legendshop.model.entity.Sub;
import com.legendshop.model.entity.User;
import com.legendshop.model.entity.UserDetail;
import com.legendshop.search.SearchArgs;
import com.legendshop.search.SearchFacade;
import com.legendshop.search.SearchResult;
import com.legendshop.spi.constants.Constants;
import com.legendshop.spi.constants.NewsPositionEnum;
import com.legendshop.spi.constants.OrderStatusEnum;
import com.legendshop.spi.constants.RegisterEnum;
import com.legendshop.spi.constants.VisitTypeEnum;
import com.legendshop.spi.dao.ShopDetailDao;
import com.legendshop.util.AppUtils;
import com.legendshop.util.BeanHelper;
import com.legendshop.util.MD5Util;
import com.legendshop.util.SafeHtml;
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

	/** The default value. */
	private final String defaultValue = "0";

	/** The default int. */
	private final Long defaultInt = 0l;

	/** The sort dao. */
	private SortDao sortDao;

	/** The pub dao. */
	private PubDao pubDao;

	/** The logo dao. */
	private LogoDao logoDao;
	/** The news dao. */
	private NewsDao newsDao;

	/** The nsort dao. */
	private NsortDao nsortDao;

	/** The external link dao. */
	private ExternalLinkDao externalLinkDao;

	/** The user detail dao. */
	private UserDetailDao userDetailDao;

	/** The pay type service. */
	private PayTypeService payTypeService;

	/** The search facade. */
	private SearchFacade searchFacade;

	/** The product dao. */
	private ProductDao productDao;

	/** The img file dao. */
	private ImgFileDao imgFileDao;

	/** The basket dao. */
	private BasketDao basketDao;

	/** The sub dao. */
	private SubDao subDao;

	/** The hotsearch dao. */
	private HotsearchDao hotsearchDao;
	
	/** The myleague dao. */
	private MyleagueDao myleagueDao;
	

//	/* (non-Javadoc)
//	 * @see com.legendshop.business.service.impl.BusinessService#index(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
//	 */
//	@Override
//	public String getIndex(HttpServletRequest request, HttpServletResponse response) {
//		String shopName = (String)request.getAttribute(Constants.SHOP_NAME);
//		ShopDetailView shopDetail = getShopDetailView(shopName, request, response); // 得到当前商城
//		if (shopDetail == null) {
//			String defaultShop = PropertiesUtil.getObject(ParameterEnum.DEFAULT_SHOP, String.class);
//			if (AppUtils.isNotBlank(defaultShop)) {
//				// 如果有默认店，则先到默认店去，默认店要先配置好
//				shopName = defaultShop;
//				shopDetail = getShopDetailView(shopName, request, response);
//			} else {
//				if (!PropertiesUtil.isSystemInstalled()) {
//					//return PageLet.INSTALL;
//					throw new BusinessException("system did not installed",EntityCodes.SYSTEM);
//				}
//				return PathResolver.getPath(FrontPage.ALL_PAGE);
//			}
//		} else {
//			shopName = shopDetail.getUserName();
//			if (!shopStatusChecker.check(shopDetail, request)) {
//				return PathResolver.getPath(FrontPage.FAIL);
//			}
//
//			// 登录历史
//			visit(shopDetail, request);
//		}
//		request.setAttribute("productList", productDao.getCommendProd(shopName, 40));
//		// 最新商品
//		request.setAttribute("newestList", productDao.getNewestProd(shopName, 11));
//		request.setAttribute("adList", externalLinkDao.getExternalLink(shopName));
//
//		request.setAttribute("logo", logoDao.getLogo(shopName));
//		request.setAttribute("sortList", sortDao.getSort(shopName, true));
//		request.setAttribute("pubList", pubDao.getPub(shopName));
//
//		// 普通新闻
//		request.setAttribute("newList", newsDao.getNews(shopName, NewsPositionEnum.NEWS_NEWS, 6));
//		// 顶部新闻
//		request.setAttribute("newsTopList", newsDao.getNews(shopName, NewsPositionEnum.NEWS_TOP, 8));
//		// 分类新闻
//		request.setAttribute("newsSortList", newsDao.getNews(shopName, NewsPositionEnum.NEWS_SORT, 8));
//
//		setAdvertisement(shopName, request);
//		List<Indexjpg> indexJpgList = imgFileDao.getIndexJpeg(shopName);
//		if (!AppUtils.isBlank(indexJpgList)) {
//			request.setAttribute("MaxScreen", indexJpgList.size());
//			JSONArray jsonArray = JSONArray.fromObject(indexJpgList);
//			request.setAttribute("indexJSON", jsonArray);
//		} else {
//			request.setAttribute("MaxScreen", 0);
//		}
//
//		String userName = UserManager.getUsername(request.getSession());
//		boolean shopExists = shopDetailDao.isShopExists(userName);
//		request.setAttribute("shopExists", shopExists);
//		request.setAttribute("canbeLeagueShop", shopDetailDao.isBeLeagueShop(shopExists, userName, shopName));
//
//		// 多线程记录访问历史
//		if (PropertiesUtil.getObject(ParameterEnum.VISIT_LOG_INDEX_ENABLE, Boolean.class)) {
//			VisitLog visitLog = new VisitLog();
//			visitLog.setDate(new Date());
//			visitLog.setIp(request.getRemoteAddr());
//			visitLog.setShopName(shopName);
//			visitLog.setUserName(userName);
//			visitLog.setPage(VisitTypeEnum.INDEX.value());
//			threadPoolExecutor.execute(new TaskThread(new PersistVisitLogTask(visitLog)));
//		} else {
//			log.info("[{}],{} visit index {}", new Object[] { request.getRemoteAddr(), userName, shopName });
//		}
//		return PathResolver.getPath(FrontPage.INDEX_PAGE);
//	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BusinessService#getShopDetailView(java.lang.String, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	

	/**
	 * Sets the advertisement.
	 * 
	 * @param shopName
	 *            the shop name
	 * @param key
	 *            the key
	 * @param request
	 *            the request
	 */
	private void setAdvertisement(String shopName, String key, HttpServletRequest request) {
		List<Advertisement> advertisement = advertisementDao.getAdvertisement(shopName, key);
		if (!AppUtils.isBlank(advertisement)) {
			request.setAttribute(key, advertisement);
		}
	}

	// 只是得到一个广告
	/**
	 * Sets the one advertisement.
	 * 
	 * @param shopName
	 *            the shop name
	 * @param key
	 *            the key
	 * @param request
	 *            the request
	 */
	private void setOneAdvertisement(String shopName, String key, HttpServletRequest request) {
		List<Advertisement> advertisement = advertisementDao.getOneAdvertisement(shopName, key);
		if (!AppUtils.isBlank(advertisement)) {
			request.setAttribute(key, advertisement);
		}
	}


	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BusinessService#top(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String getTop(HttpServletRequest request, HttpServletResponse response) {
		String shopName = getCurrentShopName();
		String userName = UserManager.getUsername(request.getSession());
		ShopDetailView shopDetail = ThreadLocalContext.getShopDetailView(shopName);
		if (shopDetail == null) {
			return PathResolver.getPath(FrontPage.TOPALL);
		}

		// set Locale
		//setLocalByShopDetail(shopDetail, request, response);

		request.setAttribute("logo", logoDao.getLogo(shopName));
		request.setAttribute("sortList", sortDao.getSort(shopName, true));

		// 顶部新闻
		request.setAttribute("newsTopList", newsDao.getNews(shopName, NewsPositionEnum.NEWS_TOP, 8));

		// 分类新闻
		request.setAttribute("newsSortList", newsDao.getNews(shopName, NewsPositionEnum.NEWS_SORT, 8));

		boolean shopExists = shopDetailDao.isShopExists(userName);
		request.setAttribute("shopExists", shopExists);
		request.setAttribute("canbeLeagueShop", shopDetailDao.isBeLeagueShop(shopExists, userName, shopName));
		return PathResolver.getPath(FrontPage.TOP);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BusinessService#topsort(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String getTopSort(HttpServletRequest request, HttpServletResponse response) {
		String shopName = getCurrentShopName();
		request.setAttribute("sortList", sortDao.getSort(shopName, true));
		return PathResolver.getPath(FrontPage.TOPSORT);
	}


	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BusinessService#topnews(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String getTopnews(HttpServletRequest request, HttpServletResponse response) {
		String name = getCurrentShopName();

		String topsortnews = request.getParameter("topsortnews");
		if ((topsortnews != null)) {
			request.setAttribute(
					"newList",
					newsDao.getNews(name, NewsPositionEnum.NEWS_NEWS,
							PropertiesUtil.getObject(ParameterEnum.FRONT_PAGE_SIZE, Integer.class)));
			return PathResolver.getPath(FrontPage.TOPSORTNEWS);
		} else {
			request.setAttribute("newList", newsDao.getNews(name, NewsPositionEnum.NEWS_NEWS, 6));
			return PathResolver.getPath(FrontPage.TOPNEWS);
		}
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BusinessService#search(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.legendshop.business.action.form.SearchForm)
	 */
	@Override
	public String search(HttpServletRequest request, HttpServletResponse response, SearchForm searchForm) {
		Sort sort = null;
		List<Nsort> nsortList = null;
		// 1、关键字不能为空
		if (AppUtils.isBlank(searchForm.getKeyword())) {
			log.error("search keyword can't be null!");
			return PathResolver.getPath(FowardPage.INDEX_QUERY);
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
			cq.eq("userName", getCurrentShopName());
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
			cq.add();
			PageSupport ps = productDao.getProdDetail(cq);
			request.setAttribute("curPageNO", new Integer(ps.getCurPageNO()));
			request.setAttribute("offset", new Integer(ps.getOffset() + 1));
			request.setAttribute("prodDetailList", ps.getResultList());
			request.setAttribute("searchForm", searchForm);
			if (ps.hasMutilPage()) {
				request.setAttribute("toolBar", ps.getToolBar(localeResolver.resolveLocale(request), Constants.SIMPLE_PAGE_PROVIDER));
			}
		} catch (Exception e) {
			log.error("getProdDetail", e);
			return PathResolver.getPath(FowardPage.INDEX_QUERY);
		}
		return PathResolver.getPath(TilesPage.PRODUCTSORT);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BusinessService#searchall(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.String, java.lang.String)
	 */
	@Override
	public String searchall(HttpServletRequest request, HttpServletResponse response, String keyword, Integer entityType) {
		if(entityType == null){
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
			return PathResolver.getPath(FrontPage.ALL);
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
				request.setAttribute("toolBar", ps.getToolBar(localeResolver.resolveLocale(request), Constants.SIMPLE_PAGE_PROVIDER));
			}
		}
		return PathResolver.getPath(TilesPage.SEARCHALL);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BusinessService#sort(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.String, java.lang.Long)
	 */
	@Override
	public String getSort(HttpServletRequest request, HttpServletResponse response, String curPageNO, Long sortId) {
		if(curPageNO == null){
			curPageNO = "1";
		}
		if (sortId == null) {
			return PathResolver.getPath(FowardPage.INDEX_QUERY);
		}
		Sort sort = sortDao.getSort(sortId);
		if (sort == null) {
			throw new NotFoundException("sort can not be null", ErrorCodes.ENTITY_NO_FOUND);
		}
		setShopName(request, response, sort.getUserName());
		setAdvertisement(sort.getUserName(), request);
		// 热门搜索
		List<Hotsearch> searchList = hotsearchDao.getHotsearch(sort.getUserName(), sortId);
		if (!AppUtils.isBlank(searchList)) {
			request.setAttribute("searchList", searchList);
		}

		List<Nsort> nsortList = nsortDao.getNsortBySortId(sortId);
		request.setAttribute("sort", sort);
		request.setAttribute("nsortList", nsortList);
		String userName = UserManager.getUsername(request.getSession());
		log.info("[{}],{},{},sort", new Object[] { request.getRemoteAddr(), userName == null ? "" : userName,
				getSessionAttribute(request, Constants.SHOP_NAME) });

		PageSupport ps = productDao.getProdDetail(localeResolver.resolveLocale(request), curPageNO, sortId);
		ps.savePage(request);
		return PathResolver.getPath(TilesPage.PRODUCTSORT);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BusinessService#nsort(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String getSecSort(HttpServletRequest request, HttpServletResponse response,Long sortId,Long nsortId,Long subNsortId) {
		String curPageNO = request.getParameter("curPageNO");
		if(curPageNO == null){
			curPageNO = "1";
		}
		String userName = UserManager.getUsername(request);
		log.info("{},{},{},{},{},nsort", new Object[] { request.getRemoteAddr(), userName == null ? "" : userName,
				getSessionAttribute(request, Constants.SHOP_NAME), sortId, nsortId });
		Sort sort = sortDao.getSort(sortId);
		if (sort != null) {
			setShopName(request, response, sort.getUserName());
			setAdvertisement(sort.getUserName(), request);
			request.setAttribute("sort", sort);
		}
		Nsort nsort = nsortDao.getNsort(nsortId);
		if ((nsort != null) && !AppUtils.isBlank(nsort.getSubSort())) {
			request.setAttribute("hasSubSort", true);
		}
		if (subNsortId != null) {
			Nsort subNsort = nsortDao.getNsort(subNsortId);
			request.setAttribute("subNsort", subNsort);
			if (subNsort != null) {
				request.setAttribute("CurrentSubNsortId", subNsort.getNsortId());
			}
		}

		List<Nsort> nsortList = nsortDao.getNsortList(sortId);
		request.setAttribute("nsort", nsort);

		request.setAttribute("nsortList", nsortDao.getOthorNsort(nsortList));
		request.setAttribute("subNsortList", nsortDao.getOthorSubNsort(nsortId, nsortList));
		if (nsort != null) {
			request.setAttribute("CurrentNsortId", nsort.getNsortId());
		}
		PageSupport ps = productDao.getProdDetail(localeResolver.resolveLocale(request), curPageNO,  sortId, nsortId, subNsortId);
		ps.savePage(request);
		
		return PathResolver.getPath(TilesPage.NSORT);
	}

	// 查看商品
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BusinessService#views(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Long)
	 */
	@Override
	public String getViews(HttpServletRequest request, HttpServletResponse response, Long prodId) {
		if (prodId == null) {
			return PathResolver.getPath(FowardPage.INDEX_QUERY);
		}
		ProductDetail prod = productDao.getProdDetail(prodId);

		if (prod != null) {
			if(ProductTypeEnum.GROUP.value().equals(prod.getProdType())){
				return PathResolver.getPath("/group/view/" + prodId, FowardPage.VARIABLE);
			}
			if (!Constants.ONLINE.equals(prod.getStatus())) {
				throw new NotFoundException("Product " + prod.getName() + " does not online.",EntityCodes.PROD);
			}
			// 查看商品的说明图片
			List<ImgFile> prodPics = imgFileDao.getProductPics(prod.getUserName(), prodId);
			if (AppUtils.isNotBlank(prodPics)) {
				request.setAttribute("prodPics", prodPics);
			}
			// 商家详细说明
			ShopDetailView shopDetail = ThreadLocalContext.getShopDetailView(prod.getUserName());
			
			if (shopDetail == null) {
				return PathResolver.getPath(FrontPage.TOPALL);
			} 
			// 相关商品
			List<Product> releationProds = productDao.getReleationProd(prod.getUserName(), prod.getProdId(), 30);
			if (!AppUtils.isBlank(releationProds)) {
				request.setAttribute("productList", releationProds);
			}

			request.setAttribute("prod", prod);
			this.setShopName(request, response, prod.getUserName());
			setAdvertisement(prod.getUserName(), request);

			// 更新查看次数
			if (PropertiesUtil.getObject(ParameterEnum.VISIT_HW_LOG_ENABLE, Boolean.class)) {
				productDao.updateProdViews(prodId);
			}

			String userName = UserManager.getUsername(request.getSession());

			if (log.isInfoEnabled()) {
				log.info(
						"{},{},{},{},viewsprod",
						new Object[] { request.getRemoteAddr(), userName == null ? "" : userName,
								getSessionAttribute(request, Constants.SHOP_NAME), prod.getName() });
			}

			// 记录登录历史
			visit(prod, request);
			// 多线程记录访问历史
			if (PropertiesUtil.getObject(ParameterEnum.VISIT_LOG_ENABLE, Boolean.class)) {
				EventHome.publishEvent(new VisitLogEvent(request.getRemoteAddr(),prod.getUserName(),userName,prod.getProdId(),prod.getName(),VisitTypeEnum.HW.value()));
			}
			return PathResolver.getPath(FrontPage.VIEWS);
		} else {
			UserMessages uem = new UserMessages();
			Locale locale = localeResolver.resolveLocale(request);
			uem.setTitle(ResourceBundleHelper.getString(locale, "product.not.found"));
			uem.setDesc(ResourceBundleHelper.getString(locale, "product.status.check"));
			uem.setCode(ErrorCodes.PRODUCT_NO_FOUND);
			request.setAttribute(UserMessages.MESSAGE_KEY, uem);
			return PathResolver.getPath(FrontPage.FAIL);
		}

	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BusinessService#orderDetail(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.String)
	 */
	@Override
	public String getOrderDetail(HttpServletRequest request, Sub sub,String userName, String subNumber) {
		List<Sub> subList = new ArrayList<Sub>();

		MemberForm form = new MemberForm();
		form.setUserAdds(sub.getSubAdds());
		form.setUserPostcode(sub.getSubPost());
		form.setOrderName(sub.getUserName());
		form.setOther(sub.getOther());
		form.setUserTel(sub.getSubTel());
		form.setPayTypeName(sub.getPayTypeName());
		request.setAttribute("member", form);

		List<Basket> baskets = subDao.getBasketBySubNumber(subNumber);
		if (!AppUtils.isBlank(baskets)) {// 每一个订单最少应该有一个商品
			sub.setBasket(baskets);
			sub.setPayType(payTypeService.getPayTypeList(sub.getShopName()));
			subList.add(sub);
			request.setAttribute("baskets", baskets);
			request.setAttribute("subList", subList);
		}
		if (OrderStatusEnum.UNPAY.value().equals(sub.getStatus())) {
			UserDetail userdetail = userDetailDao.getUserDetail(userName);
			if (userdetail != null) {
				if (userdetail.getScore() == null) {
					userdetail.setScore(0l);
				}
				request.setAttribute("availableScore", userdetail.getScore());
			}
		} else {
			request.setAttribute("availableScore", 0l);
		}
		return PathResolver.getPath(TilesPage.PAGE_SUB);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BusinessService#news(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Long)
	 */
	@Override
	public String getNews(HttpServletRequest request, HttpServletResponse response, Long newsId) {
		if (newsId != null) {
			News news = newsDao.getNewsById(newsId);
			if (news != null) {
				this.setShopName(request, response, news.getUserName());
				this.setAdvertisement(news.getUserName(), request);
				request.setAttribute("news", news);
			}

		}
		setOneAdvertisement(getCurrentShopName(), Constants.USER_REG_ADV_740, request);
		return PathResolver.getPath(TilesPage.NEWS);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BusinessService#allNews(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.String, java.lang.String)
	 */
	@Override
	public String getAllNews(HttpServletRequest request, HttpServletResponse response, String curPageNO,
			Long newsCategoryId) {
		if(curPageNO == null){
			curPageNO = "1";
		}
		String shopName = getCurrentShopName();

		PageSupport ps = newsDao.getNews(localeResolver.resolveLocale(request), curPageNO,shopName,newsCategoryId);
		ps.savePage(request);
		request.setAttribute("newsCategoryId", newsCategoryId);
		setOneAdvertisement(shopName, Constants.USER_REG_ADV_740, request);
		return PathResolver.getPath(TilesPage.ALL_NEWS);
	}

	// 初始化订单
	/**
	 * Make sub.
	 * 
	 * @param form
	 *            the form
	 * @return the sub
	 */
	private Sub makeSub(MemberForm form) {
		Sub sub = new Sub();
		sub.setUserName(form.getUserName());
		// sub.setSubNumber(form.getSubNember());
		sub.setSubDate(new Date());
		// sub.setTotal(form.getTotal());
		sub.setSubTel(form.getUserTel());
		sub.setSubPost(form.getUserPostcode());
		sub.setSubMail(form.getUserMail());
		sub.setSubAdds(form.getUserAdds());
		sub.setPayId(form.getPayType());
		sub.setOther(form.getOther());
		sub.setSubCheck(Constants.FALSE_INDICATOR);
		sub.setOrderName(form.getOrderName());
		return sub;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BusinessService#hotsale(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String getHotSale(HttpServletRequest request, HttpServletResponse response) {
		String name = getCurrentShopName();
		List<Product> hotsaleList = productDao.gethotsale(name);
		if (!AppUtils.isBlank(hotsaleList)) {
			request.setAttribute("hotsaleList", hotsaleList);
		}
		return PathResolver.getPath(FrontPage.HOTSALE);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BusinessService#friendlink(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String getFriendlink(HttpServletRequest request, HttpServletResponse response) {
		String name = getCurrentShopName();
		List<ExternalLink> adList = externalLinkDao.getExternalLink(name);
		if (!AppUtils.isBlank(adList)) {
			request.setAttribute("adList", adList);
		}
		return PathResolver.getPath(FrontPage.FRIENDLINK);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BusinessService#hoton(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String getHotProduct(HttpServletRequest request, HttpServletResponse response) {
		String sortId = request.getParameter("sortId");
		List<Product> hotonList = productDao.getHotOn(sortId);
		if (AppUtils.isNotBlank(hotonList)) {
			request.setAttribute("hotonList", hotonList);
		}
		return PathResolver.getPath(FrontPage.HOTON);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BusinessService#hotView(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String getHotView(HttpServletRequest request, HttpServletResponse response) {
		String shopName = getCurrentShopName();
		List<Product> hotViewList = productDao.getHotViewProd(shopName, 10);
		if (AppUtils.isNotBlank(hotViewList)) {
			request.setAttribute("hotViewList", hotViewList);
		}
		return PathResolver.getPath(FrontPage.HOTVIEW);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BusinessService#userReg(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.legendshop.business.action.form.UserForm)
	 */
	@Override
	public String saveUserReg(HttpServletRequest request, HttpServletResponse response, UserForm form) {
		// 过滤特殊字符
		SafeHtml safeHtml = new SafeHtml();
		form.setUserName(safeHtml.makeSafe(form.getUserName()));
		form.setUserMemo(safeHtml.makeSafe(form.getUserMemo()));
		form.setUserMobile(safeHtml.makeSafe(form.getUserMobile()));
		form.setUserPostcode(safeHtml.makeSafe(form.getUserPostcode()));
		form.setUserTel(safeHtml.makeSafe(form.getUserTel()));
		form.setUserMail(safeHtml.makeSafe(form.getUserMail()));
		form.setUserAdds(safeHtml.makeSafe(form.getUserAdds()));
		form.setMsn(safeHtml.makeSafe(form.getMsn()));
		form.setNote(safeHtml.makeSafe(form.getNote()));
		form.setQq(safeHtml.makeSafe(form.getQq()));
		form.setName(safeHtml.makeSafe(form.getName()));
		form.setNickName(safeHtml.makeSafe(form.getNickName()));

		ShopDetail shopDetail = form.getShopDetail();
		if (shopDetail != null) {
			shopDetail.setRealPath(RealPathUtil.getBigPicRealPath());
			shopDetail.setIp(request.getRemoteAddr());
			shopDetail.setSiteName(safeHtml.makeSafe(shopDetail.getSiteName()));
			shopDetail.setPostAddr(safeHtml.makeSafe(shopDetail.getPostAddr()));
			shopDetail.setIdCardNum(safeHtml.makeSafe(shopDetail.getIdCardNum()));
		}
		if (isUserInfoValid(form, request)) {
			return PathResolver.getPath(FrontPage.FAIL);
		}
		
		User user = new User();
		UserDetail userDetail = new UserDetail();
		BeanHelper.copyProperties(user, form, true);
		BeanHelper.copyProperties(userDetail, form, true);
		Date date = new Date();
		String plaintPassword = user.getPassword();
		user.setPassword(MD5Util.Md5Password(user.getName(), plaintPassword));
		userDetail.setUserRegtime(date);
		userDetail.setModifyTime(date);
		userDetail.setUserRegip(request.getRemoteAddr());
		userDetail.setTotalCash(0d);
		userDetail.setTotalConsume(0d);
		boolean isOpenShop = request.getParameter("openShop") != null;

		userDetailDao.saveUser(user, userDetail, shopDetail, isOpenShop);
		
		//提醒语
		UserMessages uem = new UserMessages();
		Locale locale = localeResolver.resolveLocale(request);
		uem.setTitle(ResourceBundleHelper.getString(locale, "regFree") + " " + form.getName() + " "
				+ ResourceBundleHelper.getString(locale, "success.hint"));
		if (userDetail.getRegisterCode() == null) {
			uem.setDesc(ResourceBundleHelper.getString(locale, "after.reg.success"));
		} else {
			uem.setDesc(ResourceBundleHelper.getString(locale, "reg.success.acknowledgement"));
		}

		uem.addCallBackList(ResourceBundleHelper.getString(locale, "login"),
				ResourceBundleHelper.getString(locale, "logon.hint.desc"), "login"+AttributeKeys.WEB_SUFFIX);
		request.setAttribute(UserMessages.MESSAGE_KEY, uem);
		userDetailDao.flush();
		
		
		// 发送通知注册成功邮件
		if (PropertiesUtil.sendMail()) {
			try {
				String filePath = request.getSession().getServletContext().getRealPath("/")
						+ "/WEB-INF/template/mail/registersuccess.jsp";
				// String text = FileProcessor.readFile(new File(filePath));
				Map<String, String> values = new HashMap<String, String>();
				values.put("#nickName#", userDetail.getNickName());
				values.put("#userName#", userDetail.getUserName());
				values.put("#password#", userDetail.getPassword());
				if (AppUtils.isNotBlank(userDetail.getRegisterCode())) {
					StringBuffer buffer = new StringBuffer();
					buffer.append("<p>你的帐号尚未开通，<a href=\"").append(Constants.LEGENDSHOP_DOMAIN_NAME)
							.append("/userRegSuccess"+ Constants.WEB_SUFFIX + "?userName=").append(user.getName()).append("&registerCode=")
							.append(userDetail.getRegisterCode()).append("\">点击开通我的帐号</a></p><br>");
					values.put("#registerCode#", buffer.toString());
				} else {
					StringBuffer buffer = new StringBuffer();
					buffer.append("<p>你的帐号已经开通成功!</p><br>");
					values.put("#registerCode#", buffer.toString());
				}
				String text = AppUtils.convertTemplate(filePath, values);
				threadPoolExecutor.execute(new TaskThread(new SendMailTask(javaMailSender, userDetail.getUserMail(),
						"恭喜您，注册商城成功", text)));
				log.info("{} 注册成功，发送通知邮件", userDetail.getUserMail());
			} catch (Exception e) {
				log.info("{}，发送通知邮件失败，请检查邮件配置", userDetail.getUserMail());
				throw new ApplicationException(e, "发送通知邮件失败，请检查邮件配置",EntityCodes.PROD);
			}

		}
		return PathResolver.getPath(TilesPage.AFTER_OPERATION);

	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BusinessService#reg(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String saveUserReg(HttpServletRequest request, HttpServletResponse response) {
		setOneAdvertisement(getCurrentShopName(), Constants.USER_REG_ADV_950, request);
		
		EventContext eventContext = new EventContext(request);
		EventHome.publishEvent(new GenericEvent(eventContext,EventId.CAN_ADD_SHOPDETAIL_EVENT));
		
		request.setAttribute("supportOpenShop", eventContext.getBooleanResponse());
		
		request.setAttribute("validationOnOpenShop",
				PropertiesUtil.getObject(ParameterEnum.VALIDATION_ON_OPEN_SHOP, Boolean.class));
		return PathResolver.getPath(TilesPage.REG);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BusinessService#isUserInfoValid(com.legendshop.business.action.form.UserForm, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public boolean isUserInfoValid(UserForm form, HttpServletRequest request) {
		boolean result = false;
		// 检查是否重名
		UserMessages messages = new UserMessages();
		Locale locale = localeResolver.resolveLocale(request);
		if (AppUtils.isBlank(form.getName())) {
			messages.addCallBackList(ResourceBundleHelper.getString(locale, "username.required"));
			result = true;
		}
		if (!result) {
			if (form.getName().length() < 4) {
				messages.addCallBackList(ResourceBundleHelper.getString(locale, "username.minlength"));
				result = true;
			}
			if (userDetailDao.isUserExist(form.getName())) {
				messages.addCallBackList(ResourceBundleHelper.getString(locale, "error.User.IsExist"));
			}
			if (AppUtils.isBlank(form.getUserMail())) {
				messages.addCallBackList(ResourceBundleHelper.getString(locale, "user.email.required"));
			} else {
				if (userDetailDao.isEmailExist(form.getUserMail())) {
					messages.addCallBackList("Email <b>" + form.getUserMail() + "</b> "
							+ ResourceBundleHelper.getString(locale, "user.email.exists"));
				}
			}

		}
		result = messages.hasError();
		if (result) {
			request.setAttribute(UserMessages.MESSAGE_KEY, messages);
			request.setAttribute("userForm", form);
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BusinessService#addShop(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.legendshop.business.action.form.UserForm)
	 */
	@Override
	public String saveShop(HttpServletRequest request, HttpServletResponse response, ShopDetail shopDetail) {

		Locale locale = localeResolver.resolveLocale(request);
		try {
			if (shopDetail != null) {
				shopDetail.setRealPath(RealPathUtil.getBigPicRealPath());
			}
			UserDetail userDetail = new UserDetail();
			userDetail.setUserId(UserManager.getUserId(request.getSession()));
			userDetail.setUserName(UserManager.getUsername(request.getSession()));
			Integer status = userDetailDao.saveShopDetailAndRole(userDetail, shopDetail);
			String openResultDesc = null;
			
			if (ShopStatusEnum.AUDITING.value().equals(status)) {
				openResultDesc = ResourceBundleHelper.getString(locale, "apply.shop.auditing");
			} else {
				openResultDesc = ResourceBundleHelper.getString(locale, "apply.shop.success.relogin");
			}

			UserMessages messages = new UserMessages(ErrorCodes.NORMAL_STAUTS, ResourceBundleHelper.getString(locale,
					"apply.shop.success"), openResultDesc);
			request.setAttribute(UserMessages.MESSAGE_KEY, messages);
			return PathResolver.getPath(TilesPage.AFTER_OPERATION);

		} catch (Exception e) {
			log.error("addShop ", e);
			UserMessages messages = new UserMessages(ErrorCodes.SAVE_ERROR, ResourceBundleHelper.getString(
					locale, "apply.shop.failed"), ResourceBundleHelper.getString(locale, "check.parameter"));
			messages.addCallBackList(ResourceBundleHelper.getString(locale, "try.again"), null, "openShop.do");
			request.setAttribute(UserMessages.MESSAGE_KEY, messages);
			return PathResolver.getPath(FrontPage.ERROR_PAGE);
		}

	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BusinessService#myaccount(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String getMyAccount(HttpServletRequest request, HttpServletResponse response) {
		String userName = UserManager.getUsername(request);
		if (userName == null) {
			return PathResolver.getPath(TilesPage.LOGIN);
		}
		String viewName = request.getParameter("userName");
		if (AppUtils.isNotBlank(viewName)) {
			if (UserManager.hasFunction(request.getSession(),FunctionEnum.FUNCTION_SECUREST.value())) { // 保留，只能超级管理员可以看
				userName = viewName;
				request.setAttribute("isAdmin", true); // 管理员不可操作
			}
		}
		UserDetail userDetail = userDetailDao.getUserDetail(userName);
		if (userDetail == null) {
			log.error("userDetail not found, userName = " + userName);
			throw new NotFoundException("userDetail not found",EntityCodes.USER);
		}
		// 如果加入即会返回当前用户的当铺
		ShopDetail shopDetail = userDetail.getShopDetail();
		if (shopDetail != null) {
			request.setAttribute("myShopDetail", shopDetail);
		}

		if (userDetail.getBirthDate() != null) {
			setBirthDate(userDetail.getBirthDate(), request);
		}
		if (userDetail.getScore() == null) {
			userDetail.setScore(0l);// 默认
		}
		request.setAttribute("user", userDetail);
		
		EventContext eventContext = new EventContext(request);
		EventHome.publishEvent(new GenericEvent(eventContext,EventId.CAN_ADD_SHOPDETAIL_EVENT));
		
		request.setAttribute("supportOpenShop", eventContext.getBooleanResponse());
		
		request.setAttribute("totalProcessingOrder", subDao.getTotalProcessingOrder(userName));
		request.setAttribute("totalBasketByuserName", basketDao.getTotalBasketByuserName(userName));
		setOneAdvertisement(getCurrentShopName(), Constants.USER_REG_ADV_740, request);
		return PathResolver.getPath(TilesPage.MYACCOUNT);
	}

	/**
	 * Sets the birth date.
	 * 
	 * @param birthDate
	 *            the birth date
	 * @param request
	 *            the request
	 */
	private void setBirthDate(String birthDate, HttpServletRequest request) {
		try {
			String year = birthDate.substring(0, 4);
			String month = birthDate.substring(4, 6);
			String day = birthDate.substring(6, 8);
			request.setAttribute("userBirthYear", year);
			request.setAttribute("userBirthMonth", month);
			request.setAttribute("userBirthDay", day);
		} catch (Exception e) {

		}

	}
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BusinessService#updateAccount(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.legendshop.business.action.form.UserForm)
	 */
	@Override
	public String updateAccount(HttpServletRequest request, HttpServletResponse response, UserForm form) {
		ShopDetail shopDetail = form.getShopDetail();
		String userName = UserManager.getUsername(request);
		if (CommonServiceUtil.haveViewAllDataFunction(request)) {// admin
			if (AppUtils.isNotBlank(form.getUserName())) {
				userName = form.getUserName();
			}
		}
		User user = userDetailDao.getUserByName(userName);
		Locale locale = localeResolver.resolveLocale(request);
		if (user == null) {
			UserMessages messages = new UserMessages(ErrorCodes.SAVE_ERROR, ResourceBundleHelper.getString(locale,
					"update.myaccount.fail"), ResourceBundleHelper.getString(locale, "check.parameter"));
			messages.addCallBackList(ResourceBundleHelper.getString(locale, "reupdate.myaccount"), null,
					"updateAccount.do");
			request.setAttribute(UserMessages.MESSAGE_KEY, messages);
			return PathResolver.getPath(TilesPage.AFTER_OPERATION);
		}
		UserDetail userDetail = userDetailDao.getUserDetail(userName);
		if (!AppUtils.isBlank(form.getPassword())) {
			if (!user.getPassword().equals(MD5Util.Md5Password(userName, form.getPasswordOld()))) {
				log.warn("old password does not match!");
				UserMessages messages = new UserMessages(ErrorCodes.SAVE_ERROR, ResourceBundleHelper.getString(
						locale, "error.old.password"), ResourceBundleHelper.getString(locale, "check.parameter"));
				messages.addCallBackList(ResourceBundleHelper.getString(locale, "reupdate.myaccount"),
						ResourceBundleHelper.getString(locale, "notmatch.old.password"), "myaccount.do");
				request.setAttribute(UserMessages.MESSAGE_KEY, messages);

				return PathResolver.getPath(TilesPage.AFTER_OPERATION);
			}
		}
		boolean update = true;
		if (userDetail == null) {
			update = false;
			userDetail = new UserDetail();
		}
		Date date = new Date();
		SafeHtml safeHtml = new SafeHtml();
		userDetail.setNickName(safeHtml.makeSafe(form.getNickName()));
		userDetail.setSex(safeHtml.makeSafe(form.getSex()));
		userDetail.setBirthDate(form.getBirthDate());
		if (UserManager.hasFunction(request,new String[]{FunctionEnum.FUNCTION_VIEW_ALL_DATA.value(),FunctionEnum.FUNCTION_F_OPERATOR.value()})) {
			userDetail.setUserMail(form.getUserMail());
		}

		userDetail.setUserAdds(safeHtml.makeSafe(form.getUserAdds()));
		userDetail.setUserTel(safeHtml.makeSafe(form.getUserTel()));
		userDetail.setUserPostcode(safeHtml.makeSafe(form.getUserPostcode()));
		userDetail.setPassword(form.getPassword());
		userDetail.setFax(safeHtml.makeSafe(form.getFax()));
		userDetail.setModifyTime(date);
		userDetail.setUserId(user.getId());
		userDetail.setUserMobile(safeHtml.makeSafe(form.getUserMobile()));
		userDetail.setMsn(safeHtml.makeSafe(form.getMsn()));

		userDetail.setQq(safeHtml.makeSafe(form.getQq()));
		// 生日设定
		String year = form.getUserBirthYear();
		String month = form.getUserBirthMonth();
		String day = form.getUserBirthDay();
		if (year != null && form.getUserBirthMonth() != null && form.getUserBirthDay() != null) {
			if (month.length() < 2) {
				month = "0" + month;
			}
			if (day.length() < 2) {
				day = "0" + day;
			}
			userDetail.setBirthDate(year + month + day);
		}

		boolean openshop = request.getParameter("openShop") != null;
		if (update) {
			userDetailDao.updateUser(userDetail);
			userDetailDao.updateShopDetail(userDetail, shopDetail, openshop);
		} else {
			userDetail.setUserRegip(request.getRemoteAddr());
			userDetail.setUserRegtime(date);
			userDetail.setUserId(user.getId());
			userDetail.setUserName(userName);
			userDetailDao.saveUerDetail(userDetail, shopDetail, openshop);
			userDetailDao.updatePassword(userDetail);
		}

		UserMessages messages = new UserMessages(ErrorCodes.NORMAL_STAUTS, "", "");
		messages.addCallBackList(ResourceBundleHelper.getString(locale, "myaccount"),
				ResourceBundleHelper.getString(locale, "reupdate.myaccount"), "myaccount" + Constants.WEB_SUFFIX);
		request.setAttribute(UserMessages.MESSAGE_KEY, messages);
		return PathResolver.getPath(TilesPage.AFTER_OPERATION);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BusinessService#ipsearch(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.String)
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
		return PathResolver.getPath(FrontPage.IPSEARCH);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BusinessService#league(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
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
			request.setAttribute("toolBar", ps.getToolBar(localeResolver.resolveLocale(request), Constants.SIMPLE_PAGE_PROVIDER));
		}
		setOneAdvertisement(getCurrentShopName(), Constants.USER_REG_ADV_740, request);
		return PathResolver.getPath(TilesPage.LEAGUE);
	}


	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BusinessService#setShopDetailDao(com.legendshop.business.dao.ShopDetailDao)
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

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BusinessService#setSortDao(com.legendshop.business.dao.SortDao)
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

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BusinessService#setPubDao(com.legendshop.business.dao.PubDao)
	 */
	/**
	 * Sets the pub dao.
	 * 
	 * @param pubDao
	 *            the new pub dao
	 */
	@Required
	public void setPubDao(PubDao pubDao) {
		this.pubDao = pubDao;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BusinessService#setLogoDao(com.legendshop.business.dao.LogoDao)
	 */
	/**
	 * Sets the logo dao.
	 * 
	 * @param logoDao
	 *            the new logo dao
	 */
	@Required
	public void setLogoDao(LogoDao logoDao) {
		this.logoDao = logoDao;
	}


	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BusinessService#setNewsDao(com.legendshop.business.dao.NewsDao)
	 */
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

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BusinessService#setNsortDao(com.legendshop.business.dao.NsortDao)
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

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BusinessService#setExternalLinkDao(com.legendshop.business.dao.ExternalLinkDao)
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

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BusinessService#setUserDetailDao(com.legendshop.business.dao.UserDetailDao)
	 */
	/**
	 * Sets the user detail dao.
	 * 
	 * @param userDetailDao
	 *            the new user detail dao
	 */
	@Required
	public void setUserDetailDao(UserDetailDao userDetailDao) {
		this.userDetailDao = userDetailDao;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BusinessService#shopcontact(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String getShopcontact(HttpServletRequest request, HttpServletResponse response) {
		String shopName = request.getParameter("shop");
		if (shopName == null) {
			shopName = getCurrentShopName();
		}
		if (shopName == null) {
			return PathResolver.getPath(TilesPage.SEARCHALL);
		}
		UserDetail userDetail = userDetailDao.getUserDetail(shopName);
		// 如果加入即会返回当前用户的当铺
		request.setAttribute("user", userDetail);
		return PathResolver.getPath(TilesPage.SHOPCONTACT);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BusinessService#setPayTypeService(com.legendshop.business.service.PayTypeServiceImpl)
	 */
	/**
	 * Sets the pay type service.
	 * 
	 * @param payTypeService
	 *            the new pay type service
	 */
	@Required
	public void setPayTypeService(PayTypeService payTypeService) {
		this.payTypeService = payTypeService;
	}

	/**
	 * 用户用registerCode激活帐号.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param userName
	 *            the user name
	 * @param registerCode
	 *            the register code
	 * @return the string
	 */
	@Override
	public String updateUserReg(HttpServletRequest request, HttpServletResponse response, String userName,
			String registerCode) {

		RegisterEnum result = userDetailDao.getUserRegStatus(userName, registerCode);
		if (!RegisterEnum.REGISTER_SUCCESS.equals(result)) {
			throw new BusinessException(ResourceBundleHelper.getString(result.value()),EntityCodes.USER);
		}
		Locale locale = localeResolver.resolveLocale(request);
		UserMessages messages = new UserMessages(ErrorCodes.NORMAL_STAUTS, ResourceBundleHelper.getString(locale,
				"reg.success.actived"), "");
		messages.addCallBackList(ResourceBundleHelper.getString(locale, "login"),
				ResourceBundleHelper.getString(locale, "logon.hint.desc"), "login" + Constants.WEB_SUFFIX);
		request.setAttribute(UserMessages.MESSAGE_KEY, messages);
		return PathResolver.getPath(TilesPage.AFTER_OPERATION);
	}


	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BusinessService#setSearchFacade(com.legendshop.search.SearchFacade)
	 */
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


	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BusinessService#copyAll(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String getNewsforCommon(HttpServletRequest request, HttpServletResponse response) {
		// 采用公用帐号的信息
		String shopName = PropertiesUtil.getDefaultShopName();
		if (AppUtils.isBlank(shopName)) {
			shopName = Constants.COMMON_USER;
		}
		request.setAttribute("newsBottomList", newsDao.getNews(shopName, NewsPositionEnum.NEWS_BOTTOM, 8));
		return PathResolver.getPath(FrontPage.COPY_ALL);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BusinessService#productGallery(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Long)
	 */
	@Override
	public String getProductGallery(HttpServletRequest request,
			HttpServletResponse response,Long prodId) {
		ProductDetail prod = productDao.getProdDetail(prodId);
		if (prod != null) {
			if (!Constants.ONLINE.equals(prod.getStatus())) {
				throw new NotFoundException("Product does not online.",EntityCodes.PROD);
			}
			request.setAttribute("prod", prod);
			// 查看商品的说明图片
			List<ImgFile> prodPics = imgFileDao.getProductPics(prod.getUserName(), prodId);
			if (AppUtils.isNotBlank(prodPics)) {
				request.setAttribute("prodPics", prodPics);
			}
			return PathResolver.getPath(FrontPage.PROD_PIC_GALLERY);
		} else {
			UserMessages uem = new UserMessages();
			Locale locale = localeResolver.resolveLocale(request);
			uem.setTitle(ResourceBundleHelper.getString(locale, "product.not.found"));
			uem.setDesc(ResourceBundleHelper.getString(locale, "product.status.check"));
			uem.setCode(ErrorCodes.PRODUCT_NO_FOUND);
			request.setAttribute(UserMessages.MESSAGE_KEY, uem);
			return PathResolver.getPath(FrontPage.FAIL);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.BusinessService#getSubBySubNumber(java.lang.String)
	 */
	@Override
	public Sub getSubBySubNumber(String subNumber){
		return subDao.getSubBySubNumber(subNumber);
	}
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BusinessService#setProductDao(com.legendshop.business.dao.ProductDao)
	 */
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

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BusinessService#setImgFileDao(com.legendshop.business.dao.ImgFileDao)
	 */
	/**
	 * Sets the img file dao.
	 * 
	 * @param imgFileDao
	 *            the new img file dao
	 */
	@Required
	public void setImgFileDao(ImgFileDao imgFileDao) {
		this.imgFileDao = imgFileDao;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BusinessService#setBasketDao(com.legendshop.business.dao.BasketDao)
	 */
	/**
	 * Sets the basket dao.
	 * 
	 * @param basketDao
	 *            the new basket dao
	 */
	@Required
	public void setBasketDao(BasketDao basketDao) {
		this.basketDao = basketDao;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BusinessService#setSubDao(com.legendshop.business.dao.SubDao)
	 */
	/**
	 * Sets the sub dao.
	 * 
	 * @param subDao
	 *            the new sub dao
	 */
	@Required
	public void setSubDao(SubDao subDao) {
		this.subDao = subDao;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BusinessService#setHotsearchDao(com.legendshop.business.dao.HotsearchDao)
	 */
	/**
	 * Sets the hotsearch dao.
	 * 
	 * @param hotsearchDao
	 *            the new hotsearch dao
	 */
	@Required
	public void setHotsearchDao(HotsearchDao hotsearchDao) {
		this.hotsearchDao = hotsearchDao;
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
