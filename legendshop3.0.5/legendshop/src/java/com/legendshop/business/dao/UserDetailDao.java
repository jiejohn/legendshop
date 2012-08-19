/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao;

import javax.mail.MessagingException;

import org.apache.oro.text.regex.MalformedPatternException;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SqlQuery;
import com.legendshop.model.entity.ShopDetail;
import com.legendshop.model.entity.User;
import com.legendshop.model.entity.UserDetail;
import com.legendshop.spi.constants.RegisterEnum;

/**
 * The Interface UserDetailDao.
 */
public interface UserDetailDao extends BaseDao{

	// 普通用户注册
	/**
	 * Save user.
	 * 
	 * @param user
	 *            the user
	 * @param userDetail
	 *            the user detail
	 * @param shopDetail
	 *            the shop detail
	 * @param isOpenShop
	 *            the is open shop
	 * @return the string
	 */
	public abstract String saveUser(User user, UserDetail userDetail, ShopDetail shopDetail, boolean isOpenShop);

	/**
	 * Save uer detail.
	 * 
	 * @param userDetail
	 *            the user detail
	 * @param shopDetail
	 *            the shop detail
	 * @param isOpenShop
	 *            the is open shop
	 */
	public abstract void saveUerDetail(UserDetail userDetail, ShopDetail shopDetail, boolean isOpenShop);

	// 返回商城开通状态
	/**
	 * Save shop detail and role.
	 * 
	 * @param userDetail
	 *            the user detail
	 * @param shopDetail
	 *            the shop detail
	 * @return the integer
	 */
	public abstract Integer saveShopDetailAndRole(UserDetail userDetail, ShopDetail shopDetail);

	/**
	 * Update shop detail.
	 * 
	 * @param userDetail
	 *            the user detail
	 * @param shopDetail
	 *            the shop detail
	 * @param isAdmin
	 *            the is admin
	 */
	public abstract void updateShopDetail(UserDetail userDetail, ShopDetail shopDetail, boolean isAdmin);

	// UserId 一定不能为空，返回当前商城开通状态
	/**
	 * Save shop detail.
	 * 
	 * @param userDetail
	 *            the user detail
	 * @param shopDetail
	 *            the shop detail
	 * @return the integer
	 */
	public abstract Integer saveShopDetail(UserDetail userDetail, ShopDetail shopDetail);

	// 普通用户信息修改
	/**
	 * Update user.
	 * 
	 * @param userDetail
	 *            the user detail
	 */
	public abstract void updateUser(UserDetail userDetail);

	// 修改用户密码
	/**
	 * Change password.
	 * 
	 * @param userDetail
	 *            the user detail
	 */
	public abstract void updatePassword(UserDetail userDetail);

	/**
	 * 客户是否存在 true: 客户已经存在 false: 客户不存在.
	 * 
	 * @param userName
	 *            the user name
	 * @return true, if is user exist
	 */
	public abstract boolean isUserExist(String userName);

	/**
	 * Email是否存在 true: Email已经存在 false: Email不存在.
	 * 
	 * @param email
	 *            the email
	 * @return true, if is email exist
	 */
	public abstract boolean isEmailExist(String email);

	// 店名是否存在
	/**
	 * Checks if is shop exist.
	 * 
	 * @param shopName
	 *            the shop name
	 * @return true, if is shop exist
	 */
	public abstract boolean isShopExist(String shopName);

	/**
	 * Find user.
	 * 
	 * @param userId
	 *            the user id
	 * @return the user
	 */
	public abstract User getUser(String userId);

	/**
	 * Find user by name.
	 * 
	 * @param userName
	 *            the user name
	 * @return the user
	 */
	public abstract User getUserByName(String userName);

	/**
	 * Find user detail by name.
	 * 
	 * @param userName
	 *            the user name
	 * @return the user detail
	 */
	public abstract UserDetail getUserDetailByName(String userName);

	/**
	 * User reg success.
	 * 
	 * @param userName
	 *            the user name
	 * @param registerCode
	 *            the register code
	 * @return the register enum
	 */
	public abstract RegisterEnum getUserRegStatus(String userName, String registerCode);

	/**
	 * Find user detail.
	 * 
	 * @param userName
	 *            the user name
	 * @return the user detail
	 */
	public abstract UserDetail getUserDetail(String userName);

	/**
	 * Find user score.
	 * 
	 * @param userName
	 *            the user name
	 * @return the long
	 */
	public abstract Long getUserScore(String userName);

	/**
	 * Find user detail list.
	 * 
	 * @param sqlQuery
	 *            the hql query
	 * @return the page support
	 */
	public abstract PageSupport getUserDetailList(SqlQuery sqlQuery);

	/**
	 * Gets the user detail list.
	 * 
	 * @param sqlQuery
	 *            the sql query
	 * @return the user detail list
	 */
	public abstract PageSupport getUserDetailList(HqlQuery sqlQuery);
	
	/**
	 * Delete user detail.
	 * 
	 * @param userId
	 *            the user id
	 * @param userName
	 *            the user name
	 * @param realPicPath
	 *            the real pic path
	 * @param smallPicPath
	 *            the small pic path
	 * @return the string
	 */
	public abstract String deleteUserDetail(String userId, String userName, String realPicPath, String smallPicPath);

	/**
	 * Update password.
	 * 
	 * @param userName
	 *            the user name
	 * @param mail
	 *            the mail
	 * @param templateFilePath
	 *            the template file path
	 * @return true, if successful
	 * @throws MalformedPatternException
	 *             the malformed pattern exception
	 * @throws MessagingException
	 *             the messaging exception
	 */
	public abstract boolean updatePassword(String userName, String mail, String templateFilePath) throws MalformedPatternException, MessagingException;

	/**
	 * Gets the all shop count.
	 * 
	 * @return the all shop count
	 */
	public abstract Long getAllUserCount();


}