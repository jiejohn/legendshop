/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.legendshop.business.form.SearchForm;
import com.legendshop.business.form.UserForm;
import com.legendshop.model.entity.ShopDetail;
import com.legendshop.model.entity.ShopDetailView;
import com.legendshop.model.entity.Sub;

public interface BusinessService extends BaseService{

	/**
	 * Index.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	public abstract String getIndex(HttpServletRequest request, HttpServletResponse response);

	/**
	 * Gets the shop detail view from session.
	 * 
	 * @param shopName
	 *            the shop name
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the shop detail view
	 */
	public abstract ShopDetailView getShopDetailView(String shopName, HttpServletRequest request,
			HttpServletResponse response);

	/**
	 * Sets the local by shop detail.
	 * 
	 * @param langStyle
	 *            the lang style
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 */
	public abstract void setLocalByShopDetail(ShopDetailView shopDetail, HttpServletRequest request,
			HttpServletResponse response);

	/**
	 * Top.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	public abstract String getTop(HttpServletRequest request, HttpServletResponse response);

	/**
	 * Topsort.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	public abstract String getTopSort(HttpServletRequest request, HttpServletResponse response);

	/**
	 * Topnews.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	public abstract String getTopnews(HttpServletRequest request, HttpServletResponse response);

	/**
	 * Search.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param searchForm
	 *            the search form
	 * @return the string
	 */
	public abstract String search(HttpServletRequest request, HttpServletResponse response, SearchForm searchForm);

	/**
	 * Searchall.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param keyword
	 *            the keyword
	 * @param entityType
	 *            the entity type
	 * @return the string
	 */
	public abstract String searchall(HttpServletRequest request, HttpServletResponse response, String keyword,
			Integer entityType);

	/**
	 * 大分类商品.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param curPageNO
	 *            the cur page no
	 * @param sortId
	 *            the sort id
	 * @return the string
	 */
	public abstract String getSort(HttpServletRequest request, HttpServletResponse response, String curPageNO, Long sortId);

	/**
	 * Nsort.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param sortId
	 *            the sort id
	 * @param nsortId
	 *            the nsort id
	 * @param subNsortId
	 *            the sub nsort id
	 * @return the string
	 */
	public abstract String getSecSort(HttpServletRequest request, HttpServletResponse response,Long sortId,Long nsortId,Long subNsortId);

	// 查看商品
	/**
	 * Views.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param prodId
	 *            the prod id
	 * @return the string
	 */
	public abstract String getViews(HttpServletRequest request, HttpServletResponse response, Long prodId);

	/**
	 * Order detail.
	 * 
	 * @param request
	 *            the request
	 * @param sub
	 *            the sub
	 * @param subNumber
	 *            the sub number
	 * @return the string
	 */
	public abstract String getOrderDetail(HttpServletRequest request, Sub sub,String userName, String subNumber);

	/**
	 * News.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param newsId
	 *            the news id
	 * @return the string
	 */
	public abstract String getNews(HttpServletRequest request, HttpServletResponse response, Long newsId);

	/**
	 * All news.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param curPageNO
	 *            the cur page no
	 * @param newsCategoryId
	 *            the news category id
	 * @return the string
	 */
	public abstract String getAllNews(HttpServletRequest request, HttpServletResponse response, String curPageNO,
			Long newsCategoryId);

	/**
	 * Hotsale.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	public abstract String getHotSale(HttpServletRequest request, HttpServletResponse response);

	/**
	 * Friendlink.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	public abstract String getFriendlink(HttpServletRequest request, HttpServletResponse response);

	/**
	 * Hoton.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	public abstract String getHotProduct(HttpServletRequest request, HttpServletResponse response);

	/**
	 * Hot view.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	public abstract String getHotView(HttpServletRequest request, HttpServletResponse response);

	/**
	 * User reg.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param form
	 *            the form
	 * @return the string
	 */
	public abstract String saveUserReg(HttpServletRequest request, HttpServletResponse response, UserForm form);

	/**
	 * Reg.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	public abstract String saveUserReg(HttpServletRequest request, HttpServletResponse response);

	/**
	 * Checks if is user info valid.
	 * 
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @return true, if is user info valid
	 */
	public abstract boolean isUserInfoValid(UserForm form, HttpServletRequest request);

	/**
	 * Adds the shop.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param shopDetail
	 *            the shop detail
	 * @return the string
	 */
	public abstract String saveShop(HttpServletRequest request, HttpServletResponse response, ShopDetail shopDetail);

	/**
	 * Myaccount.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	public abstract String getMyAccount(HttpServletRequest request, HttpServletResponse response);

	/**
	 * Gets the simple info shop detail.
	 * 
	 * @param storeName
	 *            the store name
	 * @return the simple info shop detail
	 */
	public abstract ShopDetailView getSimpleInfoShopDetail(String storeName);

	/**
	 * 修改我的帐户，用于修改用户信息.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param actionForm
	 *            the action form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the action forward
	 * @throws Exception
	 *             the exception
	 */
	public abstract String updateAccount(HttpServletRequest request, HttpServletResponse response, UserForm form);

	/**
	 * Ipsearch.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param ipAddress
	 *            the ip address
	 * @return the string
	 */
	public abstract String getIpAddress(HttpServletRequest request, HttpServletResponse response, String ipAddress);

	/**
	 * League.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	public abstract String getLeague(HttpServletRequest request, HttpServletResponse response);

	/**
	 * Shopcontact.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	public abstract String getShopcontact(HttpServletRequest request, HttpServletResponse response);

	/**
	 * User reg success.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param userName
	 *            the user name
	 * @param registerCode
	 *            the register code
	 * @return the string
	 */
	public abstract String updateUserReg(HttpServletRequest request, HttpServletResponse response, String userName,
			String registerCode);

	/**
	 * Copy all.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	public abstract String getNewsforCommon(HttpServletRequest request, HttpServletResponse response);

	/**
	 * Product gallery.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param actionForm
	 *            the action form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the action forward
	 */
	public abstract String getProductGallery(HttpServletRequest request, HttpServletResponse response, Long prodId);
	
	
	/**
	 * Gets the sub by sub number.
	 * 
	 * @param subNumber
	 *            the sub number
	 * @return the sub by sub number
	 */
	public abstract Sub getSubBySubNumber(String subNumber);

}