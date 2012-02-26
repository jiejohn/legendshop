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
public class PerssionId implements java.io.Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5510312063680243453L;
	
	/** The role id. */
	private String roleId;
	
	/** The function id. */
	private String functionId;

	// Constructors

	/**
	 * default constructor.
	 */
	public PerssionId() {
	}

	/**
	 * full constructor.
	 * 
	 * @param roleId
	 *            the role id
	 * @param functionId
	 *            the function id
	 */
	public PerssionId(String roleId, String functionId) {
		this.roleId = roleId;
		this.functionId = functionId;
	}

	// Property accessors

	/**
	 * Gets the role id.
	 * 
	 * @return the role id
	 */
	public String getRoleId() {
		return this.roleId;
	}

	/**
	 * Sets the role id.
	 * 
	 * @param roleId
	 *            the new role id
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * Gets the function id.
	 * 
	 * @return the function id
	 */
	public String getFunctionId() {
		return this.functionId;
	}

	/**
	 * Sets the function id.
	 * 
	 * @param functionId
	 *            the new function id
	 */
	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PerssionId))
			return false;
		PerssionId castOther = (PerssionId) other;

		return ((this.getRoleId() == castOther.getRoleId()) || (this
				.getRoleId() != null
				&& castOther.getRoleId() != null && this.getRoleId().equals(
				castOther.getRoleId())))
				&& ((this.getFunctionId() == castOther.getFunctionId()) || (this
						.getFunctionId() != null
						&& castOther.getFunctionId() != null && this
						.getFunctionId().equals(castOther.getFunctionId())));
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getRoleId() == null ? 0 : this.getRoleId().hashCode());
		result = 37
				* result
				+ (getFunctionId() == null ? 0 : this.getFunctionId()
						.hashCode());
		return result;
	}

}