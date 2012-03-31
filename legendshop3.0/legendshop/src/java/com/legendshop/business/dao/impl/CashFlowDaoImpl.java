/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao.impl;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.model.entity.CashFlow;
import com.legendshop.business.dao.CashFlowDao;
/**
 * The Class CashFlowDaoImpl.
 */

public class CashFlowDaoImpl extends BaseDaoImpl implements CashFlowDao {
    private static Logger log = LoggerFactory.getLogger(CashFlowDaoImpl.class);
     
    public List<CashFlow> getCashFlow(String userName){
   		return findByHQL("from CashFlow where userName = ?", userName);
    }

	public CashFlow getCashFlow(Long id){
		return get(CashFlow.class, id);
	}
	
    public void deleteCashFlow(CashFlow cashFlow){
    	delete(cashFlow);
    }
	
	public Long saveCashFlow(CashFlow cashFlow){
		return (Long)save(cashFlow);
	}
	
	public void updateCashFlow(CashFlow cashFlow){
		 update(cashFlow);
	}
	
	public PageSupport getCashFlow(CriteriaQuery cq){
		return find(cq);
	}
	
 }

