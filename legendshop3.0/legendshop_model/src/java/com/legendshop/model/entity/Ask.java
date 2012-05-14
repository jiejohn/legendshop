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
 * The Class Ask.
 */
public class Ask implements BaseEntity{


	/** The ask id. */
	private Long askId;
	
	/** The user id. */
	private String userId;
	
	/** The user name. */
	private String userName;
	
	/** The prod id. */
	private Long prodId;
	
	/** The shop id. */
	private Long shopId;
	
	/** The type. */
	private String type;
	
	/** The content. */
	private String content;
	
	/** The comment. */
	private String comment;
	
	/** The create time. */
	private Date createTime;
	
	/** The reply time. */
	private Date replyTime;

	// Constructors

	/**
	 * default constructor.
	 */
	public Ask() {
	}

	/**
	 * Gets the ask id.
	 * 
	 * @return the ask id
	 */
	public Long getAskId() {
		return askId;
	}

	/**
	 * Sets the ask id.
	 * 
	 * @param askId
	 *            the new ask id
	 */
	public void setAskId(Long askId) {
		this.askId = askId;
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
	 * Gets the content.
	 * 
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets the content.
	 * 
	 * @param content
	 *            the new content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Gets the comment.
	 * 
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Sets the comment.
	 * 
	 * @param comment
	 *            the new comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
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
	 * Gets the reply time.
	 * 
	 * @return the reply time
	 */
	public Date getReplyTime() {
		return replyTime;
	}

	/**
	 * Sets the reply time.
	 * 
	 * @param replyTime
	 *            the new reply time
	 */
	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

	public Serializable getId() {
		return askId;
	}


	
}