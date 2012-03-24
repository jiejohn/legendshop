/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.common;

import com.legendshop.core.constant.IntegerEnum;


/**
 * use by Product Sub Sort
 * The Enum ProductTypeEnum.
 */
public enum ProductTypeEnum implements IntegerEnum {

	/** 普通商品订单 *. */
	PRODUCT(1),

	/** 团购商品订单. */
	GROUP(2),
	
	/** 二手商品订单 *. */
	SECOND_HAND(3),
	
	/** 打折商品 *. */
	DISCOUNT(4)
	;

	/** The num. */
	private Integer num;

	/* (non-Javadoc)
	 * @see com.legendshop.core.constant.IntegerEnum#value()
	 */
	@Override
	public Integer value() {
		return num;
	}

	/**
	 * Instantiates a new order status enum.
	 * 
	 * @param num
	 *            the num
	 */
	ProductTypeEnum(Integer num) {
		this.num = num;
	}

}
