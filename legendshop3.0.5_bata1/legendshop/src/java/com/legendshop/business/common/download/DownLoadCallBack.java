/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.common.download;

/**
 * 下载Callback.
 */
public interface DownLoadCallBack {
	
	/**
	 * After download.
	 * 
	 * @param entity
	 *            the entity
	 */
	public void afterDownload(Object entity);
	
	/**
	 * Check can download.
	 * 
	 * @param ip
	 *            the ip
	 * @param filename
	 *            the filename
	 * @return true, if successful
	 */
	public boolean checkCanDownload(String ip,String filename) ;
	
    //检查用户是否订购了该产品
    /**
	 * Checks if is user order product.
	 * 
	 * @param prodId
	 *            the prod id
	 * @param userName
	 *            the user name
	 * @return true, if is user order product
	 */
    public boolean isUserOrderProduct(Long prodId, String userName);
}
