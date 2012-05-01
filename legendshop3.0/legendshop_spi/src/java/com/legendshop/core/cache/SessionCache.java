/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.core.cache;

import java.io.Serializable;
import java.util.Map;

/**
 * The Interface SessionCache.
 */
public interface SessionCache {
	
	/**
	 * Gets the attribute.
	 * 
	 * @param root
	 *            the root
	 * @param name
	 *            the name
	 * @return the attribute
	 */
	public Serializable getAttribute(String root, String name);

	/**
	 * Sets the attribute.
	 * 
	 * @param root
	 *            the root
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 * @param exp
	 *            the exp
	 */
	public void setAttribute(String root, String name, Serializable value,
			int exp);

	/**
	 * Clear.
	 * 
	 * @param root
	 *            the root
	 */
	public void clear(String root);

	/**
	 * Exist.
	 * 
	 * @param root
	 *            the root
	 * @return true, if successful
	 */
	public boolean exist(String root);

	/**
	 * Gets the session.
	 * 
	 * @param root
	 *            the root
	 * @return the session
	 */
	public Map<String, Serializable> getSession(String root);

	/**
	 * Sets the session.
	 * 
	 * @param root
	 *            the root
	 * @param session
	 *            the session
	 * @param exp
	 *            the exp
	 */
	public void setSession(String root, Map<String, Serializable> session,
			int exp);
}
