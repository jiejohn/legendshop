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
import org.springframework.jdbc.core.JdbcTemplate;

import com.legendshop.business.dao.LogoDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SimpleHqlQuery;
import com.legendshop.model.entity.ShopDetail;
import com.legendshop.spi.cache.ShopDetailUpdate;

/**
 * LogoDaoImpl.
 */

public class LogoDaoImpl extends BaseDaoImpl implements LogoDao {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(LogoDaoImpl.class);
	
	private JdbcTemplate jdbcTemplate;

	@Override

	public void deleteLogo(ShopDetail shopDetail) {
		updateLogo(shopDetail.getUserId(),shopDetail.getUserName(),  null);
	}

	@Override
	public void updateLogo(ShopDetail shopDetail) {
		updateLogo(shopDetail.getUserId(),shopDetail.getUserName(), shopDetail.getLogoPic());
	}

	private void updateLogo(String userId,String userName, String logoPic) {
		jdbcTemplate.update("update ls_shop_detail set logo_pic = ? where user_id = ?", logoPic,userId);
	}

	@Override
	public PageSupport getLogo(SimpleHqlQuery hql) {
    	hql.initSQL("biz.QueryLogo", "biz.QueryLogoCount");
		return find(hql);
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
