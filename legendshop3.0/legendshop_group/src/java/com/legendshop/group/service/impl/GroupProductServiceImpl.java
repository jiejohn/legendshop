/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.group.service.impl;

import javax.servlet.http.HttpServletRequest;

import com.legendshop.core.constant.ProductTypeEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.service.GroupProductService;
import com.legendshop.core.service.ProductService;
import com.legendshop.group.dao.GroupProductDao;
import com.legendshop.model.entity.GroupProduct;
import com.legendshop.model.entity.Product;

/**
 * The Class GroupProductServiceImpl.
 */
public class GroupProductServiceImpl implements GroupProductService {
	
	/** The group product dao. */
	private  GroupProductDao groupProductDao;
	
	/** The product service. */
	private ProductService productService;

	/* (non-Javadoc)
	 * @see com.legendshop.core.service.GroupProductService#getGroupProductList(com.legendshop.core.dao.support.HqlQuery)
	 */
	public PageSupport getGroupProductList(HqlQuery hql) {
		return groupProductDao.find(hql);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.group.service.GroupProductService#getGroupProductList(com.legendshop.core.dao.support.CriteriaQuery)
	 */
	
	/**
	 * Gets the group product list.
	 * 
	 * @param cq
	 *            the cq
	 * @return the group product list
	 */
	public PageSupport getGroupProductList(CriteriaQuery cq) {
		// TODO Auto-generated method stub
		return groupProductDao.find(cq);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.group.service.GroupProductService#updateGroupProduct(com.legendshop.model.group.GroupProduct)
	 */
	
	public void updateGroupProduct(GroupProduct groupProduct) {
		groupProductDao.updateProduct(groupProduct);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.group.service.GroupProductService#saveGroupProduct(com.legendshop.model.group.GroupProduct)
	 */
	
	public String saveGroupProduct(HttpServletRequest request,GroupProduct groupProduct,Product product, String loginName) {
		return null;
	}
	


	/**
	 * Sets the group product dao.
	 * 
	 * @param groupProductDao
	 *            the new group product dao
	 */
	public void setGroupProductDao(GroupProductDao groupProductDao) {
		this.groupProductDao = groupProductDao;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.core.service.GroupProductService#getProductById(java.lang.Long)
	 */
	public Product getProductById(Long prodId) {
		return groupProductDao.getProductById(prodId);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.core.service.GroupProductService#updateProduct(com.legendshop.model.entity.Product, com.legendshop.model.entity.Product, com.legendshop.model.entity.GroupProduct)
	 */
	public void updateProduct(Product product, Product origin, GroupProduct entity) {
		// update product
		productService.updateProduct(product, origin);
		
		//update group product
		groupProductDao.updateProduct(entity);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.core.service.GroupProductService#saveProduct(com.legendshop.model.entity.Product, com.legendshop.model.entity.GroupProduct)
	 */
	public void saveProduct(Product product, GroupProduct entity) {
		Long prodId = productService.saveProduct(product,ProductTypeEnum.GROUP.value());
		entity.setProdId(prodId); 
		groupProductDao.saveProduct(entity);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.core.service.GroupProductService#deleteProduct(java.lang.Long)
	 */
	public void deleteProduct(Long prodId) {
		groupProductDao.deleteProduct(prodId);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.core.service.GroupProductService#getGroupProduct(java.lang.Long)
	 */
	public GroupProduct getGroupProduct(Long prodId) {
		return groupProductDao.getGroupProduct(prodId);
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}



}
