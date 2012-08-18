/*
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * 官方网站：http://www.legendesign.net
 */
package com.legendshop.business.service.locator;

import java.util.Map;

import com.legendshop.core.constant.PageDefinition;
import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.spi.constants.TemplateEnum;

/**
 * A common service locator
 * 
 * @author George Guo
 * 
 */
public class GenericServiceLocator<T> {

	/** The service register map. */
	private Map<String, T> serviceMap;

	/**
	 * Gets the concrete service implementor.
	 * 
	 * @param template
	 *            the template
	 * @return the common page service
	 */
	public T getConcreteService(String shopName,PageDefinition page) {
		String template = ThreadLocalContext.getFrontType(page.getTemplates());
		T service = serviceMap.get(template);
		if (service == null) {
			service = serviceMap.get(TemplateEnum.DEFAULT);
		}
		return service;
	}

	/**
	 * Sets the service map.
	 * 
	 * @param serviceMap
	 *            the service map
	 */
	public void setServiceMap(Map<String, T> serviceMap) {
		this.serviceMap = serviceMap;
	}
}
