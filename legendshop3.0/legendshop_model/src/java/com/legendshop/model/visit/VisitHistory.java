/**
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.model.visit;

import java.util.Date;
import java.util.LinkedList;

import com.legendshop.model.entity.ProductDetail;
import com.legendshop.model.entity.ShopDetailView;

/**
 * 访问历史，保存在session.
 */
public class VisitHistory {

	/** The max length. */
	private Integer maxLength = 6;

	/** The visited prod. */
	private final LinkedList<VisitItem> visitedProd = new LinkedList<VisitItem>();

	/** The visited shop. */
	private final LinkedList<VisitItem> visitedShop = new LinkedList<VisitItem>();

	/**
	 * Addvisited product.
	 * 
	 * @param item
	 *            the item
	 */
	private void addvisitedProduct(VisitItem item) {
		boolean find = false;
		for (VisitItem visitItem : visitedProd) {
			if (visitItem.getId().equals(item.getId())) {
				find = true;
			}
		}

		if (!find) {
			if (visitedProd.size() >= maxLength) {
				visitedProd.removeLast();
			}
			visitedProd.addFirst(item);
		}

	}

	/**
	 * Addvisited shop.
	 * 
	 * @param item
	 *            the item
	 */
	private void addvisitedShop(VisitItem item) {
		boolean find = false;
		for (VisitItem visitItem : visitedShop) {
			if (visitItem.getId().equals(item.getId())) {
				find = true;
			}
		}

		if (!find) {
			if (visitedShop.size() >= maxLength) {
				visitedShop.removeLast();
			}
			visitedShop.addFirst(item);
		}

	}

	/**
	 * Visit.
	 * 
	 * @param prod
	 *            the prod
	 */
	public void visit(ProductDetail prod) {
		VisitItem item = new VisitItem();
		item.setName(prod.getName());
		item.setTitle(prod.getKeyWord());
		item.setId(String.valueOf(prod.getProdId()));
		item.setDate(new Date());
		item.setPic(prod.getPic());
		addvisitedProduct(item);
	}

	/**
	 * Visit.
	 * 
	 * @param shopDetail
	 *            the shop detail
	 */
	public void visit(ShopDetailView shopDetail) {
		VisitItem item = new VisitItem();
		item.setName(shopDetail.getStoreName());
		item.setTitle(shopDetail.getBriefDesc());
		item.setId(shopDetail.getUserId());
		item.setDate(new Date());
		addvisitedShop(item);
	}

	/**
	 * Gets the visit prod list.
	 * 
	 * @return the visit prod list
	 */
	public LinkedList<VisitItem> getVisitProdList() {
		return visitedProd;
	}

	/**
	 * Gets the visit shop list.
	 * 
	 * @return the visit shop list
	 */
	public LinkedList<VisitItem> getVisitShopList() {
		return visitedShop;
	}

	/**
	 * Gets the max length.
	 * 
	 * @return the max length
	 */
	public Integer getMaxLength() {
		return maxLength;
	}

	/**
	 * Sets the max length.
	 * 
	 * @param maxLength
	 *            the new max length
	 */
	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Visit Product: [ ");
		for (VisitItem item : visitedProd) {
			sb.append(item.getName()).append(", ");
		}
		sb.append("]");
		sb.append("\nVisit Shop: [ ");
		for (VisitItem item : visitedShop) {
			sb.append(item.getName()).append(", ");
		}
		sb.append("]");
		return sb.toString();
	}

	/**
	 * Gets the visited prod.
	 * 
	 * @return the visited prod
	 */
	public LinkedList<VisitItem> getVisitedProd() {
		return visitedProd;
	}

	/**
	 * Gets the visited shop.
	 * 
	 * @return the visited shop
	 */
	public LinkedList<VisitItem> getVisitedShop() {
		return visitedShop;
	}
}
