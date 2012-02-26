package com.legendshop.business.service;

import java.util.List;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Hotsearch;

public interface HotsearchService {

	/**
	 * List.
	 * 
	 * @param userName
	 *            the user name
	 * @return the list
	 */
	public abstract List<Hotsearch> getHotsearch(String userName);

	/**
	 * Load.
	 * 
	 * @param id
	 *            the id
	 * @return the hotsearch
	 */
	public abstract Hotsearch getHotsearchById(Long id);

	/**
	 * Load.
	 * 
	 * @param id
	 *            the id
	 * @param userName
	 *            the user name
	 * @return the hotsearch
	 */
	public abstract Hotsearch getHotsearchByIdAndName(Integer id, String userName);

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
	 * @param hotsearch
	 *            the hotsearch
	 * @param userName
	 *            the user name
	 * @param viewAllDataFunction
	 *            the view all data function
	 * @return the long
	 */
	public abstract Long save(Hotsearch hotsearch, String userName, boolean viewAllDataFunction);

	/**
	 * Update.
	 * 
	 * @param hotsearch
	 *            the hotsearch
	 */
	public abstract void update(Hotsearch hotsearch);

	/**
	 * Gets the data by criteria query.
	 * 
	 * @param cq
	 *            the cq
	 * @return the data by criteria query
	 */
	public abstract PageSupport getDataByCriteriaQuery(CriteriaQuery cq);

	/**
	 * Gets the search.
	 * 
	 * @param userName
	 *            the user name
	 * @param sortId
	 *            the sort id
	 * @return the search
	 */
	public abstract List<Hotsearch> getSearch(String userName, Long sortId);

}