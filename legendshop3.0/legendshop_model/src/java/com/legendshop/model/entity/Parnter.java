package com.legendshop.model.entity;

import java.sql.Timestamp;

/**
 * Parnter entity. @author MyEclipse Persistence Tools
 */

public class Parnter implements java.io.Serializable {

	// Fields

	private Integer partnerId;
	private String partnerName;
	private String password;
	private String title;
	private String homepage;
	private String userId;
	private String userName;
	private Integer shopId;
	private String bankName;
	private String bankNo;
	private String bankUser;
	private String location;
	private String contact;
	private String image;
	private String image1;
	private String image2;
	private String phone;
	private String address;
	private String other;
	private String mobile;
	private String open;
	private String status;
	private String display;
	private Integer commentGood;
	private Integer commentNone;
	private Integer commentBad;
	private Timestamp modifyTime;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public Parnter() {
	}

	/** minimal constructor */
	public Parnter(String partnerName, String password, String title, String userId, String userName, Integer shopId,
			String location, String open, String status, String display, Integer commentGood, Integer commentNone,
			Integer commentBad, Timestamp modifyTime, Timestamp createTime) {
		this.partnerName = partnerName;
		this.password = password;
		this.title = title;
		this.userId = userId;
		this.userName = userName;
		this.shopId = shopId;
		this.location = location;
		this.open = open;
		this.status = status;
		this.display = display;
		this.commentGood = commentGood;
		this.commentNone = commentNone;
		this.commentBad = commentBad;
		this.modifyTime = modifyTime;
		this.createTime = createTime;
	}

	/** full constructor */
	public Parnter(String partnerName, String password, String title, String homepage, String userId, String userName,
			Integer shopId, String bankName, String bankNo, String bankUser, String location, String contact,
			String image, String image1, String image2, String phone, String address, String other, String mobile,
			String open, String status, String display, Integer commentGood, Integer commentNone, Integer commentBad,
			Timestamp modifyTime, Timestamp createTime) {
		this.partnerName = partnerName;
		this.password = password;
		this.title = title;
		this.homepage = homepage;
		this.userId = userId;
		this.userName = userName;
		this.shopId = shopId;
		this.bankName = bankName;
		this.bankNo = bankNo;
		this.bankUser = bankUser;
		this.location = location;
		this.contact = contact;
		this.image = image;
		this.image1 = image1;
		this.image2 = image2;
		this.phone = phone;
		this.address = address;
		this.other = other;
		this.mobile = mobile;
		this.open = open;
		this.status = status;
		this.display = display;
		this.commentGood = commentGood;
		this.commentNone = commentNone;
		this.commentBad = commentBad;
		this.modifyTime = modifyTime;
		this.createTime = createTime;
	}

	// Property accessors

	public Integer getPartnerId() {
		return this.partnerId;
	}

	public void setPartnerId(Integer partnerId) {
		this.partnerId = partnerId;
	}

	public String getPartnerName() {
		return this.partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHomepage() {
		return this.homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
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

	public Integer getShopId() {
		return this.shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankNo() {
		return this.bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getBankUser() {
		return this.bankUser;
	}

	public void setBankUser(String bankUser) {
		this.bankUser = bankUser;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImage1() {
		return this.image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return this.image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOther() {
		return this.other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getOpen() {
		return this.open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDisplay() {
		return this.display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public Integer getCommentGood() {
		return this.commentGood;
	}

	public void setCommentGood(Integer commentGood) {
		this.commentGood = commentGood;
	}

	public Integer getCommentNone() {
		return this.commentNone;
	}

	public void setCommentNone(Integer commentNone) {
		this.commentNone = commentNone;
	}

	public Integer getCommentBad() {
		return this.commentBad;
	}

	public void setCommentBad(Integer commentBad) {
		this.commentBad = commentBad;
	}

	public Timestamp getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}