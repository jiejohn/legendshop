package com.done.dao;

import java.math.BigInteger;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import bingosoft.jcf.dao.support.PageSupport;
import bingosoft.jcf.util.AppUtils;
import bingosoft.jcf.util.page.PagerUtil;

/**
 * 专门做分页
 * 
 * @author hewq
 * 
 */
public class SqlPageDao {
    private HibernateTemplate hibernateTemplate;
    private boolean cache = false;

    public PageSupport findBySql(final SqlQuery sqlQuery) {

        return (PageSupport) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createSQLQuery(sqlQuery.getQueryString());
                if (cache) {
                    query.setCacheable(true);
                }
                //得到总行数
                Query allCountQuery = session.createSQLQuery(sqlQuery.getAllCountString());
                if (!AppUtils.isBlank(sqlQuery.getParams())) {
                    for (int i = 0; i < sqlQuery.getParams().size(); i++) {
                        query.setParameter(i, sqlQuery.getParams().get(i));
                        allCountQuery.setParameter(i, sqlQuery.getParams().get(i));

                    }
                }
                long allCounts = ((BigInteger) allCountQuery.list().get(0)).longValue();
                int curPageNO = PagerUtil.getCurPageNO(sqlQuery.getCurPage());
                int offset = PagerUtil.getOffset(allCounts, curPageNO, sqlQuery.getPageSize());
                query.setFirstResult(offset);
                query.setMaxResults(sqlQuery.getPageSize());
                return new PageSupport(query.list(), sqlQuery.getMyaction(), offset, curPageNO, allCounts, sqlQuery
                        .getPageSize());
            }
        });
    }

    public void setCache(boolean cache) {
        this.cache = cache;
    }

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
}
