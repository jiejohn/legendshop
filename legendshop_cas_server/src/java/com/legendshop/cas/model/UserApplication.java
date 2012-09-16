package com.legendshop.cas.model;

import java.util.Date;

/**
 * Represent a user and a authorized application correlation.
 * 
 * @author George Guo
 * 
 */
public class UserApplication {
	private User user;
	private Application application;

	private int status;
	private Date createTime;
	private Date effectTime;

	private Date expireTime;
	private boolean useSmsAuth;

	private Date updateTime;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
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

	public boolean isUseSmsAuth() {
		return useSmsAuth;
	}

	public void setUseSmsAuth(boolean useSmsAuth) {
		this.useSmsAuth = useSmsAuth;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
