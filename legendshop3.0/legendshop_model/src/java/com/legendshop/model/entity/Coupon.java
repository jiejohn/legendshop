package com.legendshop.model.entity;

import java.sql.Timestamp;

/**
 * Coupon entity. @author MyEclipse Persistence Tools
 */

public class Coupon implements java.io.Serializable {

	// Fields

	private Integer couponId;
	private Integer userId;
	private String userName;
	private Integer shopId;
	private Integer partnerId;
	private Integer prodId;
	private Integer subId;
	private String type;
	private Integer score;
	private String secret;
	private String status;
	private String ip;
	private String smsStatus;
	private String smsContent;
	private Timestamp expireTime;
	private Timestamp consumeTime;
	private Timestamp createTime;
	private Timestamp smsTime;
	private Integer buyId;

	// Constructors

	/** default constructor */
	public Coupon() {
	}

	/** minimal constructor */
	public Coupon(Integer userId, String userName, Integer shopId, Integer partnerId, Integer prodId, Integer subId,
			Timestamp expireTime, Timestamp createTime, Integer buyId) {
		this.userId = userId;
		this.userName = userName;
		this.shopId = shopId;
		this.partnerId = partnerId;
		this.prodId = prodId;
		this.subId = subId;
		this.expireTime = expireTime;
		this.createTime = createTime;
		this.buyId = buyId;
	}

	/** full constructor */
	public Coupon(Integer userId, String userName, Integer shopId, Integer partnerId, Integer prodId, Integer subId,
			String type, Integer score, String secret, String status, String ip, String smsStatus, String smsContent,
			Timestamp expireTime, Timestamp consumeTime, Timestamp createTime, Timestamp smsTime, Integer buyId) {
		this.userId = userId;
		this.userName = userName;
		this.shopId = shopId;
		this.partnerId = partnerId;
		this.prodId = prodId;
		this.subId = subId;
		this.type = type;
		this.score = score;
		this.secret = secret;
		this.status = status;
		this.ip = ip;
		this.smsStatus = smsStatus;
		this.smsContent = smsContent;
		this.expireTime = expireTime;
		this.consumeTime = consumeTime;
		this.createTime = createTime;
		this.smsTime = smsTime;
		this.buyId = buyId;
	}

	// Property accessors

	public Integer getCouponId() {
		return this.couponId;
	}

	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
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

	public Integer getShopId() {
		return this.shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public Integer getPartnerId() {
		return this.partnerId;
	}

	public void setPartnerId(Integer partnerId) {
		this.partnerId = partnerId;
	}

	public Integer getProdId() {
		return this.prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public Integer getSubId() {
		return this.subId;
	}

	public void setSubId(Integer subId) {
		this.subId = subId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getSecret() {
		return this.secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getSmsStatus() {
		return this.smsStatus;
	}

	public void setSmsStatus(String smsStatus) {
		this.smsStatus = smsStatus;
	}

	public String getSmsContent() {
		return this.smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	public Timestamp getExpireTime() {
		return this.expireTime;
	}

	public void setExpireTime(Timestamp expireTime) {
		this.expireTime = expireTime;
	}

	public Timestamp getConsumeTime() {
		return this.consumeTime;
	}

	public void setConsumeTime(Timestamp consumeTime) {
		this.consumeTime = consumeTime;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getSmsTime() {
		return this.smsTime;
	}

	public void setSmsTime(Timestamp smsTime) {
		this.smsTime = smsTime;
	}

	public Integer getBuyId() {
		return this.buyId;
	}

	public void setBuyId(Integer buyId) {
		this.buyId = buyId;
	}

}