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

import com.legendshop.event.processor.BaseProcessor;
import com.legendshop.model.entity.Sub;
//import com.legendshop.ws.service.client.SubService_Client;

/**
 * 下订单时触发的处理器
 */
public class OrderSaveProcessor extends BaseProcessor<Sub>{
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(OrderSaveProcessor.class);
	
	/* (non-Javadoc)
	 * @see com.legendshop.event.processor.AbstractProcessor#isSupport(java.lang.Object)
	 */
	@Override
	public boolean isSupport(Sub sub) {
		log.info("UserRegProcessor isSupport calling, task= " + sub);
		return true;
	}

	/**
	 * 处理逻辑
	 */
	@Override
	public void process(Sub sub) { 
		log.info("UserRegProcessor process calling, task= " + sub);
//		System.out.println("UserRegProcessor process calling, task= 111111111111111" + sub);
//		SubService_Client client = new SubService_Client();
//		com.legendshop.ws.service.sub.Sub wsSub = new com.legendshop.ws.service.sub.Sub();
//		wsSub.setProdName(sub.getProdName());
		
		//client.exportOrderService(wsSub);
	}


}
