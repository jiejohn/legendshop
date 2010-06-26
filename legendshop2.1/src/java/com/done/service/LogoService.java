package com.done.service;

import java.util.List;

import com.done.model.Logo;

import bingosoft.jcf.dao.cache.BaseDao;
import bingosoft.jcf.dao.support.CriteriaQuery;
import bingosoft.jcf.dao.support.PageSupport;
import bingosoft.jcf.util.AppUtils;

public class LogoService {
    private BaseDao baseDao;

    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    public List<Logo> list(String userName) {
        return baseDao.findByHQL("from Logo where userName = ?", new Object[] { userName });
    }

    public Logo load(Integer id) {
        return baseDao.get(Logo.class, id);
    }

    public Logo load(Integer id, String userName) {
        List<Logo> list = baseDao.getHibernateTemplate().find("from Logo where id = ? and userName = ?", id, userName);
        if (AppUtils.isBlank(list)) {
            throw new RuntimeException("no record");
        }
        return list.get(0);
    }

    public void delete(Integer id) {
        baseDao.deleteById(Logo.class, id);
    }

    public Integer save(Logo logo) {
        if (logo.getId() != null) {
            update(logo);
            return logo.getId();
        }
        return (Integer) baseDao.save(logo);
    }

    public void update(Logo logo) {
        baseDao.update(logo);
    }

    public PageSupport getDataByCriteriaQuery(CriteriaQuery cq) {
        return baseDao.find(cq);
    }
}
