/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.jdbc.core.JdbcTemplate;

import com.legendshop.business.dao.LoginHistoryDao;
import com.legendshop.business.service.LoginHistoryService;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SqlQuery;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.LoginHistory;
import com.legendshop.util.ip.IPSeeker;
import com.legendshop.util.sql.ConfigCode;

/**
 * 登录历史服务.
 */
public class LoginHistoryServiceImpl implements LoginHistoryService {

	/** The log. */
	private final Logger log = LoggerFactory.getLogger(LoginHistoryServiceImpl.class);

	/** The login history dao. */
	private LoginHistoryDao loginHistoryDao;

	/** The jdbc template. */
	private JdbcTemplate jdbcTemplate;


	/* (non-Javadoc)
	 * @see com.legendshop.business.service.LoginHistoryService#saveLoginHistory(java.lang.String, java.lang.String)
	 */
	@Override
	public void saveLoginHistory(String userName, String ip) {
		if (PropertiesUtil.getObject(ParameterEnum.LOGIN_LOG_ENABLE, Boolean.class)) {
			log.debug("user {} login system from ip {}", userName, ip);
			try {
				LoginHistory loginHistory = new LoginHistory();
				loginHistory.setIp(ip);
				loginHistory.setTime(new Date());
				loginHistory.setArea(IPSeeker.getInstance().getArea(ip));
				loginHistory.setCountry(IPSeeker.getInstance().getCountry(ip));
				loginHistory.setUserName(userName);
				loginHistoryDao.save(loginHistory);
				String sql = ConfigCode.getInstance().getCode("login.updateUserDetail");
				if(log.isDebugEnabled()){
					log.debug("execute run sql {}, Ip {}, Time {}, UserName {} ", new Object[]{sql,loginHistory.getIp(), loginHistory.getTime(), loginHistory.getUserName()});
				}
				jdbcTemplate.update(sql, new Object[] {
						loginHistory.getIp(), loginHistory.getTime(), loginHistory.getUserName() });

			} catch (Exception e) {
				log.error("save userLoginHistory", e);
			}
		}
	}

	/**
	 * Sets the jdbc template.
	 * 
	 * @param jdbcTemplate
	 *            the new jdbc template
	 */
	@Required
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.LoginHistoryService#findLoginHistory(com.legendshop.core.dao.support.CriteriaQuery)
	 */
	@Override
	public PageSupport getLoginHistory(CriteriaQuery cq) {
		return loginHistoryDao.getLoginHistory(cq);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.LoginHistoryService#findLoginHistory(com.legendshop.core.dao.support.SqlQuery)
	 */
	@Override
	public PageSupport getLoginHistoryBySQL(SqlQuery query) {
		return loginHistoryDao.getLoginHistoryBySQL(query);
	}

	/**
	 * Sets the login history dao.
	 * 
	 * @param loginHistoryDao
	 *            the new login history dao
	 */
	@Required
	public void setLoginHistoryDao(LoginHistoryDao loginHistoryDao) {
		this.loginHistoryDao = loginHistoryDao;
	}
}
