/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.search.facade;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.model.entity.ShopDetail;
import com.legendshop.search.SearchArgs;
import com.legendshop.search.SearchFacade;
import com.legendshop.search.SearchResult;

/**
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
public class ShopDetailSearchFacade {
	
	/** The search facade. */
	private SearchFacade searchFacade;

	/**
	 * Creates the.
	 * 
	 * @param shopDetail
	 *            the shop detail
	 */
	public void create(ShopDetail shopDetail) {
		searchFacade.create(CommonServiceUtil.createSearchEntity(shopDetail));

	}

	/**
	 * Update.
	 * 
	 * @param shopDetail
	 *            the shop detail
	 */
	public void update(ShopDetail shopDetail) {
		searchFacade.update(CommonServiceUtil.createSearchEntity(shopDetail));

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
	 * @param shopDetail
	 *            the shop detail
	 */
	public void delete(ShopDetail shopDetail) {
		searchFacade.delete(CommonServiceUtil.createSearchEntity(shopDetail));
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
