/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.legendshop.business.dao.MyleagueDao;
import com.legendshop.business.service.MyleagueService;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Myleague;
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
public class MyleagueServiceImpl implements MyleagueService  {
    
    /** The base dao. */
    private MyleagueDao myleagueDao;

    /**
	 * Sets the base dao.
	 * 
	 * @param myleagueDao
	 *            the new base dao
	 */
    @Required
    public void setMyleagueDao(MyleagueDao myleagueDao) {
        this.myleagueDao = myleagueDao;
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.MyleagueService#list(java.lang.String)
	 */
    @Override
	public List<Myleague> getMyleagueList(String userName) {
        return myleagueDao.findByHQL("from Myleague where userName = ?", new Object[] { userName });
    }
    

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.MyleagueService#load(java.lang.Long)
	 */
    @Override
	public Myleague getMyleagueById(Long id) {
        return myleagueDao.get(Myleague.class, id);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.MyleagueService#delete(java.lang.Long)
	 */
    @Override
	public void delete(Long id) {
        myleagueDao.deleteMyleagueById(id);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.MyleagueService#save(com.legendshop.model.entity.Myleague)
	 */
    @Override
	public Long save(Myleague myleague) {
        if (!AppUtils.isBlank(myleague.getId())) {
            update(myleague);
            return myleague.getId();
        }
        return (Long) myleagueDao.save(myleague);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.MyleagueService#update(com.legendshop.model.entity.Myleague)
	 */
    @Override
	public void update(Myleague myleague) {
        myleagueDao.updateMyleague(myleague);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.MyleagueService#getDataByCriteriaQuery(com.legendshop.core.dao.support.CriteriaQuery)
	 */
    @Override
	public PageSupport getMyleagueList(CriteriaQuery cq) {
        return myleagueDao.find(cq);
    }
}

