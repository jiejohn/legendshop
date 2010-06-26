package com.done.service;

import java.util.List;

import bingosoft.jcf.dao.support.CriteriaQuery;
import bingosoft.jcf.dao.support.PageSupport;
import bingosoft.jcf.util.AppUtils;

import com.done.dao.NewsDao;
import com.done.model.News;

/**
 * @author He-WenQiang. Create in 2009-10-09 09:48:47.
 * 
 */
public class NewsService {
    private NewsDao baseDao;

    public void setBaseDao(NewsDao baseDao) {
        this.baseDao = baseDao;
    }

    public List<News> list(String userName) {
        return baseDao.findByHQL("from News where userName = ?", new Object[] { userName });
    }

    public News load(Integer id) {
        return baseDao.get(News.class, id);
    }

    public News load(Integer id, String userName) {
        List<News> list = baseDao.getHibernateTemplate().find("from News where newsId = ? and userName = ?", id,
                userName);
        if (AppUtils.isBlank(list)) {
            throw new RuntimeException("no record");
        }
        return list.get(0);
    }

    public void delete(Integer id) {
        baseDao.deleteById(News.class, id);
    }

    public Integer save(News news) {
        if (!AppUtils.isBlank(news.getNewsId())) {
            News entity = baseDao.get(News.class, news.getNewsId());
            if (entity != null) {
                news.setUserId(entity.getUserId());
                news.setUserName(entity.getUserName());
                update(news);
                return news.getNewsId();
            }
            return null;
        }
        return (Integer) baseDao.save(news);
    }

    public void update(News news) {
        baseDao.update(news);
    }

    public PageSupport getDataByCriteriaQuery(CriteriaQuery cq) {
        return baseDao.find(cq);
    }
}
