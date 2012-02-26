/**
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.common;

import com.legendshop.core.constant.IntegerEnum;

/**
 * 产品状态.
 */
public enum ProductStatusEnum implements IntegerEnum {
	// 1: 上线， 0： 下线
	/** The PRO d_ online. */
	PROD_ONLINE(1), 
	
	/** The PRO d_ offline. */
	PROD_OFFLINE(0);

	/** The num. */
	private Integer num;

	/* (non-Javadoc)
	 * @see com.legendshop.core.constant.IntegerEnum#value()
	 */
	public Integer value() {
		return num;
	}

	/**
	 * Instantiates a new product status enum.
	 * 
	 * @param num
	 *            the num
	 */
	ProductStatusEnum(Integer num) {
		this.num = num;
	}

}
