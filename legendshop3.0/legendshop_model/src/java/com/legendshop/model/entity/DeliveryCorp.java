package com.legendshop.model.entity;

import java.sql.Timestamp;

/**
 * DeliveryCorp entity. @author MyEclipse Persistence Tools
 */

public class DeliveryCorp implements java.io.Serializable {

	// Fields

	private Integer dvyId;
	private Integer userId;
	private Integer userName;
	private String name;
	private String url;
	private Timestamp createTime;
	private Timestamp modifyTime;

	// Constructors

	/** default constructor */
	public DeliveryCorp() {
	}

	/** minimal constructor */
	public DeliveryCorp(Integer userId, Integer userName, Timestamp createTime, Timestamp modifyTime) {
		this.userId = userId;
		this.userName = userName;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
	}

	/** full constructor */
	public DeliveryCorp(Integer userId, Integer userName, String name, String url, Timestamp createTime,
			Timestamp modifyTime) {
		this.userId = userId;
		this.userName = userName;
		this.name = name;
		this.url = url;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
	}

	// Property accessors

	public Integer getDvyId() {
		return this.dvyId;
	}

	public void setDvyId(Integer dvyId) {
		this.dvyId = dvyId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserName() {
		return this.userName;
	}

	public void setUserName(Integer userName) {
		this.userName = userName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

}