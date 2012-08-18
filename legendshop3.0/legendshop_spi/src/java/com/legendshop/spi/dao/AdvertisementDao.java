/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.spi.dao;

import java.util.List;
import java.util.Map;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.model.entity.Advertisement;

/**
 * The Interface AdvertisementDao.
 */
public interface AdvertisementDao extends BaseDao{

	/**
	 * Gets the advertisement.
	 * 
	 * @param shopName
	 *            the shop name
	 * @return the advertisement
	 */
	public abstract Map<String, List<Advertisement>> getAdvertisement(final String shopName);

	/**
	 * Gets the advertisement.
	 * 
	 * @param shopName
	 *            the shop name
	 * @param key
	 *            the key
	 * @return the advertisement
	 */
	public abstract List<Advertisement> getAdvertisement(String shopName, String key);

	// 随机得到一个广告
	/**
	 * Gets the one advertisement.
	 * 
	 * @param shopName
	 *            the shop name
	 * @param key
	 *            the key
	 * @return the one advertisement
	 */
	public abstract List<Advertisement> getOneAdvertisement(String shopName, String key);

	/**
	 * Check max num.
	 * 
	 * @param userName
	 *            the user name
	 * @param type
	 *            the type
	 * @return true, if successful
	 */
	public abstract boolean isMaxNum(String userName, String type);

	/**
	 * Delete adv by id.
	 * 
	 * @param id
	 *            the id
	 */
	public abstract void deleteAdvById(Long id);

	/**
	 * Update adv.
	 * 
	 * @param advertisement
	 *            the advertisement
	 */
	public abstract void updateAdv(Advertisement advertisement);

}