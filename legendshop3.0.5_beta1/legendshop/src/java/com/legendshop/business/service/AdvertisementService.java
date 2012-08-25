/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service;

import java.util.List;
import java.util.Map;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Advertisement;
import com.legendshop.spi.service.BaseService;

public interface AdvertisementService extends BaseService{

	/**
	 * List.
	 * 
	 * @param userName
	 *            the user name
	 * @return the list
	 */
	public abstract List<Advertisement> getAdvertisementByUserName(String userName);

	/**
	 * Load.
	 * 
	 * @param id
	 *            the id
	 * @return the advertisement
	 */
	public abstract Advertisement getAdvertisement(Long id);

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
	 * Delete.
	 * 
	 * @param id
	 *            the id
	 */
	public abstract void delete(Long id);

	/**
	 * Save.
	 * 
	 * @param advertisement
	 *            the advertisement
	 * @return the long
	 */
	public abstract Long save(Advertisement advertisement);

	/**
	 * Update.
	 * 
	 * @param advertisement
	 *            the advertisement
	 */
	public abstract void update(Advertisement advertisement);

	/**
	 * Gets the data by criteria query.
	 * 
	 * @param cq
	 *            the cq
	 * @return the data by criteria query
	 */
	public abstract PageSupport getDataByCriteriaQuery(CriteriaQuery cq);

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
	 * Gets the advertisement.
	 * 
	 * @param shopName
	 *            the shop name
	 * @return the advertisement
	 */
	public abstract Map<String, List<Advertisement>> getAdvertisement(String shopName);

}