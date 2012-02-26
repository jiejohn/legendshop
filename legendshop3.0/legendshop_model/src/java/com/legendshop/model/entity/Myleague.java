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
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public class Myleague implements java.io.Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7191239531193869658L;

	/** The id. */
	private Long id;

	/** The user id. */
	private String userId;

	/** The friend id. */
	private String friendId;

	/** The friend name. */
	private String friendName;

	/** The display order. */
	private Integer displayOrder;
	
	/** The banner. */
	private String banner;
	
	/** The province. */
	private String province;
	
	/** The city. */
	private String city;
	
	/** The area. */
	private String area;
	
	/**
	 * Instantiates a new myleague.
	 * 
	 * @param friendId
	 *            the friend id
	 * @param friendName
	 *            the friend name
	 * @param banner
	 *            the banner
	 */
	public Myleague(String friendId, String friendName, String banner) {
		this.friendId = friendId;
		this.friendName = friendName;
		this.banner = banner;
	}
	
	/**
	 * Instantiates a new myleague.
	 * 
	 * @param friendId
	 *            the friend id
	 * @param friendName
	 *            the friend name
	 * @param banner
	 *            the banner
	 * @param province
	 *            the province
	 * @param city
	 *            the city
	 * @param area
	 *            the area
	 */
	public Myleague(String friendId, String friendName, String banner,String province,String city,String area) {
		this.friendId = friendId;
		this.friendName = friendName;
		this.banner = banner;
		this.province = province;
		this.city = city;
		this.area = area;
	}

	/**
	 * Gets the banner.
	 * 
	 * @return the banner
	 */
	public String getBanner() {
		return banner;
	}

	/**
	 * Sets the banner.
	 * 
	 * @param banner
	 *            the new banner
	 */
	public void setBanner(String banner) {
		this.banner = banner;
	}

	/**
	 * Gets the display order.
	 * 
	 * @return the display order
	 */
	public Integer getDisplayOrder() {
		return displayOrder;
	}

	/**
	 * Sets the display order.
	 * 
	 * @param displayOrder
	 *            the new display order
	 */
	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	/** The status. */
	private Integer status;

	/** The addtime. */
	private Date addtime;

	// Constructors

	/**
	 * default constructor.
	 */
	public Myleague() {
	}

	/**
	 * minimal constructor.
	 * 
	 * @param id
	 *            the id
	 */
	public Myleague(Long id) {
		this.id = id;
	}

	/**
	 * full constructor.
	 * 
	 * @param id
	 *            the id
	 * @param userId
	 *            the user id
	 * @param friendId
	 *            the friend id
	 * @param friendName
	 *            the friend name
	 * @param status
	 *            the status
	 * @param addtime
	 *            the addtime
	 */
	public Myleague(Long id, String userId, String friendId,
			String friendName, Integer status, Date addtime) {
		this.id = id;
		this.userId = userId;
		this.friendId = friendId;
		this.friendName = friendName;
		this.status = status;
		this.addtime = addtime;
	}

	// Property accessors

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the user id.
	 * 
	 * @return the user id
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * Sets the user id.
	 * 
	 * @param userId
	 *            the new user id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Gets the friend id.
	 * 
	 * @return the friend id
	 */
	public String getFriendId() {
		return this.friendId;
	}

	/**
	 * Sets the friend id.
	 * 
	 * @param friendId
	 *            the new friend id
	 */
	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}

	/**
	 * Gets the friend name.
	 * 
	 * @return the friend name
	 */
	public String getFriendName() {
		return this.friendName;
	}

	/**
	 * Sets the friend name.
	 * 
	 * @param friendName
	 *            the new friend name
	 */
	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}

	/**
	 * Gets the status.
	 * 
	 * @return the status
	 */
	public Integer getStatus() {
		return this.status;
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
	 * Gets the addtime.
	 * 
	 * @return the addtime
	 */
	public Date getAddtime() {
		return this.addtime;
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
	 * Gets the province.
	 * 
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * Sets the province.
	 * 
	 * @param province
	 *            the new province
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * Gets the city.
	 * 
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 * 
	 * @param city
	 *            the new city
	 */
	public void setCity(String city) {
		this.city = city;
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

}