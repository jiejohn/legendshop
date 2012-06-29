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

import com.legendshop.business.dao.BrandDao;
import com.legendshop.business.service.BrandService;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Brand;
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
public class BrandServiceImpl implements BrandService  {
    
    /** The Brand dao. */
    private BrandDao brandDao;

    /**
	 * Sets the base dao.
	 * 
	 * @param brandDao
	 *            the brandDao
	 */
    @Required
    public void setBrandDao(BrandDao brandDao) {
        this.brandDao = brandDao;
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.BrandService#list(java.lang.String)
	 */
    @Override
	public List<Brand> getBrand(String userName) {
        return brandDao.findByHQL("from Brand where userName = ?", new Object[] { userName });
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.BrandService#load(java.lang.Long)
	 */
    @Override
	public Brand getBrand(Long id) {
        return brandDao.get(Brand.class, id);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.BrandService#delete(java.lang.Long)
	 */
    @Override
	public void delete(Long id) {
        brandDao.deleteBrandById(id);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.BrandService#save(com.legendshop.model.entity.Brand)
	 */
    @Override
	public Long save(Brand brand) {
        if (!AppUtils.isBlank(brand.getBrandId())) {
            update(brand);
            return brand.getBrandId();
        }
        return (Long) brandDao.save(brand);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.BrandService#update(com.legendshop.model.entity.Brand)
	 */
    @Override
	public void update(Brand brand) {
        brandDao.updateBrand(brand);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.BrandService#getDataByCriteriaQuery(com.legendshop.core.dao.support.CriteriaQuery)
	 */
    @Override
	public PageSupport getDataByCriteriaQuery(CriteriaQuery cq) {
        return brandDao.find(cq);
    }

	@Override
	public String saveBrandItem(List<String> idList, Long nsortId, String userName) {
		
		return brandDao.saveBrandItem(idList, nsortId, userName);

	}
}

