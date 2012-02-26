package com.legendshop.business.service;

import java.util.List;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Product;
import com.legendshop.model.entity.ProductComment;

public interface ProductCommentService {

	/**
	 * List.
	 * 
	 * @param userName
	 *            the user name
	 * @return the list
	 */
	public abstract List<ProductComment> getProductCommentList(String userName);

	/**
	 * Load.
	 * 
	 * @param id
	 *            the id
	 * @return the product comment
	 */
	public abstract ProductComment getProductCommentById(Long id);

	/**
	 * Load prod.
	 * 
	 * @param id
	 *            the id
	 * @return the product
	 */
	public abstract Product getProduct(Long id);

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
	 * @param productComment
	 *            the product comment
	 * @return the long
	 */
	public abstract Long save(ProductComment productComment);

	/**
	 * Update.
	 * 
	 * @param productComment
	 *            the product comment
	 */
	public abstract void update(ProductComment productComment);

	/**
	 * Gets the data by criteria query.
	 * 
	 * @param cq
	 *            the cq
	 * @return the data by criteria query
	 */
	public abstract PageSupport getProductCommentList(CriteriaQuery cq);

	/**
	 * Gets the data by criteria query.
	 * 
	 * @param hql
	 *            the hql
	 * @return the data by criteria query
	 */
	public abstract PageSupport getProductCommentList(HqlQuery hql);

}