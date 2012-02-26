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
public class ExternalLink extends UploadFile implements java.io.Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5467294318649826405L;

	/** The id. */
	private Long id;

	/** The url. */
	private String url;

	/** The wordlink. */
	private String wordlink;

	/** The content. */
	private String content;

	/** The bs. */
	private Integer bs;

	/** The user id. */
	private String userId;

	/** The user name. */
	private String userName;
	
	/** The picture. */
	private String picture;
	
	/**
	 * Instantiates a new external link.
	 */
	public ExternalLink() {
	}

	/**
	 * Instantiates a new external link.
	 * 
	 * @param id
	 *            the id
	 */
	public ExternalLink(Long id) {
		this.id = id;
	}

	/**
	 * full constructor.
	 * 
	 * @param id
	 *            the id
	 * @param url
	 *            the url
	 * @param wordlink
	 *            the wordlink
	 * @param content
	 *            the content
	 * @param bs
	 *            the bs
	 * @param userId
	 *            the user id
	 * @param userName
	 *            the user name
	 */
	public ExternalLink(Long id, String url, String wordlink, String content,
			Integer bs, String userId, String userName) {
		this.id = id;
		this.url = url;
		this.wordlink = wordlink;
		this.content = content;
		this.bs = bs;
		this.userId = userId;
		this.userName = userName;
	}

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
	 * Gets the wordlink.
	 * 
	 * @return the wordlink
	 */
	public String getWordlink() {
		return this.wordlink;
	}

	/**
	 * Sets the wordlink.
	 * 
	 * @param wordlink
	 *            the new wordlink
	 */
	public void setWordlink(String wordlink) {
		this.wordlink = wordlink;
	}

	/**
	 * Gets the content.
	 * 
	 * @return the content
	 */
	public String getContent() {
		return this.content;
	}

	/**
	 * Sets the content.
	 * 
	 * @param content
	 *            the new content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Gets the bs.
	 * 
	 * @return the bs
	 */
	public Integer getBs() {
		return this.bs;
	}

	/**
	 * Sets the bs.
	 * 
	 * @param bs
	 *            the new bs
	 */
	public void setBs(Integer bs) {
		this.bs = bs;
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
	 * Gets the picture.
	 * 
	 * @return the picture
	 */
	public String getPicture() {
		return picture;
	}

	/**
	 * Sets the picture.
	 * 
	 * @param picture
	 *            the new picture
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}

}