/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.model.entity;


/**
 * 广告对象.
 */
public class Advertisement extends UploadFile implements java.io.Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3118175679673767409L;

    /** The id. */
    private Long id;

    /** The pic url. */
    private String picUrl;

    /** The link url. */
    private String linkUrl;

    /** The user id. */
    private String userId;

    /** The user name. */
    private String userName;

    /** The enabled. */
    private Integer enabled;

    /** The type. */
    private String type;
    
    /** The source input. */
    private String sourceInput;
    
    /** The title. */
    private String title;

    /**
	 * Gets the source input.
	 * 
	 * @return the source input
	 */
    public String getSourceInput() {
		return sourceInput;
	}

	/**
	 * Sets the source input.
	 * 
	 * @param sourceInput
	 *            the new source input
	 */
	public void setSourceInput(String sourceInput) {
		this.sourceInput = sourceInput;
	}

	/**
	 * Gets the title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
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
	 * Instantiates a new advertisement.
	 */
	public Advertisement() {
    }

    /**
	 * Gets the id.
	 * 
	 * @return the id
	 */
    public Long getId() {
        return id;
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
	 * Gets the pic url.
	 * 
	 * @return the pic url
	 */
    public String getPicUrl() {
        return picUrl;
    }

    /**
	 * Sets the pic url.
	 * 
	 * @param picUrl
	 *            the new pic url
	 */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    /**
	 * Gets the link url.
	 * 
	 * @return the link url
	 */
    public String getLinkUrl() {
        return linkUrl;
    }

    /**
	 * Sets the link url.
	 * 
	 * @param linkUrl
	 *            the new link url
	 */
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    /**
	 * Gets the user id.
	 * 
	 * @return the user id
	 */
    public String getUserId() {
        return userId;
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
	 * Gets the enabled.
	 * 
	 * @return the enabled
	 */
    public Integer getEnabled() {
        return enabled;
    }

    /**
	 * Sets the enabled.
	 * 
	 * @param enabled
	 *            the new enabled
	 */
    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    /**
	 * Gets the type.
	 * 
	 * @return the type
	 */
    public String getType() {
        return type;
    }

    /**
	 * Sets the type.
	 * 
	 * @param type
	 *            the new type
	 */
    public void setType(String type) {
        this.type = type;
    }

}
