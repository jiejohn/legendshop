/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import java.util.List;

import com.legendshop.business.dao.ImgFileDao;
import com.legendshop.business.service.ImgFileService;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.ImgFile;
import com.legendshop.model.entity.Product;
import com.legendshop.util.AppUtils;

/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * ------------------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ------------------------------------------------------------------------------------
 * 
 * 官方网站：http://www.legendesign.net
 */
public class ImgFileServiceImpl implements ImgFileService {
	
	/** The img file dao. */
	private ImgFileDao imgFileDao;

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.ImgFileService#list(java.lang.String)
	 */
	@Override
	public List<ImgFile> getImgFile(String userName) {
		return imgFileDao.findByHQL("from ImgFile where userName = ?", new Object[] { userName });
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.ImgFileService#load(java.lang.Long)
	 */
	@Override
	public ImgFile getImgFileById(Long id) {
		return imgFileDao.get(ImgFile.class, id);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.ImgFileService#loadProd(java.lang.Long)
	 */
	@Override
	public Product getProd(Long id) {
		return imgFileDao.get(Product.class, id);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.ImgFileService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		imgFileDao.deleteById(ImgFile.class, id);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.ImgFileService#save(com.legendshop.model.entity.ImgFile)
	 */
	@Override
	public Long save(ImgFile imgFile) {
		if (!AppUtils.isBlank(imgFile.getFileId())) {
			update(imgFile);
			return imgFile.getFileId();
		}
		return (Long) imgFileDao.save(imgFile);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.ImgFileService#update(com.legendshop.model.entity.ImgFile)
	 */
	@Override
	public void update(ImgFile imgFile) {
		imgFileDao.update(imgFile);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.ImgFileService#getImgFileList(com.legendshop.core.dao.support.CriteriaQuery)
	 */
	@Override
	public PageSupport getImgFileList(CriteriaQuery cq) {
		return imgFileDao.find(cq);
	}

	/**
	 * Sets the img file dao.
	 * 
	 * @param imgFileDao
	 *            the new img file dao
	 */
	public void setImgFileDao(ImgFileDao imgFileDao) {
		this.imgFileDao = imgFileDao;
	}
}
