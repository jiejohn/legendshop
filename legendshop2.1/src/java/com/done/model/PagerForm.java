package com.done.model;

import java.io.Serializable;

/**
 * 分页
 * @author hewq
 * 2009-0819
 */
public class PagerForm implements Serializable{
	private String curPageNO;

	public String getCurPageNO() {
		return curPageNO;
	}

	public void setCurPageNO(String curPageNO) {
		this.curPageNO = curPageNO;
	}

}
