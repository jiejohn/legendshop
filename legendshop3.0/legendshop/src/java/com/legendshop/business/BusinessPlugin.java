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
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.plugins.AbstractPlugin;
import com.legendshop.core.tag.TableCache;
import com.legendshop.search.SearchFacade;

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
