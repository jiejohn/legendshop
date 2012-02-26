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
import com.legendshop.core.helper.Checker;

/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * ------------------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ------------------------------------------------------------------------------------
 * 
 * 官方网站：http://www.legendesign.net
 */
public class FunctionChecker implements Checker<String> {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(FunctionChecker.class);

	/* (non-Javadoc)
	 * @see com.legendshop.core.helper.Checker#check(java.lang.Object, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public boolean check(String userName, HttpServletRequest request) {
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
				return false;
			}
		}

		return true;
	}
}
