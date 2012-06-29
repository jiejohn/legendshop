/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.cache.annotation.CacheEvict;

import com.legendshop.business.dao.AdvertisementDao;
import com.legendshop.business.service.AdvertisementService;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Advertisement;
import com.legendshop.util.AppUtils;

/**
 * 广告服务.
 */
public class AdvertisementServiceImpl implements AdvertisementService {
	
	/** The advertisement dao. */
	private AdvertisementDao advertisementDao;

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.AdvertisementService#findAdvertisement(java.lang.String)
	 */
	@Override
	public List<Advertisement> getAdvertisementByUserName(String userName) {
		return advertisementDao.findByHQL("from Advertisement where userName = ?", userName);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.AdvertisementService#load(java.lang.Long)
	 */
	@Override
	public Advertisement getAdvertisement(Long id) {
		return advertisementDao.get(Advertisement.class, id);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.AdvertisementService#isMaxNum(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isMaxNum(String userName, String type) {
		return advertisementDao.isMaxNum(userName, type);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.AdvertisementService#delete(java.lang.Long)
	 */
	@Override
	@CacheEvict(value = "Advertisement", key="#id")
	public void delete(Long id) {
		advertisementDao.deleteAdvById(id);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.AdvertisementService#save(com.legendshop.model.entity.Advertisement)
	 */
	@Override
	public Long save(Advertisement advertisement) {
		if (!AppUtils.isBlank(advertisement.getId())) {
			update(advertisement);
			return advertisement.getId();
		}
		return (Long) advertisementDao.save(advertisement);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.AdvertisementService#update(com.legendshop.model.entity.Advertisement)
	 */
	@Override
	@CacheEvict(value = "Advertisement", key="#advertisement.id")
	public void update(Advertisement advertisement) {
		advertisementDao.updateAdv(advertisement);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.AdvertisementService#getDataByCriteriaQuery(com.legendshop.core.dao.support.CriteriaQuery)
	 */
	@Override
	public PageSupport getDataByCriteriaQuery(CriteriaQuery cq) {
		return advertisementDao.find(cq);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.AdvertisementService#getOneAdvertisement(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Advertisement> getOneAdvertisement(String shopName, String key) {
		return advertisementDao.getOneAdvertisement(shopName, key);
	}
	
	/**
	 * Sets the advertisement dao.
	 * 
	 * @param advertisementDao
	 *            the new advertisement dao
	 */
	@Required
	public void setAdvertisementDao(AdvertisementDao advertisementDao) {
		this.advertisementDao = advertisementDao;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.AdvertisementService#getAdvertisement(java.lang.String)
	 */
	@Override
	public Map<String, List<Advertisement>> getAdvertisement(String shopName) {
		return advertisementDao.getAdvertisement(shopName);
	}

}
