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
public class ConstTable implements java.io.Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8272145517063923481L;
	
	/** The id. */
	private ConstTableId id;
	
	/** The value. */
	private String value;
	
	/** The seq. */
	private Integer seq;

	/**
	 * Gets the seq.
	 * 
	 * @return the seq
	 */
	public Integer getSeq() {
		return seq;
	}

	/**
	 * Sets the seq.
	 * 
	 * @param seq
	 *            the new seq
	 */
	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	/**
	 * default constructor.
	 */
	public ConstTable() {
	}

	/**
	 * minimal constructor.
	 * 
	 * @param id
	 *            the id
	 */
	public ConstTable(ConstTableId id) {
		this.id = id;
	}

	/**
	 * full constructor.
	 * 
	 * @param id
	 *            the id
	 * @param value
	 *            the value
	 */
	public ConstTable(ConstTableId id, String value) {
		this.id = id;
		this.value = value;
	}

	// Property accessors

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public ConstTableId getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	public void setId(ConstTableId id) {
		this.id = id;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * Sets the value.
	 * 
	 * @param value
	 *            the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}

}