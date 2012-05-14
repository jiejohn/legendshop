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
 * 站内信对象.
 */
public class UserComment implements BaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6496927494248199158L;

	/** The id. */
	private Long id;

	/** The comment type. */
	private Integer commentType;

	/** The user id. */
	private String userId;

	/** The user name. */
	private String userName;

	/** The your name. */
	private String yourName;

	/** The to user name. */
	private String toUserName;

	/** The content. */
	private String content;

	/** The answer. */
	private String answer;

	/**
	 * Gets the answer.
	 * 
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * Sets the answer.
	 * 
	 * @param answer
	 *            the new answer
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/** The addtime. */
	private Date addtime;

	/** The answertime. */
	private Date answertime;

	/**
	 * Gets the answertime.
	 * 
	 * @return the answertime
	 */
	public Date getAnswertime() {
		return answertime;
	}

	/**
	 * Sets the answertime.
	 * 
	 * @param answertime
	 *            the new answertime
	 */
	public void setAnswertime(Date answertime) {
		this.answertime = answertime;
	}

	/** The postip. */
	private String postip;

	/** The status. */
	private Integer status;

	// Constructors

	/**
	 * default constructor.
	 */
	public UserComment() {
	}

	/**
	 * minimal constructor.
	 * 
	 * @param id
	 *            the id
	 */
	public UserComment(Long id) {
		this.id = id;
	}

	// Property accessors

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the comment type.
	 * 
	 * @return the comment type
	 */
	public Integer getCommentType() {
		return this.commentType;
	}

	/**
	 * Sets the comment type.
	 * 
	 * @param commentType
	 *            the new comment type
	 */
	public void setCommentType(Integer commentType) {
		this.commentType = commentType;
	}

	/**
	 * Gets the user id.
	 * 
	 * @return the user id
	 */
	public String getUserId() {
		return this.userId;
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
		return this.userName;
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
	 * Gets the your name.
	 * 
	 * @return the your name
	 */
	public String getYourName() {
		return this.yourName;
	}

	/**
	 * Sets the your name.
	 * 
	 * @param yourName
	 *            the new your name
	 */
	public void setYourName(String yourName) {
		this.yourName = yourName;
	}

	/**
	 * Gets the to user name.
	 * 
	 * @return the to user name
	 */
	public String getToUserName() {
		return this.toUserName;
	}

	/**
	 * Sets the to user name.
	 * 
	 * @param toUserName
	 *            the new to user name
	 */
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	/**
	 * Gets the content.
	 * 
	 * @return the content
	 */
	public String getContent() {
		return this.content;
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
	 * Gets the addtime.
	 * 
	 * @return the addtime
	 */
	public Date getAddtime() {
		return this.addtime;
	}

	/**
	 * Sets the addtime.
	 * 
	 * @param addtime
	 *            the new addtime
	 */
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	/**
	 * Gets the postip.
	 * 
	 * @return the postip
	 */
	public String getPostip() {
		return this.postip;
	}

	/**
	 * Sets the postip.
	 * 
	 * @param postip
	 *            the new postip
	 */
	public void setPostip(String postip) {
		this.postip = postip;
	}

	/**
	 * Gets the status.
	 * 
	 * @return the status
	 */
	public Integer getStatus() {
		return this.status;
	}

	/**
	 * Sets the status.
	 * 
	 * @param status
	 *            the new status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

}