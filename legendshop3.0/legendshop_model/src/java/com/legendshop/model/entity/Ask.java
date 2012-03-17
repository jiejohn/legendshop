package com.legendshop.model.entity;

import java.sql.Timestamp;

/**
 * Ask entity. @author MyEclipse Persistence Tools
 */

public class Ask implements java.io.Serializable {

	// Fields

	private Integer askId;
	private Integer userId;
	private String userName;
	private Integer prodId;
	private Integer shopId;
	private String type;
	private String content;
	private String comment;
	private Timestamp createTime;
	private Timestamp replyTime;

	// Constructors

	/** default constructor */
	public Ask() {
	}

	/** minimal constructor */
	public Ask(Integer userId, String userName, Integer prodId, Integer shopId, String type, Timestamp createTime) {
		this.userId = userId;
		this.userName = userName;
		this.prodId = prodId;
		this.shopId = shopId;
		this.type = type;
		this.createTime = createTime;
	}

	/** full constructor */
	public Ask(Integer userId, String userName, Integer prodId, Integer shopId, String type, String content,
			String comment, Timestamp createTime, Timestamp replyTime) {
		this.userId = userId;
		this.userName = userName;
		this.prodId = prodId;
		this.shopId = shopId;
		this.type = type;
		this.content = content;
		this.comment = comment;
		this.createTime = createTime;
		this.replyTime = replyTime;
	}

	// Property accessors

	public Integer getAskId() {
		return this.askId;
	}

	public void setAskId(Integer askId) {
		this.askId = askId;
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

	public Integer getProdId() {
		return this.prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public Integer getShopId() {
		return this.shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getReplyTime() {
		return this.replyTime;
	}

	public void setReplyTime(Timestamp replyTime) {
		this.replyTime = replyTime;
	}

}