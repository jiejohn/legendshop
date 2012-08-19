/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.event.impl;

import com.legendshop.business.event.EventId;
import com.legendshop.event.SystemEvent;
import com.legendshop.model.entity.Sub;
/**
 * 保存订单事件
 *
 */
public class OrderSaveEvent extends SystemEvent<Sub>{

	/**
	 * 参数是订单对象
	 */
	public OrderSaveEvent(Sub source) {
		super(source, EventId.ORDER_SAVE_EVENT);
	}

}
