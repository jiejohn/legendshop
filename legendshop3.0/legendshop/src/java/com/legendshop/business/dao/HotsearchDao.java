package com.legendshop.business.dao;

import java.util.List;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.model.entity.Hotsearch;

public interface HotsearchDao extends BaseDao{

	/**
	 * Gets the search.
	 * 
	 * @param shopName
	 *            the shop name
	 * @return the search
	 */
	public abstract List<Hotsearch> getSearch(String shopName);

	/**
	 * Gets the search.
	 * 
	 * @param userName
	 *            the user name
	 * @param isortId
	 *            the isort id
	 * @return the search
	 */
	public abstract List<Hotsearch> getSearch(final String userName, final Long isortId);

}