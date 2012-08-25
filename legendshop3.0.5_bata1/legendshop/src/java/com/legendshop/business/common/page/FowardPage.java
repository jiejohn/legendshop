/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.common.page;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.legendshop.core.constant.PageDefinition;
import com.legendshop.core.constant.PagePathCalculator;

/**
 * The Enum FowardPage.
 */
public enum FowardPage implements PageDefinition {
	/** The VARIABLE. 可变路径 */
	VARIABLE(""),
	/** The INDE x_ query. */
	INDEX_QUERY("/index"),

	/** The AD v_ lis t_ query. */
	ADV_LIST_QUERY("/admin/advertisement/query"),

	/** The BRAN d_ lis t_ query. */
	BRAND_LIST_QUERY("/admin/brand/query"),

	/** The LIN k_ lis t_ query. */
	LINK_LIST_QUERY("/admin/externallink/query"),

	/** The HO t_ lis t_ query. */
	HOT_LIST_QUERY("/admin/hotsearch/query"),

	/** The IM g_ lis t_ query. */
	IMG_LIST_QUERY("/admin/imgFile/query"),

	/** The IJP g_ lis t_ query. */
	IJPG_LIST_QUERY("/admin/indexjpg/query"),

	/** The LOG o_ lis t_ query. */
	LOGO_LIST_QUERY("/admin/logo/query"),

	/** The LEAGU e_ lis t_ query. */
	LEAGUE_LIST_QUERY("/admin/myleague/query"),

	/** The NEW s_ lis t_ query. */
	NEWS_LIST_QUERY("/admin/news/query"),

	/** The NEWSCA t_ lis t_ query. */
	NEWSCAT_LIST_QUERY("/admin/newsCategory/query"),

	/** The NSOR t_ lis t_ query. */
	NSORT_LIST_QUERY("/admin/nsort/query"),

	/** The PA y_ typ e_ lis t_ query. */
	PAY_TYPE_LIST_QUERY("/admin/paytype/query"),

	/** The PRO d_ lis t_ query. */
	PROD_LIST_QUERY("/admin/product/query"),

	/** The SHO p_ detai l_ lis t_ query. */
	SHOP_DETAIL_LIST_QUERY("/admin/shopDetail/query"),

	/** The SOR t_ lis t_ query. */
	SORT_LIST_QUERY("/admin/sort/query"),

	/** The SOR t_ lis t_ query. */
	GSORT_LIST_QUERY("/admin/gsort/query"),

	/** The PARA m_ lis t_ query. */
	PARAM_LIST_QUERY("/system/systemParameter/query"),

	/** The PRO d_ com m_ lis t_ query. */
	PROD_COMM_LIST_QUERY("/admin/productcomment/query"),

	/** The USE r_ com m_ lis t_ query. */
	USER_COMM_LIST_QUERY("/admin/userComment/query"),

	/** The VLO g_ lis t_ query. */
	VLOG_LIST_QUERY("/admin/visitLog/query"),

	/** The USE r_ detai l_ lis t_ query. */
	USER_DETAIL_LIST_QUERY("/admin/userDetail/query"),

	/** The PU b_ lis t_ query. */
	PUB_LIST_QUERY("/admin/pub/query"),

	/** The DYNAMI c_ query. */
	DYNAMIC_QUERY("/dynamic/query"),

	DELIVERYCORP_LIST_QUERY("/admin/deliveryCorp/query"),

	DELIVERYTYPE_LIST_QUERY("/admin/deliveryType/query"),

	PARTNER_LIST_QUERY("/admin/partner/query"),

	TAG("/admin/tag/query");

	/** The value. */
	private final String value;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.core.constant.PageDefinition#getValue(javax.servlet.http
	 * .HttpServletRequest)
	 */
	@Override
	public String getValue(HttpServletRequest request, HttpServletResponse response) {
		return getValue(request,response,value, null);
	}

	@Override
	public String getValue(HttpServletRequest request, HttpServletResponse response,String path, List<String> templates) {
		return PagePathCalculator.calculateActionPath("forward:", path);
	}

	/**
	 * Instantiates a new tiles page.
	 * 
	 * @param value
	 *            the value
	 */
	private FowardPage(String value) {
		this.value = value;
	}

	@Override
	public String getNativeValue() {
		return value;
	}

	@Override
	public List<String> getTemplates() {
		return null;
	}
}
