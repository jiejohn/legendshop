/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.legendshop.business.common.page.TilesPage;
import com.legendshop.business.dao.ExternalLinkDao;
import com.legendshop.business.dao.ImgFileDao;
import com.legendshop.business.dao.LogoDao;
import com.legendshop.business.dao.NewsDao;
import com.legendshop.business.dao.ProductDao;
import com.legendshop.business.dao.PubDao;
import com.legendshop.business.dao.SortDao;
import com.legendshop.business.dao.SubDao;
import com.legendshop.business.dao.UserCommentDao;
import com.legendshop.business.dao.UserDetailDao;
import com.legendshop.business.event.impl.VisitLogEvent;
import com.legendshop.business.service.IndexService;
import com.legendshop.core.UserManager;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.exception.ApplicationException;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.event.EventHome;
import com.legendshop.model.UserInfo;
import com.legendshop.model.entity.Indexjpg;
import com.legendshop.model.entity.ShopDetailView;
import com.legendshop.spi.constants.Constants;
import com.legendshop.spi.constants.NewsPositionEnum;
import com.legendshop.util.AppUtils;

/**
 * 首页相关服务.
 */
public class IndexServiceImpl extends BaseServiceImpl implements IndexService {

	/** The log. */
	private static Logger log = LoggerFactory.getLogger(IndexServiceImpl.class);

	/** The user comment dao. */
	private UserCommentDao userCommentDao;

	/** The user detail dao. */
	private UserDetailDao userDetailDao;

	/** The news dao. */
	private NewsDao newsDao;

	/** The external link dao. */
	private ExternalLinkDao externalLinkDao;

	/** The product dao. */
	private ProductDao productDao;

	/** The img file dao. */
	private ImgFileDao imgFileDao;

	/** The sub dao. */
	private SubDao subDao;

	/** The sort dao. */
	private SortDao sortDao;

	/** The pub dao. */
	private PubDao pubDao;

	/** The logo dao. */
	private LogoDao logoDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.service.impl.BusinessService#index(javax.servlet
	 * .http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String getIndex(HttpServletRequest request, HttpServletResponse response) {

		String shopName = extractAndCheckShopDetail(request, response);

		request.setAttribute("productList", productDao.getCommendProd(shopName, 40));
		// 最新商品
		request.setAttribute("newestList", productDao.getNewestProd(shopName, 11));
		request.setAttribute("adList", externalLinkDao.getExternalLink(shopName));

		request.setAttribute("logo", logoDao.getLogo(shopName));
		request.setAttribute("sortList", sortDao.getSort(shopName, true));
		request.setAttribute("pubList", pubDao.getPub(shopName));

		// 普通新闻
		request.setAttribute("newList", newsDao.getNews(shopName, NewsPositionEnum.NEWS_NEWS, 6));
		// 顶部新闻
		request.setAttribute("newsTopList", newsDao.getNews(shopName, NewsPositionEnum.NEWS_TOP, 8));
		// 分类新闻
		request.setAttribute("newsSortList", newsDao.getNews(shopName, NewsPositionEnum.NEWS_SORT, 8));

		setAdvertisement(shopName, request);
		List<Indexjpg> indexJpgList = imgFileDao.getIndexJpeg(shopName);
		if (!AppUtils.isBlank(indexJpgList)) {
			request.setAttribute("MaxScreen", indexJpgList.size());
			JSONArray jsonArray = JSONArray.fromObject(indexJpgList);
			request.setAttribute("indexJSON", jsonArray);
		} else {
			request.setAttribute("MaxScreen", 0);
		}

		String userName = UserManager.getUsername(request.getSession());
		boolean shopExists = shopDetailDao.isShopExists(userName);
		request.setAttribute("shopExists", shopExists);
		request.setAttribute("canbeLeagueShop", shopDetailDao.isBeLeagueShop(shopExists, userName, shopName));

		logUserAccess(request, shopName, userName);
		return PathResolver.getPath(request, TilesPage.INDEX_PAGE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.service.IndexService#indexAdmin(java.lang.String,
	 * com.legendshop.model.entity.ShopDetailView)
	 */
	@Override
	public UserInfo getAdminIndex(String userName, ShopDetailView shopDetail) {
		UserInfo userInfo = new UserInfo();

		if (shopDetail != null) { // 已有商城的用户

			userInfo.setArticleNum(newsDao.getAllNews(userName));

			userInfo.setProcessingOrderNum(subDao.getTotalProcessingOrder(userName));
			userInfo.setUnReadMessageNum(userCommentDao.getTotalUnReadMessage(userName));

			userInfo.setShopDetail(shopDetail);
		} else {// 管理员
			userInfo.setUserTotalNum(userDetailDao.getAllUserCount());

			userInfo.setShopTotalNum(shopDetailDao.getAllShopCount());
		}

		return userInfo;
	}

	@Override
	public String getHome(HttpServletRequest request, HttpServletResponse response) {
		String shopName = extractAndCheckShopDetail(request, response);

		// 设置产品分类
		request.setAttribute("sortList", sortDao.getSort(shopName, true));

		// 设置幻灯片广告
		List<Indexjpg> indexJpgList = imgFileDao.getIndexJpeg(shopName);
		if (!AppUtils.isBlank(indexJpgList)) {
			request.setAttribute("MaxScreen", indexJpgList.size());
			JSONArray jsonArray = JSONArray.fromObject(indexJpgList);
			request.setAttribute("indexJSON", jsonArray);
		} else {
			request.setAttribute("MaxScreen", 0);
		}

		// 设置商城公告
		request.setAttribute("pubList", pubDao.getPub(shopName));

		// 最新商品
		request.setAttribute("newestList", productDao.getNewestProd(shopName, 4));

		// 设置内容块(Tag)

		// TODO
		// //设置友情链接
		// request.setAttribute("adList",
		// externalLinkDao.getExternalLink(shopName));

		// boolean shopExists = shopDetailDao.isShopExists(userName);
		// request.setAttribute("shopExists", shopExists);
		// request.setAttribute("canbeLeagueShop",
		// shopDetailDao.isBeLeagueShop(shopExists, userName, shopName));

		String userName = UserManager.getUsername(request.getSession());
		logUserAccess(request, shopName, userName);

		return PathResolver.getPath(request, TilesPage.HOME);
	}

	/**
	 * @param request
	 * @param shopName
	 * @param userName
	 */
	private void logUserAccess(HttpServletRequest request, String shopName, String userName) {
		// 多线程记录访问历史
		if (PropertiesUtil.getObject(ParameterEnum.VISIT_LOG_INDEX_ENABLE, Boolean.class)) {
			EventHome.publishEvent(new VisitLogEvent(request.getRemoteAddr(), shopName, userName));
		} else {
			if (log.isInfoEnabled()) {
				log.info("[{}],{} visit index {}", new Object[] { request.getRemoteAddr(), userName, shopName });
			}
		}
	}

	/**
	 * @param request
	 * @param response
	 * @param shopName
	 * @return
	 */
	private String extractAndCheckShopDetail(HttpServletRequest request, HttpServletResponse response) {
		String shopName = (String) request.getAttribute(Constants.SHOP_NAME);
		ShopDetailView shopDetail =  ThreadLocalContext.getShopDetailView(shopName, request, response);  // 得到当前商城
		
		if (shopDetail == null) {
			String defaultShop = PropertiesUtil.getObject(ParameterEnum.DEFAULT_SHOP, String.class);

			if (AppUtils.isBlank(defaultShop)) {
				throw new NotInstalledException();
			}

			// 如果有默认店，则先到默认店去，默认店要先配置好
			shopName = defaultShop;
			shopDetail = ThreadLocalContext.getShopDetailView(shopName, request, response); 

		} else {
			shopName = shopDetail.getUserName();
			// 更新用户访问历史
			updateVisitHistory(shopDetail, request);
		}
		return shopName;
	}

	class NotInstalledException extends ApplicationException {
		/**
		 * serialVersionUID
		 */
		private static final long serialVersionUID = 1780530219363637894L;

		public NotInstalledException() {
			this(null, null);
		}

		public NotInstalledException(String message, String code) {
			super("System is not installed", EntityCodes.SYSTEM);
		}
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

	/**
	 * Sets the user comment dao.
	 * 
	 * @param userCommentDao
	 *            the new user comment dao
	 */
	@Required
	public void setUserCommentDao(UserCommentDao userCommentDao) {
		this.userCommentDao = userCommentDao;
	}

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

	public UserCommentDao getUserCommentDao() {
		return userCommentDao;
	}

	public void setExternalLinkDao(ExternalLinkDao externalLinkDao) {
		this.externalLinkDao = externalLinkDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public void setImgFileDao(ImgFileDao imgFileDao) {
		this.imgFileDao = imgFileDao;
	}


	public void setSortDao(SortDao sortDao) {
		this.sortDao = sortDao;
	}

	public void setPubDao(PubDao pubDao) {
		this.pubDao = pubDao;
	}

	public void setLogoDao(LogoDao logoDao) {
		this.logoDao = logoDao;
	}

}
