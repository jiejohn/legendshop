/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.business.dao.CommonUtilDao;
import com.legendshop.business.service.CommonUtil;
/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public class CommonUtilImpl implements CommonUtil {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(CommonServiceUtil.class);
	
	/** The common util dao. */
	private CommonUtilDao commonUtilDao;


	// 保存后台管理员角色
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.CommonUtil#saveAdminRight(com.legendshop.core.dao.impl.BaseDaoImpl, java.lang.String)
	 */
	@Override
	public void saveAdminRight(String userId) {
		commonUtilDao.saveAdminRight(userId);
	}

	// 保存用户角色
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.CommonUtil#saveUserRight(com.legendshop.core.dao.impl.BaseDaoImpl, java.lang.String)
	 */
	@Override
	public void saveUserRight(String userId) {
		commonUtilDao.saveUserRight(userId);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.CommonUtil#removeAdminRight(com.legendshop.core.dao.impl.BaseDaoImpl, java.lang.String)
	 */
	@Override
	public void removeAdminRight(String userId) {
		commonUtilDao.removeAdminRight(userId);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.CommonUtil#removeUserRight(com.legendshop.core.dao.impl.BaseDaoImpl, java.lang.String)
	 */
	@Override
	public void removeUserRight(String userId) {
		commonUtilDao.removeUserRight(userId);
	}

	/**
	 * Sets the common util dao.
	 * 
	 * @param commonUtilDao
	 *            the new common util dao
	 */
	@Required
	public void setCommonUtilDao(CommonUtilDao commonUtilDao) {
		this.commonUtilDao = commonUtilDao;
	}

}
