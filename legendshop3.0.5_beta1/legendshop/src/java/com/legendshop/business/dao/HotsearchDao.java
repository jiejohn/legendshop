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
import com.legendshop.model.entity.Hotsearch;

/**
 * The Interface HotsearchDao.
 */
public interface HotsearchDao extends BaseDao{


	/**
	 * Gets the search.
	 * 
	 * @param shopName
	 *            the shop name
	 * @return the search
	 */
	public abstract List<Hotsearch> getHotsearch(String shopName);

	/**
	 * Gets the search.
	 * 
	 * @param userName
	 *            the user name
	 * @param isortId
	 *            the isort id
	 * @return the search
	 */
	public abstract List<Hotsearch> getHotsearch(final String userName, final Long isortId);

	/**
	 * Delete hotsearch by id.
	 * 
	 * @param id
	 *            the id
	 */
	public abstract void deleteHotsearchById(Long id);

	/**
	 * Update hotsearch.
	 * 
	 * @param hotsearch
	 *            the hotsearch
	 */
	public abstract void updateHotsearch(Hotsearch hotsearch);
	
	
	/**
	 * Gets the hotsearch by id and name.
	 * 
	 * @param id
	 *            the id
	 * @param userName
	 *            the user name
	 * @return the hotsearch by id and name
	 */
	public Hotsearch getHotsearchByIdAndName(Integer id, String userName);

}