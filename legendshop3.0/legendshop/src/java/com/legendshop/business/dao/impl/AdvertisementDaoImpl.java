/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.cache.annotation.Cacheable;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.business.dao.AdvertisementDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.model.entity.Advertisement;
import com.legendshop.util.AppUtils;
import com.legendshop.util.sql.ConfigCode;

/**
 * 广告Dao.
 */
@SuppressWarnings("unchecked")
public class AdvertisementDaoImpl extends BaseDaoImpl implements AdvertisementDao {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(AdvertisementDaoImpl.class);
	
	/** The max num per type. */
	private Integer maxNumPerType;

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.AdvertisementDao#getAdvertisement(java.lang.String)
	 */
	@Override
	@Cacheable(value="AdvertisementList",key="#shopName")
	public Map<String, List<Advertisement>> getAdvertisement(final String shopName) {
			log.debug("shopName = {}", shopName);
			Map<String, List<Advertisement>> advertisementMap = new LinkedHashMap<String, List<Advertisement>>();
			List<Advertisement> list = find(ConfigCode.getInstance().getCode("ad.getAdvertisement"),
					shopName);
			for (Advertisement advertisement : list) {
				List<Advertisement> ads = advertisementMap.get(advertisement.getType());
				if (ads == null) {
					ads = new ArrayList<Advertisement>();
				}
				ads.add(advertisement);
				advertisementMap.put(advertisement.getType(), ads);
			}
			return advertisementMap;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.AdvertisementDao#getAdvertisement(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Advertisement> getAdvertisement(String shopName, String key) {
		if (log.isDebugEnabled()) {
			log.debug("shopName = {},key = {}", shopName, key);
		}
		Map<String, List<Advertisement>> advertisementMap = getAdvertisement(shopName);
		if (advertisementMap != null) {
			return advertisementMap.get(key);
		}
		return null;
	}

	// 随机得到一个广告
	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.AdvertisementDao#getOneAdvertisement(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Advertisement> getOneAdvertisement(String shopName, String key) {
		if (log.isDebugEnabled()) {
			log.debug("shopName = {},key = {}", shopName, key);
		}
		Map<String, List<Advertisement>> advertisementMap = getAdvertisement(shopName);
		List<Advertisement> resunt = null;
		if (advertisementMap != null) {
			List<Advertisement> list = advertisementMap.get(key);
			if (AppUtils.isNotBlank(list)) {
				resunt = new ArrayList<Advertisement>(1);
				resunt.add(list.get(CommonServiceUtil.random(list.size())));
			}
		}
		return resunt;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.AdvertisementDao#checkMaxNum(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isMaxNum(String userName, String type) {
		boolean result = false;
		Long num = findUniqueBy(ConfigCode.getInstance().getCode("ad.getAdvertisementCount"), Long.class, userName,
				type);
		if (num != null) {
			result = num <= maxNumPerType;
		}
		if (log.isDebugEnabled()) {
			log.debug("userName = {},type = {},result = {}", new Object[] { userName, type, result });
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.AdvertisementDao#setMaxNumPerType(java.lang.Integer)
	 */
	@Required
	public void setMaxNumPerType(Integer maxNumPerType) {
		this.maxNumPerType = maxNumPerType;
	}
}
