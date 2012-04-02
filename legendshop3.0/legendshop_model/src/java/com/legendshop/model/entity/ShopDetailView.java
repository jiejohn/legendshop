/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public class ShopDetailView implements Serializable, Cloneable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8763595849099410286L;

	/** The shop id. */
	private Long shopId;

	/** The user id. */
	private String userId;

	/** The web. */
	private String web;

	/** The sitename. */
	private String sitename;

	/** The maddr. */
	private String maddr;

	/** The msn. */
	private String msn;

	/** The mname. */
	private String mname;

	/** The code. */
	private String code;

	/** The ymaddr. */
	private String ymaddr;

	/** The ymname. */
	private String ymname;

	/** The status. */
	private Integer status;

	/** The store name. */
	private String storeName;

	/** The visit times. */
	private Integer visitTimes;

	// 今天的访问次数
	/** The visit times today. */
	private Integer visitTimesToday;
	// 产品数量
	/** The product num. */
	private Integer productNum;
	// 评论数量
	/** The comm num. */
	private Integer commNum;
	// 下线产品数量
	/** The off product num. */
	private Integer offProductNum;

	/** The lang style. */
	private String langStyle;

	/** The modify time. */
	private Date modifyTime;

	/** The addtime. */
	private Date addtime;

	/** The brief desc. */
	private String briefDesc;

	/** The detail desc. */
	private String detailDesc;

	/** The shop pic. */
	private String shopPic;

	/** The color style. */
	private String colorStyle;

	// 从UserDetail拷贝过来的属性
	/** The user mail. */
	private String userMail;

	/** The user adds. */
	private String userAdds;

	/** The user tel. */
	private String userTel;

	/** The user postcode. */
	private String userPostcode;

	/** The grade id. */
	private Integer gradeId;

	/** The type. */
	private Integer type;

	/** The id card pic. */
	private String idCardPic;

	/** The traffic pic. */
	private String trafficPic;

	/** The id card pic file. */
	protected MultipartFile idCardPicFile;

	/** The traffic pic file. */
	protected MultipartFile trafficPicFile;

	/** The id card num. */
	private String idCardNum;

	/** The real path. */
	private String realPath;

	/** The create area code. */
	private String createAreaCode;

	/** The create country code. */
	private String createCountryCode;

	/** The ip. */
	private String ip;

	/** The provinceid. */
	private Integer provinceid;

	/** The cityid. */
	private Integer cityid;

	/** The areaid. */
	private Integer areaid;

	/** The province. */
	private String province;
	
	/** The city. */
	private String city;
	
	/** The area. */
	private String area;

	// //// user detail
	/** The nick name. */
	private String nickName;

	/** The user mobile. */
	private String userMobile;

	/** The msn number. */
	private String msnNumber;

	/** The qq. */
	private String qq;

	/** The fax. */
	private String fax;

	/** The qq list. */
	private List<String> qqList = new ArrayList<String>(); // copy from

	/** The front type. */
    private String frontType;
	
	/** The end type. */
	private String endType;
	
	
	// userDetail qq
	
	/**
	 * Gets the province.
	 * 
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * Sets the province.
	 * 
	 * @param province
	 *            the new province
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * Gets the city.
	 * 
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 * 
	 * @param city
	 *            the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the area.
	 * 
	 * @return the area
	 */
	public String getArea() {
		return area;
	}

	/**
	 * Sets the area.
	 * 
	 * @param area
	 *            the new area
	 */
	public void setArea(String area) {
		this.area = area;
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
	 * Gets the web.
	 * 
	 * @return the web
	 */
	public String getWeb() {
		return web;
	}

	/**
	 * Sets the web.
	 * 
	 * @param web
	 *            the new web
	 */
	public void setWeb(String web) {
		this.web = web;
	}

	/**
	 * Gets the sitename.
	 * 
	 * @return the sitename
	 */
	public String getSitename() {
		return sitename;
	}

	/**
	 * Sets the sitename.
	 * 
	 * @param sitename
	 *            the new sitename
	 */
	public void setSitename(String sitename) {
		this.sitename = sitename;
	}

	/**
	 * Gets the maddr.
	 * 
	 * @return the maddr
	 */
	public String getMaddr() {
		return maddr;
	}

	/**
	 * Sets the maddr.
	 * 
	 * @param maddr
	 *            the new maddr
	 */
	public void setMaddr(String maddr) {
		this.maddr = maddr;
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
	 * Gets the mname.
	 * 
	 * @return the mname
	 */
	public String getMname() {
		return mname;
	}

	/**
	 * Sets the mname.
	 * 
	 * @param mname
	 *            the new mname
	 */
	public void setMname(String mname) {
		this.mname = mname;
	}

	/**
	 * Gets the code.
	 * 
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 * 
	 * @param code
	 *            the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Gets the ymaddr.
	 * 
	 * @return the ymaddr
	 */
	public String getYmaddr() {
		return ymaddr;
	}

	/**
	 * Sets the ymaddr.
	 * 
	 * @param ymaddr
	 *            the new ymaddr
	 */
	public void setYmaddr(String ymaddr) {
		this.ymaddr = ymaddr;
	}

	/**
	 * Gets the ymname.
	 * 
	 * @return the ymname
	 */
	public String getYmname() {
		return ymname;
	}

	/**
	 * Sets the ymname.
	 * 
	 * @param ymname
	 *            the new ymname
	 */
	public void setYmname(String ymname) {
		this.ymname = ymname;
	}

	/**
	 * Gets the status.
	 * 
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 * 
	 * @param status
	 *            the new status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * Gets the store name.
	 * 
	 * @return the store name
	 */
	public String getStoreName() {
		return storeName;
	}

	/**
	 * Sets the store name.
	 * 
	 * @param storeName
	 *            the new store name
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	/**
	 * Gets the visit times.
	 * 
	 * @return the visit times
	 */
	public Integer getVisitTimes() {
		return visitTimes;
	}

	/**
	 * Sets the visit times.
	 * 
	 * @param visitTimes
	 *            the new visit times
	 */
	public void setVisitTimes(Integer visitTimes) {
		this.visitTimes = visitTimes;
	}

	/**
	 * Gets the visit times today.
	 * 
	 * @return the visit times today
	 */
	public Integer getVisitTimesToday() {
		return visitTimesToday;
	}

	/**
	 * Sets the visit times today.
	 * 
	 * @param visitTimesToday
	 *            the new visit times today
	 */
	public void setVisitTimesToday(Integer visitTimesToday) {
		this.visitTimesToday = visitTimesToday;
	}

	/**
	 * Gets the product num.
	 * 
	 * @return the product num
	 */
	public Integer getProductNum() {
		return productNum;
	}

	/**
	 * Sets the product num.
	 * 
	 * @param productNum
	 *            the new product num
	 */
	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}

	/**
	 * Gets the comm num.
	 * 
	 * @return the comm num
	 */
	public Integer getCommNum() {
		return commNum;
	}

	/**
	 * Sets the comm num.
	 * 
	 * @param commNum
	 *            the new comm num
	 */
	public void setCommNum(Integer commNum) {
		this.commNum = commNum;
	}

	/**
	 * Gets the off product num.
	 * 
	 * @return the off product num
	 */
	public Integer getOffProductNum() {
		return offProductNum;
	}

	/**
	 * Sets the off product num.
	 * 
	 * @param offProductNum
	 *            the new off product num
	 */
	public void setOffProductNum(Integer offProductNum) {
		this.offProductNum = offProductNum;
	}

	/**
	 * Gets the lang style.
	 * 
	 * @return the lang style
	 */
	public String getLangStyle() {
		return langStyle;
	}

	/**
	 * Sets the lang style.
	 * 
	 * @param langStyle
	 *            the new lang style
	 */
	public void setLangStyle(String langStyle) {
		this.langStyle = langStyle;
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
	 * Gets the addtime.
	 * 
	 * @return the addtime
	 */
	public Date getAddtime() {
		return addtime;
	}

	/**
	 * Sets the addtime.
	 * 
	 * @param addtime
	 *            the new addtime
	 */
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	/**
	 * Gets the brief desc.
	 * 
	 * @return the brief desc
	 */
	public String getBriefDesc() {
		return briefDesc;
	}

	/**
	 * Sets the brief desc.
	 * 
	 * @param briefDesc
	 *            the new brief desc
	 */
	public void setBriefDesc(String briefDesc) {
		this.briefDesc = briefDesc;
	}

	/**
	 * Gets the detail desc.
	 * 
	 * @return the detail desc
	 */
	public String getDetailDesc() {
		return detailDesc;
	}

	/**
	 * Sets the detail desc.
	 * 
	 * @param detailDesc
	 *            the new detail desc
	 */
	public void setDetailDesc(String detailDesc) {
		this.detailDesc = detailDesc;
	}

	/**
	 * Gets the shop pic.
	 * 
	 * @return the shop pic
	 */
	public String getShopPic() {
		return shopPic;
	}

	/**
	 * Sets the shop pic.
	 * 
	 * @param shopPic
	 *            the new shop pic
	 */
	public void setShopPic(String shopPic) {
		this.shopPic = shopPic;
	}

	/**
	 * Gets the color style.
	 * 
	 * @return the color style
	 */
	public String getColorStyle() {
		return colorStyle;
	}

	/**
	 * Sets the color style.
	 * 
	 * @param colorStyle
	 *            the new color style
	 */
	public void setColorStyle(String colorStyle) {
		this.colorStyle = colorStyle;
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
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * Sets the type.
	 * 
	 * @param type
	 *            the new type
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * Gets the id card pic.
	 * 
	 * @return the id card pic
	 */
	public String getIdCardPic() {
		return idCardPic;
	}

	/**
	 * Sets the id card pic.
	 * 
	 * @param idCardPic
	 *            the new id card pic
	 */
	public void setIdCardPic(String idCardPic) {
		this.idCardPic = idCardPic;
	}

	/**
	 * Gets the traffic pic.
	 * 
	 * @return the traffic pic
	 */
	public String getTrafficPic() {
		return trafficPic;
	}

	/**
	 * Sets the traffic pic.
	 * 
	 * @param trafficPic
	 *            the new traffic pic
	 */
	public void setTrafficPic(String trafficPic) {
		this.trafficPic = trafficPic;
	}

	/**
	 * Gets the id card pic file.
	 * 
	 * @return the id card pic file
	 */
	public MultipartFile getIdCardPicFile() {
		return idCardPicFile;
	}

	/**
	 * Sets the id card pic file.
	 * 
	 * @param idCardPicFile
	 *            the new id card pic file
	 */
	public void setIdCardPicFile(MultipartFile idCardPicFile) {
		this.idCardPicFile = idCardPicFile;
	}

	/**
	 * Gets the traffic pic file.
	 * 
	 * @return the traffic pic file
	 */
	public MultipartFile getTrafficPicFile() {
		return trafficPicFile;
	}

	/**
	 * Sets the traffic pic file.
	 * 
	 * @param trafficPicFile
	 *            the new traffic pic file
	 */
	public void setTrafficPicFile(MultipartFile trafficPicFile) {
		this.trafficPicFile = trafficPicFile;
	}

	/**
	 * Gets the id card num.
	 * 
	 * @return the id card num
	 */
	public String getIdCardNum() {
		return idCardNum;
	}

	/**
	 * Sets the id card num.
	 * 
	 * @param idCardNum
	 *            the new id card num
	 */
	public void setIdCardNum(String idCardNum) {
		this.idCardNum = idCardNum;
	}

	/**
	 * Gets the real path.
	 * 
	 * @return the real path
	 */
	public String getRealPath() {
		return realPath;
	}

	/**
	 * Sets the real path.
	 * 
	 * @param realPath
	 *            the new real path
	 */
	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}

	/**
	 * Gets the creates the area code.
	 * 
	 * @return the creates the area code
	 */
	public String getCreateAreaCode() {
		return createAreaCode;
	}

	/**
	 * Sets the creates the area code.
	 * 
	 * @param createAreaCode
	 *            the new creates the area code
	 */
	public void setCreateAreaCode(String createAreaCode) {
		this.createAreaCode = createAreaCode;
	}

	/**
	 * Gets the creates the country code.
	 * 
	 * @return the creates the country code
	 */
	public String getCreateCountryCode() {
		return createCountryCode;
	}

	/**
	 * Sets the creates the country code.
	 * 
	 * @param createCountryCode
	 *            the new creates the country code
	 */
	public void setCreateCountryCode(String createCountryCode) {
		this.createCountryCode = createCountryCode;
	}

	/**
	 * Gets the ip.
	 * 
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * Sets the ip.
	 * 
	 * @param ip
	 *            the new ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * Gets the provinceid.
	 * 
	 * @return the provinceid
	 */
	public Integer getProvinceid() {
		return provinceid;
	}

	/**
	 * Sets the provinceid.
	 * 
	 * @param provinceid
	 *            the new provinceid
	 */
	public void setProvinceid(Integer provinceid) {
		this.provinceid = provinceid;
	}

	/**
	 * Gets the cityid.
	 * 
	 * @return the cityid
	 */
	public Integer getCityid() {
		return cityid;
	}

	/**
	 * Sets the cityid.
	 * 
	 * @param cityid
	 *            the new cityid
	 */
	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}

	/**
	 * Gets the areaid.
	 * 
	 * @return the areaid
	 */
	public Integer getAreaid() {
		return areaid;
	}

	/**
	 * Sets the areaid.
	 * 
	 * @param areaid
	 *            the new areaid
	 */
	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
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
	 * Gets the msn number.
	 * 
	 * @return the msn number
	 */
	public String getMsnNumber() {
		return msnNumber;
	}

	/**
	 * Sets the msn number.
	 * 
	 * @param msnNumber
	 *            the new msn number
	 */
	public void setMsnNumber(String msnNumber) {
		this.msnNumber = msnNumber;
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
	 * Gets the qq list.
	 * 
	 * @return the qq list
	 */
	public List<String> getQqList() {
		return qqList;
	}

	/**
	 * Sets the qq list.
	 * 
	 * @param qqList
	 *            the new qq list
	 */
	public void setQqList(List<String> qqList) {
		this.qqList = qqList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public ShopDetailView clone() {
		ShopDetailView shopDetail = new ShopDetailView();
		shopDetail.shopId = this.shopId;
		shopDetail.userId = this.userId;
		shopDetail.web = this.web;
		shopDetail.sitename = this.sitename;
		shopDetail.maddr = this.maddr;
		shopDetail.msn = this.msn;
		shopDetail.mname = this.mname;
		shopDetail.code = this.code;
		shopDetail.ymaddr = this.ymaddr;
		shopDetail.ymname = this.ymname;
		shopDetail.status = this.status;
		shopDetail.storeName = this.storeName;
		shopDetail.visitTimes = this.visitTimes;
		shopDetail.visitTimesToday = this.visitTimesToday;
		shopDetail.productNum = this.productNum;
		shopDetail.commNum = this.commNum;
		shopDetail.offProductNum = this.offProductNum;
		shopDetail.langStyle = this.langStyle;
		shopDetail.modifyTime = this.modifyTime;
		shopDetail.addtime = this.addtime;
		shopDetail.briefDesc = this.briefDesc;
		shopDetail.detailDesc = this.detailDesc;
		shopDetail.shopPic = this.shopPic;
		shopDetail.colorStyle = this.colorStyle;
		shopDetail.userMail = this.userMail;
		shopDetail.userAdds = this.userAdds;
		shopDetail.userTel = this.userTel;
		shopDetail.userPostcode = this.userPostcode;
		shopDetail.gradeId = this.gradeId;
		shopDetail.type = this.type;
		shopDetail.idCardPic = this.idCardPic;
		shopDetail.trafficPic = this.trafficPic;
		shopDetail.idCardPicFile = this.idCardPicFile;
		shopDetail.trafficPicFile = this.trafficPicFile;
		shopDetail.idCardNum = this.idCardNum;
		shopDetail.realPath = this.realPath;
		shopDetail.createAreaCode = this.createAreaCode;
		shopDetail.createCountryCode = this.createCountryCode;
		shopDetail.ip = this.ip;
		shopDetail.provinceid = this.provinceid;
		shopDetail.cityid = this.cityid;
		shopDetail.areaid = this.areaid;
		shopDetail.province = this.province;
		shopDetail.city = this.city;
		shopDetail.area = this.area;
		shopDetail.nickName = this.nickName;
		shopDetail.userMobile = this.userMobile;
		shopDetail.msnNumber = this.msnNumber;
		shopDetail.qq = this.qq;
		shopDetail.fax = this.fax;
		return shopDetail;
	}

	/**
	 * Gets the front type.
	 * 
	 * @return the front type
	 */
	public String getFrontType() {
		return frontType;
	}

	/**
	 * Sets the front type.
	 * 
	 * @param frontType
	 *            the new front type
	 */
	public void setFrontType(String frontType) {
		this.frontType = frontType;
	}

	/**
	 * Gets the end type.
	 * 
	 * @return the end type
	 */
	public String getEndType() {
		return endType;
	}

	/**
	 * Sets the end type.
	 * 
	 * @param endType
	 *            the new end type
	 */
	public void setEndType(String endType) {
		this.endType = endType;
	}

}