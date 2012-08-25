/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.legendshop.event.TaskItem;
/**
 * 
 * 异步任务执行器
 */
public class TaskThread extends Thread {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(TaskThread.class);
	
	/** The item. */
	public TaskItem item;
	
	/**
	 * Instantiates a new task thread.
	 * 
	 * @param item
	 *            the item
	 */
	public TaskThread(TaskItem item){
		this.item = item;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		log.debug("{} item start running at {}",item.getClass().getSimpleName(),System.currentTimeMillis());
		item.execute();
		log.debug("{} item finished running at {}",item.getClass().getSimpleName(),System.currentTimeMillis());
	}
}
