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
public class Function  implements BaseEntity {

	 /** The Constant serialVersionUID. */
 	private static final long serialVersionUID = -3353517712968544688L;   
     
     /** The id. */
     private String id;
     
     /** The name. */
     private String name;
     
     /** The url. */
     private String url;
     
     /** The parent name. */
     private String parentName;
     
     /** The protect function. */
     private String protectFunction;
     
     /** The note. */
     private String note;
     
     /** The roles. */
     private List<Role> roles = new ArrayList<Role>();
	
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
	 * Gets the url.
	 * 
	 * @return the url
	 */
	public String getUrl() {
		return url;
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
	 * Gets the protect function.
	 * 
	 * @return the protect function
	 */
	public String getProtectFunction() {
		return protectFunction;
	}
	
	/**
	 * Sets the protect function.
	 * 
	 * @param protectFunction
	 *            the new protect function
	 */
	public void setProtectFunction(String protectFunction) {
		this.protectFunction = protectFunction;
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
	 * Gets the parent name.
	 * 
	 * @return the parent name
	 */
	public String getParentName() {
		return parentName;
	}
	
	/**
	 * Sets the parent name.
	 * 
	 * @param parentName
	 *            the new parent name
	 */
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

}