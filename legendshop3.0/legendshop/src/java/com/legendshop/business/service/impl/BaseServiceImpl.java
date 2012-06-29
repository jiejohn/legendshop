/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.LocaleResolver;

import com.legendshop.spi.service.impl.AbstractService;

/**
 * BaseServiceImpl.
 */
public abstract class BaseServiceImpl extends AbstractService {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(BaseServiceImpl.class);
	
	/** The java mail sender. */
	protected JavaMailSenderImpl javaMailSender;
	
	/** The thread pool executor. */
	protected ThreadPoolTaskExecutor threadPoolExecutor;
	
	/** The locale resolver. */
	protected LocaleResolver localeResolver;

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BaseService#setJavaMailSender(org.springframework.mail.javamail.JavaMailSenderImpl)
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

	
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BaseService#setThreadPoolExecutor(org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor)
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

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BaseService#setLocaleResolver(org.springframework.web.servlet.LocaleResolver)
	 */
	/**
	 * Sets the locale resolver.
	 * 
	 * @param localeResolver
	 *            the new locale resolver
	 */
	@Required
	public void setLocaleResolver(LocaleResolver localeResolver) {
		this.localeResolver = localeResolver;
	}

}
