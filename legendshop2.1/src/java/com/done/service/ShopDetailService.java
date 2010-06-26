package com.done.service;

import java.util.List;

import bingosoft.jcf.dao.support.CriteriaQuery;
import bingosoft.jcf.dao.support.PageSupport;
import bingosoft.jcf.util.AppUtils;

import com.done.dao.ShopDetailDao;
import com.done.model.ShopDetail;

/**
 * @author He-WenQiang. Create in 2009-10-07 19:54:19.
 * 
 */
public class ShopDetailService {
    private ShopDetailDao baseDao;

    public void setBaseDao(ShopDetailDao baseDao) {
        this.baseDao = baseDao;
    }

    public List<ShopDetail> list(String userName) {
        return baseDao.findByHQL("from ShopDetail where userName = ?", new Object[] { userName });
    }

    public ShopDetail load(String id) {
        return baseDao.get(ShopDetail.class, id);
    }

    public void delete(String id) {
        baseDao.deleteById(ShopDetail.class, id);
    }

    //UserId 一定不能为空
    public String save(ShopDetail shopDetail) {
        if (!AppUtils.isBlank(shopDetail.getUserId())) {
            ShopDetail shop = baseDao.get(ShopDetail.class, shopDetail.getUserId());
            if (shop != null) {
                update(shopDetail);
            } else {
                return (String) baseDao.save(shopDetail);
            }
            return shopDetail.getUserId();
        } else {
            throw new RuntimeException("shopDetail does not has userId");
        }
    }

    public void update(ShopDetail shopDetail) {
        baseDao.update(shopDetail);
    }

    public PageSupport getDataByCriteriaQuery(CriteriaQuery cq) {
        return baseDao.find(cq);
    }
}
