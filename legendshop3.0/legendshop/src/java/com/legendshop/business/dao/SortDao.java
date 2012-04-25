/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao;

import java.util.List;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Sort;

/**
 * The Interface SortDao.
 */
public interface SortDao extends BaseDao{

	/**
	 * Gets the sort.
	 * 
	 * @param shopName
	 *            the shop name
	 * @param loadAll
	 *            是否加载 nsort 1、由部分加载改为全部加载，在jsp中再做限制 2、只通过top.jsp获得sort，避免多处查找
	 * @return the sort
	 */
	public abstract List<Sort> getSort(final String shopName, final Boolean loadAll);
	
	/**
	 * Gets the sort.
	 * 
	 * @param shopName
	 *            the shop name
	 * @param sortType
	 *            the sort type
	 * @param loadAll
	 *            the load all
	 * @return the sort
	 */
	public List<Sort> getSort(final String shopName,String sortType, final Boolean loadAll);

	/**
	 * Gets the sort.
	 * 
	 * @param sortId
	 *            the sort id
	 * @return the sort
	 */
	public abstract Sort getSort(Long sortId);

	/**
	 * Query sort.
	 * 
	 * @param cq
	 *            the cq
	 * @return the page support
	 */
	public abstract PageSupport getSortByCriteria(CriteriaQuery cq);

	/**
	 * Query sort list.
	 * 
	 * @param userName
	 *            the user name
	 * @return the list
	 */
	public abstract List<Sort> getSortList(String userName);


	/**
	 * Delete sort by id.
	 * 
	 * @param id
	 *            the id
	 */
	public abstract void deleteSortById(Long id);

	/**
	 * Update sort.
	 * 
	 * @param sort
	 *            the sort
	 */
	public abstract void updateSort(Sort sort);

	/**
	 * Save sort.
	 * 
	 * @param sort
	 *            the sort
	 * @return the long
	 */
	public abstract Long saveSort(Sort sort);

	/**
	 * Query product by sort id.
	 * 
	 * @param sortId
	 *            the sort id
	 * @return the list
	 */
	public abstract List getProductBySortId(Long sortId);

	/**
	 * Query nsort by sort id.
	 * 
	 * @param sortId
	 *            the sort id
	 * @return the list
	 */
	public abstract List getNsortBySortId(Long sortId);

	/**
	 * Delete sort.
	 * 
	 * @param sort
	 *            the sort
	 */
	public abstract void deleteSort(Sort sort);

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

}