/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service;

import java.util.List;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Message;

/**
 * The Class MessageService.
 */
public interface MessageService  {

	public Message getMessage(String id);
    
    public void deleteMessage(Message message);
    
    public String saveMessage(Message message);

    public void updateMessage(Message message);

    public PageSupport getMessage(CriteriaQuery cq);
}

