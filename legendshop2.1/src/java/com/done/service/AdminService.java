package com.done.service;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import bingosoft.jcf.dao.cache.BaseDao;
import bingosoft.jcf.dao.support.CriteriaQuery;
import bingosoft.jcf.dao.support.HqlQuery;
import bingosoft.jcf.dao.support.PageSupport;
import bingosoft.jcf.util.AppUtils;

import com.done.common.Constants;
import com.done.dao.SqlPageDao;
import com.done.dao.SqlQuery;
import com.done.model.Hw;
import com.done.model.Indexjpg;
import com.done.model.News;
import com.done.model.Nsort;
import com.done.model.Sort;
import com.done.model.TUser;
import com.done.model.UserDetail;
import com.done.model.UserRole;

/**
 * 后台服务类
 * 
 * @author hewq 2009-07-14
 * 
 */
public class AdminService {
    private static Logger log = LoggerFactory.getLogger(AdminService.class);
    private JdbcTemplate jdbcTemplate;

    private BaseDao baseDao;

    private SqlPageDao sqlPageDao;

    public BaseDao getBaseDao() {
        return baseDao;
    }

    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 保存新闻
     * 
     * @param news
     */
    public void saveNews(News news) {
        baseDao.saveOrUpdate(news);
    }

    /**
     * 得到新闻
     * 
     * @param newsId
     * @return
     */
    public News getNewsById(Integer newsId) {
        return baseDao.get(News.class, newsId);
    }

    /**
     * 分页查询
     * 
     * @param cq
     * @return
     */
    public PageSupport getDataByCQ(CriteriaQuery cq) {
        return baseDao.find(cq, true);
    }

    /**
     * 分页查询,采用post的方式来处理工具条
     * 
     * @param cq
     * @return
     */
    public PageSupport getDataByCriteriaQuery(CriteriaQuery cq) {
        return baseDao.find(cq);
    }

    public void deleteSort(Integer sortId) {
    	List list = baseDao.findByHQL("from Hw where sortId = ?", new Object[]{sortId});
    	if(!AppUtils.isBlank(list)){
    		throw new RuntimeException("请删除该类型对应的产品");
    	}
        baseDao.deleteById(Sort.class, sortId);
    }

    /**
     * 得到Sort
     * 
     * @param sortId
     * @return
     */
    public Sort getSort(Integer sortId) {
        return baseDao.get(Sort.class, sortId);
    }

    public Indexjpg getIndexJpg(Integer id) {
        return baseDao.get(Indexjpg.class, id);
    }

    /**
     * 保存Sort
     * 
     * @param sort
     */
    public void saveSort(Sort sort) {
        baseDao.saveOrUpdate(sort);
    }

    public void saveNsort(Nsort nsort) {
        baseDao.saveOrUpdate(nsort);
    }

    public void deleteNsort(Integer nsortId) {
        baseDao.deleteById(Nsort.class, nsortId);

    }

    public void deleteNsort(Nsort nsort) {
        baseDao.delete(nsort);
    }

    /**
     * 得到Sort
     * 
     * @param sortId
     * @return
     */
    public Nsort getNsort(Integer nsortId) {
        return baseDao.get(Nsort.class, nsortId);
    }

    /**
     * 保存对象
     * 
     * @param o
     */
    public void saveOrUpdate(Object o) {
        baseDao.saveOrUpdate(o);
    }

    /**
     * 根据ID删除对象.
     */
    public <T> void deleteById(Class<T> entityClass, Serializable id) {
        baseDao.deleteById(entityClass, id);
    }

    public void deleteIndexJpg(Integer id) {
        baseDao.deleteById(Indexjpg.class, id);
    }

    /**
     * 根据ID获取对象. 实际调用Hibernate的session.load()方法返回实体或其proxy对象.
     * 如果对象不存在，抛出异常.
     */
    public <T> T get(Class<T> entityClass, Serializable id) {
        return baseDao.get(entityClass, id);
    }

    /**
     * 产品上线
     * 
     * @param hwId 产品Id
     */
    public boolean hwOnline(Integer hwId) {
        Hw hw = baseDao.get(Hw.class, hwId);
        if (hw.getStatus() != Constants.HW_ONLINE) {
            hw.setStatus(Constants.HW_ONLINE);
            baseDao.update(hw);
            return true;
        }
        return false;
    }

    /**
     * 产品下线
     * 
     * @param hwId 产品Id
     */
    public boolean hwOffline(Integer hwId) {
        Hw hw = baseDao.get(Hw.class, hwId);
        if (hw.getStatus() != Constants.HW_OFFLINE) {
            hw.setStatus(Constants.HW_OFFLINE);
            baseDao.update(hw);
            return true;
        }
        return false;
    }

    //删除用户,只能删除普通用户
    public String deleteUserDetail(String userId, String userName) {

        List<UserRole> list = baseDao.findByHQL("from UserRole where id.userId = ?", new Object[] { userId });
        boolean isAdmin = false;
        for (UserRole role : list) {
//            if (role.getId().getRoleId().equals(Constants.ROLE_Admin)) {
//                isAdmin = true;
//                break;
//            }
            if (role.getId().getRoleId().equals(Constants.ROLE_SUPERVISOR)) {
                isAdmin = true;
                break;
            }

        }
        if (isAdmin) {
            return "你无权删除管理员用户";
        }
        //删除用户角色
        baseDao.deleteAll(list);
        //删除用户详细信息
        baseDao.deleteById(UserDetail.class, userId);
        //删除用户基本信息
        baseDao.deleteById(TUser.class, userId);
        //删除basket
        Integer dbr = baseDao.exeByHQL("delete from Basket where userName = ?", new Object[] { userName },
                new Type[] { Hibernate.STRING });

        //删除order
        Integer dsr = baseDao.exeByHQL("delete from Sub where userName = ?", new Object[] { userName },
                new Type[] { Hibernate.STRING });
        //删除shopDetail
        baseDao.exeByHQL("delete from ShopDetail where storeName = ?", new Object[] { userName },
                new Type[] { Hibernate.STRING });
        log.debug("删除basket result {}, 删除order result {}", dbr, dsr);
        return null;

    }

    public Long getIndexJpgNum(String userName) {
        String sql = "select count(*) from Indexjpg where userName = ?";
        List list = baseDao.find(sql, new Object[] { userName });
        if (list != null) {
            return (Long) list.get(0);
        } else {
            return null;
        }

    }

    //HQL查询
    public PageSupport getDataByHQL(HqlQuery hql) {
        return baseDao.find(hql);
    }

    //HqlQuery 的SQL查询
    public PageSupport getDataBySQL(SqlQuery query) {
        return sqlPageDao.findBySql(query);
    }

    //HqlQuery 的SQL查询
    public PageSupport getDataBySQL(HqlQuery query) {
        return baseDao.findBySql(query);
    }

    public void setSqlPageDao(SqlPageDao sqlPageDao) {
        this.sqlPageDao = sqlPageDao;
    }
}
