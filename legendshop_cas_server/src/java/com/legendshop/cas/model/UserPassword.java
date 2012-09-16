package com.legendshop.cas.model;

import java.util.Date;
import java.util.List;

/**
 * User's password information.
 * 
 * @author George Guo
 * 
 */
public class UserPassword {
	private String currentPassword;
	private List<String> historyPasswords;

	/**
	 * The password expire time
	 */
	private Date expireTime;
	/**
	 * The time when modify the password last time.
	 */
	private Date lastModifyTime;

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public List<String> getHistoryPasswords() {
		return historyPasswords;
	}

	public void setHistoryPasswords(List<String> historyPasswords) {
		this.historyPasswords = historyPasswords;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public boolean isResetPassword() {
		return resetPassword;
	}

	public void setResetPassword(boolean resetPassword) {
		this.resetPassword = resetPassword;
	}

	private boolean resetPassword;
}
