/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.core.service;

import java.util.List;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Sort;

/**
 * The Interface SortService.
 */
public interface SortService {

	/**
	 * Query sort list.
	 * 
	 * @param userName
	 *            the user name
	 * @return the list
	 */
	public abstract List<Sort> getSortList(String userName);

	/**
	 * Query sort.
	 * 
	 * @param id
	 *            the id
	 * @return the sort
	 */
	public abstract Sort getSortById(Long id);

	/**
	 * Delete sort.
	 * 
	 * @param sortId
	 *            the sort id
	 */
	public abstract void deleteSort(Long sortId);

	/**
	 * Save.
	 * 
	 * @param sort
	 *            the sort
	 * @return the long
	 */
	public abstract Long save(Sort sort);

	/**
	 * Update sort.
	 * 
	 * @param sort
	 *            the sort
	 */
	public abstract void updateSort(Sort sort);

	/**
	 * Gets the data by criteria query.
	 * 
	 * @param cq
	 *            the cq
	 * @return the data by criteria query
	 */
	public abstract PageSupport getSortList(CriteriaQuery cq);

	/**
	 * Gets the sort.
	 * 
	 * @param shopName
	 *            the shop name
	 * @param loadAll
	 *            是否加载 nsort 1、由部分加载改为全部加载，在jsp中再做限制 2、只通过top.jsp获得sort，避免多处查找
	 * @return the sort
	 */
	public abstract List<Sort> getSort(String shopName, boolean loadAll);

	/**
	 * Delete.
	 * 
	 * @param sort
	 *            the sort
	 */
	public abstract void delete(Sort sort);

	/**
	 * Gets the sort.
	 * 
	 * @param name
	 *            the name
	 * @param sortType
	 *            the sort type
	 * @param loadAll
	 *            the load all
	 * @return the sort
	 */
	public abstract List<Sort> getSort(String name, String sortType, boolean loadAll);
	
	public abstract List<Sort> getSort(String name, String sortType,Integer headerMenu,Integer navigationMenu, boolean loadAll);

}