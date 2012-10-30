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
import com.legendshop.model.entity.SystemParameter;

/**
 * The Class SysParamUpdateEvent.
 */
public class SysParamUpdateEvent extends SystemEvent<SystemParameter>{


	/**
	 * Instantiates a new sys param update event.
	 *
	 * @param source the source
	 */
	public SysParamUpdateEvent(SystemParameter source) {
		super(source, EventId.SYS_PARAM_EVENT);
	}

}
