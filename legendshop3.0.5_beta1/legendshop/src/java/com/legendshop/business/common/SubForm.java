/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.common;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单信息PO.
 */
public class SubForm implements Serializable{

	/**
	 * Gets the total.
	 * 
	 * @return the total
	 */
	public Double getTotal() {
		return total;
	}

	/**
	 * Sets the total.
	 * 
	 * @param total
	 *            the new total
	 */
	public void setTotal(Double total) {
		this.total = total;
	}

	/**
	 * Gets the pay type.
	 * 
	 * @return the pay type
	 */
	public Long getPayType() {
		return payType;
	}

	/**
	 * Sets the pay type.
	 * 
	 * @param payType
	 *            the new pay type
	 */
	public void setPayType(Long payType) {
		this.payType = payType;
	}

	/**
	 * Gets the basket id.
	 * 
	 * @return the basket id
	 */
	public String getBasketId() {
		return basketId;
	}

	/**
	 * Sets the basket id.
	 * 
	 * @param basketId
	 *            the new basket id
	 */
	public void setBasketId(String basketId) {
		this.basketId = basketId;
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
	 * Gets the user id.
	 * 
	 * @return the user id
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 * 
	 * @param userId
	 *            the new user id
	 */
	public void setUserId(Integer userId) {
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
	 * Gets the order name.
	 * 
	 * @return the order name
	 */
	public String getOrderName() {
		return orderName;
	}

	/**
	 * Sets the order name.
	 * 
	 * @param orderName
	 *            the new order name
	 */
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	/**
	 * Gets the user pass.
	 * 
	 * @return the user pass
	 */
	public String getUserPass() {
		return userPass;
	}

	/**
	 * Sets the user pass.
	 * 
	 * @param userPass
	 *            the new user pass
	 */
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	/**
	 * Gets the user mail.
	 * 
	 * @return the user mail
	 */
	public String getUserMail() {
		return userMail;
	}

	/**
	 * Sets the user mail.
	 * 
	 * @param userMail
	 *            the new user mail
	 */
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	/**
	 * Gets the user adds.
	 * 
	 * @return the user adds
	 */
	public String getUserAdds() {
		return userAdds;
	}

	/**
	 * Sets the user adds.
	 * 
	 * @param userAdds
	 *            the new user adds
	 */
	public void setUserAdds(String userAdds) {
		this.userAdds = userAdds;
	}

	/**
	 * Gets the pay type name.
	 * 
	 * @return the pay type name
	 */
	public String getPayTypeName() {
		return payTypeName;
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
	 * Gets the user tel.
	 * 
	 * @return the user tel
	 */
	public String getUserTel() {
		return userTel;
	}

	/**
	 * Sets the user tel.
	 * 
	 * @param userTel
	 *            the new user tel
	 */
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	/**
	 * Gets the user regtime.
	 * 
	 * @return the user regtime
	 */
	public Date getUserRegtime() {
		return userRegtime;
	}

	/**
	 * Sets the user regtime.
	 * 
	 * @param userRegtime
	 *            the new user regtime
	 */
	public void setUserRegtime(Date userRegtime) {
		this.userRegtime = userRegtime;
	}

	/**
	 * Gets the user regip.
	 * 
	 * @return the user regip
	 */
	public String getUserRegip() {
		return userRegip;
	}

	/**
	 * Sets the user regip.
	 * 
	 * @param userRegip
	 *            the new user regip
	 */
	public void setUserRegip(String userRegip) {
		this.userRegip = userRegip;
	}

	/**
	 * Gets the user lasttime.
	 * 
	 * @return the user lasttime
	 */
	public Date getUserLasttime() {
		return userLasttime;
	}

	/**
	 * Sets the user lasttime.
	 * 
	 * @param userLasttime
	 *            the new user lasttime
	 */
	public void setUserLasttime(Date userLasttime) {
		this.userLasttime = userLasttime;
	}

	/**
	 * Gets the user lastip.
	 * 
	 * @return the user lastip
	 */
	public String getUserLastip() {
		return userLastip;
	}

	/**
	 * Sets the user lastip.
	 * 
	 * @param userLastip
	 *            the new user lastip
	 */
	public void setUserLastip(String userLastip) {
		this.userLastip = userLastip;
	}

	/**
	 * Gets the user postcode.
	 * 
	 * @return the user postcode
	 */
	public String getUserPostcode() {
		return userPostcode;
	}

	/**
	 * Sets the user postcode.
	 * 
	 * @param userPostcode
	 *            the new user postcode
	 */
	public void setUserPostcode(String userPostcode) {
		this.userPostcode = userPostcode;
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6924455471052900466L;

	/** The total. */
	private Double total;//该订单的总钱数

    /** The pay type. */
    private Long payType;

    /** The basket id. */
    private String basketId;

    /** The other. */
    private String other;

    /** The user id. */
    private Integer userId;

    /** The user name. */
    private String userName;

    /** The order name. */
    private String orderName;

    /** The user pass. */
    private String userPass;

    /** The user mail. */
    private String userMail;

    /** The user adds. */
    private String userAdds;
    
    /** The pay type name. */
    private String payTypeName;

    /** The user tel. */
    private String userTel;

    /** The user regtime. */
    private Date userRegtime;

    /** The user regip. */
    private String userRegip;

    /** The user lasttime. */
    private Date userLasttime;

    /** The user lastip. */
    private String userLastip;

    /** The user postcode. */
    private String userPostcode;



}