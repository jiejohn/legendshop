/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Required;

import com.legendshop.business.common.download.DownLoadCallBack;
import com.legendshop.business.dao.BusinessDao;
import com.legendshop.business.dao.DownloadLogDao;
import com.legendshop.business.service.DownloadLogService;
import com.legendshop.model.entity.DownloadLog;
import com.legendshop.util.DateUtil;

/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。 ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ---------------------------------------------------------------------------- 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public class DownloadLogServiceImpl implements DownLoadCallBack, DownloadLogService {
	
	/** The download log dao. */
	private DownloadLogDao downloadLogDao;
	
	/** The business dao. */
	private BusinessDao businessDao;
	
	/** The max times. */
	private Integer maxTimes;

	// 相隔分钟数，如果>=0则不做检查
	/** The inter val minutes. */
	private Integer interValMinutes;

	/* (non-Javadoc)
	 * @see com.legendshop.business.common.download.DownLoadCallBack#checkCanDownload(java.lang.String, java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.DownloadLogService#checkCanDownload(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean checkCanDownload(String ip, String filename) {
		if (interValMinutes >= 0) {
			return true;
		}
		String sql = "select count(*) from DownloadLog where ip = ? and date > ? and fileName = ?";
		Long result = downloadLogDao.findUniqueBy(sql, Long.class, ip, DateUtil.add(new Date(), Calendar.MINUTE,
				interValMinutes), filename);
		return result > maxTimes;
	}

	// 检查用户是否订购了该产品
	/* (non-Javadoc)
	 * @see com.legendshop.business.common.download.DownLoadCallBack#isUserOrderProduct(java.lang.Long, java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.DownloadLogService#isUserOrderProduct(java.lang.Long, java.lang.String)
	 */
	@Override
	public boolean isUserOrderProduct(Long prodId, String userName) {
		return businessDao.isUserOrderProduct(prodId, userName);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.DownloadLogService#save(com.legendshop.model.entity.DownloadLog)
	 */
	@Override
	public void save(DownloadLog downloadLog) {
		downloadLogDao.save(downloadLog);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.DownloadLogService#getMaxTimes()
	 */
	@Override
	public Integer getMaxTimes() {
		return maxTimes;
	}

	/**
	 * Sets the max times.
	 * 
	 * @param maxTimes
	 *            the new max times
	 */
	public void setMaxTimes(Integer maxTimes) {
		this.maxTimes = maxTimes;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.DownloadLogService#getInterValMinutes()
	 */
	@Override
	public Integer getInterValMinutes() {
		return interValMinutes;
	}

	/**
	 * Sets the inter val minutes.
	 * 
	 * @param interValMinutes
	 *            the new inter val minutes
	 */
	public void setInterValMinutes(Integer interValMinutes) {
		this.interValMinutes = interValMinutes;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.common.download.DownLoadCallBack#afterDownload(java.lang.Object)
	 */
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.DownloadLogService#afterDownload(java.lang.Object)
	 */
	@Override
	public void afterDownload(Object entity) {
		DownloadLog downloadLog = (DownloadLog) entity;
		save(downloadLog);
	}

	/**
	 * Sets the business dao.
	 * 
	 * @param businessDao
	 *            the new business dao
	 */
	@Required
	public void setBusinessDao(BusinessDao businessDao) {
		this.businessDao = businessDao;
	}

	/**
	 * Sets the download log dao.
	 * 
	 * @param downloadLogDao
	 *            the new download log dao
	 */
	@Required
	public void setDownloadLogDao(DownloadLogDao downloadLogDao) {
		this.downloadLogDao = downloadLogDao;
	}

}
