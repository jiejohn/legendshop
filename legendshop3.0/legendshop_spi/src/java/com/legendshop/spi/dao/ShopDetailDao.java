/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.spi.dao;

import java.util.List;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.model.entity.Myleague;
import com.legendshop.model.entity.Product;
import com.legendshop.model.entity.ShopDetail;
import com.legendshop.model.entity.ShopDetailView;

/**
 * The Interface ShopDetailDao.
 */
public interface ShopDetailDao extends BaseDao{

	/**
	 * Checks if is shop exists.
	 * 
	 * @param userName
	 *            the store name
	 * @return the boolean
	 */
	public abstract Boolean isShopExists(final String userName);

	/**
	 * Gets the shop detail for update.
	 * 
	 * @param userName
	 *            the store name
	 * @return the shop detail for update
	 */
	public abstract ShopDetail getShopDetailForUpdate(final String userName);

	/**
	 * Gets the shop detail view.
	 * 
	 * @param userName
	 *            the store name
	 * @return the shop detail view
	 */
	public abstract ShopDetailView getShopDetailView(final String userName);

	/**
	 * Gets the simple info shop detail.
	 * 
	 * @param userName
	 *            the store name
	 * @return the simple info shop detail
	 */
	public abstract ShopDetailView getSimpleInfoShopDetail(final String userName);

	/**
	 * Gets the shop detail.
	 * 
	 * @param userName
	 *            the store name
	 * @return the shop detail
	 */
	public abstract ShopDetail getShopDetail(final String userName);
	
	/**
	 * Gets the shop detail.
	 * 
	 * @param shopId
	 *            the shop id
	 * @return the shop detail
	 */
	public abstract List<ShopDetailView> getShopDetail(final Long[] shopId);

	/**
	 * Checks if is league shop exists.
	 * 
	 * @param userName
	 *            the store name
	 * @return the boolean
	 */
	public abstract Boolean isLeagueShopExists(final String userName);

	/**
	 * Canbe league shop.
	 * 
	 * @param isShopExists
	 *            the is shop exists
	 * @param userName
	 *            the store name
	 * @param friendName
	 *            the friend name
	 * @return the boolean
	 */
	public abstract Boolean isBeLeagueShop(final boolean isShopExists, final String userName, final String friendName);

	/**
	 * Find myleague by user name and shop name.
	 * 
	 * @param userName
	 *            the user name
	 * @param shopName
	 *            the shop name
	 * @return the myleague
	 */
	public abstract Myleague getMyleague(String userName, String shopName);

	/**
	 * Save myleague.
	 * 
	 * @param myleague
	 *            the myleague
	 * @return the long
	 */
	public abstract Long saveMyleague(Myleague myleague);

	/**
	 * Inits the color type options.
	 * 
	 * @return the color type options
	 */
	public abstract void getColorTypeOptions();

	/**
	 * Gets the product num.
	 * 
	 * @param userName
	 *            the user name
	 * @return the product num
	 */
	public abstract Integer getProductNum(String userName);

	/**
	 * Gets the off product num.
	 * 
	 * @param userName
	 *            the user name
	 * @return the off product num
	 */
	public abstract Integer getOffProductNum(String userName);


	/**
	 * 更新产品.
	 * 
	 * @param shopdetail
	 *            the shopdetail
	 */
	public abstract void updateShopDetail(ShopDetail shopdetail);

	/**
	 * 更新产品时更新商城所拥有的产品数.
	 * 
	 * @param product
	 *            the product
	 */
	public abstract void updateShopDetailWhenProductChange(Product product);
	
	
	/**
	 * Gets the shop detail by user id.
	 * 
	 * @param userId
	 *            the user id
	 * @return the shop detail by user id
	 */
	public abstract ShopDetail getShopDetailByUserId(String userId);

	/**
	 * Update shop detail.
	 * 
	 * @param userName
	 *            the user name
	 */
	public abstract void updateShopDetail(String userName);

	/**
	 * Update shop.
	 * 
	 * @param loginUserName
	 *            the login user name
	 * @param userId
	 *            the user id
	 * @param shopDetail
	 *            the shop detail
	 * @param status
	 *            the status
	 * @return true, if successful
	 */
	public abstract boolean updateShop(String loginUserName, String userId, ShopDetail shopDetail, Integer status);

	/**
	 * Save shop detail.
	 * 
	 * @param shopDetail
	 *            the shop detail
	 */
	public abstract void saveShopDetail(ShopDetail shopDetail);
	
	/**
	 * Gets the shop detail by shop id.
	 * 
	 * @param shopId
	 *            the shop id
	 * @return the shop detail by shop id
	 */
	public abstract ShopDetail getShopDetailByShopId(final Long shopId);

	/**
	 * Delete shop detail by id.
	 * 
	 * @param id
	 *            the id
	 */
	public abstract void deleteShopDetailById(Long id);

	/**
	 * Delete shop detail.
	 * 
	 * @param shopDetail
	 *            the shop detail
	 */
	public abstract void deleteShopDetail(ShopDetail shopDetail);

	/**
	 * Gets the all shop count.
	 * 
	 * @return the all shop count
	 */
	public abstract Long getAllShopCount();

}