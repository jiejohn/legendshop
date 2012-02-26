/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.legendshop.business.service.SystemParameterService;
import com.legendshop.central.HealthCheckerHolder;
import com.legendshop.central.install.StartupService;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.tag.TableCache;
import com.legendshop.search.SearchFacade;

/**
 * 系统启动时初始化
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 
 * 官方网站：http://www.legendesign.net
 * 
 * ----------------------------------------------------------------------------
 */
public class StartupServiceImpl implements StartupService {

	/** The log. */
	private static Logger log = LoggerFactory.getLogger(StartupServiceImpl.class);

	/** The system parameter service. */
	private SystemParameterService systemParameterService;

	/** The code tables cache. */
	private TableCache codeTablesCache;

	/** The search facade. */
	private SearchFacade searchFacade;

	/**
	 * 初始化,注意顺序不能调转.
	 * 
	 * @param servletContext
	 *            the servlet context
	 */
	@Override
	public synchronized void startup(ServletContext servletContext) {

		// Load System Parameter
		systemParameterService.initSystemParameter();

		codeTablesCache.initCodeTablesCache();

		String luceneIndexPath = PropertiesUtil.getLucenePath();
		log.info("luceneIndexPath is {}", luceneIndexPath);

		searchFacade.init(luceneIndexPath);

		HealthCheckerHolder.isInitialized(servletContext);
	}

	/**
	 * Sets the system parameter service.
	 * 
	 * @param systemParameterService
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
