/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.model.entity;

import java.util.Date;


/**
 * The Class UserDetailView.
 */
public class UserDetailView implements java.io.Serializable {

	// Fields

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3648830158220060652L;

	/** The user id. */
	private String userId;

	/** The grade id. */
	private Integer gradeId;

	/** The user name. */
	private String userName;

	/** The nick name. */
	private String nickName;

	/** The user mail. */
	private String userMail;

	/** The user adds. */
	private String userAdds;

	/** The user tel. */
	private String userTel;

	/** The user postcode. */
	private String userPostcode;

	/** The msn. */
	private String msn;

	/** The qq. */
	private String qq;

	/** The fax. */
	private String fax;

	/** The modify time. */
	private Date modifyTime;

	/** The user regtime. */
	private Date userRegtime;

	/** The user regip. */
	private String userRegip;

	/** The user lasttime. */
	private Date userLasttime;

	/** The user lastip. */
	private String userLastip;

	/** The user memo. */
	private String userMemo;

	/** The password. */
	private String password;

	/** The sex. */
	private String sex;

	/** The birth date. */
	private String birthDate;

	/** The user mobile. */
	private String userMobile;

	/** The shop id. */
	private Long shopId;

	/** The shop detail. */
	private ShopDetail shopDetail;

	/** The register code. */
	private String registerCode;

	// 从用户信息表中来
	/** The enabled. */
	private String enabled;

	/** The score. */
	private Long score;

	/**
	 * default constructor.
	 */
	public UserDetailView() {
	}

	// for 用户列表（用户名 用户昵称 邮件 注册IP 修改时间 注册时间,状态 ）
	/**
	 * Instantiates a new user detail view.
	 * 
	 * @param userId
	 *            the user id
	 * @param userName
	 *            the user name
	 * @param nickName
	 *            the nick name
	 * @param userMail
	 *            the user mail
	 * @param userRegip
	 *            the user regip
	 * @param modifyTime
	 *            the modify time
	 * @param userRegtime
	 *            the user regtime
	 * @param enabled
	 *            the enabled
	 * @param shopId
	 *            the shop id
	 */
	public UserDetailView(String userId, String userName, String nickName, String userMail, String userRegip,
			Date modifyTime, Date userRegtime, String enabled, Long shopId) {
		this.userId = userId;
		this.userName = userName;
		this.nickName = nickName;
		this.userMail = userMail;
		this.userRegip = userRegip;
		this.modifyTime = modifyTime;
		this.userRegtime = userRegtime;
		this.enabled = enabled;
		this.shopId = shopId;
	}

	/**
	 * Gets the user id.
	 * 
	 * @return the user id
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * Sets the user id.
	 * 
	 * @param userId
	 *            the new user id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Gets the grade id.
	 * 
	 * @return the grade id
	 */
	public Integer getGradeId() {
		return this.gradeId;
	}

	/**
	 * Sets the grade id.
	 * 
	 * @param gradeId
	 *            the new grade id
	 */
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	/**
	 * Gets the user name.
	 * 
	 * @return the user name
	 */
	public String getUserName() {
		return this.userName;
	}

	/**
	 * Sets the user name.
	 * 
	 * @param userName
	 *            the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets the user mail.
	 * 
	 * @return the user mail
	 */
	public String getUserMail() {
		return this.userMail;
	}

	/**
	 * Sets the user mail.
	 * 
	 * @param userMail
	 *            the new user mail
	 */
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	/**
	 * Gets the user adds.
	 * 
	 * @return the user adds
	 */
	public String getUserAdds() {
		return this.userAdds;
	}

	/**
	 * Sets the user adds.
	 * 
	 * @param userAdds
	 *            the new user adds
	 */
	public void setUserAdds(String userAdds) {
		this.userAdds = userAdds;
	}

	/**
	 * Gets the user tel.
	 * 
	 * @return the user tel
	 */
	public String getUserTel() {
		return this.userTel;
	}

	/**
	 * Sets the user tel.
	 * 
	 * @param userTel
	 *            the new user tel
	 */
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	/**
	 * Gets the user postcode.
	 * 
	 * @return the user postcode
	 */
	public String getUserPostcode() {
		return this.userPostcode;
	}

	/**
	 * Sets the user postcode.
	 * 
	 * @param userPostcode
	 *            the new user postcode
	 */
	public void setUserPostcode(String userPostcode) {
		this.userPostcode = userPostcode;
	}

	/**
	 * Gets the msn.
	 * 
	 * @return the msn
	 */
	public String getMsn() {
		return this.msn;
	}

	/**
	 * Sets the msn.
	 * 
	 * @param msn
	 *            the new msn
	 */
	public void setMsn(String msn) {
		this.msn = msn;
	}

	/**
	 * Gets the qq.
	 * 
	 * @return the qq
	 */
	public String getQq() {
		return this.qq;
	}

	/**
	 * Sets the qq.
	 * 
	 * @param qq
	 *            the new qq
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}

	/**
	 * Gets the fax.
	 * 
	 * @return the fax
	 */
	public String getFax() {
		return this.fax;
	}

	/**
	 * Sets the fax.
	 * 
	 * @param fax
	 *            the new fax
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * Gets the modify time.
	 * 
	 * @return the modify time
	 */
	public Date getModifyTime() {
		return this.modifyTime;
	}

	/**
	 * Sets the modify time.
	 * 
	 * @param modifyTime
	 *            the new modify time
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	/**
	 * Gets the user regtime.
	 * 
	 * @return the user regtime
	 */
	public Date getUserRegtime() {
		return this.userRegtime;
	}

	/**
	 * Sets the user regtime.
	 * 
	 * @param userRegtime
	 *            the new user regtime
	 */
	public void setUserRegtime(Date userRegtime) {
		this.userRegtime = userRegtime;
	}

	/**
	 * Gets the user regip.
	 * 
	 * @return the user regip
	 */
	public String getUserRegip() {
		return this.userRegip;
	}

	/**
	 * Sets the user regip.
	 * 
	 * @param userRegip
	 *            the new user regip
	 */
	public void setUserRegip(String userRegip) {
		this.userRegip = userRegip;
	}

	/**
	 * Gets the user lasttime.
	 * 
	 * @return the user lasttime
	 */
	public Date getUserLasttime() {
		return this.userLasttime;
	}

	/**
	 * Sets the user lasttime.
	 * 
	 * @param userLasttime
	 *            the new user lasttime
	 */
	public void setUserLasttime(Date userLasttime) {
		this.userLasttime = userLasttime;
	}

	/**
	 * Gets the user lastip.
	 * 
	 * @return the user lastip
	 */
	public String getUserLastip() {
		return this.userLastip;
	}

	/**
	 * Sets the user lastip.
	 * 
	 * @param userLastip
	 *            the new user lastip
	 */
	public void setUserLastip(String userLastip) {
		this.userLastip = userLastip;
	}

	/**
	 * Gets the user memo.
	 * 
	 * @return the user memo
	 */
	public String getUserMemo() {
		return this.userMemo;
	}

	/**
	 * Sets the user memo.
	 * 
	 * @param userMemo
	 *            the new user memo
	 */
	public void setUserMemo(String userMemo) {
		this.userMemo = userMemo;
	}

	/**
	 * Gets the shop detail.
	 * 
	 * @return the shop detail
	 */
	public ShopDetail getShopDetail() {
		return shopDetail;
	}

	/**
	 * Sets the shop detail.
	 * 
	 * @param shopDetail
	 *            the new shop detail
	 */
	public void setShopDetail(ShopDetail shopDetail) {
		this.shopDetail = shopDetail;
	}

	/**
	 * Gets the nick name.
	 * 
	 * @return the nick name
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * Sets the nick name.
	 * 
	 * @param nickName
	 *            the new nick name
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * Gets the password.
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 * 
	 * @param password
	 *            the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the sex.
	 * 
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * Sets the sex.
	 * 
	 * @param sex
	 *            the new sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * Gets the birth date.
	 * 
	 * @return the birth date
	 */
	public String getBirthDate() {
		return birthDate;
	}

	/**
	 * Sets the birth date.
	 * 
	 * @param birthDate
	 *            the new birth date
	 */
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * Gets the user mobile.
	 * 
	 * @return the user mobile
	 */
	public String getUserMobile() {
		return userMobile;
	}

	/**
	 * Sets the user mobile.
	 * 
	 * @param userMobile
	 *            the new user mobile
	 */
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	/**
	 * Gets the shop id.
	 * 
	 * @return the shop id
	 */
	public Long getShopId() {
		return shopId;
	}

	/**
	 * Sets the shop id.
	 * 
	 * @param shopId
	 *            the new shop id
	 */
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	/**
	 * Gets the register code.
	 * 
	 * @return the register code
	 */
	public String getRegisterCode() {
		return registerCode;
	}

	/**
	 * Sets the register code.
	 * 
	 * @param registerCode
	 *            the new register code
	 */
	public void setRegisterCode(String registerCode) {
		this.registerCode = registerCode;
	}

	/**
	 * Gets the enabled.
	 * 
	 * @return the enabled
	 */
	public String getEnabled() {
		return enabled;
	}

	/**
	 * Sets the enabled.
	 * 
	 * @param enabled
	 *            the new enabled
	 */
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	/**
	 * Gets the score.
	 * 
	 * @return the score
	 */
	public Long getScore() {
		return score;
	}

	/**
	 * Sets the score.
	 * 
	 * @param score
	 *            the new score
	 */
	public void setScore(Long score) {
		this.score = score;
	}

}