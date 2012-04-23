package com.legendshop.business.common;

import com.legendshop.core.constant.IntegerEnum;

public enum SortEnum implements IntegerEnum {
	
	NORMAL(0),
	SHOW_IN_INDEX_MENU(1);

	/** The num. */
	private Integer num;

	SortEnum(Integer num) {
		this.num = num;
	}

	@Override
	public Integer value() {
		return num;
	}

}
