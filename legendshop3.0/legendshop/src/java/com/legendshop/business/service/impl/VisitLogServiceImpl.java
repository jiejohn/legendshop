/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import java.util.List;

import com.legendshop.business.dao.VisitLogDao;
import com.legendshop.business.service.VisitLogService;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.VisitLog;
import com.legendshop.util.AppUtils;

/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public class VisitLogServiceImpl implements VisitLogService  {
    
    /** The base dao. */
    private VisitLogDao visitLogDao;

    /**
	 * Sets the base dao.
	 * 
	 * @param visitLogDao
	 *            the new base dao
	 */
    public void setVisitLogDao(VisitLogDao visitLogDao) {
        this.visitLogDao = visitLogDao;
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.VisitLogService#list(java.lang.String)
	 */
    @Override
	public List<VisitLog> getVisitLogList(String userName) {
        return visitLogDao.findByHQL("from VisitLog where userName = ?", new Object[] { userName });
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.VisitLogService#load(java.lang.Long)
	 */
    @Override
	public VisitLog getVisitLogById(Long id) {
        return visitLogDao.get(VisitLog.class, id);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.VisitLogService#delete(java.lang.Long)
	 */
    @Override
	public void delete(Long id) {
        visitLogDao.deleteVisitLogById(id);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.VisitLogService#save(com.legendshop.model.entity.VisitLog)
	 */
    @Override
	public Long save(VisitLog visitLog) {
        if (!AppUtils.isBlank(visitLog.getVisitId())) {
            update(visitLog);
            return visitLog.getVisitId();
        }
        return (Long) visitLogDao.save(visitLog);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.VisitLogService#update(com.legendshop.model.entity.VisitLog)
	 */
    @Override
	public void update(VisitLog visitLog) {
        visitLogDao.updateVisitLog(visitLog);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.VisitLogService#getDataByCriteriaQuery(com.legendshop.core.dao.support.CriteriaQuery)
	 */
    @Override
	public PageSupport getVisitLogList(CriteriaQuery cq) {
        return visitLogDao.find(cq);
    }
}

