/**
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.model.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户访问历史对象.
 */

public class VisitLog implements BaseEntity{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6138702495183168329L;
	
	/** The visit id. */
	private Long visitId;
	
	/** The ip. */
	private String ip;
	
	/** The country. */
	private String country;
	
	/** The area. */
	private String area;
	
	/** The user name. */
	private String userName;
	
	/** The shop name. */
	private String shopName;
	
	/** The product name. */
	private String productName;
	
	/** The product id. */
	private String productId;
	
	/** The page. */
	private String page;
	
	/** The date. */
	private Date date;
	
	/** The visit num. */
	private Integer visitNum;

	/** The start time. */
	private Date startTime;

	/** The end time. */
	private Date endTime;

	/**
	 * Gets the start time.
	 * 
	 * @return the start time
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * Sets the start time.
	 * 
	 * @param startTime
	 *            the new start time
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * Gets the end time.
	 * 
	 * @return the end time
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * Sets the end time.
	 * 
	 * @param endTime
	 *            the new end time
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * Instantiates a new visit log.
	 */
	public VisitLog() {
	}

	/**
	 * Instantiates a new visit log.
	 * 
	 * @param ip
	 *            the ip
	 * @param page
	 *            the page
	 * @param date
	 *            the date
	 */
	public VisitLog(String ip, String page, Date date) {
		this.ip = ip;
		this.page = page;
		this.date = date;
	}

	/**
	 * full constructor.
	 * 
	 * @param ip
	 *            the ip
	 * @param country
	 *            the country
	 * @param area
	 *            the area
	 * @param userName
	 *            the user name
	 * @param shopName
	 *            the shop name
	 * @param productName
	 *            the product name
	 * @param page
	 *            the page
	 * @param date
	 *            the date
	 */
	public VisitLog(String ip, String country, String area, String userName,
			String shopName, String productName, String page, Date date) {
		this.ip = ip;
		this.country = country;
		this.area = area;
		this.userName = userName;
		this.shopName = shopName;
		this.productName = productName;
		this.page = page;
		this.date = date;
	}

	/**
	 * Gets the visit id.
	 * 
	 * @return the visit id
	 */
	public Long getVisitId() {
		return this.visitId;
	}

	/**
	 * Sets the visit id.
	 * 
	 * @param visitId
	 *            the new visit id
	 */
	public void setVisitId(Long visitId) {
		this.visitId = visitId;
	}

	/**
	 * Gets the ip.
	 * 
	 * @return the ip
	 */
	public String getIp() {
		return this.ip;
	}

	/**
	 * Sets the ip.
	 * 
	 * @param ip
	 *            the new ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * Gets the country.
	 * 
	 * @return the country
	 */
	public String getCountry() {
		return this.country;
	}

	/**
	 * Sets the country.
	 * 
	 * @param country
	 *            the new country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Gets the area.
	 * 
	 * @return the area
	 */
	public String getArea() {
		return this.area;
	}

	/**
	 * Sets the area.
	 * 
	 * @param area
	 *            the new area
	 */
	public void setArea(String area) {
		this.area = area;
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
	 * Gets the shop name.
	 * 
	 * @return the shop name
	 */
	public String getShopName() {
		return this.shopName;
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
	 * Gets the product name.
	 * 
	 * @return the product name
	 */
	public String getProductName() {
		return this.productName;
	}

	/**
	 * Sets the product name.
	 * 
	 * @param productName
	 *            the new product name
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * Gets the page.
	 * 
	 * @return the page
	 */
	public String getPage() {
		return this.page;
	}

	/**
	 * Sets the page.
	 * 
	 * @param page
	 *            the new page
	 */
	public void setPage(String page) {
		this.page = page;
	}

	/**
	 * Gets the date.
	 * 
	 * @return the date
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * Sets the date.
	 * 
	 * @param date
	 *            the new date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Gets the product id.
	 * 
	 * @return the product id
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * Sets the product id.
	 * 
	 * @param productId
	 *            the new product id
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * Gets the visit num.
	 * 
	 * @return the visit num
	 */
	public Integer getVisitNum() {
		return visitNum;
	}

	/**
	 * Sets the visit num.
	 * 
	 * @param visitNum
	 *            the new visit num
	 */
	public void setVisitNum(Integer visitNum) {
		this.visitNum = visitNum;
	}

	public Serializable getId() {
		return visitId;
	}

}