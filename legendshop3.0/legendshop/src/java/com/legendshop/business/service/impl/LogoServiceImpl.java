/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import java.util.List;

import com.legendshop.business.dao.LogoDao;
import com.legendshop.business.service.LogoService;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.BusinessException;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.model.entity.Logo;

/**
 * Logo服务.
 */
public class LogoServiceImpl implements LogoService {
    
    /** The logo dao. */
    private LogoDao logoDao;

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.LogoService#list(java.lang.String)
	 */
    @Override
	public List<Logo> getLogoList(String userName) {
        return logoDao.findByHQL("from Logo where userName = ?", userName);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.LogoService#load(java.lang.Long)
	 */
    @Override
	public Logo getLogoById(Long id) {
        return logoDao.get(Logo.class, id);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.LogoService#load(java.lang.Long, java.lang.String)
	 */
    @Override
	public Logo getLogoByIdAndName(Long id, String userName) {
        Logo logo = logoDao.findUniqueBy("from Logo where id = ? and userName = ?",Logo.class, id, userName);
        if (logo == null) {
            throw new BusinessException("no Logo record",EntityCodes.LOGO);
        }
        return logo;
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.LogoService#delete(java.lang.Long)
	 */
    @Override
	public void delete(Long id) {
    	logoDao.deleteById(Logo.class, id);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.LogoService#save(com.legendshop.model.entity.Logo)
	 */
    @Override
	public Long save(Logo logo) {
        if (logo.getId() != null) {
            update(logo);
            return logo.getId();
        }
        return (Long) logoDao.save(logo);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.LogoService#update(com.legendshop.model.entity.Logo)
	 */
    @Override
	public void update(Logo logo) {
    	logoDao.update(logo);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.LogoService#getDataByCriteriaQuery(com.legendshop.core.dao.support.CriteriaQuery)
	 */
    @Override
	public PageSupport getLogoList(CriteriaQuery cq) {
        return logoDao.find(cq);
    }
    

    /**
	 * Sets the logo dao.
	 * 
	 * @param logoDao
	 *            the new logo dao
	 */
    public void setLogoDao(LogoDao logoDao) {
        this.logoDao = logoDao;
    }
}
