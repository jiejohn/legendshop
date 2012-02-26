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
 * 此类可以从session中读取用户信息，判断是否有权限进行相关操作
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。 ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ---------------------------------------------------------------------------- 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public class EnabledUserAction implements UserAction {

	// 权限--新建文件夹
	/* (non-Javadoc)
	 * @see net.fckeditor.requestcycle.UserAction#isCreateFolderEnabled(javax.servlet.http.HttpServletRequest)
	 */
	public boolean isCreateFolderEnabled(HttpServletRequest request) {
		return true;
	}

	// 权限--浏览服务器上的文件列表
	/* (non-Javadoc)
	 * @see net.fckeditor.requestcycle.UserAction#isEnabledForFileBrowsing(javax.servlet.http.HttpServletRequest)
	 */
	public boolean isEnabledForFileBrowsing(HttpServletRequest request) {
		// System.out.println("isEnabledForFileBrowsing calling");
		return true;
	}

	// 权限--上传文件
	/* (non-Javadoc)
	 * @see net.fckeditor.requestcycle.UserAction#isEnabledForFileUpload(javax.servlet.http.HttpServletRequest)
	 */
	public boolean isEnabledForFileUpload(HttpServletRequest request) {
		// System.out.println("isEnabledForFileUpload calling");
		return true;
	}

}
