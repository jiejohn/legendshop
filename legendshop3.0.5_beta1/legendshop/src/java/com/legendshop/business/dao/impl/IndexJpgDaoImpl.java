/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.legendshop.business.dao.IndexJpgDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Indexjpg;

/**
 * 首页广告Dao.
 */
public class IndexJpgDaoImpl extends BaseDaoImpl implements IndexJpgDao {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(IndexJpgDaoImpl.class);

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.IndexJpgDao#queryIndexJpg(com.legendshop.core.dao.support.CriteriaQuery)
	 */
	@Override
	public PageSupport queryIndexJpg(CriteriaQuery cq) {
		return find(cq);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.IndexJpgDao#queryIndexJpg(java.lang.Long)
	 */
	@Override
	public Indexjpg queryIndexJpg(Long id) {
		return get(Indexjpg.class, id);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.IndexJpgDao#deleteIndexJpg(com.legendshop.model.entity.Indexjpg)
	 */
	@Override
	public void deleteIndexJpg(Indexjpg indexjpg) {
		delete(indexjpg);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.IndexJpgDao#getIndexJpgNum(java.lang.String)
	 */
	@Override
	public Long getIndexJpgNum(String name) {
		return findUniqueBy("select count(*) from Indexjpg where userName = ?", Long.class, name);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.IndexJpgDao#updateIndexjpg(com.legendshop.model.entity.Indexjpg)
	 */
	@Override
	public void updateIndexjpg(Indexjpg origin) {
		update(origin);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.IndexJpgDao#saveIndexjpg(com.legendshop.model.entity.Indexjpg)
	 */
	@Override
	public void saveIndexjpg(Indexjpg indexjpg) {
		save(indexjpg);
	}

}
