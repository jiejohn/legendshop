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
 * 系统事件
 */

public class Event implements BaseEntity {

	// Fields

	/** The event id. */
	private Long eventId;
	
	/** The user id. */
	private String userId;
	
	/** The user name. */
	private String userName;
	
	/** The type. */
	private String type;
	
	/** The operation. */
	private String operation;
	
	/** The relate data. */
	private String relateData;
	
	/** The create time. */
	private Date createTime;
	
	/**
	 * Gets the event id.
	 * 
	 * @return the event id
	 */
	public Long getEventId() {
		return eventId;
	}
	
	/**
	 * Sets the event id.
	 * 
	 * @param eventId
	 *            the new event id
	 */
	public void setEventId(Long eventId) {
		this.eventId = eventId;
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
	 * Gets the operation.
	 * 
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}
	
	/**
	 * Sets the operation.
	 * 
	 * @param operation
	 *            the new operation
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	/**
	 * Gets the relate data.
	 * 
	 * @return the relate data
	 */
	public String getRelateData() {
		return relateData;
	}
	
	/**
	 * Sets the relate data.
	 * 
	 * @param relateData
	 *            the new relate data
	 */
	public void setRelateData(String relateData) {
		this.relateData = relateData;
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

	public Serializable getId() {
		return eventId;
	}

}