/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.spi.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.LocaleResolver;

import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.model.entity.Advertisement;
import com.legendshop.spi.constants.Constants;
import com.legendshop.spi.dao.AdvertisementDao;
import com.legendshop.spi.dao.ShopDetailDao;
import com.legendshop.spi.service.BaseService;
import com.legendshop.util.AppUtils;

/**
 * The Class AbstractService.
 */
public abstract class AbstractService implements BaseService {
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(AbstractService.class);

	/** The shop detail dao. */
	protected ShopDetailDao shopDetailDao;

	/** The locale resolver. */
	protected LocaleResolver localeResolver;
	

	protected AdvertisementDao advertisementDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.service.impl.BaseService#getSessionAttribute(
	 * javax.servlet.http.HttpServletRequest, java.lang.String)
	 */

	public Object getSessionAttribute(HttpServletRequest request, String name) {
		Object obj = null;
		HttpSession session = request.getSession();
		if (session != null) {
			obj = session.getAttribute(name);
		}
		return obj;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.service.impl.BaseService#setSessionAttribute(
	 * javax.servlet.http.HttpServletRequest, java.lang.String,
	 * java.lang.Object)
	 */

	public void setSessionAttribute(HttpServletRequest request, String name, Object obj) {
		HttpSession session = request.getSession();
		if (session != null) {
			session.setAttribute(name, obj);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.service.impl.BaseService#setShopName(javax.servlet
	 * .http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.String)
	 */

	public void setShopName(HttpServletRequest request, HttpServletResponse response, String shopName) {
		// Session
		String name = ThreadLocalContext.getCurrentShopName(request, response);
		if (name == null || !name.equals(shopName)) {
			setSessionAttribute(request, Constants.SHOP_NAME, shopName);
		}

		// Cookie
		// Cookie cookie = new Cookie(Constants.SHOP_NAME, shopName);
		// //生命周期
		// cookie.setMaxAge(60 * 60 * 24 * 365);
		// cookie.setPath("/");
		// response.addCookie(cookie);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.service.impl.BaseService#convertStringToInteger
	 * (java.lang.String)
	 */

	public Long convertStringToInteger(String id) {
		try {
			Long result = Long.valueOf(id);
			if (result == 0) {
				return null;
			} else {
				return result;
			}
		} catch (Exception e) {
			log.error("can not convert id " + id);
			return null;
		}
	}

	
	/**
	 * 设置广告
	 * @param shopName
	 */
	public void getAndSetAdvertisement(HttpServletRequest request, HttpServletResponse response,String shopName) {
		Map<String, List<Advertisement>> advertisement = advertisementDao.getAdvertisement(shopName);
		if (!AppUtils.isBlank(advertisement)) {
			for (String type : advertisement.keySet()) {
				if (Constants.COUPLET.equals(type)) {
					List<Advertisement> list = advertisement.get(type);
					if (AppUtils.isNotBlank(list)) {
						request.setAttribute(type, list.get(random(list.size())));
					}
				} else {
					request.setAttribute(type, advertisement.get(type));
				}

			}
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
	public void getAndSetOneAdvertisement(HttpServletRequest request, HttpServletResponse response,String shopName, String key) {
		List<Advertisement> advertisement = advertisementDao.getOneAdvertisement(shopName, key);
		if (!AppUtils.isBlank(advertisement)) {
			request.setAttribute(key, advertisement);
		}
	}
	
	
	protected int random(int count) {
		Random random = new Random();
		return random.nextInt(count);
	}

	/**
	 * Sets the shop detail dao.
	 * 
	 * @param shopDetailDao
	 *            the new shop detail dao
	 */
	public void setShopDetailDao(ShopDetailDao shopDetailDao) {
		this.shopDetailDao = shopDetailDao;
	}

	/**
	 * Sets the locale resolver.
	 * 
	 * @param localeResolver
	 *            the new locale resolver
	 */
	public void setLocaleResolver(LocaleResolver localeResolver) {
		this.localeResolver = localeResolver;
	}
	
	public void setAdvertisementDao(AdvertisementDao advertisementDao) {
		this.advertisementDao = advertisementDao;
	}

}
