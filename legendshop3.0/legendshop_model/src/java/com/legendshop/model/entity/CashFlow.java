package com.legendshop.model.entity;

/**
 * CashFlow entity. @author MyEclipse Persistence Tools
 */

public class CashFlow implements java.io.Serializable {

	// Fields

	private Integer flowId;
	private String userId;
	private String userName;
	private String adminId;
	private String detailId;
	private String detail;
	private String direction;
	private Double money;
	private String action;
	private Integer createTime;

	// Constructors

	/** default constructor */
	public CashFlow() {
	}

	/** minimal constructor */
	public CashFlow(String userId, String userName, String adminId, String direction, Double money, String action,
			Integer createTime) {
		this.userId = userId;
		this.userName = userName;
		this.adminId = adminId;
		this.direction = direction;
		this.money = money;
		this.action = action;
		this.createTime = createTime;
	}

	/** full constructor */
	public CashFlow(String userId, String userName, String adminId, String detailId, String detail, String direction,
			Double money, String action, Integer createTime) {
		this.userId = userId;
		this.userName = userName;
		this.adminId = adminId;
		this.detailId = detailId;
		this.detail = detail;
		this.direction = direction;
		this.money = money;
		this.action = action;
		this.createTime = createTime;
	}

	// Property accessors

	public Integer getFlowId() {
		return this.flowId;
	}

	public void setFlowId(Integer flowId) {
		this.flowId = flowId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAdminId() {
		return this.adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getDetailId() {
		return this.detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getDirection() {
		return this.direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Integer getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}

}