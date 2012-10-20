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
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public class PayType extends AbstractEntity implements BaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 173116392190218430L;
	
	/** The pay id. */
	private Long payId;
	
	/** The user name. */
	private String userName;
	
	/** The pay type id. */
	private Integer payTypeId;
	
	/** The pay type name. */
	private String payTypeName;
	
	/** The partner. */
	private String partner;
	
	/** The validate key. */
	private String validateKey;
	
	/** The seller email. */
	private String sellerEmail;
	
	/** The memo. */
	private String memo;

	// Constructors

	/**
	 * default constructor.
	 */
	public PayType() {
	}

	/**
	 * minimal constructor.
	 * 
	 * @param payId
	 *            the pay id
	 * @param userName
	 *            the user name
	 * @param payTypeId
	 *            the pay type id
	 */
	public PayType(Long payId, String userName, Integer payTypeId) {
		this.payId = payId;
		this.userName = userName;
		this.payTypeId = payTypeId;
	}

	/**
	 * full constructor.
	 * 
	 * @param payId
	 *            the pay id
	 * @param userName
	 *            the user name
	 * @param payTypeId
	 *            the pay type id
	 * @param payTypeName
	 *            the pay type name
	 * @param partner
	 *            the partner
	 * @param validateKey
	 *            the validate key
	 * @param sellerEmail
	 *            the seller email
	 * @param memo
	 *            the memo
	 */
	public PayType(Long payId, String userName, Integer payTypeId,
			String payTypeName, String partner, String validateKey,
			String sellerEmail, String memo) {
		this.payId = payId;
		this.userName = userName;
		this.payTypeId = payTypeId;
		this.payTypeName = payTypeName;
		this.partner = partner;
		this.validateKey = validateKey;
		this.sellerEmail = sellerEmail;
		this.memo = memo;
	}

	// Property accessors

	/**
	 * Gets the pay id.
	 * 
	 * @return the pay id
	 */
	public Long getPayId() {
		return this.payId;
	}

	/**
	 * Sets the pay id.
	 * 
	 * @param payId
	 *            the new pay id
	 */
	public void setPayId(Long payId) {
		this.payId = payId;
	}

	/**
	 * Gets the user name.
	 * 
	 * @return the user name
	 */
	public String getUserName() {
		return this.userName;
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
	 * Gets the pay type id.
	 * 
	 * @return the pay type id
	 */
	public Integer getPayTypeId() {
		return this.payTypeId;
	}

	/**
	 * Sets the pay type id.
	 * 
	 * @param payTypeId
	 *            the new pay type id
	 */
	public void setPayTypeId(Integer payTypeId) {
		this.payTypeId = payTypeId;
	}

	/**
	 * Gets the pay type name.
	 * 
	 * @return the pay type name
	 */
	public String getPayTypeName() {
		return this.payTypeName;
	}

	/**
	 * Sets the pay type name.
	 * 
	 * @param payTypeName
	 *            the new pay type name
	 */
	public void setPayTypeName(String payTypeName) {
		this.payTypeName = payTypeName;
	}

	/**
	 * Gets the partner.
	 * 
	 * @return the partner
	 */
	public String getPartner() {
		return this.partner;
	}

	/**
	 * Sets the partner.
	 * 
	 * @param partner
	 *            the new partner
	 */
	public void setPartner(String partner) {
		this.partner = partner;
	}

	// public String getValidateKey() {
	// return this.validateKey;
	// }

	// 解密再用
	/**
	 * Gets the validate key.
	 * 
	 * @return the validate key
	 */
	public String getValidateKey() {
		// if(AppUtils.isNotBlank(this.validateKey)){
		// try {
		// return new
		// String(des.createDecryptor(des.stringToByte(ByteConverter.decode(this.validateKey))));
		// } catch (Exception e) {
		// System.err.println("getValidateKey error");
		// }
		// }
		return this.validateKey;
	}

	/**
	 * Sets the validate key.
	 * 
	 * @param validateKey
	 *            the new validate key
	 */
	public void setValidateKey(String validateKey) {
		this.validateKey = validateKey;
	}

	/**
	 * Gets the seller email.
	 * 
	 * @return the seller email
	 */
	public String getSellerEmail() {
		return this.sellerEmail;
	}

	/**
	 * Sets the seller email.
	 * 
	 * @param sellerEmail
	 *            the new seller email
	 */
	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}

	/**
	 * Gets the memo.
	 * 
	 * @return the memo
	 */
	public String getMemo() {
		return this.memo;
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

	public Serializable getId() {
		return payId;
	}

}