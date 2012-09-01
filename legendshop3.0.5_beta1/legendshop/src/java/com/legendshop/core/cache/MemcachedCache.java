/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.core.cache;


import java.io.Serializable;

import net.spy.memcached.MemcachedClient;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.util.Assert;

/**
 * 用memcached实现spring的cache通用接口.
 */
public class MemcachedCache extends AbstractLegendCache implements InitializingBean {

	/** The name. */
	private String name;

	/** The memcached client. */
	private MemcachedClient memcachedClient;

	/** The Constant MAX_EXPIRED_DURATION. */
	private static final int MAX_EXPIRED_DURATION = 60 * 60 * 24 * 30;

	/** The expired duration. */
	private int expiredDuration = MAX_EXPIRED_DURATION;

	/** The Constant NULL_HOLDER. */
	private static final Object NULL_HOLDER = new NullHolder();

	/** The allow null values. */
	private boolean allowNullValues = true;
	
	/**
	 * Instantiates a new memcached cache.
	 */
	public MemcachedCache() {
		
	}
	
	/**
	 * Instantiates a new memcached cache.
	 * 
	 * @param cacheManager
	 *            the cache manager
	 * @param memcachedClient
	 *            the memcached client
	 */
	public MemcachedCache(LegendCacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	/* (non-Javadoc)
	 * @see org.springframework.cache.Cache#getName()
	 */
	public String getName() {
		return this.name;
	}

	/* (non-Javadoc)
	 * @see org.springframework.cache.Cache#getNativeCache()
	 */
	public MemcachedClient getNativeCache() {
		return memcachedClient;
	}

	/* (non-Javadoc)
	 * @see org.springframework.cache.Cache#get(java.lang.Object)
	 */
	public ValueWrapper get(Object key) {
		Object value = memcachedClient.get(name + key.toString());
		return (value != null ? new SimpleValueWrapper(fromStoreValue(value)) : null);
	}

	/* (non-Javadoc)
	 * @see org.springframework.cache.Cache#put(java.lang.Object, java.lang.Object)
	 */
	public void put(Object key, Object value) {
		memcachedClient.add(name + key.toString(), expiredDuration,   toStoreValue(value));
		putObject(key, value);
	}

	/* (non-Javadoc)
	 * @see org.springframework.cache.Cache#evict(java.lang.Object)
	 */
	public void evict(Object key) {
		evictObject(key);
		
		memcachedClient.delete(name + key.toString());
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.cache.Cache#clear()
	 */
	public void clear() {
		memcachedClient.flush();
	}

	/**
	 * The Class NullHolder.
	 */
	private static class NullHolder implements Serializable {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = -99681708140860560L;
	}

	/**
	 * From store value.
	 * 
	 * @param storeValue
	 *            the store value
	 * @return the object
	 */
	protected Object fromStoreValue(Object storeValue) {
		if (this.allowNullValues && storeValue instanceof NullHolder) {
			return null;
		}
		return storeValue;
	}

	/**
	 * To store value.
	 * 
	 * @param userValue
	 *            the user value
	 * @return the object
	 */
	protected Object toStoreValue(Object userValue) {
		if (this.allowNullValues && userValue == null) {
			return NULL_HOLDER;
		}
		return userValue;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the memcached client.
	 * 
	 * @param memcachedClient
	 *            the new memcached client
	 */
	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}

	/**
	 * Sets the expired duration.
	 * 
	 * @param expiredDuration
	 *            the new expired duration
	 */
	public void setExpiredDuration(int expiredDuration) {
		this.expiredDuration = expiredDuration;
	}

	/**
	 * Sets the allow null values.
	 * 
	 * @param allowNullValues
	 *            the new allow null values
	 */
	public void setAllowNullValues(boolean allowNullValues) {
		this.allowNullValues = allowNullValues;
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(memcachedClient, "memcachedClient must not be null!");
	}

	@Override
	public String generateRelCacheKey(String relCacheKey) {
		return name + relCacheKey;
	}

}