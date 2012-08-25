package com.legendshop.business.service;

import java.util.List;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.ExternalLink;

public interface ExternalLinkService {

	/**
	 * List.
	 * 
	 * @param userName
	 *            the user name
	 * @return the list
	 */
	public abstract List<ExternalLink> getExternalLink(String userName);

	/**
	 * Load.
	 * 
	 * @param id
	 *            the id
	 * @return the external link
	 */
	public abstract ExternalLink getExternalLinkById(Long id);

	/**
	 * Load.
	 * 
	 * @param id
	 *            the id
	 * @param userName
	 *            the user name
	 * @return the external link
	 */
	public abstract ExternalLink getExternalLinkList(Long id, String userName);

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
	 * @param externalLink
	 *            the external link
	 * @return the long
	 */
	public abstract Long save(ExternalLink externalLink);

	/**
	 * Update.
	 * 
	 * @param externalLink
	 *            the external link
	 */
	public abstract void update(ExternalLink externalLink);

	/**
	 * Gets the data by criteria query.
	 * 
	 * @param cq
	 *            the cq
	 * @return the data by criteria query
	 */
	public abstract PageSupport getDataByCriteriaQuery(CriteriaQuery cq);

}