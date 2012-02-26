/**
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */

package com.legendshop.business.dao.impl;

import java.util.List;

import net.sf.ehcache.Cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.legendshop.business.common.CacheKeys;
import com.legendshop.business.dao.ImgFileDao;
import com.legendshop.core.cache.CacheCallBack;
import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.ImgFile;
import com.legendshop.model.entity.Indexjpg;
import com.legendshop.util.AppUtils;

/**
 * 获取图片Dao.
 */
@SuppressWarnings("unchecked")
public class ImgFileDaoImpl extends BaseDaoImpl implements ImgFileDao {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(ImgFileDaoImpl.class);

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ImgFileDao#getIndexJpeg(java.lang.String)
	 */
	@Override
	public List<Indexjpg> getIndexJpeg(final String userName) {
		log.debug("getIndexJpeg, userName = {}", userName);
		return (List<Indexjpg>) getObjectFromCache(getKey(CacheKeys.IMGFILEDAO_GETINDEXJPEG, userName),
				new CacheCallBack<List<Indexjpg>>() {
					public List<Indexjpg> doInCache(String cahceName, Cache cache) {
						String name = userName;
						if (AppUtils.isBlank(userName)) {
							name = PropertiesUtil.getObject(ParameterEnum.DEFAULT_SHOP, String.class);
						}
						return findByHQL("from Indexjpg where userName = ? OR userName = 'common'", name);
					}

				});
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ImgFileDao#getProductPics(java.lang.String, java.lang.Long)
	 */
	@Override
	public List<ImgFile> getProductPics(final String userName, final Long prodId) {
		return (List<ImgFile>) getObjectFromCache(getKey(CacheKeys.IMGFILEDAO_GETPRODUCTPICS, userName, prodId),
				new CacheCallBack<List<ImgFile>>() {
					public List<ImgFile> doInCache(String cahceName, Cache cache) {
						return findByHQL(
								"from ImgFile where productType = 1 and status = 1 and  userName = ? and productId = ? ",
								userName, prodId);
					}
				});
	}

}
