package com.legendshop.model.entity;

import java.sql.Timestamp;

/**
 * Cash entity. @author MyEclipse Persistence Tools
 */

public class Cash implements java.io.Serializable {

	// Fields

	private Integer cashId;
	private Integer userId;
	private String userName;
	private String code;
	private String shopId;
	private Integer prodId;
	private String partnerId;
	private Integer subId;
	private String detail;
	private Double money;
	private String status;
	private Timestamp beginTime;
	private Timestamp endTime;
	private String ip;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public Cash() {
	}

	/** minimal constructor */
	public Cash(Integer userId, String userName, String code, Double money, String status, Timestamp beginTime,
			Timestamp createTime) {
		this.userId = userId;
		this.userName = userName;
		this.code = code;
		this.money = money;
		this.status = status;
		this.beginTime = beginTime;
		this.createTime = createTime;
	}

	/** full constructor */
	public Cash(Integer userId, String userName, String code, String shopId, Integer prodId, String partnerId,
			Integer subId, String detail, Double money, String status, Timestamp beginTime, Timestamp endTime,
			String ip, Timestamp createTime) {
		this.userId = userId;
		this.userName = userName;
		this.code = code;
		this.shopId = shopId;
		this.prodId = prodId;
		this.partnerId = partnerId;
		this.subId = subId;
		this.detail = detail;
		this.money = money;
		this.status = status;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.ip = ip;
		this.createTime = createTime;
	}

	// Property accessors

	public Integer getCashId() {
		return this.cashId;
	}

	public void setCashId(Integer cashId) {
		this.cashId = cashId;
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

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getShopId() {
		return this.shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public Integer getProdId() {
		return this.prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public String getPartnerId() {
		return this.partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public Integer getSubId() {
		return this.subId;
	}

	public void setSubId(Integer subId) {
		this.subId = subId;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(Timestamp beginTime) {
		this.beginTime = beginTime;
	}

	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}