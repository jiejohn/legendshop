/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.common.page;

import javax.servlet.http.HttpServletRequest;

import com.legendshop.core.constant.PageDefinition;
import com.legendshop.core.constant.PagePathCalculator;

/**
 * The Enum TilesPage.
 */
public enum TilesPage implements PageDefinition{
	/** The VARIABLE. 可变路径 */
	VARIABLE(""),
	
	/** The INDEX page. */
	INDEX_PAGE("index."),

	/** The N o_ login. */
	NO_LOGIN("loginhint."),

	/** The AFTE r_ operation. */
	AFTER_OPERATION("afterOperation."),

	/** The NSORT. */
	NSORT("nsort."),

	/** The LOGIN. */
	LOGIN(LOGIN_PATH),

	/** The LEAVEWORD. */
	LEAVEWORD("leaveword."),

	/** The NEWS. */
	NEWS("news."),

	/** The AL l_ news. */
	ALL_NEWS("allNews."),

	/** The PAG e_ cash. */
	PAGE_CASH("cash."),

	/** The REG. */
	REG("reg."),

	/** The LEAGUE. */
	LEAGUE("league."),

	/** The PRODUCTSORT. */
	PRODUCTSORT("productSort."),

	/** The SEARCHALL. */
	SEARCHALL("searchall."),

	/** The PAG e_ sub. */
	PAGE_SUB("savesub."),

	/** The MYACCOUNT. */
	MYACCOUNT("myaccount."),

	/** The SHOPCONTACT. */
	SHOPCONTACT("shopcontact."),

	/** The BUY. */
	BUY("buy."),

	/** The ORDER. */
	ORDER("order."),

	/** The OPENSHOP. */
	OPENSHOP("openShop.");
	
	
	/** The value. */
	private final String value;

	/* (non-Javadoc)
	 * @see com.legendshop.core.constant.PageDefinition#getValue(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String getValue(HttpServletRequest request) {
		return getValue(request,value);
	}
	
	@Override
	public String getValue(HttpServletRequest request, String path) {
		return PagePathCalculator.calculateTilesPath(request,path);
	}

	
	/**
	 * Instantiates a new tiles page.
	 * 
	 * @param value
	 *            the value
	 */
	private TilesPage(String value) {
		this.value = value;
	}
	

	@Override
	public String getValue() {
		return value;
	}
}
