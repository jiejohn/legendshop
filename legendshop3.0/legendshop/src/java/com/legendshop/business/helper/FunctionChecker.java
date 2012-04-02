/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.helper;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.legendshop.business.event.EventId;
import com.legendshop.core.exception.ErrorCodes;
import com.legendshop.core.exception.PermissionException;
import com.legendshop.core.helper.Checker;
import com.legendshop.event.EventContext;
import com.legendshop.event.EventHome;
import com.legendshop.event.GenericEvent;
import com.legendshop.model.UserMessages;

/**
 * 
 * 系统权限Checker
 */
public class FunctionChecker implements Checker<String> {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(FunctionChecker.class);

	/* (non-Javadoc)
	 * @see com.legendshop.core.helper.Checker#check(java.lang.Object, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public boolean check(String userName, HttpServletRequest request) {
		EventContext eventContext = new EventContext(request);
		eventContext.setRequest(userName);
		EventHome.publishEvent(new GenericEvent(eventContext,EventId.FUNCTION_CHECK_EVENT));
		Boolean result = eventContext.getBooleanResponse();
		if(!result){
			UserMessages uem = new UserMessages();
			uem.setTitle("免费版不提供该功能");
			uem.setCode(ErrorCodes.UN_AUTHORIZATION);

			request.setAttribute(UserMessages.MESSAGE_KEY, uem);
			throw new PermissionException("UN_AUTHORIZATION", ErrorCodes.UN_AUTHORIZATION);
		}

		return result;
	}
}
