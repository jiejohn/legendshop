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
import com.legendshop.model.entity.Nsort;

/**
 * The Interface NsortDao.
 */
public interface NsortDao extends BaseDao{

	/**
	 * Query nsort.
	 * 
	 * @param nsortId
	 *            the nsort id
	 * @return the nsort
	 */
	public abstract Nsort getNsort(final Long nsortId);

	// 得到其他的相关小类
	/**
	 * Query nsort list.
	 * 
	 * @param sortId
	 *            the sort id
	 * @param nsortId
	 *            the nsort id
	 * @return the list
	 */
	public abstract List<Nsort> getNsortList(final Long sortId, final Long nsortId);

	// 得到其他的相关小类
	/**
	 * Query nsort list.
	 * 
	 * @param sortId
	 *            the sort id
	 * @return the list
	 */
	public abstract List<Nsort> getNsortList(final Long sortId);

	// 得到相关二级小类
	/**
	 * Query othor nsort.
	 * 
	 * @param list
	 *            the list
	 * @return the list
	 */
	public abstract List<Nsort> getOthorNsort(List<Nsort> list);

	// 得到相关三级小类
	/**
	 * Query othor sub nsort.
	 * 
	 * @param InsortId
	 *            the insort id
	 * @param list
	 *            the list
	 * @return the list
	 */
	public abstract List<Nsort> getOthorSubNsort(Long InsortId, List<Nsort> list);

	/**
	 * Query nsort by sort id.
	 * 
	 * @param sortId
	 *            the sort id
	 * @return the list
	 */
	public abstract List<Nsort> getNsortBySortId(final Long sortId);

}