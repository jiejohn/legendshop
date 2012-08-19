/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao;

import java.util.Map;

import com.legendshop.model.entity.Sub;

public interface ScoreDao extends SubCommonDao{

	/**
	 * 用户增加积分.
	 * 
	 * @param sub
	 *            the sub
	 */
	public abstract void saveScore(Sub sub);

	/**
	 * score:用户可用积分
	 * 
	 * 用户使用积分.
	 * 
	 * @param sub
	 *            the sub
	 * @param avaibleScore
	 *            the avaible score
	 * @return 减去积分优惠之后的该付款数和用户剩下的积分数
	 */
	public abstract Map<String, Object> deleteScore(Sub sub, Long avaibleScore);

	/**
	 * Cal score.
	 * 
	 * @param total
	 *            the total
	 * @param scoreType
	 *            the score type
	 * @return the long
	 */
	public abstract Long calScore(Double total, String scoreType);

	/**
	 * Cal money.
	 * 
	 * @param score
	 *            the score
	 * @return the double
	 */
	public abstract Double calMoney(Long score);

}