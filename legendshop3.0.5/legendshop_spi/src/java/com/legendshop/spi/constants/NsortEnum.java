package com.legendshop.spi.constants;

import com.legendshop.core.constant.IntegerEnum;

public enum NsortEnum implements IntegerEnum {
	
	NO(0),
	YES(1);

	/** The num. */
	private Integer num;

	NsortEnum(Integer num) {
		this.num = num;
	}

	public Integer value() {
		return num;
	}

}
