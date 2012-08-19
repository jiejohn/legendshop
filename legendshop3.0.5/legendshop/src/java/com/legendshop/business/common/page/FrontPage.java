/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.common.page;

import java.util.ArrayList;
import java.util.List;

import com.legendshop.core.constant.PageDefinition;
import com.legendshop.core.constant.PagePathCalculator;
import com.legendshop.util.AppUtils;

/**
 * The Enum FrontPage.
 */
public enum FrontPage implements PageDefinition {
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

	/** The COPY Page. */
	COPY("/copy"),

	/** The HOTON. */
	HOTON("/hoton"),

	/** The HOTSALE. */
	HOTSALE("/hotSale"),

	/** The FRIENDLINK. */
	FRIENDLINK("/friendlink"),

	/** The HOTVIEW. */
	HOTVIEW("/hotview"),

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
	RESETPASSWORD("/resetpassword"),

	/**
	 * the top page
	 */
	HOME_TOP("/home/top"),

	/**
	 * the common bottom page
	 */
	BOTTOM("/bottom");
	/** The value. */
	private final String value;
	
	private List<String> templates;
	

	private FrontPage(String value,String ... template) {
		this.value = value;
		if(AppUtils.isNotBlank(template)){
			this.templates = new ArrayList<String>();
			for (String temp : template) {
				templates.add(temp);
			}
		}
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.core.constant.PageDefinition#getValue(javax.servlet.http
	 * .HttpServletRequest)
	 */
	@Override
	public String getValue() {
		return getValue(value,templates);
	}

	@Override
	public String getValue(String path,List<String> templates) {
		return PagePathCalculator.calculateFronendPath(path,templates);
	}

	@Override
	public String getNativeValue() {
		return value;
	}

	@Override
	public List<String> getTemplates() {
		return this.templates;
	}
}
