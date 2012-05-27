/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Required;

import com.legendshop.business.dao.ProductDao;
import com.legendshop.business.dao.ShopDetailDao;
import com.legendshop.business.search.facade.ProductSearchFacade;
import com.legendshop.core.constant.ProductStatusEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Product;
import com.legendshop.spi.service.ProductService;

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
		if(prodId == null){
			return null;
		}
		return productDao.getProduct(prodId);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.ProductService#updateProduct(com.legendshop.model.entity.Product)
	 */
	@Override
	public void updateProduct(Product product, Product origin) {
		// update
		Date date = new Date();
		origin.setModifyDate(date);
		if (product.getStocks() != null && !product.getStocks().equals(origin.getStocks())) {
			origin.setActualStocks(product.getStocks());
		}
		origin.setName(product.getName());
		origin.setSortId(product.getSortId());
		origin.setSubNsortId(product.getSubNsortId());
		origin.setNsortId(product.getNsortId());
		origin.setModelId(product.getModelId());
		origin.setKeyWord(product.getKeyWord());
		origin.setPrice(product.getPrice());
		origin.setCash(product.getCash());
		origin.setCarriage(product.getCarriage());
		origin.setStocks(product.getStocks());
		origin.setBrandId(product.getBrandId());
		origin.setBrief(product.getBrief());
		origin.setContent(product.getContent());
		origin.setProdType(product.getProdType());
		origin.setStartDate(product.getStartDate());
		origin.setEndDate(product.getEndDate());
		origin.setCommend(product.getCommend());
		productDao.updateProduct(origin);
		shopDetailDao.updateShopDetailWhenProductChange(origin);
		productSearchFacade.update(origin);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.ProductService#saveProduct(com.legendshop.model.entity.Product)
	 */
	@Override
	public Long saveProduct(Product product,String prodType) {
		Date date = new Date();
		product.setStatus(ProductStatusEnum.PROD_ONLINE.value());
		product.setRecDate(date);
		product.setModifyDate(date);
		product.setViews(0);
		product.setBuys(0);
		product.setProdType(prodType);
		if (product.getStocks() != null) {
			product.setActualStocks(product.getStocks());
		}
		Long prodId = productDao.saveProduct(product);
		product.setProdId(prodId);
		shopDetailDao.updateShopDetailWhenProductChange(product);
		productSearchFacade.create(product);
		return prodId;
	}

}
