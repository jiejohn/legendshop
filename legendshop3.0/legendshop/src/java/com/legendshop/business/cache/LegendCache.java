/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.cache;

import java.util.Collection;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCache;
import org.springframework.util.Assert;

import com.legendshop.model.entity.BaseEntity;

/**
 * The Class LegendCache.
 */
public class LegendCache implements Cache {

	/** The log. */
	private final Logger log = LoggerFactory.getLogger(LegendCache.class);

	/** The cache manager. */
	private final LegendCacheManager cacheManager;

	/** The cache. */
	private final Ehcache cache;

	/** The SUFFIX. */
	private final String SUFFIX = "List";

	/**
	 * Create an {@link EhCacheCache} instance.
	 * 
	 * @param cacheManager
	 *            the cache manager
	 * @param ehcache
	 *            backing Ehcache instance
	 */
	public LegendCache(LegendCacheManager cacheManager, Ehcache ehcache) {
		Assert.notNull(ehcache, "Ehcache must not be null");
		Status status = ehcache.getStatus();
		Assert.isTrue(Status.STATUS_ALIVE.equals(status),
				"An 'alive' Ehcache is required - current cache is " + status.toString());
		this.cacheManager = cacheManager;
		this.cache = ehcache;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.cache.Cache#getName()
	 */
	@Override
	public String getName() {
		return this.cache.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.cache.Cache#getNativeCache()
	 */
	@Override
	public Ehcache getNativeCache() {
		return this.cache;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.cache.Cache#clear()
	 */
	@Override
	public void clear() {
		this.cache.removeAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.cache.Cache#get(java.lang.Object)
	 */
	@Override
	public ValueWrapper get(Object key) {
		Element element = this.cache.get(key);
		if (element != null) {
			//log.info("get from cache {} by key {}, result {}", new Object[] { getName(), key, element.getObjectValue() });
			return new LegendValueWrapper(element.getObjectValue());
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.cache.Cache#put(java.lang.Object,
	 * java.lang.Object)
	 */
	@Override
	public void put(Object key, Object value) {
		//log.info("put into cache {} by key {}, value {}", new Object[] { getName(), key, value });
		this.cache.put(new Element(key, value));
		
		if(this.getName().endsWith(SUFFIX) && cacheManager.isSupportQueryCache() && Collection.class.isAssignableFrom(value.getClass())){
			//for list
				// 如果是列表，则保存以ID为主键的关系
					Collection<BaseEntity> coll = (Collection<BaseEntity>) value;
					for (BaseEntity entity : coll) {
						//put relevant into relcache  format: entity name + id:{[cacheName, key]}
						Cache relCache = cacheManager.getCache(cacheManager.getRelCacheName());
						String relCacheKey = this.getName().substring(0, this.getName().length() - 4) + entity.getId();
						LegendValueWrapper valueWrapper = (LegendValueWrapper) relCache.get(relCacheKey);
						if (valueWrapper == null) {
							valueWrapper = new LegendValueWrapper(entity.getId());
						}
						if (valueWrapper.addRelObject(this.getName(),key)) {
							//log.info("put into rel cache {} by key {}, value {}", new Object[] {relCacheKey, relCacheKey, valueWrapper });
							relCache.put(relCacheKey, valueWrapper);
						}
			}

		}


	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.cache.Cache#evict(java.lang.Object)
	 */
	@Override
	public void evict(Object key) {
		//evict effect only in entity cache
		//log.info("evict from cache {} by key {}", getName(), key);
		this.cache.remove(key);
		
		//for list
		if (cacheManager.isSupportQueryCache() && !this.getName().endsWith(SUFFIX)) {
				// clean entity
				Cache relCache = cacheManager.getCache(cacheManager.getRelCacheName());
				if (relCache != null) {
					String relCacheKey = this.getName() + key;
					LegendValueWrapper valueWrapper = (LegendValueWrapper)relCache.get(relCacheKey);
					if(valueWrapper != null){
						for (CacheNameAndItemWrapper warpper : valueWrapper.getRelObject()) {
							//log.info("evict from cache {} by key {}", warpper.getCacheName(), warpper.getKey());
							cacheManager.getCache(warpper.getCacheName()).evict(warpper.getKey());
						}
				}
					//remove relcache
					relCache.evict(relCacheKey);
			}
		}else if(cacheManager.isRemoveAllEntries()){
			Cache listCache = cacheManager.getCache(this.getName() + SUFFIX);
			if(listCache != null){
				listCache.clear();
			}
		}
	}
	

}
