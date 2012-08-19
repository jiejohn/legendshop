/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.ws.service;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.legendshop.ws.model.Product;

/**
 * The Interface ProductService.
 * 导入商品
 */
@WebService(targetNamespace = "http://product.service.ws.legendshop.com", name = "productService")
public interface ProductService {
	
	/**
	 * Receive product.
	 * RFID中心数据库导出商品数据到电子商务平台接口
	 * @param product
	 *            the product
	 * @return the string
	 */
	public String importProductService(@WebParam(name="product") Product product);
}
