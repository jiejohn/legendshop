/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.legendshop.business.dao.NsortDao;
import com.legendshop.core.constant.ProductTypeEnum;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.model.entity.Nsort;

/**
 * 产品子分类Dao.
 */

public class NsortDaoImpl extends BaseDaoImpl implements NsortDao {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(NsortDaoImpl.class);

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.NsortDao#queryNsort(java.lang.Long)
	 */
	@Override
	@Cacheable(value="Nsort",key="#nsortId")
	public Nsort getNsort(final Long nsortId) {
		log.debug("queryNsort,nsortId = {} ", nsortId);
		Nsort nsort = get(Nsort.class, nsortId);
		if (nsort != null) {
			nsort.setSubSort(new HashSet<Nsort>(findByHQL("from Nsort where parent_nsort_id = ?", nsort
					.getNsortId())));
		}
		return nsort;
	}

	// 得到其他的相关小类
	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.NsortDao#queryNsortList(java.lang.Long, java.lang.Long)
	 */
	@Override
	@Cacheable(value="NsortList")
	public List<Nsort> getNsortList(final Long sortId, final Long nsortId) {
		return findByHQL("from Nsort where sortId = ? and nsortId <> ?", sortId, nsortId);
	}

	// 得到其他的相关小类
	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.NsortDao#queryNsortList(java.lang.Long)
	 */
	@Override
	@Cacheable(value="NsortList")
	public List<Nsort> getNsortList(final Long sortId) {
		return findByHQL("from Nsort where sortId = ?", sortId);
	}

	// 得到相关二级小类
	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.NsortDao#queryOthorNsort(java.util.List)
	 */
	@Override
	public List<Nsort> getOthorNsort(List<Nsort> list) {
		List<Nsort> result = new ArrayList<Nsort>();
		for (Nsort nsort : list) {
			if (nsort.getParentNsortId() == null) {
				result.add(nsort);
			}
		}
		return result;
	}

	// 得到相关三级小类
	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.NsortDao#queryOthorSubNsort(java.lang.Long, java.util.List)
	 */
	@Override
	public List<Nsort> getOthorSubNsort(Long InsortId, List<Nsort> list) {
		List<Nsort> result = new ArrayList<Nsort>();
		for (Nsort nsort : list) {
			if ((nsort.getParentNsortId() != null) && nsort.getParentNsortId().equals(InsortId)) {
				result.add(nsort);
			}
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.NsortDao#queryNsortBySortId(java.lang.Long)
	 */
	@Override
	@Cacheable(value="NsortList",key="#sortId")
	public List<Nsort> getNsortBySortId(final Long sortId) {
		return findByHQL("from Nsort where sortId = ? and parent_nsort_id is null", sortId);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.NsortDao#getNavigationNsort(java.lang.String)
	 */
	@Override
	public List<Nsort> getNavigationNsort(String userName) {
		return findByHQL("select n from Nsort n,Sort s where n.sortId=s.id and s.userName=? and s.sortType=? and s.navigationMenu=?", userName,ProductTypeEnum.PRODUCT.value(),1);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.NsortDao#updateNsort(com.legendshop.model.entity.Nsort)
	 */
	@Override
	@CacheEvict(value = "Nsort", key = "#nsort.nsortId")
	public void updateNsort(Nsort nsort) {
		update(nsort);
		
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.NsortDao#deleteNsortById(java.lang.Long)
	 */
	@Override
	@CacheEvict(value = "Nsort", key = "#id")
	public void deleteNsortById(Long id) {
		delete(Nsort.class, id);
		
	}

}
