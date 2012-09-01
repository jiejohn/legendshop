/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.core.cache;

import java.util.Collection;

import org.springframework.beans.factory.InitializingBean;


/**
 * The Class MemCachedManager.
 */
public class MemCachedManager extends AbstractLegendCacheManager implements LegendCacheManager, InitializingBean{

	/** The caches. */
	private Collection<? extends MemcachedCache> caches;

	/**
	 * Specify the collection of Cache instances to use for this CacheManager.
	 * 
	 * @param caches
	 *            the new caches
	 */
	public void setCaches(Collection<? extends MemcachedCache> caches) {
		this.caches = caches;
	}

	/* (non-Javadoc)
	 * @see org.springframework.cache.support.AbstractCacheManager#loadCaches()
	 */
	@Override
	protected Collection<? extends MemcachedCache> loadCaches() {
		return this.caches;
	}
	
	
	/* (non-Javadoc)
	 * @see org.springframework.cache.support.AbstractCacheManager#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() {
		if(caches != null){
			for (MemcachedCache cache : caches) {
				cache.setCacheManager(this);
				addCache(cache);
			}
		}
	}

}