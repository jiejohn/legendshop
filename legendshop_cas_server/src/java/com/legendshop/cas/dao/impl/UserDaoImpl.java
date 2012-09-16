package com.legendshop.cas.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.legendshop.cas.CasConstant;
import com.legendshop.cas.dao.UserDao;
import com.legendshop.cas.model.User;
import com.legendshop.cas.model.UserPassword;

/**
 * To storage and update the user correlated entity.
 * 
 * @author George Guo
 * 
 */
public class UserDaoImpl implements UserDao {

	private static Log logger = LogFactory.getLog(UserDaoImpl.class);

	private JdbcTemplate jdbcTemplate;

	@Override
	public int getPasswordIncorrectCount(String userAccount) {
		if (userAccount == null) {
			return 0;
		}

		String sql = "select a.pwderrorcount from uums_user_extends a, FRAME_ORGSTRUC_USER b where "
				+ "a.userId=b.userId and b.account=?";
		try {
			Integer errorCount = (Integer) jdbcTemplate
					.queryForObject(sql, new Object[] { userAccount }, Integer.class);
			if (logger.isDebugEnabled()) {
				logger.debug("Found password error count is [" + errorCount + "] for user [" + userAccount + "]. ");
			}
			return errorCount == null ? 0 : errorCount.intValue();
		} catch (Exception e) {
			if (e instanceof org.springframework.dao.EmptyResultDataAccessException) {
				logger.error("No user extends info found with userId: " + userAccount);
			} else {
				logger.error("Error when query password incorrect count for user with userId: " + userAccount, e);
			}
		}
		return 0;
	}

	@Override
	public void updatIncorrectPasswordCount(String userAccount, int count, Date frozenExpireTime) {
		if (userAccount == null) {
			return;
		}

		// Query the userId
		String userId = null;
		String queryUserIdSql = "select userId from FRAME_ORGSTRUC_USER where account=?";
		try {
			userId = (String) jdbcTemplate.queryForObject(queryUserIdSql, new Object[] { userAccount }, String.class);

			if (logger.isDebugEnabled()) {
				logger.debug("Found userId: [" + userId + "] with account: " + userAccount);
			}
		} catch (Exception e) {
			logger.error("error when update incorrect password count", e);
		}

		if (userId == null) {
			logger.error("Can't find userId with account: " + userAccount);
			return;
		}

		if (frozenExpireTime == null) {
			String sql = "update uums_user_extends set pwderrorcount=? where userid=?";
			Object[] params = new Object[] { count, userId };
			try {
				jdbcTemplate.update(sql, params);
			} catch (Exception e) {
				logger.error("error when update incorrect password count", e);
			}
		} else {
			String sql = "update uums_user_extends set pwderrorcount=?, frozenexpiretime=? where userid=?";
			Object[] params = new Object[] { count, frozenExpireTime, userId };

			// update incorrect times and frozen expire time
			try {
				jdbcTemplate.update(sql, params);
			} catch (Exception e) {
				logger.error("error when update incorrect password count and password expire time", e);
				return;
			}
			// update user account status
			sql = "update frame_orgstruc_user set userstate=? where account=?";
			params = new Object[] { CasConstant.USER_STATUS_FROZEN, userAccount };
			try {
				jdbcTemplate.update(sql, params);
			} catch (Exception e) {
				logger.error("error when update user status for user account: " + userAccount, e);
			}
		}

	}

	@Override
	public User getUserDetail(String account) {
		if (account == null) {
			return null;
		}

		StringBuilder s = new StringBuilder();
		s.append("select b.UserID,b.UserState, b.mobile, a.accounteffectDate,a.AccountDisableDate,a.PwdValidity, a.PwdModifyDate, a.frozenExpireTime");
		s.append(" from FRAME_ORGSTRUC_USER b left join UUMS_USER_EXTENDS a ");
		s.append("on b.UserID=a.UserID where b.account=?");

		try {
			User user = (User) jdbcTemplate.queryForObject(s.toString(), new Object[] { account },
					new UserDetailRowMapper());

			if (logger.isDebugEnabled()) {
				logger.debug("Found user detail info exist for user with account: " + account);
			}

			return user;
		} catch (Exception e) {
			logger.error("Error when query user password detail and expire time for user with account: " + account
					+ ", exception: " + e.getMessage());
		}
		return null;
	}

	class UserDetailRowMapper implements RowMapper {
		@Override
		public Object mapRow(ResultSet rs, int index) throws SQLException {

			User user = new User();
			user.setUserId(rs.getString("userid"));
			user.setEffectTime(rs.getDate("accounteffectDate"));
			user.setExpireTime(rs.getDate("accountdisableDate"));
			user.setStatus(rs.getInt("UserState"));
			user.setFrozenExpireTime(rs.getDate("frozenExpireTime"));

			UserPassword userPassword = new UserPassword();

			userPassword.setExpireTime(rs.getDate("pwdvalidity"));
			userPassword.setLastModifyTime(rs.getDate("PwdModifyDate"));

			user.setUserPassword(userPassword);
			user.setMobile(rs.getString("mobile"));

			return user;
		}
	}

	/**
	 * @param jdbcTemplate
	 *            the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
