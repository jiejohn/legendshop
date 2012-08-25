/**
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.model.entity;

import java.util.List;

/**
 * 产品详细信息表.
 */
public class ProductDetail  extends AbstractProduct {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6275498676062425893L;

    /** The sort name. */
    private String sortName;

    /** The nsort name. */
    private String nsortName;

    /** The sub nsort name. */
    private String subNsortName;

	/** The brand name. */
	private String brandName;

	/** The prod pics. */
	List<ImgFile> prodPics;
	
	/** The rel prods. */
	List<Product> relProds;

	/**
	 * Gets the sort name.
	 * 
	 * @return the sort name
	 */
	public String getSortName() {
		return sortName;
	}

	/**
	 * Sets the sort name.
	 * 
	 * @param sortName
	 *            the new sort name
	 */
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	/**
	 * Gets the nsort name.
	 * 
	 * @return the nsort name
	 */
	public String getNsortName() {
		return nsortName;
	}

	/**
	 * Sets the nsort name.
	 * 
	 * @param nsortName
	 *            the new nsort name
	 */
	public void setNsortName(String nsortName) {
		this.nsortName = nsortName;
	}

	/**
	 * Gets the sub nsort name.
	 * 
	 * @return the sub nsort name
	 */
	public String getSubNsortName() {
		return subNsortName;
	}

	/**
	 * Sets the sub nsort name.
	 * 
	 * @param subNsortName
	 *            the new sub nsort name
	 */
	public void setSubNsortName(String subNsortName) {
		this.subNsortName = subNsortName;
	}

	/**
	 * Gets the brand name.
	 * 
	 * @return the brand name
	 */
	public String getBrandName() {
		return brandName;
	}

	/**
	 * Sets the brand name.
	 * 
	 * @param brandName
	 *            the new brand name
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	/**
	 * Gets the prod pics.
	 * 
	 * @return the prod pics
	 */
	public List<ImgFile> getProdPics() {
		return prodPics;
	}

	/**
	 * Sets the prod pics.
	 * 
	 * @param prodPics
	 *            the new prod pics
	 */
	public void setProdPics(List<ImgFile> prodPics) {
		this.prodPics = prodPics;
	}

	/**
	 * Gets the rel prods.
	 * 
	 * @return the rel prods
	 */
	public List<Product> getRelProds() {
		return relProds;
	}

	/**
	 * Sets the rel prods.
	 * 
	 * @param relProds
	 *            the new rel prods
	 */
	public void setRelProds(List<Product> relProds) {
		this.relProds = relProds;
	}


}