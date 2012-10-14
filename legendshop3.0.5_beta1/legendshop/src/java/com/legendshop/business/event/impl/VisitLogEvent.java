/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.event.impl;

import java.util.Date;

import com.legendshop.business.event.EventId;
import com.legendshop.event.SystemEvent;
import com.legendshop.model.entity.VisitLog;
import com.legendshop.spi.constants.VisitTypeEnum;

/**
 * 记录访问历史
 * The Class FireEvent.
 */
public class VisitLogEvent extends SystemEvent<VisitLog>{

	/**
	 * Instantiates a new visit log event.
	 * 
	 * @param ip
	 *            the ip
	 * @param shopName
	 *            the shop name
	 * @param userName
	 *            the user name
	 * @param prodId
	 *            the prod id
	 * @param prodName
	 *            the prod name
	 * @param page
	 *            the page
	 */
	public VisitLogEvent(String ip, String shopName,String userName,Long prodId,String prodName,String page) {
		super(EventId.VISIT_LOG_EVENT);
		VisitLog visitLog = new VisitLog();
		visitLog.setDate(new Date());
		visitLog.setIp(ip);
		visitLog.setShopName(shopName);
		visitLog.setProductName(prodName);
		visitLog.setUserName(userName);
		if(prodId != null){
			visitLog.setProductId(String.valueOf(prodId));
		}
		visitLog.setPage(page);
		setSource(visitLog);
	}
	
	/**
	 * Instantiates a new visit log event.
	 * 
	 * for index visit
	 * 
	 * @param ip
	 *            the ip
	 * @param shopName
	 *            the shop name
	 * @param userName
	 *            the user name
	 * @param page
	 *            the page
	 */
	public VisitLogEvent(String ip, String shopName,String userName) {
		this(ip,shopName,userName,null,null,VisitTypeEnum.INDEX.value());
	}

}
