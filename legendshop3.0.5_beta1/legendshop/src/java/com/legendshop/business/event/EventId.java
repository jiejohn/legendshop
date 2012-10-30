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
 * 系统事件ID
 */
public enum EventId implements BaseEventId {
	//用户注册
	USER_REG_EVENT("USER_REG"),
	
	//发送邮件
	SEND_MAIL_EVENT("SEND_MAIL"),
	
	//发送短消息
	SEND_SMS_EVENT("SEND_SMS"),
	
	//登录
	LOGIN_EVENT("LOGIN_EVENT"),
	
	//商城系统更改
	SHOP_CHANGE_EVENT("SHOP_CHANGE"),
	
	//产品更改
	PROD_CHANGE_EVENT("PROD_CHANGE"),
	
	//用户查看历史
	VISIT_LOG_EVENT("VISIT_LOG"),
	
	
	//是否可以增加商店
	CAN_ADD_SHOPDETAIL_EVENT("CAN_ADD_SHOPDETAIL"), 
	
	//下订单事件
	ORDER_SAVE_EVENT("ORDER_SAVE"),
	
		//取消订单
	ORDER_CHANGE_EVENT("ORDER_CHANGE"),
	
	//更新系统配置
	SYS_PARAM_EVENT("SYS_PARAM")
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
