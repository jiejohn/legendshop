/**
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */

package com.legendshop.model.entity;

import java.util.Date;

/**
 * 相关产品.
 */
public class RelProduct implements java.io.Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8175142186764722214L;

	/** The rel id. */
	private Long relId;

	/** The prod id. */
	private Long prodId;

	/** The rel prod id. */
	private Long relProdId;

	/** The rel prod name. */
	private String relProdName;

	/** The addtime. */
	private Date addtime;
	
	/** The user name. */
	private String userName;

	/**
	 * Gets the rel id.
	 * 
	 * @return the rel id
	 */
	public Long getRelId() {
		return relId;
	}

	/**
	 * Sets the rel id.
	 * 
	 * @param relId
	 *            the new rel id
	 */
	public void setRelId(Long relId) {
		this.relId = relId;
	}

	/**
	 * Gets the prod id.
	 * 
	 * @return the prod id
	 */
	public Long getProdId() {
		return prodId;
	}

	/**
	 * Sets the prod id.
	 * 
	 * @param prodId
	 *            the new prod id
	 */
	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}

	/**
	 * Gets the rel prod id.
	 * 
	 * @return the rel prod id
	 */
	public Long getRelProdId() {
		return relProdId;
	}

	/**
	 * Sets the rel prod id.
	 * 
	 * @param relProdId
	 *            the new rel prod id
	 */
	public void setRelProdId(Long relProdId) {
		this.relProdId = relProdId;
	}

	/**
	 * Gets the rel prod name.
	 * 
	 * @return the rel prod name
	 */
	public String getRelProdName() {
		return relProdName;
	}

	/**
	 * Sets the rel prod name.
	 * 
	 * @param relProdName
	 *            the new rel prod name
	 */
	public void setRelProdName(String relProdName) {
		this.relProdName = relProdName;
	}

	/**
	 * Gets the addtime.
	 * 
	 * @return the addtime
	 */
	public Date getAddtime() {
		return addtime;
	}

	/**
	 * Sets the addtime.
	 * 
	 * @param addtime
	 *            the new addtime
	 */
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
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


}