package com.done.common;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import bingosoft.jcf.service.impl.ContextServiceLocator;
import bingosoft.jcf.sql.ConfigCode;
import bingosoft.jcf.util.EnvironmentConfig;
import bingosoft.jcf.util.page.PagerUtil;

public class InitSysListener implements ServletContextListener {
	private static Logger log = LoggerFactory.getLogger(InitSysListener.class);
    private String SEPARATOR = File.separator;
    private static boolean state = false;

    /**
     * 系统初始化
     */
    public void contextDestroyed(ServletContextEvent event) {
        //
    }

    public void contextInitialized(ServletContextEvent event) {
        System.out.println("********* Initialized LegendShop System now *********************");
        ConfigCode sQlCode = ConfigCode.getInstance("classpath*:DAL.cfg.xml");
        sQlCode.setDebug(true);
        PagerUtil.setPath(event.getServletContext().getContextPath());
        String defaultShop = EnvironmentConfig.getInstance().getPropertyValue("config/common.properties","DEFAULT_SHOP");
        String domainName =  EnvironmentConfig.getInstance().getPropertyValue("config/acegi-cas.properties", "cas.service.url");
        String photoPath = EnvironmentConfig.getInstance().getPropertyValue("fckeditor.properties", "connector.userFilesPath");
        String smallPhotoPath = EnvironmentConfig.getInstance().getPropertyValue("config/common.properties","connector.smallFilesPath");
        event.getServletContext().setAttribute("DEFAULT_SHOP", defaultShop);
        event.getServletContext().setAttribute("DOMAIN_NAME", domainName);
        event.getServletContext().setAttribute("PHOTO_PATH", photoPath);
        event.getServletContext().setAttribute("SMALL_PHOTO_PATH", smallPhotoPath);
        initspring(event);
        System.out.println("********* Initialized LegendShop System success *********************");
    }

    /*
     * 从Web上下文得到Spring
     */
    private boolean initspring(ServletContextEvent event) {
        if (state) {
            return state;
        }
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
        //ApplicationContext ctx =  new ClassPathXmlApplicationContext(event.getServletContext().getInitParameter("contextConfigLocation"));
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
