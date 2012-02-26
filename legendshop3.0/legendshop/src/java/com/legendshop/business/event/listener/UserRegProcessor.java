package com.legendshop.business.event.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.legendshop.event.processor.BaseProcessor;

public class UserRegProcessor extends BaseProcessor<String>{
	private final Logger log = LoggerFactory.getLogger(UserRegProcessor.class);
	@Override
	public boolean isSupport(String task) {
		log.info("UserRegProcessor isSupport calling, task= " + task);
		return true;
	}

	@Override
	public void process(String task) { 
		log.info("UserRegProcessor process calling, task= " + task);
	}


}
