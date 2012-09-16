package com.legendshop.cas.model;

import java.util.Date;

/**
 * A user represent a client who use the CAS service.
 * 
 * @author George Guo
 * 
 */
public class User {

	/**
	 * the user identity which must be unique, the same as the username in CAS
	 * credential
	 */
	private String userId;

	/**
	 * the password in CAS
	 */
	// private String password;

	private UserPassword userPassword;

	/**
	 * the nick name or local name of a user (e.g. a Chinese name)
	 */
	private String userName;

	/**
	 * the organization or unit identity which the use belongs to
	 */
	private String organization;

	/**
	 * the user status, 1 means active, 0 means inactive.
	 */
	private int status;

	/**
	 * The user's cell phone number
	 */
	private String mobile;

	/**
	 * the create time of the user entity
	 */
	private Date createTime;

	private Date effectTime;

	private Date expireTime;

	private Date updateTime;

	/**
	 * the account frozen expire time
	 */
	private Date frozenExpireTime;

	public Date getFrozenExpireTime() {
		return frozenExpireTime;
	}

	public void setFrozenExpireTime(Date frozenExpireTime) {
		this.frozenExpireTime = frozenExpireTime;
	}

	public Date getEffectTime() {
		return effectTime;
	}

	public void setEffectTime(Date effectTime) {
		this.effectTime = effectTime;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public UserPassword getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(UserPassword userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
