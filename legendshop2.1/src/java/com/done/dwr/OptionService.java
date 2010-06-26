package com.done.dwr;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bingosoft.jcf.dao.cache.BaseDao;
import bingosoft.jcf.framework.IState;
import bingosoft.jcf.util.AppUtils;

/**
 * 选项服务类
 * Jun 28, 2009 1:15:21 AM
 * Author:Newway(hewq@bingosoft.net)
 * ReadMe:
 * ChangeLog:
 */
public class OptionService{
	private static Logger log = LoggerFactory.getLogger(OptionService.class);
	private BaseDao baseDao;
			
	public Map<Object, Object> getLinkedOptionsByHql(String hql) {
		try {
			List<Object[]> list = baseDao.findByHQL(hql);
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
			throw new RuntimeException("invoke getLinkedOptionsByHql error");
		}

	}
	
	public Map<Object, Object> getLinkedOptionsBySql(String sql) {
		try {
			List<Object[]> list = baseDao.findBySQL(sql);
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
			throw new RuntimeException("invoke getLinkedOptionsBySql error");
		}

	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
}
