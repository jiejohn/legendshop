package com.legendshop.business.service;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Indexjpg;

public interface IndexJpgService {

	/**
	 * Query index jpg.
	 * 
	 * @param cq
	 *            the cq
	 * @return the page support
	 */
	public abstract PageSupport getIndexJpg(CriteriaQuery cq);

	/**
	 * Query index jpg.
	 * 
	 * @param id
	 *            the id
	 * @return the indexjpg
	 */
	public abstract Indexjpg getIndexJpgById(Long id);

	/**
	 * Delete index jpg.
	 * 
	 * @param indexjpg
	 *            the indexjpg
	 */
	public abstract void deleteIndexJpg(Indexjpg indexjpg);

	/**
	 * Gets the index jpg num.
	 * 
	 * @param name
	 *            the name
	 * @return the index jpg num
	 */
	public abstract Long getIndexJpgNum(String name);

	/**
	 * Update indexjpg.
	 * 
	 * @param origin
	 *            the origin
	 */
	public abstract void updateIndexjpg(Indexjpg origin);

	/**
	 * Save indexjpg.
	 * 
	 * @param indexjpg
	 *            the indexjpg
	 */
	public abstract void saveIndexjpg(Indexjpg indexjpg);

}