/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.model.entity;



/**
 * 代表一个商城详细信息
 */
public class ShopDetailView extends AbstractShopDetail{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8763595849099410286L;

	/** The province. */
	private String province;
	
	/** The city. */
	private String city;
	
	/** The area. */
	private String area;

	// //// user detail
	/** The nick name. */
	private String nickName;

	/** The user mobile. */
	private String userMobile;

	/** The bankCard number. */
	private String msnNumber;

	/** The qq. */
	private String qq;

	/** The fax. */
	private String fax;

	/**
	 * default constructor.
	 */
    public ShopDetailView() {
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



	/**
	 * Gets the nick name.
	 * 
	 * @return the nick name
	 */
	public String getNickName() {
		return nickName;
	}



	/**
	 * Sets the nick name.
	 * 
	 * @param nickName
	 *            the new nick name
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}



	/**
	 * Gets the user mobile.
	 * 
	 * @return the user mobile
	 */
	public String getUserMobile() {
		return userMobile;
	}



	/**
	 * Sets the user mobile.
	 * 
	 * @param userMobile
	 *            the new user mobile
	 */
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}



	/**
	 * Gets the msn number.
	 * 
	 * @return the msn number
	 */
	public String getMsnNumber() {
		return msnNumber;
	}



	/**
	 * Sets the msn number.
	 * 
	 * @param msnNumber
	 *            the new msn number
	 */
	public void setMsnNumber(String msnNumber) {
		this.msnNumber = msnNumber;
	}



	/**
	 * Gets the qq.
	 * 
	 * @return the qq
	 */
	public String getQq() {
		return qq;
	}



	/**
	 * Sets the qq.
	 * 
	 * @param qq
	 *            the new qq
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}



	/**
	 * Gets the fax.
	 * 
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}



	/**
	 * Sets the fax.
	 * 
	 * @param fax
	 *            the new fax
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

}