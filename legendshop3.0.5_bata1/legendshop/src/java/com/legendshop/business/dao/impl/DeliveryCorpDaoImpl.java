/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao.impl;
 
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.legendshop.business.dao.DeliveryCorpDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.DeliveryCorp;
/**
 * The Class DeliveryCorpDaoImpl.
 */

public class DeliveryCorpDaoImpl extends BaseDaoImpl implements DeliveryCorpDao {
    
    /** The log. */
    private static Logger log = LoggerFactory.getLogger(DeliveryCorpDaoImpl.class);
     
    /* (non-Javadoc)
     * @see com.legendshop.business.dao.DeliveryCorpDao#getDeliveryCorp(java.lang.String)
     */
    @Override
	public List<DeliveryCorp> getDeliveryCorp(String userName){
   		return findByHQL("from DeliveryCorp where userName = ?", userName);
    }

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.DeliveryCorpDao#getDeliveryCorp(java.lang.Long)
	 */
	@Override
	public DeliveryCorp getDeliveryCorp(Long id){
		return get(DeliveryCorp.class, id);
	}
	
    /* (non-Javadoc)
     * @see com.legendshop.business.dao.DeliveryCorpDao#deleteDeliveryCorp(java.lang.Long)
     */
    @Override
	public void deleteDeliveryCorp(DeliveryCorp deliveryCorp){
    	delete(deliveryCorp);
    }
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.DeliveryCorpDao#saveDeliveryCorp(com.legendshop.model.entity.DeliveryCorp)
	 */
	@Override
	public Long saveDeliveryCorp(DeliveryCorp deliveryCorp){
		return (Long)save(deliveryCorp);
	}
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.DeliveryCorpDao#updateDeliveryCorp(com.legendshop.model.entity.DeliveryCorp)
	 */
	@Override
	public void updateDeliveryCorp(DeliveryCorp deliveryCorp){
		 update(deliveryCorp);
	}
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.DeliveryCorpDao#getDeliveryCorp(com.legendshop.core.dao.support.CriteriaQuery)
	 */
	@Override
	public PageSupport getDeliveryCorp(CriteriaQuery cq){
		return find(cq);
	}
	
 }

