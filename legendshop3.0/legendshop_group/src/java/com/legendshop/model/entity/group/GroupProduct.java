/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.model.entity.group;

import java.util.Date;

import com.legendshop.model.entity.Product;

/**
 * The Class GroupProduct.
 * 团购产品
 */
public class GroupProduct{
	
	/** The group prod id. */
	private Long groupProdId;
	
	private Product product;
	
	/** The prod id. */
	private Long prodId;
	// for 团购
	/** The partner id. */
	private Long partnerId;

	/** 以购买成功人数成团，以产品购买数成团. */
	private String successCause;

	/** 购买次数限制 仅购买一次，可购买多次. */
	private String buyCondition;

	/** 虚拟购买数. */
	private Integer visualBuys;

	/** 每人限购. */
	private Integer maxBuys;

	/** The dvy type id.递送方式 */
	private Long dvyTypeId;

	/** 可使用代金券最大面额. */
	private Double maxMoney;
	
	/** 券开始有效期. */
	private Date couponStartTime;

	/** 券结束有效期. */
	private Date couponEndTime;

	/** 是否售完. */
	private Boolean soldOut;

	/** 最小成团数. */
	private Integer salesMin;

	/** 已经销售数量. */
	private Integer salesNum;

	/** 最大的销售数量，销售完不能再订购. */
	private Integer salesMax;

	/** 是否已经跟商家结算. */
	private Boolean isPost;

	
	public GroupProduct(){
		
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
	 * Gets the success cause.
	 * 
	 * @return the success cause
	 */
	public String getSuccessCause() {
		return successCause;
	}

	/**
	 * Sets the success cause.
	 * 
	 * @param successCause
	 *            the new success cause
	 */
	public void setSuccessCause(String successCause) {
		this.successCause = successCause;
	}

	/**
	 * Gets the buy condition.
	 * 
	 * @return the buy condition
	 */
	public String getBuyCondition() {
		return buyCondition;
	}

	/**
	 * Sets the buy condition.
	 * 
	 * @param buyCondition
	 *            the new buy condition
	 */
	public void setBuyCondition(String buyCondition) {
		this.buyCondition = buyCondition;
	}

	/**
	 * Gets the visual buys.
	 * 
	 * @return the visual buys
	 */
	public Integer getVisualBuys() {
		return visualBuys;
	}

	/**
	 * Sets the visual buys.
	 * 
	 * @param visualBuys
	 *            the new visual buys
	 */
	public void setVisualBuys(Integer visualBuys) {
		this.visualBuys = visualBuys;
	}

	/**
	 * Gets the max buys.
	 * 
	 * @return the max buys
	 */
	public Integer getMaxBuys() {
		return maxBuys;
	}

	/**
	 * Sets the max buys.
	 * 
	 * @param maxBuys
	 *            the new max buys
	 */
	public void setMaxBuys(Integer maxBuys) {
		this.maxBuys = maxBuys;
	}

	/**
	 * Gets the dvy type id.
	 * 
	 * @return the dvy type id
	 */
	public Long getDvyTypeId() {
		return dvyTypeId;
	}

	/**
	 * Sets the dvy type id.
	 * 
	 * @param dvyTypeId
	 *            the new dvy type id
	 */
	public void setDvyTypeId(Long dvyTypeId) {
		this.dvyTypeId = dvyTypeId;
	}

	/**
	 * Gets the max money.
	 * 
	 * @return the max money
	 */
	public Double getMaxMoney() {
		return maxMoney;
	}

	/**
	 * Sets the max money.
	 * 
	 * @param maxMoney
	 *            the new max money
	 */
	public void setMaxMoney(Double maxMoney) {
		this.maxMoney = maxMoney;
	}

	/**
	 * Gets the coupon start time.
	 * 
	 * @return the coupon start time
	 */
	public Date getCouponStartTime() {
		return couponStartTime;
	}

	/**
	 * Sets the coupon start time.
	 * 
	 * @param couponStartTime
	 *            the new coupon start time
	 */
	public void setCouponStartTime(Date couponStartTime) {
		this.couponStartTime = couponStartTime;
	}

	/**
	 * Gets the coupon end time.
	 * 
	 * @return the coupon end time
	 */
	public Date getCouponEndTime() {
		return couponEndTime;
	}

	/**
	 * Sets the coupon end time.
	 * 
	 * @param couponEndTime
	 *            the new coupon end time
	 */
	public void setCouponEndTime(Date couponEndTime) {
		this.couponEndTime = couponEndTime;
	}

	/**
	 * Gets the sold out.
	 * 
	 * @return the sold out
	 */
	public Boolean getSoldOut() {
		return soldOut;
	}

	/**
	 * Sets the sold out.
	 * 
	 * @param soldOut
	 *            the new sold out
	 */
	public void setSoldOut(Boolean soldOut) {
		this.soldOut = soldOut;
	}

	/**
	 * Gets the sales min.
	 * 
	 * @return the sales min
	 */
	public Integer getSalesMin() {
		return salesMin;
	}

	/**
	 * Sets the sales min.
	 * 
	 * @param salesMin
	 *            the new sales min
	 */
	public void setSalesMin(Integer salesMin) {
		this.salesMin = salesMin;
	}

	/**
	 * Gets the sales num.
	 * 
	 * @return the sales num
	 */
	public Integer getSalesNum() {
		return salesNum;
	}

	/**
	 * Sets the sales num.
	 * 
	 * @param salesNum
	 *            the new sales num
	 */
	public void setSalesNum(Integer salesNum) {
		this.salesNum = salesNum;
	}

	/**
	 * Gets the sales max.
	 * 
	 * @return the sales max
	 */
	public Integer getSalesMax() {
		return salesMax;
	}

	/**
	 * Sets the sales max.
	 * 
	 * @param salesMax
	 *            the new sales max
	 */
	public void setSalesMax(Integer salesMax) {
		this.salesMax = salesMax;
	}



	/**
	 * Gets the group prod id.
	 * 
	 * @return the group prod id
	 */
	public Long getGroupProdId() {
		return groupProdId;
	}

	/**
	 * Sets the group prod id.
	 * 
	 * @param groupProdId
	 *            the new group prod id
	 */
	public void setGroupProdId(Long groupProdId) {
		this.groupProdId = groupProdId;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.model.entity.Product#getProdId()
	 */
	public Long getProdId() {
		return prodId;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.model.entity.Product#setProdId(java.lang.Long)
	 */
	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setPost(boolean isPost) {
		this.isPost = isPost;
	}


	public Boolean getIsPost() {
		return isPost;
	}


	public void setIsPost(Boolean isPost) {
		this.isPost = isPost;
	}
}
