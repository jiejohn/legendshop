/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.spi.service;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.DynamicTemp;
import com.legendshop.model.entity.Product;
import com.legendshop.model.entity.ProductDetail;
import com.legendshop.model.entity.RelProduct;

public interface ProductService extends BaseService{

	/**
	 * Find product.
	 * 
	 * @param hql
	 *            the hql
	 * @return the page support 
	 */
	public abstract PageSupport getProductList(HqlQuery hql);

	/**
	 * Find product.
	 * 
	 * @param cq
	 *            the cq
	 * @return the page support
	 */
	public abstract PageSupport getProductList(CriteriaQuery cq);

	/**
	 * 根据ID获取一个产品.
	 * 
	 * @param prodId
	 *            the prod id
	 * @return the product
	 */
	public abstract Product getProductById(Long prodId);

	/**
	 * 更新产品.
	 * 
	 * @param product
	 *            the product
	 */
	public abstract void updateProduct(Product product, Product origin);

	/**
	 * 保存产品.
	 * 
	 * @param product
	 *            the product
	 * @return 产品ID
	 */
	public abstract Long saveProduct(Product product,String prodType);
	
	// 商品动态属性
	/**
	 * Load attributeprod attribute.
	 * 
	 * @param prodId
	 *            the prod id
	 * @return the string
	 */
	public abstract String getAttributeprodAttribute(Long prodId);
	
	// 得到商品
	/**
	 * Load prod.
	 * 
	 * @param prodId
	 *            the prod id
	 * @param userName
	 *            the user name
	 * @return the product
	 */
	public abstract Product getProd(Long prodId, String userName);
	
	// 商品动态参数
	/**
	 * Load prod parameter.
	 * 
	 * @param prodId
	 *            the prod id
	 * @return the string
	 */
	public abstract String getProdParameter(Long prodId);
	




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
	 * 更新产品动态模板.
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
	 * Load dynamic temp.
	 * 
	 * @param tempId
	 *            the temp id
	 * @param userName
	 *            the user name
	 * @return the dynamic temp
	 */
	public abstract DynamicTemp getDynamicTemp(Long tempId, String userName);

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
	 * 商品上线.
	 * 
	 * @param prodId
	 *            商品Id
	 * @return true, if successful
	 */
	public abstract boolean updateProdOnline(Long prodId);

	/**
	 * 商品下线.
	 * 
	 * @param prodId
	 *            商品Id
	 * @return true, if successful
	 */
	public abstract boolean updateProdOffline(Long prodId);
	



	/**
	 * 更新商品.
	 * 
	 * @param product
	 *            the product
	 */
	public abstract void updateProd(Product product);
	
    /**
     * Gets the prod detail.
     *
     * @param prodId
     *            the prod id
     * @return the prod detail
     */
    public abstract ProductDetail getProdDetail(final Long prodId);
    
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
    

    // 更新商品查看数
    /**
     * Update prod views.
     *
     * @param prodId
     *            the prod id
     */
    public abstract void updateProdViews(Long prodId);
    
    /**
     * hot sell product
     * @param shopName
     * @return
     */
    public List<Product> getHotSale(String shopName);
    
    public List<Product> getHotOn(String sortId);

	public abstract List<Product> getHotViewProd(String shopName, int maxNum);
	
    public abstract PageSupport getProdDetail(Locale locale, String curPageNO, Long sortId);
    
	/**
	 * Product gallery.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param actionForm
	 *            the action form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the action forward
	 */
	public abstract String getProductGallery(HttpServletRequest request, HttpServletResponse response, Long prodId);
	

}