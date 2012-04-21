/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.legendshop.core.AttributeKeys;
import com.legendshop.core.StartupService;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.RealPathUtil;
import com.legendshop.util.AppUtils;
import com.legendshop.util.ContextServiceLocator;
import com.legendshop.util.sql.ConfigCode;

/**
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * 官方网站：http://www.legendesign.net
 * 
 * @see InitSysEvent
 */
public class InitSysListener implements ServletContextListener {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(InitSysListener.class);
	
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
		//
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		log.info("********* Initializing LegendShop System now *************");
		// 1. 初始化系统真实路径
		String realPath = RealPathUtil.getSystemRealPath(event.getServletContext());
		if(realPath != null && !realPath.endsWith("/")){
			realPath = realPath +"/";
		}
		PropertiesUtil.setSystemRealPath(realPath);

		// 2.Spring
		initspring(event);

		// 3.ConfigCode
		ConfigCode sQlCode = ConfigCode.getInstance("classpath*:DAL.cfg.xml");
		boolean debugMode = PropertiesUtil.isSystemInDebugMode();
		log.debug("System in DEBUG MODE is {}", debugMode);
		sQlCode.setDebug(debugMode);

		// ShopSystemId，必须先更改再reload配置文件
		if (AppUtils.isBlank(PropertiesUtil.getLegendShopSystemId())) {
			PropertiesUtil.changeLegendShopSystemId();
		}
		
		event.getServletContext().setAttribute("WEB_SUFFIX",AttributeKeys.WEB_SUFFIX);
		
		// 4.系统初始化
		if (PropertiesUtil.isSystemInstalled()) {
			StartupService startupService = (StartupService) ContextServiceLocator.getInstance().getBean(
					"startupService");
			startupService.startup(event.getServletContext());
		}

		log.info("*********  LegendShop System Initialized successful **********");
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
		// ApplicationContext ctx =
		// WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext());
		// ApplicationContext ctx = new
		// ClassPathXmlApplicationContext(event.getServletContext().getInitParameter("contextConfigLocation"));
		if (ctx == null) {
			state = false;
		} else {
			state = true;
		}
		String[] beans = ctx.getBeanDefinitionNames();
		StringBuffer sb = new StringBuffer("[ ");
		if (!AppUtils.isBlank(beans)) {
			for (String bean : beans) {
				sb.append(bean).append(" ");
			}
			sb.append(" ]");
			log.debug("系统配置的Spring Bean " + sb.toString());
		}
		ContextServiceLocator.getInstance().setContext(ctx);
		return state;
	}

}
