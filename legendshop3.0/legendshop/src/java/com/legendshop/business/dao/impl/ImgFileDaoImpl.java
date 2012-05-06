/**
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */

package com.legendshop.business.dao.impl;

import java.util.List;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;

import com.legendshop.business.dao.ImgFileDao;
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
	@Cacheable(value="IndexjpgList",key="#userName")
	public List<Indexjpg> getIndexJpeg(final String userName) {
		log.debug("getIndexJpeg, userName = {}", userName);
		String name = userName;
		if (AppUtils.isBlank(userName)) {
			name = PropertiesUtil.getObject(ParameterEnum.DEFAULT_SHOP, String.class);
		}
		return findByHQL("from Indexjpg where userName = ? OR userName = 'common'", name);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.ImgFileDao#getProductPics(java.lang.String, java.lang.Long)
	 */
	@Override
	@Cacheable(value="ImgFileList",key="#userName + #prodId")
	public List<ImgFile> getProductPics(final String userName, final Long prodId) {
		return findByHQL(
				"from ImgFile where productType = 1 and status = 1 and  userName = ? and productId = ? ",
				userName, prodId);
	}

	@Override
	@Cacheable(value="IndexjpgList")
	public JSONArray getIndexJpegJSON(String userName) {
		log.debug("getIndexJpeg, userName = {}", userName);
		String name = userName;
		if (AppUtils.isBlank(userName)) {
			name = PropertiesUtil.getObject(ParameterEnum.DEFAULT_SHOP, String.class);
		}
		List indexJpgList = findByHQL("from Indexjpg where userName = ? OR userName = 'common'", name);
		if(AppUtils.isNotBlank(indexJpgList)){
			return JSONArray.fromObject(indexJpgList);
		}else{
			return null;
		}
	}

}
