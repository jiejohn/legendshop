/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao;

import java.util.List;

import net.sf.json.JSONArray;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.model.entity.ImgFile;
import com.legendshop.model.entity.Indexjpg;

/**
 * The Interface ImgFileDao.
 */
public interface ImgFileDao extends BaseDao{

	/**
	 * 得到首页图片.
	 * 
	 * @param userName
	 *            the user name
	 * @return the index jpeg
	 */
	public abstract List<Indexjpg> getIndexJpeg(final String userName);
	
	
	/**
	 * Gets the index jpeg json.
	 * 
	 * @param userName
	 *            the user name
	 * @return the index jpeg json
	 */
	public abstract JSONArray getIndexJpegJSON(final String userName);
	
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
	
	
	/**
	 * Gets the all product pics.
	 * 得到所以的产品的描述图片
	 * @param userName
	 *            the user name
	 * @param prodId
	 *            the prod id
	 * @return the all product pics
	 */
	public abstract List<ImgFile> getAllProductPics(final String userName, final Long prodId);


	/**
	 * Delete img file by id.
	 * 
	 * @param id
	 *            the id
	 */
	public abstract void deleteImgFileById(Long id);


	/**
	 * Update img file.
	 * 
	 * @param imgFile
	 *            the img file
	 */
	public abstract void updateImgFile(ImgFile imgFile);
	
	/**
	 * Update img file online.
	 * 
	 * @param fileId
	 *            the file id
	 * @return true, if successful
	 */
	public boolean updateImgFileOnline(Long fileId);

	/**
	 * Update img file offline.
	 * 
	 * @param fileId
	 *            the file id
	 * @return true, if successful
	 */
	public boolean updateImgFileOffline(Long fileId);


	/**
	 * Delete img file.
	 * 
	 * @param imgFile
	 *            the img file
	 */
	public abstract void deleteImgFile(ImgFile imgFile);
}