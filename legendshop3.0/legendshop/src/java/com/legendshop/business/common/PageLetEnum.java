/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.common;

import javax.servlet.http.HttpServletRequest;

import com.legendshop.core.constant.PathEnum;

/**
 * 页面定义
 * 
 * tiles页面后面加上"."来区分
 */
public enum PageLetEnum implements PathEnum {

	// /////////1.front page

//	ERROR_PAGE(ERROR_PAGE_PATH, FRONT_PAGE),
//
//	INSTALL("/install/index", FRONT_PAGE),
//
//	ALL_PAGE("/all", FRONT_PAGE),
//
//	FAIL("/fail", FRONT_PAGE),
//
//	TOPALL("/topAll", FRONT_PAGE),
//
//	TOP("/top", FRONT_PAGE),
//
//	TOPSORT("/topsort", FRONT_PAGE),
//
//	TOPSORTNEWS("/topsortnews", FRONT_PAGE),
//
//	TOPNEWS("/topnews", FRONT_PAGE),
//
//	VIEWS("/views", FRONT_PAGE),
//
//	COPY_ALL("/copyAll", FRONT_PAGE),
//
//	HOTON("/hoton", FRONT_PAGE),
//
//	HOTSALE("/hotSale", FRONT_PAGE),
//
//	FRIENDLINK("/friendlink", FRONT_PAGE),
//
//	HOTVIEW("/hotview", FRONT_PAGE),
//
//	INDEX_PAGE("/index", FRONT_PAGE),
//
//	ALL("/all", FRONT_PAGE),
//
//	PROD_PIC_GALLERY("/gallery", FRONT_PAGE),
//
//	IPSEARCH("/ipsearch", FRONT_PAGE),
//
//	BOUGHT("/bought", FRONT_PAGE),
//
//	CASH_SAVE("/cashsave", FRONT_PAGE),
//
//	RESETPASSWORD("/resetpassword", FRONT_PAGE),

	// ///////////////////////////////////
	// 2.Tiles page

//	NO_LOGIN("loginhint.", TILES),
//
//	AFTER_OPERATION("afterOperation.", TILES),
//
//	NSORT("nsort.", TILES),
//
//	LOGIN(LOGIN_PATH, TILES),
//
//	LEAVEWORD("leaveword.", TILES),
//
//	NEWS("news.", TILES),
//
//	ALL_NEWS("allNews.", TILES),
//
//	PAGE_CASH("cash.", TILES),
//
//	REG("reg.", TILES),
//
//	LEAGUE("league.", TILES),
//
//	PRODUCTSORT("productSort.", TILES),
//
//	SEARCHALL("searchall.", TILES),
//
//	PAGE_SUB("savesub.", TILES),
//
//	MYACCOUNT("myaccount.", TILES),
//
//	SHOPCONTACT("shopcontact.", TILES),
//
//	BUY("buy.", TILES),
//
//	ORDER("order.", TILES),
//
//	OPENSHOP("openShop.", TILES),

	// ///////////////////
	// 3.foward page
//	INDEX_QUERY("/index", FOWARD),
//
//	ADV_LIST_QUERY("/admin/advertisement/query", FOWARD),
//
//	BRAND_LIST_QUERY("/admin/brand/query", FOWARD),
//
//	LINK_LIST_QUERY("/admin/externallink/query", FOWARD),
//
//	HOT_LIST_QUERY("/admin/hotsearch/query", FOWARD),
//
//	IMG_LIST_QUERY("/admin/imgFile/query", FOWARD),
//
//	IJPG_LIST_QUERY("/admin/indexjpg/query", FOWARD),
//
//	LOGO_LIST_QUERY("/admin/logo/query", FOWARD),
//
//	LEAGUE_LIST_QUERY("/admin/myleague/query", FOWARD),
//
//	NEWS_LIST_QUERY("/admin/news/query", FOWARD),
//
//	NEWSCAT_LIST_QUERY("/admin/newsCategory/query", FOWARD),
//
//	NSORT_LIST_QUERY("/admin/nsort/query", FOWARD),
//
//	PAY_TYPE_LIST_QUERY("/admin/paytype/query", FOWARD),
//
//	PROD_LIST_QUERY("/admin/product/query", FOWARD),
//
//	SHOP_DETAIL_LIST_QUERY("/admin/shopDetail/query", FOWARD),
//
//	SORT_LIST_QUERY("/admin/sort/query", FOWARD),
//
//	PARAM_LIST_QUERY("/system/systemParameter/query", FOWARD),
//
//	PROD_COMM_LIST_QUERY("/admin/productcomment/query", FOWARD),
//
//	USER_COMM_LIST_QUERY("/admin/userComment/query", FOWARD),
//
//	VLOG_LIST_QUERY("/admin/visitLog/query", FOWARD),
//
//	FUNCTION_LIST_QUERY("/member/right/listFunction", FOWARD),
//
//	FIND_FUNCTION_BY_ROLE("/member/role/functions", FOWARD),
//
//	FIND_ALL_ROLE("/member/right/findAllRole", FOWARD), 
//	
//	FIND_ROLE_BY_USER("/member/user/findRoleByUser", FOWARD),
//
//	FIND_USER_ROLES("/member/user/roles", FOWARD),
//
//	ALL_ROLE("/member/role/query", FOWARD),
//	
//	USER_DETAIL_LIST_QUERY("/admin/userDetail/query", FOWARD),
//	
//	PUB_LIST_QUERY("/admin/pub/query", FOWARD),
//
//	DYNAMIC_QUERY("/dynamic/query", FOWARD),
//	
//	USERS_LIST("/member/user/query", FOWARD),

	////////////////////////
	//4.BACK_PAGE
	
//	BACK_ERROR_PAGE(ERROR_PAGE_PATH, BACK_PAGE),
//
//	ORDERDETAIL("/order/orderDetail", BACK_PAGE),
//
//	ADV_LIST_PAGE("/advertisement/advertisementList", BACK_PAGE),
//
//	ADV_EDIT_PAGE("/advertisement/advertisement", BACK_PAGE),
//
//	BRAND_LIST_PAGE("/brand/brandList", BACK_PAGE),
//
//	BRAND_EDIT_PAGE("/brand/brand", BACK_PAGE),
//
//	LINK_LIST_PAGE("/externallink/externallinkList", BACK_PAGE),
//
//	LINK_EDIT_PAGE("/externallink/externallink", BACK_PAGE),
//
//	HOT_LIST_PAGE("/hotsearch/hotsearchList", BACK_PAGE),
//
//	HOT_EDIT_PAGE("/hotsearch/hotsearch", BACK_PAGE),
//
//	IMG_LIST_PAGE("/imgFile/imgFileList", BACK_PAGE),
//
//	IMG_EDIT_PAGE("/prod/prod", BACK_PAGE),
//
//	DASH_BOARD("/dashboard/index", BACK_PAGE),
//
//	ADMIN_HOME("/frame/index", BACK_PAGE),
//
//	ADMIN_TOP("/frame/top", BACK_PAGE),
//
//	IJPG_LIST_PAGE("/indexjpg/indexjpgList", BACK_PAGE),
//
//	IJPG_EDIT_PAGE("/indexjpg/indexjpg", BACK_PAGE),
//
//	UPGRADE_PAGE("/dashboard/upgrade", BACK_PAGE),
//
//	LOGIN_HIST_LIST_PAGE("/loginhistory/loginHistoryList", BACK_PAGE),
//
//	LOGIN_HIST_SUM_PAGE("/loginhistory/loginHistorySum", BACK_PAGE),
//
//	LOGO_LIST_PAGE("/logo/logoList", BACK_PAGE),
//
//	LOGO_EDIT_PAGE("/logo/logo", BACK_PAGE),
//
//	LUCENE_PAGE("/lucene/reindexer", BACK_PAGE),
//
//	LEAGUE_LIST_PAGE("/myleague/myleagueList", BACK_PAGE),
//
//	LEAGUE_EDIT_PAGE("/myleague/myleague", BACK_PAGE),
//
//	NEWS_LIST_PAGE("/news/newsList", BACK_PAGE),
//
//	NEWS_EDIT_PAGE("/news/news", BACK_PAGE),
//
//	NEWSCAT_LIST_PAGE("/newsCategory/newsCategoryList", BACK_PAGE),
//
//	NEWSCAT_EDIT_PAGE("/newsCategory/newsCategory", BACK_PAGE),
//
//	NSORT_LIST_PAGE("/nsort/nsortList", BACK_PAGE),
//
//	NSORT_EDIT_PAGE("/nsort/nsort", BACK_PAGE),
//
//	NSORT_APPENDBRAND_PAGE("/nsort/appendBrand", BACK_PAGE),
//
//	ORDER_LIST_PAGE("/order/orderList", BACK_PAGE),
//
//	PAY_PAGE("/payment/payto", BACK_PAGE),
//
//	PAY_TYPE_LIST_PAGE("/payType/payTypeList", BACK_PAGE),
//
//	PAY_TYPE_EDIT_PAGE("/payType/payType", BACK_PAGE),
//
//	PROD_LIST_PAGE("/prod/prodList", BACK_PAGE),
//
//	PROD_EDIT_PAGE("/prod/prod", BACK_PAGE),
//
//	APPEND_PRODUCT("/prod/appendProduct", BACK_PAGE),
//
//	CREATEP_RODUCT_STEP("/prod/createProductStep", BACK_PAGE),
//
//	PROD_COMM_LIST_PAGE("/prodComment/prodCommentList", BACK_PAGE),
//
//	PROD_COMM_EDIT_PAGE("/prodComment/prodComment", BACK_PAGE),
//
//	PUB_LIST_PAGE("/pub/pubList", BACK_PAGE),
//
//	PUB_EDIT_PAGE("/pub/pub", BACK_PAGE),
//
//	SHOP_DETAIL_LIST_PAGE("/shopDetail/shopDetailList", BACK_PAGE),
//
//	SHOP_DETAIL_EDIT_PAGE("/shopDetail/shopDetail", BACK_PAGE),
//
//	SORT_LIST_PAGE("/sort/sortList", BACK_PAGE),
//
//	SORT_EDIT_PAGE("/sort/sort", BACK_PAGE),
//
//	PARAM_LIST_PAGE("/systemParameter/systemParameterList", BACK_PAGE),
//
//	PARAM_EDIT_PAGE("/systemParameter/systemParameter", BACK_PAGE),
//
//	USER_COMM_LIST_PAGE("/userComment/userCommentList", BACK_PAGE),
//
//	USER_COMM_EDIT_PAGE("/userComment/userComment", BACK_PAGE),
//
//	USER_DETAIL_LIST_PAGE("/userDetail/userDetailList", BACK_PAGE),
//
//	VLOG_LIST_PAGE("/visitLog/visitLogList", BACK_PAGE),
//
//	VLOG_EDIT_PAGE("/visitLog/visitLog", BACK_PAGE),
//
//	SHOW_DYNAMIC_ATTRIBUTE("/xml/showDynamicAttribute", BACK_PAGE),
//
//	SHOW_DYNAMIC("/xml/showdynamic", BACK_PAGE),
//
//	DYNAMIC_ATTRIBUTE("/xml/dynamicAttribute", BACK_PAGE),
//
//	UPDATE_FUNCTION("/member/right/updateFunction", BACK_PAGE),
//
//	// FIND_FUNCTION_BY_ROLE("/member/right/findFunctionByRole",FOWARD),
//
//	ROLE_FUNCTION("/member/right/findFunctionByRole", BACK_PAGE),
//
//	FIND_OTHER_FUNCTION_LIST("/member/right/findOtherfunctionList", BACK_PAGE),
//
//	FUNCTION_LIST("/member/right/functionList", BACK_PAGE),
//
//	ROLE_LIST("/member/right/roleList", BACK_PAGE),
//
//	FIND_ROLE_BY_FUNCTION("/member/right/findRoleByFunction", BACK_PAGE),
//
//	FIND_ROLE_BY_USER_PAGE("/member/user/findRoleByUser", BACK_PAGE),
//
//	UPDATE_USER_STATUS("/member/user/updateUserStatus", BACK_PAGE),
//
//	UPDATE_USER_PASSWORD("/member/user/updateUserPassword", BACK_PAGE),
//
//	MODIFY_USER("/member/user/saveUser", BACK_PAGE),
//
//	FIND_OTHER_ROLE_BY_USER("/member/user/findOtherRoleByUser", BACK_PAGE),
//
//	FIND_FUNCTION_BY_USER("/member/user/findFunctionByUser", BACK_PAGE),
//
//	USER_LIST_PAGE("/member/user/userlist", BACK_PAGE),
//
//	SAVE_ROLE("/member/right/saveRole", BACK_PAGE),
//
//	MODIFYPRICE("/order/modifyPrice", BACK_PAGE)

	;

	/** The value. */
	private final String value;

	private final int type;

	/**
	 * Instantiates a new function enum.
	 * 
	 * @param value
	 *            the value
	 */
	private PageLetEnum(String value, int type) {
		this.value = value;
		this.type = type;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public int getType() {
		return type;
	}

	public static void main(String[] args) {
		System.out.println(PageLetEnum.values().length);
	}

	@Override
	public String getValue(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
