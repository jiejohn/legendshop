package com.legendshop.model.entity;

import java.sql.Timestamp;

/**
 * UserAddress entity. @author MyEclipse Persistence Tools
 */

public class UserAddress implements java.io.Serializable {

	// Fields

	private Integer addrId;
	private Integer userId;
	private Integer userName;
	private String receiver;
	private String subAdds;
	private String subPost;
	private Integer provinceId;
	private Integer cityId;
	private String mobile;
	private String telphone;
	private String email;
	private String status;
	private String commonAddr;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public UserAddress() {
	}

	/** minimal constructor */
	public UserAddress(Integer userId, Integer userName, String status, Timestamp createTime) {
		this.userId = userId;
		this.userName = userName;
		this.status = status;
		this.createTime = createTime;
	}

	/** full constructor */
	public UserAddress(Integer userId, Integer userName, String receiver, String subAdds, String subPost,
			Integer provinceId, Integer cityId, String mobile, String telphone, String email, String status,
			String commonAddr, Timestamp createTime) {
		this.userId = userId;
		this.userName = userName;
		this.receiver = receiver;
		this.subAdds = subAdds;
		this.subPost = subPost;
		this.provinceId = provinceId;
		this.cityId = cityId;
		this.mobile = mobile;
		this.telphone = telphone;
		this.email = email;
		this.status = status;
		this.commonAddr = commonAddr;
		this.createTime = createTime;
	}

	// Property accessors

	public Integer getAddrId() {
		return this.addrId;
	}

	public void setAddrId(Integer addrId) {
		this.addrId = addrId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserName() {
		return this.userName;
	}

	public void setUserName(Integer userName) {
		this.userName = userName;
	}

	public String getReceiver() {
		return this.receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getSubAdds() {
		return this.subAdds;
	}

	public void setSubAdds(String subAdds) {
		this.subAdds = subAdds;
	}

	public String getSubPost() {
		return this.subPost;
	}

	public void setSubPost(String subPost) {
		this.subPost = subPost;
	}

	public Integer getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTelphone() {
		return this.telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCommonAddr() {
		return this.commonAddr;
	}

	public void setCommonAddr(String commonAddr) {
		this.commonAddr = commonAddr;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}