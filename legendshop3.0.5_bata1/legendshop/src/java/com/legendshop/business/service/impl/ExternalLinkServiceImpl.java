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

import com.legendshop.business.dao.ExternalLinkDao;
import com.legendshop.business.service.ExternalLinkService;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.BusinessException;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.model.entity.ExternalLink;
import com.legendshop.util.AppUtils;

/**
 * 友情链接服务.
 */
public class ExternalLinkServiceImpl implements ExternalLinkService {
    
    /** The base dao. */
    private ExternalLinkDao externalLinkDao;

    /**
	 * Sets the base dao.
	 * 
	 * @param externalLinkDao
	 *            the new base dao
	 */
    @Required
    public void setExternalLinkDao(ExternalLinkDao externalLinkDao) {
        this.externalLinkDao = externalLinkDao;
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.ExternalLinkService#list(java.lang.String)
	 */
    @Override
	public List<ExternalLink> getExternalLink(String userName) {
        return externalLinkDao.findByHQL("from ExternalLink where userName = ?", userName);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.ExternalLinkService#load(java.lang.Long)
	 */
    @Override
	public ExternalLink getExternalLinkById(Long id) {
        return externalLinkDao.get(ExternalLink.class, id);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.ExternalLinkService#load(java.lang.Long, java.lang.String)
	 */
    @Override
	public ExternalLink getExternalLinkList(Long id, String userName) {
        ExternalLink externalLink = externalLinkDao.findUniqueBy("from ExternalLink where id = ? and userName = ?",ExternalLink.class, id, userName);
        if (externalLink == null) {
            throw new BusinessException("no ExternalLink record",EntityCodes.LINK);
        }
        return externalLink;
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.ExternalLinkService#delete(java.lang.Long)
	 */
    @Override
	public void delete(Long id) {
        externalLinkDao.deleteExternalLinkById(id);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.ExternalLinkService#save(com.legendshop.model.entity.ExternalLink)
	 */
    @Override
	public Long save(ExternalLink externalLink) {
        if (!AppUtils.isBlank(externalLink.getId())) {
            ExternalLink entity = externalLinkDao.get(ExternalLink.class, externalLink.getId());
            if (entity != null) {
                entity.setUrl(externalLink.getUrl());
                entity.setWordlink(externalLink.getWordlink());
                entity.setContent(externalLink.getContent());
                entity.setBs(externalLink.getBs());
                update(entity);
                return externalLink.getId();
            }
            return null;
        }
        return (Long) externalLinkDao.save(externalLink);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.ExternalLinkService#update(com.legendshop.model.entity.ExternalLink)
	 */
    @Override
	public void update(ExternalLink externalLink) {
        externalLinkDao.updateExternalLink(externalLink);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.ExternalLinkService#getDataByCriteriaQuery(com.legendshop.core.dao.support.CriteriaQuery)
	 */
    @Override
	public PageSupport getDataByCriteriaQuery(CriteriaQuery cq) {
        return externalLinkDao.find(cq);
    }
}
