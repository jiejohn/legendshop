/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import java.util.Map;

import com.legendshop.business.dao.ScoreDao;
import com.legendshop.business.service.ScoreService;
import com.legendshop.model.entity.Sub;

/**
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * ----------------------------------------------------------------------------
 * -------- 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * --------
 * ----------------------------------------------------------------------------.
 */
public class ScoreServiceImpl implements ScoreService {

	/** The score dao. */
	private ScoreDao scoreDao;

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.ScoreService#addScore(com.legendshop.model.entity.Sub)
	 */
	@Override
	public void addScore(Sub sub) {
		scoreDao.saveScore(sub);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.ScoreService#useScore(com.legendshop.model.entity.Sub, java.lang.Long)
	 */
	@Override
	public Map<String, Object> useScore(Sub sub, Long avaibleScore) {
		return scoreDao.deleteScore(sub, avaibleScore);

	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.ScoreService#calScore(java.lang.Double, java.lang.String)
	 */
	@Override
	public Long calScore(Double total, String scoreType) {
		return scoreDao.calScore(total, scoreType);

	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.ScoreService#calMoney(java.lang.Long)
	 */
	@Override
	public Double calMoney(Long score) {
		return scoreDao.calMoney(score);
	}

	/**
	 * Sets the score dao.
	 * 
	 * @param scoreDao
	 *            the new score dao
	 */
	public void setScoreDao(ScoreDao scoreDao) {
		this.scoreDao = scoreDao;
	}

}
