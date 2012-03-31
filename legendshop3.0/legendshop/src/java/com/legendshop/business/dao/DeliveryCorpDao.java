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
import com.legendshop.model.entity.DeliveryCorp;

/**
 * The Class DeliveryCorpDao.
 */

public interface DeliveryCorpDao extends BaseDao {
     
    public abstract List<DeliveryCorp> getDeliveryCorp(String shopName);

	public abstract DeliveryCorp getDeliveryCorp(Long id);
	
    public abstract void deleteDeliveryCorp(DeliveryCorp deliveryCorp);
	
	public abstract Long saveDeliveryCorp(DeliveryCorp deliveryCorp);
	
	public abstract void updateDeliveryCorp(DeliveryCorp deliveryCorp);
	
	public abstract PageSupport getDeliveryCorp(CriteriaQuery cq);
	
 }

