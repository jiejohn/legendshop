/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;

import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.core.tag.LegendShopTag;
import com.legendshop.model.entity.Sort;
import com.legendshop.spi.constants.Constants;
import com.legendshop.spi.service.SortService;


/**
 * The Class SortTag.
 */
public class SortTag extends LegendShopTag {

	/** The var. */
	private String var;
	
	/** The shop name. */
	private String shopName;
	
	/** The sort type. */
	private String sortType;
	
	/** The load all. */
	private boolean loadAll;
	
	/** The sort service. */
	private SortService sortService;
	
	/**
	 * Instantiates a new sort tag.
	 */
	public SortTag(){
		if(sortService == null){
			sortService = (SortService)getBean("sortService");
		}
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
		String name  = shopName;
		if(name == null ){
			String sessionShopName = ThreadLocalContext.getCurrentShopName(request(), response());
			if(sessionShopName!=null){
				name = sessionShopName;
			}else{
				return;
			}
			
		}
		List<Sort> sorts;
		
		if(sortType == null){
			 sorts = sortService.getSort(name, loadAll);
		}else{
			 sorts = sortService.getSort(name, sortType, loadAll);
		}
		
		int index = 1;
		if(sorts != null){
			for (Sort sort : sorts) {
				this.setAttribute(this.var, sort);
				this.setAttribute(this.var + "Index", index);
				this.invokeJspBody();
				index++;
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

	/**
	 * Sets the shop name.
	 * 
	 * @param shopName
	 *            the new shop name
	 */
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	/**
	 * Sets the load all.
	 * 
	 * @param loadAll
	 *            the new load all
	 */
	public void setLoadAll(boolean loadAll) {
		this.loadAll = loadAll;
	}

	/**
	 * Sets the sort type.
	 * 
	 * @param sortType
	 *            the new sort type
	 */
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}


}
