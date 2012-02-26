/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.common;

import com.legendshop.core.constant.IntegerEnum;

/**
 * 评论类型.
 */

public enum CommentTypeEnum implements IntegerEnum {
	// 0: 未读消息 1: 已读消息 2.普通消息
	/** The COMMEN t_ u n_ read. */
	COMMENT_UN_READ(0), 
	
	/** The COMMEN t_ readed. */
	COMMENT_READED(1), 
	
	/** The COMMONTALK. */
	COMMONTALK(2);

	/** The num. */
	private Integer num;

	/* (non-Javadoc)
	 * @see com.legendshop.core.constant.IntegerEnum#value()
	 */
	public Integer value() {
		return num;
	}

	/**
	 * Instantiates a new comment type enum.
	 * 
	 * @param num
	 *            the num
	 */
	CommentTypeEnum(Integer num) {
		this.num = num;
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {

	}
}
