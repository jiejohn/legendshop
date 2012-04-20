/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.permission.form;


/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
abstract public class BaseValidatorForm{
	// Instance Variables
	/** The myaction. */
	private String myaction = "";

	/** The cur page no. */
	private int curPageNO = 1;

	/** The str array. */
	private String strArray[] = new String[0];

	/**
	 * Instantiates a new base validator form.
	 */
	public BaseValidatorForm() {
	}

	/**
	 * Gets the cur page no.
	 * 
	 * @return the cur page no
	 */
	public int getCurPageNO() {
		return curPageNO;
	}

	/**
	 * Sets the cur page no.
	 * 
	 * @param curPageNO
	 *            the new cur page no
	 */
	public void setCurPageNO(int curPageNO) {
		this.curPageNO = curPageNO;
	}

	/**
	 * Gets the str array.
	 * 
	 * @return the str array
	 */
	public String[] getStrArray() {
		return strArray;
	}

	/**
	 * Sets the str array.
	 * 
	 * @param strArray
	 *            the new str array
	 */
	public void setStrArray(String[] strArray) {
		this.strArray = strArray;
	}

	/**
	 * Gets the myaction.
	 * 
	 * @return the myaction
	 */
	public String getMyaction() {
		return myaction;
	}

	/**
	 * Sets the myaction.
	 * 
	 * @param myaction
	 *            the new myaction
	 */
	public void setMyaction(String myaction) {
		this.myaction = myaction;
	}
}
