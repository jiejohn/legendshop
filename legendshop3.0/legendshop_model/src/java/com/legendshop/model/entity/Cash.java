/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.model.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Cash entity. @author MyEclipse Persistence Tools
 */

public class Cash implements java.io.Serializable {

	// Fields

	/** The cash id. */
	private Long cashId;
	
	/** The user id. */
	private String userId;
	
	/** The user name. */
	private String userName;
	
	/** The code. */
	private String code;
	
	/** The shop id. */
	private String shopId;
	
	/** The prod id. */
	private Long prodId;
	
	/** The partner id. */
	private String partnerId;
	
	/** The sub id. */
	private Long subId;
	
	/** The detail. */
	private String detail;
	
	/** The money. */
	private BigDecimal money;
	
	/** The status. */
	private String status;
	
	/** The begin time. */
	private Date beginTime;
	
	/** The end time. */
	private Date endTime;
	
	/** The ip. */
	private String ip;
	
	/** The create time. */
	private Date createTime;
	
	/**
	 * Gets the cash id.
	 * 
	 * @return the cash id
	 */
	public Long getCashId() {
		return cashId;
	}
	
	/**
	 * Sets the cash id.
	 * 
	 * @param cashId
	 *            the new cash id
	 */
	public void setCashId(Long cashId) {
		this.cashId = cashId;
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
	 * Gets the code.
	 * 
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * Sets the code.
	 * 
	 * @param code
	 *            the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * Gets the shop id.
	 * 
	 * @return the shop id
	 */
	public String getShopId() {
		return shopId;
	}
	
	/**
	 * Sets the shop id.
	 * 
	 * @param shopId
	 *            the new shop id
	 */
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	
	/**
	 * Gets the prod id.
	 * 
	 * @return the prod id
	 */
	public Long getProdId() {
		return prodId;
	}
	
	/**
	 * Sets the prod id.
	 * 
	 * @param prodId
	 *            the new prod id
	 */
	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}
	
	/**
	 * Gets the partner id.
	 * 
	 * @return the partner id
	 */
	public String getPartnerId() {
		return partnerId;
	}
	
	/**
	 * Sets the partner id.
	 * 
	 * @param partnerId
	 *            the new partner id
	 */
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	
	/**
	 * Gets the sub id.
	 * 
	 * @return the sub id
	 */
	public Long getSubId() {
		return subId;
	}
	
	/**
	 * Sets the sub id.
	 * 
	 * @param subId
	 *            the new sub id
	 */
	public void setSubId(Long subId) {
		this.subId = subId;
	}
	
	/**
	 * Gets the detail.
	 * 
	 * @return the detail
	 */
	public String getDetail() {
		return detail;
	}
	
	/**
	 * Sets the detail.
	 * 
	 * @param detail
	 *            the new detail
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	/**
	 * Gets the money.
	 * 
	 * @return the money
	 */
	public BigDecimal getMoney() {
		return money;
	}
	
	/**
	 * Sets the money.
	 * 
	 * @param money
	 *            the new money
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	/**
	 * Gets the status.
	 * 
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * Sets the status.
	 * 
	 * @param status
	 *            the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * Gets the begin time.
	 * 
	 * @return the begin time
	 */
	public Date getBeginTime() {
		return beginTime;
	}
	
	/**
	 * Sets the begin time.
	 * 
	 * @param beginTime
	 *            the new begin time
	 */
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	
	/**
	 * Gets the end time.
	 * 
	 * @return the end time
	 */
	public Date getEndTime() {
		return endTime;
	}
	
	/**
	 * Sets the end time.
	 * 
	 * @param endTime
	 *            the new end time
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	/**
	 * Gets the ip.
	 * 
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}
	
	/**
	 * Sets the ip.
	 * 
	 * @param ip
	 *            the new ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
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

	
}