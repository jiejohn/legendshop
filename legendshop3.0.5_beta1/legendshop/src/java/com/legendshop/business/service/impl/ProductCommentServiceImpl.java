/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.legendshop.business.dao.ProductCommentDao;
import com.legendshop.business.service.ProductCommentService;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Product;
import com.legendshop.model.entity.ProductComment;
import com.legendshop.util.AppUtils;

/**
 * 产品评论服务.
 */
public class ProductCommentServiceImpl implements ProductCommentService {
	
	/** 产品评论Dao. */
	private ProductCommentDao productCommentDao;
	

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.ProductCommentService#list(java.lang.String)
	 */
	@Override
	public List<ProductComment> getProductCommentList(String userName) {
		return productCommentDao.findByHQL("from ProductComment where userName = ?", new Object[] { userName });
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.ProductCommentService#load(java.lang.Long)
	 */
	@Override
	public ProductComment getProductCommentById(Long id) {
		return productCommentDao.get(ProductComment.class, id);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.ProductCommentService#loadProd(java.lang.Long)
	 */
	@Override
	public Product getProduct(Long id) {
		return productCommentDao.get(Product.class, id);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.ProductCommentService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		productCommentDao.deleteProductCommentById(id);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.ProductCommentService#save(com.legendshop.model.entity.ProductComment)
	 */
	@Override
	public Long save(ProductComment productComment) {
		if (!AppUtils.isBlank(productComment.getId())) {
			update(productComment);
			return productComment.getId();
		}
		return (Long) productCommentDao.save(productComment);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.ProductCommentService#update(com.legendshop.model.entity.ProductComment)
	 */
	@Override
	public void update(ProductComment productComment) {
		productCommentDao.updateProductComment(productComment);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.ProductCommentService#getDataByCriteriaQuery(com.legendshop.core.dao.support.CriteriaQuery)
	 */
	@Override
	public PageSupport getProductCommentList(CriteriaQuery cq) {
		return productCommentDao.find(cq);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.ProductCommentService#getDataByCriteriaQuery(com.legendshop.core.dao.support.HqlQuery)
	 */
	@Override
	public PageSupport getProductCommentList(HqlQuery hql) {
		return productCommentDao.find(hql);
	}
	
	/**
	 * Sets the product comment dao.
	 * 
	 * @param productCommentDao
	 *            the new product comment dao
	 */
	@Required
	public void setProductCommentDao(ProductCommentDao productCommentDao) {
		this.productCommentDao = productCommentDao;
	}
}
