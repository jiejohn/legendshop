/*
 *
 * LegendShop 多用户商城系统
 *
 *  版权所有,并保留所有权利。
 *
 */
package com.legendshop.business.dao;

import java.util.List;
import java.util.Locale;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.dynamic.Model;
import com.legendshop.model.entity.DynamicTemp;
import com.legendshop.model.entity.Product;
import com.legendshop.model.entity.ProductDetail;
import com.legendshop.model.entity.RelProduct;

/**
 * The Interface ProductDao.
 */
public interface ProductDao extends BaseDao{

    /**
     * Gets the commend prod.
     *
     * @param shopName
     *            the shop name
     * @param total
     *            the total
     * @return the commend prod
     */
    @SuppressWarnings("unchecked")
    public abstract List<Product> getCommendProd(final String shopName, final int total);

    /**
     * Gets the releation prod.
     *
     * @param shopName
     *            the shop name
     * @param prodId
     *            the prod id
     * @param total
     *            the total
     * @return the releation prod
     */
    public abstract List<Product> getReleationProd(final String shopName, final Long prodId, final int total);
    
    
    /**
	 * Gets the releation prod.
	 * 
	 * @param prodId
	 *            the prod id
	 * @param userName
	 *            the user name
	 * @return the releation prod
	 */
    public abstract List<RelProduct> getReleationProd(Long prodId, String userName);

    /**
     * Gets the newest prod.
     *
     * @param shopName
     *            the shop name
     * @param total
     *            the total
     * @return the newest prod
     */
    public abstract List<Product> getNewestProd(final String shopName, final int total);

    /**
     * Gets the hotsale.
     *
     * @param shopName
     *            the shopName
     * @return the hotsale
     */
    public abstract List<Product> gethotsale(final String shopName);

    /**
     * Gets the prod detail.
     *
     * @param locale
     *            the locale
     * @param curPageNO
     *            the cur page no
     * @param sortId
     *            the sort id
     * @param nsortId
     *            the nsort id
     * @param subNsortId
     *            the sub nsort id
     * @return the prod detail
     */
    public abstract PageSupport getProdDetail(Locale locale, String curPageNO, Long sortId,Long nsortId,Long subNsortId);

    /**
     * Gets the prod detail.
     *
     * @param locale
     *            the locale
     * @param curPageNO
     *            the cur page no
     * @param sortId
     *            the sort id
     * @return the prod detail
     */
    public abstract PageSupport getProdDetail(Locale locale, String curPageNO, Long sortId);

    /**
     * Gets the prod detail.
     *
     * @param prodId
     *            the prod id
     * @return the prod detail
     */
    public abstract ProductDetail getProdDetail(final Long prodId);

    /**
     * Gets the search prod detail.
     *
     * @param cq
     *            the cq
     * @return the search prod detail
     */
    public abstract PageSupport getProdDetail(CriteriaQuery cq);


    /**
     * Gets the prod detail.
     *
     * @param prodId
     *            the prod id
     * @return the prod detail
     */
    public abstract List<ProductDetail> getProdDetail(final Long[] prodId);

    // 热门商品
    /**
     * Gets the hot on.
     *
     * @param sortId
     *            the sort id
     * @return the hot on
     */
    public abstract List<Product> getHotOn(String sortId);

    // 查看某个商城的热门商品
    /**
     * Gets the hot view prod.
     *
     * @param shopName
     *            the shop name
     * @param number
     *            the number
     * @return the hot view prod
     */
    public abstract List<Product> getHotViewProd(String shopName, Integer number);

    /**
     * 根据ID获取一个产品.
     *
     * @param id
     *            the id
     * @return the product
     */
    public abstract Product getProduct(Long id);

    // 更新商品查看数
    /**
     * Update prod views.
     *
     * @param prodId
     *            the prod id
     */
    public abstract void updateProdViews(Long prodId);

    /**
     * 更新产品.
     *
     * @param product
     *            the product
     */
    public abstract void updateProduct(Product product);

    /**
     * Save product.
     *
     * @param product
     *            the product
     * @return the long
     */
    public abstract Long saveProduct(Product product);

    /**
     * Delete prod by id.
     *
     * @param prodId
     *            the prod id
     */
    public abstract void deleteProd(Product product);

    /**
     * Update prod online.
     *
     * @param prodId
     *            the prod id
     * @return true, if successful
     */
    public abstract boolean updateProdOnline(Long prodId) ;

    /**
     * Update prod offline.
     *
     * @param prodId
     *            the prod id
     * @return true, if successful
     */
    public abstract boolean updateProdOffline(Long prodId);

    /**
     * Load dynamic attribute.
     *
     * @param prodId
     *            the prod id
     * @param userName
     *            the user name
     * @return the list
     */
    public abstract List<Model> loadDynamicAttribute(Long prodId, String userName);

    /**
     * Gets the prod.
     *
     * @param prodId
     *            the prod id
     * @param userName
     *            the user name
     * @return the prod
     */
    public abstract Product getProd(Long prodId, String userName);

    /**
     * Gets the dynamic temp.
     *
     * @param tempId
     *            the temp id
     * @param userName
     *            the user name
     * @return the dynamic temp
     */
    public abstract DynamicTemp getDynamicTemp(Long tempId, String userName);

    /**
	 * Save dynamic temp.
	 * 
	 * @param tempName
	 *            the temp name
	 * @param userName
	 *            the user name
	 * @param type
	 *            the type
	 * @param content
	 *            the content
	 * @return true, if successful
	 */
    public abstract boolean saveDynamicTemp(String tempName, String userName, Short type, String content);

    /**
	 * Update dynamic temp.
	 * 
	 * @param tempId
	 *            the temp id
	 * @param userName
	 *            the user name
	 * @param type
	 *            the type
	 * @param content
	 *            the content
	 * @return true, if successful
	 */
    public abstract boolean updateDynamicTemp(Long tempId, String userName, Short type, String content);

	/**
	 * Delete dynamic temp.
	 * 
	 * @param tempId
	 *            the temp id
	 * @param userName
	 *            the user name
	 * @return true, if successful
	 */
	public abstract boolean deleteDynamicTemp(Long tempId, String userName);
	
	/**
	 * Save prod item.
	 * 
	 * @param idList
	 *            the id list
	 * @param nameList
	 *            the name list
	 * @param prodId
	 *            the prod id
	 * @param userName
	 *            the user name
	 * @return the string
	 */
	public abstract String saveProdItem(List<String> idList, List<String> nameList, Long prodId, String userName);

	/**
	 * Gets the prod parameter.
	 * 
	 * @param prodId
	 *            the prod id
	 * @return the prod parameter
	 */
	public abstract String getProdParameter(Long prodId);

}
