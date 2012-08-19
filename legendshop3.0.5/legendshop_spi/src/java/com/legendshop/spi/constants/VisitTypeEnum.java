/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.spi.constants;

import com.legendshop.core.constant.StringEnum;
/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public enum VisitTypeEnum implements StringEnum{
	
	/** The INDEX. */
	INDEX("0"),
/** The HW. */
HW("1");
	
	/** The value. */
	private final String value;  
	
	/**
	 * Instantiates a new visit type enum.
	 * 
	 * @param value
	 *            the value
	 */
	private VisitTypeEnum(String value){
		this.value = value;
	}
	
	/* (non-Javadoc)
	 * @see com.legendshop.core.constant.StringEnum#value()
	 */
	public String value() {
		return this.value;
	}
	
}
