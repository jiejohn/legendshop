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

import com.legendshop.model.UserInfo;
import com.legendshop.model.entity.ShopDetailView;
import com.legendshop.spi.service.BaseService;

/**
 * The Interface IndexService.
 */
public interface IndexService extends BaseService {

	/**
	 * Gets the index.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the index
	 */
	public String getIndex(HttpServletRequest request, HttpServletResponse response);

	/**
	 * Index admin.
	 * 
	 * @param userName
	 *            the user name
	 * @param shopDetail
	 *            the shop detail
	 * @return the user info
	 */
	public abstract UserInfo getAdminIndex(String userName, ShopDetailView shopDetail);

}