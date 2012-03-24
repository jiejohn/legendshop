/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.core.tag;

import java.io.IOException;


/**
 * The Class TemplateResourceTag.
 */
public class TemplateResourceTag extends LegendShopTag {
	
	/** The item. */
	private String item;

	/**
	 * Do tag.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag()
	 */
	@Override
	public void doTag() throws IOException {
		String path = new StringBuilder(128)
			.append(this.request().getContextPath())
//			.append('/').append(this.config().getValue(ConfigKeys.TEMPLATE_DIRECTORY)).append('/')
//			.append(this.config().getValue(ConfigKeys.TEMPLATE_NAME))
			.append(this.item)
			.toString();

		this.write(path);
	}

	/**
	 * Sets the item.
	 * 
	 * @param item
	 *            the resource to set
	 */
	public void setItem(String item) {
		this.item = item;
	}
}
