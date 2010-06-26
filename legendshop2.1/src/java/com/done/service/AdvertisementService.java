package com.done.service;

import java.util.List;

import bingosoft.jcf.dao.support.CriteriaQuery;
import bingosoft.jcf.dao.support.PageSupport;
import bingosoft.jcf.util.AppUtils;

import com.done.dao.AdvertisementDao;
import com.done.model.Advertisement;

/**
 * @author He-WenQiang. Create in 2009-11-12 20:48:45.
 * 
 */
public class AdvertisementService {
    private AdvertisementDao baseDao;

    public void setBaseDao(AdvertisementDao baseDao) {
        this.baseDao = baseDao;
    }

    public List<Advertisement> list(String userName) {
        return baseDao.findByHQL("from Advertisement where userName = ?", new Object[] { userName });
    }

    public Advertisement load(Integer id) {
        return baseDao.get(Advertisement.class, id);
    }

    public Advertisement load(String userName, String type) {
        List<Advertisement> list = baseDao.findByHQL("from Advertisement where userName = ? and type = ?",
                new Object[] { userName, type });
        if (AppUtils.isBlank(list)) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public void delete(Integer id) {
        baseDao.deleteById(Advertisement.class, id);
    }

    public Integer save(Advertisement advertisement) {
        if (!AppUtils.isBlank(advertisement.getId())) {
            update(advertisement);
            return advertisement.getId();
        }
        return (Integer) baseDao.save(advertisement);
    }

    public void update(Advertisement advertisement) {
        baseDao.update(advertisement);
    }

    public PageSupport getDataByCriteriaQuery(CriteriaQuery cq) {
        return baseDao.find(cq);
    }
}
