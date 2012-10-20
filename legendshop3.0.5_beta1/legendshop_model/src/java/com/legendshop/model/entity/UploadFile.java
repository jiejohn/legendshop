/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.model.entity;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

/**
 * 上传文件对象.
 */
public abstract class UploadFile extends AbstractEntity implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8055409574740815068L;
	
	/** The file. */
	protected MultipartFile file;
	
	/**
	 * Gets the file.
	 * 
	 * @return the file
	 */
	public MultipartFile getFile() {
		return file;
	}

	/**
	 * Sets the file.
	 * 
	 * @param file
	 *            the new file
	 */
	public void setFile(MultipartFile file) {
		this.file = file;
	}



}
