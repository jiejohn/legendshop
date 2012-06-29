/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.dwr.impl;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.legendshop.business.dao.TagLibDao;
import com.legendshop.business.service.dwr.OptionService;
import com.legendshop.core.exception.ApplicationException;
import com.legendshop.core.exception.BusinessException;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.exception.ErrorCodes;
import com.legendshop.util.AppUtils;

/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public class OptionServiceImpl implements OptionService{
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(OptionServiceImpl.class);

	private TagLibDao tagLibDao;
			
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.OptionService#getLinkedOptionsByHql(java.lang.String)
	 */
	@Override
	public Map<Object, Object> getLinkedOptionsByHql(String hql) {
		try {
			List<Object[]> list = tagLibDao.findDataByHQL(hql);
			Map<Object, Object> options = null;
			if(!AppUtils.isBlank(list)) {
				options = new LinkedHashMap<Object, Object>(list.size());
				
				for(Object[] objArray: list) {
					options.put(objArray[0], objArray[1]);
				}
			}
			
			return options;
		} catch (Exception e) {
			log.error("getLinkedOptionsByHql",e);
			throw new BusinessException(e,"invoke getLinkedOptionsByHql error",EntityCodes.LINK,ErrorCodes.BUSINESS_ERROR);
		}

	}
	
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.dwr.impl.OptionService#getLinkedOptionsBySql(java.lang.String)
	 */
	@Override
	public Map<Object, Object> getLinkedOptionsBySql(String sql) {
		try {
			List<Object[]> list = tagLibDao.findDataBySQL(sql);
			Map<Object, Object> options = null;
			if(!AppUtils.isBlank(list)) {
				options = new LinkedHashMap<Object, Object>(list.size());
				
				for(Object[] objArray: list) {
					options.put(objArray[0], objArray[1]);
				}
			}
			
			return options;
		} catch (Exception e) {
			log.error("getLinkedOptionsBySql",e);
			 throw new ApplicationException(e,"invoke getLinkedOptionsBySql error",EntityCodes.LINK,ErrorCodes.BUSINESS_ERROR);	
		}

	}

	@Required
	public void setTagLibDao(TagLibDao tagLibDao) {
		this.tagLibDao = tagLibDao;
	}
}
