package com.legendshop.business.process.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.legendshop.event.processor.ThreadProcessor;

public class SendMailProcessor extends ThreadProcessor<String>{
	private final Logger log = LoggerFactory.getLogger(SendMailProcessor.class);
	@Override
	public boolean isSupport(String task) {
		log.info("SendMailProcessor isSupport calling, task= " + task);
		return true;
	}

	@Override
	public void process(String task) {
		log.info("SendMailProcessor process calling, task= " + task);
	}


}
