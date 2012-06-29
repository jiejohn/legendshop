/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.legendshop.business.dao.SystemParameterDao;
import com.legendshop.business.dao.impl.SystemParameterDaoImpl;
import com.legendshop.business.service.SystemParameterService;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.SystemParameter;

/**
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------.
 */
public class SystemParameterServiceImpl implements SystemParameterService {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(SystemParameterServiceImpl.class);

	/** The base dao. */
	private SystemParameterDao systemParameterDao;

	/**
	 * Sets the base dao.
	 * 
	 * @param systemParameterDao
	 *            the new base dao
	 */
	@Required
	public void setBaseDao(SystemParameterDaoImpl systemParameterDao) {
		this.systemParameterDao = systemParameterDao;
	}

	/**
	 * List.
	 * 
	 * @return the list
	 */
	private List<SystemParameter> list() {
		return systemParameterDao.findByHQL("from SystemParameter");
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.SystemParameterService#load(java.lang.String)
	 */
	@Override
	public SystemParameter getSystemParameter(String id) {
		return systemParameterDao.get(SystemParameter.class, id);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.SystemParameterService#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) {
		systemParameterDao.deleteSystemParameterById(id);
	}


	/* (non-Javadoc)
	 * @see com.legendshop.business.service.SystemParameterService#update(com.legendshop.model.entity.SystemParameter)
	 */
	@Override
	public void update(SystemParameter systemParameter) {
		systemParameterDao.updateSystemParameter(systemParameter);
	}

	/* (non-Javadoc)
	 * @see com.legendshop.business.service.SystemParameterService#getDataByCriteriaQuery(com.legendshop.core.dao.support.CriteriaQuery)
	 */
	@Override
	public PageSupport getSystemParameterList(CriteriaQuery cq) {
		return systemParameterDao.find(cq);
	}

	// 初始化System parameter
	/* (non-Javadoc)
	 * @see com.legendshop.business.service.SystemParameterService#initSystemParameter()
	 */
	@Override
	public void initSystemParameter() {
		List<SystemParameter> list = list();
		for (SystemParameter parameter : list) {
			PropertiesUtil.setParameter(parameter, null);
		}
		log.info("System Parameter size = {}", list.size());
	}

}
