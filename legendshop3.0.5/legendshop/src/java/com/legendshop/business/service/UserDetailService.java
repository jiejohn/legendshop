/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oro.text.regex.MalformedPatternException;

import com.legendshop.business.form.UserForm;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SqlQuery;
import com.legendshop.model.entity.ShopDetail;
import com.legendshop.model.entity.UserDetail;
import com.legendshop.spi.service.BaseService;

/**
 * The Interface UserDetailService.
 */
public interface UserDetailService extends BaseService{


	/**
	 * Query score.
	 * 
	 * @param userName
	 *            the user name
	 * @return the long
	 */
	public abstract Long getScore(String userName);

	/**
	 * Gets the user detail list.
	 * 
	 * @param hqlQuery
	 *            the hql query
	 * @return the user detail list
	 */
	public abstract PageSupport getUserDetailList(HqlQuery hqlQuery);
	
	
	/**
	 * Gets the user detail list.
	 * 
	 * @param sqlQuery
	 *            the sql query
	 * @return the user detail list
	 */
	public abstract PageSupport getUserDetailList(SqlQuery sqlQuery);

	/**
	 * Find user detail.
	 * 
	 * @param userName
	 *            the user name
	 * @return the user detail
	 */
	public abstract UserDetail getUserDetail(String userName);
	
	
	/**
	 * 删除用户,只能删除普通用户 因为没有采用外键，所以要一个一个删除 注意删除无法恢复，慎用.
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
	 * 重置密码.
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

	public abstract boolean updatePassword(String userName, String mail, String templateFilePath)
			throws MalformedPatternException, MessagingException;

	/**
	 * 修改我的帐户，用于修改用户信息.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param actionForm
	 *            the action form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the action forward
	 * @throws Exception
	 *             the exception
	 */
	public abstract String updateAccount(HttpServletRequest request, HttpServletResponse response, UserForm form);
	
	/**
	 * User reg.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param form
	 *            the form
	 * @return the string
	 */
	public abstract String saveUserReg(HttpServletRequest request, HttpServletResponse response, UserForm form);
	
	/**
	 * Adds the shop.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param shopDetail
	 *            the shop detail
	 * @return the string
	 */
	public abstract String saveShop(HttpServletRequest request, HttpServletResponse response, ShopDetail shopDetail);
	
	
	public abstract String updateUserReg(HttpServletRequest request, HttpServletResponse response, String userName,
			String registerCode) ;


}