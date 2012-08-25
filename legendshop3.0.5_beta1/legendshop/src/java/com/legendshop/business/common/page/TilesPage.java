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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.legendshop.core.constant.PageDefinition;
import com.legendshop.core.constant.PagePathCalculator;
import com.legendshop.spi.constants.TemplateEnum;
import com.legendshop.util.AppUtils;

/**
 * The Enum TilesPage.
 */
public enum TilesPage implements PageDefinition {
	/** The VARIABLE. 可变路径 */
	VARIABLE(""),

	/** The INDEX page. */
	INDEX_PAGE("index.", TemplateEnum.DEFAULT, TemplateEnum.RED),

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
	OPENSHOP("openShop."),
	
	/** The VIEWS. */
	VIEWS("views."),

	/**
	 * C2C home page
	 */
	HOME("home.", TemplateEnum.RED),

	/** The User Center main page. */
	USER_CENTER_MAIN("usercenter.", TemplateEnum.RED),

	/**
	 * The favorite main page.
	 */
	MY_FAVOURITE_MAIN("favourite.", TemplateEnum.RED),

	/**
	 * The inBox message page
	 */
	SITE_MESSAGE_INBOX("inbox.", TemplateEnum.RED),

	/**
	 * The outBox message page
	 */
	SITE_MESSAGE_OUTBOX("outbox.", TemplateEnum.RED),

	/**
	 * All products sort page
	 */
	ALL_PRODUCT_SORT("allProductSort.", TemplateEnum.RED),

	/**
	 * All brands page
	 */
	ALL_BRAND("allBrand.", TemplateEnum.RED),

	/**
	 * Customer leave message page
	 */
	LEAVE_MSG("leaveMsg.", TemplateEnum.RED),

	/**
	 * The shop search page
	 */
	SHOP_SEARCH("shopSearch.", TemplateEnum.RED),

	/**
	 * The warning message page
	 */
	WARNING("warning.", TemplateEnum.RED) ;

	/** The value. */
	private final String value;

	private List<String> templates;

	private TilesPage(String value, String... template) {
		this.value = value;
		if (AppUtils.isNotBlank(template)) {
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
	public String getValue(HttpServletRequest request, HttpServletResponse response) {
		return getValue(request,response,value, templates);
	}

	@Override
	public String getValue(HttpServletRequest request, HttpServletResponse response,String path, List<String> templates) {
		return PagePathCalculator.calculateTilesPath(request,response,path, templates);
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
