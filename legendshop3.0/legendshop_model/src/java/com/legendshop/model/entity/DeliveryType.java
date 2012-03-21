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
 * DeliveryType entity. @author MyEclipse Persistence Tools
 */

public class DeliveryType implements java.io.Serializable {

	// Fields

	/** The dvy type id. */
	private Long dvyTypeId;
	
	/** The user id. */
	private String userId;
	
	/** The user name. */
	private String userName;
	
	/** The dvy id. */
	private Long dvyId;
	
	/** The type. */
	private String type;
	
	/** The name. */
	private String name;
	
	/** The desc. */
	private String desc;
	
	/** The create time. */
	private Date createTime;
	
	/** The modify time. */
	private Date modifyTime;
	
	/**
	 * Gets the dvy type id.
	 * 
	 * @return the dvy type id
	 */
	public Long getDvyTypeId() {
		return dvyTypeId;
	}
	
	/**
	 * Sets the dvy type id.
	 * 
	 * @param dvyTypeId
	 *            the new dvy type id
	 */
	public void setDvyTypeId(Long dvyTypeId) {
		this.dvyTypeId = dvyTypeId;
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
	 * Gets the dvy id.
	 * 
	 * @return the dvy id
	 */
	public Long getDvyId() {
		return dvyId;
	}
	
	/**
	 * Sets the dvy id.
	 * 
	 * @param dvyId
	 *            the new dvy id
	 */
	public void setDvyId(Long dvyId) {
		this.dvyId = dvyId;
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
	 * Gets the desc.
	 * 
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}
	
	/**
	 * Sets the desc.
	 * 
	 * @param desc
	 *            the new desc
	 */
	public void setDesc(String desc) {
		this.desc = desc;
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
	 * Gets the modify time.
	 * 
	 * @return the modify time
	 */
	public Date getModifyTime() {
		return modifyTime;
	}
	
	/**
	 * Sets the modify time.
	 * 
	 * @param modifyTime
	 *            the new modify time
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	
}