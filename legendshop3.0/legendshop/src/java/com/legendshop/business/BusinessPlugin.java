/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.legendshop.business.service.SystemParameterService;
import com.legendshop.central.license.BusinessModeEnum;
import com.legendshop.core.AttributeKeys;
import com.legendshop.core.constant.ConfigPropertiesEnum;
import com.legendshop.core.constant.LanguageEnum;
import com.legendshop.core.constant.RuntimeModeEnum;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.page.PagerUtil;
import com.legendshop.core.plugins.AbstractPlugin;
import com.legendshop.core.tag.TableCache;
import com.legendshop.event.EventHome;
import com.legendshop.search.SearchFacade;
import com.legendshop.util.FileConfig;

/**
 * The Class BusinessPlugin.
 */
public class BusinessPlugin extends AbstractPlugin{
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(BusinessPlugin.class);
	/** The system parameter service. */
	private SystemParameterService systemParameterService;

	/** The code tables cache. */
	private TableCache codeTablesCache;

	/** The search facade. */
	private SearchFacade searchFacade;
	
	/* (non-Javadoc)
	 * @see com.legendshop.core.plugins.Plugin#bind(javax.servlet.ServletContext)
	 */
	@Override
	public void bind(ServletContext servletContext) {
		// Load System Parameter
		systemParameterService.initSystemParameter();

		codeTablesCache.initCodeTablesCache();

		String luceneIndexPath = PropertiesUtil.getLucenePath();
		log.info("luceneIndexPath is {}", luceneIndexPath);

		searchFacade.init(luceneIndexPath);
		
		EventHome.initBaseEventListener();
		
		////////////
		PagerUtil.setPath(servletContext.getContextPath());

		servletContext.setAttribute(ConfigPropertiesEnum.CURRENCY_PATTERN.name(), PropertiesUtil.getCurrencyPattern());

		servletContext.setAttribute(AttributeKeys.DOMAIN_NAME, PropertiesUtil.getDomainName());

		servletContext.setAttribute("LEGENDSHOP_DOMAIN_NAME", AttributeKeys.LEGENDSHOP_DOMAIN_NAME);

		servletContext.setAttribute(ConfigPropertiesEnum.LEGENDSHOP_VERSION.name(), PropertiesUtil.getProperties(
				FileConfig.GlobalFile, ConfigPropertiesEnum.LEGENDSHOP_VERSION.name()));

		servletContext.setAttribute(AttributeKeys.BUSINESS_MODE, BusinessModeEnum.C2C.name());

		servletContext.setAttribute(AttributeKeys.RUNTIME_MODE, RuntimeModeEnum.PRODUCTION);

		servletContext.setAttribute(AttributeKeys.LANGUAGE_MODE, LanguageEnum.USERCHOICE);
		
	}

	/* (non-Javadoc)
	 * @see com.legendshop.core.plugins.Plugin#unbind(javax.servlet.ServletContext)
	 */
	@Override
	public void unbind(ServletContext servletContext) {
		// TODO Auto-generated method stub
		
	}

	

	/**
	 * Sets the system parameter service.
	 * 
	 * @param systemParameterServiceImpl
	 *            the new system parameter service
	 */
	public void setSystemParameterService(SystemParameterService systemParameterServiceImpl) {
		this.systemParameterService = systemParameterServiceImpl;
	}

	/**
	 * Sets the code tables cache.
	 * 
	 * @param codeTablesCache
	 *            the new code tables cache
	 */
	public void setCodeTablesCache(TableCache codeTablesCache) {
		this.codeTablesCache = codeTablesCache;
	}

	/**
	 * Sets the search facade.
	 * 
	 * @param searchFacade
	 *            the new search facade
	 */
	public void setSearchFacade(SearchFacade searchFacade) {
		this.searchFacade = searchFacade;
	}

}
