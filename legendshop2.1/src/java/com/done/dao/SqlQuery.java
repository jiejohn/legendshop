package com.done.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *@author HEWQ. 
 *2009-08-20
 */
public class SqlQuery  implements Serializable{
	
	private String curPage=null ;
	private int pageSize=12;
	private String myaction="javascript:pager";
	private String queryString;
	private String allCountString;
	private List params = new ArrayList();
	public SqlQuery(String queryString, String allCountString,List params) {
		this.queryString = queryString;
		this.allCountString = allCountString;
		this.params = params;
	}
	
	public SqlQuery(String myaction) {
		this.myaction = myaction;
	}
	

	public String getCurPage() {
		return curPage;
	}
	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}
	public String getMyaction() {
		return myaction;
	}
	public void setMyaction(String myaction) {
		this.myaction = myaction;
	}

	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getQueryString() {
		return queryString;
	}
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((curPage == null) ? 0 : curPage.hashCode());
		result = PRIME * result + ((myaction == null) ? 0 : myaction.hashCode());
		result = PRIME * result + pageSize;
		result = PRIME * result + ((queryString == null) ? 0 : queryString.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final SqlQuery other = (SqlQuery) obj;
		if (curPage == null) {
			if (other.curPage != null)
				return false;
		} else if (!curPage.equals(other.curPage))
			return false;
		if (myaction == null) {
			if (other.myaction != null)
				return false;
		} else if (!myaction.equals(other.myaction))
			return false;
		if (pageSize != other.pageSize)
			return false;
		if (queryString == null) {
			if (other.queryString != null)
				return false;
		} else if (!queryString.equals(other.queryString))
			return false;
		return true;
	}

	public String getAllCountString() {
		return allCountString;
	}

	public void setAllCountString(String allCountString) {
		this.allCountString = allCountString;
	}

	public List getParams() {
		return params;
	}
	
	public boolean addParam(Object param) {
		return params.add(param);
	}

	public void setParams(List params) {
		this.params = params;
	}

}