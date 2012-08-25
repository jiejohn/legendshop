/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Myleague;

/**
 * The Interface MyleagueDao.
 */
public interface MyleagueDao extends BaseDao{

	/**
	 * Gets the league.
	 * 
	 * @param shopName
	 *            the shop name
	 * @param curPageNO
	 *            the cur page no
	 * @return the league
	 */
	public PageSupport getLeague(String shopName, String curPageNO);

	/**
	 * Delete myleague by id.
	 * 
	 * @param id
	 *            the id
	 */
	public void deleteMyleagueById(Long id);

	/**
	 * Update myleague.
	 * 
	 * @param myleague
	 *            the myleague
	 */
	public void updateMyleague(Myleague myleague);

}