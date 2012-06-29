/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.spi.service;

import java.util.List;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.DynamicTemp;
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
	public abstract void updateProduct(Product product, Product origin);

	/**
	 * 保存产品.
	 * 
	 * @param product
	 *            the product
	 * @return 产品ID
	 */
	public abstract Long saveProduct(Product product,String prodType);
	
	// 商品动态属性
	/**
	 * Load attributeprod attribute.
	 * 
	 * @param prodId
	 *            the prod id
	 * @return the string
	 */
	public abstract String getAttributeprodAttribute(Long prodId);
	
	// 得到商品
	/**
	 * Load prod.
	 * 
	 * @param prodId
	 *            the prod id
	 * @param userName
	 *            the user name
	 * @return the product
	 */
	public abstract Product getProd(Long prodId, String userName);
	
	// 商品动态参数
	/**
	 * Load prod parameter.
	 * 
	 * @param prodId
	 *            the prod id
	 * @return the string
	 */
	public abstract String getProdParameter(Long prodId);
	




	/**
	 * Save dynamic temp.
	 * 
	 * @param tempName
	 *            the temp name
	 * @param userName
	 *            the user name
	 * @param type
	 *            the type
	 * @param content
	 *            the content
	 * @return true, if successful
	 */
	public abstract boolean saveDynamicTemp(String tempName, String userName, Short type, String content);

	/**
	 * 更新产品动态模板.
	 * 
	 * @param tempId
	 *            the temp id
	 * @param userName
	 *            the user name
	 * @param type
	 *            the type
	 * @param content
	 *            the content
	 * @return true, if successful
	 */
	public abstract boolean updateDynamicTemp(Long tempId, String userName, Short type, String content);

	/**
	 * Load dynamic temp.
	 * 
	 * @param tempId
	 *            the temp id
	 * @param userName
	 *            the user name
	 * @return the dynamic temp
	 */
	public abstract DynamicTemp getDynamicTemp(Long tempId, String userName);

	/**
	 * Delete dynamic temp.
	 * 
	 * @param tempId
	 *            the temp id
	 * @param userName
	 *            the user name
	 * @return true, if successful
	 */
	public abstract boolean deleteDynamicTemp(Long tempId, String userName);
	
	/**
	 * Save prod item.
	 * 
	 * @param idList
	 *            the id list
	 * @param nameList
	 *            the name list
	 * @param prodId
	 *            the prod id
	 * @param userName
	 *            the user name
	 * @return the string
	 */
	public abstract String saveProdItem(List<String> idList, List<String> nameList, Long prodId, String userName);
	
	/**
	 * 商品上线.
	 * 
	 * @param prodId
	 *            商品Id
	 * @return true, if successful
	 */
	public abstract boolean updateProdOnline(Long prodId);

	/**
	 * 商品下线.
	 * 
	 * @param prodId
	 *            商品Id
	 * @return true, if successful
	 */
	public abstract boolean updateProdOffline(Long prodId);
	



	/**
	 * 更新商品.
	 * 
	 * @param product
	 *            the product
	 */
	public abstract void updateProd(Product product);


}