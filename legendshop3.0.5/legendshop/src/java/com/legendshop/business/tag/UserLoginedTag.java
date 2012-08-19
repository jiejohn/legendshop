/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.tag;

import javax.servlet.jsp.tagext.TagSupport;

import com.legendshop.core.UserManager;


/**
 * The Class UserLoginedTag.
 */
public class UserLoginedTag extends TagSupport {

	private static final long serialVersionUID = -779804105023929973L;

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	@Override
	public int doStartTag() {
		String userName = UserManager.getUsername(pageContext.getSession());
		if(userName!= null){
			return TagSupport.EVAL_BODY_INCLUDE; 
		}else{
			return TagSupport.SKIP_BODY;
		}
	}
	
}
