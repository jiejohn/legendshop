package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;

public interface BusinessDao extends BaseDao{

	/**
	 * Gets the order list.
	 * 
	 * @param cq
	 *            the cq
	 * @return the order list
	 */
	public abstract PageSupport getOrderList(CriteriaQuery cq);

	/**
	 * Process order.
	 * 
	 * @param cq
	 *            the cq
	 * @return the page support
	 */
	public abstract PageSupport getOrder(CriteriaQuery cq);

	/**
	 * Gets the total processing order.
	 * 
	 * @param userName
	 *            the user name
	 * @return the total processing order
	 */
	public abstract Long getTotalProcessingOrder(String userName);

	/**
	 * Answre word.
	 * 
	 * @param id
	 *            the id
	 * @param answer
	 *            the answer
	 * @param toUserName
	 *            the to user name
	 * @return true, if successful
	 */
	public abstract boolean updateUserComment(Long id, String answer, String toUserName);

	// 判断用户是否已经订购产品，条件:订单状态为Y
	/**
	 * Checks if is user order product.
	 * 
	 * @param prodId
	 *            the prod id
	 * @param userName
	 *            the user name
	 * @return true, if is user order product
	 */
	public abstract boolean isUserOrderProduct(Long prodId, String userName);

}