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
 * use by Product Sub Sort
 * The Enum ProductTypeEnum.
 */
public enum ProductTypeEnum implements StringEnum {

	/** 普通商品订单 *. */
	PRODUCT("P"),

	/** 团购商品订单. */
	GROUP("G"),
	
	/** 二手商品订单 *. */
	SECOND_HAND("S"),
	
	/** 打折商品 *. */
	DISCOUNT("D")
	;

	/** The num. */
	private String value;

	/* (non-Javadoc)
	 * @see com.legendshop.core.constant.IntegerEnum#value()
	 */
	@Override
	public String value() {
		return value;
	}

	/**
	 * Instantiates a new order status enum.
	 * 
	 * @param value
	 *            the value
	 */
	ProductTypeEnum(String value) {
		this.value = value;
	}

}
