/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.core.tag;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.jsp.JspException;

import org.apache.commons.lang.StringUtils;

import com.legendshop.core.AttributeKeys;


/**
 * The Class URLTag.
 */
public class URLTag extends LegendShopTag {
	
	/** The Constant URL_ENCODE. */
	public static final String URL_ENCODE ="UTF-8";
	
	/** The address. */
	private String address;
	
	/** The encode. */
	private boolean encode;

	/**
	 * Do tag.
	 * 
	 * @throws JspException
	 *             the jsp exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag()
	 */
	@Override
	public void doTag() throws JspException, IOException {

		StringBuilder urlBuilder = new StringBuilder(128)
						.append(this.request().getContextPath());
		
		if(encode){
			if(this.address == null)
				this.address="";
			String[] addresses = this.address.split("/");
			for(String _address : addresses){
				if(StringUtils.isNotEmpty(_address))
					urlBuilder.append("/").append(URLEncoder.encode(_address, URL_ENCODE));
			}
		}else{
			urlBuilder.append(this.address);
		}
			
		urlBuilder.append(AttributeKeys.WEB_SUFFIX);
		this.write(this.response().encodeURL(urlBuilder.toString()));
	}
	
	/**
	 * Sets the address.
	 * 
	 * @param address
	 *            the resource to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Sets the encode.
	 * 
	 * @param encode
	 *            the encode to set
	 */
	public void setEncode(boolean encode) {
		this.encode = encode;
	}
	
}
