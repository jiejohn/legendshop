/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import org.springframework.beans.factory.annotation.Required;

import com.legendshop.business.dao.ProductDao;
import com.legendshop.business.dao.ShopDetailDao;
import com.legendshop.business.search.facade.ProductSearchFacade;
import com.legendshop.business.service.ProductService;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Product;

/**
 * 产品服务.
 */
public class ProductServiceImpl implements ProductService {

	/** 产品Dao. */
	private ProductDao productDao;

	/** 商城Dao. */
	private ShopDetailDao shopDetailDao;

	/** 产品LunceFacade. */
	private ProductSearchFacade productSearchFacade;
	
	/**
	 * Sets the product search facade.
	 * 
	 * @param productSearchFacade
	 *            the new product search facade
	 */
	@Required
	public void setProductSearchFacade(ProductSearchFacade productSearchFacade) {
		this.productSearchFacade = productSearchFacade;
	}

	/**
	 * Sets the shop detail dao.
	 * 
	 * @param shopDetailDao
	 *            the new shop detail dao
	 */
	@Required
	public void setShopDetailDao(ShopDetailDao shopDetailDao) {
		this.shopDetailDao = shopDetailDao;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.ProductService#findProduct(com.legendshop.core.dao.support.HqlQuery)
	 */
	@Override
	public PageSupport getProductList(HqlQuery hql) {
		return productDao.find(hql);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.ProductService#findProduct(com.legendshop.core.dao.support.CriteriaQuery)
	 */
	@Override
	public PageSupport getProductList(CriteriaQuery cq) {
		return productDao.find(cq);
	}

	/**
	 * Sets the product dao.
	 * 
	 * @param productDao
	 *            the new product dao
	 */
	@Required
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.ProductService#queryProduct(java.lang.Long)
	 */
	@Override
	public Product getProductById(Long prodId) {
		return productDao.getProduct(prodId);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.ProductService#updateProduct(com.legendshop.model.entity.Product)
	 */
	@Override
	public void updateProduct(Product product) {
		productDao.updateProduct(product);
		shopDetailDao.updateShopDetailWhenProductChange(product);
		productSearchFacade.update(product);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.ProductService#saveProduct(com.legendshop.model.entity.Product)
	 */
	@Override
	public Long saveProduct(Product product) {
		Long prodId = productDao.saveProduct(product);
		product.setProdId(prodId);
		shopDetailDao.updateShopDetailWhenProductChange(product);
		productSearchFacade.create(product);
		return prodId;
	}

}
