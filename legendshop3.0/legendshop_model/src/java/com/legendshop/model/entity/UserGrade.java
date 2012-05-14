/**
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.model.entity;

import java.io.Serializable;

/**
 * 用户等级.
 */
public class UserGrade implements BaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2994159243674997206L;

	/** The grade id. */
	private Integer gradeId;

	/** The name. */
	private String name;

	/** The max sort size. */
	private Integer maxSortSize;

	/** The max nsort size. */
	private Integer maxNsortSize;

	/** The max prod. */
	private Integer maxProd;

	/**
	 * default constructor.
	 */
	public UserGrade() {
	}

	/**
	 * minimal constructor.
	 * 
	 * @param gradeId
	 *            the grade id
	 */
	public UserGrade(Integer gradeId) {
		this.gradeId = gradeId;
	}

	/**
	 * full constructor.
	 * 
	 * @param gradeId
	 *            the grade id
	 * @param name
	 *            the name
	 * @param maxSortSize
	 *            the max sort size
	 * @param maxNsortSize
	 *            the max nsort size
	 * @param maxProd
	 *            the max prod
	 */
	public UserGrade(Integer gradeId, String name, Integer maxSortSize,
			Integer maxNsortSize, Integer maxProd) {
		this.gradeId = gradeId;
		this.name = name;
		this.maxSortSize = maxSortSize;
		this.maxNsortSize = maxNsortSize;
		this.maxProd = maxProd;
	}

	// Property accessors

	/**
	 * Gets the grade id.
	 * 
	 * @return the grade id
	 */
	public Integer getGradeId() {
		return this.gradeId;
	}

	/**
	 * Sets the grade id.
	 * 
	 * @param gradeId
	 *            the new grade id
	 */
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
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
	 * Gets the max sort size.
	 * 
	 * @return the max sort size
	 */
	public Integer getMaxSortSize() {
		return this.maxSortSize;
	}

	/**
	 * Sets the max sort size.
	 * 
	 * @param maxSortSize
	 *            the new max sort size
	 */
	public void setMaxSortSize(Integer maxSortSize) {
		this.maxSortSize = maxSortSize;
	}

	/**
	 * Gets the max nsort size.
	 * 
	 * @return the max nsort size
	 */
	public Integer getMaxNsortSize() {
		return this.maxNsortSize;
	}

	/**
	 * Sets the max nsort size.
	 * 
	 * @param maxNsortSize
	 *            the new max nsort size
	 */
	public void setMaxNsortSize(Integer maxNsortSize) {
		this.maxNsortSize = maxNsortSize;
	}

	/**
	 * Gets the max prod.
	 * 
	 * @return the max prod
	 */
	public Integer getMaxProd() {
		return this.maxProd;
	}

	/**
	 * Sets the max prod.
	 * 
	 * @param maxProd
	 *            the new max prod
	 */
	public void setMaxProd(Integer maxProd) {
		this.maxProd = maxProd;
	}

	public Serializable getId() {
		return gradeId;
	}

}