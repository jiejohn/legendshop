/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.form;

import java.util.Date;

import com.legendshop.model.entity.ShopDetail;


/**
 * 
 *  for UserDetail
 */
public class UserForm{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4363234793127853507L;

	/** The user id. */
	private String userId;
	
	/** The name. */
	private String name;

	/** The password. */
	private String password;
	
	/** The password old. */
	private String passwordOld;
	//默认注册用户是可以使用，0为不可使用
	/** The enabled. */
	private String enabled = "1";

	/** The note. */
	private String note;

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
	
	/** The sex. */
	private String sex;
	
	/** The birth date. */
	private String birthDate;
	
	/** The user mobile. */
	private String userMobile;
	//生日的年月日
	/** The user birth year. */
	private String userBirthYear;
	
	/** The user birth month. */
	private String userBirthMonth;
	
	/** The user birth day. */
	private String userBirthDay;
	
	/** The shop detail. */
	private ShopDetail shopDetail = new ShopDetail();
	

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
	 * Gets the fax.
	 * 
	 * @return the fax
	 */
	public String getFax() {
		return fax;
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
	 * Gets the grade id.
	 * 
	 * @return the grade id
	 */
	public Integer getGradeId() {
		return gradeId;
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
	 * Gets the modify time.
	 * 
	 * @return the modify time
	 */
	public Date getModifyTime() {
		return modifyTime;
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
	 * Gets the msn.
	 * 
	 * @return the msn
	 */
	public String getMsn() {
		return msn;
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
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
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
	 * Gets the note.
	 * 
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * Sets the note.
	 * 
	 * @param note
	 *            the new note
	 */
	public void setNote(String note) {
		this.note = note;
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
	 * Gets the qq.
	 * 
	 * @return the qq
	 */
	public String getQq() {
		return qq;
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
	 * Gets the user adds.
	 * 
	 * @return the user adds
	 */
	public String getUserAdds() {
		return userAdds;
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
	 * Gets the user id.
	 * 
	 * @return the user id
	 */
	public String getUserId() {
		return userId;
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
	 * Gets the user lastip.
	 * 
	 * @return the user lastip
	 */
	public String getUserLastip() {
		return userLastip;
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
	 * Gets the user lasttime.
	 * 
	 * @return the user lasttime
	 */
	public Date getUserLasttime() {
		return userLasttime;
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
	 * Gets the user mail.
	 * 
	 * @return the user mail
	 */
	public String getUserMail() {
		return userMail;
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
	 * Gets the user memo.
	 * 
	 * @return the user memo
	 */
	public String getUserMemo() {
		return userMemo;
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
	 * Gets the user name.
	 * 
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
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
	 * Gets the user postcode.
	 * 
	 * @return the user postcode
	 */
	public String getUserPostcode() {
		return userPostcode;
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
	 * Gets the user regip.
	 * 
	 * @return the user regip
	 */
	public String getUserRegip() {
		return userRegip;
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
	 * Gets the user regtime.
	 * 
	 * @return the user regtime
	 */
	public Date getUserRegtime() {
		return userRegtime;
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
	 * Gets the user tel.
	 * 
	 * @return the user tel
	 */
	public String getUserTel() {
		return userTel;
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
	 * Gets the password old.
	 * 
	 * @return the password old
	 */
	public String getPasswordOld() {
		return passwordOld;
	}

	/**
	 * Sets the password old.
	 * 
	 * @param passwordOld
	 *            the new password old
	 */
	public void setPasswordOld(String passwordOld) {
		this.passwordOld = passwordOld;
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
	 * Gets the user birth year.
	 * 
	 * @return the user birth year
	 */
	public String getUserBirthYear() {
		return userBirthYear;
	}

	/**
	 * Sets the user birth year.
	 * 
	 * @param userBirthYear
	 *            the new user birth year
	 */
	public void setUserBirthYear(String userBirthYear) {
		this.userBirthYear = userBirthYear;
	}

	/**
	 * Gets the user birth month.
	 * 
	 * @return the user birth month
	 */
	public String getUserBirthMonth() {
		return userBirthMonth;
	}

	/**
	 * Sets the user birth month.
	 * 
	 * @param userBirthMonth
	 *            the new user birth month
	 */
	public void setUserBirthMonth(String userBirthMonth) {
		this.userBirthMonth = userBirthMonth;
	}

	/**
	 * Gets the user birth day.
	 * 
	 * @return the user birth day
	 */
	public String getUserBirthDay() {
		return userBirthDay;
	}

	/**
	 * Sets the user birth day.
	 * 
	 * @param userBirthDay
	 *            the new user birth day
	 */
	public void setUserBirthDay(String userBirthDay) {
		this.userBirthDay = userBirthDay;
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


}