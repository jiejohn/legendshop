/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.process.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.legendshop.business.service.VisitLogService;
import com.legendshop.event.ThreadProcessor;
import com.legendshop.model.entity.VisitLog;

/**
 * The Class VisitLogProcessor.
 */
public class VisitLogProcessor extends ThreadProcessor<VisitLog>{
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(VisitLogProcessor.class);
	
	/** The dao. */
	private VisitLogService visitLogService;
	
	/* (non-Javadoc)
	 * @see com.legendshop.event.ThreadProcessor#isSupport(java.lang.Object)
	 */
	@Override
	public boolean isSupport(VisitLog visitLog) {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.event.processor.AbstractProcessor#process(java.lang.Object)
	 */
	@Override
	public void process(VisitLog visitLog) {
		if (log.isDebugEnabled()) {
			log.debug("[{}],{} visit index {}, this {}", new Object[] { visitLog.getIp(), visitLog.getUserName(),
					visitLog.getShopName(),this });
		}
		visitLogService.process(visitLog);
		
	}

	/**
	 * Sets the visit log service.
	 * 
	 * @param visitLogService
	 *            the new visit log service
	 */
	public void setVisitLogService(VisitLogService visitLogService) {
		this.visitLogService = visitLogService;
	}
	




}
