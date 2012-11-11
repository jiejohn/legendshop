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

import com.legendshop.business.dao.MessageDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Message;
/**
 * The Class MessageDaoImpl.
 */

public class MessageDaoImpl extends BaseDaoImpl implements MessageDao {
    private static Logger log = LoggerFactory.getLogger(MessageDaoImpl.class);

	public Message getMessage(String id){
		return get(Message.class, id);
	}
	
    public void deleteMessage(Message message){
    	delete(message);
    }
	
	public String saveMessage(Message message){
		return (String)save(message);
	}
	
	public void updateMessage(Message message){
		 update(message);
	}
	
	public PageSupport getMessage(CriteriaQuery cq){
		return find(cq);
	}
	
 }

