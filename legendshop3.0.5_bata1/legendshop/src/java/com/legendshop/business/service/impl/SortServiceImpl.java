/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.legendshop.business.common.page.TilesPage;
import com.legendshop.business.dao.NsortDao;
import com.legendshop.business.dao.ProductDao;
import com.legendshop.business.dao.SortDao;
import com.legendshop.core.UserManager;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.BusinessException;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.model.entity.Nsort;
import com.legendshop.model.entity.Sort;
import com.legendshop.spi.service.SortService;
import com.legendshop.util.AppUtils;


/**
 * 产品分类服务.
 */
public class SortServiceImpl extends BaseServiceImpl implements SortService  {
    
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(SortServiceImpl.class);
	
    /** The sort dao. */
    private SortDao sortDao;
    
    private NsortDao nsortDao;
    
    private ProductDao productDao;

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
    	return sortDao.getSort(shopName, loadAll);
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

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.SortService#getSort(java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public List<Sort> getSort(String name, String sortType, boolean loadAll) {
		return sortDao.getSort(name,sortType, loadAll);
	}

	@Override
	public List<Sort> getSort(String name, String sortType, Integer headerMenu, Integer navigationMenu, boolean loadAll) {		
		return sortDao.getSort(name, sortType, headerMenu, navigationMenu, loadAll);
	}
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.BusinessService#nsort(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String getSecSort(HttpServletRequest request, HttpServletResponse response,Long sortId,Long nsortId,Long subNsortId) {
		String curPageNO = request.getParameter("curPageNO");
		if(curPageNO == null){
			curPageNO = "1";
		}
		String userName = UserManager.getUsername(request);
		log.info("{},{},{},{},{},nsort", new Object[] { request.getRemoteAddr(), userName == null ? "" : userName,
				ThreadLocalContext.getCurrentShopName(request, response), sortId, nsortId });
		Sort sort = sortDao.getSort(sortId);
		if (sort != null) {
			setShopName(request, response, sort.getUserName());
			getAndSetAdvertisement(request,response,sort.getUserName());
			request.setAttribute("sort", sort);
		}
		Nsort nsort = nsortDao.getNsort(nsortId);
		if ((nsort != null) && !AppUtils.isBlank(nsort.getSubSort())) {
			request.setAttribute("hasSubSort", true);
		}
		if (subNsortId != null) {
			Nsort subNsort = nsortDao.getNsort(subNsortId);
			request.setAttribute("subNsort", subNsort);
			if (subNsort != null) {
				request.setAttribute("CurrentSubNsortId", subNsort.getNsortId());
			}
		}

		List<Nsort> nsortList = nsortDao.getNsortList(sortId);
		request.setAttribute("nsort", nsort);

		request.setAttribute("nsortList", nsortDao.getOthorNsort(nsortList));
		request.setAttribute("subNsortList", nsortDao.getOthorSubNsort(nsortId, nsortList));
		if (nsort != null) {
			request.setAttribute("CurrentNsortId", nsort.getNsortId());
		}
		PageSupport ps = productDao.getProdDetail(localeResolver.resolveLocale(request), curPageNO,  sortId, nsortId, subNsortId);
		ps.savePage(request);
		
		return PathResolver.getPath(request,response,TilesPage.NSORT);
	}

	public void setNsortDao(NsortDao nsortDao) {
		this.nsortDao = nsortDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

}

