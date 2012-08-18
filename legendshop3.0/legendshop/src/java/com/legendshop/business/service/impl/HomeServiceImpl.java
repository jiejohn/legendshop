/*
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * 官方网站：http://www.legendesign.net
 */
package com.legendshop.business.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.legendshop.business.common.page.TilesPage;
import com.legendshop.business.dao.ImgFileDao;
import com.legendshop.business.dao.ProductDao;
import com.legendshop.business.dao.PubDao;
import com.legendshop.business.dao.SortDao;
import com.legendshop.business.service.HomeService;
import com.legendshop.core.UserManager;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.model.entity.Indexjpg;

/**
 * @author George Guo
 * 
 */
public class HomeServiceImpl extends BaseServiceImpl implements HomeService {

	/** The log. */
	private static Logger logger = LoggerFactory.getLogger(HomeServiceImpl.class);

	/** The product dao. */
	private ProductDao productDao;

	/** The img file dao. */
	private ImgFileDao imgFileDao;

	/** The sort dao. */
	private SortDao sortDao;

	/** The pub dao. */
	private PubDao pubDao;

	@Override
	public String getHome(HttpServletRequest request, HttpServletResponse response) {
		String shopName = ThreadLocalContext.getCurrentShopName();

		// 设置产品分类
		request.setAttribute("sortList", sortDao.getSort(shopName, true));

		// 设置幻灯片广告
		List<Indexjpg> indexJpgList = imgFileDao.getIndexJpeg(shopName);
		request.setAttribute("indexJpgList", indexJpgList);

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

		super.logUserAccess(request, shopName, userName);

		return PathResolver.getPath(TilesPage.HOME);
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

}
