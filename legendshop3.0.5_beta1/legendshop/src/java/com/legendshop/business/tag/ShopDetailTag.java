/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;

import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.core.tag.LegendShopTag;
import com.legendshop.model.entity.ShopDetailView;
import com.legendshop.util.AppUtils;


/**
 * The Class SortTag.
 */
public class ShopDetailTag extends LegendShopTag {

	/** The var. */
	private String var;
		
	/**
	 * Instantiates a new sort tag.
	 */
	public ShopDetailTag(){

	}

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
		String shopName = ThreadLocalContext.getCurrentShopName(request(), response());
		if(AppUtils.isNotBlank(shopName) && request().getAttribute(this.var) == null){
			ShopDetailView shopDetail = ThreadLocalContext.getShopDetailView(request(),response(),shopName);
			if(shopDetail != null){
				this.setAttribute(this.var, shopDetail);
				this.invokeJspBody();
			}
		}
	}

	/**
	 * Sets the var.
	 * 
	 * @param var
	 *            the new var
	 */
	public void setVar(String var) {
		this.var = var;
	}

}
