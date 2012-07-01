/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.business.dao.AdvertisementDao;
import com.legendshop.model.entity.Advertisement;
import com.legendshop.spi.constants.Constants;
import com.legendshop.spi.service.impl.AbstractService;
import com.legendshop.util.AppUtils;

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
	
	protected AdvertisementDao advertisementDao;

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


	public void setAdvertisement(String shopName, HttpServletRequest request) {
		Map<String, List<Advertisement>> advertisement = advertisementDao.getAdvertisement(shopName);
		if (!AppUtils.isBlank(advertisement)) {
			for (String type : advertisement.keySet()) {
				if (Constants.COUPLET.equals(type)) {
					List<Advertisement> list = advertisement.get(type);
					if (AppUtils.isNotBlank(list)) {
						request.setAttribute(type, list.get(CommonServiceUtil.random(list.size())));
					}
				} else {
					request.setAttribute(type, advertisement.get(type));
				}

			}
		}
	}


	public void setAdvertisementDao(AdvertisementDao advertisementDao) {
		this.advertisementDao = advertisementDao;
	}

}
