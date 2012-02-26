/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Product;

public interface ProductService {

	/**
	 * Find product.
	 * 
	 * @param hql
	 *            the hql
	 * @return the page support 
	 */
	public abstract PageSupport getProductList(HqlQuery hql);

	/**
	 * Find product.
	 * 
	 * @param cq
	 *            the cq
	 * @return the page support
	 */
	public abstract PageSupport getProductList(CriteriaQuery cq);

	/**
	 * 根据ID获取一个产品.
	 * 
	 * @param prodId
	 *            the prod id
	 * @return the product
	 */
	public abstract Product getProductById(Long prodId);

	/**
	 * 更新产品.
	 * 
	 * @param product
	 *            the product
	 */
	public abstract void updateProduct(Product product);

	/**
	 * 保存产品.
	 * 
	 * @param product
	 *            the product
	 * @return 产品ID
	 */
	public abstract Long saveProduct(Product product);

}