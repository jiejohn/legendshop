package com.legendshop.cas.dao;

import java.util.Date;

import com.legendshop.cas.model.User;

/**
 * To define a user entity operation in customized CAS server.
 * 
 * @author George Guo
 * 
 */
public interface UserDao {

	/**
	 * query the user incorrect password input count.
	 * 
	 * @param userAccount
	 * @return
	 */
	int getPasswordIncorrectCount(String userAccount);

	/**
	 * update user incorrect password count
	 * 
	 * @param userAccount
	 * @param count
	 *            the incorrect password retried times
	 * @param frozenExpireTime
	 *            if its value is null, this field will be ignored.
	 */
	void updatIncorrectPasswordCount(String userAccount, int count, Date frozenExpireTime);

	/**
	 * query user detail info, including password and user effective/expire
	 * time.
	 * 
	 * @param account
	 *            The login user name.
	 * @return
	 */
	User getUserDetail(String account);

}
