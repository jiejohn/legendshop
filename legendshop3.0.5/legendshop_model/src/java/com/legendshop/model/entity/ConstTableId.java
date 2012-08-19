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
public class ConstTableId implements java.io.Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4645320684663141427L;
	
	/** The key. */
	private String key;
	
	/** The type. */
	private String type;

	// Constructors

	/**
	 * default constructor.
	 */
	public ConstTableId() {
	}

	/**
	 * full constructor.
	 * 
	 * @param key
	 *            the key
	 * @param type
	 *            the type
	 */
	public ConstTableId(String key, String type) {
		this.key = key;
		this.type = type;
	}

	// Property accessors

	/**
	 * Gets the key.
	 * 
	 * @return the key
	 */
	public String getKey() {
		return this.key;
	}

	/**
	 * Sets the key.
	 * 
	 * @param key
	 *            the new key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public String getType() {
		return this.type;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ConstTableId))
			return false;
		ConstTableId castOther = (ConstTableId) other;

		return ((this.getKey() == castOther.getKey()) || (this.getKey() != null
				&& castOther.getKey() != null && this.getKey().equals(
				castOther.getKey())))
				&& ((this.getType() == castOther.getType()) || (this.getType() != null
						&& castOther.getType() != null && this.getType()
						.equals(castOther.getType())));
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getKey() == null ? 0 : this.getKey().hashCode());
		result = 37 * result
				+ (getType() == null ? 0 : this.getType().hashCode());
		return result;
	}

}