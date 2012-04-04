/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao;
 
import com.legendshop.core.dao.BaseDao;
import java.util.List;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Partner;

/**
 * The Class PartnerDao.
 */

public interface PartnerDao extends BaseDao {
     
    public abstract List<Partner> getPartner(String shopName);

	public abstract Partner getPartner(Long id);
	
    public abstract void deletePartner(Partner partner);
	
	public abstract Long savePartner(Partner partner);
	
	public abstract void updatePartner(Partner partner);
	
	public abstract PageSupport getPartner(CriteriaQuery cq);
	
 }

