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
public class Logo extends UploadFile implements java.io.Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8146764684614656689L;

	/** The id. = -1 用于占位*/
	private Long id;

	/** The banner. */
	private String banner;

	/** The url. */
	private String url;

	/** The user id. */
	private String userId;

	/** The user name. */
	private String userName;

	/** The memo. */
	private String memo;

	// Constructors

	/**
	 * default constructor.
	 */
	public Logo() {
	}

	/**
	 * minimal constructor.
	 * 
	 * @param id
	 *            the id
	 */
	public Logo(Long id) {
		this.id = id;
	}

	/**
	 * full constructor.
	 * 
	 * @param id
	 *            the id
	 * @param banner
	 *            the banner
	 * @param url
	 *            the url
	 * @param userId
	 *            the user id
	 * @param userName
	 *            the user name
	 */
	public Logo(Long id, String banner, String url, String userId,
			String userName) {
		this.id = id;
		this.banner = banner;
		this.url = url;
		this.userId = userId;
		this.userName = userName;
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
	 * Gets the banner.
	 * 
	 * @return the banner
	 */
	public String getBanner() {
		return this.banner;
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
	 * Gets the url.
	 * 
	 * @return the url
	 */
	public String getUrl() {
		return this.url;
	}

	/**
	 * Sets the url.
	 * 
	 * @param url
	 *            the new url
	 */
	public void setUrl(String url) {
		this.url = url;
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
	 * Gets the memo.
	 * 
	 * @return the memo
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * Sets the memo.
	 * 
	 * @param memo
	 *            the new memo
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

}