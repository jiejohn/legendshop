/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import com.legendshop.business.dao.LogoDao;
import com.legendshop.business.service.LogoService;
import com.legendshop.core.OperationTypeEnum;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SimpleHqlQuery;
import com.legendshop.core.event.impl.FireEvent;
import com.legendshop.event.EventHome;
import com.legendshop.model.entity.ShopDetail;
import com.legendshop.spi.cache.ShopDetailUpdate;

/**
 * Logo服务.
 */
public class LogoServiceImpl implements LogoService {
    
    /** The logo dao. */
    private LogoDao logoDao;

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.LogoService#delete(java.lang.Long)
	 */
    @Override
	@ShopDetailUpdate
	public void deleteLogo(ShopDetail shopDetail) {
    	logoDao.deleteLogo(shopDetail);
    }


    /* (non-Javadoc)
	 * @see com.legendshop.business.service.LogoService#update(com.legendshop.model.entity.Logo)
	 */
    @Override
	@ShopDetailUpdate
	public void updateLogo(ShopDetail shopDetail) {
    	EventHome.publishEvent(new FireEvent(shopDetail, OperationTypeEnum.UPDATE));
    	logoDao.updateLogo(shopDetail);
    }


	@Override
	public PageSupport getLogo(SimpleHqlQuery hql) {
		return logoDao.getLogo(hql);
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
