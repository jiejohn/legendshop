package com.done.service;

import java.util.List;

import bingosoft.jcf.dao.support.CriteriaQuery;
import bingosoft.jcf.dao.support.HqlQuery;
import bingosoft.jcf.dao.support.PageSupport;
import bingosoft.jcf.util.AppUtils;

import com.done.dao.NsortDao;
import com.done.model.Nsort;
import com.done.model.Sort;

/**
 * @author He-WenQiang. Create in 2009-11-18 22:45:39.
 * 
 */
public class NsortService {
    private NsortDao baseDao;

    public void setBaseDao(NsortDao baseDao) {
        this.baseDao = baseDao;
    }

    public List<Nsort> list(String userName) {
        return baseDao.findByHQL("from Nsort where userName = ?", new Object[] { userName });
    }

    //parentNsortId is not null ：3级分类
    public List<Nsort> listBySort(Integer sortId) {
        return baseDao.findByHQL("from Nsort where sortId = ? and parentNsortId is not null", new Object[] { sortId });
    }

    public List<Nsort> hasChildNsort(Integer id) {
        return baseDao.findByHQL("from Nsort where parentNsortId = ?", new Object[] { id });
    }

    public Nsort load(Integer id) {
        return baseDao.get(Nsort.class, id);
    }

    public Sort loadSort(Integer id) {
        return baseDao.get(Sort.class, id);
    }

    public void delete(Integer id) {
        baseDao.deleteById(Nsort.class, id);
    }

    public Integer save(Nsort nsort) {
        if (!AppUtils.isBlank(nsort.getNsortId())) {
            update(nsort);
            return nsort.getNsortId();
        }
        return (Integer) baseDao.save(nsort);
    }

    public void update(Nsort nsort) {
        baseDao.update(nsort);
    }

    public PageSupport getDataByCriteriaQuery(CriteriaQuery cq) {
        return baseDao.find(cq);
    }

    public PageSupport getDataByCriteriaQuery(HqlQuery hql) {
        return baseDao.find(hql);
    }
}
