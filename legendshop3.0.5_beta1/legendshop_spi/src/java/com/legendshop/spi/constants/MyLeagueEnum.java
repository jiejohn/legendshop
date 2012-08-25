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
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public enum MyLeagueEnum implements IntegerEnum{
	
	/** The ONGOING. */
	ONGOING(0), 
 /** The AGREE. */
 AGREE(1), 
 /** The DENY. */
 DENY(2), 
 /** The NONE. */
 NONE(3),
/** The DONE. */
DONE(4),
/** The THESAME. */
THESAME(5), 
 /** The ERROR. */
 ERROR(6);

	/** The num. */
	private Integer num;

	/* (non-Javadoc)
	 * @see com.legendshop.core.constant.IntegerEnum#value()
	 */
	public Integer value() {
		return num;
	}

	/**
	 * Instantiates a new my league enum.
	 * 
	 * @param num
	 *            the num
	 */
	MyLeagueEnum(Integer num) {
		this.num = num;
	}
}
