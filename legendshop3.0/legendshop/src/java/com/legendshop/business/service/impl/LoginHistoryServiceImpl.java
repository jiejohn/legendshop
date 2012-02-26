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
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.legendshop.business.helper.TaskThread;
import com.legendshop.business.service.LoginHistoryService;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.dao.BaseDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SqlQuery;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.event.TaskItem;
import com.legendshop.model.entity.LoginHistory;
import com.legendshop.util.ip.IPSeeker;
import com.legendshop.util.sql.ConfigCode;

/**
 * 登录历史服务.
 */
public class LoginHistoryServiceImpl implements LoginHistoryService {

	/** The log. */
	private final Logger log = LoggerFactory.getLogger(LoginHistoryServiceImpl.class);

	/** The base dao. */
	private BaseDao baseDao;

	/** The jdbc template. */
	private JdbcTemplate jdbcTemplate;

	/** The thread pool executor. */
	private ThreadPoolTaskExecutor threadPoolExecutor;

	/**
	 * Sets the thread pool executor.
	 * 
	 * @param threadPoolExecutor
	 *            the new thread pool executor
	 */
	@Required
	public void setThreadPoolExecutor(ThreadPoolTaskExecutor threadPoolExecutor) {
		this.threadPoolExecutor = threadPoolExecutor;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.LoginHistoryService#saveLoginHistory(java.lang.String, java.lang.String)
	 */
	@Override
	public void saveLoginHistory(String userName, String ip) {
		if (PropertiesUtil.getObject(ParameterEnum.LOGIN_LOG_ENABLE, Boolean.class)) {
			threadPoolExecutor.execute(new TaskThread(new PersistLoginHistoryTask(userName, ip)));
		}
	}

	/**
	 * The Class PersistLoginHistoryTask.
	 */
	class PersistLoginHistoryTask implements TaskItem {

		/** The user name. */
		private final String userName;

		/** The ip. */
		private final String ip;

		/**
		 * Instantiates a new persist login history task.
		 * 
		 * @param userName
		 *            the user name
		 * @param ip
		 *            the ip
		 */
		public PersistLoginHistoryTask(String userName, String ip) {
			this.userName = userName;
			this.ip = ip;
		}

		/* (non-Javadoc)
		 * @see com.legendshop.business.helper.TaskItem#execute()
		 */
		@Override
		public void execute() {
			log.debug("user {} login system from ip {}", userName, ip);
			try {
				LoginHistory loginHistory = new LoginHistory();
				loginHistory.setIp(ip);
				loginHistory.setTime(new Date());
				loginHistory.setArea(IPSeeker.getInstance().getArea(ip));
				loginHistory.setCountry(IPSeeker.getInstance().getCountry(ip));
				loginHistory.setUserName(userName);
				baseDao.save(loginHistory);
				jdbcTemplate.update(ConfigCode.getInstance().getCode("login.updateUserDetail"), new Object[] {
						loginHistory.getIp(), loginHistory.getTime(), loginHistory.getUserName() });

			} catch (Exception e) {
				log.error("save userLoginHistory", e);
			}

		}

	}

	/**
	 * Sets the base dao.
	 * 
	 * @param baseDao
	 *            the new base dao
	 */
	@Required
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
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
		return baseDao.find(cq);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.LoginHistoryService#findLoginHistory(com.legendshop.core.dao.support.SqlQuery)
	 */
	@Override
	public PageSupport getLoginHistoryBySQL(SqlQuery query) {
		return baseDao.find(query);
	}
}
