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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.legendshop.business.dao.HotsearchDao;
import com.legendshop.business.service.HotsearchService;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.BusinessException;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.exception.PermissionException;
import com.legendshop.model.entity.Hotsearch;
import com.legendshop.util.AppUtils;

/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * -----------------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * -----------------------------------------------------------------------------------
 * 
 * 官方网站：http://www.legendesign.net
 */
public class HotsearchServiceImpl implements HotsearchService {
	
	/** The log. */
	Logger log = LoggerFactory.getLogger(HotsearchServiceImpl.class);

	/** The hotsearch dao. */
	private HotsearchDao hotsearchDao;

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.HotsearchService#list(java.lang.String)
	 */
	@Override
	public List<Hotsearch> getHotsearch(String userName) {
		return hotsearchDao.findByHQL("from Hotsearch where userName = ?", new Object[] { userName });
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.HotsearchService#load(java.lang.Long)
	 */
	@Override
	public Hotsearch getHotsearchById(Long id) {
		return hotsearchDao.get(Hotsearch.class, id);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.HotsearchService#load(java.lang.Integer, java.lang.String)
	 */
	@Override
	public Hotsearch getHotsearchByIdAndName(Integer id, String userName) {
		Hotsearch hotsearch = hotsearchDao.findUniqueBy("from Hotsearch where id = ? and userName = ?",
				Hotsearch.class, id, userName);
		if (hotsearch == null) {
			throw new BusinessException("no Hotsearch record",EntityCodes.PROD);
		}
		return hotsearch;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.HotsearchService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		hotsearchDao.deleteById(Hotsearch.class, id);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.HotsearchService#save(com.legendshop.model.entity.Hotsearch, java.lang.String, boolean)
	 */
	@Override
	public Long save(Hotsearch hotsearch, String userName, boolean viewAllDataFunction) {
		if (!AppUtils.isBlank(hotsearch.getId())) {
			Hotsearch entity = hotsearchDao.get(Hotsearch.class, hotsearch.getId());
			if (entity != null) {
				if (!viewAllDataFunction && !userName.equals(entity.getUserName())) {
					throw new PermissionException("Can't edit Hotsearch does not onw to you!",EntityCodes.PROD);
				}
				entity.setDate(new Date());
				entity.setMsg(hotsearch.getMsg());
				entity.setTitle(hotsearch.getTitle());
				entity.setSort(hotsearch.getSort());
				update(entity);
				return hotsearch.getId();
			}
			return null;
		}
		return (Long) hotsearchDao.merge(hotsearch);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.HotsearchService#update(com.legendshop.model.entity.Hotsearch)
	 */
	@Override
	public void update(Hotsearch hotsearch) {
		hotsearchDao.update(hotsearch);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.HotsearchService#getDataByCriteriaQuery(com.legendshop.core.dao.support.CriteriaQuery)
	 */
	@Override
	public PageSupport getDataByCriteriaQuery(CriteriaQuery cq) {
		return hotsearchDao.find(cq);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.HotsearchService#getSearch(java.lang.String, java.lang.Long)
	 */
	@Override
	public List<Hotsearch> getSearch(String userName, Long sortId) {
		return hotsearchDao.getSearch(userName, sortId);
	}
	
	/**
	 * Sets the hotsearch dao.
	 * 
	 * @param hotsearchDao
	 *            the new hotsearch dao
	 */
	@Required
	public void setHotsearchDao(HotsearchDao hotsearchDao) {
		this.hotsearchDao = hotsearchDao;
	}
}
