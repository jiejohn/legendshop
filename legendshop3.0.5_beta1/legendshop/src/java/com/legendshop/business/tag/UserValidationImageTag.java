/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.tag;

import javax.servlet.jsp.tagext.TagSupport;

import com.legendshop.business.common.CommonServiceUtil;


/**
 * The Class SettingsTag.
 */
public class UserValidationImageTag extends TagSupport {

	private static final long serialVersionUID = -8943927608529578818L;

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	@Override
	public int doStartTag() {
		Boolean result = CommonServiceUtil.needToValidation(pageContext.getSession());
		if(result){
			return TagSupport.EVAL_BODY_INCLUDE; 
		}else{
			return TagSupport.SKIP_BODY;
		}
	}
	
}
