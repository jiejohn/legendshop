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

import com.legendshop.business.dao.BusinessDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.UserComment;
import com.legendshop.spi.constants.Constants;

/**
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * ----------------------------------------------------------------------------
 * -------- 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * --------
 * ----------------------------------------------------------------------------.
 */
@SuppressWarnings("unchecked")
public class BusinessDaoImpl extends BaseDaoImpl implements BusinessDao {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(BusinessDaoImpl.class);

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.BusinessDao#getOrderList(com.legendshop.core.dao.support.CriteriaQuery)
	 */
	@Override
	public PageSupport getOrderList(CriteriaQuery cq) {
		return find(cq, true);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.BusinessDao#processOrder(com.legendshop.core.dao.support.CriteriaQuery)
	 */
	@Override
	public PageSupport getOrder(CriteriaQuery cq) {
		return find(cq);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.BusinessDao#getTotalProcessingOrder(java.lang.String)
	 */
	@Override
	public Long getTotalProcessingOrder(String userName) {
		return findUniqueBy("select count(*) from Sub where subCheck = ? and userName = ?", Long.class,
				Constants.FALSE_INDICATOR, userName);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.BusinessDao#answreWord(java.lang.Long, java.lang.String, java.lang.String)
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

	// 判断用户是否已经订购产品，条件:订单状态为Y
	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.BusinessDao#isUserOrderProduct(java.lang.Long, java.lang.String)
	 */
	@Override
	public boolean isUserOrderProduct(Long prodId, String userName) {
		String sql = "select count(*) from Basket b, Sub s where s.subNumber = b.subNumber and s.subCheck = ? and b.prodId = ? and b.userName = ?";
		Long result = findUniqueBy(sql, Long.class, Constants.TRUE_INDICATOR, prodId, userName);
		return result > 0;
	}

}
