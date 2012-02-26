/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service;

import com.legendshop.model.UserInfo;
import com.legendshop.model.entity.ShopDetailView;

public interface IndexService {

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