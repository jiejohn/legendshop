/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.common;

import com.legendshop.core.constant.StringEnum;

/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * -----------------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * -----------------------------------------------------------------------------------
 * 
 * 官方网站：http://www.legendesign.net
 */
public enum RegisterEnum implements StringEnum {
	
	/** The REGISTE r_ success. */
	REGISTER_SUCCESS("reg.success.actived"),

	/** The REGISTE r_ n o_ use r_ found. */
	REGISTER_NO_USER_FOUND("user.isNotExist"),

	/** The REGISTE r_ cod e_ no t_ match. */
	REGISTER_CODE_NOT_MATCH("error.image.validation"),

	/** The REGISTE r_ fail. */
	REGISTER_FAIL("reg.fail.actived");

	/** The value. */
	private final String value;

	/**
	 * Instantiates a new register enum.
	 * 
	 * @param value
	 *            the value
	 */
	private RegisterEnum(String value) {
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.core.constant.StringEnum#value()
	 */
	public String value() {
		return this.value;
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		System.out.println(REGISTER_SUCCESS.value);
	}

}
