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

public class NsortBrandId implements java.io.Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8233057942953041238L;
	
	/** The brand id. */
	private Long brandId;
	
	/** The nsort id. */
	private Long nsortId;

	// Constructors

	/**
	 * default constructor.
	 */
	public NsortBrandId() {
	}

	/**
	 * full constructor.
	 * 
	 * @param brandId
	 *            the brand id
	 * @param nsortId
	 *            the nsort id
	 */
	public NsortBrandId(Long brandId, Long nsortId) {
		this.brandId = brandId;
		this.nsortId = nsortId;
	}

	// Property accessors

	/**
	 * Gets the brand id.
	 * 
	 * @return the brand id
	 */
	public Long getBrandId() {
		return this.brandId;
	}

	/**
	 * Sets the brand id.
	 * 
	 * @param brandId
	 *            the new brand id
	 */
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	/**
	 * Gets the nsort id.
	 * 
	 * @return the nsort id
	 */
	public Long getNsortId() {
		return this.nsortId;
	}

	/**
	 * Sets the nsort id.
	 * 
	 * @param nsortId
	 *            the new nsort id
	 */
	public void setNsortId(Long nsortId) {
		this.nsortId = nsortId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof NsortBrandId))
			return false;
		NsortBrandId castOther = (NsortBrandId) other;

		return ((this.getBrandId() == castOther.getBrandId()) || (this
				.getBrandId() != null
				&& castOther.getBrandId() != null && this.getBrandId().equals(
				castOther.getBrandId())))
				&& ((this.getNsortId() == castOther.getNsortId()) || (this
						.getNsortId() != null
						&& castOther.getNsortId() != null && this.getNsortId()
						.equals(castOther.getNsortId())));
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getBrandId() == null ? 0 : this.getBrandId().hashCode());
		result = 37 * result
				+ (getNsortId() == null ? 0 : this.getNsortId().hashCode());
		return result;
	}

}