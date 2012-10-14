/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.process.event;

import com.legendshop.business.service.LoginHistoryService;
import com.legendshop.core.security.model.UserDetail;
import com.legendshop.event.processor.ThreadProcessor;

/**
 * 用户登录操作
 */
public class LoginHistoryProcessor extends ThreadProcessor<UserDetail> {
	
	private LoginHistoryService loginHistoryService;

	public boolean isSupport(UserDetail userDetail) {
		return true;
	}

	@Override
	public void process(UserDetail userDetail) {
		loginHistoryService.saveLoginHistory(userDetail.getUsername(),userDetail.getIpAddress());
	}
	
	public void setLoginHistoryService(LoginHistoryService loginHistoryService) {
		this.loginHistoryService = loginHistoryService;
	}
	
}
