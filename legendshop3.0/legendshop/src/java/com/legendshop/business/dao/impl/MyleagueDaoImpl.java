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
import org.springframework.cache.annotation.CacheEvict;

import com.legendshop.business.dao.MyleagueDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SqlQuery;
import com.legendshop.model.entity.Myleague;
import com.legendshop.util.sql.ConfigCode;

/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public class MyleagueDaoImpl extends BaseDaoImpl implements MyleagueDao {

	/** The log. */
	private static Logger log = LoggerFactory.getLogger(MyleagueDaoImpl.class);

	@Override
	public PageSupport getLeague(String shopName, String curPageNO) {
		SqlQuery sqlQuery = new SqlQuery(15, curPageNO);
		String queryAllSQL = ConfigCode.getInstance().getCode("biz.QueryLeagueCount");
		String querySQL = ConfigCode.getInstance().getCode("biz.QueryLeague");
		sqlQuery.setAllCountString(queryAllSQL);
		sqlQuery.setQueryString(querySQL);
		sqlQuery.addParams(shopName);
		sqlQuery.addEntityClass("myleague", Myleague.class);
		return find(sqlQuery);
	}

	@Override
	@CacheEvict(value = "Myleague", key = "#id")
	public void deleteMyleagueById(Long id) {
		deleteById(Myleague.class, id);
	}

	@Override
	@CacheEvict(value = "Myleague", key = "#myleague.id")
	public void updateMyleague(Myleague myleague) {
		update(myleague);
	}
}
