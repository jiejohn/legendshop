/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;

import com.legendshop.business.dao.PubDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.model.entity.Pub;
import com.legendshop.spi.constants.Constants;
import com.legendshop.util.AppUtils;

/**
 * 公告Dao
 */
@SuppressWarnings("unchecked")
public class PubDaoImpl extends BaseDaoImpl implements PubDao {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(PubDaoImpl.class);

	/* (non-Javadoc)
	 * @see com.legendshop.business.dao.impl.PubDao#getPub(java.lang.String)
	 */
	@Override
	@Cacheable(value="PubList",key="#shopName")
	public List<Pub> getPub(final String shopName) {
		log.debug("getPub, shopName = {}", shopName);
		if (shopName == null){
			return null;
		}
		CriteriaQuery cq = new CriteriaQuery(Pub.class);
		cq.eq("userName", shopName);
		cq.addOrder("desc", "date");
		
		List<Pub> list = findListByCriteria(cq, 0, 4);
		if (AppUtils.isBlank(list)) {
			cq = new CriteriaQuery(Pub.class);
			cq.eq("userName", Constants.COMMON_USER);
			cq.addOrder("desc", "date");
			
			list = findListByCriteria(cq, 0, 4);
		}
		return list;
	}

	@Override
	public void deletePubById(Long id) {
		deleteById(Pub.class, id);
		
	}

	@Override
	public void updatePub(Pub pub) {
		update(pub);
		
	}

}
