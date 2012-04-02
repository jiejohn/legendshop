/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service;

import java.io.Serializable;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.oro.text.regex.MalformedPatternException;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SqlQuery;
import com.legendshop.model.entity.DynamicTemp;
import com.legendshop.model.entity.News;
import com.legendshop.model.entity.Nsort;
import com.legendshop.model.entity.Product;
import com.legendshop.model.entity.ShopDetail;
import com.legendshop.model.entity.Sort;

/**
 * The Interface AdminService.
 */
public interface AdminService extends BaseService {


	/**
	 * 保存新闻.
	 * 
	 * @param news
	 *            the news
	 */
	public abstract void saveNews(News news);

	/**
	 * 得到新闻.
	 * 
	 * @param newsId
	 *            the news id
	 * @return the news by id
	 */
	public abstract News getNewsById(Long newsId);

	/**
	 * 分页查询.
	 * 
	 * @param cq
	 *            the cq
	 * @return the data by cq
	 */
	public abstract PageSupport getDataByCQ(CriteriaQuery cq);

	/**
	 * 分页查询,采用post的方式来处理工具条.
	 * 
	 * @param cq
	 *            the cq
	 * @return the data by criteria query
	 */
	public abstract PageSupport getDataByCriteriaQuery(CriteriaQuery cq);

	/**
	 * Gets the data by criteria query.
	 * 
	 * @param sqlQuery
	 *            the sql query
	 * @return the data by criteria query
	 */
	public abstract PageSupport getDataByCriteriaQuery(SqlQuery sqlQuery);

	/**
	 * Gets the data by criteria query map.
	 * 
	 * @param sqlQuery
	 *            the sql query
	 * @return the data by criteria query map
	 */
	public abstract PageSupport getDataByCriteriaQueryMap(SqlQuery sqlQuery);

	/**
	 * Gets the user detail list.
	 * 
	 * @param hql
	 *            the hql
	 * @param request
	 *            the request
	 * @return the user detail list
	 */
	public abstract PageSupport getUserDetailList(HqlQuery hql, HttpServletRequest request);

	/**
	 * Delete sort.
	 * 
	 * @param sortId
	 *            the sort id
	 */
	public abstract void deleteSort(Long sortId);

	/**
	 * 得到Sort.
	 * 
	 * @param sortId
	 *            the sort id
	 * @return the sort
	 */
	public abstract Sort getSort(Long sortId);

	/**
	 * 保存Sort.
	 * 
	 * @param sort
	 *            the sort
	 */
	public abstract void saveSort(Sort sort);

	/**
	 * Save nsort.
	 * 
	 * @param nsort
	 *            the nsort
	 */
	public abstract void saveNsort(Nsort nsort);

	/**
	 * Delete nsort.
	 * 
	 * @param nsortId
	 *            the nsort id
	 */
	public abstract void deleteNsort(Long nsortId);

	/**
	 * Delete nsort.
	 * 
	 * @param nsort
	 *            the nsort
	 */
	public abstract void deleteNsort(Nsort nsort);

	/**
	 * 得到Sort.
	 * 
	 * @param nsortId
	 *            the nsort id
	 * @return the nsort
	 */
	public abstract Nsort getNsort(Long nsortId);

	/**
	 * 保存或更新对象.
	 * 
	 * @param o
	 *            the o
	 */
	public abstract void saveOrUpdate(Object o);

	/**
	 * 更新商品.
	 * 
	 * @param product
	 *            the product
	 */
	public abstract void updateProd(Product product);

	/**
	 * 根据ID删除对象.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param entityClass
	 *            the entity class
	 * @param id
	 *            the id
	 */
	public abstract <T> void deleteById(Class<T> entityClass, Serializable id);

	/**
	 * 根据ID获取对象. 实际调用Hibernate的session.load()方法返回实体或其proxy对象. 如果对象不存在，抛出异常.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param entityClass
	 *            the entity class
	 * @param id
	 *            the id
	 * @return the t
	 */
	public abstract <T> T get(Class<T> entityClass, Serializable id);

	/**
	 * 商品上线.
	 * 
	 * @param prodId
	 *            商品Id
	 * @return true, if successful
	 */
	public abstract boolean updateProdOnline(Long prodId);

	/**
	 * 商品下线.
	 * 
	 * @param prodId
	 *            商品Id
	 * @return true, if successful
	 */
	public abstract boolean updateProdOffline(Long prodId);

	/**
	 * 删除用户,只能删除普通用户 因为没有采用外键，所以要一个一个删除 注意删除无法恢复，慎用.
	 * 
	 * @param userId
	 *            the user id
	 * @param userName
	 *            the user name
	 * @param realPicPath
	 *            the real pic path
	 * @param smallPicPath
	 *            the small pic path
	 * @return the string
	 */
	public abstract String deleteUserDetail(String userId, String userName, String realPicPath, String smallPicPath);

	/**
	 * Gets the index jpg num.
	 * 
	 * @param userName
	 *            the user name
	 * @return the index jpg num
	 */
	public abstract Long getIndexJpgNum(String userName);

	// HQL查询
	/**
	 * Gets the data by hql.
	 * 
	 * @param hql
	 *            the hql
	 * @return the data by hql
	 */
	public abstract PageSupport getDataByHQL(HqlQuery hql);

	// HqlQuery 的SQL查询
	/**
	 * Gets the data by sql.
	 * 
	 * @param query
	 *            the query
	 * @return the data by sql
	 */
	public abstract PageSupport getDataBySQL(SqlQuery query);

	// HqlQuery 的SQL查询
	/**
	 * Gets the data by sql.
	 * 
	 * @param query
	 *            the query
	 * @return the data by sql
	 */
	public abstract PageSupport getDataBySQL(HqlQuery query);

	/**
	 * 审核商城.
	 * 
	 * @param loginUserName
	 *            the login user name
	 * @param userId
	 *            the user id
	 * @param shopId
	 *            the shop id
	 * @param status
	 *            状态,是否上线1：在线，0下线，-1审核中,-2拒绝,-3关闭（管理员操作）
	 * @return true, if successful
	 */
	public abstract boolean updateShop(String loginUserName, String userId, ShopDetail shopDetail, Integer status);

	/**
	 * Save brand item.
	 * 
	 * @param idList
	 *            the id list
	 * @param nsortId
	 *            the nsort id
	 * @param userName
	 *            the user name
	 * @return the string
	 */
	public abstract String saveBrandItem(List<String> idList, Long nsortId, String userName);

	/**
	 * Img file online.
	 * 
	 * @param fileId
	 *            the file id
	 * @return true, if successful
	 */
	public abstract boolean updateImgFileOnline(Long fileId);

	/**
	 * Img file offline.
	 * 
	 * @param fileId
	 *            the file id
	 * @return true, if successful
	 */
	public abstract boolean updateImgFileOffline(Long fileId);

	// 得到商品
	/**
	 * Load prod.
	 * 
	 * @param prodId
	 *            the prod id
	 * @param userName
	 *            the user name
	 * @return the product
	 */
	public abstract Product getProd(Long prodId, String userName);

	// 商品动态属性
	/**
	 * Load attributeprod attribute.
	 * 
	 * @param prodId
	 *            the prod id
	 * @return the string
	 */
	public abstract String getAttributeprodAttribute(Long prodId);

	// 商品动态参数
	/**
	 * Load prod parameter.
	 * 
	 * @param prodId
	 *            the prod id
	 * @return the string
	 */
	public abstract String getProdParameter(Long prodId);

	/**
	 * Save dynamic temp.
	 * 
	 * @param tempName
	 *            the temp name
	 * @param userName
	 *            the user name
	 * @param type
	 *            the type
	 * @param content
	 *            the content
	 * @return true, if successful
	 */
	public abstract boolean saveDynamicTemp(String tempName, String userName, Short type, String content);

	/**
	 * Update dynamic temp.
	 * 
	 * @param tempId
	 *            the temp id
	 * @param userName
	 *            the user name
	 * @param type
	 *            the type
	 * @param content
	 *            the content
	 * @return true, if successful
	 */
	public abstract boolean updateDynamicTemp(Long tempId, String userName, Short type, String content);

	/**
	 * Load dynamic temp.
	 * 
	 * @param tempId
	 *            the temp id
	 * @param userName
	 *            the user name
	 * @return the dynamic temp
	 */
	public abstract DynamicTemp getDynamicTemp(Long tempId, String userName);

	/**
	 * Delete dynamic temp.
	 * 
	 * @param tempId
	 *            the temp id
	 * @param userName
	 *            the user name
	 * @return true, if successful
	 */
	public abstract boolean deleteDynamicTemp(Long tempId, String userName);

	/**
	 * Save prod item.
	 * 
	 * @param idList
	 *            the id list
	 * @param nameList
	 *            the name list
	 * @param prodId
	 *            the prod id
	 * @param userName
	 *            the user name
	 * @return the string
	 */
	public abstract String saveProdItem(List<String> idList, List<String> nameList, Long prodId, String userName);

	/**
	 * 重置密码.
	 * 
	 * @param userName
	 *            the user name
	 * @param mail
	 *            the mail
	 * @param templateFilePath
	 *            the template file path
	 * @return true, if successful
	 * @throws MalformedPatternException
	 *             the malformed pattern exception
	 * @throws MessagingException
	 *             the messaging exception
	 */

	public abstract boolean updatePassword(String userName, String mail, String templateFilePath)
			throws MalformedPatternException, MessagingException;


	/**
	 * Update shop detail.
	 * 
	 * @param product
	 *            the product
	 */
	public abstract void updateShopDetail(Product product);



}