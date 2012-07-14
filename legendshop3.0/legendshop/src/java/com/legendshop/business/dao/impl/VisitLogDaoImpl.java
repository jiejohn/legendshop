/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao.impl;
 
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.legendshop.business.dao.VisitLogDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.model.entity.VisitLog;
import com.legendshop.util.AppUtils;
import com.legendshop.util.DateUtil;
/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */

public class VisitLogDaoImpl extends BaseDaoImpl implements VisitLogDao {
     
     /** The log. */
     private static Logger log = LoggerFactory.getLogger(VisitLogDaoImpl.class);

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.VisitLogDao#updateVisitLog(com.legendshop.model.entity.VisitLog)
	 */
	@Override
	public void updateVisitLog(VisitLog visitLog) {
		update(visitLog);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.VisitLogDao#deleteVisitLogById(java.lang.Long)
	 */
	@Override
	public void deleteVisitLogById(Long id) {
		deleteById(VisitLog.class, id);
		
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.VisitLogDao#getVisitedIndexLog(com.legendshop.model.entity.VisitLog)
	 */
	@Override
	public VisitLog getVisitedIndexLog(VisitLog visitLog){
		String sql = "select v from VisitLog v where v.ip = ? and v.shopName = ? and v.date > ?";
		Date date = DateUtil.add(new Date(), Calendar.MINUTE, -30);
		List<VisitLog> list = findByHQLLimit(sql, 0, 1, visitLog.getIp(), visitLog.getShopName(), date);
		if (AppUtils.isNotBlank(list)) {
			return list.get(0);
		}
		return null;
	}
	
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.VisitLogDao#getVisitedProdLog(com.legendshop.model.entity.VisitLog)
	 */
	@Override
	public VisitLog getVisitedProdLog(VisitLog visitLog){
		String sql = "select v from VisitLog v where v.ip = ? and v.shopName = ? and v.productId = ? and v.date > ?";
		Date date = DateUtil.add(new Date(), Calendar.MINUTE, -30);
		List<VisitLog> list = findByHQLLimit(sql, 0, 1, visitLog.getIp(), visitLog.getShopName(), visitLog
				.getProductId(), date);
		if (AppUtils.isNotBlank(list)) {
			return list.get(0);
		}
		return null;
	}
	
 }

