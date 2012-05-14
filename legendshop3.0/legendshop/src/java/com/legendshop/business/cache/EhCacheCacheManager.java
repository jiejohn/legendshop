/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.cache;

import java.util.Collection;
import java.util.LinkedHashSet;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Status;

import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractCacheManager;
import org.springframework.util.Assert;

/**
 * The Class EhCacheCacheManager.
 */
public class EhCacheCacheManager extends AbstractCacheManager implements LegendCacheManager {

	/** The cache manager. */
	private net.sf.ehcache.CacheManager cacheManager;
	
	/** The support query cache. */
	private boolean supportQueryCache;
	
	/** The remove all entries. */
	private boolean removeAllEntries;
	
	/** The rel cache name. */
	private  String relCacheName = "LEGENDSHOP_CACHE";


	/**
	 * Set the backing EhCache {@link net.sf.ehcache.CacheManager}.
	 * 
	 * @param cacheManager
	 *            the new cache manager
	 */
	public void setCacheManager(net.sf.ehcache.CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}


	/* (non-Javadoc)
	 * @see org.springframework.cache.support.AbstractCacheManager#loadCaches()
	 */
	@Override
	protected Collection<Cache> loadCaches() {
		Assert.notNull(this.cacheManager, "A backing EhCache CacheManager is required");
		Status status = this.cacheManager.getStatus();
		Assert.isTrue(Status.STATUS_ALIVE.equals(status),
				"An 'alive' EhCache CacheManager is required - current cache is " + status.toString());

		String[] names = this.cacheManager.getCacheNames();
		Collection<Cache> caches = new LinkedHashSet<Cache>(names.length);
		for (String name : names) {
			caches.add(new LegendCache(this,this.cacheManager.getEhcache(name)));
		}
		return caches;
	}

	/* (non-Javadoc)
	 * @see org.springframework.cache.support.AbstractCacheManager#getCache(java.lang.String)
	 */
	@Override
	public Cache getCache(String name) {
		Cache cache = super.getCache(name);
		if (cache == null) {
			// check the EhCache cache again
			// (in case the cache was added at runtime)
			Ehcache ehcache = this.cacheManager.getEhcache(name);
			if (ehcache != null) {
				cache = new LegendCache(this,ehcache);
				addCache(cache);
			}
		}
		return cache;
	}


	/* (non-Javadoc)
	 * @see com.legendshop.business.cache.LegendCacheManager#isSupportQueryCache()
	 */
	@Override
	public boolean isSupportQueryCache() {
		return supportQueryCache;
	}


	/**
	 * Sets the support query cache.
	 * 
	 * @param supportQueryCache
	 *            the new support query cache
	 */
	public void setSupportQueryCache(boolean supportQueryCache) {
		this.supportQueryCache = supportQueryCache;
	}


	/* (non-Javadoc)
	 * @see com.legendshop.business.cache.LegendCacheManager#getRelCacheName()
	 */
	@Override
	public String getRelCacheName() {
		return relCacheName;
	}


	/**
	 * Sets the rel cache name.
	 * 
	 * @param relCacheName
	 *            the new rel cache name
	 */
	public void setRelCacheName(String relCacheName) {
		this.relCacheName = relCacheName;
	}


	/* (non-Javadoc)
	 * @see com.legendshop.business.cache.LegendCacheManager#isRemoveAllEntries()
	 */
	@Override
	public boolean isRemoveAllEntries() {
		return false;
	}


	/**
	 * Sets the removes the all entries.
	 * 
	 * @param removeAllEntries
	 *            the new removes the all entries
	 */
	public void setRemoveAllEntries(boolean removeAllEntries) {
		this.removeAllEntries = removeAllEntries;
	}

}