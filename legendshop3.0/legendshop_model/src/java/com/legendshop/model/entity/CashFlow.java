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
 * CashFlow entity. @author MyEclipse Persistence Tools
 */

public class CashFlow implements java.io.Serializable {

	// Fields

	/** The flow id. */
	private Long flowId;
	
	/** The user id. */
	private String userId;
	
	/** The user name. */
	private String userName;
	
	/** The admin id. */
	private String adminId;
	
	/** The detail id. */
	private String detailId;
	
	/** The detail. */
	private String detail;
	
	/** The direction. */
	private String direction;
	
	/** The money. */
	private Double money;
	
	/** The action. */
	private String action;
	
	/** The create time. */
	private Date createTime;
	
	/**
	 * Gets the flow id.
	 * 
	 * @return the flow id
	 */
	public Long getFlowId() {
		return flowId;
	}
	
	/**
	 * Sets the flow id.
	 * 
	 * @param flowId
	 *            the new flow id
	 */
	public void setFlowId(Long flowId) {
		this.flowId = flowId;
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
	 * Gets the admin id.
	 * 
	 * @return the admin id
	 */
	public String getAdminId() {
		return adminId;
	}
	
	/**
	 * Sets the admin id.
	 * 
	 * @param adminId
	 *            the new admin id
	 */
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	
	/**
	 * Gets the detail id.
	 * 
	 * @return the detail id
	 */
	public String getDetailId() {
		return detailId;
	}
	
	/**
	 * Sets the detail id.
	 * 
	 * @param detailId
	 *            the new detail id
	 */
	public void setDetailId(String detailId) {
		this.detailId = detailId;
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
	 * Gets the direction.
	 * 
	 * @return the direction
	 */
	public String getDirection() {
		return direction;
	}
	
	/**
	 * Sets the direction.
	 * 
	 * @param direction
	 *            the new direction
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	/**
	 * Gets the money.
	 * 
	 * @return the money
	 */
	public Double getMoney() {
		return money;
	}
	
	/**
	 * Sets the money.
	 * 
	 * @param money
	 *            the new money
	 */
	public void setMoney(Double money) {
		this.money = money;
	}
	
	/**
	 * Gets the action.
	 * 
	 * @return the action
	 */
	public String getAction() {
		return action;
	}
	
	/**
	 * Sets the action.
	 * 
	 * @param action
	 *            the new action
	 */
	public void setAction(String action) {
		this.action = action;
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