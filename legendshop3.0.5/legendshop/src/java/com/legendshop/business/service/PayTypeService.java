package com.legendshop.business.service;

import java.util.List;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.PayType;

public interface PayTypeService {

	/**
	 * List.
	 * 
	 * @param userName
	 *            the user name
	 * @return the list
	 */
	public abstract List<PayType> getPayTypeList(String userName);

	/**
	 * Load.
	 * 
	 * @param id
	 *            the id
	 * @return the pay type
	 */
	public abstract PayType getPayTypeById(Long id);

	/**
	 * Load.
	 * 
	 * @param userName
	 *            the user name
	 * @param payTypeId
	 *            the pay type id
	 * @return the pay type
	 */
	public abstract PayType getPayTypeByIdAndName(String userName, Integer payTypeId);

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
	 * @param payType
	 *            the pay type
	 * @return the long
	 */
	public abstract Long save(PayType payType);

	/**
	 * Update.
	 * 
	 * @param payType
	 *            the pay type
	 */
	public abstract void update(PayType payType);

	/**
	 * Gets the data by criteria query.
	 * 
	 * @param cq
	 *            the cq
	 * @return the data by criteria query
	 */
	public abstract PageSupport getPayTypeList(CriteriaQuery cq);

}