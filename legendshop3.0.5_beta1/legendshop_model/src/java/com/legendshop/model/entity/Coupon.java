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
 * Coupon entity. @author MyEclipse Persistence Tools
 */

public class Coupon implements BaseEntity {

	// Fields

	/** The coupon id. */
	private Long couponId;
	
	/** The user id. */
	private String userId;
	
	/** The user name. */
	private String userName;
	
	/** The shop id. */
	private Long shopId;
	
	/** The partner id. */
	private Long partnerId;
	
	/** The prod id. */
	private Long prodId;
	
	/** The sub id. */
	private Long subId;
	
	/** The type. */
	private String type;
	
	/** The score. */
	private Long score;
	
	/** The secret. */
	private String secret;
	
	/** The status. */
	private String status;
	
	/** The ip. */
	private String ip;
	
	/** The sms status. */
	private String smsStatus;
	
	/** The sms content. */
	private String smsContent;
	
	/** The expire time. */
	private Date expireTime;
	
	/** The consume time. */
	private Date consumeTime;
	
	/** The create time. */
	private Date createTime;
	
	/** The sms time. */
	private Date smsTime;
	
	/** The buy id. */
	private Integer buyId;

	// Constructors

	/**
	 * default constructor.
	 */
	public Coupon() {
	}

	/**
	 * Gets the coupon id.
	 * 
	 * @return the coupon id
	 */
	public Long getCouponId() {
		return couponId;
	}

	/**
	 * Sets the coupon id.
	 * 
	 * @param couponId
	 *            the new coupon id
	 */
	public void setCouponId(Long couponId) {
		this.couponId = couponId;
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
	 * Gets the shop id.
	 * 
	 * @return the shop id
	 */
	public Long getShopId() {
		return shopId;
	}

	/**
	 * Sets the shop id.
	 * 
	 * @param shopId
	 *            the new shop id
	 */
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	/**
	 * Gets the partner id.
	 * 
	 * @return the partner id
	 */
	public Long getPartnerId() {
		return partnerId;
	}

	/**
	 * Sets the partner id.
	 * 
	 * @param partnerId
	 *            the new partner id
	 */
	public void setPartnerId(Long partnerId) {
		this.partnerId = partnerId;
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
	 * Gets the score.
	 * 
	 * @return the score
	 */
	public Long getScore() {
		return score;
	}

	/**
	 * Sets the score.
	 * 
	 * @param score
	 *            the new score
	 */
	public void setScore(Long score) {
		this.score = score;
	}

	/**
	 * Gets the secret.
	 * 
	 * @return the secret
	 */
	public String getSecret() {
		return secret;
	}

	/**
	 * Sets the secret.
	 * 
	 * @param secret
	 *            the new secret
	 */
	public void setSecret(String secret) {
		this.secret = secret;
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
	 * Gets the sms status.
	 * 
	 * @return the sms status
	 */
	public String getSmsStatus() {
		return smsStatus;
	}

	/**
	 * Sets the sms status.
	 * 
	 * @param smsStatus
	 *            the new sms status
	 */
	public void setSmsStatus(String smsStatus) {
		this.smsStatus = smsStatus;
	}

	/**
	 * Gets the sms content.
	 * 
	 * @return the sms content
	 */
	public String getSmsContent() {
		return smsContent;
	}

	/**
	 * Sets the sms content.
	 * 
	 * @param smsContent
	 *            the new sms content
	 */
	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	/**
	 * Gets the expire time.
	 * 
	 * @return the expire time
	 */
	public Date getExpireTime() {
		return expireTime;
	}

	/**
	 * Sets the expire time.
	 * 
	 * @param expireTime
	 *            the new expire time
	 */
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	/**
	 * Gets the consume time.
	 * 
	 * @return the consume time
	 */
	public Date getConsumeTime() {
		return consumeTime;
	}

	/**
	 * Sets the consume time.
	 * 
	 * @param consumeTime
	 *            the new consume time
	 */
	public void setConsumeTime(Date consumeTime) {
		this.consumeTime = consumeTime;
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
	 * Gets the sms time.
	 * 
	 * @return the sms time
	 */
	public Date getSmsTime() {
		return smsTime;
	}

	/**
	 * Sets the sms time.
	 * 
	 * @param smsTime
	 *            the new sms time
	 */
	public void setSmsTime(Date smsTime) {
		this.smsTime = smsTime;
	}

	/**
	 * Gets the buy id.
	 * 
	 * @return the buy id
	 */
	public Integer getBuyId() {
		return buyId;
	}

	/**
	 * Sets the buy id.
	 * 
	 * @param buyId
	 *            the new buy id
	 */
	public void setBuyId(Integer buyId) {
		this.buyId = buyId;
	}

	public Serializable getId() {
		return couponId;
	}

}