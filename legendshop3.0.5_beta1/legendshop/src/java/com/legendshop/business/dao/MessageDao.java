/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao;
 
import com.legendshop.core.dao.BaseDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Message;

/**
 * The Class MessageDao.
 */

public interface MessageDao extends BaseDao {
     
	public abstract Message getMessage(String id);
	
    public abstract void deleteMessage(Message message);
	
	public abstract String saveMessage(Message message);
	
	public abstract void updateMessage(Message message);
	
	public abstract PageSupport getMessage(CriteriaQuery cq);
	
 }

