/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.model.entity;

import java.util.Date;

/**
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * ----------------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------------
 * 
 * 官方网站：http://www.legendesign.net
 */

public class SubHistory implements java.io.Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8432484297847924412L;
	
	/** The sub hist id. */
	private Long subHistId;
	
	/** The sub id. */
	private Long subId;
	
	/** The basket id. */
	private String basketId;
	
	/** The prod name. */
	private String prodName;
	
	/** The user name. */
	private String userName;
	
	/** The order name. */
	private String orderName;
	
	/** The sub date. */
	private Date subDate;
	
	/** The pay date. */
	private Date payDate;
	
	/** The update date. */
	private Date updateDate;
	
	/** The sub number. */
	private String subNumber;
	
	/** The sub check. */
	private String subCheck;
	
	/** The total. */
	private Double total;
	// 实付
	/** The actual total. */
	private Double actualTotal;
	
	/** The sub mail. */
	private String subMail;
	
	/** The sub tel. */
	private String subTel;
	
	/** The pay id. */
	private Long payId;
	
	/** The pay type id. */
	private Integer payTypeId;
	
	/** The pay type name. */
	private String payTypeName;
	
	/** The sub adds. */
	private String subAdds;
	
	/** The sub post. */
	private String subPost;
	
	/** The other. */
	private String other;
	
	/** The shop name. */
	private String shopName;
	
	/** The status. */
	private Integer status;
	
	/** The score id. */
	private Long scoreId;
	
	/** The score. */
	private Long score;
	
	/** The update user. */
	private String updateUser;
	
	/** The update time. */
	private Date updateTime;
	
	/** The sub action. */
	private String subAction;
	// 支付宝交易号
	/** The trade no. */
	private String tradeNo;

	/**
	 * default constructor.
	 */
	public SubHistory() {
	}

	/**
	 * Gets the sub hist id.
	 * 
	 * @return the sub hist id
	 */
	public Long getSubHistId() {
		return subHistId;
	}

	/**
	 * Sets the sub hist id.
	 * 
	 * @param subHistId
	 *            the new sub hist id
	 */
	public void setSubHistId(Long subHistId) {
		this.subHistId = subHistId;
	}

	/**
	 * Gets the sub id.
	 * 
	 * @return the sub id
	 */
	public Long getSubId() {
		return subId;
	}

	/**
	 * Sets the sub id.
	 * 
	 * @param subId
	 *            the new sub id
	 */
	public void setSubId(Long subId) {
		this.subId = subId;
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
	 * Gets the prod name.
	 * 
	 * @return the prod name
	 */
	public String getProdName() {
		return prodName;
	}

	/**
	 * Sets the prod name.
	 * 
	 * @param prodName
	 *            the new prod name
	 */
	public void setProdName(String prodName) {
		this.prodName = prodName;
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
	 * Gets the sub date.
	 * 
	 * @return the sub date
	 */
	public Date getSubDate() {
		return subDate;
	}

	/**
	 * Sets the sub date.
	 * 
	 * @param subDate
	 *            the new sub date
	 */
	public void setSubDate(Date subDate) {
		this.subDate = subDate;
	}

	/**
	 * Gets the pay date.
	 * 
	 * @return the pay date
	 */
	public Date getPayDate() {
		return payDate;
	}

	/**
	 * Sets the pay date.
	 * 
	 * @param payDate
	 *            the new pay date
	 */
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	/**
	 * Gets the update date.
	 * 
	 * @return the update date
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * Sets the update date.
	 * 
	 * @param updateDate
	 *            the new update date
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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
	 * Gets the sub mail.
	 * 
	 * @return the sub mail
	 */
	public String getSubMail() {
		return subMail;
	}

	/**
	 * Sets the sub mail.
	 * 
	 * @param subMail
	 *            the new sub mail
	 */
	public void setSubMail(String subMail) {
		this.subMail = subMail;
	}

	/**
	 * Gets the sub tel.
	 * 
	 * @return the sub tel
	 */
	public String getSubTel() {
		return subTel;
	}

	/**
	 * Sets the sub tel.
	 * 
	 * @param subTel
	 *            the new sub tel
	 */
	public void setSubTel(String subTel) {
		this.subTel = subTel;
	}

	/**
	 * Gets the pay id.
	 * 
	 * @return the pay id
	 */
	public Long getPayId() {
		return payId;
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
	 * Gets the pay type id.
	 * 
	 * @return the pay type id
	 */
	public Integer getPayTypeId() {
		return payTypeId;
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
	 * Gets the update user.
	 * 
	 * @return the update user
	 */
	public String getUpdateUser() {
		return updateUser;
	}

	/**
	 * Sets the update user.
	 * 
	 * @param updateUser
	 *            the new update user
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	/**
	 * Gets the update time.
	 * 
	 * @return the update time
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * Sets the update time.
	 * 
	 * @param updateTime
	 *            the new update time
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * Gets the score id.
	 * 
	 * @return the score id
	 */
	public Long getScoreId() {
		return scoreId;
	}

	/**
	 * Sets the score id.
	 * 
	 * @param scoreId
	 *            the new score id
	 */
	public void setScoreId(Long scoreId) {
		this.scoreId = scoreId;
	}

	/**
	 * Gets the score.
	 * 
	 * @return the score
	 */
	public Long getScore() {
		return score;
	}

	/**
	 * Sets the score.
	 * 
	 * @param score
	 *            the new score
	 */
	public void setScore(Long score) {
		this.score = score;
	}

	/**
	 * Gets the actual total.
	 * 
	 * @return the actual total
	 */
	public Double getActualTotal() {
		return actualTotal;
	}

	/**
	 * Sets the actual total.
	 * 
	 * @param actualTotal
	 *            the new actual total
	 */
	public void setActualTotal(Double actualTotal) {
		this.actualTotal = actualTotal;
	}

	/**
	 * Gets the sub action.
	 * 
	 * @return the sub action
	 */
	public String getSubAction() {
		return subAction;
	}

	/**
	 * Sets the sub action.
	 * 
	 * @param subAction
	 *            the new sub action
	 */
	public void setSubAction(String subAction) {
		this.subAction = subAction;
	}

	/**
	 * Gets the trade no.
	 * 
	 * @return the trade no
	 */
	public String getTradeNo() {
		return tradeNo;
	}

	/**
	 * Sets the trade no.
	 * 
	 * @param tradeNo
	 *            the new trade no
	 */
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

}