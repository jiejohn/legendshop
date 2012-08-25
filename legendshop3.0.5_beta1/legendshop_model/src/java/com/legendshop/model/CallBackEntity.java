/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.model;

import java.io.Serializable;
/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public class CallBackEntity implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8483149238149350917L;
	//返回的连接地址标题
	/** The call back title. */
	private String callBackTitle;
	//返回的连接地址说明
	/** The call back desc. */
	private String callBackDesc;
	//返回的连接地址
	/** The call back href. */
	private String callBackHref;

	/**
	 * Gets the call back title.
	 * 
	 * @return the call back title
	 */
	public String getCallBackTitle() {
		return callBackTitle;
	}

	/**
	 * Sets the call back title.
	 * 
	 * @param callBackTitle
	 *            the new call back title
	 */
	public void setCallBackTitle(String callBackTitle) {
		this.callBackTitle = callBackTitle;
	}

	/**
	 * Gets the call back desc.
	 * 
	 * @return the call back desc
	 */
	public String getCallBackDesc() {
		return callBackDesc;
	}

	/**
	 * Sets the call back desc.
	 * 
	 * @param callBackDesc
	 *            the new call back desc
	 */
	public void setCallBackDesc(String callBackDesc) {
		this.callBackDesc = callBackDesc;
	}

	/**
	 * Gets the call back href.
	 * 
	 * @return the call back href
	 */
	public String getCallBackHref() {
		return callBackHref;
	}

	/**
	 * Sets the call back href.
	 * 
	 * @param callBackHref
	 *            the new call back href
	 */
	public void setCallBackHref(String callBackHref) {
		this.callBackHref = callBackHref;
	}

	/**
	 * Instantiates a new call back entity.
	 * 
	 * @param callBackTitle
	 *            the call back title
	 * @param callBackDesc
	 *            the call back desc
	 * @param callBackHref
	 *            the call back href
	 */
	public CallBackEntity(String callBackTitle, String callBackDesc,
			String callBackHref) {
		this.callBackTitle = callBackTitle;
		this.callBackDesc = callBackDesc;
		this.callBackHref = callBackHref;
	}
}
