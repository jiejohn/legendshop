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
 * 商城状态
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------.
 */
public enum ShopStatusEnum implements IntegerEnum {
	// 在线
	/** The SHO p_ online. */
	ONLINE(1),

	//同意
	/** The AGREE. */
	AGREE(1),
	// 下线
	/** The SHO p_ offline. */
	OFFLINE(0),

	// 审核中
	/** The SHO p_ auditing. */
	AUDITING(-1),

	// 审核失败
	/** The SHO p_ reject. */
	REJECT(-2),
	
	//管理员关闭
	/** The CLOSE. */
	CLOSE(-3);

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
	 * Instantiates a new shop status enum.
	 * 
	 * @param num
	 *            the num
	 */
	ShopStatusEnum(Integer num) {
		this.num = num;
	}

}
