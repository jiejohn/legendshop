/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.legendshop.business.dao.BasketDao;
import com.legendshop.business.dao.ProductDao;
import com.legendshop.business.service.BasketService;
import com.legendshop.model.entity.Basket;

/**
 * Basket服务.
 */
public class BasketServiceImpl implements BasketService {
	
	/** The basket dao. */
	private BasketDao basketDao;
	
	/** The product dao. */
	private ProductDao productDao;

	
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.BasketService#saveToCart(java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer)
	 */
	@Override
	public void saveToCart(Long prodId,String shopName,String prodattr,String userName,Integer count){
				String attribute = prodattr == null ? "" : prodattr;
				Basket basket = basketDao.getBasketByIdName(prodId, userName, shopName, attribute);
				if (basket == null) {
					basketDao.saveToCart(userName, prodId, count, attribute);
				}
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

	/**
	 * Sets the basket dao.
	 * 
	 * @param basketDao
	 *            the new basket dao
	 */
	@Required
	public void setBasketDao(BasketDao basketDao) {
		this.basketDao = basketDao;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.BasketService#deleteBasketByUserName(java.lang.String)
	 */
	@Override
	public void deleteBasketByUserName(String userName) {
		basketDao.deleteBasketByUserName(userName);
		
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.BasketService#deleteBasketById(java.lang.Long)
	 */
	@Override
	public void deleteBasketById(Long id) {
		basketDao.deleteBasketById(id);
		
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.BasketService#getBasketByuserName(java.lang.String)
	 */
	@Override
	public List<Basket> getBasketByuserName(String userName) {
		return basketDao.getBasketByuserName(userName);
	}

	@Override
	public Long getTotalBasketByuserName(String userName) {
		return basketDao.getTotalBasketByuserName(userName);
	}



}
