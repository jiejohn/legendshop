/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.spi.service;

import javax.servlet.http.HttpServletRequest;

import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.GroupProduct;
import com.legendshop.model.entity.Product;


/**
 * The Interface GroupProductService.
 */
public interface GroupProductService {


	/**
	 * Gets the group product list.
	 * 
	 * @param hql
	 *            the hql
	 * @return the group product list
	 */
	public abstract PageSupport getGroupProductList(HqlQuery hql);


	/**
	 * Update group product.
	 * 
	 * @param groupProduct
	 *            the group product
	 */
	public abstract void updateGroupProduct(GroupProduct groupProduct);


	/**
	 * Save group product.
	 * 
	 * @param request
	 *            the request
	 * @param groupProduct
	 *            the group product
	 * @param product
	 *            the product
	 * @param loginName
	 *            the login name
	 * @return the long
	 */
	public abstract String saveGroupProduct(HttpServletRequest request,GroupProduct groupProduct,Product product,String loginName);


	/**
	 * Gets the product by id.
	 * 
	 * @param prodId
	 *            the prod id
	 * @return the product by id
	 */
	public abstract Product getProductById(Long prodId);


	/**
	 * Update product.
	 * 
	 * @param product
	 *            the product
	 * @param origin
	 *            the origin
	 * @param entity
	 *            the entity
	 */
	public abstract void updateProduct(Product product, Product origin, GroupProduct entity);


	/**
	 * Save product.
	 * 
	 * @param product
	 *            the product
	 * @param entity
	 *            the entity
	 */
	public abstract void saveProduct(Product product, GroupProduct entity);


	/**
	 * Delete product.
	 * 
	 * @param prodId
	 *            the prod id
	 */
	public abstract void deleteProduct(Long prodId);
	
	/**
	 * Gets the group product.
	 * 
	 * @param prodId
	 *            the prod id
	 * @return the group product
	 */
	public GroupProduct getGroupProduct(Long prodId);
}
