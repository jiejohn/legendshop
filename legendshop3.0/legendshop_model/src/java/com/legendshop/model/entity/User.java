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
 * 
 * 官方网站：http://www.legendesign.net
 */
public class User implements java.io.Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5151325646123051299L;

	/** The id. */
	private String id;

	/** The name. */
	private String name;

	/** The password. */
	private String password;
	
    /** The passwordag. */
    private String passwordag;

	/** The enabled. */
	private String enabled;

	/** The note. */
	private String note;

	/**
	 * default constructor.
	 */
	public User() {
	}

	/**
	 * minimal constructor.
	 * 
	 * @param id
	 *            the id
	 * @param name
	 *            the name
	 * @param password
	 *            the password
	 * @param enabled
	 *            the enabled
	 */
	public User(String id, String name, String password, String enabled) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.enabled = enabled;
	}

	/**
	 * full constructor.
	 * 
	 * @param id
	 *            the id
	 * @param name
	 *            the name
	 * @param password
	 *            the password
	 * @param enabled
	 *            the enabled
	 * @param note
	 *            the note
	 */
	public User(String id, String name, String password, String enabled,
			String note) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.enabled = enabled;
		this.note = note;
	}

	// Property accessors

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return this.name;
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

	/**
	 * Gets the password.
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Sets the password.
	 * 
	 * @param password
	 *            the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the enabled.
	 * 
	 * @return the enabled
	 */
	public String getEnabled() {
		return this.enabled;
	}

	/**
	 * Sets the enabled.
	 * 
	 * @param enabled
	 *            the new enabled
	 */
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	/**
	 * Gets the note.
	 * 
	 * @return the note
	 */
	public String getNote() {
		return this.note;
	}

	/**
	 * Sets the note.
	 * 
	 * @param note
	 *            the new note
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * Gets the passwordag.
	 * 
	 * @return the passwordag
	 */
	public String getPasswordag() {
		return passwordag;
	}

	/**
	 * Sets the passwordag.
	 * 
	 * @param passwordag
	 *            the new passwordag
	 */
	public void setPasswordag(String passwordag) {
		this.passwordag = passwordag;
	}

}