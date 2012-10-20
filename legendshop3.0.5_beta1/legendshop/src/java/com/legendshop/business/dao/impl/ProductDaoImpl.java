/**
 *
 * LegendShop 多用户商城系统
 *
 *  版权所有,并保留所有权利。
 *
 */
package com.legendshop.business.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import com.legendshop.business.dao.ProductDao;
import com.legendshop.core.OperationTypeEnum;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.constant.ProductStatusEnum;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.event.impl.FireEvent;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.event.EventHome;
import com.legendshop.model.dynamic.Model;
import com.legendshop.model.entity.DynamicTemp;
import com.legendshop.model.entity.Product;
import com.legendshop.model.entity.RelProduct;
import com.legendshop.spi.constants.Constants;
import com.legendshop.util.AppUtils;
import com.legendshop.util.sql.ConfigCode;

/**
 * 产品Dao.
 */
public abstract class ProductDaoImpl extends BaseDaoImpl implements ProductDao {

	/** The log. */
	private static Logger log = LoggerFactory.getLogger(ProductDaoImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.dao.impl.ProductDao#getCommendProd(java.lang.
	 * String, int)
	 */
	@Override
	@SuppressWarnings("unchecked")
	@Cacheable(value = "ProductList")
	public List<Product> getCommendProd(final String shopName, final int total) {
		log.debug("getCommendProd, shopName = {},total = {}", shopName, total);
		// return (List<Product>)
		// getObjectFromCache(getKey(CacheKeys.PRODUCTDAO_GETCOMMEND_PROD,
		// shopName),
		// new CacheCallBack<List<Product>>() {
		// @Override
		// public List<Product> doInCache(String cahceName, Cache cache) {
		// Date date = new Date();
		// return
		// findByHQLLimit(ConfigCode.getInstance().getCode("biz.getCommend"), 0,
		// total, shopName,
		// date, date);
		// }
		//
		// });
		Date date = new Date();
		return findByHQLLimit(ConfigCode.getInstance().getCode("biz.getCommend"), 0, total, shopName, date, date);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.dao.impl.ProductDao#getReleationProd(java.lang
	 * .String, java.lang.Long, int)
	 */
	@Override
	@Cacheable(value = "ProductList")
	public List<Product> getReleationProd(final String shopName, final Long prodId, final int total) {
		Date date = new Date();
		return findByHQLLimit(ConfigCode.getInstance().getCode("biz.getRelationProd"), 0, total, shopName, date, date,
				prodId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.dao.impl.ProductDao#getNewestProd(java.lang.String
	 * , int)
	 */
	@Override
	@Cacheable(value = "ProductList")
	public List<Product> getNewestProd(final String shopName, final int total) {
		Date date = new Date();
		return findByHQLLimit(ConfigCode.getInstance().getCode("biz.getNewestProd"), 0, total, shopName, date, date);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.dao.impl.ProductDao#gethotsale(java.lang.String)
	 */
	@Override
	@Cacheable(value = "ProductList")
	public List<Product> gethotsale(final String shopName) {
		if (shopName == null) {
			return null;
		}
		Date date = new Date();
		return findByHQLLimit(ConfigCode.getInstance().getCode("biz.gethotsale"), 0, 6, shopName, date, date);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.dao.impl.ProductDao#getProdDetail(com.legendshop
	 * .core.dao.support.CriteriaQuery)
	 */
	@Override
	@Cacheable(value = "ProductList", condition = "T(Integer).parseInt(#curPageNO) < 3")
	public PageSupport getProdDetail(Locale locale, String curPageNO, Long sortId, Long nsortId, Long subNsortId) {
		// Qbc查找方式
		CriteriaQuery cq = new CriteriaQuery(Product.class, curPageNO);
		cq.setPageSize(PropertiesUtil.getObject(ParameterEnum.FRONT_PAGE_SIZE, Integer.class));
		cq.addOrder("desc", "prodId");
		cq.eq("sortId", sortId);
		cq.eq("nsortId", nsortId);
		cq.eq("subNsortId", subNsortId);
		
		PageSupport ps = find(cq);
		ps.setToolBar(locale, Constants.SIMPLE_PAGE_PROVIDER);
		return ps;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.dao.impl.ProductDao#getProdDetail(com.legendshop
	 * .core.dao.support.HqlQuery)
	 */
	@Override
	public PageSupport getProdDetail(Locale locale, String curPageNO, Long sortId) {
		// HQL查找方式
		HqlQuery hql = new HqlQuery(PropertiesUtil.getObject(ParameterEnum.FRONT_PAGE_SIZE, Integer.class), curPageNO);
		String QueryNsortCount = ConfigCode.getInstance().getCode("biz.getSortProdCount");
		String QueryNsort = ConfigCode.getInstance().getCode("biz.getSortProd");
		hql.setAllCountString(QueryNsortCount);
		hql.setQueryString(QueryNsort);
		Date date = new Date();
		hql.setParam(new Object[] { sortId, date, date });
		PageSupport ps = find(hql);
		ps.setToolBar(locale, Constants.SIMPLE_PAGE_PROVIDER);
		return ps;
	}

	// 热门商品
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.dao.impl.ProductDao#getHotOn(java.lang.String)
	 */
	@Override
	@Cacheable(value = "ProductList")
	public List<Product> getHotOn(String sortId) {
		if (AppUtils.isBlank(sortId)) {
			return Collections.EMPTY_LIST;
		}
		Date date = new Date();
		return findByHQLLimit(ConfigCode.getInstance().getCode("biz.getHotOnProd"), 0, 21, Long.valueOf(sortId), date,
				date);
	}

	// 查看某个商城的热门商品
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.dao.impl.ProductDao#getHotViewProd(java.lang.
	 * String, java.lang.Integer)
	 */
	@Override
	public List<Product> getHotViewProd(String shopName, Integer number) {
		Date date = new Date();
		return findByHQLLimit(ConfigCode.getInstance().getCode("biz.getHotViewProd"), 0, number, shopName, date, date);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.dao.impl.ProductDao#queryProduct(java.lang.Long)
	 */
	@Override
	public Product getProduct(Long id) {
		return get(Product.class, id);
	}

	// 更新商品查看数
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.dao.impl.ProductDao#updateProdViews(java.lang
	 * .Long)
	 */
	@Override
	public void updateProdViews(Long prodId) {
		exeByHQL("update Product set views = views+1 where prodId = ?", prodId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.dao.impl.ProductDao#updateProduct(com.legendshop
	 * .model.entity.Product)
	 */
	@Override
	@Caching(evict = { @CacheEvict(value = "ProductDetail", key = "#product.prodId"),
			@CacheEvict(value = "Product", key = "#product.prodId") })
	public void updateProduct(Product product) {
		EventHome.publishEvent(new FireEvent(product, OperationTypeEnum.UPDATE));
		update(product);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.dao.impl.ProductDao#saveProduct(com.legendshop
	 * .model.entity.Product)
	 */
	@Override
	public Long saveProduct(Product product) {
		return (Long) save(product);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.dao.ProductDao#getProdDetail(com.legendshop.core
	 * .dao.support.CriteriaQuery)
	 */
	@Override
	public PageSupport getProdDetail(CriteriaQuery cq) {
		return find(cq);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.dao.ProductDao#deleteProdById(java.lang.Long)
	 */
	@Override
	@Caching(evict = {
			@CacheEvict(value = "ProductDetail", key = "#prodId"),
			@CacheEvict(value = "Product", key = "#prodId") })
	public void deleteProdById(Long prodId) {
		Product product = getProduct(prodId);
		if(product != null){
			EventHome.publishEvent(new FireEvent(product, OperationTypeEnum.DELETE));
			delete(product);
		}
	}

	/**
	 * Update product status.
	 * 
	 * @param prodId
	 *            the prod id
	 * @param status
	 *            the status
	 * @return true, if successful
	 */
	private boolean updateProductStatus(Product product, Integer status) {
		if (!status.equals(product.getStatus())) {
			product.setStatus(status);
			this.updateProduct(product);
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.dao.ProductDao#updateProdOnline(java.lang.Long)
	 */
	@Override
	@Caching(evict = {
			@CacheEvict(value = "ProductDetail", key = "#prodId"),
			@CacheEvict(value = "Product", key = "#prodId") })
	public boolean updateProdOnline(Long prodId) {
		Product product = get(Product.class, prodId);
		EventHome.publishEvent(new FireEvent(product, OperationTypeEnum.TURN_ON));
		return updateProductStatus(product, ProductStatusEnum.PROD_ONLINE.value());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.dao.ProductDao#updateProdOffline(java.lang.Long)
	 */
	@Override
	@Caching(evict = {
			@CacheEvict(value = "ProductDetail", key = "#prodId"),
			@CacheEvict(value = "Product", key = "#prodId") })
	public boolean updateProdOffline(Long prodId) {
		Product product = get(Product.class, prodId);
		EventHome.publishEvent(new FireEvent(product, OperationTypeEnum.TURN_OFF));
		return updateProductStatus(product, ProductStatusEnum.PROD_OFFLINE.value());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.dao.ProductDao#loadDynamicAttribute(java.lang
	 * .Long, java.lang.String)
	 */
	@Override
	public List<Model> loadDynamicAttribute(Long prodId, String userName) {
		List<Model> list = new ArrayList<Model>();
		Product product = getProd(prodId, userName);
		if (AppUtils.isNotBlank(product)) {
			if (AppUtils.isNotBlank(product.getAttribute()))
				list = JSONArray.fromObject(product.getAttribute());
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.legendshop.business.dao.ProductDao#getProd(java.lang.Long,
	 * java.lang.String)
	 */
	@Override
	public Product getProd(Long prodId, String userName) {
		return findUniqueBy("from Product prod where prod.prodId = ? and prod.userName = ?", Product.class, prodId,
				userName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.legendshop.business.dao.ProductDao#getDynamicTemp(java.lang.Long,
	 * java.lang.String)
	 */
	@Override
	public DynamicTemp getDynamicTemp(Long tempId, String userName) {
		return findUniqueBy("from DynamicTemp where id = ?  and userName = ?", DynamicTemp.class, tempId, userName);
	}

	@Override
	public boolean saveDynamicTemp(String tempName, String userName, Short type, String content) {
		if (AppUtils.isBlank(tempName) || AppUtils.isBlank(userName)) {
			return false;
		}
		List<DynamicTemp> temps = findByHQL("from DynamicTemp where type = ? and name = ? and userName = ?", type,
				tempName, userName);
		if (AppUtils.isNotBlank(temps)) {
			return false;
		}
		DynamicTemp temp = new DynamicTemp();
		temp.setContent(content);
		temp.setName(tempName);
		temp.setUserName(userName);
		temp.setType(type);
		save(temp);
		return true;
	}

	@Override
	@Caching(evict = {
			@CacheEvict(value = "ProductDetail", key = "#prodId"),
			@CacheEvict(value = "Product", key = "#prodId") })
	public boolean updateDynamicTemp(Long tempId, String userName, Short type, String content) {
		if (AppUtils.isBlank(tempId) || AppUtils.isBlank(userName)) {
			return false;
		}
		List<DynamicTemp> temps = findByHQL("from DynamicTemp where id = ? and userName = ?", tempId, userName);
		if (AppUtils.isBlank(temps)) {
			return false;
		}
		DynamicTemp temp = temps.get(0);
		temp.setContent(content);
		update(temp);
		return true;
	}

	@Override
	@Caching(evict = {
			@CacheEvict(value = "ProductDetail", key = "#prodId"),
			@CacheEvict(value = "Product", key = "#prodId") })
	public boolean deleteDynamicTemp(Long tempId, String userName) {
		DynamicTemp temp = findUniqueBy("from DynamicTemp where id = ?  and userName = ?",
				DynamicTemp.class, tempId, userName);
		if (temp != null) {
			delete(temp);
		}
		return true;
	}
	
	@Override
	public  String saveProdItem(List<String> idList, List<String> nameList, Long prodId, String userName){
		List<RelProduct> list = find("from RelProduct n where n.prodId = ? and userName = ?", prodId, userName);
		// delete all
		deleteAll(list);
		if (AppUtils.isNotBlank(idList)) {
			for (int i = 0; i < idList.size(); i++) {
				RelProduct rprod = new RelProduct();
				rprod.setAddtime(new Date());
				rprod.setProdId(prodId);
				rprod.setRelProdId(Long.valueOf(idList.get(i)));
				rprod.setRelProdName(nameList.get(i));
				rprod.setUserName(userName);
				save(rprod);
			}

		}
		return null;
	}
	
	
	@Override
	public String getProdParameter(Long prodId){
		return findUniqueBy("select prod.parameter from Product prod where prod.prodId = ?", String.class, prodId);
	}
	
    @Override
	public  List<RelProduct> getReleationProd(Long prodId, String userName){
    	return find("from RelProduct n where n.prodId = ? and userName = ?", prodId,userName);
    }
}
