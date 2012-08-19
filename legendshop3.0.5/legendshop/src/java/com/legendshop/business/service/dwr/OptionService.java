package com.legendshop.business.service.dwr;

import java.util.Map;

public interface OptionService {

	/**
	 * Gets the linked options by hql.
	 * 
	 * @param hql
	 *            the hql
	 * @return the linked options by hql
	 */
	public abstract Map<Object, Object> getLinkedOptionsByHql(String hql);

	/**
	 * Gets the linked options by sql.
	 * 
	 * @param sql
	 *            the sql
	 * @return the linked options by sql
	 */
	public abstract Map<Object, Object> getLinkedOptionsBySql(String sql);

}