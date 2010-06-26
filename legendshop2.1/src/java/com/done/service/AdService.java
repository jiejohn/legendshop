package com.done.service;

import java.util.List;

import bingosoft.jcf.dao.support.CriteriaQuery;
import bingosoft.jcf.dao.support.PageSupport;
import bingosoft.jcf.util.AppUtils;

import com.done.dao.AdDao;
import com.done.model.Ad;

/**
 * @author He-WenQiang. Create in 2009-10-08 20:40:06.
 * 
 */
public class AdService {
    private AdDao baseDao;

    public void setBaseDao(AdDao baseDao) {
        this.baseDao = baseDao;
    }

    public List<Ad> list(String userName) {
        return baseDao.findByHQL("from Ad where userName = ?", new Object[] { userName });
    }

    public Ad load(Integer id) {
        return baseDao.get(Ad.class, id);
    }

    public Ad load(Integer id, String userName) {
        List<Ad> list = baseDao.getHibernateTemplate().find("from Ad where id = ? and userName = ?", id, userName);
        if (AppUtils.isBlank(list)) {
            throw new RuntimeException("no record");
        }
        return list.get(0);
    }

    public void delete(Integer id) {
        baseDao.deleteById(Ad.class, id);
    }

    public Integer save(Ad ad) {
        if (!AppUtils.isBlank(ad.getId())) {
            Ad entity = baseDao.get(Ad.class, ad.getId());
            if (entity != null) {
                entity.setUrl(ad.getUrl());
                entity.setWordlink(ad.getWordlink());
                entity.setContent(ad.getContent());
                entity.setBs(ad.getBs());
                update(entity);
                return ad.getId();
            }
            return null;
        }
        return (Integer) baseDao.save(ad);
    }

    public void update(Ad ad) {
        baseDao.update(ad);
    }

    public PageSupport getDataByCriteriaQuery(CriteriaQuery cq) {
        return baseDao.find(cq);
    }
}
