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
import com.legendshop.model.entity.ProductDetail;

/**
 * Basket服务.
 */
public class BasketServiceImpl implements BasketService {
	
	/** The basket dao. */
	private BasketDao basketDao;
	
	/** The product dao. */
	private ProductDao productDao;
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.BasketService#addtocart(java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer)
	 */
	@Override
	public void saveToCart(Long prodId,String addtoCart,String shopName,String prodattr,String userName,Integer count){
		ProductDetail prod = productDao.getProdDetail(prodId);
		if (prod != null) {
			if ("buy".equals(addtoCart)) {
				String attribute = prodattr == null ? "" : prodattr;
				Basket basket = basketDao.getBasketByIdName(prodId, userName, shopName, attribute);
				if (basket == null) {
					basketDao.saveToCart(prod.getProdId(), prod.getPic(), userName, shopName,count,
							attribute, prod.getName(), prod.getCash(), prod.getCarriage());
				}
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.BasketService#addtocart(java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.String, java.lang.String, java.lang.Double, java.lang.Double)
	 */
	@Override
	public void saveToCart(Long prodId, String pic, String userName, String shopName, Integer count, String attribute,
			String prodName, Double cash, Double carriage) {
		basketDao.saveToCart(prodId, pic, userName, shopName, count, attribute, prodName, cash, carriage);
		
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



}
