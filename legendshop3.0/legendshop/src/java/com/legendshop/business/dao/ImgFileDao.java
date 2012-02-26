/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao;

import java.util.List;

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
	 * 得到产品的描述图片.
	 * 
	 * @param userName
	 *            the user name
	 * @param prodId
	 *            the prod id
	 * @return the product pics
	 */
	public abstract List<ImgFile> getProductPics(final String userName, final Long prodId);

}