/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.common;

import com.legendshop.core.constant.IntegerEnum;
import com.legendshop.model.entity.PayType;

/**
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * ----------------------------------------------------------------------------
 * -------- 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * --------
 * ----------------------------------------------------------------------------.
 */
public enum PayTypeEnum implements IntegerEnum {
	// 1: 支付宝直接转账/银行转账,2:支付宝担保转账,3:货到付款
	/** The AL i_ direc t_ pay. */
	ALI_DIRECT_PAY(1),

	/** The AL i_ pay. */
	ALI_PAY(2),

	/** The PA y_ a t_ good s_ arrived. */
	PAY_AT_GOODS_ARRIVED(3);

	/** The num. */
	private Integer num;

	/* (non-Javadoc)
	 * @see com.legendshop.core.constant.IntegerEnum#value()
	 */
	public Integer value() {
		return num;
	}

	/**
	 * Instantiates a new pay type enum.
	 * 
	 * @param num
	 *            the num
	 */
	PayTypeEnum(Integer num) {
		this.num = num;
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {

	}

	/**
	 * Checks if is alipay.
	 * 
	 * @param payType
	 *            the pay type
	 * @return true, if is alipay
	 */
	public static boolean isAlipay(PayType payType) {
		if (payType == null) {
			return false;
		}
		if (ALI_DIRECT_PAY.value().equals(payType.getPayTypeId()) || ALI_PAY.value().equals(payType.getPayTypeId())) {
			return true;
		} else {
			return false;
		}

	}
}
