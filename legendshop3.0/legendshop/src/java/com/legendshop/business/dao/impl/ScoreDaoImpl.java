/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.legendshop.business.dao.ScoreDao;
import com.legendshop.business.dao.UserDetailDao;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.exception.BusinessException;
import com.legendshop.core.exception.ErrorCodes;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.Score;
import com.legendshop.model.entity.Sub;
import com.legendshop.model.entity.UserDetail;
import com.legendshop.spi.constants.OrderStatusEnum;
import com.legendshop.spi.constants.SubStatusEnum;

/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * -----------------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * -----------------------------------------------------------------------------------
 * 
 * 官方网站：http://www.legendesign.net
 */
public class ScoreDaoImpl extends SubCommonDaoImpl implements ScoreDao {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(ScoreDaoImpl.class);

	/** The user detail dao. */
	private UserDetailDao userDetailDao;

	// 1元 = ？ 积分
	/** The money per score. */
	private Long moneyPerScore = 1l;

	// 1积分 = ？元
	/** The score per money. */
	private Double scorePerMoney = 0.1;

	// 增加积分
	/** The CREDI t_ score. */
	private final String CREDIT_SCORE = "C";

	// 扣积分
	/** The DEBI t_ score. */
	private final String DEBIT_SCORE = "D";

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ScoreDao#addScore(com.legendshop.model.entity.Sub)
	 */
	@Override
	public void saveScore(Sub sub) {
		log.debug("addScore UserName = {},Score ={} ", sub.getUserName(), sub.getScore());
		// 已经使用了积分的单子不再另外赠送积分
		if (sub == null || sub.getTotal() <= 0 || sub.getScoreId() != null
				|| !PropertiesUtil.getObject(ParameterEnum.USE_SCORE, Boolean.class)) {
			return;
		}
		UserDetail userDetail = userDetailDao.getUserDetailByName(sub.getUserName());
		Long score = userDetail.getScore();
		if (score == null) {
			score = 0l;
		}
		Long core = calScore(sub.getTotal(), CREDIT_SCORE);
		userDetail.setScore(score + core);
		update(userDetail);
		save(makeScore(sub, core, CREDIT_SCORE));
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ScoreDao#useScore(com.legendshop.model.entity.Sub, java.lang.Long)
	 */
	@Override
	public Map<String, Object> deleteScore(Sub sub, Long avaibleScore) {
		if (sub == null || avaibleScore == null || avaibleScore <= 0
				|| !PropertiesUtil.getObject(ParameterEnum.USE_SCORE, Boolean.class)) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		UserDetail userDetail = userDetailDao.getUserDetailByName(sub.getUserName());
		Long orginScore = userDetail.getScore();
		if (orginScore == null) {
			orginScore = 0l;
		}
		if (orginScore - avaibleScore < 0) {
			throw new BusinessException("Not enough score", ErrorCodes.NOT_ENOUGH_SCORE);
		}
		// 最多需要的积分
		Long requiredScore = calRequiredScore(sub.getTotal());
		Long usedScore = null;
		// 积分不足以购买整个商品，所有积分都用掉
		if (requiredScore > avaibleScore) {
			userDetail.setScore(orginScore - avaibleScore); // expect 0
			usedScore = avaibleScore;
			saveSubHistory(sub, SubStatusEnum.DEBIT_SCORE.value());
			sub.setActualTotal(sub.getTotal());
			sub.setTotal(sub.getTotal() - calMoney(avaibleScore));
		} else {
			saveSubHistory(sub, SubStatusEnum.DEBIT_SCORE.value());
			// 积分可以购买整个商品，只是扣除部分积分，订单成交
			sub.setActualTotal(sub.getTotal());// 订单实际现金为0
			sub.setTotal(0d);
			sub.setStatus(OrderStatusEnum.PADYED.value());
			sub.setUpdateDate(new Date());
			userDetail.setScore(avaibleScore - requiredScore);
			usedScore = requiredScore;
		}

		map.put("userScore", userDetail.getScore());
		update(userDetail);
		map.put("subTotal", sub.getTotal());
		Long scoreId = (Long) save(makeScore(sub, usedScore, DEBIT_SCORE));
		sub.setScoreId(scoreId);
		sub.setScore(usedScore);
		update(sub);
		return map;

	}

	/**
	 * 购买整个商品所需要的积分.
	 * 
	 * @param total
	 *            the total
	 * @return the long
	 */
	private Long calRequiredScore(Double total) {
		return ((Double) (Math.ceil(total / scorePerMoney))).longValue();
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ScoreDao#calScore(java.lang.Double, java.lang.String)
	 */
	@Override
	public Long calScore(Double total, String scoreType) {
		if (CREDIT_SCORE.equals(scoreType)) {
			// 加积分取最小值
			return ((Double) (Math.floor(moneyPerScore * total))).longValue();
		} else {
			// 使用积分取最大值
			return ((Double) (Math.ceil(moneyPerScore * total))).longValue();
		}

	}

	/**
	 * Make score.
	 * 
	 * @param sub
	 *            the sub
	 * @param score
	 *            the score
	 * @param scoreType
	 *            the score type
	 * @return the score
	 */
	private Score makeScore(Sub sub, Long score, String scoreType) {
		Score entity = new Score();
		entity.setRecDate(new Date());
		entity.setScore(score);
		entity.setScoreType(scoreType);
		entity.setSubId(sub.getSubId());
		entity.setUserName(sub.getUserName());
		return entity;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ScoreDao#calMoney(java.lang.Long)
	 */
	@Override
	public Double calMoney(Long score) {
		return scorePerMoney * score;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ScoreDao#setMoneyPerScore(java.lang.Long)
	 */
	@Required
	public void setMoneyPerScore(Long moneyPerScore) {
		this.moneyPerScore = moneyPerScore;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ScoreDao#setScorePerMoney(java.lang.Double)
	 */
	@Required
	public void setScorePerMoney(Double scorePerMoney) {
		this.scorePerMoney = scorePerMoney;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ScoreDao#setUserDetailDao(com.legendshop.business.dao.impl.UserDetailDaoImpl)
	 */
	@Required
	public void setUserDetailDao(UserDetailDao userDetailDaoImpl) {
		this.userDetailDao = userDetailDaoImpl;
	}
}
