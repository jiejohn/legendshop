/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.tag;

import java.io.IOException;

import com.legendshop.core.tag.LegendShopTag;
import com.legendshop.util.CookieUtil;


/**
 * 最后登录用户.
 */
public class LastLogingUserTag extends LegendShopTag {

	
    public static final String LAST_USERNAME_KEY = "LAST_LOGINING_USERNAME";

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	@Override
	public void doTag() throws IOException {
		String userName = CookieUtil.getCookieValue(this.request(), LAST_USERNAME_KEY);
		if(userName!=null){
			this.write(userName);
		}
	}
	
}
