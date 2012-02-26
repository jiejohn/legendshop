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
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * ----------------------------------------------------------------------------
 * 
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * 
 * ----------------------------------------------------------------------------.
 */
public enum RoleEnum implements StringEnum {

	/** The ROL e_ supervisor. */
	ROLE_SUPERVISOR("1"),

	/** The ROL e_ admin. */
	ROLE_ADMIN("2"),

	/** The ROL e_ user. */
	ROLE_USER("3");

	/** The value. */
	private final String value;

	/**
	 * Instantiates a new role enum.
	 * 
	 * @param value
	 *            the value
	 */
	private RoleEnum(String value) {
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.core.constant.StringEnum#value()
	 */
	public String value() {
		return this.value;
	}

	/**
	 * Instance.
	 * 
	 * @param name
	 *            the name
	 * @return true, if successful
	 */
	public static boolean instance(String name) {
		RoleEnum[] licenseEnums = values();
		for (RoleEnum licenseEnum : licenseEnums) {
			if (licenseEnum.name().equals(name)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets the value.
	 * 
	 * @param name
	 *            the name
	 * @return the value
	 */
	public static String getValue(String name) {
		RoleEnum[] licenseEnums = values();
		for (RoleEnum licenseEnum : licenseEnums) {
			if (licenseEnum.name().equals(name)) {
				return licenseEnum.value();
			}
		}
		return null;
	}
}
