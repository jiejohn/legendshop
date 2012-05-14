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
 * UserAddress entity. @author MyEclipse Persistence Tools
 */

public class UserAddress implements BaseEntity {

	// Fields

	/** The addr id. */
	private Long addrId;
	
	/** The user id. */
	private String userId;
	
	/** The user name. */
	private String userName;
	
	/** The receiver. */
	private String receiver;
	
	/** The sub adds. */
	private String subAdds;
	
	/** The sub post. */
	private String subPost;
	
	/** The province id. */
	private Integer provinceId;
	
	/** The city id. */
	private Integer cityId;
	
	/** The mobile. */
	private String mobile;
	
	/** The telphone. */
	private String telphone;
	
	/** The email. */
	private String email;
	
	/** The status. */
	private String status;
	
	/** The common addr. */
	private String commonAddr;
	
	/** The create time. */
	private Date createTime;
	
	/**
	 * Gets the addr id.
	 * 
	 * @return the addr id
	 */
	public Long getAddrId() {
		return addrId;
	}
	
	/**
	 * Sets the addr id.
	 * 
	 * @param addrId
	 *            the new addr id
	 */
	public void setAddrId(Long addrId) {
		this.addrId = addrId;
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
	 * Gets the receiver.
	 * 
	 * @return the receiver
	 */
	public String getReceiver() {
		return receiver;
	}
	
	/**
	 * Sets the receiver.
	 * 
	 * @param receiver
	 *            the new receiver
	 */
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	/**
	 * Gets the sub adds.
	 * 
	 * @return the sub adds
	 */
	public String getSubAdds() {
		return subAdds;
	}
	
	/**
	 * Sets the sub adds.
	 * 
	 * @param subAdds
	 *            the new sub adds
	 */
	public void setSubAdds(String subAdds) {
		this.subAdds = subAdds;
	}
	
	/**
	 * Gets the sub post.
	 * 
	 * @return the sub post
	 */
	public String getSubPost() {
		return subPost;
	}
	
	/**
	 * Sets the sub post.
	 * 
	 * @param subPost
	 *            the new sub post
	 */
	public void setSubPost(String subPost) {
		this.subPost = subPost;
	}
	
	/**
	 * Gets the province id.
	 * 
	 * @return the province id
	 */
	public Integer getProvinceId() {
		return provinceId;
	}
	
	/**
	 * Sets the province id.
	 * 
	 * @param provinceId
	 *            the new province id
	 */
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	
	/**
	 * Gets the city id.
	 * 
	 * @return the city id
	 */
	public Integer getCityId() {
		return cityId;
	}
	
	/**
	 * Sets the city id.
	 * 
	 * @param cityId
	 *            the new city id
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	
	/**
	 * Gets the mobile.
	 * 
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	
	/**
	 * Sets the mobile.
	 * 
	 * @param mobile
	 *            the new mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	/**
	 * Gets the telphone.
	 * 
	 * @return the telphone
	 */
	public String getTelphone() {
		return telphone;
	}
	
	/**
	 * Sets the telphone.
	 * 
	 * @param telphone
	 *            the new telphone
	 */
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	
	/**
	 * Gets the email.
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Sets the email.
	 * 
	 * @param email
	 *            the new email
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * Gets the common addr.
	 * 
	 * @return the common addr
	 */
	public String getCommonAddr() {
		return commonAddr;
	}
	
	/**
	 * Sets the common addr.
	 * 
	 * @param commonAddr
	 *            the new common addr
	 */
	public void setCommonAddr(String commonAddr) {
		this.commonAddr = commonAddr;
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

	public Serializable getId() {
		return addrId;
	}


}