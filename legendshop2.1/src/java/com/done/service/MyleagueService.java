package com.done.service;

import java.util.List;

import bingosoft.jcf.dao.support.CriteriaQuery;
import bingosoft.jcf.dao.support.PageSupport;
import bingosoft.jcf.util.AppUtils;
import com.done.dao.MyleagueDao;
import com.done.model.Myleague;

/**
 * @author He-WenQiang. Create in 2010-05-17 21:19:36.
 *
 */
public class MyleagueService  {
    private MyleagueDao baseDao;

    public void setBaseDao(MyleagueDao baseDao) {
        this.baseDao = baseDao;
    }

    public List<Myleague> list(String userName) {
        return baseDao.findByHQL("from Myleague where userName = ?", new Object[] { userName });
    }
    

    public Myleague load(Integer id) {
        return baseDao.get(Myleague.class, id);
    }

    public void delete(Integer id) {
        baseDao.deleteById(Myleague.class, id);
    }

    public Integer save(Myleague myleague) {
        if (!AppUtils.isBlank(myleague.getId())) {
            update(myleague);
            return myleague.getId();
        }
        return (Integer) baseDao.save(myleague);
    }

    public void update(Myleague myleague) {
        baseDao.update(myleague);
    }

    public PageSupport getDataByCriteriaQuery(CriteriaQuery cq) {
        return baseDao.find(cq);
    }
}

