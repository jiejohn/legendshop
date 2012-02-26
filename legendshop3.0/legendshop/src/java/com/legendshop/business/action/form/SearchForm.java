/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.action.form;



/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public class SearchForm{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2489827230218323847L;
	
	/** The cur page no top. */
	private String curPageNOTop = "1";
	
	/** The keyword. */
	private String keyword;//需要搜索的关键字
	
	/** The sort id. */
	private Long sortId;
	
	/**
	 * Gets the cur page no top.
	 * 
	 * @return the cur page no top
	 */
	public String getCurPageNOTop() {
		return curPageNOTop;
	}

	/**
	 * Sets the cur page no top.
	 * 
	 * @param curPageNOTop
	 *            the new cur page no top
	 */
	public void setCurPageNOTop(String curPageNOTop) {
		this.curPageNOTop = curPageNOTop;
	}

	/**
	 * Gets the keyword.
	 * 
	 * @return the keyword
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * Sets the keyword.
	 * 
	 * @param keyword
	 *            the new keyword
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * Gets the sort id.
	 * 
	 * @return the sort id
	 */
	public Long getSortId() {
		return sortId;
	}

	/**
	 * Sets the sort id.
	 * 
	 * @param sortId
	 *            the new sort id
	 */
	public void setSortId(Long sortId) {
		this.sortId = sortId;
	}

	
}