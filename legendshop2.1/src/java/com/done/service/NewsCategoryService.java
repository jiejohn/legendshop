package com.done.service;

import java.util.List;

import bingosoft.jcf.dao.support.CriteriaQuery;
import bingosoft.jcf.dao.support.PageSupport;
import bingosoft.jcf.util.AppUtils;
import com.done.dao.NewsCategoryDao;
import com.done.model.NewsCategory;

/**
 * @author He-WenQiang. Create in 2010-06-25 22:17:19.
 *
 */
public class NewsCategoryService  {
    private NewsCategoryDao baseDao;

    public void setBaseDao(NewsCategoryDao baseDao) {
        this.baseDao = baseDao;
    }

    public List<NewsCategory> list(String userName) {
        return baseDao.findByHQL("from NewsCategory where userName = ?", new Object[] { userName });
    }

    public NewsCategory load(Integer id) {
        return baseDao.get(NewsCategory.class, id);
    }

    public void delete(Integer id) {
        baseDao.deleteById(NewsCategory.class, id);
    }

    public Integer save(NewsCategory newsCategory) {
        if (!AppUtils.isBlank(newsCategory.getNewsCategoryId())) {
            update(newsCategory);
            return newsCategory.getNewsCategoryId();
        }
        return (Integer) baseDao.save(newsCategory);
    }

    public void update(NewsCategory newsCategory) {
        baseDao.update(newsCategory);
    }

    public PageSupport getDataByCriteriaQuery(CriteriaQuery cq) {
        return baseDao.find(cq);
    }
}

