/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.model.entity;

import java.io.Serializable;

/**
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public class PagerForm implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1699328271492126860L;
	
	/** The cur page no. */
	private String curPageNO;

	/**
	 * Gets the cur page no.
	 * 
	 * @return the cur page no
	 */
	public String getCurPageNO() {
		return curPageNO;
	}

	/**
	 * Sets the cur page no.
	 * 
	 * @param curPageNO
	 *            the new cur page no
	 */
	public void setCurPageNO(String curPageNO) {
		this.curPageNO = curPageNO;
	}

}
