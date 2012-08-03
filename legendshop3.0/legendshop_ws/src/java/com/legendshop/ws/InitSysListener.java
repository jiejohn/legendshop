/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.ws;

import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.legendshop.util.ContextServiceLocator;
import com.legendshop.util.sql.ConfigCode;

/**
 * 系统初始化.
 * 
 * @author hewq
 */
public class InitSysListener extends org.springframework.web.context.ContextLoaderListener {
	
	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(InitSysListener.class);
	
	/** The state. */
	private static boolean state = false;

	/**
	 * 系统初始化.
	 * 
	 * @param event
	 *            the event
	 */
	@Override
	public void contextDestroyed(ServletContextEvent event) {

	}

	/* (non-Javadoc)
	 * @see org.springframework.web.context.ContextLoaderListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		log.info("********* Initializing System now *********************");

		//super.contextInitialized(event);
		ConfigCode configCode = ConfigCode.getInstance();
		// configCode.init("Parameter.cfg.xml");
		configCode.setDebug(false);
		// 初始化Spring
		boolean result = initspring(event);
		// 初始化系统的SQL语句。
		log.info("init spring {}", result ? "成功" : "失败");

		log.info("********* Initialized System success *********************");
	}

	/*
	 * 从Web上下文得到Spring
	 */
	/**
	 * Initspring.
	 * 
	 * @param event
	 *            the event
	 * @return true, if successful
	 */
	private boolean initspring(ServletContextEvent event) {
		if (state) {
			return state;
		}
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
//		 ApplicationContext ctx = new
//		 ClassPathXmlApplicationContext(event.getServletContext().getInitParameter("contextConfigLocation"));

		if (ctx == null) {
			state = false;
		} else {
			state = true;
		}
		ContextServiceLocator.getInstance().setContext(ctx);

		System.out.println("ContextServiceLocator = " + ContextServiceLocator.getInstance());
		System.out.println("ContextServiceLocator context = " + ContextServiceLocator.getInstance().getContext());
		return state;
	}


}
