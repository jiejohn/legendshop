package com.done.util.page.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;

import com.done.util.page.Page;
import com.done.util.page.PageRequest;

/**
 * @author badqiu
 */
public class CriteriaPage extends Page {

    public CriteriaPage(Criteria criteria, int pageNumber, int pageSize) {
        super(pageNumber, pageSize, queryTotalCount(criteria));
        result = criteria.setFirstResult(getFirstResult()).setMaxResults(this.pageSize).list();
    }

    public CriteriaPage(Criteria criteria, PageRequest p) {
        this(criteria, p.getPageNumber(), p.getPageSize());
    }

    private static int queryTotalCount(Criteria criteria) {
        return ((Integer) (criteria.setProjection(Projections.rowCount()).uniqueResult())).intValue();
    }

}
