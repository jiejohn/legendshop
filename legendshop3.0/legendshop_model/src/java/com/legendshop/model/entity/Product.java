/*
 * LegendShop 多用户商城系统
 * 
 *  版权所有, 并保留所有权利。
 * 
 */
package com.legendshop.model.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 产品对象.
 */
public class Product extends UploadFile implements BaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7571396124663475715L;
	
	/** The prod id. */
	private Long prodId;
	
	/** The sort id. */
	private Long sortId;
	
	/** The nsort id. */
	private Long nsortId;
	
	/** The sub nsort id. */
	private Long subNsortId;
	
	/** The model id. */
	private String modelId;
	
	/** The name. */
	private String name;
	
	/** The price. */
	private Double price;
	
	/** The cash. */
	private Double cash;
	
	/** The proxy price. */
	private Double proxyPrice;
	
	/** The carriage. */
	private Double carriage;
	
	/** The brief. */
	private String brief;
	
	/** The content. */
	private String content;
	
	/** The views. */
	private Integer views;
	
	/** The buys. */
	private Integer buys;
	
	/** The rec date. */
	private Date recDate;
	
	/** The pic. */
	private String pic;
	
	/** The commend. */
	private String commend;
	
	/** The status. */
	private Integer status;
	
	/** The modify date. */
	private Date modifyDate;
	
	/** The user id. */
	private String userId;
	
	/** The user name. */
	private String userName;
	
	/** The start date. */
	private Date startDate;
	
	/** The end date. */
	private Date endDate;
	
	/** The stocks. */
	private Integer stocks;
	
	/** The prod type. */
	private String prodType;
	
	/** The key word. */
	private String keyWord;
	
	/** The attribute. */
	private String attribute;
	
	/** The parameter. */
	private String parameter;
	
	/** The brand id. */
	private Long brandId;
	
	/** The actual stocks. */
	private Integer actualStocks;
	
	
	

	/**
	 * default constructor.
	 */
	public Product() {
	}


	/**
	 * Instantiates a new product.
	 * 
	 * @param prodId
	 *            the prod id
	 * @param sortId
	 *            the sort id
	 * @param nsortId
	 *            the nsort id
	 * @param subNsortId
	 *            the sub nsort id
	 * @param prodName
	 *            the prod name
	 */
	public Product(Long prodId, Long sortId, Long nsortId, Long subNsortId, String prodName) {
		this.prodId = prodId;
		this.sortId = sortId;
		this.nsortId = nsortId;
		this.subNsortId = subNsortId;
		this.name = prodName;
	}

	/**
	 * Instantiates a new product.
	 * 
	 * @param prodId
	 *            the prod id
	 * @param sortId
	 *            the sort id
	 * @param nsortId
	 *            the nsort id
	 * @param subNsortId
	 *            the sub nsort id
	 * @param prodName
	 *            the prod name
	 * @param pic
	 *            the pic
	 * @param price
	 *            the price
	 * @param cash
	 *            the cash
	 * @param proxyPrice
	 *            the proxy price
	 * @param views
	 *            the views
	 * @param buys
	 *            the buys
	 */
	public Product(Long prodId, Long sortId, Long nsortId, Long subNsortId, String prodName, String pic, Double price,
			Double cash, Double proxyPrice, Integer views, Integer buys) {
		this.prodId = prodId;
		this.sortId = sortId;
		this.nsortId = nsortId;
		this.subNsortId = subNsortId;
		this.name = prodName;
		this.pic = pic;
		this.price = price;
		this.cash = cash;
		this.proxyPrice = proxyPrice;
		this.views = views;
		this.buys = buys;
	}

	/**
	 * Instantiates a new product.
	 * 
	 * @param prodId
	 *            the prod id
	 * @param sortId
	 *            the sort id
	 * @param nsortId
	 *            the nsort id
	 * @param subNsortId
	 *            the sub nsort id
	 * @param prodName
	 *            the prod name
	 * @param price
	 *            the price
	 * @param cash
	 *            the cash
	 * @param proxyPrice
	 *            the proxy price
	 * @param carriage
	 *            the carriage
	 * @param brief
	 *            the brief
	 */
	public Product(Long prodId, Long sortId, Long nsortId, Long subNsortId, String prodName, Double price, Double cash,
			Double proxyPrice, Double carriage, String brief) {
		this.prodId = prodId;
		this.sortId = sortId;
		this.nsortId = nsortId;
		this.subNsortId = subNsortId;
		this.name = prodName;
		this.price = price;
		this.cash = cash;
		this.proxyPrice = proxyPrice;
		this.carriage = carriage;
		this.brief = brief;
	}

	/**
	 * minimal constructor.
	 * 
	 * @param views
	 *            the views
	 * @param buys
	 *            the buys
	 * @param prodType
	 *            the prod type
	 */
	public Product(Integer views, Integer buys, String prodType) {
		this.views = views;
		this.buys = buys;
		this.prodType = prodType;
	}

	/**
	 * full constructor.
	 * 
	 * @param sortId
	 *            the sort id
	 * @param nsortId
	 *            the nsort id
	 * @param subNsortId
	 *            the sub nsort id
	 * @param modelId
	 *            the model id
	 * @param prodName
	 *            the prod name
	 * @param price
	 *            the price
	 * @param cash
	 *            the cash
	 * @param proxyPrice
	 *            the proxy price
	 * @param carriage
	 *            the carriage
	 * @param prodBrief
	 *            the prod brief
	 * @param prodContent
	 *            the prod content
	 * @param views
	 *            the views
	 * @param buys
	 *            the buys
	 * @param prodDate
	 *            the prod date
	 * @param pic
	 *            the pic
	 * @param commend
	 *            the commend
	 * @param status
	 *            the status
	 * @param modifyDate
	 *            the modify date
	 * @param userId
	 *            the user id
	 * @param userName
	 *            the user name
	 * @param startDate
	 *            the start date
	 * @param endDate
	 *            the end date
	 * @param stocks
	 *            the stocks
	 * @param prodType
	 *            the prod type
	 * @param keyWord
	 *            the key word
	 * @param attribute
	 *            the attribute
	 */
	public Product(Long sortId, Long nsortId, Long subNsortId, String modelId, String prodName, Double price,
			Double cash, Double proxyPrice, Double carriage, String prodBrief, String prodContent, Integer views,
			Integer buys, Date prodDate, String pic, String commend, Integer status, Date modifyDate, String userId,
			String userName, Date startDate, Date endDate, Integer stocks, String prodType, String keyWord,
			String attribute) {
		this.sortId = sortId;
		this.nsortId = nsortId;
		this.subNsortId = subNsortId;
		this.modelId = modelId;
		this.name = prodName;
		this.price = price;
		this.cash = cash;
		this.proxyPrice = proxyPrice;
		this.carriage = carriage;
		this.brief = prodBrief;
		this.content = prodContent;
		this.views = views;
		this.buys = buys;
		this.recDate = prodDate;
		this.pic = pic;
		this.commend = commend;
		this.status = status;
		this.modifyDate = modifyDate;
		this.userId = userId;
		this.userName = userName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.stocks = stocks;
		this.prodType = prodType;
		this.keyWord = keyWord;
		this.attribute = attribute;
	}

	/**
	 * Gets the prod id.
	 * 
	 * @return the prod id
	 */
	public Long getProdId() {
		return prodId;
	}

	/**
	 * Sets the prod id.
	 * 
	 * @param prodId
	 *            the new prod id
	 */
	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}

	/**
	 * Gets the sort id.
	 * 
	 * @return the sort id
	 */
	public Long getSortId() {
		return sortId;
	}

	/**
	 * Sets the sort id.
	 * 
	 * @param sortId
	 *            the new sort id
	 */
	public void setSortId(Long sortId) {
		this.sortId = sortId;
	}

	/**
	 * Gets the nsort id.
	 * 
	 * @return the nsort id
	 */
	public Long getNsortId() {
		return nsortId;
	}

	/**
	 * Sets the nsort id.
	 * 
	 * @param nsortId
	 *            the new nsort id
	 */
	public void setNsortId(Long nsortId) {
		this.nsortId = nsortId;
	}

	/**
	 * Gets the sub nsort id.
	 * 
	 * @return the sub nsort id
	 */
	public Long getSubNsortId() {
		return subNsortId;
	}

	/**
	 * Sets the sub nsort id.
	 * 
	 * @param subNsortId
	 *            the new sub nsort id
	 */
	public void setSubNsortId(Long subNsortId) {
		this.subNsortId = subNsortId;
	}

	/**
	 * Gets the model id.
	 * 
	 * @return the model id
	 */
	public String getModelId() {
		return modelId;
	}

	/**
	 * Sets the model id.
	 * 
	 * @param modelId
	 *            the new model id
	 */
	public void setModelId(String modelId) {
		this.modelId = modelId;
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
	 * Gets the price.
	 * 
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * Sets the price.
	 * 
	 * @param price
	 *            the new price
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * Gets the cash.
	 * 
	 * @return the cash
	 */
	public Double getCash() {
		return cash;
	}

	/**
	 * Sets the cash.
	 * 
	 * @param cash
	 *            the new cash
	 */
	public void setCash(Double cash) {
		this.cash = cash;
	}

	/**
	 * Gets the proxy price.
	 * 
	 * @return the proxy price
	 */
	public Double getProxyPrice() {
		return proxyPrice;
	}

	/**
	 * Sets the proxy price.
	 * 
	 * @param proxyPrice
	 *            the new proxy price
	 */
	public void setProxyPrice(Double proxyPrice) {
		this.proxyPrice = proxyPrice;
	}

	/**
	 * Gets the carriage.
	 * 
	 * @return the carriage
	 */
	public Double getCarriage() {
		return carriage;
	}

	/**
	 * Sets the carriage.
	 * 
	 * @param carriage
	 *            the new carriage
	 */
	public void setCarriage(Double carriage) {
		this.carriage = carriage;
	}

	/**
	 * Gets the brief.
	 * 
	 * @return the brief
	 */
	public String getBrief() {
		return brief;
	}

	/**
	 * Sets the brief.
	 * 
	 * @param brief
	 *            the new brief
	 */
	public void setBrief(String brief) {
		this.brief = brief;
	}

	/**
	 * Gets the content.
	 * 
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets the content.
	 * 
	 * @param content
	 *            the new content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Gets the views.
	 * 
	 * @return the views
	 */
	public Integer getViews() {
		return views;
	}

	/**
	 * Sets the views.
	 * 
	 * @param views
	 *            the new views
	 */
	public void setViews(Integer views) {
		this.views = views;
	}

	/**
	 * Gets the buys.
	 * 
	 * @return the buys
	 */
	public Integer getBuys() {
		return buys;
	}

	/**
	 * Sets the buys.
	 * 
	 * @param buys
	 *            the new buys
	 */
	public void setBuys(Integer buys) {
		this.buys = buys;
	}

	/**
	 * Gets the rec date.
	 * 
	 * @return the rec date
	 */
	public Date getRecDate() {
		return recDate;
	}

	/**
	 * Sets the rec date.
	 * 
	 * @param recDate
	 *            the new rec date
	 */
	public void setRecDate(Date recDate) {
		this.recDate = recDate;
	}

	/**
	 * Gets the pic.
	 * 
	 * @return the pic
	 */
	public String getPic() {
		return pic;
	}

	/**
	 * Sets the pic.
	 * 
	 * @param pic
	 *            the new pic
	 */
	public void setPic(String pic) {
		this.pic = pic;
	}

	/**
	 * Gets the commend.
	 * 
	 * @return the commend
	 */
	public String getCommend() {
		return commend;
	}

	/**
	 * Sets the commend.
	 * 
	 * @param commend
	 *            the new commend
	 */
	public void setCommend(String commend) {
		this.commend = commend;
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
	 * Gets the modify date.
	 * 
	 * @return the modify date
	 */
	public Date getModifyDate() {
		return modifyDate;
	}

	/**
	 * Sets the modify date.
	 * 
	 * @param modifyDate
	 *            the new modify date
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
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
	 * Gets the start date.
	 * 
	 * @return the start date
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Sets the start date.
	 * 
	 * @param startDate
	 *            the new start date
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets the end date.
	 * 
	 * @return the end date
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Sets the end date.
	 * 
	 * @param endDate
	 *            the new end date
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Gets the stocks.
	 * 
	 * @return the stocks
	 */
	public Integer getStocks() {
		return stocks;
	}

	/**
	 * Sets the stocks.
	 * 
	 * @param stocks
	 *            the new stocks
	 */
	public void setStocks(Integer stocks) {
		this.stocks = stocks;
	}

	/**
	 * Gets the prod type.
	 * 
	 * @return the prod type
	 */
	public String getProdType() {
		return prodType;
	}

	/**
	 * Sets the prod type.
	 * 
	 * @param prodType
	 *            the new prod type
	 */
	public void setProdType(String prodType) {
		this.prodType = prodType;
	}

	/**
	 * Gets the key word.
	 * 
	 * @return the key word
	 */
	public String getKeyWord() {
		return keyWord;
	}

	/**
	 * Sets the key word.
	 * 
	 * @param keyWord
	 *            the new key word
	 */
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	/**
	 * Gets the attribute.
	 * 
	 * @return the attribute
	 */
	public String getAttribute() {
		return attribute;
	}

	/**
	 * Sets the attribute.
	 * 
	 * @param attribute
	 *            the new attribute
	 */
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	/**
	 * Gets the parameter.
	 * 
	 * @return the parameter
	 */
	public String getParameter() {
		return parameter;
	}

	/**
	 * Sets the parameter.
	 * 
	 * @param parameter
	 *            the new parameter
	 */
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	/**
	 * Gets the brand id.
	 * 
	 * @return the brand id
	 */
	public Long getBrandId() {
		return brandId;
	}

	/**
	 * Sets the brand id.
	 * 
	 * @param brandId
	 *            the new brand id
	 */
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	/**
	 * Gets the actual stocks.
	 * 
	 * @return the actual stocks
	 */
	public Integer getActualStocks() {
		return actualStocks;
	}

	/**
	 * Sets the actual stocks.
	 * 
	 * @param actualStocks
	 *            the new actual stocks
	 */
	public void setActualStocks(Integer actualStocks) {
		this.actualStocks = actualStocks;
	}


	public Serializable getId() {
		return prodId;
	}

}