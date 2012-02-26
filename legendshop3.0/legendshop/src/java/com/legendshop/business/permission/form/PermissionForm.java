/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.permission.form;

/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public class PermissionForm extends BaseValidatorForm {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6137922551383167345L;
	
	/** The role id. */
	private String roleId;
    
    /** The function id. */
    private String functionId;

	/**
	 * Gets the function id.
	 * 
	 * @return the function id
	 */
	public String getFunctionId() {
		return functionId;
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

	/**
	 * Gets the role id.
	 * 
	 * @return the role id
	 */
	public String getRoleId() {
		return roleId;
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

}