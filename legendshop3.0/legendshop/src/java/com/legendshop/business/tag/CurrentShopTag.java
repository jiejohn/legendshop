/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.tag;

import java.io.IOException;

import com.legendshop.business.common.Constants;
import com.legendshop.core.tag.LegendShopTag;


/**
 * The Class CurrentShopTag.
 */
public class CurrentShopTag extends LegendShopTag {

	/**
	 * Do tag.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag()
	 */
	@Override
	public void doTag() throws IOException {
		String shopName = (String)pageContext().getSession().getAttribute(Constants.SHOP_NAME);
		if(shopName!=null){
			this.write(shopName);
		}
	}

}
