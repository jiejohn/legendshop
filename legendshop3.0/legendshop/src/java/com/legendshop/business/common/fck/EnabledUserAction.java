/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.common.fck;

import javax.servlet.http.HttpServletRequest;

import net.fckeditor.requestcycle.UserAction;

/**
 * 验证用户是否有权浏览与上传.
 */
public class EnabledUserAction implements UserAction {

	// 权限--新建文件夹
	/* (non-Javadoc)
	 * @see net.fckeditor.requestcycle.UserAction#isCreateFolderEnabled(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public boolean isCreateFolderEnabled(HttpServletRequest request) {
		return true;
	}

	// 权限--浏览服务器上的文件列表
	/* (non-Javadoc)
	 * @see net.fckeditor.requestcycle.UserAction#isEnabledForFileBrowsing(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public boolean isEnabledForFileBrowsing(HttpServletRequest request) {
		// System.out.println("isEnabledForFileBrowsing calling");
		return true;
	}

	// 权限--上传文件
	/* (non-Javadoc)
	 * @see net.fckeditor.requestcycle.UserAction#isEnabledForFileUpload(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public boolean isEnabledForFileUpload(HttpServletRequest request) {
		// System.out.println("isEnabledForFileUpload calling");
		return true;
	}

}
