/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.model.entity;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

/**
 * 产品分类，第一级分类.
 */
public class Sort extends UploadFile implements BaseEntity {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6590849925688867352L;

	/** The sort id. */
	private Long sortId;

	/** The sort name. */
	private String sortName;

	/** The picture. */
	private String picture;

	/** The user id. */
	private String userId;
	
	/** The sort type. */
	private String sortType;

	/** The user name. */
	private String userName;

	/** The seq. */
	private Integer seq;

	/** 0:normal, 1:show in header menu  */
	private Integer headerMenu;
	
	/** 0:normal, 1:show in navigation menu  */
	private Integer navigationMenu;
	
	/** 按seq字段排序. */
	Set<Nsort> nsort = new TreeSet<Nsort>(new NsortComparator());
	

	// Constructors

	/**
	 * default constructor.
	 */
	public Sort() {
	}

	/**
	 * minimal constructor.
	 * 
	 * @param sortId
	 *            the sort id
	 */
	public Sort(Long sortId) {
		this.sortId = sortId;
	}

	/**
	 * full constructor.
	 * 
	 * @param sortId
	 *            the sort id
	 * @param sortName
	 *            the sort name
	 * @param picture
	 *            the picture
	 * @param userId
	 *            the user id
	 * @param userName
	 *            the user name            
	 */
	public Sort(Long sortId, String sortName, String picture, String userId,
			String userName) {
		this.sortId = sortId;
		this.sortName = sortName;
		this.picture = picture;
		this.userId = userId;
		this.userName = userName;
	}

	// Property accessors

	/**
	 * Gets the sort id.
	 * 
	 * @return the sort id
	 */
	public Long getSortId() {
		return this.sortId;
	}

	/**
	 * Sets the sort id.
	 * 
	 * @param sortId
	 *            the new sort id
	 */
	public void setSortId(Long sortId) {
		this.sortId = sortId;
	}

	/**
	 * Gets the sort name.
	 * 
	 * @return the sort name
	 */
	public String getSortName() {
		return this.sortName;
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
	 * Gets the picture.
	 * 
	 * @return the picture
	 */
	public String getPicture() {
		return this.picture;
	}

	/**
	 * Sets the picture.
	 * 
	 * @param picture
	 *            the new picture
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}

	/**
	 * Gets the user id.
	 * 
	 * @return the user id
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * Sets the user id.
	 * 
	 * @param userId
	 *            the new user id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Gets the user name.
	 * 
	 * @return the user name
	 */
	public String getUserName() {
		return this.userName;
	}

	/**
	 * Sets the user name.
	 * 
	 * @param userName
	 *            the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets the nsort.
	 * 
	 * @return the nsort
	 */
	public Set<Nsort> getNsort() {
		return nsort;
	}

	/**
	 * Sets the nsort.
	 * 
	 * @param nsort
	 *            the new nsort
	 */
	public void setNsort(Set<Nsort> nsort) {
		this.nsort = nsort;
	}

	/**
	 * Gets the seq.
	 * 
	 * @return the seq
	 */
	public Integer getSeq() {
		return seq;
	}

	/**
	 * Sets the seq.
	 * 
	 * @param seq
	 *            the new seq
	 */
	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	// 增加二级菜单
	/**
	 * Adds the sub sort.
	 * 
	 * @param n
	 *            the n
	 */
	public void addSubSort(Nsort n) {
		if (this.getSortId().equals(n.getSortId())
				&& n.getParentNsortId() == null) {
			nsort.add(n);
		}
	}

	/**
	 * Gets the sort type.
	 * 
	 * @return the sort type
	 */
	public String getSortType() {
		return sortType;
	}

	/**
	 * Sets the sort type.
	 * 
	 * @param sortType
	 *            the new sort type
	 */
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public Integer getHeaderMenu() {
		return headerMenu;
	}

	public void setHeaderMenu(Integer headerMenu) {
		this.headerMenu = headerMenu;
	}

	public Integer getNavigationMenu() {
		return navigationMenu;
	}

	public void setNavigationMenu(Integer navigationMenu) {
		this.navigationMenu = navigationMenu;
	}

	public Serializable getId() {
		return sortId;
	}
	


}