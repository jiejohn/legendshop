/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.cache;

import org.springframework.cache.CacheManager;

/**
 * The Interface LegendCacheManager.
 */
public interface LegendCacheManager extends CacheManager{
	
	/**
	 * 
	 * Checks if is support query cache.
	 * 
	 * @return true, if is support query cache
	 */
	public boolean isSupportQueryCache();
	
	/**
	 * Checks if is removes the all entries.
	 * 
	 * @return true, if is removes the all entries
	 */
	public boolean isRemoveAllEntries();
	
	/**
	 * Gets the rel cache name.
	 * 
	 * @return the rel cache name
	 */
	public String getRelCacheName();
}
