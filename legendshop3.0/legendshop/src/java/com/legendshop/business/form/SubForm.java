/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.form;

import com.legendshop.spi.constants.Constants;


/**
 * 订单Form
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public class SubForm{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2769000419985544497L;
	
	/** The user name. */
	private String userName;
	
	/** The shop name. */
	private String shopName;
	
	/** The sub number. */
	private String subNumber;
	
	/** The status. */
	private Integer status;
	
	/** The sub check. */
	private String subCheck = Constants.FALSE_INDICATOR;// 处理中的订单, Y 已经处理完,N 尚未处理
	
	/** The cur page no. */
	private String curPageNO = "1";

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
	 * Gets the shop name.
	 * 
	 * @return the shop name
	 */
	public String getShopName() {
		return shopName;
	}

	/**
	 * Sets the shop name.
	 * 
	 * @param shopName
	 *            the new shop name
	 */
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	/**
	 * Gets the sub number.
	 * 
	 * @return the sub number
	 */
	public String getSubNumber() {
		return subNumber;
	}

	/**
	 * Sets the sub number.
	 * 
	 * @param subNumber
	 *            the new sub number
	 */
	public void setSubNumber(String subNumber) {
		this.subNumber = subNumber;
	}

	/**
	 * Gets the status.
	 * 
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 * 
	 * @param status
	 *            the new status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * Gets the sub check.
	 * 
	 * @return the sub check
	 */
	public String getSubCheck() {
		return subCheck;
	}

	/**
	 * Sets the sub check.
	 * 
	 * @param subCheck
	 *            the new sub check
	 */
	public void setSubCheck(String subCheck) {
		this.subCheck = subCheck;
	}

	/**
	 * Gets the cur page no.
	 * 
	 * @return the cur page no
	 */
	public String getCurPageNO() {
		return curPageNO;
	}

	/**
	 * Sets the cur page no.
	 * 
	 * @param curPageNO
	 *            the new cur page no
	 */
	public void setCurPageNO(String curPageNO) {
		this.curPageNO = curPageNO;
	}

}