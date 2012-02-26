/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.model.entity;

/**
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */

public class NsortBrand implements java.io.Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -213312180753350928L;
	
	/** The id. */
	private NsortBrandId id;
	
	/** The user name. */
	private String userName;

	// Constructors

	/**
	 * default constructor.
	 */
	public NsortBrand() {
	}

	/**
	 * full constructor.
	 * 
	 * @param id
	 *            the id
	 * @param userName
	 *            the user name
	 */
	public NsortBrand(NsortBrandId id, String userName) {
		this.id = id;
		this.userName = userName;
	}

	// Property accessors

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public NsortBrandId getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	public void setId(NsortBrandId id) {
		this.id = id;
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

}