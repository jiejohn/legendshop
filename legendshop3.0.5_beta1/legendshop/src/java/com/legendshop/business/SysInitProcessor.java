/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.legendshop.business.service.SystemParameterService;
import com.legendshop.core.AttributeKeys;
import com.legendshop.core.constant.BusinessModeEnum;
import com.legendshop.core.constant.ConfigPropertiesEnum;
import com.legendshop.core.constant.LanguageEnum;
import com.legendshop.core.constant.RuntimeModeEnum;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.page.PagerUtil;
import com.legendshop.core.tag.TableCache;
import com.legendshop.event.processor.BaseProcessor;
import com.legendshop.search.SearchFacade;
import com.legendshop.util.FileConfig;

/**
 * 系统初始化事件 eventId: SYS_INIT.
 */
public class SysInitProcessor extends BaseProcessor<ServletContextEvent> {
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(SysInitProcessor.class);
	/** The system parameter service. */
	private SystemParameterService systemParameterService;

	/** The code tables cache. */
	private TableCache codeTablesCache;

	/** The search facade. */
	private SearchFacade searchFacade;

	/* (non-Javadoc)
	 * @see com.legendshop.event.processor.AbstractProcessor#process(java.lang.Object)
	 */
	@Override
	public void process(ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();
		// Load System Parameter
		systemParameterService.initSystemParameter();

		codeTablesCache.initCodeTablesCache();

		String luceneIndexPath = PropertiesUtil.getLucenePath();
		log.info("luceneIndexPath is {}", luceneIndexPath);

		searchFacade.init(luceneIndexPath);

		PagerUtil.setPath(servletContext.getContextPath());

		servletContext.setAttribute(ConfigPropertiesEnum.CURRENCY_PATTERN.name(), PropertiesUtil.getCurrencyPattern());

		servletContext.setAttribute(AttributeKeys.DOMAIN_NAME, PropertiesUtil.getDomainName());

		servletContext.setAttribute("LEGENDSHOP_DOMAIN_NAME", AttributeKeys.LEGENDSHOP_DOMAIN_NAME);

		servletContext.setAttribute(ConfigPropertiesEnum.LEGENDSHOP_VERSION.name(),
				PropertiesUtil.getProperties(FileConfig.GlobalFile, ConfigPropertiesEnum.LEGENDSHOP_VERSION.name()));

		// 业务模式
		String businessMode = PropertiesUtil.getProperties(FileConfig.GlobalFile,
				ConfigPropertiesEnum.BUSINESS_MODE.name());
		if (BusinessModeEnum.C2C.name().equals(businessMode)) {
			servletContext.setAttribute(AttributeKeys.BUSINESS_MODE, BusinessModeEnum.C2C.name());
		} else {
			servletContext.setAttribute(AttributeKeys.BUSINESS_MODE, BusinessModeEnum.B2C.name());
		}

		// 业务模式
		String languageMode = PropertiesUtil.getProperties(FileConfig.GlobalFile,
				ConfigPropertiesEnum.LANGUAGE_MODE.name());
		if (LanguageEnum.ENGLISH.equals(languageMode)) {
			servletContext.setAttribute(AttributeKeys.LANGUAGE_MODE, LanguageEnum.USERCHOICE);
		} else if (LanguageEnum.CHINESE.equals(languageMode)) {
			servletContext.setAttribute(AttributeKeys.LANGUAGE_MODE, LanguageEnum.CHINESE);
		} else {
			servletContext.setAttribute(AttributeKeys.LANGUAGE_MODE, LanguageEnum.USERCHOICE);
		}

		servletContext.setAttribute(AttributeKeys.RUNTIME_MODE, RuntimeModeEnum.PRODUCTION);
	}

	/**
	 * Sets the system parameter service.
	 *
	 * @param systemParameterService the new system parameter service
	 */
	public void setSystemParameterService(SystemParameterService systemParameterService) {
		this.systemParameterService = systemParameterService;
	}

	/**
	 * Sets the code tables cache.
	 *
	 * @param codeTablesCache the new code tables cache
	 */
	public void setCodeTablesCache(TableCache codeTablesCache) {
		this.codeTablesCache = codeTablesCache;
	}

	/**
	 * Sets the search facade.
	 *
	 * @param searchFacade the new search facade
	 */
	public void setSearchFacade(SearchFacade searchFacade) {
		this.searchFacade = searchFacade;
	}

}
