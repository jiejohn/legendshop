/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao.impl;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.legendshop.business.dao.ProductCommentDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.model.entity.ProductComment;

/**
 * 产品评论Dao.
 */
public class ProductCommentDaoImpl extends BaseDaoImpl implements ProductCommentDao {
     
     /** The log. */
     private static Logger log = LoggerFactory.getLogger(ProductCommentDaoImpl.class);

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.ProductCommentDao#deleteProductComment(java.lang.Long, java.lang.String)
	 */
	@Override
	public void deleteProductComment(Long prodId, String userName) {
		exeByHQL("delete from ProductComment where prodId = ? and ownerName = ?", new Object[] {prodId, userName });
		
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.ProductCommentDao#saveProductComment(com.legendshop.model.entity.ProductComment)
	 */
	@Override
	public void saveProductComment(ProductComment productComment) {
		save(productComment);
	}

	@Override
	public void updateProductComment(ProductComment productComment) {
		update(productComment);
		
	}

	@Override
	public void deleteProductCommentById(Long id) {
		deleteById(ProductComment.class, id);
		
	}
     
	
 }

