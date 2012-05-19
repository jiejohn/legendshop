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
 * The Enum FrontPage.
 */
public enum FrontPage  implements PageDefinition{
	/** The VARIABLE. 可变路径 */
	VARIABLE(""),
	/** The ERRO r_ page. */
	ERROR_PAGE(ERROR_PAGE_PATH),

	/** The INSTALL. */
	INSTALL("/install/index"),

	/** The AL l_ page. */
	ALL_PAGE("/all"),

	/** The FAIL. */
	FAIL("/fail"),

	/** The TOPALL. */
	TOPALL("/topAll"),

	/** The TOP. */
	TOP("/top"),

	/** The TOPSORT. */
	TOPSORT("/topsort"),

	/** The TOPSORTNEWS. */
	TOPSORTNEWS("/topsortnews"),

	/** The TOPNEWS. */
	TOPNEWS("/topnews"),

	/** The VIEWS. */
	VIEWS("/views"),

	/** The COP y_ all. */
	COPY_ALL("/copyAll"),

	/** The HOTON. */
	HOTON("/hoton"),

	/** The HOTSALE. */
	HOTSALE("/hotSale"),

	/** The FRIENDLINK. */
	FRIENDLINK("/friendlink"),

	/** The HOTVIEW. */
	HOTVIEW("/hotview"),

	/** The INDE x_ page. */
	INDEX_PAGE("/index"),

	/** The ALL. */
	ALL("/all"),

	/** The PRO d_ pi c_ gallery. */
	PROD_PIC_GALLERY("/gallery"),

	/** The IPSEARCH. */
	IPSEARCH("/ipsearch"),

	/** The BOUGHT. */
	BOUGHT("/bought"),

	/** The CAS h_ save. */
	CASH_SAVE("/cashsave"),

	/** The RESETPASSWORD. */
	RESETPASSWORD("/resetpassword");
	
	
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
		return PagePathCalculator.calculateFronendPath(request,path);
	}

	
	/**
	 * Instantiates a new front page.
	 * 
	 * @param value
	 *            the value
	 */
	private FrontPage(String value) {
		this.value = value;
	}
	

	@Override
	public String getValue() {
		return value;
	}
}
