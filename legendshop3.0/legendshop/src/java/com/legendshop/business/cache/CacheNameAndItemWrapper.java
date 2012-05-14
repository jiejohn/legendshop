/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.cache;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * The Class CacheNameAndItemWrapper.
 */
public /**
 * The Class CacheNameAndItemWrapper.
 */
class CacheNameAndItemWrapper{
	
	/** The cache name. */
	private String cacheName;
	
	/** The key. */
	private Object key;
	
	/**
	 * Instantiates a new cache name and item wrapper.
	 * 
	 * @param cacheName
	 *            the cache name
	 * @param key
	 *            the key
	 */
	public CacheNameAndItemWrapper(String cacheName,Object key){
		this.cacheName = cacheName;
		this.key = key;
	}
	
	/**
	 * Gets the cache name.
	 * 
	 * @return the cache name
	 */
	public String getCacheName() {
		return cacheName;
	}
	
	/**
	 * Sets the cache name.
	 * 
	 * @param cacheName
	 *            the new cache name
	 */
	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}
	
	/**
	 * Gets the key.
	 * 
	 * @return the key
	 */
	public Object getKey() {
		return key;
	}
	
	/**
	 * Sets the key.
	 * 
	 * @param key
	 *            the new key
	 */
	public void setKey(Object key) {
		this.key = key;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
	      return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
	              // if deriving: appendSuper(super.hashCode()).
	              append(cacheName).
	              append(key).
	              toHashCode();

	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		  if (obj == null)
	            return false;
	        if (obj == this)
	            return true;
	        if (obj.getClass() != getClass())
	            return false;

	        CacheNameAndItemWrapper wapper = (CacheNameAndItemWrapper) obj;
	        return new EqualsBuilder().
	            append(cacheName, wapper.getCacheName()).
	            append(key, wapper.getKey()).
	            isEquals();

	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return cacheName + key ;
	}
}