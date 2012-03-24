/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.core.tag;

import javax.servlet.jsp.tagext.TagSupport;

import com.legendshop.core.helper.PropertiesUtil;


/**
 * The Class SettingsTag.
 */
public class SettingsTag extends TagSupport {

	private static final long serialVersionUID = -8943927608529578818L;
	/** The key. */
	private String key;
	

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	@Override
	public int doStartTag() {
		Boolean result = PropertiesUtil.getBooleanObject(this.key);
		if(result!= null && result){
			return TagSupport.EVAL_BODY_INCLUDE; 
		}else{
			return TagSupport.SKIP_BODY;
		}
	}
	
	/**
	 * Sets the key.
	 * 
	 * @param key
	 *            the new key
	 */
	public void setKey(String key) {
		this.key = key;
	}
}
