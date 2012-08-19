/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 给用户看到的 message 页面信息 LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ---------------------------------------------------------------------------- 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public class UserMessages implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2811715881322185950L;

	/** The MESSAG e_ key. */
	public static String MESSAGE_KEY = "User_Messages";

	/** The code. */
	private String code;

	/** The title. */
	private String title;

	/** The desc. */
	private String desc;

	// 返回的list，将会在页面展示
	/** The call back list. */
	private List<CallBackEntity> callBackList = new ArrayList<CallBackEntity>();

	/**
	 * Instantiates a new user messages.
	 */
	public UserMessages() {

	}

	/**
	 * Instantiates a new user messages.
	 * 
	 * @param code
	 *            the code
	 * @param title
	 *            the title
	 * @param desc
	 *            the desc
	 */
	public UserMessages(String code, String title, String desc) {
		this.code = code;
		this.title = title;
		this.desc = desc;
	}

	/**
	 * Gets the title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 * 
	 * @param title
	 *            the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the desc.
	 * 
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * Sets the desc.
	 * 
	 * @param desc
	 *            the new desc
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * Gets the call back list.
	 * 
	 * @return the call back list
	 */
	public List<CallBackEntity> getCallBackList() {
		return callBackList;
	}

	/**
	 * Adds the call back list.
	 * 
	 * @param callBackTitle
	 *            the call back title
	 * @param callBackDesc
	 *            the call back desc
	 * @param callBackHref
	 *            the call back href
	 */
	public void addCallBackList(String callBackTitle, String callBackDesc, String callBackHref) {
		CallBackEntity callback = new CallBackEntity(callBackTitle, callBackDesc, callBackHref);
		callBackList.add(callback);
	}

	/**
	 * Adds the call back list.
	 * 
	 * @param callBackTitle
	 *            the call back title
	 * @param callBackDesc
	 *            the call back desc
	 */
	public void addCallBackList(String callBackTitle, String callBackDesc) {
		CallBackEntity callback = new CallBackEntity(callBackTitle, callBackDesc, null);
		callBackList.add(callback);
	}

	/**
	 * Adds the call back list.
	 * 
	 * @param callBackTitle
	 *            the call back title
	 */
	public void addCallBackList(String callBackTitle) {
		CallBackEntity callback = new CallBackEntity(callBackTitle, null, null);
		callBackList.add(callback);
	}

	/**
	 * Checks for error.
	 * 
	 * @return true, if successful
	 */
	public boolean hasError() {
		return callBackList.size() > 0;
	}

	/**
	 * Sets the call back list.
	 * 
	 * @param callBackList
	 *            the new call back list
	 */
	public void setCallBackList(List<CallBackEntity> callBackList) {
		this.callBackList = callBackList;
	}

	/**
	 * Gets the code.
	 * 
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 * 
	 * @param code
	 *            the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new StringBuffer().append("code = ").append(code).append(",title = ").append(title).append(",desc = ")
				.append(desc).toString();
	}
}
