/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.permission.form;

import com.legendshop.model.entity.Function;
/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public class FunctionForm extends BaseValidatorForm {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7048928553222685963L;
	
	/** The function. */
	public Function function = new Function();

	/**
	 * Gets the function.
	 * 
	 * @return the function
	 */
	public Function getFunction() {
		return function;
	}

	/**
	 * Sets the function.
	 * 
	 * @param function
	 *            the new function
	 */
	public void setFunction(Function function) {
		this.function = function;
	}
}