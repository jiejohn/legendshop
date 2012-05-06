/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;

import com.legendshop.business.dao.LogoDao;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.Logo;

/**
 * LogoDaoImpl.
 */

public class LogoDaoImpl extends BaseDaoImpl implements LogoDao {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(LogoDaoImpl.class);

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.LogoDao#getLogo(java.lang.String)
	 */
	@Override
	@Cacheable(value="Logo",key="#shopName")
	public Logo getLogo(final String shopName) {
		log.debug("getLogo, shopName = {}", shopName);
		if (shopName == null) {
			return null;
		}
		Logo logo = findUniqueBy("from Logo where userName = ?", Logo.class, shopName);
		if (logo == null) {
			logo = getDefaultLogo();
		}
		if(logo == null){
			logo = new Logo();
			logo.setId(-1l);//id = -1 用于占位
		}
		return logo;
	}

	/**
	 * Gets the default logo.
	 * 
	 * @return the default logo
	 */
	private Logo getDefaultLogo() {
		return findUniqueBy("from Logo where userName = ?", Logo.class, PropertiesUtil.getObject(
				ParameterEnum.DEFAULT_SHOP, String.class));
	}

	/**
	 * Gets the key.
	 * 
	 * @param methodName
	 *            the method name
	 * @param storeName
	 *            the store name
	 * @return the key
	 */
	private String getKey(String methodName, String storeName) {
		return new StringBuffer().append("logo_").append(methodName).append(storeName).toString();
	}

}
