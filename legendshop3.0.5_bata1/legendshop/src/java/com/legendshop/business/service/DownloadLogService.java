/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service;

import com.legendshop.model.entity.DownloadLog;

public interface DownloadLogService {

	/* (non-Javadoc)
	 * @see com.legendshop.business.common.download.DownLoadCallBack#checkCanDownload(java.lang.String, java.lang.String)
	 */
	public abstract boolean checkCanDownload(String ip, String filename);

	// 检查用户是否订购了该产品
	/* (non-Javadoc)
	 * @see com.legendshop.business.common.download.DownLoadCallBack#isUserOrderProduct(java.lang.Long, java.lang.String)
	 */
	public abstract boolean isUserOrderProduct(Long prodId, String userName);

	/**
	 * Save.
	 * 
	 * @param downloadLog
	 *            the download log
	 */
	public abstract void save(DownloadLog downloadLog);

	/**
	 * Gets the max times.
	 * 
	 * @return the max times
	 */
	public abstract Integer getMaxTimes();

	/**
	 * Gets the inter val minutes.
	 * 
	 * @return the inter val minutes
	 */
	public abstract Integer getInterValMinutes();

	/* (non-Javadoc)
	 * @see com.legendshop.business.common.download.DownLoadCallBack#afterDownload(java.lang.Object)
	 */
	public abstract void afterDownload(Object entity);

}