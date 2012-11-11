/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.spi.constants;

import com.legendshop.core.constant.StringEnum;

/**
 * 系统参数类型
 */
public enum SysParamGroupEnum implements StringEnum {

	//系统参数配置
	SYS("sy"),
	
	//商城参数配置
	SHOP("sh"),
	
	//日志配置
	LOG("lo"),
	
	//邮件配置
	MAIL("ma");


	/** The value. */
 private final String value;

	/**
	 * Instantiates a new sub status enum.
	 * 
	 * @param value
	 *            the value
	 */
	private SysParamGroupEnum(String value) {
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
		SysParamGroupEnum[] licenseEnums = values();
		for (SysParamGroupEnum licenseEnum : licenseEnums) {
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
		SysParamGroupEnum[] licenseEnums = values();
		for (SysParamGroupEnum licenseEnum : licenseEnums) {
			if (licenseEnum.name().equals(name)) {
				return licenseEnum.value();
			}
		}
		return null;
	}
	
	public static String getName(String value) {
		SysParamGroupEnum[] licenseEnums = values();
		for (SysParamGroupEnum licenseEnum : licenseEnums) {
			if (licenseEnum.value().equals(value)) {
				return licenseEnum.name();
			}
		}
		return null;
	}
}
