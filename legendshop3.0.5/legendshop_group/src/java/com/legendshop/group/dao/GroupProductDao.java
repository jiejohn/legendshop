/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.group.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.model.entity.GroupProduct;
import com.legendshop.model.entity.Product;

/**
 * The Interface GroupProductDao.
 */
public interface GroupProductDao  extends BaseDao{

	/**
	 * Gets the product by id.
	 * 
	 * @param prodId
	 *            the prod id
	 * @return the product by id
	 */
	public Product getProductById(Long prodId);

	/**
	 * Update product.
	 * 
	 * @param product
	 *            the product
	 */
	public void updateProduct(GroupProduct product);

	/**
	 * Save product.
	 * 
	 * @param product
	 *            the product
	 */
	public void saveProduct(GroupProduct product);

	/**
	 * Delete product.
	 * 
	 * @param prodId
	 *            the prod id
	 */
	public void deleteProduct(Long prodId);
	
	/**
	 * Gets the group product.
	 * 
	 * @param prodId
	 *            the prod id
	 * @return the group product
	 */
	public GroupProduct getGroupProduct(Long prodId);

}
