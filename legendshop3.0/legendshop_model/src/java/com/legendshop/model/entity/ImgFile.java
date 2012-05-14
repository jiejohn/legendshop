/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.model.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 产品图片.
 */
public class ImgFile extends UploadFile implements BaseEntity{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7521569831212302925L;

	/** The file id. */
	private Long fileId;

	/** The user name. */
	private String userName;

	/** The product id. */
	private Long productId;

	/** The product type. */
	private Short productType;

	/** The file path. */
	private String filePath;

	/** The file type. */
	private String fileType;

	/** The file size. */
	private Integer fileSize;

	/** The upoad time. */
	private Date upoadTime;

	/** The status. */
	private Short status;


	/**
	 * Instantiates a new img file.
	 */
	public ImgFile() {
	}

	/**
	 * Gets the file id.
	 * 
	 * @return the file id
	 */
	public Long getFileId() {
		return fileId;
	}

	/**
	 * Sets the file id.
	 * 
	 * @param fileId
	 *            the new file id
	 */
	public void setFileId(Long fileId) {
		this.fileId = fileId;
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
	 * Gets the product id.
	 * 
	 * @return the product id
	 */
	public Long getProductId() {
		return productId;
	}

	/**
	 * Sets the product id.
	 * 
	 * @param productId
	 *            the new product id
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/**
	 * Gets the product type.
	 * 
	 * @return the product type
	 */
	public Short getProductType() {
		return productType;
	}

	/**
	 * Sets the product type.
	 * 
	 * @param productType
	 *            the new product type
	 */
	public void setProductType(Short productType) {
		this.productType = productType;
	}

	/**
	 * Gets the file path.
	 * 
	 * @return the file path
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * Sets the file path.
	 * 
	 * @param filePath
	 *            the new file path
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * Gets the file type.
	 * 
	 * @return the file type
	 */
	public String getFileType() {
		return fileType;
	}

	/**
	 * Sets the file type.
	 * 
	 * @param fileType
	 *            the new file type
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	/**
	 * Gets the file size.
	 * 
	 * @return the file size
	 */
	public Integer getFileSize() {
		return fileSize;
	}

	/**
	 * Sets the file size.
	 * 
	 * @param fileSize
	 *            the new file size
	 */
	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * Gets the upoad time.
	 * 
	 * @return the upoad time
	 */
	public Date getUpoadTime() {
		return upoadTime;
	}

	/**
	 * Sets the upoad time.
	 * 
	 * @param upoadTime
	 *            the new upoad time
	 */
	public void setUpoadTime(Date upoadTime) {
		this.upoadTime = upoadTime;
	}

	/**
	 * Gets the status.
	 * 
	 * @return the status
	 */
	public Short getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 * 
	 * @param status
	 *            the new status
	 */
	public void setStatus(Short status) {
		this.status = status;
	}

	public Serializable getId() {
		return fileId;
	}

}
