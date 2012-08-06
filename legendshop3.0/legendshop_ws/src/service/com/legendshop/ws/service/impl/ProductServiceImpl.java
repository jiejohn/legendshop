/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.ws.service.impl;

import java.util.Date;

import javax.jws.WebService;

import com.legendshop.core.constant.ProductStatusEnum;
import com.legendshop.ws.dao.ProductDao;
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
	
	private ProductDao productDao;
	/* (non-Javadoc)
	 * @see com.legendshop.ws.service.ProductService#importProductService(com.legendshop.ws.model.Product)
	 */
	@Override
	public String importProductService(Product product) {
		System.out.println("importProductService calling");
		product.setUserId("f808081263df458012646d2bf3f0002");
		product.setUserName("360buy");
		Date date = new Date();
		product.setStatus(ProductStatusEnum.PROD_ONLINE.value());
		product.setRecDate(date);
		product.setModifyDate(date);
		product.setViews(0);
		product.setBuys(0);
		product.setProdType("P");
		if (product.getStocks() != null) {
			product.setActualStocks(product.getStocks());
		}
		return productDao.importProductService(product);
	}
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

}
