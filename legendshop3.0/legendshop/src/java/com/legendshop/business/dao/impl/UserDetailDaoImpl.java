/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.legendshop.business.common.RegisterEnum;
import com.legendshop.business.common.ShopStatusEnum;
import com.legendshop.business.dao.UserDetailDao;
import com.legendshop.business.service.CommonUtil;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SqlQuery;
import com.legendshop.core.helper.FileProcessor;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.ShopDetail;
import com.legendshop.model.entity.User;
import com.legendshop.model.entity.UserDetail;
import com.legendshop.util.AppUtils;
import com.legendshop.util.MD5Util;
import com.legendshop.util.ip.IPSeeker;

/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * 官方网站：http://www.legendesign.net
 */
public class UserDetailDaoImpl extends BaseDaoImpl implements UserDetailDao {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(UserDetailDaoImpl.class);
	
	/** The common util. */
	private CommonUtil commonUtil;
	
	/** The REGISTE d_ tag. */
	private final String REGISTED_TAG = "REGISTED";

	// 普通用户注册
	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.UserDetailDao#saveUser(com.legendshop.model.entity.User, com.legendshop.model.entity.UserDetail, com.legendshop.model.entity.ShopDetail, boolean)
	 */
	@Override
	public String saveUser(User user, UserDetail userDetail, ShopDetail shopDetail, boolean isOpenShop) {
		// 1/保存
		Boolean validationFromMail = PropertiesUtil.getObject(ParameterEnum.VALIDATION_FROM_MAIL, Boolean.class);
		if (validationFromMail) {
			user.setEnabled("0");// 不可用
			userDetail.setRegisterCode(MD5Util.Md5Password(user.getName(), user.getPassword())); // 将RegisterCode发送到邮件，点击开通用户
		}
		String userId = (String) save(user);
		// 2/保存用户详细信息
		userDetail.setUserId(userId);
		userDetail.setUserName(user.getName());
		saveUerDetail(userDetail, shopDetail, isOpenShop);
		return userId;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.UserDetailDao#saveUerDetail(com.legendshop.model.entity.UserDetail, com.legendshop.model.entity.ShopDetail, boolean)
	 */
	@Override
	public void saveUerDetail(UserDetail userDetail, ShopDetail shopDetail, boolean isOpenShop) {
		if (isOpenShop) {
			saveShopDetailAndRole(userDetail, shopDetail);
		} else {
			// 保存用户角色
			commonUtil.saveUserRight(this, userDetail.getUserId());
		}
		save(userDetail);
	}

	// 返回商城开通状态
	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.UserDetailDao#saveShopDetailAndRole(com.legendshop.model.entity.UserDetail, com.legendshop.model.entity.ShopDetail)
	 */
	@Override
	public Integer saveShopDetailAndRole(UserDetail userDetail, ShopDetail shopDetail) {
		// 保存管理员角色
		commonUtil.saveAdminRight(this, userDetail.getUserId());
		// save shopDetail
		return saveShopDetail(userDetail, shopDetail);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.UserDetailDao#updateShopDetail(com.legendshop.model.entity.UserDetail, com.legendshop.model.entity.ShopDetail, boolean)
	 */
	@Override
	public void updateShopDetail(UserDetail userDetail, ShopDetail shopDetail, boolean isAdmin) {
		if (isAdmin) {
			// 保存管理员角色
			commonUtil.saveAdminRight(this, userDetail.getUserId());

			saveShopDetail(userDetail, shopDetail);
		}
	}

	// UserId 一定不能为空，返回当前商城开通状态
	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.UserDetailDao#saveShopDetail(com.legendshop.model.entity.UserDetail, com.legendshop.model.entity.ShopDetail)
	 */
	@Override
	public Integer saveShopDetail(UserDetail userDetail, ShopDetail shopDetail) {
		Date date = new Date();
		shopDetail.setStoreName(userDetail.getUserName());
		shopDetail.setMaddr(userDetail.getUserAdds());
		shopDetail.setModifyTime(date);
		shopDetail.setUserId(userDetail.getUserId());
		shopDetail.setAddtime(date);
		shopDetail.setVisitTimes(0);
		shopDetail.setOffProductNum(0);
		shopDetail.setProductNum(0);
		shopDetail.setCommNum(0);
		shopDetail.setWeb(shopDetail.getStoreName());
		shopDetail.setColorStyle("oneday");
		if (PropertiesUtil.getObject(ParameterEnum.VALIDATION_ON_OPEN_SHOP, Boolean.class)) {
			shopDetail.setStatus(ShopStatusEnum.AUDITING.value());
		} else {
			shopDetail.setStatus(ShopStatusEnum.ONLINE.value());
		}

		// 得到创建时的国家和地区
		if (shopDetail.getIp() != null) {
			shopDetail.setCreateAreaCode(IPSeeker.getInstance().getArea(shopDetail.getIp()));
			shopDetail.setCreateCountryCode(IPSeeker.getInstance().getCountry(shopDetail.getIp()));
		}
		String userName = userDetail.getUserName();
		String subPath = userName + "/shop/";
		String filename = null;
		// 取得上传的文件
		MultipartFile idCardPicFile = shopDetail.getIdCardPicFile();
		MultipartFile trafficPicFile = shopDetail.getTrafficPicFile();

		if ((idCardPicFile != null) && (idCardPicFile.getSize() > 0)) {
			filename = FileProcessor.uploadFileAndCallback(idCardPicFile, subPath,"sd" + userName);
			log.info("{} 上传身份证照片文件 {} ", userDetail.getUserName(), filename);
			shopDetail.setIdCardPic(subPath + filename);
	
		}

		if ((trafficPicFile != null) && (trafficPicFile.getSize() > 0)) {
			filename = FileProcessor.uploadFileAndCallback(trafficPicFile, subPath,"sd" + userName);
			log.info("{} 营业执照照片文件 {} ", userDetail.getUserName(), filename);
			shopDetail.setTrafficPic(subPath + filename);
		}

		this.save(shopDetail);
		return shopDetail.getStatus();
	}

	// 普通用户信息修改
	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.UserDetailDao#updateUser(com.legendshop.model.entity.UserDetail)
	 */
	@Override
	public void updateUser(UserDetail userDetail) {
		this.update(userDetail);
		updatePassword(userDetail);
	}

	// 修改用户密码
	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.UserDetailDao#changePassword(com.legendshop.model.entity.UserDetail)
	 */
	@Override
	public void updatePassword(UserDetail userDetail) {
		if (!AppUtils.isBlank(userDetail.getPassword())) {
			User user = this.get(User.class, userDetail.getUserId());
			user.setPassword(MD5Util.Md5Password(userDetail.getUserName(), userDetail.getPassword()));
			this.update(user);
		}
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.UserDetailDao#isUserExist(java.lang.String)
	 */
	@Override
	public boolean isUserExist(String userName) {
		List list = this.findByHQL("from User where name = ?", userName);
		return AppUtils.isNotBlank(list);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.UserDetailDao#isEmailExist(java.lang.String)
	 */
	@Override
	public boolean isEmailExist(String email) {
		List list = this.findByHQL("from UserDetail where userMail = ?", email);
		return AppUtils.isNotBlank(list);
	}

	// 店名是否存在
	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.UserDetailDao#isShopExist(java.lang.String)
	 */
	@Override
	public boolean isShopExist(String shopName) {
		List list = this.findByHQL("from ShopDetail where storeName = ?", new Object[] { shopName });
		if (AppUtils.isBlank(list)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.UserDetailDao#findUser(java.lang.String)
	 */
	@Override
	public User getUser(String userId) {
		return this.get(User.class, userId);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.UserDetailDao#findUserByName(java.lang.String)
	 */
	@Override
	public User getUserByName(String userName) {
		return findUniqueBy("from User where name = ?", User.class, userName);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.UserDetailDao#findUserDetailByName(java.lang.String)
	 */
	@Override
	public UserDetail getUserDetailByName(String userName) {
		return findUniqueBy("from UserDetail where userName = ?", UserDetail.class, userName);
	}

	/**
	 * Sets the common util.
	 * 
	 * @param commonUtil
	 *            the new common util
	 */
	public void setCommonUtil(CommonUtil commonUtilImpl) {
		this.commonUtil = commonUtilImpl;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.UserDetailDao#userRegSuccess(java.lang.String, java.lang.String)
	 */
	@Override
	public RegisterEnum getUserRegStatus(String userName, String registerCode) {
		UserDetail userDetail = getUserDetailByName(userName);
		if (userDetail == null) {
			return RegisterEnum.REGISTER_NO_USER_FOUND;
		}
		if (registerCode != null && registerCode.equals(userDetail.getRegisterCode())) {
			// update user
			User user = getUser(userDetail.getUserId());
			user.setEnabled("1");
			update(user);
			// update userDetail
			userDetail.setRegisterCode(REGISTED_TAG);
			update(userDetail);
			return RegisterEnum.REGISTER_SUCCESS;
		}
		if (REGISTED_TAG.equals(userDetail.getRegisterCode())) {
			return RegisterEnum.REGISTER_SUCCESS;
		}
		return RegisterEnum.REGISTER_FAIL;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.UserDetailDao#findUserDetail(java.lang.String)
	 */
	@Override
	public UserDetail getUserDetail(String userName) {
		UserDetail userDetail = findUniqueBy("from UserDetail where userName= ?", UserDetail.class, userName);
		if (userDetail != null) {
			String qq = userDetail.getQq();
			if (AppUtils.isNotBlank(qq)) {
				String[] qqs = qq.split(",");
				List<String> list = new ArrayList<String>();
				for (int i = 0; i < qqs.length; i++) {
					if (AppUtils.isNotBlank(qqs[i])) {
						list.add(qqs[i]);
					}
				}
				userDetail.setQqList(list);
			}
			ShopDetail shopDetail = findUniqueBy("from ShopDetail where userId = ?", ShopDetail.class, userDetail
					.getUserId());
			userDetail.setShopDetail(shopDetail);
		}

		return userDetail;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.UserDetailDao#findUserScore(java.lang.String)
	 */
	@Override
	public Long getUserScore(String userName) {
		UserDetail userDetail = findUniqueBy("from UserDetail where userName= ?", UserDetail.class, userName);
		if (userDetail != null && userDetail.getScore() != null) {
				 return userDetail.getScore();
		}
		return 0l;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.UserDetailDao#findUserDetailList(com.legendshop.core.dao.support.HqlQuery)
	 */
	@Override
	public PageSupport getUserDetailList(HqlQuery hqlQuery) {
		return find(hqlQuery);
	}

	@Override
	public PageSupport getUserDetailList(SqlQuery sqlQuery) {
		return find(sqlQuery);
	}

}
