/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service;

import java.util.List;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.ImgFile;
import com.legendshop.model.entity.Product;

/**
 * The Interface ImgFileService.
 */
public interface ImgFileService {

	/**
	 * List.
	 * 
	 * @param userName
	 *            the user name
	 * @return the list
	 */
	public abstract List<ImgFile> getImgFile(String userName);

	/**
	 * Load.
	 * 
	 * @param id
	 *            the id
	 * @return the img file
	 */
	public abstract ImgFile getImgFileById(Long id);

	/**
	 * Load prod.
	 * 
	 * @param id
	 *            the id
	 * @return the product
	 */
	public abstract Product getProd(Long id);

	/**
	 * Delete.
	 * 
	 * @param id
	 *            the id
	 */
	public abstract void delete(Long id);

	/**
	 * Save.
	 * 
	 * @param imgFile
	 *            the img file
	 * @return the long
	 */
	public abstract Long save(ImgFile imgFile);

	/**
	 * Update.
	 * 
	 * @param imgFile
	 *            the img file
	 */
	public abstract void update(ImgFile imgFile);

	/**
	 * Gets the img file list.
	 * 
	 * @param cq
	 *            the cq
	 * @return the img file list
	 */
	public abstract PageSupport getImgFileList(CriteriaQuery cq);
	
	/**
	 * Img file online.
	 * 
	 * @param fileId
	 *            the file id
	 * @return true, if successful
	 */
	public abstract boolean updateImgFileOnline(Long fileId);

	/**
	 * Img file offline.
	 * 
	 * @param fileId
	 *            the file id
	 * @return true, if successful
	 */
	public abstract boolean updateImgFileOffline(Long fileId);
	
	/**
	 * 得到有效的产品的描述图片.
	 * 
	 * @param userName
	 *            the user name
	 * @param prodId
	 *            the prod id
	 * @return the product pics
	 */
	public abstract List<ImgFile> getProductPics(final String userName, final Long prodId);

}