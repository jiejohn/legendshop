/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.ws.service.impl;

import javax.jws.WebService;

import com.legendshop.ws.model.Sub;
import com.legendshop.ws.service.SubService;

/**
 * The Class SubServiceImpl.
 */
@WebService(endpointInterface="com.legendshop.ws.service.SubService",
serviceName="SubService",
portName="SubServicePort",
targetNamespace="http://sub.service.ws.legendshop.com")
public class SubServiceImpl implements SubService{

	/* (non-Javadoc)
	 * @see com.legendshop.ws.service.SubService#exportOrderService(com.legendshop.ws.model.Sub)
	 */
	@Override
	public String exportOrderService(Sub sub) {
		System.out.println("exportOrderService calling");
		System.out.println("sub ProdName = " + sub.getProdName() +", UserName = " + sub.getUserName());
		return "exportOrderService";
	}

}
