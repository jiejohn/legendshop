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

import com.legendshop.central.license.LSResponse;
import com.legendshop.central.license.LicenseEnum;
import com.legendshop.central.license.LicenseHelper;
import com.legendshop.core.AttributeKeys;
import com.legendshop.core.exception.ErrorCodes;
import com.legendshop.core.exception.PermissionException;
import com.legendshop.core.helper.Checker;
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
		boolean result = true;
		LSResponse resopose = (LSResponse) request.getSession().getServletContext().getAttribute(AttributeKeys.LEGENSHOP_LICENSE);
		if (resopose == null) {
			try {
				resopose = LicenseHelper.getPersistedResopnse();
			} catch (Exception e) {
			}
		}
		if (resopose != null) {
			String license = resopose.getLicense();
			if (LicenseEnum.FREE.name().equals(license) || LicenseEnum.UNKNOWN.name().equals(license)) {
				log.debug("user name = {} did not have function on this componment", userName);
				//TODO				
				result = false;
			}
		}
		
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
