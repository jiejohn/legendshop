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
import com.legendshop.model.entity.UserComment;

/**
 * The Interface UserCommentDao.
 */
public interface UserCommentDao extends BaseDao{

	/**
	 * Query user comment.
	 * 
	 * @param cq
	 *            the cq
	 * @return the page support
	 */
	public abstract PageSupport getUserCommentByCriteria(CriteriaQuery cq);

	/**
	 * Query user comment.
	 * 
	 * @param id
	 *            the id
	 * @return the user comment
	 */
	public abstract UserComment getUserComment(Long id);

	/**
	 * User comment.
	 * 
	 * @param userComment
	 *            the user comment
	 */
	public abstract void deleteUserComment(UserComment userComment);

	/**
	 * Update user comment.
	 * 
	 * @param comment
	 *            the comment
	 */
	public abstract void updateUserComment(UserComment comment);

	/**
	 * Save or update user comment.
	 * 
	 * @param comment
	 *            the comment
	 */
	public abstract void saveOrUpdateUserComment(UserComment comment);

}