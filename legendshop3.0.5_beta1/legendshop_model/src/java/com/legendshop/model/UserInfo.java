/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.model;

import java.io.Serializable;

import com.legendshop.model.entity.ShopDetailView;

/**
 * The Class UserInfo.
 */
public class UserInfo implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2459516151507147213L;

	/** The current version. */
	private String currentVersion;
	
	/** The newest version. */
	private String newestVersion;
	
	//会员总数
	/** The user total num. */
	private Long userTotalNum;
	
	//商家总数
	/** The shop total num. */
	private Long shopTotalNum;
	
	//未处理订单数
	/** The processing order num. */
	private Long processingOrderNum;
	
	//未读消息
	/** The un read message num. */
	private Long unReadMessageNum;
	
	//文章数
	/** The article num. */
	private Long articleNum;
	
	/** The shop detail. */
	private ShopDetailView shopDetail;

	/**
	 * Gets the current version.
	 * 
	 * @return the current version
	 */
	public String getCurrentVersion() {
		return currentVersion;
	}

	/**
	 * Sets the current version.
	 * 
	 * @param currentVersion
	 *            the new current version
	 */
	public void setCurrentVersion(String currentVersion) {
		this.currentVersion = currentVersion;
	}

	/**
	 * Gets the newest version.
	 * 
	 * @return the newest version
	 */
	public String getNewestVersion() {
		return newestVersion;
	}

	/**
	 * Sets the newest version.
	 * 
	 * @param newestVersion
	 *            the new newest version
	 */
	public void setNewestVersion(String newestVersion) {
		this.newestVersion = newestVersion;
	}

	/**
	 * Gets the user total num.
	 * 
	 * @return the user total num
	 */
	public Long getUserTotalNum() {
		return userTotalNum;
	}

	/**
	 * Sets the user total num.
	 * 
	 * @param userTotalNum
	 *            the new user total num
	 */
	public void setUserTotalNum(Long userTotalNum) {
		this.userTotalNum = userTotalNum;
	}

	/**
	 * Gets the shop total num.
	 * 
	 * @return the shop total num
	 */
	public Long getShopTotalNum() {
		return shopTotalNum;
	}

	/**
	 * Sets the shop total num.
	 * 
	 * @param shopTotalNum
	 *            the new shop total num
	 */
	public void setShopTotalNum(Long shopTotalNum) {
		this.shopTotalNum = shopTotalNum;
	}

	/**
	 * Gets the processing order num.
	 * 
	 * @return the processing order num
	 */
	public Long getProcessingOrderNum() {
		return processingOrderNum;
	}

	/**
	 * Sets the processing order num.
	 * 
	 * @param processingOrderNum
	 *            the new processing order num
	 */
	public void setProcessingOrderNum(Long processingOrderNum) {
		this.processingOrderNum = processingOrderNum;
	}

	/**
	 * Gets the un read message num.
	 * 
	 * @return the un read message num
	 */
	public Long getUnReadMessageNum() {
		return unReadMessageNum;
	}

	/**
	 * Sets the un read message num.
	 * 
	 * @param unReadMessageNum
	 *            the new un read message num
	 */
	public void setUnReadMessageNum(Long unReadMessageNum) {
		this.unReadMessageNum = unReadMessageNum;
	}

	/**
	 * Gets the article num.
	 * 
	 * @return the article num
	 */
	public Long getArticleNum() {
		return articleNum;
	}

	/**
	 * Sets the article num.
	 * 
	 * @param articleNum
	 *            the new article num
	 */
	public void setArticleNum(Long articleNum) {
		this.articleNum = articleNum;
	}

	/**
	 * Gets the shop detail.
	 * 
	 * @return the shop detail
	 */
	public ShopDetailView getShopDetail() {
		return shopDetail;
	}

	/**
	 * Sets the shop detail.
	 * 
	 * @param shopDetail
	 *            the new shop detail
	 */
	public void setShopDetail(ShopDetailView shopDetail) {
		this.shopDetail = shopDetail;
	}

	
}
