/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service;

import java.util.List;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Brand;

public interface BrandService {

	/**
	 * List.
	 * 
	 * @param userName
	 *            the user name
	 * @return the list
	 */
	public abstract List<Brand> getBrand(String userName);

	/**
	 * Load.
	 * 
	 * @param id
	 *            the id
	 * @return the brand
	 */
	public abstract Brand getBrand(Long id);

	/**
	 * Delete.
	 * 
	 * @param id
	 *            the id
	 */
	public abstract void delete(Long id);

	/**
	 * Save.
	 * 
	 * @param brand
	 *            the brand
	 * @return the long
	 */
	public abstract Long save(Brand brand);

	/**
	 * Update.
	 * 
	 * @param brand
	 *            the brand
	 */
	public abstract void update(Brand brand);

	/**
	 * Gets the data by criteria query.
	 * 
	 * @param cq
	 *            the cq
	 * @return the data by criteria query
	 */
	public abstract PageSupport getDataByCriteriaQuery(CriteriaQuery cq);
	
	/**
	 * Save brand item.
	 * 
	 * @param idList
	 *            the id list
	 * @param nsortId
	 *            the nsort id
	 * @param userName
	 *            the user name
	 * @return the string
	 */
	public abstract String saveBrandItem(List<String> idList, Long nsortId, String userName);

}