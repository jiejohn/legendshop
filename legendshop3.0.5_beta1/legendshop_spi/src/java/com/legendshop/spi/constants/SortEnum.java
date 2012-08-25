package com.legendshop.spi.constants;

import com.legendshop.core.constant.IntegerEnum;

public enum SortEnum implements IntegerEnum {
	
	NORMAL(0),
	SHOW_IN_INDEX_MENU(1);

	/** The num. */
	private Integer num;

	SortEnum(Integer num) {
		this.num = num;
	}

	public Integer value() {
		return num;
	}

}
