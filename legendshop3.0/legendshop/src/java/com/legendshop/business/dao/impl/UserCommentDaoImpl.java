/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.legendshop.business.dao.UserCommentDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.UserComment;

/**
 * 用户产品评论Dao.
 */
public class UserCommentDaoImpl extends BaseDaoImpl implements UserCommentDao {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(UserCommentDaoImpl.class);

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.UserCommentDao#queryUserComment(com.legendshop.core.dao.support.CriteriaQuery)
	 */
	@Override
	public PageSupport getUserCommentByCriteria(CriteriaQuery cq) {
		return find(cq);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.UserCommentDao#queryUserComment(java.lang.Long)
	 */
	@Override
	public UserComment getUserComment(Long id) {
		return get(UserComment.class, id);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.UserCommentDao#deleteUserComment(com.legendshop.model.entity.UserComment)
	 */
	@Override
	public void deleteUserComment(UserComment userComment) {
		delete(userComment);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.UserCommentDao#updateUserComment(com.legendshop.model.entity.UserComment)
	 */
	@Override
	public void updateUserComment(UserComment comment) {
		update(comment);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.UserCommentDao#saveOrUpdateUserComment(com.legendshop.model.entity.UserComment)
	 */
	@Override
	public void saveOrUpdateUserComment(UserComment comment) {
		saveOrUpdate(comment);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.UserCommentDao#updateUserComment(java.lang.Long, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean updateUserComment(Long id, String answer, String toUserName) {
		UserComment comment = get(UserComment.class, id);
		if (comment != null) {
			if (!comment.getToUserName().equals(toUserName)) {
				log.debug("toUserName try to answer comments own to " + comment.getToUserName() + " ,but fail");
				return false;
			}
			comment.setAnswer(answer);
			comment.setAnswertime(new Date());
			update(comment);
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.UserCommentDao#getTotalUnReadMessage(java.lang.String)
	 */
	@Override
	public Long getTotalUnReadMessage(String userName) {
		return findUniqueBy("select count(*) from UserComment where status = ? and toUserName = ?", Long.class, 0, userName);
	}


}
