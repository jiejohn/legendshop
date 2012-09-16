package com.legendshop.cas.auth;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.inspektr.common.ioc.annotation.NotNull;
import org.jasig.cas.adaptors.jdbc.AbstractJdbcUsernamePasswordAuthenticationHandler;
import org.jasig.cas.authentication.handler.AuthenticationException;
import org.jasig.cas.authentication.handler.PasswordEncoder;
import org.jasig.cas.authentication.principal.UsernamePasswordCredentials;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import com.legendshop.cas.dao.UserDao;

/**
 * To check the user password, the user account will be frozen if user tried
 * more than 3 times with the incorrect password.
 * 
 * @author George Guo
 * 
 */
public class QueryDBAuthenticationHandler extends AbstractJdbcUsernamePasswordAuthenticationHandler {

	private static Log logger = LogFactory.getLog(QueryDBAuthenticationHandler.class);

	@NotNull
	private String sql;

	/**
	 * The switcher to frozen user account,default is false.
	 */
	private boolean frozenUserAccount = false;

	private UserDao userDao;

	protected final boolean authenticateUsernamePasswordInternal(final UsernamePasswordCredentials credentials)
			throws AuthenticationException {
		final String username = credentials.getUsername();
		final String password = credentials.getPassword();
		final String encryptedPassword = getPasswordEncoder(username).encode(password);

		try {
			final String dbPassword = getJdbcTemplate().queryForObject(this.sql, String.class, username);
			// return dbPassword.equals(encryptedPassword);
			if (dbPassword.equals(encryptedPassword)) {
				// clear the user input incorrect password times
				updateUserAccountWhenPasswordCorrect(username);

				return true;
			}
			// update the user account information
			updateUserAccountWhenPasswordIncorrect(username);

			if (logger.isDebugEnabled()) {
				logger.debug("Password is incorrect for user:[" + username + "], with password:" + password);
			}

			return false;
		} catch (final IncorrectResultSizeDataAccessException e) {
			// this means the user name was not found.
			return false;
		}
	}

	private PasswordEncoder getPasswordEncoder(String userName) {
		PasswordEncoder encoder = this.getPasswordEncoder();
		if (encoder != null && DelegatePasswordEncoder.class.isAssignableFrom(encoder.getClass())) {
			((DelegatePasswordEncoder) encoder).setSaltKey(userName);
		}
		return encoder;
	}

	private void updateUserAccountWhenPasswordCorrect(String userAccount) {
		if (logger.isDebugEnabled()) {
			logger.debug("User [" + userAccount + "] submitted the correct password, to clear error count.");
		}

		if (!frozenUserAccount) {
			return;
		}

		userDao.updatIncorrectPasswordCount(userAccount, 0, null);

		if (logger.isDebugEnabled()) {
			logger.debug("Updated incorrect password count to 0 for user [" + userAccount + "].");
		}
	}

	private void updateUserAccountWhenPasswordIncorrect(String userAccount) {

		if (logger.isDebugEnabled()) {
			logger.debug("User [" + userAccount + "] submitted the incorrect password.");
		}
		if (!frozenUserAccount) {
			return;
		}

		int passwordErrorCount = userDao.getPasswordIncorrectCount(userAccount) + 1;
		if (passwordErrorCount >= 3) {
			// set the user account to frozen
			userDao.updatIncorrectPasswordCount(userAccount, passwordErrorCount, getUserAccountFrozenExpireTime());

			if (logger.isDebugEnabled()) {
				logger.debug("User [" + userAccount
						+ "] has been frozen since the max incorrect password times control.");
			}
		} else {
			userDao.updatIncorrectPasswordCount(userAccount, passwordErrorCount, null);

			if (logger.isDebugEnabled()) {
				logger.debug("Updated incorrect password count for user [" + userAccount + "].");
			}
		}

	}

	/**
	 * The duration time unit is minute, default value is 24 hours.
	 */
	private int userAccountFrozenDuration = 1440;

	private Date getUserAccountFrozenExpireTime() {
		long currentTimeMili = System.currentTimeMillis();
		Date frozenExpireTime = new Date(currentTimeMili + userAccountFrozenDuration * 60 * 1000L);

		if (logger.isDebugEnabled()) {
			logger.debug("Calculated frozen time is:" + frozenExpireTime);
		}

		return frozenExpireTime;
	}

	/**
	 * @param sql
	 *            The sql to set.
	 */
	public void setSql(final String sql) {
		this.sql = sql;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setFrozenUserAccount(boolean frozenUserAccount) {
		this.frozenUserAccount = frozenUserAccount;
	}

	public void setUserAccountFrozenDuration(int userAccountFrozenDuration) {
		this.userAccountFrozenDuration = userAccountFrozenDuration;
	}

}
