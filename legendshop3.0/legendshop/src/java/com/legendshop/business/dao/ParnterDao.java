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
import com.legendshop.model.entity.Parnter;

/**
 * The Class ParnterDao.
 */

public interface ParnterDao extends BaseDao {
     
    public abstract List<Parnter> getParnter(String shopName);

	public abstract Parnter getParnter(Long id);
	
    public abstract void deleteParnter(Parnter parnter);
	
	public abstract Long saveParnter(Parnter parnter);
	
	public abstract void updateParnter(Parnter parnter);
	
	public abstract PageSupport getParnter(CriteriaQuery cq);
	
 }

