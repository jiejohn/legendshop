/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.common.page;

import javax.servlet.http.HttpServletRequest;

import com.legendshop.core.AttributeKeys;
import com.legendshop.core.constant.PathEnum;

/**
 * The Enum FowardPage.
 */
public enum FowardPage implements PathEnum{
	
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

	/** The PARA m_ lis t_ query. */
	PARAM_LIST_QUERY("/system/systemParameter/query"),

	/** The PRO d_ com m_ lis t_ query. */
	PROD_COMM_LIST_QUERY("/admin/productcomment/query"),

	/** The USE r_ com m_ lis t_ query. */
	USER_COMM_LIST_QUERY("/admin/userComment/query"),

	/** The VLO g_ lis t_ query. */
	VLOG_LIST_QUERY("/admin/visitLog/query"),

	/** The FUNCTIO n_ lis t_ query. */
	FUNCTION_LIST_QUERY("/member/right/listFunction"),

	/** The FIN d_ functio n_ b y_ role. */
	FIND_FUNCTION_BY_ROLE("/member/role/functions"),

	/** The FIN d_ al l_ role. */
	FIND_ALL_ROLE("/member/right/findAllRole"), 
	
	/** The FIN d_ rol e_ b y_ user. */
	FIND_ROLE_BY_USER("/member/user/findRoleByUser"),

	/** The FIN d_ use r_ roles. */
	FIND_USER_ROLES("/member/user/roles"),

	/** The AL l_ role. */
	ALL_ROLE("/member/role/query"),
	
	/** The USE r_ detai l_ lis t_ query. */
	USER_DETAIL_LIST_QUERY("/admin/userDetail/query"),
	
	/** The PU b_ lis t_ query. */
	PUB_LIST_QUERY("/admin/pub/query"),

	/** The DYNAMI c_ query. */
	DYNAMIC_QUERY("/dynamic/query"),
	
	/** The USER s_ list. */
	USERS_LIST("/member/user/query"), 
	
	DELIVERYCORP_LIST_QUERY("/admin/deliveryCorp/query");
	
	
	/** The value. */
	private final String value;

	/* (non-Javadoc)
	 * @see com.legendshop.core.constant.PathEnum#getValue(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String getValue(HttpServletRequest request) {
		//TODO
		return new StringBuffer("forward:").append(value).append(AttributeKeys.WEB_SUFFIX).toString();
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
	
	/* (non-Javadoc)
	 * @see com.legendshop.core.constant.PathEnum#getType()
	 */
	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.core.constant.PathEnum#getValue()
	 */
	@Override
	public String getValue() {
		return value;
	}

}
