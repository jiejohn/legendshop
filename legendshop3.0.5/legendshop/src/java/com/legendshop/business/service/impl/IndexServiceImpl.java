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

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.legendshop.business.dao.ExternalLinkDao;
import com.legendshop.business.dao.ImgFileDao;
import com.legendshop.business.dao.NewsDao;
import com.legendshop.business.dao.ProductDao;
import com.legendshop.business.dao.PubDao;
import com.legendshop.business.dao.SubDao;
import com.legendshop.business.dao.UserCommentDao;
import com.legendshop.business.dao.UserDetailDao;
import com.legendshop.business.service.IndexService;
import com.legendshop.core.UserManager;
import com.legendshop.model.UserInfo;
import com.legendshop.model.entity.Indexjpg;
import com.legendshop.model.entity.ShopDetailView;
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

	/** The pub dao. */
	private PubDao pubDao;


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.service.impl.BusinessService#index(javax.servlet
	 * .http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void getIndex(HttpServletRequest request, ShopDetailView shopDetail) {

		String shopName = shopDetail.getUserName();

		request.setAttribute("commendProdList", productDao.getCommendProd(shopName, 40));
		// 最新商品
		request.setAttribute("newestProdList", productDao.getNewestProd(shopName, 11));

		request.setAttribute("pubList", pubDao.getPub(shopName));
		getAndSetAdvertisement(shopName);
		// 普通新闻
		request.setAttribute("newList", newsDao.getNews(shopName, NewsPositionEnum.NEWS_NEWS, 6));

		List<Indexjpg> indexJpgList = imgFileDao.getIndexJpeg(shopName);
		if (!AppUtils.isBlank(indexJpgList)) {
			request.setAttribute("MaxScreen", indexJpgList.size());
			JSONArray jsonArray = JSONArray.fromObject(indexJpgList);
			request.setAttribute("indexJSON", jsonArray);
		} else {
			request.setAttribute("MaxScreen", 0);
		}

		request.setAttribute("externalLinkList", externalLinkDao.getExternalLink(shopName));
		
		String userName = UserManager.getUsername(request.getSession());
		logUserAccess(request, shopName, userName);
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


	public void setPubDao(PubDao pubDao) {
		this.pubDao = pubDao;
	}


}
