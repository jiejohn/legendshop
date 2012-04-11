/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.model.entity;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

/**
 * Partner entity. @author MyEclipse Persistence Tools
 */

public class Partner implements java.io.Serializable {

	// Fields

	/** The partner id. */
	private Long partnerId;
	
	/** The partner name. */
	private String partnerName;
	
	/** The password. */
	private String password;
	
	/** The title. */
	private String title;
	
	/** The homepage. */
	private String homepage;
	
	/** The user id. */
	private String userId;
	
	/** The user name. */
	private String userName;
	
	/** The shop id. */
	private Long shopId;
	
	/** The bank name. */
	private String bankName;
	
	/** The bank no. */
	private String bankNo;
	
	/** The bank user. */
	private String bankUser;
	
	/** The location. */
	private String location;
	
	/** The contact. */
	private String contact;
	
	/** The image. */
	private String image;
	
	/** The image1. */
	private String image1;
	
	/** The image2. */
	private String image2;
	
	/** The phone. */
	private String phone;
	
	/** The address. */
	private String address;
	
	/** The other. */
	private String other;
	
	/** The mobile. */
	private String mobile;
	
	/** The open. */
	private String open;
	
	/** The status. */
	private String status;
	
	/** The display. */
	private String display;
	
	/** The comment good. */
	private Integer commentGood;
	
	/** The comment none. */
	private Integer commentNone;
	
	/** The comment bad. */
	private Integer commentBad;
	
	/** The modify time. */
	private Date modifyTime;
	
	/** The create time. */
	private Date createTime;
	
	protected MultipartFile imageFile;
	protected MultipartFile imageFile1;
	protected MultipartFile imageFile2;
	
	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}

	public MultipartFile getImageFile1() {
		return imageFile1;
	}

	public void setImageFile1(MultipartFile imageFile1) {
		this.imageFile1 = imageFile1;
	}

	public MultipartFile getImageFile2() {
		return imageFile2;
	}

	public void setImageFile2(MultipartFile imageFile2) {
		this.imageFile2 = imageFile2;
	}

	/**
	 * Gets the partner id.
	 * 
	 * @return the partner id
	 */
	public Long getPartnerId() {
		return partnerId;
	}
	
	/**
	 * Sets the partner id.
	 * 
	 * @param partnerId
	 *            the new partner id
	 */
	public void setPartnerId(Long partnerId) {
		this.partnerId = partnerId;
	}
	
	/**
	 * Gets the partner name.
	 * 
	 * @return the partner name
	 */
	public String getPartnerName() {
		return partnerName;
	}
	
	/**
	 * Sets the partner name.
	 * 
	 * @param partnerName
	 *            the new partner name
	 */
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	
	/**
	 * Gets the password.
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets the password.
	 * 
	 * @param password
	 *            the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Gets the title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Sets the title.
	 * 
	 * @param title
	 *            the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Gets the homepage.
	 * 
	 * @return the homepage
	 */
	public String getHomepage() {
		return homepage;
	}
	
	/**
	 * Sets the homepage.
	 * 
	 * @param homepage
	 *            the new homepage
	 */
	public void setHomepage(String homepage) {
		this.homepage = homepage;
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
	 * Gets the bank name.
	 * 
	 * @return the bank name
	 */
	public String getBankName() {
		return bankName;
	}
	
	/**
	 * Sets the bank name.
	 * 
	 * @param bankName
	 *            the new bank name
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	/**
	 * Gets the bank no.
	 * 
	 * @return the bank no
	 */
	public String getBankNo() {
		return bankNo;
	}
	
	/**
	 * Sets the bank no.
	 * 
	 * @param bankNo
	 *            the new bank no
	 */
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}
	
	/**
	 * Gets the bank user.
	 * 
	 * @return the bank user
	 */
	public String getBankUser() {
		return bankUser;
	}
	
	/**
	 * Sets the bank user.
	 * 
	 * @param bankUser
	 *            the new bank user
	 */
	public void setBankUser(String bankUser) {
		this.bankUser = bankUser;
	}
	
	/**
	 * Gets the location.
	 * 
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	
	/**
	 * Sets the location.
	 * 
	 * @param location
	 *            the new location
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	
	/**
	 * Gets the contact.
	 * 
	 * @return the contact
	 */
	public String getContact() {
		return contact;
	}
	
	/**
	 * Sets the contact.
	 * 
	 * @param contact
	 *            the new contact
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	/**
	 * Gets the image.
	 * 
	 * @return the image
	 */
	public String getImage() {
		return image;
	}
	
	/**
	 * Sets the image.
	 * 
	 * @param image
	 *            the new image
	 */
	public void setImage(String image) {
		this.image = image;
	}
	
	/**
	 * Gets the image1.
	 * 
	 * @return the image1
	 */
	public String getImage1() {
		return image1;
	}
	
	/**
	 * Sets the image1.
	 * 
	 * @param image1
	 *            the new image1
	 */
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	
	/**
	 * Gets the image2.
	 * 
	 * @return the image2
	 */
	public String getImage2() {
		return image2;
	}
	
	/**
	 * Sets the image2.
	 * 
	 * @param image2
	 *            the new image2
	 */
	public void setImage2(String image2) {
		this.image2 = image2;
	}
	
	/**
	 * Gets the phone.
	 * 
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * Sets the phone.
	 * 
	 * @param phone
	 *            the new phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * Gets the address.
	 * 
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Sets the address.
	 * 
	 * @param address
	 *            the new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * Gets the other.
	 * 
	 * @return the other
	 */
	public String getOther() {
		return other;
	}
	
	/**
	 * Sets the other.
	 * 
	 * @param other
	 *            the new other
	 */
	public void setOther(String other) {
		this.other = other;
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
	 * Gets the open.
	 * 
	 * @return the open
	 */
	public String getOpen() {
		return open;
	}
	
	/**
	 * Sets the open.
	 * 
	 * @param open
	 *            the new open
	 */
	public void setOpen(String open) {
		this.open = open;
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
	 * Gets the display.
	 * 
	 * @return the display
	 */
	public String getDisplay() {
		return display;
	}
	
	/**
	 * Sets the display.
	 * 
	 * @param display
	 *            the new display
	 */
	public void setDisplay(String display) {
		this.display = display;
	}
	
	/**
	 * Gets the comment good.
	 * 
	 * @return the comment good
	 */
	public Integer getCommentGood() {
		return commentGood;
	}
	
	/**
	 * Sets the comment good.
	 * 
	 * @param commentGood
	 *            the new comment good
	 */
	public void setCommentGood(Integer commentGood) {
		this.commentGood = commentGood;
	}
	
	/**
	 * Gets the comment none.
	 * 
	 * @return the comment none
	 */
	public Integer getCommentNone() {
		return commentNone;
	}
	
	/**
	 * Sets the comment none.
	 * 
	 * @param commentNone
	 *            the new comment none
	 */
	public void setCommentNone(Integer commentNone) {
		this.commentNone = commentNone;
	}
	
	/**
	 * Gets the comment bad.
	 * 
	 * @return the comment bad
	 */
	public Integer getCommentBad() {
		return commentBad;
	}
	
	/**
	 * Sets the comment bad.
	 * 
	 * @param commentBad
	 *            the new comment bad
	 */
	public void setCommentBad(Integer commentBad) {
		this.commentBad = commentBad;
	}
	
	/**
	 * Gets the modify time.
	 * 
	 * @return the modify time
	 */
	public Date getModifyTime() {
		return modifyTime;
	}
	
	/**
	 * Sets the modify time.
	 * 
	 * @param modifyTime
	 *            the new modify time
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
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