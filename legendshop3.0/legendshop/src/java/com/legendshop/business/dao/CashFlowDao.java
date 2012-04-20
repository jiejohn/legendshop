/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao;
 
import java.util.List;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.CashFlow;

/**
 * The Class CashFlowDao.
 */

public interface CashFlowDao extends BaseDao {
     
    public abstract List<CashFlow> getCashFlow(String shopName);

	public abstract CashFlow getCashFlow(Long id);
	
    public abstract void deleteCashFlow(CashFlow cashFlow);
	
	public abstract Long saveCashFlow(CashFlow cashFlow);
	
	public abstract void updateCashFlow(CashFlow cashFlow);
	
	public abstract PageSupport getCashFlow(CriteriaQuery cq);
	
 }

