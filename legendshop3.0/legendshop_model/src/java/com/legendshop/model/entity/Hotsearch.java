/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.model.entity;

import java.util.Date;

/**
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public class Hotsearch implements java.io.Serializable {

	// Fields

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1349792154724940069L;

	/** The id. */
	private Long id;

	/** The title. */
	private String title;
	
	/** The sort. */
	private Long sort;

	/** The msg. */
	private String msg;

	/** The date. */
	private Date date;

	/** The user id. */
	private String userId;

	/** The user name. */
	private String userName;

	// Constructors

	/**
	 * default constructor.
	 */
	public Hotsearch() {
	}

	/**
	 * minimal constructor.
	 * 
	 * @param id
	 *            the id
	 * @param title
	 *            the title
	 * @param msg
	 *            the msg
	 * @param date
	 *            the date
	 */
	public Hotsearch(Long id, String title, String msg, Date date) {
		this.id = id;
		this.title = title;
		this.msg = msg;
		this.date = date;
	}

	/**
	 * full constructor.
	 * 
	 * @param id
	 *            the id
	 * @param title
	 *            the title
	 * @param msg
	 *            the msg
	 * @param date
	 *            the date
	 * @param userId
	 *            the user id
	 * @param userName
	 *            the user name
	 */
	public Hotsearch(Long id, String title, String msg, Date date,
			String userId, String userName) {
		this.id = id;
		this.title = title;
		this.msg = msg;
		this.date = date;
		this.userId = userId;
		this.userName = userName;
	}


	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Sets the title.
	 * 
	 * @param title
	 *            the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the msg.
	 * 
	 * @return the msg
	 */
	public String getMsg() {
		return this.msg;
	}

	/**
	 * Sets the msg.
	 * 
	 * @param msg
	 *            the new msg
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * Gets the date.
	 * 
	 * @return the date
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * Sets the date.
	 * 
	 * @param date
	 *            the new date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Gets the user id.
	 * 
	 * @return the user id
	 */
	public String getUserId() {
		return this.userId;
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
		return this.userName;
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

	/**
	 * Gets the sort.
	 * 
	 * @return the sort
	 */
	public Long getSort() {
		return sort;
	}

	/**
	 * Sets the sort.
	 * 
	 * @param sort
	 *            the new sort
	 */
	public void setSort(Long sort) {
		this.sort = sort;
	}

}