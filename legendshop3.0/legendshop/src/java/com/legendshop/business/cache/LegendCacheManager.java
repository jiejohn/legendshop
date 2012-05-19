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
	 * 是否支持查询缓存
	 * 
	 * @return true, if is support query cache
	 */
	public boolean isSupportQueryCache();
	
	/**
	 * Checks if is removes the all entries.
	 * 在不支持查询缓存的情况下起效，当Entity Cache更新时是否删除List Cache所有对象
	 * 
	 * @return true, if is removes the all entries
	 */
	public boolean isRemoveAllEntries();
	
	/**
	 * Gets the rel cache name.
	 * RelationShop 缓存名称
	 * 
	 * @return the rel cache name
	 */
	public String getRelCacheName();
}
