package com.legendshop.model.entity;

import java.sql.Timestamp;

/**
 * DeliveryType entity. @author MyEclipse Persistence Tools
 */

public class DeliveryType implements java.io.Serializable {

	// Fields

	private Integer dvyTypeId;
	private Integer userId;
	private Integer userName;
	private Integer dvyId;
	private String type;
	private String name;
	private String desc;
	private Timestamp createTime;
	private Timestamp modifyTime;

	// Constructors

	/** default constructor */
	public DeliveryType() {
	}

	/** minimal constructor */
	public DeliveryType(Integer userId, Integer userName, Integer dvyId, Timestamp createTime, Timestamp modifyTime) {
		this.userId = userId;
		this.userName = userName;
		this.dvyId = dvyId;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
	}

	/** full constructor */
	public DeliveryType(Integer userId, Integer userName, Integer dvyId, String type, String name, String desc,
			Timestamp createTime, Timestamp modifyTime) {
		this.userId = userId;
		this.userName = userName;
		this.dvyId = dvyId;
		this.type = type;
		this.name = name;
		this.desc = desc;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
	}

	// Property accessors

	public Integer getDvyTypeId() {
		return this.dvyTypeId;
	}

	public void setDvyTypeId(Integer dvyTypeId) {
		this.dvyTypeId = dvyTypeId;
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

	public Integer getDvyId() {
		return this.dvyId;
	}

	public void setDvyId(Integer dvyId) {
		this.dvyId = dvyId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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