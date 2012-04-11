/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.common.page;

import javax.servlet.http.HttpServletRequest;

import com.legendshop.core.constant.PagePathCalculator;
import com.legendshop.core.constant.PageDefinition;

/**
 * The Enum BackPage.
 */
public enum BackPage implements PageDefinition{
	
	/** The VARIABLE. 可变路径 */
	VARIABLE(""),
	
	/** The BAC k_ erro r_ page. */
	BACK_ERROR_PAGE(ERROR_PAGE_PATH),

	/** The ORDERDETAIL. */
	ORDERDETAIL("/order/orderDetail"),

	/** The AD v_ lis t_ page. */
	ADV_LIST_PAGE("/advertisement/advertisementList"),

	/** The AD v_ edi t_ page. */
	ADV_EDIT_PAGE("/advertisement/advertisement"),

	/** The BRAN d_ lis t_ page. */
	BRAND_LIST_PAGE("/brand/brandList"),

	/** The BRAN d_ edi t_ page. */
	BRAND_EDIT_PAGE("/brand/brand"),

	/** The LIN k_ lis t_ page. */
	LINK_LIST_PAGE("/externallink/externallinkList"),

	/** The LIN k_ edi t_ page. */
	LINK_EDIT_PAGE("/externallink/externallink"),

	/** The HO t_ lis t_ page. */
	HOT_LIST_PAGE("/hotsearch/hotsearchList"),

	/** The HO t_ edi t_ page. */
	HOT_EDIT_PAGE("/hotsearch/hotsearch"),

	/** The IM g_ lis t_ page. */
	IMG_LIST_PAGE("/imgFile/imgFileList"),

	/** The IM g_ edi t_ page. */
	IMG_EDIT_PAGE("/prod/prod"),

	/** The DAS h_ board. */
	DASH_BOARD("/dashboard/index"),

	/** The ADMI n_ home. */
	ADMIN_HOME("/frame/index"),

	/** The ADMI n_ top. */
	ADMIN_TOP("/frame/top"),

	/** The IJP g_ lis t_ page. */
	IJPG_LIST_PAGE("/indexjpg/indexjpgList"),

	/** The IJP g_ edi t_ page. */
	IJPG_EDIT_PAGE("/indexjpg/indexjpg"),

	/** The UPGRAD e_ page. */
	UPGRADE_PAGE("/dashboard/upgrade"),

	/** The LOGI n_ his t_ lis t_ page. */
	LOGIN_HIST_LIST_PAGE("/loginhistory/loginHistoryList"),

	/** The LOGI n_ his t_ su m_ page. */
	LOGIN_HIST_SUM_PAGE("/loginhistory/loginHistorySum"),

	/** The LOG o_ lis t_ page. */
	LOGO_LIST_PAGE("/logo/logoList"),

	/** The LOG o_ edi t_ page. */
	LOGO_EDIT_PAGE("/logo/logo"),

	/** The LUCEN e_ page. */
	LUCENE_PAGE("/lucene/reindexer"),

	/** The LEAGU e_ lis t_ page. */
	LEAGUE_LIST_PAGE("/myleague/myleagueList"),

	/** The LEAGU e_ edi t_ page. */
	LEAGUE_EDIT_PAGE("/myleague/myleague"),

	/** The NEW s_ lis t_ page. */
	NEWS_LIST_PAGE("/news/newsList"),

	/** The NEW s_ edi t_ page. */
	NEWS_EDIT_PAGE("/news/news"),

	/** The NEWSCA t_ lis t_ page. */
	NEWSCAT_LIST_PAGE("/newsCategory/newsCategoryList"),

	/** The NEWSCA t_ edi t_ page. */
	NEWSCAT_EDIT_PAGE("/newsCategory/newsCategory"),

	/** The NSOR t_ lis t_ page. */
	NSORT_LIST_PAGE("/nsort/nsortList"),

	/** The NSOR t_ edi t_ page. */
	NSORT_EDIT_PAGE("/nsort/nsort"),

	/** The NSOR t_ appendbran d_ page. */
	NSORT_APPENDBRAND_PAGE("/nsort/appendBrand"),

	/** The ORDE r_ lis t_ page. */
	ORDER_LIST_PAGE("/order/orderList"),

	/** The PA y_ page. */
	PAY_PAGE("/payment/payto"),

	/** The PA y_ typ e_ lis t_ page. */
	PAY_TYPE_LIST_PAGE("/payType/payTypeList"),

	/** The PA y_ typ e_ edi t_ page. */
	PAY_TYPE_EDIT_PAGE("/payType/payType"),

	/** The PRO d_ lis t_ page. */
	PROD_LIST_PAGE("/prod/prodList"),

	/** The PRO d_ edi t_ page. */
	PROD_EDIT_PAGE("/prod/prod"),

	/** The APPEN d_ product. */
	APPEND_PRODUCT("/prod/appendProduct"),

	/** The CREATE p_ roduc t_ step. */
	CREATEP_RODUCT_STEP("/prod/createProductStep"),

	/** The PRO d_ com m_ lis t_ page. */
	PROD_COMM_LIST_PAGE("/prodComment/prodCommentList"),

	/** The PRO d_ com m_ edi t_ page. */
	PROD_COMM_EDIT_PAGE("/prodComment/prodComment"),

	/** The PU b_ lis t_ page. */
	PUB_LIST_PAGE("/pub/pubList"),

	/** The PU b_ edi t_ page. */
	PUB_EDIT_PAGE("/pub/pub"),

	/** The SHO p_ detai l_ lis t_ page. */
	SHOP_DETAIL_LIST_PAGE("/shopDetail/shopDetailList"),

	/** The SHO p_ detai l_ edi t_ page. */
	SHOP_DETAIL_EDIT_PAGE("/shopDetail/shopDetail"),

	/** The SOR t_ lis t_ page. */
	SORT_LIST_PAGE("/sort/sortList"),

	/** The SOR t_ edi t_ page. */
	SORT_EDIT_PAGE("/sort/sort"),

	/** The PARA m_ lis t_ page. */
	PARAM_LIST_PAGE("/systemParameter/systemParameterList"),

	/** The PARA m_ edi t_ page. */
	PARAM_EDIT_PAGE("/systemParameter/systemParameter"),

	/** The USE r_ com m_ lis t_ page. */
	USER_COMM_LIST_PAGE("/userComment/userCommentList"),

	/** The USE r_ com m_ edi t_ page. */
	USER_COMM_EDIT_PAGE("/userComment/userComment"),

	/** The USE r_ detai l_ lis t_ page. */
	USER_DETAIL_LIST_PAGE("/userDetail/userDetailList"),

	/** The VLO g_ lis t_ page. */
	VLOG_LIST_PAGE("/visitLog/visitLogList"),

	/** The VLO g_ edi t_ page. */
	VLOG_EDIT_PAGE("/visitLog/visitLog"),

	/** The SHO w_ dynami c_ attribute. */
	SHOW_DYNAMIC_ATTRIBUTE("/xml/showDynamicAttribute"),

	/** The SHO w_ dynamic. */
	SHOW_DYNAMIC("/xml/showdynamic"),

	/** The DYNAMI c_ attribute. */
	DYNAMIC_ATTRIBUTE("/xml/dynamicAttribute"),

	/** The UPDAT e_ function. */
	UPDATE_FUNCTION("/member/right/updateFunction"),

	// FIND_FUNCTION_BY_ROLE("/member/right/findFunctionByRole",FOWARD),

	/** The ROL e_ function. */
	ROLE_FUNCTION("/member/right/findFunctionByRole"),

	/** The FIN d_ othe r_ functio n_ list. */
	FIND_OTHER_FUNCTION_LIST("/member/right/findOtherfunctionList"),

	/** The FUNCTIO n_ list. */
	FUNCTION_LIST("/member/right/functionList"),

	/** The ROL e_ list. */
	ROLE_LIST("/member/right/roleList"),

	/** The FIN d_ rol e_ b y_ function. */
	FIND_ROLE_BY_FUNCTION("/member/right/findRoleByFunction"),

	/** The FIN d_ rol e_ b y_ use r_ page. */
	FIND_ROLE_BY_USER_PAGE("/member/user/findRoleByUser"),

	/** The UPDAT e_ use r_ status. */
	UPDATE_USER_STATUS("/member/user/updateUserStatus"),

	/** The UPDAT e_ use r_ password. */
	UPDATE_USER_PASSWORD("/member/user/updateUserPassword"),

	/** The MODIF y_ user. */
	MODIFY_USER("/member/user/saveUser"),

	/** The FIN d_ othe r_ rol e_ b y_ user. */
	FIND_OTHER_ROLE_BY_USER("/member/user/findOtherRoleByUser"),

	/** The FIN d_ functio n_ b y_ user. */
	FIND_FUNCTION_BY_USER("/member/user/findFunctionByUser"),

	/** The USE r_ lis t_ page. */
	USER_LIST_PAGE("/member/user/userlist"),

	/** The SAV e_ role. */
	SAVE_ROLE("/member/right/saveRole"),

	/** The MODIFYPRICE. */
	MODIFYPRICE("/order/modifyPrice"), 
	
	/** The DELIVERYCOR p_ lis t_ page. */
	DELIVERYCORP_LIST_PAGE("/deliveryCorp/deliveryCorpList"), 
	
	/** The DELIVERYCOR p_ edi t_ page. */
	DELIVERYCORP_EDIT_PAGE("/deliveryCorp/deliveryCorp"), 
	
	/** The DELIVERYTYP e_ lis t_ page. */
	DELIVERYTYPE_LIST_PAGE("/deliveryType/deliveryTypeList"), 
	
	/** The DELIVERYTYP e_ edi t_ page. */
	DELIVERYTYPE_EDIT_PAGE("/deliveryType/deliveryType"), 
	
	/** The DELIVERYTYP e_ lis t_ page. */
	PARTNER_LIST_PAGE("/partner/partnerList"), 
	
	/** The DELIVERYTYP e_ edi t_ page. */
	PARTNER_EDIT_PAGE("/partner/partner"),
	
	PARTNER_CHANGE_PASSWORD_PAGE("/partner/partnerChangePassword"),
	
	;
	
	
	/** The value. */
	private final String value;
	
	/**
	 * Instantiates a new back page.
	 * 
	 * @param value
	 *            the value
	 */
	private BackPage(String value) {
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.core.constant.PageDefinition#getValue(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String getValue(HttpServletRequest request) {
		return getValue(request,value);
	}
	
	/* (non-Javadoc)
	 * @see com.legendshop.core.constant.PageDefinition#getValue(javax.servlet.http.HttpServletRequest, java.lang.String)
	 */
	@Override
	public String getValue(HttpServletRequest request, String path) {
		return PagePathCalculator.calculateBackendPath(request,path);
	}

	
}
