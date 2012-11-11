/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.legendshop.business.event.impl.VisitLogEvent;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.event.EventHome;
import com.legendshop.spi.service.impl.AbstractService;

/**
 * BaseServiceImpl.
 */
public abstract class BaseServiceImpl extends AbstractService {

	/** The log. */
	private final Logger log = LoggerFactory.getLogger(BaseServiceImpl.class);

	/**
	 * @param request
	 * @param shopName
	 * @param userName
	 */
	protected void logUserAccess(HttpServletRequest request, String shopName, String userName) {
		// 多线程记录访问历史
		if (PropertiesUtil.getObject(SysParameterEnum.VISIT_LOG_INDEX_ENABLE, Boolean.class)) {
			EventHome.publishEvent(new VisitLogEvent(request.getRemoteAddr(), shopName, userName));
		} else {
			if (log.isInfoEnabled()) {
				log.info("[{}],{} visit index {}", new Object[] { request.getRemoteAddr(), userName, shopName });
			}
		}
	}

}
