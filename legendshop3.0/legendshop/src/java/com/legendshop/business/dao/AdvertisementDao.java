package com.legendshop.business.dao;

import java.util.List;
import java.util.Map;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.model.entity.Advertisement;

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

}