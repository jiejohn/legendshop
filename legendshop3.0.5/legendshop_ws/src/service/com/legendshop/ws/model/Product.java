/*
 * LegendShop 多用户商城系统
 * 
 *  版权所有, 并保留所有权利。
 * 
 */
package com.legendshop.ws.model;

/**
 * 产品对象.
 */
public class Product extends AbstractProduct {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7571396124663475715L;

	/**
	 * Instantiates a new product.
	 */
	public Product() {

	}
	
	/**
	 * Instantiates a new product.
	 * 
	 * @param prodId
	 *            the prod id
	 * @param sortId
	 *            the sort id
	 * @param nsortId
	 *            the nsort id
	 * @param subNsortId
	 *            the sub nsort id
	 * @param prodName
	 *            the prod name
	 */
	public Product(Long prodId, Long sortId, Long nsortId, Long subNsortId, String prodName) {
		this.prodId = prodId;
		this.sortId = sortId;
		this.nsortId = nsortId;
		this.subNsortId = subNsortId;
		this.name = prodName;
	}
	
	/**
	 * Instantiates a new product.
	 * 
	 * @param prodId
	 *            the prod id
	 * @param sortId
	 *            the sort id
	 * @param nsortId
	 *            the nsort id
	 * @param subNsortId
	 *            the sub nsort id
	 * @param prodName
	 *            the prod name
	 * @param pic
	 *            the pic
	 * @param price
	 *            the price
	 * @param cash
	 *            the cash
	 * @param proxyPrice
	 *            the proxy price
	 * @param views
	 *            the views
	 * @param buys
	 *            the buys
	 */
	public Product(Long prodId, Long sortId, Long nsortId, Long subNsortId, String prodName, String pic, Double price,
			Double cash, Double proxyPrice, Integer views, Integer buys) {
		this.prodId = prodId;
		this.sortId = sortId;
		this.nsortId = nsortId;
		this.subNsortId = subNsortId;
		this.name = prodName;
		this.pic = pic;
		this.price = price;
		this.cash = cash;
		this.proxyPrice = proxyPrice;
		this.views = views;
		this.buys = buys;
	}

}