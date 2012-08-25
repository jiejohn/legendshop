/*
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
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public class DownloadLog implements BaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5134672215418217047L;

	/** The dl id. */
	private Long dlId;

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

	/** The file name. */
	private String fileName;

	/** The date. */
	private Date date;

	/**
	 * Instantiates a new download log.
	 */
	public DownloadLog() {
	}

	/**
	 * Gets the dl id.
	 * 
	 * @return the dl id
	 */
	public Long getDlId() {
		return dlId;
	}

	/**
	 * Sets the dl id.
	 * 
	 * @param dlId
	 *            the new dl id
	 */
	public void setDlId(Long dlId) {
		this.dlId = dlId;
	}

	/**
	 * Gets the ip.
	 * 
	 * @return the ip
	 */
	public String getIp() {
		return ip;
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
	 * Gets the file name.
	 * 
	 * @return the file name
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sets the file name.
	 * 
	 * @param fileName
	 *            the new file name
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Gets the date.
	 * 
	 * @return the date
	 */
	public Date getDate() {
		return date;
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
	 * Gets the country.
	 * 
	 * @return the country
	 */
	public String getCountry() {
		return country;
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
		return area;
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

	public Serializable getId() {
		return dlId;
	}

}
