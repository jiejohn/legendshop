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
import com.legendshop.model.entity.Cash;
import com.legendshop.business.dao.CashDao;
/**
 * The Class CashDaoImpl.
 */

public class CashDaoImpl extends BaseDaoImpl implements CashDao {
    private static Logger log = LoggerFactory.getLogger(CashDaoImpl.class);
     
    public List<Cash> getCash(String userName){
   		return findByHQL("from Cash where userName = ?", userName);
    }

	public Cash getCash(Long id){
		return get(Cash.class, id);
	}
	
    public void deleteCash(Cash cash){
    	delete(cash);
    }
	
	public Long saveCash(Cash cash){
		return (Long)save(cash);
	}
	
	public void updateCash(Cash cash){
		 update(cash);
	}
	
	public PageSupport getCash(CriteriaQuery cq){
		return find(cq);
	}
	
 }

