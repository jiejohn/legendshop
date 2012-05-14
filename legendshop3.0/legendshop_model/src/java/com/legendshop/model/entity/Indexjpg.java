/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.model.entity;

/**
 * 首页轮换图片.
 */
public class Indexjpg extends UploadFile implements BaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1888032358468727806L;

	/** The id. */
	private Long id;

	/** The href. */
	private String href;

	/** The img. */
	private String img;

	/** The alt. */
	private String alt;

	/** The title. */
	private String title;

	/** The stitle. */
	private String stitle;

	/** The link. */
	private String link;

	/** The title link. */
	private String titleLink;

	/** The user id. */
	private String userId;

	/** The user name. */
	private String userName;

	// Constructors

	/**
	 * default constructor.
	 */
	public Indexjpg() {
	}

	/**
	 * minimal constructor.
	 * 
	 * @param id
	 *            the id
	 * @param href
	 *            the href
	 * @param img
	 *            the img
	 * @param alt
	 *            the alt
	 * @param title
	 *            the title
	 * @param stitle
	 *            the stitle
	 * @param link
	 *            the link
	 * @param titleLink
	 *            the title link
	 * @param userName
	 *            the user name
	 */
	public Indexjpg(Long id, String href, String img, String alt,
			String title, String stitle, String link, String titleLink,
			String userName) {
		this.id = id;
		this.href = href;
		this.img = img;
		this.alt = alt;
		this.title = title;
		this.stitle = stitle;
		this.link = link;
		this.titleLink = titleLink;
		this.userName = userName;
	}

	/**
	 * full constructor.
	 * 
	 * @param id
	 *            the id
	 * @param href
	 *            the href
	 * @param img
	 *            the img
	 * @param alt
	 *            the alt
	 * @param title
	 *            the title
	 * @param stitle
	 *            the stitle
	 * @param link
	 *            the link
	 * @param titleLink
	 *            the title link
	 * @param userId
	 *            the user id
	 * @param userName
	 *            the user name
	 */
	public Indexjpg(Long id, String href, String img, String alt,
			String title, String stitle, String link, String titleLink,
			String userId, String userName) {
		this.id = id;
		this.href = href;
		this.img = img;
		this.alt = alt;
		this.title = title;
		this.stitle = stitle;
		this.link = link;
		this.titleLink = titleLink;
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
	 * Gets the href.
	 * 
	 * @return the href
	 */
	public String getHref() {
		return this.href;
	}

	/**
	 * Sets the href.
	 * 
	 * @param href
	 *            the new href
	 */
	public void setHref(String href) {
		this.href = href;
	}

	/**
	 * Gets the img.
	 * 
	 * @return the img
	 */
	public String getImg() {
		return this.img;
	}

	/**
	 * Sets the img.
	 * 
	 * @param img
	 *            the new img
	 */
	public void setImg(String img) {
		this.img = img;
	}

	/**
	 * Gets the alt.
	 * 
	 * @return the alt
	 */
	public String getAlt() {
		return this.alt;
	}

	/**
	 * Sets the alt.
	 * 
	 * @param alt
	 *            the new alt
	 */
	public void setAlt(String alt) {
		this.alt = alt;
	}

	/**
	 * Gets the title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Sets the title.
	 * 
	 * @param title
	 *            the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the stitle.
	 * 
	 * @return the stitle
	 */
	public String getStitle() {
		return this.stitle;
	}

	/**
	 * Sets the stitle.
	 * 
	 * @param stitle
	 *            the new stitle
	 */
	public void setStitle(String stitle) {
		this.stitle = stitle;
	}

	/**
	 * Gets the link.
	 * 
	 * @return the link
	 */
	public String getLink() {
		return this.link;
	}

	/**
	 * Sets the link.
	 * 
	 * @param link
	 *            the new link
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * Gets the title link.
	 * 
	 * @return the title link
	 */
	public String getTitleLink() {
		return this.titleLink;
	}

	/**
	 * Sets the title link.
	 * 
	 * @param titleLink
	 *            the new title link
	 */
	public void setTitleLink(String titleLink) {
		this.titleLink = titleLink;
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

}