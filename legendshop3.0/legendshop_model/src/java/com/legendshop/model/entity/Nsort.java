/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.model.entity;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */

public class Nsort implements java.io.Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2161524693470603026L;

	/** The nsort id. */
	private Long nsortId;

	/** The nsort name. */
	private String nsortName;

	/** The sort id. */
	private Long sortId;
	// 父节点
	/** The parent nsort id. */
	private Long parentNsortId;

	/** The parent. */
	private Nsort parent;

	/** The seq. */
	private Integer seq;
	
	private Integer sortDeputy;
	// 子节点
	/** The sub sort. */
	Set<Nsort> subSort = new TreeSet<Nsort>(new Comparator<Nsort>() {
		public int compare(Nsort o1, Nsort o2) {
			if (o1 == null || o2 == null || o1.getSeq() == null || o2.getSeq() == null) {
				return -1;
			}else if (o1.getSeq() < o2.getSeq()) {
				return -1;
			} else if (o1.getSeq() == o2.getSeq()) {
				return 0;
			} else {
				return 1;
			}
		}
	});

	// Constructors

	/**
	 * default constructor.
	 */
	public Nsort() {
	}

	/**
	 * minimal constructor.
	 * 
	 * @param nsortId
	 *            the nsort id
	 */
	public Nsort(Long nsortId) {
		this.nsortId = nsortId;
	}

	/**
	 * full constructor.
	 * 
	 * @param nsortId
	 *            the nsort id
	 * @param nsortName
	 *            the nsort name
	 * @param sortId
	 *            the sort id
	 */
	public Nsort(Long nsortId, String nsortName, Long sortId,Integer sortDeputy) {
		this.nsortId = nsortId;
		this.nsortName = nsortName;
		this.sortId = sortId;
		this.sortDeputy=sortDeputy;
	}

	// Property accessors

	/**
	 * Gets the nsort id.
	 * 
	 * @return the nsort id
	 */
	public Long getNsortId() {
		return this.nsortId;
	}

	/**
	 * Sets the nsort id.
	 * 
	 * @param nsortId
	 *            the new nsort id
	 */
	public void setNsortId(Long nsortId) {
		this.nsortId = nsortId;
	}

	/**
	 * Gets the nsort name.
	 * 
	 * @return the nsort name
	 */
	public String getNsortName() {
		return this.nsortName;
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
	 * Gets the parent nsort id.
	 * 
	 * @return the parent nsort id
	 */
	public Long getParentNsortId() {
		return parentNsortId;
	}

	/**
	 * Sets the parent nsort id.
	 * 
	 * @param parentNsortId
	 *            the new parent nsort id
	 */
	public void setParentNsortId(Long parentNsortId) {
		this.parentNsortId = parentNsortId;
	}

	/**
	 * Gets the sub sort.
	 * 
	 * @return the sub sort
	 */
	public Set<Nsort> getSubSort() {
		return subSort;
	}

	/**
	 * Sets the sub sort.
	 * 
	 * @param subSort
	 *            the new sub sort
	 */
	public void setSubSort(Set<Nsort> subSort) {
		this.subSort = subSort;
	}

	/**
	 * Adds the sub sort.
	 * 
	 * @param nsort
	 *            the nsort
	 */
	public void addSubSort(Nsort nsort) {
		if (this.getNsortId().equals(nsort.getParentNsortId())) {
			subSort.add(nsort);
		}
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

	/**
	 * Gets the parent.
	 * 
	 * @return the parent
	 */
	public Nsort getParent() {
		return parent;
	}

	/**
	 * Sets the parent.
	 * 
	 * @param parent
	 *            the new parent
	 */
	public void setParent(Nsort parent) {
		this.parent = parent;
	}

	public Integer getSortDeputy() {
		return sortDeputy;
	}

	public void setSortDeputy(Integer sortDeputy) {
		this.sortDeputy = sortDeputy;
	}

}