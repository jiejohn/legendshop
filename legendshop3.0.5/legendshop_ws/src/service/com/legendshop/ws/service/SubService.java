/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.ws.service;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.legendshop.ws.model.Sub;

/**
 * The Interface SubService.
 */
@WebService(targetNamespace = "http://sub.service.ws.legendshop.com", name = "subService")
public interface SubService {
	
	/**
	 * Export order service.
	 * 
	 * @param sub
	 *            the sub
	 * @return the string
	 */
	public String exportOrderService(@WebParam(name="sub") Sub sub);
}
