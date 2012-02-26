package com.legendshop.business.service;

import java.util.List;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Nsort;
import com.legendshop.model.entity.Sort;

public interface NsortService {

	/**
	 * List.
	 * 
	 * @param userName
	 *            the user name
	 * @return the list
	 */
	public abstract List<Nsort> getNsortList(String userName);

	//parentNsortId is not null ：3级分类
	/**
	 * List by sort.
	 * 
	 * @param sortId
	 *            the sort id
	 * @return the list
	 */
	public abstract List<Nsort> getNSortBySort(Long sortId);

	/**
	 * Checks for child nsort.
	 * 
	 * @param id
	 *            the id
	 * @return true, if successful
	 */
	public abstract boolean isHasChildNsort(Long id);

	/**
	 * Checks for child nsort brand.
	 * 
	 * @param id
	 *            the id
	 * @return true, if successful
	 */
	public abstract boolean isHasChildNsortBrand(Long id);

	/**
	 * Load.
	 * 
	 * @param id
	 *            the id
	 * @return the nsort
	 */
	public abstract Nsort getNsort(Long id);

	/**
	 * Load sort.
	 * 
	 * @param id
	 *            the id
	 * @return the sort
	 */
	public abstract Sort getSort(Long id);

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
	 * @param nsort
	 *            the nsort
	 * @return the long
	 */
	public abstract Long save(Nsort nsort);

	/**
	 * Update.
	 * 
	 * @param nsort
	 *            the nsort
	 */
	public abstract void update(Nsort nsort);

	/**
	 * Gets the data by criteria query.
	 * 
	 * @param cq
	 *            the cq
	 * @return the data by criteria query
	 */
	public abstract PageSupport getNsortList(CriteriaQuery cq);

	/**
	 * Gets the data by criteria query.
	 * 
	 * @param hql
	 *            the hql
	 * @return the data by criteria query
	 */
	public abstract PageSupport getNsortList(HqlQuery hql);

	/**
	 * Query nsort.
	 * 
	 * @param id
	 *            the id
	 * @return the nsort
	 */
	public abstract Nsort getNsortById(Long id);

	/**
	 * Query nsort by sort id.
	 * 
	 * @param sortId
	 *            the sort id
	 * @return the list
	 */
	public abstract List<Nsort> getNsortBySortId(Long sortId);

}