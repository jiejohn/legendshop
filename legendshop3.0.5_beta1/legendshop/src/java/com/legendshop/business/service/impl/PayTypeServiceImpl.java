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

import com.legendshop.business.dao.PayTypeDao;
import com.legendshop.business.service.PayTypeService;
import com.legendshop.core.OperationTypeEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.event.impl.FireEvent;
import com.legendshop.event.EventHome;
import com.legendshop.model.entity.PayType;

/**
 * 付费类型服务.
 */
public class PayTypeServiceImpl implements PayTypeService  {
    
    /** The base dao. */
    private PayTypeDao payTypeDao;

    /**
	 * Sets the base dao.
	 * 
	 * @param payTypeDao
	 *            the new base dao
	 */
    @Required
    public void setPayTypeDao(PayTypeDao payTypeDao) {
        this.payTypeDao = payTypeDao;
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.PayTypeService#list(java.lang.String)
	 */
    @Override
	public List<PayType> getPayTypeList(String userName) {
        return payTypeDao.findByHQL("from PayType where userName = ?",userName);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.PayTypeService#load(java.lang.Long)
	 */
    @Override
	public PayType getPayTypeById(Long id) {
        return payTypeDao.get(PayType.class, id);
    }
    
    /* (non-Javadoc)
	 * @see com.legendshop.business.service.PayTypeService#load(java.lang.String, java.lang.Integer)
	 */
    @Override
	public PayType getPayTypeByIdAndName(String userName,Integer payTypeId) {
    	return payTypeDao.findUniqueBy("from PayType where userName = ? and payTypeId = ?",PayType.class, userName ,payTypeId);
    }
    
    /* (non-Javadoc)
	 * @see com.legendshop.business.service.PayTypeService#delete(java.lang.Long)
	 */
    @Override
	public void delete(Long id) {
    	PayType payType = getPayTypeById(id);
    	if(payType != null){
        	EventHome.publishEvent(new FireEvent(payType, OperationTypeEnum.DELETE));
    		 payTypeDao.deletePayTypeById(id);
    	}
       
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.PayTypeService#save(com.legendshop.model.entity.PayType)
	 */
    @Override
	public Long save(PayType payType) {
        return (Long) payTypeDao.save(payType);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.PayTypeService#update(com.legendshop.model.entity.PayType)
	 */
    @Override
	public void update(PayType payType) {
    	EventHome.publishEvent(new FireEvent(payType, OperationTypeEnum.UPDATE));
        payTypeDao.updatePayType(payType);
    }

    /* (non-Javadoc)
	 * @see com.legendshop.business.service.PayTypeService#getDataByCriteriaQuery(com.legendshop.core.dao.support.CriteriaQuery)
	 */
    @Override
	public PageSupport getPayTypeList(CriteriaQuery cq) {
        return payTypeDao.find(cq);
    }
}

