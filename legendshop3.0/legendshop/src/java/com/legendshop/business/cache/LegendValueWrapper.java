/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.cache;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.Cache.ValueWrapper;

import com.legendshop.util.AppUtils;


/**
 * The Class LegendValueWrapper.
 */
public class LegendValueWrapper implements ValueWrapper {

	/** The id. */
	private final Object id;
	
	/** The rel object. */
	private List<CacheNameAndItemWrapper> relObject = null;



	/**
	 * Instantiates a new legend value wrapper.
	 * 
	 * @param id
	 *            the id
	 */
	public LegendValueWrapper(Object id) {
		this.id = id;
	}


	/**
	 * Simply returns the value as given at construction time.
	 * 
	 * @return the object
	 */
	@Override
	public Object get() {
		return this.id;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(id);
		if(AppUtils.isNotBlank(relObject)){
			for (CacheNameAndItemWrapper wrapper : relObject) {
				sb.append("[").append(wrapper.getCacheName()).append(",").append(wrapper.getKey()).append("]");
			}
		}
		return sb.toString();
	}
	

	/**
	 * Gets the rel object.
	 * 
	 * @return the rel object
	 */
	public List<CacheNameAndItemWrapper> getRelObject() {
		return relObject;
	}
	
	/**
	 * Adds the rel object.
	 * 
	 * @param cacheName
	 *            the cache name
	 * @param key
	 *            the key
	 */
	public boolean addRelObject(String cacheName,Object key){
		if(relObject == null){
			relObject = new ArrayList<CacheNameAndItemWrapper>(5);
		}
		CacheNameAndItemWrapper warpper = new CacheNameAndItemWrapper(cacheName, key);
		if(!relObject.contains(warpper)){
			relObject.add(warpper);
			return true;
		}
		return false;
		
	}
	
	public static void main(String[] args) {
		CacheNameAndItemWrapper a = new CacheNameAndItemWrapper("a", "b");
		CacheNameAndItemWrapper b = new CacheNameAndItemWrapper("a", "b");
		CacheNameAndItemWrapper c = new CacheNameAndItemWrapper("a1", "c");
		System.out.println(a.equals(b));
		System.out.println(a.equals(c));
		System.out.println(a.equals(null));
	}

}