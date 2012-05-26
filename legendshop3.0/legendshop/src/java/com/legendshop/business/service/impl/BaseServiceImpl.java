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
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.LocaleResolver;

import com.legendshop.business.service.BaseService;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.ProductDetail;
import com.legendshop.model.entity.ShopDetailView;
import com.legendshop.model.visit.VisitHistory;
import com.legendshop.spi.constants.Constants;

/**
 * BaseServiceImpl.
 */
public abstract class BaseServiceImpl implements BaseService {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(BaseServiceImpl.class);
	
	/** The java mail sender. */
	protected JavaMailSenderImpl javaMailSender;
	
	/** The thread pool executor. */
	protected ThreadPoolTaskExecutor threadPoolExecutor;
	
	/** The locale resolver. */
	protected LocaleResolver localeResolver;

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BaseService#getSessionAttribute(javax.servlet.http.HttpServletRequest, java.lang.String)
	 */
	@Override
	public Object getSessionAttribute(HttpServletRequest request, String name) {
		Object obj = null;
		HttpSession session = request.getSession(false);
		if (session != null) {
			obj = session.getAttribute(name);
		}
		return obj;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BaseService#getShopName(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String getShopName(HttpServletRequest request, HttpServletResponse response) {
		return (String) getSessionAttribute(request, Constants.SHOP_NAME);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BaseService#setSessionAttribute(javax.servlet.http.HttpServletRequest, java.lang.String, java.lang.Object)
	 */
	@Override
	public void setSessionAttribute(HttpServletRequest request, String name, Object obj) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.setAttribute(name, obj);
		}
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BaseService#setShopName(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.String)
	 */
	@Override
	public void setShopName(HttpServletRequest request, HttpServletResponse response, String shopName) {
		// Session
		String name = getShopName(request,response);
		if(name == null  || !name.equals(shopName)){
			setSessionAttribute(request, Constants.SHOP_NAME, shopName);
		}
	
		// Cookie
		// Cookie cookie = new Cookie(Constants.SHOP_NAME, shopName);
		// //生命周期
		// cookie.setMaxAge(60 * 60 * 24 * 365);
		// cookie.setPath("/");
		// response.addCookie(cookie);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BaseService#convertStringToInteger(java.lang.String)
	 */
	@Override
	public Long convertStringToInteger(String id) {
		try {
			Long result = Long.valueOf(id);
			if (result == 0) {
				return null;
			} else {
				return result;
			}
		} catch (Exception e) {
			log.error("can not convert id " + id);
			return null;
		}
	}

	/**
	 * Visit.
	 * 
	 * @param prod
	 *            the prod
	 * @param request
	 *            the request
	 */
	protected void visit(ProductDetail prod, HttpServletRequest request) {
		VisitHistory visitHistory = (VisitHistory) request.getSession().getAttribute(Constants.VISIT_HISTORY);
		if (visitHistory == null) {
			visitHistory = new VisitHistory();
		}
		visitHistory.visit(prod);
		request.getSession().setAttribute(Constants.VISIT_HISTORY, visitHistory);
	}

	/**
	 * Visit.
	 * 
	 * @param shopDetail
	 *            the shop detail
	 * @param request
	 *            the request
	 */
	public void visit(ShopDetailView shopDetail, HttpServletRequest request) {
		VisitHistory visitHistory = (VisitHistory) request.getSession().getAttribute(Constants.VISIT_HISTORY);
		if (visitHistory == null) {
			visitHistory = new VisitHistory();
		}
		visitHistory.visit(shopDetail);
		request.getSession().setAttribute(Constants.VISIT_HISTORY, visitHistory);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BaseService#setJavaMailSender(org.springframework.mail.javamail.JavaMailSenderImpl)
	 */
	@Required
	public void setJavaMailSender(JavaMailSenderImpl javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	
	/**
	 * Send mail.
	 * 
	 * @return true, if successful
	 */
	protected boolean sendMail() {
		return PropertiesUtil.getObject(ParameterEnum.SEND_MAIL, Boolean.class);
	}
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BaseService#setThreadPoolExecutor(org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor)
	 */
	@Required
	public void setThreadPoolExecutor(ThreadPoolTaskExecutor threadPoolExecutor) {
		this.threadPoolExecutor = threadPoolExecutor;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BaseService#setLocaleResolver(org.springframework.web.servlet.LocaleResolver)
	 */
	@Required
	public void setLocaleResolver(LocaleResolver localeResolver) {
		this.localeResolver = localeResolver;
	}

}
