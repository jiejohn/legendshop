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

public class SendMailEvent extends SystemEvent<String>{

	/**
	 * Instantiates a new user reg event.
	 * 
	 * @param source
	 *            the source
	 */
	public SendMailEvent(String source) {
		super(source, EventId.SEND_MAIL_EVENT);
	}

}
