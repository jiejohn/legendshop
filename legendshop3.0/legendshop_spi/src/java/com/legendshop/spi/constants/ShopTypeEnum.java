/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.spi.constants;

import com.legendshop.core.constant.IntegerEnum;

/**
 * 商城类型0：个人用户，1：商家用户
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public enum ShopTypeEnum implements IntegerEnum{
	
	/** The PERSONAL. */
	PERSONAL(0), 
	 /** The BUSINESS. */
	 BUSINESS(1);

	/** The num. */
	private Integer num;

	/* (non-Javadoc)
	 * @see com.legendshop.core.constant.IntegerEnum#value()
	 */
	public Integer value() {
		return num;
	}

	/**
	 * Instantiates a new shop type enum.
	 * 
	 * @param num
	 *            the num
	 */
	ShopTypeEnum(Integer num) {
		this.num = num;
	}

}
