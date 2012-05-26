/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.legendshop.business.dao.UserCommentDao;
import com.legendshop.business.service.UserCommentService;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.UserComment;
import com.legendshop.spi.constants.CommentTypeEnum;

/**
 * 用户产品评论服务.
 */
public class UserCommentServiceImpl implements UserCommentService {
	
	/** The log. */
	Logger log = LoggerFactory.getLogger(UserCommentServiceImpl.class);

	/** The user comment dao. */
	private UserCommentDao userCommentDao;

	/**
	 * Sets the user comment dao.
	 * 
	 * @param userCommentDao
	 *            the new user comment dao
	 */
	@Required
	public void setUserCommentDao(UserCommentDao userCommentDao) {
		this.userCommentDao = userCommentDao;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.UserCommentService#queryUserComment(com.legendshop.core.dao.support.CriteriaQuery)
	 */
	@Override
	public PageSupport getUserCommentList(CriteriaQuery cq) {
		return userCommentDao.getUserCommentByCriteria(cq);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.UserCommentService#queryUserComment(java.lang.Long)
	 */
	@Override
	public UserComment getUserComment(Long id) {
		return userCommentDao.getUserComment(id);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.UserCommentService#delete(com.legendshop.model.entity.UserComment)
	 */
	@Override
	public void delete(UserComment userComment) {
		userCommentDao.deleteUserComment(userComment);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.UserCommentService#updateUserCommentToReaded(com.legendshop.model.entity.UserComment)
	 */
	@Override
	public void updateUserCommentToReaded(UserComment comment) {
		if (!comment.getStatus().equals(CommentTypeEnum.COMMENT_READED.value())) {
			comment.setStatus(CommentTypeEnum.COMMENT_READED.value());
			userCommentDao.updateUserComment(comment);
		}
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.UserCommentService#saveOrUpdateUserComment(com.legendshop.model.entity.UserComment)
	 */
	@Override
	public void saveOrUpdateUserComment(UserComment comment) {
		userCommentDao.saveOrUpdateUserComment(comment);
		
	}

}
