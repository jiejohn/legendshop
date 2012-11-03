/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.event.impl;

import com.legendshop.business.event.EventId;
import com.legendshop.core.security.model.UserDetail;
import com.legendshop.event.SystemEvent;

/**
 * 登录时触发的事件.
 */
public class LoginEvent extends SystemEvent<UserDetail>{

	/**
	 * Instantiates a new login event.
	 *
	 * @param userDetail the user detail
	 * @param ipAddress the ip address
	 */
	public LoginEvent(UserDetail userDetail, String ipAddress) {
		super(EventId.LOGIN_EVENT);
		userDetail.setIpAddress(ipAddress);
		this.setSource(userDetail);
	}
	

}
