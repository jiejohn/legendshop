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

import com.legendshop.business.dao.SortDao;
import com.legendshop.business.service.SortService;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.BusinessException;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.model.entity.Nsort;
import com.legendshop.model.entity.Sort;
import com.legendshop.util.AppUtils;


/**
 * 产品分类服务.
 */
public class SortServiceImpl implements SortService  {
    
    /** The sort dao. */
    private SortDao sortDao;

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.SortService#querySortList(java.lang.String)
	 */
	@Override
	public List<Sort> getSortList(String userName) {
    	return sortDao.getSortList(userName);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.SortService#querySort(java.lang.Long)
	 */
    @Override
	public Sort getSortById(Long id) {
    	return sortDao.getSort(id);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.SortService#deleteSort(java.lang.Long)
	 */
    @Override
	public void deleteSort(Long sortId) {
		List list = sortDao.getProductBySortId(sortId);
		if (!AppUtils.isBlank(list)) {
			throw new BusinessException("请删除该类型对应的商品",EntityCodes.SORT);
		}

		List nsortList = sortDao.getNsortBySortId(sortId);
		if (!AppUtils.isBlank(nsortList)) {
			throw new BusinessException("请删除该类型对应的二级商品类型",EntityCodes.SORT);
		}
    	sortDao.deleteSortById(sortId);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.SortService#save(com.legendshop.model.entity.Sort)
	 */
    @Override
	public Long save(Sort sort) {
        if (!AppUtils.isBlank(sort.getSortId())) {
            updateSort(sort);
            return sort.getSortId();
        }
        return sortDao.saveSort(sort);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.SortService#updateSort(com.legendshop.model.entity.Sort)
	 */
    @Override
	public void updateSort(Sort sort) {
    	  sortDao.updateSort(sort);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.SortService#getDataByCriteriaQuery(com.legendshop.core.dao.support.CriteriaQuery)
	 */
    @Override
	public PageSupport getSortList(CriteriaQuery cq) {
        return sortDao.find(cq);
    }
    
    /* (non-Javadoc)
	 * @see com.legendshop.business.service.SortService#getSort(java.lang.String, boolean)
	 */
    @Override
	public List<Sort> getSort(String shopName, boolean loadAll) {
        List<Sort> list = sortDao.findByHQL("from Sort where userName = ? order by seq",  shopName);
        if (loadAll) {
        	//找出所有的Nsort
        	List<Nsort> nsortList = sortDao.findByHQL("select n from Nsort n, Sort s where n.sortId = s.sortId and s.userName = ?", shopName );
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
        	/*
            for (Sort sort : list) {
                sort.setNsort(sortDao.findByHQL("from Nsort where sortId = ? and parentNsortId is null order by seq",
                        new Object[] { sort.getSortId() }));
            }
            */
        }
        return list;
    }
    

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.SortService#delete(com.legendshop.model.entity.Sort)
	 */
	@Override
	public void delete(Sort sort) {
		sortDao.deleteSort(sort);
	}


    /**
	 * Sets the sort dao.
	 * 
	 * @param sortDao
	 *            the new sort dao
	 */
    @Required
    public void setSortDao(SortDao sortDao) {
        this.sortDao = sortDao;
    }


}

