/**
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import net.sf.ehcache.Cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;

import com.legendshop.business.common.CacheKeys;
import com.legendshop.business.dao.ProductDao;
import com.legendshop.core.cache.CacheCallBack;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Product;
import com.legendshop.model.entity.ProductDetail;
import com.legendshop.util.AppUtils;
import com.legendshop.util.sql.ConfigCode;


/**
 * 产品Dao.
 */
public class ProductDaoImpl extends BaseDaoImpl implements ProductDao {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(ProductDaoImpl.class);

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ProductDao#getCommendProd(java.lang.String, int)
	 */
	@Override
	@SuppressWarnings("unchecked")
	@Cacheable("sortCache")
	public List<Product> getCommendProd(final String shopName, final int total) {
		log.debug("getCommendProd, shopName = {},total = {}", shopName, total);
//		return (List<Product>) getObjectFromCache(getKey(CacheKeys.PRODUCTDAO_GETCOMMEND_PROD, shopName),
//				new CacheCallBack<List<Product>>() {
//					@Override
//					public List<Product> doInCache(String cahceName, Cache cache) {
//						Date date = new Date();
//						return findByHQLLimit(ConfigCode.getInstance().getCode("biz.getCommend"), 0, total, shopName,
//								date, date);
//					}
//
//				});
		Date date = new Date();
		return findByHQLLimit(ConfigCode.getInstance().getCode("biz.getCommend"), 0, total, shopName,
				date, date);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ProductDao#getReleationProd(java.lang.String, java.lang.Long, int)
	 */
	@Override
	public List<Product> getReleationProd(final String shopName, final Long prodId, final int total) {
		return (List<Product>) getObjectFromCache(getKey(CacheKeys.PRODUCTDAO_GETRELEATION_PROD, shopName, prodId, total),
				new CacheCallBack<List<Product>>() {
					@Override
					public List<Product> doInCache(String cahceName, Cache cache) {
						Date date = new Date();
						return findByHQLLimit(ConfigCode.getInstance().getCode("biz.getRelationProd"), 0, total,
								shopName, date, date, prodId);
					}

				});
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ProductDao#getNewestProd(java.lang.String, int)
	 */
	@Override
	public List<Product> getNewestProd(final String shopName, final int total) {
		return (List<Product>) getObjectFromCache(getKey(CacheKeys.PRODUCTDAO_GETNEWEST_PROD, shopName),
				new CacheCallBack<List<Product>>() {
					@Override
					public List<Product> doInCache(String cahceName, Cache cache) {
						Date date = new Date();
						return findByHQLLimit(ConfigCode.getInstance().getCode("biz.getNewestProd"), 0, total, shopName,
								date, date);
					}
				});

	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ProductDao#gethotsale(java.lang.String)
	 */
	@Override
	public List<Product> gethotsale(final String name) {
		if (name == null) {
			return null;
		}
		return (List<Product>) getObjectFromCache(getKey(CacheKeys.RPODUCTDAO_GETORDERhotsale, name), new CacheCallBack() {
			@Override
			public List<Product> doInCache(String cahceName, Cache cache) {
				Date date = new Date();
				return findByHQLLimit(ConfigCode.getInstance().getCode("biz.gethotsale"), 0, 6, name, date, date);
			}

		});
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ProductDao#getProdDetail(com.legendshop.core.dao.support.CriteriaQuery)
	 */
	@Override
	public PageSupport getProdDetail(CriteriaQuery cq) {
		return find(cq);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ProductDao#getProdDetail(com.legendshop.core.dao.support.HqlQuery)
	 */
	@Override
	public PageSupport getProdDetail(HqlQuery hql) {
		return find(hql);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ProductDao#getProdDetail(java.lang.Long)
	 */
	@Override
	public ProductDetail getProdDetail(final Long prodId) {
		return get(ProductDetail.class, prodId);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ProductDao#getProdById(java.lang.Long)
	 */
	@Override
	public Product getProdById(Long prodId) {
		return get(Product.class, prodId);
	}

	// 热门商品
	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ProductDao#getHotOn(java.lang.String)
	 */
	@Override
	public List<Product> getHotOn(String sortId) {
		if (AppUtils.isBlank(sortId)) {
			return Collections.EMPTY_LIST;
		}
		Date date = new Date();
		return findByHQLLimit(ConfigCode.getInstance().getCode("biz.getHotOnProd"), 0, 21, Long.valueOf(sortId), date,
				date);
	}

	// 查看某个商城的热门商品
	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ProductDao#getHotViewProd(java.lang.String, java.lang.Integer)
	 */
	@Override
	public List<Product> getHotViewProd(String shopName, Integer number) {
		Date date = new Date();
		return findByHQLLimit(ConfigCode.getInstance().getCode("biz.getHotViewProd"), 0, number, shopName, date, date);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ProductDao#queryProduct(java.lang.Long)
	 */
	@Override
	public Product getProduct(Long id) {
		return get(Product.class, id);
	}

	// 更新商品查看数
	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ProductDao#updateProdViews(java.lang.Long)
	 */
	@Override
	public void updateProdViews(Long prodId) {
		exeByHQL("update Product set views = views+1 where prodId = ?", prodId);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ProductDao#updateProduct(com.legendshop.model.entity.Product)
	 */
	@Override
	public void  updateProduct(Product origin) {
			update(origin);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ProductDao#saveProduct(com.legendshop.model.entity.Product)
	 */
	@Override
	public Long saveProduct(Product product) {
		return (Long)save(product);
	}

	@Override
	public List<ProductDetail> getProdDetail(Long[] prodId) {
		// TODO Auto-generated method stub
		return null;
	}

}
