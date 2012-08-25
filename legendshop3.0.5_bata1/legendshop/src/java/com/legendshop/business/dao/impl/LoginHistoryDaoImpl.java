/*
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * 官方网站：http://www.legendesign.net
 */
package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.LoginHistoryDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SqlQuery;

/**
 * 登录历史Dao
 *
 */
public class LoginHistoryDaoImpl extends BaseDaoImpl implements LoginHistoryDao {

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.LoginHistoryDao#getLoginHistoryBySQL(com.legendshop.core.dao.support.SqlQuery)
	 */
	@Override
	public PageSupport getLoginHistoryBySQL(SqlQuery query) {
		return find(query);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.LoginHistoryDao#getLoginHistory(com.legendshop.core.dao.support.CriteriaQuery)
	 */
	@Override
	public PageSupport getLoginHistory(CriteriaQuery cq) {
		return find(cq);
	}

}
