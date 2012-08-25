/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Indexjpg;

/**
 * The Interface IndexJpgDao.
 */
public interface IndexJpgDao extends BaseDao{

	/**
	 * Query index jpg.
	 * 
	 * @param cq
	 *            the cq
	 * @return the page support
	 */
	public abstract PageSupport queryIndexJpg(CriteriaQuery cq);

	/**
	 * Query index jpg.
	 * 
	 * @param id
	 *            the id
	 * @return the indexjpg
	 */
	public abstract Indexjpg queryIndexJpg(Long id);

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