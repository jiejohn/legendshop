/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import java.util.List;

import com.legendshop.business.dao.DeliveryCorpDao;
import com.legendshop.business.service.DeliveryCorpService;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.DeliveryCorp;
import com.legendshop.util.AppUtils;

/**
 * The Class DeliveryCorpServiceImpl.
 */
public class DeliveryCorpServiceImpl  implements DeliveryCorpService{
    
    /** The delivery corp dao. */
    private DeliveryCorpDao deliveryCorpDao;

    /**
	 * Sets the delivery corp dao.
	 * 
	 * @param deliveryCorpDao
	 *            the new delivery corp dao
	 */
    public void setDeliveryCorpDao(DeliveryCorpDao deliveryCorpDao) {
        this.deliveryCorpDao = deliveryCorpDao;
    }

    /* (non-Javadoc)
     * @see com.legendshop.business.service.DeliveryCorpService#getDeliveryCorp(java.lang.String)
     */
    @Override
	public List<DeliveryCorp> getDeliveryCorp(String userName) {
        return deliveryCorpDao.getDeliveryCorp(userName);
    }

    /* (non-Javadoc)
     * @see com.legendshop.business.service.DeliveryCorpService#getDeliveryCorp(java.lang.Long)
     */
    @Override
	public DeliveryCorp getDeliveryCorp(Long id) {
        return deliveryCorpDao.getDeliveryCorp(id);
    }

    /* (non-Javadoc)
     * @see com.legendshop.business.service.DeliveryCorpService#delete(java.lang.Long)
     */
    @Override
	public void deleteDeliveryCorp(DeliveryCorp deliveryCorp) {
        deliveryCorpDao.deleteDeliveryCorp(deliveryCorp);
    }

    /* (non-Javadoc)
     * @see com.legendshop.business.service.DeliveryCorpService#save(com.legendshop.model.entity.DeliveryCorp)
     */
    @Override
	public Long saveDeliveryCorp(DeliveryCorp deliveryCorp) {
        if (!AppUtils.isBlank(deliveryCorp.getDvyId())) {
            updateDeliveryCorp(deliveryCorp);
            return deliveryCorp.getDvyId();
        }
        return deliveryCorpDao.saveDeliveryCorp(deliveryCorp);
    }

    /* (non-Javadoc)
     * @see com.legendshop.business.service.DeliveryCorpService#update(com.legendshop.model.entity.DeliveryCorp)
     */
    @Override
	public void updateDeliveryCorp(DeliveryCorp deliveryCorp) {
        deliveryCorpDao.updateDeliveryCorp(deliveryCorp);
    }

    /* (non-Javadoc)
     * @see com.legendshop.business.service.DeliveryCorpService#getDeliveryCorp(com.legendshop.core.dao.support.CriteriaQuery)
     */
    @Override
	public PageSupport getDeliveryCorp(CriteriaQuery cq) {
        return deliveryCorpDao.getDeliveryCorp(cq);
    }
}

