/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao;

import java.util.List;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.model.dynamic.Item;
import com.legendshop.model.entity.Brand;

/**
 * The Interface BrandDao.
 */
public interface BrandDao extends BaseDao{

	/**
	 * 可以选择的品牌.
	 * 
	 * @param nsortId
	 *            the nsort id
	 * @param userName
	 *            the user name
	 * @return "select b from Brand b, NsortBrand n where b.brandId =
	 *         n.id.brandId and n.id.nsortId = ? and userName = ?
	 */

	public abstract List<Item> getUsableBrand(Long nsortId, String userName);

	/**
	 * 可以选择的品牌.
	 * 
	 * @param nsortId
	 *            the nsort id
	 * @param userName
	 *            the user name
	 * @param brandName
	 *            the brand name
	 * @return the usable brand by name
	 */
	public abstract List<Item> getUsableBrandByName(Long nsortId, String userName, String brandName);

	/**
	 * 已经选择的品牌.
	 * 
	 * @param nsortId
	 *            the nsort id
	 * @param userName
	 *            the user name
	 * @return the used brand
	 */
	public abstract List<Item> getUsedBrand(Long nsortId, String userName);

	/**
	 * Gets the used prod.
	 * 
	 * @param prodId
	 *            the prod id
	 * @param userName
	 *            the user name
	 * @return the used prod
	 */
	public abstract List<Item> getUsedProd(Long prodId, String userName);

	/**
	 * Gets the usable product item by name.
	 * 
	 * @param prodId
	 *            the prod id
	 * @param userName
	 *            the user name
	 * @param prodName
	 *            the prod name
	 * @return the usable product item by name
	 */
	public abstract List<Item> getUsableProductItemByName(Long prodId, String userName, String prodName);

	/**
	 * Gets the usable product item.
	 * 
	 * @param prodId
	 *            the prod id
	 * @param userName
	 *            the user name
	 * @return the usable product item
	 */
	public abstract List<Item> getUsableProductItem(Long prodId, String userName);


	/**
	 * Save brand item.
	 * 
	 * @param idList
	 *            the id list
	 * @param nsortId
	 *            the nsort id
	 * @param userName
	 *            the user name
	 * @return the string
	 */
	public abstract String saveBrandItem(List<String> idList, Long nsortId, String userName);

	/**
	 * Delete brand by id.
	 * 
	 * @param id
	 *            the id
	 */
	public abstract void deleteBrandById(Long id);

	/**
	 * Update brand.
	 * 
	 * @param brand
	 *            the brand
	 */
	public abstract void updateBrand(Brand brand); 

}