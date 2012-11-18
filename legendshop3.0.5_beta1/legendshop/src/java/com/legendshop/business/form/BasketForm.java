/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.form;

import java.util.Date;


/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public class BasketForm{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5076753905519824021L;

	/** The count. */
	private Integer count;
    
    /** The carriage. */
    private Double carriage;//运费
    
    /** The attribute. */
    private String attribute; //商品属性
    
	/** The basket id. */
	private Long basketId;

	/** The prod id. */
	private Long prodId;
	
	/** The pic. */
	private String pic;

	/** The user name. */
	private String userName;

	/** The basket count. */
	private Integer basketCount;

	/** The basket date. */
	private Date basketDate;

	/** The basket check. */
	private String basketCheck;

	/** The prod name. */
	private String prodName;

	/** The cash. */
	private Double cash;

	/** The sub number. */
	private String subNumber;

	/** The daili. */
	private String daili;

	/**
	 * default constructor.
	 */
	public BasketForm() {
	}

	/**
	 * minimal constructor.
	 * 
	 * @param basketId
	 *            the basket id
	 */
	public BasketForm(Long basketId) {
		this.basketId = basketId;
	}


	/**
	 * Gets the basket id.
	 * 
	 * @return the basket id
	 */
	public Long getBasketId() {
		return this.basketId;
	}

	/**
	 * Sets the basket id.
	 * 
	 * @param basketId
	 *            the new basket id
	 */
	public void setBasketId(Long basketId) {
		this.basketId = basketId;
	}

	/**
	 * Gets the prod id.
	 * 
	 * @return the prod id
	 */
	public Long getProdId() {
		return this.prodId;
	}

	/**
	 * Sets the prod id.
	 * 
	 * @param hwId
	 *            the new prod id
	 */
	public void setProdId(Long hwId) {
		this.prodId = hwId;
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
	 * Gets the basket count.
	 * 
	 * @return the basket count
	 */
	public Integer getBasketCount() {
		return this.basketCount;
	}

	/**
	 * Sets the basket count.
	 * 
	 * @param basketCount
	 *            the new basket count
	 */
	public void setBasketCount(Integer basketCount) {
		this.basketCount = basketCount;
	}

	/**
	 * Gets the basket date.
	 * 
	 * @return the basket date
	 */
	public Date getBasketDate() {
		return this.basketDate;
	}

	/**
	 * Sets the basket date.
	 * 
	 * @param basketDate
	 *            the new basket date
	 */
	public void setBasketDate(Date basketDate) {
		this.basketDate = basketDate;
	}

	/**
	 * Gets the basket check.
	 * 
	 * @return the basket check
	 */
	public String getBasketCheck() {
		return this.basketCheck;
	}

	/**
	 * Sets the basket check.
	 * 
	 * @param basketCheck
	 *            the new basket check
	 */
	public void setBasketCheck(String basketCheck) {
		this.basketCheck = basketCheck;
	}

	/**
	 * Gets the prod name.
	 * 
	 * @return the prod name
	 */
	public String getProdName() {
		return this.prodName;
	}

	/**
	 * Sets the prod name.
	 * 
	 * @param hwName
	 *            the new prod name
	 */
	public void setProdName(String hwName) {
		this.prodName = hwName;
	}

	/**
	 * Gets the cash.
	 * 
	 * @return the cash
	 */
	public Double getCash() {
		return this.cash;
	}

	/**
	 * Sets the cash.
	 * 
	 * @param hwCash
	 *            the new cash
	 */
	public void setCash(Double hwCash) {
		this.cash = hwCash;
	}

	/**
	 * Gets the sub number.
	 * 
	 * @return the sub number
	 */
	public String getSubNumber() {
		return this.subNumber;
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
	 * Gets the daili.
	 * 
	 * @return the daili
	 */
	public String getDaili() {
		return this.daili;
	}

	/**
	 * Sets the daili.
	 * 
	 * @param daili
	 *            the new daili
	 */
	public void setDaili(String daili) {
		this.daili = daili;
	}

	/**
	 * Gets the count.
	 * 
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * Sets the count.
	 * 
	 * @param count
	 *            the new count
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * Gets the carriage.
	 * 
	 * @return the carriage
	 */
	public Double getCarriage() {
		return carriage;
	}

	/**
	 * Sets the carriage.
	 * 
	 * @param carriage
	 *            the new carriage
	 */
	public void setCarriage(Double carriage) {
		this.carriage = carriage;
	}

	/**
	 * Gets the attribute.
	 * 
	 * @return the attribute
	 */
	public String getAttribute() {
		return attribute;
	}

	/**
	 * Sets the attribute.
	 * 
	 * @param attribute
	 *            the new attribute
	 */
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	/**
	 * Gets the pic.
	 * 
	 * @return the pic
	 */
	public String getPic() {
		return pic;
	}

	/**
	 * Sets the pic.
	 * 
	 * @param hwPic
	 *            the new pic
	 */
	public void setPic(String hwPic) {
		this.pic = hwPic;
	}

}