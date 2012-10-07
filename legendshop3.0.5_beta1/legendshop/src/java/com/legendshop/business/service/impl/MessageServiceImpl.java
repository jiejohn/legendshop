/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import java.util.List;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.util.AppUtils;
import com.legendshop.business.dao.MessageDao;
import com.legendshop.model.entity.Message;
import com.legendshop.business.service.MessageService;

/**
 * The Class MessageServiceImpl.
 */
public class MessageServiceImpl  implements MessageService{
    private MessageDao messageDao;

    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public Message getMessage(String id) {
        return messageDao.getMessage(id);
    }

    public void deleteMessage(Message message) {
        messageDao.deleteMessage(message);
    }

    public String saveMessage(Message message) {
        if (!AppUtils.isBlank(message.getMsgId())) {
            updateMessage(message);
            return message.getMsgId();
        }
        return (String) messageDao.save(message);
    }

    public void updateMessage(Message message) {
        messageDao.updateMessage(message);
    }

    public PageSupport getMessage(CriteriaQuery cq) {
        return messageDao.find(cq);
    }
}

