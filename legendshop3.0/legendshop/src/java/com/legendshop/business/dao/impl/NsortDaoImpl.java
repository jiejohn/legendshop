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

import net.sf.ehcache.Cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.legendshop.business.common.CacheKeys;
import com.legendshop.business.dao.NsortDao;
import com.legendshop.core.cache.CacheCallBack;
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
	public Nsort getNsort(final Long nsortId) {
		log.debug("queryNsort,nsortId = {} ", nsortId);
		return (Nsort) getObjectFromCache(getKey(CacheKeys.NSORTDAO_GETNSORT, nsortId), new CacheCallBack<Nsort>() {
			@Override
			public Nsort doInCache(String cahceName, Cache cache) {
				Nsort nsort = get(Nsort.class, nsortId);
				if (nsort != null) {
					nsort.setSubSort(new HashSet<Nsort>(findByHQL("from Nsort where parent_nsort_id = ?", nsort
							.getNsortId())));
				}
				return nsort;
			}
		});
	}

	// 得到其他的相关小类
	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.NsortDao#queryNsortList(java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<Nsort> getNsortList(final Long sortId, final Long nsortId) {
		return (List<Nsort>) getObjectFromCache(getKey(CacheKeys.NSORTDAO_GETNSORTLIST, sortId, nsortId),
				new CacheCallBack<List<Nsort>>() {
					@Override
					public List<Nsort> doInCache(String cahceName, Cache cache) {
						return findByHQL("from Nsort where sortId = ? and nsortId <> ?", sortId, nsortId);
					}
				});
	}

	// 得到其他的相关小类
	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.NsortDao#queryNsortList(java.lang.Long)
	 */
	@Override
	public List<Nsort> getNsortList(final Long sortId) {
		return (List<Nsort>) getObjectFromCache(getKey(CacheKeys.NSORTDAO_GETNSORTLIST, sortId),
				new CacheCallBack<List<Nsort>>() {
					@Override
					public List<Nsort> doInCache(String cahceName, Cache cache) {
						return findByHQL("from Nsort where sortId = ?", sortId);
					}
				});

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
	public List<Nsort> getNsortBySortId(final Long sortId) {
		return (List<Nsort>) getObjectFromCache(getKey(CacheKeys.NSORTDAO_GETNSORTBYSORTID, sortId),
				new CacheCallBack<List<Nsort>>() {
					@Override
					public List<Nsort> doInCache(String cahceName, Cache cache) {
						return findByHQL("from Nsort where sortId = ? and parent_nsort_id is null", sortId);
					}
				});

	}

}
