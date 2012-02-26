/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.legendshop.business.dao.PubDao;
import com.legendshop.business.service.PubService;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.exception.PermissionException;
import com.legendshop.model.entity.Pub;
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
public class PubServiceImpl implements PubService {
    
    /** The base dao. */
    private PubDao pubDao;

    /**
	 * Sets the base dao.
	 * 
	 * @param pubDao
	 *            the new base dao
	 */
    @Required
    public void setPubDao(PubDao pubDao) {
        this.pubDao = pubDao;
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.PubService#list(java.lang.String)
	 */
    @Override
	public List<Pub> getPubList(String userName) {
        return pubDao.findByHQL("from Pub where userName = ?", new Object[] { userName });
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.PubService#load(java.lang.Long)
	 */
    @Override
	public Pub getPubById(Long id) {
        return pubDao.get(Pub.class, id);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.PubService#delete(java.lang.Long)
	 */
    @Override
	public void delete(Long id) {
        pubDao.deleteById(Pub.class, id);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.PubService#save(com.legendshop.model.entity.Pub, java.lang.String, boolean)
	 */
    @Override
	public Long save(Pub pub, String userName, boolean viewAllDataFunction) {
        if (!AppUtils.isBlank(pub.getId())) {
            Pub entity = pubDao.get(Pub.class, pub.getId());
            if (entity != null) {
                if (!viewAllDataFunction && !userName.equals(entity.getUserName())) {
                    throw new PermissionException("Can't edit Pub does not onw to you!",EntityCodes.PUB);
                }
                entity.setDate(new Date());
                entity.setMsg(pub.getMsg());
                entity.setTitle(pub.getTitle());
                update(entity);
                return pub.getId();
            }
            return null;
        }
        return (Long) pubDao.save(pub);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.PubService#update(com.legendshop.model.entity.Pub)
	 */
    @Override
	public void update(Pub pub) {
        pubDao.update(pub);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.PubService#getDataByCriteriaQuery(com.legendshop.core.dao.support.CriteriaQuery)
	 */
    @Override
	public PageSupport getPubList(CriteriaQuery cq) {
        return pubDao.find(cq);
    }
}
