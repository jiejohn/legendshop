/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public class UserEntity  implements java.io.Serializable {
	 
 	/** The Constant serialVersionUID. */
 	private static final long serialVersionUID = -8401714272377570649L;
     
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
     
     /** The roles. */
     private List<Role> roles = new ArrayList<Role>();
     
     /** The functions. */
     private List<Function> functions = new ArrayList<Function>();
	
	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public String getId() {
		return id;
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
	
	/**
	 * Gets the password.
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
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
	
	/**
	 * Gets the enabled.
	 * 
	 * @return the enabled
	 */
	public String getEnabled() {
		return enabled;
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
		return note;
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
	 * Gets the roles.
	 * 
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
	}
	
	/**
	 * Sets the roles.
	 * 
	 * @param roles
	 *            the new roles
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	/**
	 * Gets the functions.
	 * 
	 * @return the functions
	 */
	public List<Function> getFunctions() {
		return functions;
	}
	
	/**
	 * Sets the functions.
	 * 
	 * @param functions
	 *            the new functions
	 */
	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}


}