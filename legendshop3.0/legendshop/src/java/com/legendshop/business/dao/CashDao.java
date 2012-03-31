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
import com.legendshop.model.entity.Cash;

/**
 * The Class CashDao.
 */

public interface CashDao extends BaseDao {
     
    public abstract List<Cash> getCash(String shopName);

	public abstract Cash getCash(Long id);
	
    public abstract void deleteCash(Cash cash);
	
	public abstract Long saveCash(Cash cash);
	
	public abstract void updateCash(Cash cash);
	
	public abstract PageSupport getCash(CriteriaQuery cq);
	
 }

