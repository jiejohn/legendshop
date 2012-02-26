package com.legendshop.business.dao;

import java.util.List;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.model.dynamic.Item;

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

}