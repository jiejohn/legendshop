/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.legendshop.business.dao.SortDao;
import com.legendshop.core.constant.ProductTypeEnum;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Nsort;
import com.legendshop.model.entity.Sort;

/**
 * 产品分类Dao.
 */
@SuppressWarnings("unchecked")
public class SortDaoImpl extends BaseDaoImpl implements SortDao {

	/** The log. */
	private static Logger log = LoggerFactory.getLogger(SortDaoImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.legendshop.business.dao.impl.SortDao#getSort(java.lang.String,
	 * java.lang.Boolean)
	 */
	@Override
	@Cacheable(value = "SortList")
	public List<Sort> getSort(final String shopName, final Boolean loadAll) {
		return getSort(shopName, ProductTypeEnum.PRODUCT.value(), loadAll);
	}

	@Override
	@Cacheable(value = "SortList")
	public List<Sort> getSort(final String shopName, final String sortType, final Boolean loadAll) {
		log.debug("getSort, shopName = {}, loadAll = {}", shopName, loadAll);
		List<Sort> list = findByHQL("from Sort where userName = ? and sortType = ? order by seq", shopName, sortType);
		if (loadAll) {
			// 找出所有的Nsort
			List<Nsort> nsortList = findByHQL(
					"select n from Nsort n, Sort s where n.sortId = s.sortId and s.userName = ? and s.sortType = ? ",
					shopName, sortType);
			for (int i = 0; i < nsortList.size(); i++) {
				Nsort n1 = nsortList.get(i);
				for (int j = i; j < nsortList.size(); j++) {
					Nsort n2 = nsortList.get(j);
					n1.addSubSort(n2);
					n2.addSubSort(n1);
				}
			}

			for (Sort sort : list) {
				for (Nsort nsort : nsortList) {
					sort.addSubSort(nsort);
				}
			}
		}
		return list;
	}

	@Override
	@Cacheable(value = "SortList")
	public List<Sort> getSort(final String shopName, final String sortType, final boolean loadAll) {
		log.debug("getSort, shopName = {}, loadAll = {}", shopName, loadAll);
		List<Sort> list = findByHQL("from Sort where userName = ? and sortType = ? order by seq", shopName, sortType);
		if (loadAll) {
			// 找出所有的Nsort
			List<Nsort> nsortList = findByHQL(
					"select n from Nsort n, Sort s where n.sortId = s.sortId and s.userName = ? and s.sortType = ?",
					shopName, sortType);
			for (int i = 0; i < nsortList.size(); i++) {
				Nsort n1 = nsortList.get(i);
				for (int j = i; j < nsortList.size(); j++) {
					Nsort n2 = nsortList.get(j);
					n1.addSubSort(n2);
					n2.addSubSort(n1);
				}
			}

			for (Sort sort : list) {
				for (Nsort nsort : nsortList) {
					sort.addSubSort(nsort);
				}
			}
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.legendshop.business.dao.impl.SortDao#getSort(java.lang.Long)
	 */
	@Override
	@Cacheable(value = "Sort", key = "#sortId")
	public Sort getSort(Long sortId) {
		return get(Sort.class, sortId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.dao.impl.SortDao#querySort(com.legendshop.core
	 * .dao.support.CriteriaQuery)
	 */
	@Override
	public PageSupport getSortByCriteria(CriteriaQuery cq) {
		return find(cq);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.dao.impl.SortDao#querySortList(java.lang.String)
	 */
	@Override
	public List<Sort> getSortList(String userName) {
		return findByHQL("from Sort where userName = ?", new Object[] { userName });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.dao.impl.SortDao#deleteSortById(java.lang.Long)
	 */
	@Override
	@CacheEvict(value = "Sort", key = "#sortId")
	public void deleteSortById(Long sortId) {
		deleteById(Sort.class, sortId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.dao.impl.SortDao#updateSort(com.legendshop.model
	 * .entity.Sort)
	 */
	@Override
	public void updateSort(Sort sort) {
		update(sort);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.dao.impl.SortDao#saveSort(com.legendshop.model
	 * .entity.Sort)
	 */
	@Override
	public Long saveSort(Sort sort) {
		return (Long) save(sort);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.dao.impl.SortDao#queryProductBySortId(java.lang
	 * .Long)
	 */
	@Override
	public List getProductBySortId(Long sortId) {
		return findByHQL("from Product where sortId = ?", sortId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.dao.impl.SortDao#queryNsortBySortId(java.lang
	 * .Long)
	 */
	@Override
	public List getNsortBySortId(Long sortId) {
		return findByHQL("from Nsort where sortId = ?", sortId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.dao.impl.SortDao#deleteSort(com.legendshop.model
	 * .entity.Sort)
	 */
	@Override
	@CacheEvict(value = "Sort", key = "#sort.sortId")
	public void deleteSort(Sort sort) {
		delete(sort);
	}

	@Override
	@Cacheable(value = "SortList")
	public List<Sort> getSort(final String shopName, final String sortType, final Integer headerMenu,
			final Integer navigationMenu, final boolean loadAll) {

		log.debug("getSort, shopName = {}, loadAll = {}", shopName, loadAll);

		List<Object> paramList = new ArrayList<Object>();
		String hql = "from Sort where userName = ? ";
		paramList.add(shopName);

		// ,sortType,headerMenu,navigationMenu
		if (sortType != null) {
			hql += " and sortType = ? ";
			paramList.add(sortType);
		}
		if (headerMenu != null) {
			hql += " and headerMenu = ? ";
			paramList.add(headerMenu);
		}
		if (navigationMenu != null) {
			hql += " and navigationMenu = ? ";
			paramList.add(navigationMenu);
		}
		hql += " order by seq ";
		List<Sort> list = findByHQL(hql, paramList.toArray());
		if (loadAll) {
			// 找出所有的Nsort
			List<Nsort> nsortList = findByHQL(
					"select n from Nsort n, Sort s where n.sortId = s.sortId and s.userName = ? and s.sortType = ?",
					shopName, sortType);
			for (int i = 0; i < nsortList.size(); i++) {
				Nsort n1 = nsortList.get(i);
				for (int j = i; j < nsortList.size(); j++) {
					Nsort n2 = nsortList.get(j);
					n1.addSubSort(n2);
					n2.addSubSort(n1);
				}
			}

			for (Sort sort : list) {
				for (Nsort nsort : nsortList) {
					sort.addSubSort(nsort);
				}
			}
		}
		return list;

	}

}
