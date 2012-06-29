/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.helper.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.legendshop.business.dao.VisitLogDao;
import com.legendshop.event.TaskItem;
import com.legendshop.model.entity.VisitLog;
import com.legendshop.spi.constants.VisitTypeEnum;
import com.legendshop.util.AppUtils;
import com.legendshop.util.ContextServiceLocator;
import com.legendshop.util.DateUtil;
import com.legendshop.util.ip.IPSeeker;

/**
 * 用户访问历史，30分钟之内算访问一次
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * ------------------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ------------------------------------------------------------------------------------
 * 
 * 官方网站：http://www.legendesign.net
 */
public class PersistVisitLogTask implements TaskItem {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(PersistVisitLogTask.class);
	
	/** The dao. */
	private VisitLogDao visitLogDao;
	
	/** The visit log. */
	private final  VisitLog visitLog;

	/**
	 * Instantiates a new persist visit log task.
	 * 
	 * @param visitLog
	 *            the visit log
	 * @param dao
	 *            the dao
	 */
	public PersistVisitLogTask(VisitLog visitLog) {
		this.visitLog = visitLog;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.helper.TaskItem#execute()
	 */
	@Override
	public void execute() {
		if (log.isDebugEnabled()) {
			log.debug("[{}],{} visit index {}", new Object[] { visitLog.getIp(), visitLog.getUserName(),
					visitLog.getShopName() });
		}
		visitLog.setArea(IPSeeker.getInstance().getArea(visitLog.getIp()));
		visitLog.setCountry(IPSeeker.getInstance().getCountry(visitLog.getIp()));
		VisitLog origin = null;
		if (VisitTypeEnum.INDEX.value().equals(visitLog.getPage())) {
			String sql = "select v from VisitLog v where v.ip = ? and v.shopName = ? and v.date > ?";
			Date date = DateUtil.add(new Date(), Calendar.MINUTE, -30);
			List<VisitLog> list = getVisitLogDao().findByHQLLimit(sql, 0, 1, visitLog.getIp(), visitLog.getShopName(), date);
			if (AppUtils.isNotBlank(list)) {
				origin = list.get(0);
			}
		} else {
			String sql = "select v from VisitLog v where v.ip = ? and v.shopName = ? and v.productId = ? and v.date > ?";
			Date date = DateUtil.add(new Date(), Calendar.MINUTE, -30);
			List<VisitLog> list = getVisitLogDao().findByHQLLimit(sql, 0, 1, visitLog.getIp(), visitLog.getShopName(), visitLog
					.getProductId(), date);
			if (AppUtils.isNotBlank(list)) {
				origin = list.get(0);
			}
		}
		if (origin != null) {
			Integer num = origin.getVisitNum();
			if (num == null) {
				num = 1;
			} else {
				num++;
			}
			origin.setVisitNum(num);
			origin.setDate(new Date());
			getVisitLogDao().updateVisitLog(origin);
		} else {
			visitLog.setVisitNum(1);
			getVisitLogDao().save(visitLog);

		}
	}
	
	private VisitLogDao getVisitLogDao(){
		if(visitLogDao == null){
			visitLogDao = (VisitLogDao)ContextServiceLocator.getInstance().getBean("visitLogDao");
		}
		return visitLogDao;
	}

}
