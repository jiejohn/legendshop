/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.timer;

import java.util.List;

import com.legendshop.business.common.SubForm;
import com.legendshop.model.entity.Sub;

/**
 * 订单服务接口.
 */
public interface SubService {

	/**
	 * 结束超时不付费的订单.
	 */
	public void finishUnPay();

	/**
	 * 结束超时不确认收货的订单.
	 */
	public void finishUnAcklodge();

	/**
	 * 移除已经过期的购物车.
	 */
	public void removeOverTimeBasket();

	/**
	 * Save sub.
	 * 
	 * @param form
	 *            the form
	 * @return the list
	 */
	public List<Sub> saveSub(SubForm form);
}
