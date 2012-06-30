/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.spi.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.legendshop.model.entity.Product;

/**
 * The Interface GroupService.
 */
public interface GroupService extends BaseService{

	/**
	 * Gets the index.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param curPageNO
	 *            the cur page no
	 * @param order
	 *            the order
	 * @param seq
	 *            the seq
	 * @param product
	 *            the product
	 * @return the index
	 */
	public String getIndex(HttpServletRequest request, HttpServletResponse response,String curPageNO,String order,String seq,Product product);
	
	/**
	 * Gets the view.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param id
	 *            the id
	 * @return the view
	 */
	public String getView(HttpServletRequest request, HttpServletResponse response,Long id) ;
}
