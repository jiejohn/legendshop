/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.ws.service.impl;

import javax.jws.WebService;

import com.legendshop.ws.model.Product;
import com.legendshop.ws.service.ProductService;

/**
 * The Class ProductServiceImpl.
 */
@WebService(endpointInterface="com.legendshop.ws.service.ProductService",
serviceName="ProductService",
portName="ProductServicePort",
targetNamespace="http://product.service.ws.legendshop.com")
public class ProductServiceImpl implements ProductService{

	/* (non-Javadoc)
	 * @see com.legendshop.ws.service.ProductService#importProductService(com.legendshop.ws.model.Product)
	 */
	@Override
	public String importProductService(Product product) {
		System.out.println("importProductService calling");
		return "OK";
	}

}
