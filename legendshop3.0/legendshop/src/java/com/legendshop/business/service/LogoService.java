package com.legendshop.business.service;

import java.util.List;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Logo;

public interface LogoService {

	/**
	 * List.
	 * 
	 * @param userName
	 *            the user name
	 * @return the list
	 */
	public abstract List<Logo> getLogoList(String userName);

	/**
	 * Load.
	 * 
	 * @param id
	 *            the id
	 * @return the logo
	 */
	public abstract Logo getLogoById(Long id);

	/**
	 * Load.
	 * 
	 * @param id
	 *            the id
	 * @param userName
	 *            the user name
	 * @return the logo
	 */
	public abstract Logo getLogoByIdAndName(Long id, String userName);

	/**
	 * Delete.
	 * 
	 * @param id
	 *            the id
	 */
	public abstract void delete(Logo logo);

	/**
	 * Save.
	 * 
	 * @param logo
	 *            the logo
	 * @return the long
	 */
	public abstract Long save(Logo logo);

	/**
	 * Update.
	 * 
	 * @param logo
	 *            the logo
	 */
	public abstract void update(Logo logo);

	/**
	 * Gets the data by criteria query.
	 * 
	 * @param cq
	 *            the cq
	 * @return the data by criteria query
	 */
	public abstract PageSupport getLogoList(CriteriaQuery cq);

}