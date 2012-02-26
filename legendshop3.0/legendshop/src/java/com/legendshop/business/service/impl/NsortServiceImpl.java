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

import com.legendshop.business.dao.NsortDao;
import com.legendshop.business.service.NsortService;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Nsort;
import com.legendshop.model.entity.Sort;
import com.legendshop.util.AppUtils;

/**
 * 产品子分类服务.
 */
public class NsortServiceImpl implements NsortService {
    
    /** The nsort dao. */
    private NsortDao nsortDao;

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.NsortService#list(java.lang.String)
	 */
    @Override
	public List<Nsort> getNsortList(String userName) {
        return nsortDao.findByHQL("from Nsort where userName = ?", new Object[] { userName });
    }

    //parentNsortId is not null ：3级分类
    /* (non-Javadoc)
	 * @see com.legendshop.business.service.NsortService#listBySort(java.lang.Long)
	 */
    @Override
	public List<Nsort> getNSortBySort(Long sortId) {
        return nsortDao.findByHQL("from Nsort where sortId = ? and parentNsortId is not null", sortId);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.NsortService#hasChildNsort(java.lang.Long)
	 */
    @Override
	public boolean isHasChildNsort(Long id) {
        Long result = nsortDao.findUniqueBy("select count(*) from Nsort where parentNsortId = ?",Long.class, id);
        return result > 0;
    }
    
    /* (non-Javadoc)
	 * @see com.legendshop.business.service.NsortService#hasChildNsortBrand(java.lang.Long)
	 */
    @Override
	public boolean isHasChildNsortBrand(Long id) {
        Long result = nsortDao.findUniqueBy("select count(*) from NsortBrand n where n.id.nsortId = ?",Long.class, id);
        return result > 0;
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.NsortService#load(java.lang.Long)
	 */
    @Override
	public Nsort getNsort(Long id) {
        return nsortDao.get(Nsort.class, id);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.NsortService#loadSort(java.lang.Long)
	 */
    @Override
	public Sort getSort(Long id) {
        return nsortDao.get(Sort.class, id);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.NsortService#delete(java.lang.Long)
	 */
    @Override
	public void delete(Long id) {
        nsortDao.deleteById(Nsort.class, id);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.NsortService#save(com.legendshop.model.entity.Nsort)
	 */
    @Override
	public Long save(Nsort nsort) {
        if (!AppUtils.isBlank(nsort.getNsortId())) {
            update(nsort);
            return nsort.getNsortId();
        }
        return (Long) nsortDao.save(nsort);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.NsortService#update(com.legendshop.model.entity.Nsort)
	 */
    @Override
	public void update(Nsort nsort) {
        nsortDao.update(nsort);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.NsortService#getDataByCriteriaQuery(com.legendshop.core.dao.support.CriteriaQuery)
	 */
    @Override
	public PageSupport getNsortList(CriteriaQuery cq) {
        return nsortDao.find(cq);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.NsortService#getDataByCriteriaQuery(com.legendshop.core.dao.support.HqlQuery)
	 */
    @Override
	public PageSupport getNsortList(HqlQuery hql) {
        return nsortDao.find(hql);
    }
    
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.NsortService#queryNsort(java.lang.Long)
	 */
	@Override
	public Nsort getNsortById(Long id) {
		 return nsortDao.getNsort(id);
	}
    
    /**
	 * Sets the nsort dao.
	 * 
	 * @param baseDao
	 *            the new nsort dao
	 */
    @Required
    public void setNsortDao(NsortDao nsortDao) {
        this.nsortDao = nsortDao;
    }

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.NsortService#queryNsortBySortId(java.lang.Long)
	 */
	@Override
	public List<Nsort> getNsortBySortId(Long sortId) {
		return nsortDao.getNsortBySortId(sortId);
	}


}
