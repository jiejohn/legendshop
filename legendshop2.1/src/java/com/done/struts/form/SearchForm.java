package com.done.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;


/**
 * 用户搜索Form
 * @author hewq
 * 2009-08-20
 */

public class SearchForm extends ValidatorForm {
	private String curPageNOTop = "1";
	private String keyword;//需要搜索的关键字
	private Integer sortId;
	
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		return null;
	}

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		
	}


	public String getCurPageNOTop() {
		return curPageNOTop;
	}

	public void setCurPageNOTop(String curPageNOTop) {
		this.curPageNOTop = curPageNOTop;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getSortId() {
		return sortId;
	}

	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}

	
}