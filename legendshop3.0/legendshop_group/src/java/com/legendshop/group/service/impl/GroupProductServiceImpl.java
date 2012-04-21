/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.group.service.impl;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.group.dao.GroupProductDao;
import com.legendshop.group.service.GroupProductService;
import com.legendshop.model.entity.group.GroupProduct;

/**
 * The Class GroupProductServiceImpl.
 */
public class GroupProductServiceImpl implements GroupProductService {
	
	/** The group product dao. */
	private  GroupProductDao groupProductDao;

	public PageSupport getGroupProductList(HqlQuery hql) {
		return groupProductDao.find(hql);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.group.service.GroupProductService#getGroupProductList(com.legendshop.core.dao.support.CriteriaQuery)
	 */
	
	public PageSupport getGroupProductList(CriteriaQuery cq) {
		// TODO Auto-generated method stub
		return groupProductDao.find(cq);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.group.service.GroupProductService#getGroupGroupProductById(java.lang.Long)
	 */
	
	public GroupProduct getGroupGroupProductById(Long groupProdId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.group.service.GroupProductService#updateGroupProduct(com.legendshop.model.group.GroupProduct)
	 */
	
	public void updateGroupProduct(GroupProduct groupProduct) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.legendshop.group.service.GroupProductService#saveGroupProduct(com.legendshop.model.group.GroupProduct)
	 */
	
	public Long saveGroupProduct(GroupProduct groupProduct) {
		// TODO Auto-generated method stub
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



}
