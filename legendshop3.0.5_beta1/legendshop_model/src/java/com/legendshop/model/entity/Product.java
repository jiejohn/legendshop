/*
 * LegendShop 多用户商城系统
 * 
 *  版权所有, 并保留所有权利。
 * 
 */
package com.legendshop.model.entity;

/**
 * 产品对象.
 */
public class Product extends AbstractProduct {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7571396124663475715L;

	public Product() {

	}
	
	public Product(Long prodId, Long sortId, Long nsortId, Long subNsortId, String prodName) {
		this.prodId = prodId;
		this.sortId = sortId;
		this.nsortId = nsortId;
		this.subNsortId = subNsortId;
		this.name = prodName;
	}
	
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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(" [prodId=").append(prodId).append(", sortId=").append(sortId).append(", nsortId=").append(nsortId).append(", name=").append(name).append(", userName=").append(userName).append("] ");
		return sb.toString();
	}

}