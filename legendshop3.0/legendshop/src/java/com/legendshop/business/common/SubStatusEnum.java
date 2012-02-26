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
 * --------- 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * --------
 * ----------------------------------------------------------------------
 * -------.
 */
public enum SubStatusEnum implements StringEnum {

	/** The ORDE r_ capture. */
	ORDER_CAPTURE("CA"), // 下订单

	/** The ORDE r_ del. */
 ORDER_DEL("DE"), // 删除订单

	/** The PRIC e_ change. */
 PRICE_CHANGE("PC"), // 改价格

	/** The CREDI t_ score. */
 CREDIT_SCORE("CS"), // 增加积分

	/** The DEBI t_ score. */
 DEBIT_SCORE("DS"), // 使用积分

	/** The ORDE r_ ove r_ time. */
 ORDER_OVER_TIME("OT"), // 超时

	/** The CHANG e_ status. */
 CHANGE_STATUS("ST"); // 订单状态变化

	/** The value. */
 private final String value;

	/**
	 * Instantiates a new sub status enum.
	 * 
	 * @param value
	 *            the value
	 */
	private SubStatusEnum(String value) {
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
		SubStatusEnum[] licenseEnums = values();
		for (SubStatusEnum licenseEnum : licenseEnums) {
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
		SubStatusEnum[] licenseEnums = values();
		for (SubStatusEnum licenseEnum : licenseEnums) {
			if (licenseEnum.name().equals(name)) {
				return licenseEnum.value();
			}
		}
		return null;
	}
}
