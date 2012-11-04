/**
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.model.entity;

/**
 * 产品动态属性模板.
 */
public class DynamicTemp implements BaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8083024182117947985L;
	
	/** The id. */
	private Long id;
	
	/** The name. */
	private String name;
	
	/** The content. */
	private String content;
	
	/** The type. */
	private Short type;
	
	/** The user name. */
	private String userName;

	// Constructors

	/**
	 * default constructor.
	 */
	public DynamicTemp() {
	}

	/**
	 * full constructor.
	 * 
	 * @param content
	 *            the content
	 * @param type
	 *            the type
	 * @param userName
	 *            the user name
	 */
	public DynamicTemp(String content, Short type, String userName) {
		this.content = content;
		this.type = type;
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
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public Short getType() {
		return this.type;
	}

	/**
	 * Sets the type.
	 * 
	 * @param type
	 *            the new type
	 */
	public void setType(Short type) {
		this.type = type;
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
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

}