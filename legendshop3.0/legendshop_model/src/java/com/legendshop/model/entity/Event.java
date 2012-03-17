package com.legendshop.model.entity;

import java.sql.Timestamp;

/**
 * Event entity. @author MyEclipse Persistence Tools
 */

public class Event implements java.io.Serializable {

	// Fields

	private Integer eventId;
	private Integer userId;
	private String userName;
	private String type;
	private String operation;
	private String relateData;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public Event() {
	}

	/** minimal constructor */
	public Event(Integer userId, String userName, Timestamp createTime) {
		this.userId = userId;
		this.userName = userName;
		this.createTime = createTime;
	}

	/** full constructor */
	public Event(Integer userId, String userName, String type, String operation, String relateData, Timestamp createTime) {
		this.userId = userId;
		this.userName = userName;
		this.type = type;
		this.operation = operation;
		this.relateData = relateData;
		this.createTime = createTime;
	}

	// Property accessors

	public Integer getEventId() {
		return this.eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOperation() {
		return this.operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getRelateData() {
		return this.relateData;
	}

	public void setRelateData(String relateData) {
		this.relateData = relateData;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}