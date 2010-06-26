package com.done.service;

import java.util.Date;
import java.util.List;

import bingosoft.jcf.dao.support.CriteriaQuery;
import bingosoft.jcf.dao.support.PageSupport;
import bingosoft.jcf.util.AppUtils;

import com.done.dao.PubDao;
import com.done.model.Pub;

/**
 * @author He-WenQiang. Create in 2009-10-11 00:17:57.
 * 
 */
public class PubService {
    private PubDao baseDao;

    public void setBaseDao(PubDao baseDao) {
        this.baseDao = baseDao;
    }

    public List<Pub> list(String userName) {
        return baseDao.findByHQL("from Pub where userName = ?", new Object[] { userName });
    }

    public Pub load(Integer id) {
        return baseDao.get(Pub.class, id);
    }

    public void delete(Integer id) {
        baseDao.deleteById(Pub.class, id);
    }

    public Integer save(Pub pub, String userName, boolean viewAllDataFunction) {
        if (!AppUtils.isBlank(pub.getId())) {
            Pub entity = baseDao.get(Pub.class, pub.getId());
            if (entity != null) {
                if (!viewAllDataFunction && !userName.equals(entity.getUserName())) {
                    throw new RuntimeException("Cant not edit Pub does not own to you!");
                }
                entity.setDate(new Date());
                entity.setMsg(pub.getMsg());
                entity.setTitle(pub.getTitle());
                update(entity);
                return pub.getId();
            }
            return null;
        }
        return (Integer) baseDao.save(pub);
    }

    public void update(Pub pub) {
        baseDao.update(pub);
    }

    public PageSupport getDataByCriteriaQuery(CriteriaQuery cq) {
        return baseDao.find(cq);
    }
}
