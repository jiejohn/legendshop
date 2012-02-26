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

import net.sf.ehcache.Cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.legendshop.business.common.CacheKeys;
import com.legendshop.business.dao.ShopDetailDao;
import com.legendshop.central.license.LSResponse;
import com.legendshop.central.license.LicenseEnum;
import com.legendshop.core.cache.CacheCallBack;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.tag.TableCache;
import com.legendshop.model.entity.Myleague;
import com.legendshop.model.entity.Product;
import com.legendshop.model.entity.ShopDetail;
import com.legendshop.model.entity.ShopDetailView;
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
public class ShopDetailDaoImpl extends BaseDaoImpl implements ShopDetailDao {
	
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

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ShopDetailDao#isShopExists(java.lang.String)
	 */
	@Override
	public Boolean isShopExists(final String storeName) {
		if (AppUtils.isBlank(storeName)) {
			return false;
		}
		return (Boolean) getObjectFromCache(getKey(CacheKeys.SHOPDETAILDAO_ISSHOPEXISTS, storeName),
				new CacheCallBack<Boolean>() {
					@Override
					public Boolean doInCache(String cahceName, Cache cache) {
						List list = findByHQL("select storeName from ShopDetail where storeName = ?", storeName);
						return list != null && list.size() > 0;
					}
				});
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ShopDetailDao#getShopDetailForUpdate(java.lang.String)
	 */
	@Override
	public ShopDetail getShopDetailForUpdate(final String storeName) {
		ShopDetail shopDetail = findUniqueBy("from ShopDetail sd where sd.storeName = ?", ShopDetail.class, storeName);
		return shopDetail;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ShopDetailDao#getShopDetailView(java.lang.String)
	 */
	@Override
	public ShopDetailView getShopDetailView(final String storeName) {
		if (AppUtils.isBlank(storeName)) {
			return null;
		}
		return (ShopDetailView) getObjectFromCache(getKey(CacheKeys.SHOPDETAILDAO_GETSHOPDETAILVIEW, storeName),
				new CacheCallBack<ShopDetailView>() {
					@Override
					public ShopDetailView doInCache(String cahceName, Cache cache) {
						ShopDetailView shopDetail = getSimpleInfoShopDetail(storeName);
						if (shopDetail != null) {
							ShopDetailView result = shopDetail.clone();
							if (result.getQq() != null) {
								String[] qqs = result.getQq().split(",");
								List<String> qqList = new ArrayList<String>(qqs.length);
								for (int i = 0; i < qqs.length; i++) {
									if (qqs[i] != null && qqs[i].length() > 0) {
										qqList.add(qqs[i]);
									}
								}
								result.setQqList(qqList);
							}
							if (colorTypeOneDay.equals(result.getColorStyle())) {
								result.setColorStyle(getColorTyle(storeName));
							}
							return result;
						}
						return shopDetail;
					}

				});
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ShopDetailDao#getSimpleInfoShopDetail(java.lang.String)
	 */
	/**
	 * Hibernate View做法，已经抛弃 @Deprecated
	 */
	
	@Override
	public ShopDetailView getSimpleInfoShopDetail(final String storeName) {
		return findUniqueBy("from ShopDetailView sd where sd.storeName = ?", ShopDetailView.class, storeName);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ShopDetailDao#getShopDetail(java.lang.String)
	 */
	@Override
	public ShopDetail getShopDetail(final String storeName) {
		return findUniqueBy("from ShopDetail sd where sd.storeName = ?", ShopDetail.class, storeName);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ShopDetailDao#isLeagueShopExists(java.lang.String)
	 */
	@Override
	public Boolean isLeagueShopExists(final String storeName) {
		if (storeName == null)
			return false;
		return (Boolean) getObjectFromCache(getKey(CacheKeys.SHOPDETAILDAO_ISLEAGUESHOPEXISTS, storeName),
				new CacheCallBack<Boolean>() {
					@Override
					public Boolean doInCache(String cahceName, Cache cache) {
						Long num = findUniqueBy("select count(*) from Myleague where userId = ? ", Long.class,
								storeName);
						return num > 0;
					}
				});
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ShopDetailDao#canbeLeagueShop(boolean, java.lang.String, java.lang.String)
	 */
	@Override
	public Boolean isBeLeagueShop(final boolean isShopExists, final String userName, final String storeName) {
		if (!isShopExists || AppUtils.isBlank(userName) || userName.equals(storeName)) {
			return false;
		}
		return (Boolean) getObjectFromCache(getKey(CacheKeys.SHOPDETAILDAO_CANBELEAGUESHOP, isShopExists, userName,
				storeName), new CacheCallBack<Boolean>() {
			@Override
			public Boolean doInCache(String cahceName, Cache cache) {
				Long result = findUniqueBy("select count(*) from Myleague where userId = ? and friendId = ?",
						Long.class, userName, storeName);
				return result <= 0;
			}
		});
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

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ShopDetailDao#canAddShopDetail(com.legendshop.central.license.LSResponse)
	 */
	@Override
	public boolean isCanAddShopDetail(LSResponse lsResponse) {
		if (!PropertiesUtil.getObject(ParameterEnum.OPEN_SHOP, Boolean.class)) {
			return false;
		}
		Integer maxShopNumberByType = null;
		if (lsResponse == null) {
			maxShopNumberByType = getMaxShopNumberByType(null, LicenseEnum.FREE);
		} else {
			String license = lsResponse.getLicense();
			if (LicenseEnum.instance(license)) {
				maxShopNumberByType = getMaxShopNumberByType(lsResponse, LicenseEnum.valueOf(license));
			} else {
				maxShopNumberByType = getMaxShopNumberByType(lsResponse, LicenseEnum.FREE);
			}
		}
		// always pass if null
		if (maxShopNumberByType == null) {
			return true;
		}

		return maxShopNumberByType > this.getMaxShopDetail();
	}

	/**
	 * Gets the max shop detail.
	 * 
	 * @return the max shop detail
	 */
	private Long getMaxShopDetail() {
		return findUniqueBy("select count(*) from ShopDetail sd where sd.status = 1", Long.class);
	}

	// if return null means no need to check
	/**
	 * Gets the max shop number by type.
	 * 
	 * @param lsResponse
	 *            the ls response
	 * @param licenseEnum
	 *            the license enum
	 * @return the max shop number by type
	 */
	private Integer getMaxShopNumberByType(LSResponse lsResponse, LicenseEnum licenseEnum) {
		if (lsResponse != null && lsResponse.getShopCount() != null) {
			return lsResponse.getShopCount();
		}
		if (LicenseEnum.B2C_YEAR.equals(licenseEnum) || LicenseEnum.C2C_YEAR.equals(licenseEnum)) {
			return 100;
		} else if (LicenseEnum.FREE.equals(licenseEnum) || LicenseEnum.EXPIRED.equals(licenseEnum)) {
			return 10;
		} else if (LicenseEnum.B2C_ALWAYS.equals(licenseEnum) || LicenseEnum.C2C_ALWAYS.equals(licenseEnum)) {
			return null;
		}
		return 0;
	}

	/**
	 * Gets the color tyle.
	 * 
	 * @param storeName
	 *            the store name
	 * @return the color tyle
	 */
	protected String getColorTyle(String storeName) {
		getColorTypeOptions();
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
		String s = simpledateformat.format(new Date());
		long i = (Long.valueOf(s) + Long.valueOf(storeName.hashCode())) % colorTypeOptions.length;
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
	public void updateShopDetail(ShopDetail shopdetail) {
		update(shopdetail);
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
}
