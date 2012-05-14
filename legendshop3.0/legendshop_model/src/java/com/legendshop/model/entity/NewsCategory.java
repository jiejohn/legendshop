/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.model.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public class NewsCategory implements BaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8019865349891607453L;

	/** The news category id. */
	private Long newsCategoryId;

	/** The news category name. */
	private String newsCategoryName;

	/** The status. */
	private Short status;

	/** The news category date. */
	private Date newsCategoryDate;

	/** The user id. */
	private String userId;

	/** The user name. */
	private String userName;

	/**
	 * Instantiates a new news category.
	 */
	public NewsCategory() {
	}

	/**
	 * Gets the news category id.
	 * 
	 * @return the news category id
	 */
	public Long getNewsCategoryId() {
		return newsCategoryId;
	}

	/**
	 * Sets the news category id.
	 * 
	 * @param newsCategoryId
	 *            the new news category id
	 */
	public void setNewsCategoryId(Long newsCategoryId) {
		this.newsCategoryId = newsCategoryId;
	}

	/**
	 * Gets the news category name.
	 * 
	 * @return the news category name
	 */
	public String getNewsCategoryName() {
		return newsCategoryName;
	}

	/**
	 * Sets the news category name.
	 * 
	 * @param newsCategoryName
	 *            the new news category name
	 */
	public void setNewsCategoryName(String newsCategoryName) {
		this.newsCategoryName = newsCategoryName;
	}

	/**
	 * Gets the status.
	 * 
	 * @return the status
	 */
	public Short getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 * 
	 * @param status
	 *            the new status
	 */
	public void setStatus(Short status) {
		this.status = status;
	}

	/**
	 * Gets the news category date.
	 * 
	 * @return the news category date
	 */
	public Date getNewsCategoryDate() {
		return newsCategoryDate;
	}

	/**
	 * Sets the news category date.
	 * 
	 * @param newsCategoryDate
	 *            the new news category date
	 */
	public void setNewsCategoryDate(Date newsCategoryDate) {
		this.newsCategoryDate = newsCategoryDate;
	}

	/**
	 * Gets the user id.
	 * 
	 * @return the user id
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 * 
	 * @param userId
	 *            the new user id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Gets the user name.
	 * 
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the user name.
	 * 
	 * @param userName
	 *            the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Serializable getId() {
		return newsCategoryId;
	}

}
