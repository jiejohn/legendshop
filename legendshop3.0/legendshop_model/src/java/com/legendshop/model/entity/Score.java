/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.model.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * --------------------------------------------
 * ----------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ------------------
 * ------------------------------------------------------------------.
 */
public class Score implements BaseEntity {

	/** The score id. */
	private Long scoreId;

	/** The sub id. */
	private Long subId;

	/** The score. */
	private Long score;

	/** The score type. */
	private String scoreType;

	/** The user name. */
	private String userName;

	/** The rec date. */
	private Date recDate;

	// Constructors

	/**
	 * default constructor.
	 */
	public Score() {
	}

	/**
	 * Gets the score id.
	 * 
	 * @return the score id
	 */
	public Long getScoreId() {
		return scoreId;
	}

	/**
	 * Sets the score id.
	 * 
	 * @param scoreId
	 *            the new score id
	 */
	public void setScoreId(Long scoreId) {
		this.scoreId = scoreId;
	}

	/**
	 * Gets the sub id.
	 * 
	 * @return the sub id
	 */
	public Long getSubId() {
		return subId;
	}

	/**
	 * Sets the sub id.
	 * 
	 * @param subId
	 *            the new sub id
	 */
	public void setSubId(Long subId) {
		this.subId = subId;
	}

	/**
	 * Gets the score.
	 * 
	 * @return the score
	 */
	public Long getScore() {
		return score;
	}

	/**
	 * Sets the score.
	 * 
	 * @param score
	 *            the new score
	 */
	public void setScore(Long score) {
		this.score = score;
	}

	/**
	 * Gets the score type.
	 * 
	 * @return the score type
	 */
	public String getScoreType() {
		return scoreType;
	}

	/**
	 * Sets the score type.
	 * 
	 * @param scoreType
	 *            the new score type
	 */
	public void setScoreType(String scoreType) {
		this.scoreType = scoreType;
	}

	/**
	 * Gets the rec date.
	 * 
	 * @return the rec date
	 */
	public Date getRecDate() {
		return recDate;
	}

	/**
	 * Sets the rec date.
	 * 
	 * @param recDate
	 *            the new rec date
	 */
	public void setRecDate(Date recDate) {
		this.recDate = recDate;
	}

	/**
	 * Gets the user name.
	 * 
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the user name.
	 * 
	 * @param userName
	 *            the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Serializable getId() {
		return scoreId;
	}

}