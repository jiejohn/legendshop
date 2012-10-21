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
import com.legendshop.util.AppUtils;

/**
 * The Enum BackPage.
 */
public enum BackPage implements PageDefinition{
	
	/** The variable. */
	VARIABLE(""),
	
	/** The back error page. */
	BACK_ERROR_PAGE(ERROR_PAGE_PATH),

	/** The orderdetail. */
	ORDERDETAIL("/order/orderDetail"),

	/** The adv list page. */
	ADV_LIST_PAGE("/advertisement/advertisementList"),

	/** The adv edit page. */
	ADV_EDIT_PAGE("/advertisement/advertisement"),

	/** The brand list page. */
	BRAND_LIST_PAGE("/brand/brandList"),

	/** The brand edit page. */
	BRAND_EDIT_PAGE("/brand/brand"),

	/** The link list page. */
	LINK_LIST_PAGE("/externallink/externallinkList"),

	/** The link edit page. */
	LINK_EDIT_PAGE("/externallink/externallink"),

	/** The hot list page. */
	HOT_LIST_PAGE("/hotsearch/hotsearchList"),

	/** The hot edit page. */
	HOT_EDIT_PAGE("/hotsearch/hotsearch"),

	/** The img list page. */
	IMG_LIST_PAGE("/imgFile/imgFileList"),

	/** The img edit page. */
	IMG_EDIT_PAGE("/prod/prod"),

	/** The dash board. */
	DASH_BOARD("/dashboard/index"),

	/** The admin home. */
	ADMIN_HOME("/frame/index"),

	/** The admin top. */
	ADMIN_TOP("/frame/top"),

	/** The ijpg list page. */
	IJPG_LIST_PAGE("/indexjpg/indexjpgList"),

	/** The ijpg edit page. */
	IJPG_EDIT_PAGE("/indexjpg/indexjpg"),

	/** The upgrade page. */
	UPGRADE_PAGE("/dashboard/upgrade"),

	/** The login hist list page. */
	LOGIN_HIST_LIST_PAGE("/loginhistory/loginHistoryList"),

	/** The login hist sum page. */
	LOGIN_HIST_SUM_PAGE("/loginhistory/loginHistorySum"),

	/** The logo list page. */
	LOGO_LIST_PAGE("/logo/logoList"),

	/** The logo edit page. */
	LOGO_EDIT_PAGE("/logo/logo"),

	/** The lucene page. */
	LUCENE_PAGE("/lucene/reindexer"),

	/** The league list page. */
	LEAGUE_LIST_PAGE("/myleague/myleagueList"),

	/** The league edit page. */
	LEAGUE_EDIT_PAGE("/myleague/myleague"),

	/** The news list page. */
	NEWS_LIST_PAGE("/news/newsList"),

	/** The news edit page. */
	NEWS_EDIT_PAGE("/news/news"),

	/** The newscat list page. */
	NEWSCAT_LIST_PAGE("/newsCategory/newsCategoryList"),

	/** The newscat edit page. */
	NEWSCAT_EDIT_PAGE("/newsCategory/newsCategory"),

	/** The nsort list page. */
	NSORT_LIST_PAGE("/nsort/nsortList"),

	/** The nsort edit page. */
	NSORT_EDIT_PAGE("/nsort/nsort"),

	/** The nsort appendbrand page. */
	NSORT_APPENDBRAND_PAGE("/nsort/appendBrand"),

	/** The processing order list page. */
	PROCESSING_ORDER_LIST_PAGE("/order/processingOrder"),
	
	/** The processed order list page. */
	PROCESSED_ORDER_LIST_PAGE("/order/processedOrder"),

	/** The pay page. */
	PAY_PAGE("/payment/payto"),

	/** The pay type list page. */
	PAY_TYPE_LIST_PAGE("/payType/payTypeList"),

	/** The pay type edit page. */
	PAY_TYPE_EDIT_PAGE("/payType/payType"),

	/** The prod list page. */
	PROD_LIST_PAGE("/prod/prodList"),

	/** The prod edit page. */
	PROD_EDIT_PAGE("/prod/prod"),

	/** The append product. */
	APPEND_PRODUCT("/prod/appendProduct"),

	/** The createp roduct step. */
	CREATEP_RODUCT_STEP("/prod/createProductStep"),

	/** The prod comm list page. */
	PROD_COMM_LIST_PAGE("/prodComment/prodCommentList"),

	/** The prod comm edit page. */
	PROD_COMM_EDIT_PAGE("/prodComment/prodComment"),

	/** The pub list page. */
	PUB_LIST_PAGE("/pub/pubList"),

	/** The pub edit page. */
	PUB_EDIT_PAGE("/pub/pub"),

	/** The shop detail list page. */
	SHOP_DETAIL_LIST_PAGE("/shopDetail/shopDetailList"),

	/** The shop detail edit page. */
	SHOP_DETAIL_EDIT_PAGE("/shopDetail/shopDetail"),

	/** The sort list page. */
	SORT_LIST_PAGE("/sort/sortList"),

	/** The sort edit page. */
	SORT_EDIT_PAGE("/sort/sort"),

	/** The param list page. */
	PARAM_LIST_PAGE("/systemParameter/systemParameterList"),

	/** The cache list page. */
	CACHE_LIST_PAGE("/cache/cacheList"),
	
	/** The param edit page. */
	PARAM_EDIT_PAGE("/systemParameter/systemParameter"),

	/** The user comm list page. */
	USER_COMM_LIST_PAGE("/userComment/userCommentList"),

	/** The user comm edit page. */
	USER_COMM_EDIT_PAGE("/userComment/userComment"),

	/** The user detail list page. */
	USER_DETAIL_LIST_PAGE("/userDetail/userDetailList"),

	/** The vlog list page. */
	VLOG_LIST_PAGE("/visitLog/visitLogList"),

	/** The vlog edit page. */
	VLOG_EDIT_PAGE("/visitLog/visitLog"),

	/** The show dynamic attribute. */
	SHOW_DYNAMIC_ATTRIBUTE("/xml/showDynamicAttribute"),

	/** The show dynamic. */
	SHOW_DYNAMIC("/xml/showdynamic"),

	/** The dynamic attribute. */
	DYNAMIC_ATTRIBUTE("/xml/dynamicAttribute"),

	/** The modifyprice. */
	MODIFYPRICE("/order/modifyPrice"), 
	
	/** The deliverycorp list page. */
	DELIVERYCORP_LIST_PAGE("/deliveryCorp/deliveryCorpList"), 
	
	/** The deliverycorp edit page. */
	DELIVERYCORP_EDIT_PAGE("/deliveryCorp/deliveryCorp"), 
	
	/** The deliverytype list page. */
	DELIVERYTYPE_LIST_PAGE("/deliveryType/deliveryTypeList"), 
	
	/** The deliverytype edit page. */
	DELIVERYTYPE_EDIT_PAGE("/deliveryType/deliveryType"), 
	
	/** The partner list page. */
	PARTNER_LIST_PAGE("/partner/partnerList"), 
	
	/** The partner edit page. */
	PARTNER_EDIT_PAGE("/partner/partner"),
	
	/** The partner change password page. */
	PARTNER_CHANGE_PASSWORD_PAGE("/partner/partnerChangePassword"), 
	
	/** The tag list. */
	TAG_LIST("/tag/tagList"),
	
	/** The tag. */
	TAG("/tag/tag"),
	
	EVENT_LIST_PAGE("/event/eventList"),
	
	EVENT_EDIT_PAGE("/event/event")
	;
	
	/** The value. */
	private final String value;
	
	/** The templates. */
	private List<String> templates;
	

	/**
	 * Instantiates a new back page.
	 * 
	 * @param value
	 *            the value
	 * @param template
	 *            the template
	 */
	private BackPage(String value,String ... template) {
		this.value = value;
		if(AppUtils.isNotBlank(template)){
			this.templates = new ArrayList<String>();
			for (String temp : template) {
				templates.add(temp);
			}
		}
		
	}
	

	/* (non-Javadoc)
	 * @see com.legendshop.core.constant.PageDefinition#getValue(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String getValue(HttpServletRequest request, HttpServletResponse response) {
		return getValue(request,response,value,templates);
	}
	
	/* (non-Javadoc)
	 * @see com.legendshop.core.constant.PageDefinition#getValue(javax.servlet.http.HttpServletRequest, java.lang.String)
	 */
	@Override
	public String getValue(HttpServletRequest request, HttpServletResponse response,String path,List<String> templates) {
		return PagePathCalculator.calculateBackendPath(request,response,path,templates);
	}


	/* (non-Javadoc)
	 * @see com.legendshop.core.constant.PageDefinition#getNativeValue()
	 */
	@Override
	public String getNativeValue() {
		return value;
	}


	/* (non-Javadoc)
	 * @see com.legendshop.core.constant.PageDefinition#getTemplates()
	 */
	@Override
	public List<String> getTemplates() {
		return this.templates;
	}

	
}
