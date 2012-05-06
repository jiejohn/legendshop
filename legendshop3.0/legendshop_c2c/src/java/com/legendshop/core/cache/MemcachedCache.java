package com.legendshop.core.cache;

import java.io.Serializable;

import net.spy.memcached.MemcachedClient;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.util.Assert;

/**
 * 用memcached实现spring的cache通用接口
 * 
 */
public class MemcachedCache implements Cache, InitializingBean {

	private String name;

	private MemcachedClient memcachedClient;

	private static final int MAX_EXPIRED_DURATION = 60 * 60 * 24 * 30;

	private int expiredDuration = MAX_EXPIRED_DURATION;

	private static final Object NULL_HOLDER = new NullHolder();

	private boolean allowNullValues = true;

	public String getName() {
		return this.name;
	}

	public MemcachedClient getNativeCache() {
		return memcachedClient;
	}

	public ValueWrapper get(Object key) {
		Object value = memcachedClient.get(key.toString());
		return (value != null ? new SimpleValueWrapper(fromStoreValue(value)) : null);
	}

	public void put(Object key, Object value) {
		memcachedClient.add(key.toString(), expiredDuration, toStoreValue(value));
	}

	public void evict(Object key) {
		memcachedClient.delete(key.toString());
	}

	public void clear() {
		memcachedClient.flush();
	}

	private static class NullHolder implements Serializable {

		private static final long serialVersionUID = -99681708140860560L;
	}

	protected Object fromStoreValue(Object storeValue) {
		if (this.allowNullValues && storeValue instanceof NullHolder) {
			return null;
		}
		return storeValue;
	}

	protected Object toStoreValue(Object userValue) {
		if (this.allowNullValues && userValue == null) {
			return NULL_HOLDER;
		}
		return userValue;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}

	public void setExpiredDuration(int expiredDuration) {
		this.expiredDuration = expiredDuration;
	}

	public void setAllowNullValues(boolean allowNullValues) {
		this.allowNullValues = allowNullValues;
	}

	public void afterPropertiesSet() throws Exception {
		Assert.notNull(memcachedClient, "memcachedClient must not be null!");
	}

}