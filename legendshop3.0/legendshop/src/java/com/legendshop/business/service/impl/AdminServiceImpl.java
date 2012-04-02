/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.oro.text.regex.MalformedPatternException;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.legendshop.business.common.ProductStatusEnum;
import com.legendshop.business.common.RoleEnum;
import com.legendshop.business.common.ShopStatusEnum;
import com.legendshop.business.dao.ShopDetailDao;
import com.legendshop.business.event.EventId;
import com.legendshop.business.helper.TaskThread;
import com.legendshop.business.helper.impl.SendMailTask;
import com.legendshop.business.service.AdminService;
import com.legendshop.business.service.CommonUtil;
import com.legendshop.core.dao.BaseDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SqlQuery;
import com.legendshop.core.exception.BusinessException;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.core.helper.FileProcessor;
import com.legendshop.core.randing.RandomStringUtils;
import com.legendshop.event.EventContext;
import com.legendshop.event.EventHome;
import com.legendshop.event.GenericEvent;
import com.legendshop.model.entity.DynamicTemp;
import com.legendshop.model.entity.ImgFile;
import com.legendshop.model.entity.News;
import com.legendshop.model.entity.Nsort;
import com.legendshop.model.entity.NsortBrand;
import com.legendshop.model.entity.NsortBrandId;
import com.legendshop.model.entity.Product;
import com.legendshop.model.entity.RelProduct;
import com.legendshop.model.entity.ShopDetail;
import com.legendshop.model.entity.Sort;
import com.legendshop.model.entity.User;
import com.legendshop.model.entity.UserDetail;
import com.legendshop.model.entity.UserRole;
import com.legendshop.util.AppUtils;
import com.legendshop.util.MD5Util;

/**
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * ----------------------------------------------------------------------------
 * -------- 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * --------
 * ----------------------------------------------------------------------------.
 */
public class AdminServiceImpl extends BaseServiceImpl implements AdminService {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);
	
	/** The base dao. */
	private BaseDao baseDao;

	/** The shop detail dao. */
	private ShopDetailDao shopDetailDao;

	/** The common util. */
	private CommonUtil commonUtil;


	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.AdminService#setBaseDao(com.legendshop.core.dao.BaseDaoImpl)
	 */
	@Required
	public void setBaseDao(BaseDaoImpl baseDao) {
		this.baseDao = baseDao;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.AdminService#saveNews(com.legendshop.model.entity.News)
	 */
	@Override
	public void saveNews(News news) {
		baseDao.saveOrUpdate(news);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.AdminService#getNewsById(java.lang.Long)
	 */
	@Override
	public News getNewsById(Long newsId) {
		return baseDao.get(News.class, newsId);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.AdminService#getDataByCQ(com.legendshop.core.dao.support.CriteriaQuery)
	 */
	@Override
	public PageSupport getDataByCQ(CriteriaQuery cq) {
		return baseDao.find(cq, true);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.AdminService#getDataByCriteriaQuery(com.legendshop.core.dao.support.CriteriaQuery)
	 */
	@Override
	public PageSupport getDataByCriteriaQuery(CriteriaQuery cq) {
		return baseDao.find(cq);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.AdminService#getDataByCriteriaQuery(com.legendshop.core.dao.support.SqlQuery)
	 */
	@Override
	public PageSupport getDataByCriteriaQuery(SqlQuery sqlQuery) {
		return baseDao.find(sqlQuery);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.AdminService#getDataByCriteriaQueryMap(com.legendshop.core.dao.support.SqlQuery)
	 */
	@Override
	public PageSupport getDataByCriteriaQueryMap(SqlQuery sqlQuery) {
		return baseDao.findMap(sqlQuery);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.AdminService#getUserDetailList(com.legendshop.core.dao.support.HqlQuery, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public PageSupport getUserDetailList(HqlQuery hql, HttpServletRequest request) {
		EventContext eventContext = new EventContext(request);
		EventHome.publishEvent(new GenericEvent(eventContext,EventId.CAN_ADD_SHOPDETAIL_EVENT));
		Boolean isSupportOpenShop = eventContext.getBooleanResponse();
		request.setAttribute("supportOpenShop", isSupportOpenShop);
		return baseDao.find(hql);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.AdminService#deleteSort(java.lang.Long)
	 */
	@Override
	public void deleteSort(Long sortId) {
		List list = baseDao.findByHQL("from Product where sortId = ?", sortId);
		if (!AppUtils.isBlank(list)) {
			throw new BusinessException("请删除该类型对应的商品",EntityCodes.PROD);
		}

		List nsortList = baseDao.findByHQL("from Nsort where sortId = ?", sortId);
		if (!AppUtils.isBlank(nsortList)) {
			throw new BusinessException("请删除该类型对应的二级商品类型",EntityCodes.PROD);
		}

		baseDao.deleteById(Sort.class, sortId);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.AdminService#getSort(java.lang.Long)
	 */
	@Override
	public Sort getSort(Long sortId) {
		return baseDao.get(Sort.class, sortId);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.AdminService#saveSort(com.legendshop.model.entity.Sort)
	 */
	@Override
	public void saveSort(Sort sort) {
		baseDao.saveOrUpdate(sort);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.AdminService#saveNsort(com.legendshop.model.entity.Nsort)
	 */
	@Override
	public void saveNsort(Nsort nsort) {
		baseDao.saveOrUpdate(nsort);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.AdminService#deleteNsort(java.lang.Long)
	 */
	@Override
	public void deleteNsort(Long nsortId) {
		baseDao.deleteById(Nsort.class, nsortId);

	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.AdminService#deleteNsort(com.legendshop.model.entity.Nsort)
	 */
	@Override
	public void deleteNsort(Nsort nsort) {
		baseDao.delete(nsort);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.AdminService#getNsort(java.lang.Long)
	 */
	@Override
	public Nsort getNsort(Long nsortId) {
		return baseDao.get(Nsort.class, nsortId);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.AdminService#saveOrUpdate(java.lang.Object)
	 */
	@Override
	public void saveOrUpdate(Object o) {
		baseDao.saveOrUpdate(o);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.AdminService#updateProd(com.legendshop.model.entity.Product)
	 */
	@Override
	public void updateProd(Product product) {
		baseDao.update(product);
	}


	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.AdminService#deleteById(java.lang.Class, java.io.Serializable)
	 */
	@Override
	public <T> void deleteById(Class<T> entityClass, Serializable id) {
		baseDao.deleteById(entityClass, id);
	}


	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.AdminService#get(java.lang.Class, java.io.Serializable)
	 */
	@Override
	public <T> T get(Class<T> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.AdminService#prodOnline(java.lang.Long)
	 */
	@Override
	public boolean updateProdOnline(Long prodId) {
		Product product = baseDao.get(Product.class, prodId);
		if (!ProductStatusEnum.PROD_ONLINE.value().equals(product.getStatus())) {
			product.setStatus(ProductStatusEnum.PROD_ONLINE.value());
			baseDao.update(product);
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.AdminService#prodOffline(java.lang.Long)
	 */
	@Override
	public boolean updateProdOffline(Long prodId) {
		Product product = baseDao.get(Product.class, prodId);
		if (!ProductStatusEnum.PROD_OFFLINE.value().equals(product.getStatus())) {
			product.setStatus(ProductStatusEnum.PROD_OFFLINE.value());
			baseDao.update(product);
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.AdminService#deleteUserDetail(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String deleteUserDetail(String userId, String userName, String realPicPath, String smallPicPath) {

		List<UserRole> list = baseDao.findByHQL("from UserRole where id.userId = ?", userId);
		boolean isAdmin = false;
		for (UserRole role : list) {
			// ||role.getId().getRoleId().equals(RoleEnum.ROLE_ADMIN.value())
			if (role.getId().getRoleId().equals(RoleEnum.ROLE_SUPERVISOR.value())) {
				isAdmin = true;
				break;
			}

		}
		if (isAdmin) {
			return "不能删除商家用户或者管理员用户，请先备份好数据和去掉该用户的权限再试！";
		}

		// 删除basket
		Integer dbr = baseDao.exeByHQL("delete from Basket where userName = ?", new Object[] { userName },
				new Type[] { Hibernate.STRING });

		// 删除Sub
		baseDao.exeByHQL("delete from Sub where userName = ?", new Object[] { userName },
				new Type[] { Hibernate.STRING });
		// 删除shopDetail
		baseDao.exeByHQL("delete from ShopDetail where storeName = ?", new Object[] { userName },
				new Type[] { Hibernate.STRING });

		// 删除用户商品的介绍图片
		baseDao.exeByHQL("delete from ImgFile where userName = ?", new Object[] { userName },
				new Type[] { Hibernate.STRING });

		// 删除用户商品的相关商品
		baseDao.exeByHQL("delete from RelProduct where userName = ?", new Object[] { userName },
				new Type[] { Hibernate.STRING });

		// 删除用户商家联盟
		baseDao.exeByHQL("delete from Myleague where userId = ?", new Object[] { userName },
				new Type[] { Hibernate.STRING });

		// 删除用户商品
		baseDao.exeByHQL("delete from Product where userId = ?", new Object[] { userId }, new Type[] { Hibernate.STRING });

		// 删除用户商品图片目录
		if (AppUtils.isNotBlank(userName)) {
			FileProcessor.deleteDirectory(new File(realPicPath + "/" + userName));
			FileProcessor.deleteDirectory(new File(smallPicPath + "/" + userName));
		}

		// 删除商品评论
		baseDao.exeByHQL("delete from ProductComment where ownerName = ?", new Object[] { userName },
				new Type[] { Hibernate.STRING });

		// 删除短消息
		baseDao.exeByHQL("delete from UserComment where userId = ?", new Object[] { userName },
				new Type[] { Hibernate.STRING });

		// 删除商品品牌
		baseDao.exeByHQL("delete from Brand where userName = ?", new Object[] { userName },
				new Type[] { Hibernate.STRING });

		// 删除商品动态属性
		baseDao.exeByHQL("delete from DynamicTemp where userName = ?", new Object[] { userName },
				new Type[] { Hibernate.STRING });

		// 删除商品首页广告图片
		baseDao.exeByHQL("delete from Indexjpg where userName = ?", new Object[] { userName },
				new Type[] { Hibernate.STRING });

		// 删除广告
		baseDao.exeByHQL("delete from Advertisement where userName = ?", new Object[] { userName },
				new Type[] { Hibernate.STRING });
		// 删除热点商品
		baseDao.exeByHQL("delete from Hotsearch where userName = ?", new Object[] { userName },
				new Type[] { Hibernate.STRING });

		// 删除Log
		baseDao.exeByHQL("delete from Logo where userName = ?", new Object[] { userName },
				new Type[] { Hibernate.STRING });

		// 删除News
		baseDao.exeByHQL("delete from News where userName = ?", new Object[] { userName },
				new Type[] { Hibernate.STRING });

		// 删除NewsCategory
		baseDao.exeByHQL("delete from NewsCategory where userName = ?", new Object[] { userName },
				new Type[] { Hibernate.STRING });

		// 删除NsortBrand
		baseDao.exeByHQL("delete from NsortBrand where userName = ?", new Object[] { userName },
				new Type[] { Hibernate.STRING });

		// 删除Nsort
		baseDao.exeByHQL(
				"delete from Nsort n where exists (select 1 from Sort s where n.sortId = s.sortId and s.userName = ?)",
				new Object[] { userName }, new Type[] { Hibernate.STRING });

		// 删除Sort
		baseDao.exeByHQL("delete from Sort where userName = ?", new Object[] { userName },
				new Type[] { Hibernate.STRING });

		// 删除PayType
		baseDao.exeByHQL("delete from PayType where userName = ?", new Object[] { userName },
				new Type[] { Hibernate.STRING });

		// 删除公告
		baseDao.exeByHQL("delete from Pub where userName = ?", new Object[] { userName },
				new Type[] { Hibernate.STRING });

		// 删除友情链接
		baseDao.exeByHQL("delete from ExternalLink where userName = ?", new Object[] { userName },
				new Type[] { Hibernate.STRING });

		// 删除用户角色
		baseDao.deleteAll(list);
		// 删除用户详细信息
		baseDao.deleteById(UserDetail.class, userId);
		// 删除用户基本信息
		baseDao.deleteById(User.class, userId);

		log.debug("删除用户 {},  {} 成功", userId, userName);
		return null;

	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.AdminService#getIndexJpgNum(java.lang.String)
	 */
	@Override
	public Long getIndexJpgNum(String userName) {
		return baseDao.findUniqueBy("select count(*) from Indexjpg where userName = ?", Long.class, userName);
	}

	// HQL查询
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.AdminService#getDataByHQL(com.legendshop.core.dao.support.HqlQuery)
	 */
	@Override
	public PageSupport getDataByHQL(HqlQuery hql) {
		return baseDao.find(hql);
	}

	// HqlQuery 的SQL查询
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.AdminService#getDataBySQL(com.legendshop.core.dao.support.SqlQuery)
	 */
	@Override
	public PageSupport getDataBySQL(SqlQuery query) {
		return baseDao.find(query);
	}

	// HqlQuery 的SQL查询
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.AdminService#getDataBySQL(com.legendshop.core.dao.support.HqlQuery)
	 */
	@Override
	public PageSupport getDataBySQL(HqlQuery query) {
		return baseDao.findBySql(query);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.AdminService#auditShop(java.lang.String, java.lang.String, java.lang.Long, java.lang.Integer)
	 */
	@Override
	public boolean updateShop(String loginUserName, String userId, ShopDetail shopDetail, Integer status) {
		boolean result = true;
		try {
				if (new Integer(ShopStatusEnum.REJECT.value()).equals(status) || new Integer(ShopStatusEnum.CLOSE.value()).equals(status)) {
					//拒绝开店
					commonUtil.removeAdminRight(baseDao, userId);
				} else if (new Integer(1).equals(status)) {
					commonUtil.saveAdminRight(baseDao, userId);

				}
				shopDetail.setStatus(status);
				baseDao.saveOrUpdate(shopDetail);

		} catch (Exception e) {
			log.error("auditShop ", e);
			result = false;
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.AdminService#saveBrandItem(java.util.List, java.lang.Long, java.lang.String)
	 */
	@Override
	public String saveBrandItem(List<String> idList, Long nsortId, String userName) {
		// delete all
		List<NsortBrand> list = baseDao.find("from NsortBrand n where n.id.nsortId = ? and userName = ?", nsortId,
				userName);
		baseDao.deleteAll(list);
		if (AppUtils.isNotBlank(idList)) {
			for (String brandId : idList) {
				NsortBrand nb = new NsortBrand();
				NsortBrandId id = new NsortBrandId();
				id.setBrandId(Long.valueOf(brandId));
				id.setNsortId(nsortId);
				nb.setId(id);
				nb.setUserName(userName);
				baseDao.save(nb);
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.AdminService#imgFileOnline(java.lang.Long)
	 */
	@Override
	public boolean updateImgFileOnline(Long fileId) {
		ImgFile imgFile = baseDao.get(ImgFile.class, fileId);
		if (imgFile.getStatus() != ProductStatusEnum.PROD_ONLINE.value().shortValue()) {
			imgFile.setStatus(ProductStatusEnum.PROD_ONLINE.value().shortValue());
			baseDao.update(imgFile);
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.AdminService#imgFileOffline(java.lang.Long)
	 */
	@Override
	public boolean updateImgFileOffline(Long fileId) {
		ImgFile imgFile = baseDao.get(ImgFile.class, fileId);
		if (imgFile.getStatus() != ProductStatusEnum.PROD_OFFLINE.value().shortValue()) {
			imgFile.setStatus(ProductStatusEnum.PROD_OFFLINE.value().shortValue());
			baseDao.update(imgFile);
			return true;
		}
		return false;
	}

	// 得到商品
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.AdminService#loadProd(java.lang.Long, java.lang.String)
	 */
	@Override
	public Product getProd(Long prodId, String userName) {
		return baseDao.findUniqueBy("from Product prod where prod.prodId = ? and prod.userName = ?", Product.class, prodId, userName);
	}

	// 商品动态属性
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.AdminService#loadAttributeprodAttribute(java.lang.Long)
	 */
	@Override
	public String getAttributeprodAttribute(Long prodId) {
		return baseDao.findUniqueBy("select prod.attribute from Product prod where prod.prodId = ?", String.class, prodId);
	}

	// 商品动态参数
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.AdminService#loadProdParameter(java.lang.Long)
	 */
	@Override
	public String getProdParameter(Long prodId) {
		return baseDao.findUniqueBy("select prod.parameter from Product prod where prod.prodId = ?", String.class, prodId);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.AdminService#saveDynamicTemp(java.lang.String, java.lang.String, java.lang.Short, java.lang.String)
	 */
	@Override
	public boolean saveDynamicTemp(String tempName, String userName, Short type, String content) {
		if (AppUtils.isBlank(tempName) || AppUtils.isBlank(userName)) {
			return false;
		}
		List<DynamicTemp> temps = baseDao.findByHQL(
				"from DynamicTemp where type = ? and name = ? and userName = ?", type, tempName, userName);
		if (AppUtils.isNotBlank(temps)) {
			return false;
		}
		DynamicTemp temp = new DynamicTemp();
		temp.setContent(content);
		temp.setName(tempName);
		temp.setUserName(userName);
		temp.setType(type);
		baseDao.save(temp);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.AdminService#updateDynamicTemp(java.lang.Long, java.lang.String, java.lang.Short, java.lang.String)
	 */
	@Override
	public boolean updateDynamicTemp(Long tempId, String userName, Short type, String content) {
		if (AppUtils.isBlank(tempId) || AppUtils.isBlank(userName)) {
			return false;
		}
		List<DynamicTemp> temps = baseDao.findByHQL("from DynamicTemp where id = ? and userName = ?", tempId,
				userName);
		if (AppUtils.isBlank(temps)) {
			return false;
		}
		DynamicTemp temp = temps.get(0);
		temp.setContent(content);
		baseDao.update(temp);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.AdminService#loadDynamicTemp(java.lang.Long, java.lang.String)
	 */
	@Override
	public DynamicTemp getDynamicTemp(Long tempId, String userName) {
		return baseDao.findUniqueBy("from DynamicTemp where id = ?  and userName = ?", DynamicTemp.class, tempId,
				userName);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.AdminService#deleteDynamicTemp(java.lang.Long, java.lang.String)
	 */
	@Override
	public boolean deleteDynamicTemp(Long tempId, String userName) {
		DynamicTemp temp = baseDao.findUniqueBy("from DynamicTemp where id = ?  and userName = ?",
				DynamicTemp.class, tempId, userName);
		if (temp != null) {
			baseDao.delete(temp);
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.AdminService#saveProdItem(java.util.List, java.util.List, java.lang.Long, java.lang.String)
	 */
	@Override
	public String saveProdItem(List<String> idList, List<String> nameList, Long prodId, String userName) {
		// delete all
		List<RelProduct> list = baseDao.find("from RelProduct n where n.prodId = ? and userName = ?", prodId, userName);
		baseDao.deleteAll(list);
		if (AppUtils.isNotBlank(idList)) {
			for (int i = 0; i < idList.size(); i++) {
				RelProduct rprod = new RelProduct();
				rprod.setAddtime(new Date());
				rprod.setProdId(prodId);
				rprod.setRelProdId(Long.valueOf(idList.get(i)));
				rprod.setRelProdName(nameList.get(i));
				rprod.setUserName(userName);
				baseDao.save(rprod);
			}

		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.AdminService#resetPassword(java.lang.String, java.lang.String, java.lang.String)
	 */

	@Override
	public boolean updatePassword(String userName, String mail, String templateFilePath)
			throws MalformedPatternException, MessagingException {
		UserDetail userDetail = baseDao.findUniqueBy("from UserDetail n where n.userName = ? and n.userMail = ?",
				UserDetail.class, userName, mail);
		if (userDetail == null) {
			return false;
		}
		// update password
		User user = baseDao.get(User.class, userDetail.getUserId());
		String newPassword = RandomStringUtils.randomNumeric(10, 6);
		user.setPassword(MD5Util.Md5Password(user.getName(), newPassword));
		baseDao.update(user);
		// send mail
		log.info("{} 修改密码，发送通知邮件到 {}", userName, userDetail.getUserMail());
		// String text = FileProcessor.readFile(new File(filePath));
		Map<String, String> values = new HashMap<String, String>();
		values.put("#nickName#", userDetail.getNickName());
		values.put("#userName#", userDetail.getUserName());
		values.put("#password#", newPassword);
		String text = AppUtils.convertTemplate(templateFilePath, values);
		if (sendMail()) {
			threadPoolExecutor.execute(new TaskThread(new SendMailTask(javaMailSender, userDetail.getUserMail(), "恭喜您，修改密码成功！", text)));
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.AdminService#setCommonUtil(com.legendshop.business.helper.CommonUtil)
	 */
	@Required
	public void setCommonUtil(CommonUtil commonUtil) {
		this.commonUtil = commonUtil;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.AdminService#updateShopDetail(com.legendshop.model.entity.Product)
	 */
	@Override
	public void updateShopDetail(Product product) {
		ShopDetail shopdetail = shopDetailDao.getShopDetailForUpdate(product.getUserName());
		if (shopdetail == null) {
			throw new NotFoundException("ShopDetail is null, UserName = " + product.getUserName(),EntityCodes.PROD);
		}
		shopdetail.setProductNum(shopDetailDao.getProductNum(product.getUserName()));
		shopdetail.setOffProductNum(shopDetailDao.getOffProductNum(product.getUserName()));
		shopDetailDao.update(shopdetail);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.impl.AdminService#setShopDetailDao(com.legendshop.business.dao.ShopDetailDao)
	 */
	@Required
	public void setShopDetailDao(ShopDetailDao shopDetailDao) {
		this.shopDetailDao = shopDetailDao;
	}

}
