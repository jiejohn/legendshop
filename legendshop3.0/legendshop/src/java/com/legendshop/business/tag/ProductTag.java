/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.tag;

import java.io.IOException;

import com.legendshop.business.service.ProductService;
import com.legendshop.core.tag.LegendShopTag;
import com.legendshop.model.entity.Product;


/**
 * The Class CurrentShopTag.
 */
public class ProductTag extends LegendShopTag {

	/** The var. */
	private String var;
	
	private Long prodId;
	
	/** The product service. */
	private ProductService productService;
	
	/**
	 * Instantiates a new product tag.
	 */
	public ProductTag(){
		if(productService == null){
			productService = (ProductService)getBean("productService");
		}
	}
	
	/**
	 * Do tag.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag()
	 */
	@Override
	public void doTag() throws IOException {
		Product product = productService.getProductById(prodId);
		if(product!=null){
			this.setAttribute(this.var, product);
		}
	}

	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}

}
