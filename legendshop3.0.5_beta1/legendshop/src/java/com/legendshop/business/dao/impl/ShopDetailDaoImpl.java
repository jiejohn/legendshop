/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import com.legendshop.business.service.CommonUtil;
import com.legendshop.core.OperationTypeEnum;
import com.legendshop.core.constant.ShopStatusEnum;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.event.impl.FireEvent;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.core.tag.TableCache;
import com.legendshop.event.EventHome;
import com.legendshop.model.entity.Myleague;
import com.legendshop.model.entity.Product;
import com.legendshop.model.entity.ShopDetail;
import com.legendshop.spi.dao.ShopDetailDao;
import com.legendshop.util.AppUtils;

/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * -----------------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * -----------------------------------------------------------------------------------
 * 
 * 
 * 官方网站：http://www.legendesign.net
 */
public abstract class ShopDetailDaoImpl extends BaseDaoImpl implements ShopDetailDao {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(ShopDetailDaoImpl.class);

	/** The code tables cache. */
	private TableCache codeTablesCache;
	
	/** The color type options. */
	protected String[] colorTypeOptions = null;
	// new String[] { "blue", "green", "red","yellow","black","purple" }; //see
	// shopDetail.jsp
	/** The color type one day. */
	protected final String colorTypeOneDay = "oneday"; // 每天一个风格
	
	/** The common util. */
	private CommonUtil commonUtil;

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ShopDetailDao#isShopExists(java.lang.String)
	 */
	@Override
	@Cacheable(value="ShopDetail")
	public Boolean isShopExists(final String userName) {
		if (AppUtils.isBlank(userName)) {
			return false;
		}
		List list = findByHQL("select userName from ShopDetail where userName = ?", userName);
		return list != null && list.size() > 0;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ShopDetailDao#getShopDetailForUpdate(java.lang.String)
	 */
	@Override
	public ShopDetail getShopDetailForUpdate(final String userName) {
		ShopDetail shopDetail = findUniqueBy("from ShopDetail sd where sd.userName = ?", ShopDetail.class, userName);
		return shopDetail;
	}

	
	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ShopDetailDao#getShopDetail(java.lang.String)
	 */
	@Override
	public ShopDetail getShopDetail(final String userName) {
		return findUniqueBy("from ShopDetail sd where sd.userName = ?", ShopDetail.class, userName);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ShopDetailDao#isLeagueShopExists(java.lang.String)
	 */
	@Override
	@Cacheable(value="ShopDetail")
	public Boolean isLeagueShopExists(final String userName) {
		if (userName == null)
			return false;
		Long num = findUniqueBy("select count(*) from Myleague where userId = ? ", Long.class,
				userName);
		return num > 0;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ShopDetailDao#canbeLeagueShop(boolean, java.lang.String, java.lang.String)
	 */
	@Override
	@Cacheable(value="ShopDetail")
	public Boolean isBeLeagueShop(final boolean isShopExists, final String userName, final String friendName) {
		if (!isShopExists || AppUtils.isBlank(userName) || userName.equals(friendName)) {
			return false;
		}
		Long result = findUniqueBy("select count(*) from Myleague where userId = ? and friendId = ?",
				Long.class, userName, friendName);
		return result <= 0;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ShopDetailDao#findMyleagueByUserNameAndShopName(java.lang.String, java.lang.String)
	 */
	@Override
	public Myleague getMyleague(String userName, String shopName) {
		return findUniqueBy("from Myleague where userId = ? and friendId = ?", Myleague.class, userName, shopName);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ShopDetailDao#saveMyleague(com.legendshop.model.entity.Myleague)
	 */
	@Override
	public Long saveMyleague(Myleague myleague) {
		Long result = (Long) save(myleague);
		return result;
	}

	/**
	 * Sets the code tables cache.
	 * 
	 * @param codeTablesCache
	 *            the new code tables cache
	 */
	public void setCodeTablesCache(TableCache codeTablesCache) {
		this.codeTablesCache = codeTablesCache;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ShopDetailDao#initColorTypeOptions()
	 */
	@Override
	public void getColorTypeOptions() {
		if (colorTypeOptions == null) {
			Map<String, String> map = codeTablesCache.getCodeTable("COLOR_STYLE");
			List<String> list = new ArrayList<String>();
			String value = "";
			for (String colorType : map.keySet()) {
				if (AppUtils.isNotBlank(colorType)) {
					list.add(colorType);
					value = value + " " + colorType;
				}
			}
			colorTypeOptions = new String[list.size()];
			int i = 0;
			for (String colorType : list) {
				colorTypeOptions[i++] = colorType;
			}
			log.info("System Color Type" + value);
		}
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ShopDetailDao#getProductNum(java.lang.String)
	 */
	@Override
	public Integer getProductNum(String userName) {
		String sql = "select count(*) from Product prod where prod.status = 1 and prod.userName = ?";
		return findUniqueBy(sql, Long.class, userName).intValue();
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ShopDetailDao#getOffProductNum(java.lang.String)
	 */
	@Override
	public Integer getOffProductNum(String userName) {
		String sql = "select count(*) from Product prod where prod.status = 0 and prod.userName = ?";
		return findUniqueBy(sql, Long.class, userName).intValue();
	}


	/**
	 * Gets the color tyle.
	 * 
	 * @param userName
	 *            the store name
	 * @return the color tyle
	 */
	protected String getColorTyle(String userName) {
		getColorTypeOptions();
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
		String s = simpledateformat.format(new Date());
		long i = (Long.valueOf(s) + Long.valueOf(userName.hashCode())) % colorTypeOptions.length;
		if (i < 0) {
			i = -i;
		}
		String type = colorTypeOptions[(int) i];
		return type;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ShopDetailDao#updateShopDetail(com.legendshop.model.entity.ShopDetail)
	 */
	@Override
	@CacheEvict(value="ShopDetailView", key="#shopdetail.userName")
	public void updateShopDetail(ShopDetail shopdetail) {
		update(shopdetail);
	}
	
	/**
	 * Save or update shop detail.
	 * 
	 * @param shopdetail
	 *            the shopdetail
	 */
	@CacheEvict(value="ShopDetailView", key="#shopdetail.userName")
	private void saveOrUpdateShopDetail(ShopDetail shopdetail) {
		saveOrUpdate(shopdetail);
	}
	
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ShopDetailDao#updateShopDetailWhenProductChange(com.legendshop.model.entity.Product)
	 */
	@Override
	public void updateShopDetailWhenProductChange(Product product) {
		ShopDetail shopdetail = getShopDetailForUpdate(product.getUserName());
		if (shopdetail == null) {
			throw new NotFoundException("ShopDetail is null, UserName = " + product.getUserName(),EntityCodes.SHOP);
		}
		shopdetail.setProductNum(getProductNum(product.getUserName()));
		shopdetail.setOffProductNum(getOffProductNum(product.getUserName()));
		updateShopDetail(shopdetail);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.ShopDetailDao#getShopDetailByUserId(java.lang.String)
	 */
	@Override
	public ShopDetail getShopDetailByUserId(String userId) {
		return findUniqueBy("from ShopDetail sd where sd.userId = ?", ShopDetail.class, userId);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.ShopDetailDao#updateShopDetail(java.lang.String)
	 */
	@Override
	public void updateShopDetail(String userName) {
		ShopDetail shopdetail = getShopDetailForUpdate(userName);
		if (shopdetail == null) {
			throw new NotFoundException("ShopDetail is null, UserName = " + userName,EntityCodes.PROD);
		}
		shopdetail.setProductNum(getProductNum(userName));
		shopdetail.setOffProductNum(getOffProductNum(userName));
		updateShopDetail(shopdetail);
	}
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.ShopDetailDao#updateShop(java.lang.String, java.lang.String, com.legendshop.model.entity.ShopDetail, java.lang.Integer)
	 */
	@Override
	public  boolean updateShop(String loginUserName, String userId, ShopDetail shopDetail, Integer status){
		
		boolean result = true;
		try {
				if (new Integer(ShopStatusEnum.REJECT.value()).equals(status) || new Integer(ShopStatusEnum.CLOSE.value()).equals(status)) {
					//拒绝开店
					commonUtil.removeAdminRight(userId);
				} else if (new Integer(1).equals(status)) {
					commonUtil.saveAdminRight(userId);

				}
				shopDetail.setStatus(status);
				EventHome.publishEvent(new FireEvent(shopDetail, OperationTypeEnum.UPDATE_STATUS));
				saveOrUpdateShopDetail(shopDetail);

		} catch (Exception e) {
			log.error("auditShop ", e);
			result = false;
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.ShopDetailDao#saveShopDetail(com.legendshop.model.entity.ShopDetail)
	 */
	@Override
	public void saveShopDetail(ShopDetail shopDetail) {
		save(shopDetail);
	// save right
	// 保存管理员角色
	commonUtil.saveAdminRight(shopDetail.getUserId());
		
	}
	
	/**
	 * Delete shop detail by id.
	 * 
	 * @param id
	 *            the id
	 */
	@Override
	@Caching(evict = { 
			@CacheEvict(value="ShopDetail", key="#id"),
			@CacheEvict(value="ShopDetailView", key="#id")
			})
	public void deleteShopDetailById(Long id){
		ShopDetail shopDetail = getShopDetailByShopId(id);
		if(shopDetail != null){
			EventHome.publishEvent(new FireEvent(shopDetail, OperationTypeEnum.DELETE));
			delete(shopDetail);
		}

	}

	/**
	 * Delete shop detail.
	 * 
	 * @param shopDetail
	 *            the shop detail
	 */
	@Override
	@Caching(evict = {
			@CacheEvict(value="ShopDetail", key="#shopDetail.userName"),
			@CacheEvict(value="ShopDetailView", key="#shopDetail.userName")
			})
	public void deleteShopDetail(ShopDetail shopDetail){
		EventHome.publishEvent(new FireEvent(shopDetail, OperationTypeEnum.DELETE));
		delete(shopDetail);
	}
	
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.ShopDetailDao#getShopDetailByShopId(java.lang.Long)
	 */
	@Override
	public  ShopDetail getShopDetailByShopId(final Long shopId){
		return get(ShopDetail.class, shopId);
	}
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.ShopDetailDao#getAllShopCount()
	 */
	@Override
	public Long getAllShopCount(){
		return findUniqueBy("select count(*) from ShopDetail", Long.class);
	}


	
	/**
	 * Sets the common util.
	 * 
	 * @param commonUtil
	 *            the new common util
	 */
	@Required
	public void setCommonUtil(CommonUtil commonUtil) {
		this.commonUtil = commonUtil;
	}

}
