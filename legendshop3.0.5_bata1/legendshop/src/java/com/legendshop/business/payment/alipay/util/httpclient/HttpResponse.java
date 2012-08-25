/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.payment.alipay.util.httpclient;

import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.Header;

import com.legendshop.business.payment.alipay.config.AlipayConfig;

/* *
 *类名：HttpResponse
 *功能：Http返回对象的封装
 *详细：封装Http返回信息
 *版本：3.2
 *日期：2011-03-17
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

/**
 * The Class HttpResponse.
 */
public class HttpResponse {

	/** 返回中的Header信息. */
	private Header[] responseHeaders;

	/** String类型的result. */
	private String stringResult;

	/** btye类型的result. */
	private byte[] byteResult;

	/**
	 * Gets the response headers.
	 * 
	 * @return the response headers
	 */
	public Header[] getResponseHeaders() {
		return responseHeaders;
	}

	/**
	 * Sets the response headers.
	 * 
	 * @param responseHeaders
	 *            the new response headers
	 */
	public void setResponseHeaders(Header[] responseHeaders) {
		this.responseHeaders = responseHeaders;
	}

	/**
	 * Gets the byte result.
	 * 
	 * @return the byte result
	 */
	public byte[] getByteResult() {
		if (byteResult != null) {
			return byteResult;
		}
		if (stringResult != null) {
			return stringResult.getBytes();
		}
		return null;
	}

	/**
	 * Sets the byte result.
	 * 
	 * @param byteResult
	 *            the new byte result
	 */
	public void setByteResult(byte[] byteResult) {
		this.byteResult = byteResult;
	}

	/**
	 * Gets the string result.
	 * 
	 * @return the string result
	 * @throws UnsupportedEncodingException
	 *             the unsupported encoding exception
	 */
	public String getStringResult() throws UnsupportedEncodingException {
		if (stringResult != null) {
			return stringResult;
		}
		if (byteResult != null) {
			return new String(byteResult, AlipayConfig.input_charset);
		}
		return null;
	}

	/**
	 * Sets the string result.
	 * 
	 * @param stringResult
	 *            the new string result
	 */
	public void setStringResult(String stringResult) {
		this.stringResult = stringResult;
	}

}
