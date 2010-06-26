package com.done.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bingosoft.jcf.dao.support.CriteriaQuery;
import bingosoft.jcf.dao.support.PageSupport;
import bingosoft.jcf.util.AppUtils;

import com.done.dao.HotsearchDao;
import com.done.model.Hotsearch;

/**
 * @author He-WenQiang. Create in 2009-10-11 00:21:44.
 * 
 */
public class HotsearchService {
    Logger log = LoggerFactory.getLogger(HotsearchService.class);
    private HotsearchDao baseDao;

    public void setBaseDao(HotsearchDao baseDao) {
        this.baseDao = baseDao;
    }

    public List<Hotsearch> list(String userName) {
        return baseDao.findByHQL("from Hotsearch where userName = ?", new Object[] { userName });
    }

    public Hotsearch load(Integer id) {
        return baseDao.get(Hotsearch.class, id);
    }

    public Hotsearch load(Integer id, String userName) {
        List<Hotsearch> list = baseDao.getHibernateTemplate().find("from Hotsearch where id = ? and userName = ?", id,
                userName);
        if (AppUtils.isBlank(list)) {
            throw new RuntimeException("no record");
        }
        return list.get(0);
    }

    public void delete(Integer id) {
        baseDao.deleteById(Hotsearch.class, id);
    }

    public Integer save(Hotsearch hotsearch, String userName, boolean viewAllDataFunction) {
        if (!AppUtils.isBlank(hotsearch.getId())) {
            Hotsearch entity = baseDao.get(Hotsearch.class, hotsearch.getId());
            if (entity != null) {
                if (!viewAllDataFunction && !userName.equals(entity.getUserName())) {
                    throw new RuntimeException("Can't edit Hotsearch does not own to you!");
                }
                entity.setDate(new Date());
                entity.setMsg(hotsearch.getMsg());
                entity.setTitle(hotsearch.getTitle());
                update(entity);
                return hotsearch.getId();
            }
            return null;
        }
        return (Integer) baseDao.save(hotsearch);
    }

    public void update(Hotsearch hotsearch) {
        baseDao.update(hotsearch);
    }

    public PageSupport getDataByCriteriaQuery(CriteriaQuery cq) {
        return baseDao.find(cq);
    }
}
