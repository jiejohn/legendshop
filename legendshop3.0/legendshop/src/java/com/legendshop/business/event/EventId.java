/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.event;

import com.legendshop.event.BaseEventId;

/**
 * The Interface BaseEventId.
 */
public enum EventId implements BaseEventId {
	USER_REG_EVENT("USER_REG"),
	
	SEND_MAIL_EVENT("SEND_MAIL"),
	
	SEND_SMS_EVENT("SEND_SMS"),
	
	ORDER_CHANGE_EVENT("ORDER_CHANGE"),
	
	SHOP_CHANGE_EVENT("SHOP_CHANGE"),
	
	PROD_CHANGE_EVENT("PROD_CHANGE"),
	
	
	//是否可以增加商店
	CAN_ADD_SHOPDETAIL_EVENT("CAN_ADD_SHOPDETAIL")
	
	;
	
	 
	/** The value. */
	private final String value;
	@Override
	public String getEventId() {
		return this.value;
	}
	
	private EventId(String value){
		this.value = value;
	}
	
	@Override
	public boolean instance(String name) {
		EventId[] eventIds = values();
		for (EventId eventId : eventIds) {
			if (eventId.name().equals(name)) {
				return true;
			}
		}
		return false;
	}

	

}
