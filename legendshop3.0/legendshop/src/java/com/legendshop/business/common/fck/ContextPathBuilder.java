/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.common.fck;

import javax.servlet.http.HttpServletRequest;

import net.fckeditor.requestcycle.impl.ServerRootPathBuilder;

import com.legendshop.core.AttributeKeys;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.exception.PermissionException;
import com.legendshop.core.helper.PropertiesUtil;

/**
 * 文件上传路径 LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ---------------------------------------------------------------------------- 
 * 
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public class ContextPathBuilder extends ServerRootPathBuilder {
	
	private final String PIC_PATH = "/fckeditor";

	/* (non-Javadoc)
	 * @see net.fckeditor.requestcycle.impl.ServerRootPathBuilder#getUserFilesPath(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String getUserFilesPath(HttpServletRequest request) {
		// 在此可以根据从session中读取的用户名或者ID来对应其可以操作的目录
		String userName = (String) request.getSession().getAttribute(AttributeKeys.USER_NAME);
		if (userName == null) {
			throw new PermissionException("did not logon yet!",EntityCodes.RIGHT);
		}

		return new StringBuffer().append(super.getUserFilesPath(request)).append("/").append(userName).append(PIC_PATH).toString();
	}

	/* (non-Javadoc)
	 * @see net.fckeditor.requestcycle.impl.ServerRootPathBuilder#getUserFilesAbsolutePath(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String getUserFilesAbsolutePath(HttpServletRequest request) {
		String userName = (String) request.getSession().getAttribute(AttributeKeys.USER_NAME);
		if (userName == null) {
			throw new PermissionException("did not logon yet!",EntityCodes.RIGHT);
		}
		return new StringBuffer().append(PropertiesUtil.getBigFilesAbsolutePath()).append("/").append(userName).append(PIC_PATH).toString();
	}

}
