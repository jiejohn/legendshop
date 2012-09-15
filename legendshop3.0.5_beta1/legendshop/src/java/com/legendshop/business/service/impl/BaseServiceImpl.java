/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.legendshop.business.event.impl.VisitLogEvent;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.event.EventHome;
import com.legendshop.model.entity.ShopDetailView;
import com.legendshop.spi.constants.Constants;
import com.legendshop.spi.service.impl.AbstractService;
import com.legendshop.util.AppUtils;

/**
 * BaseServiceImpl.
 */
public abstract class BaseServiceImpl extends AbstractService {

	/** The log. */
	private final Logger log = LoggerFactory.getLogger(BaseServiceImpl.class);

	/** The java mail sender. */
	protected JavaMailSenderImpl javaMailSender;

	/** The thread pool executor. */
	protected ThreadPoolTaskExecutor threadPoolExecutor;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.service.impl.BaseService#setJavaMailSender(org
	 * .springframework.mail.javamail.JavaMailSenderImpl)
	 */
	/**
	 * Sets the java mail sender.
	 * 
	 * @param javaMailSender
	 *            the new java mail sender
	 */
	@Required
	public void setJavaMailSender(JavaMailSenderImpl javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.service.impl.BaseService#setThreadPoolExecutor
	 * (org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor)
	 */
	/**
	 * Sets the thread pool executor.
	 * 
	 * @param threadPoolExecutor
	 *            the new thread pool executor
	 */
	@Required
	public void setThreadPoolExecutor(ThreadPoolTaskExecutor threadPoolExecutor) {
		this.threadPoolExecutor = threadPoolExecutor;
	}


	/**
	 * @param request
	 * @param shopName
	 * @param userName
	 */
	protected void logUserAccess(HttpServletRequest request, String shopName, String userName) {
		// 多线程记录访问历史
		if (PropertiesUtil.getObject(ParameterEnum.VISIT_LOG_INDEX_ENABLE, Boolean.class)) {
			EventHome.publishEvent(new VisitLogEvent(request.getRemoteAddr(), shopName, userName));
		} else {
			if (log.isInfoEnabled()) {
				log.info("[{}],{} visit index {}", new Object[] { request.getRemoteAddr(), userName, shopName });
			}
		}
	}

}
