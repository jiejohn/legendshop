/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.tag;

import java.io.IOException;

import com.legendshop.core.UserManager;
import com.legendshop.core.tag.LegendShopTag;


/**
 * The Class CurrentUserTag.
 */
public class CurrentUserTag extends LegendShopTag {

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */

	@Override
	public void doTag() throws IOException {
		String userName = UserManager.getUsername(this.request());
		if(userName!=null){
			this.write(userName);
		}
	}
	
}
