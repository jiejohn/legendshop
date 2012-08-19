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
public class UserRole implements BaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5680621301003712147L;
	
	/** The id. */
	private UserRoleId id;

	/**
	 * default constructor.
	 */
	public UserRole() {
	}

	/**
	 * full constructor.
	 * 
	 * @param id
	 *            the id
	 */
	public UserRole(UserRoleId id) {
		this.id = id;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public UserRoleId getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	public void setId(UserRoleId id) {
		this.id = id;
	}

}