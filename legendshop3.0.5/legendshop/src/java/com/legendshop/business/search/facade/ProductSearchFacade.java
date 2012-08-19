/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */

package com.legendshop.business.search.facade;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.model.entity.Product;
import com.legendshop.search.SearchArgs;
import com.legendshop.search.SearchFacade;
import com.legendshop.search.SearchResult;

/**
 * 产品全文搜索入口调用类.
 */
public class ProductSearchFacade {
	
	/** The search facade. */
	private SearchFacade searchFacade;

	/**
	 * Creates the.
	 * 
	 * @param product
	 *            the product
	 */
	public void create(Product product) {
		searchFacade.create(CommonServiceUtil.createSearchEntity(product));
	}

	/**
	 * Update.
	 * 
	 * @param product
	 *            the product
	 */
	public void update(Product product) {
		searchFacade.update(CommonServiceUtil.createSearchEntity(product));

	}

	/**
	 * Search.
	 * 
	 * @param args
	 *            the args
	 * @return the search result
	 */
	public SearchResult search(SearchArgs args) {
		return searchFacade.search(args);
	}

	/**
	 * Delete.
	 * 
	 * @param product
	 *            the product
	 */
	public void delete(Product product) {
		searchFacade.delete(CommonServiceUtil.createSearchEntity(product));
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
