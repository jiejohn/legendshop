package com.legendshop.business.processor.event;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.legendshop.business.dao.VisitLogDao;
import com.legendshop.event.processor.ThreadProcessor;
import com.legendshop.model.entity.VisitLog;
import com.legendshop.spi.constants.VisitTypeEnum;
import com.legendshop.util.ip.IPSeeker;

public class VisitLogProcessor extends ThreadProcessor<VisitLog>{
	private final Logger log = LoggerFactory.getLogger(VisitLogProcessor.class);
	
	/** The dao. */
	private VisitLogDao visitLogDao;
	
	@Override
	public boolean isSupport(VisitLog visitLog) {
		return true;
	}

	@Override
	public void process(VisitLog visitLog) {
		if (log.isDebugEnabled()) {
			log.debug("[{}],{} visit index {}, this {}", new Object[] { visitLog.getIp(), visitLog.getUserName(),
					visitLog.getShopName(),this });
		}
		visitLog.setArea(IPSeeker.getInstance().getArea(visitLog.getIp()));
		visitLog.setCountry(IPSeeker.getInstance().getCountry(visitLog.getIp()));
		VisitLog origin = null;
		if (VisitTypeEnum.INDEX.value().equals(visitLog.getPage())) {
			origin = visitLogDao.getVisitedIndexLog(visitLog);
		} else {
			origin = visitLogDao.getVisitedProdLog(visitLog);
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
			visitLogDao.updateVisitLog(origin);
		} else {
			visitLog.setVisitNum(1);
			visitLogDao.save(visitLog);

		}
	}
	


	public void setVisitLogDao(VisitLogDao visitLogDao) {
		this.visitLogDao = visitLogDao;
	}


}
