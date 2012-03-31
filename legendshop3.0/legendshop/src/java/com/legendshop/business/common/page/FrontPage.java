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
import com.legendshop.model.entity.ShopDetailView;

/**
 * The Enum FrontPage.
 */
public enum FrontPage  implements PathEnum{
	
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
	 * @see com.legendshop.core.constant.PathEnum#getValue(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String getValue(HttpServletRequest request) {
		return calculatePrefixPath(request) +value;
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
	
	/**
	 * Calculate prefix path.
	 * 
	 * @param request
	 *            the request
	 * @return the string
	 */
	private String calculatePrefixPath(HttpServletRequest request){
		//TODO
		ShopDetailView shopDetail = (ShopDetailView) request.getSession().getAttribute(AttributeKeys.SHOP_DETAIL);
		String path = "/frontend";
		if(shopDetail !=null){
			//每个商城可以自定义自己的模板
			//path = shopDetail.getxxx
			path += "/default";
		}else{
			path += "/default";
		}
		return path;
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
