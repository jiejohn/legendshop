/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.model.entity;

import java.io.Serializable;


/**
 * 产品品牌.
 */
public class Brand extends UploadFile implements BaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3941969699979401870L;

	/** The brand id. */
	private Long brandId;

	/** The brand name. */
	private String brandName;

	/** The user id. */
	private String userId;

	/** The user name. */
	private String userName;

	/** The brand pic. */
	private String brandPic;

	/** The memo. */
	private String memo;

	/**
	 * Instantiates a new brand.
	 */
	public Brand() {
	}

	/**
	 * Gets the brand id.
	 * 
	 * @return the brand id
	 */
	public Long getBrandId() {
		return brandId;
	}

	/**
	 * Sets the brand id.
	 * 
	 * @param brandId
	 *            the new brand id
	 */
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	/**
	 * Gets the brand name.
	 * 
	 * @return the brand name
	 */
	public String getBrandName() {
		return brandName;
	}

	/**
	 * Sets the brand name.
	 * 
	 * @param brandName
	 *            the new brand name
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
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
	 * Gets the memo.
	 * 
	 * @return the memo
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * Sets the memo.
	 * 
	 * @param memo
	 *            the new memo
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * Gets the brand pic.
	 * 
	 * @return the brand pic
	 */
	public String getBrandPic() {
		return brandPic;
	}

	/**
	 * Sets the brand pic.
	 * 
	 * @param brandPic
	 *            the new brand pic
	 */
	public void setBrandPic(String brandPic) {
		this.brandPic = brandPic;
	}

	public Serializable getId() {
		return brandId;
	}

}
