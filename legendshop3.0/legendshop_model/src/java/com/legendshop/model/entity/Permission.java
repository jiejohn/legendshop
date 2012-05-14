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
public class Permission implements BaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6636728502142356224L;
	
	/** The id. */
	private PerssionId id;

	// Constructors

	/**
	 * default constructor.
	 */
	public Permission() {
	}

	/**
	 * full constructor.
	 * 
	 * @param id
	 *            the id
	 */
	public Permission(PerssionId id) {
		this.id = id;
	}

	// Property accessors

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public PerssionId getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	public void setId(PerssionId id) {
		this.id = id;
	}

}