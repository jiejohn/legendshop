/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.model.entity;

import java.io.Serializable;
import java.util.Comparator;

/**
 * The Class NsortComparator.
 */
public 	class NsortComparator implements Comparator<Nsort>,Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7171985873166798645L;

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Nsort o1, Nsort o2) {
		if (o1 == null || o2 == null || o1.getSeq() == null || o2.getSeq() == null) {
			return -1;
		} else if (o1.getSeq() < o2.getSeq()) {
			return -1;
		} else if (o1.getSeq() == o2.getSeq()) {
			return 0;
		} else {
			return 1;
		}
	}
}