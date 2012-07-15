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
 * The Class Tag.
 */
public class Tag implements BaseEntity {

	/** The tag id. */
	private Long tagId;

	/** The name. */
	private String name;

	/** The sort id. */
	private Long sortId;

	/** The news category id. */
	private Long newsCategoryId;

	/** The type. */
	private String type;

	/** The status. */
	private Long status;

	/** The create time. */
	private Date createTime;

	/** The user id. */
	private String userId;

	/** The user name. */
	private String userName;

	/**
	 * Instantiates a new tag.
	 */
	public Tag() {
	}

	/**
	 * Gets the tag id.
	 * 
	 * @return the tag id
	 */
	public Long getTagId() {
		return tagId;
	}

	/**
	 * Sets the tag id.
	 * 
	 * @param tagId
	 *            the new tag id
	 */
	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the sort id.
	 * 
	 * @return the sort id
	 */
	public Long getSortId() {
		return sortId;
	}

	/**
	 * Sets the sort id.
	 * 
	 * @param sortId
	 *            the new sort id
	 */
	public void setSortId(Long sortId) {
		this.sortId = sortId;
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
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 * 
	 * @param type
	 *            the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the status.
	 * 
	 * @return the status
	 */
	public Long getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 * 
	 * @param status
	 *            the new status
	 */
	public void setStatus(Long status) {
		this.status = status;
	}

	/**
	 * Gets the creates the time.
	 * 
	 * @return the creates the time
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * Sets the creates the time.
	 * 
	 * @param createTime
	 *            the new creates the time
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	/* (non-Javadoc)
	 * @see com.legendshop.model.entity.BaseEntity#getId()
	 */
	public Serializable getId() {
		return tagId;
	}

}
