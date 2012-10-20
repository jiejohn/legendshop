/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.model.entity;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 系統实体抽象类
 * 用于记录操作日志
 */
public abstract class AbstractEntity  implements BaseEntity{
	
	private static final long serialVersionUID = 2382363811364535140L;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	abstract public String getUserName();
	
}
