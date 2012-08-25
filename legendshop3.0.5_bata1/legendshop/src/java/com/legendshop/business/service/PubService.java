package com.legendshop.business.service;

import java.util.List;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Pub;

public interface PubService {

	/**
	 * List.
	 * 
	 * @param userName
	 *            the user name
	 * @return the list
	 */
	public abstract List<Pub> getPubList(String userName);

	/**
	 * Load.
	 * 
	 * @param id
	 *            the id
	 * @return the pub
	 */
	public abstract Pub getPubById(Long id);

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
	 * @param pub
	 *            the pub
	 * @param userName
	 *            the user name
	 * @param viewAllDataFunction
	 *            the view all data function
	 * @return the long
	 */
	public abstract Long save(Pub pub, String userName, boolean viewAllDataFunction);

	/**
	 * Update.
	 * 
	 * @param pub
	 *            the pub
	 */
	public abstract void update(Pub pub);

	/**
	 * Gets the data by criteria query.
	 * 
	 * @param cq
	 *            the cq
	 * @return the data by criteria query
	 */
	public abstract PageSupport getPubList(CriteriaQuery cq);

}