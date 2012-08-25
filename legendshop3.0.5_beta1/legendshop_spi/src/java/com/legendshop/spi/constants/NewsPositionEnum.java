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
 * 新闻状态
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------.
 */
public enum NewsPositionEnum implements IntegerEnum {

	// 普通新闻
	/** The NEW s_ news. */
	NEWS_NEWS(1),

	// 顶部新闻
	/** The NEW s_ top. */
	NEWS_TOP(0),

	// 分类新闻
	/** The NEW s_ sort. */
	NEWS_SORT(3),

	// 底部新闻
	/** The NEW s_ bottom. */
	NEWS_BOTTOM(4),
	
	//团购上部新闻
	NEWS_GROUP_TOP(2),
	
	//团购下部新闻
	NEWS_GROUP_BOTTOM(5)
	;

	/** The num. */
	private Integer num;

	/* (non-Javadoc)
	 * @see com.legendshop.core.constant.IntegerEnum#value()
	 */
	public Integer value() {
		return num;
	}

	/**
	 * Instantiates a new news category status enum.
	 * 
	 * @param num
	 *            the num
	 */
	NewsPositionEnum(Integer num) {
		this.num = num;
	}

}
