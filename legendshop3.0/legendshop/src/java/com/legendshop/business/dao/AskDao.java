/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao;
 
import java.util.List;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Ask;

/**
 * The Class AskDao.
 */

public interface AskDao extends BaseDao {
     
    public abstract List<Ask> getAsk(String shopName);

	public abstract Ask getAsk(Long id);
	
    public abstract void deleteAsk(Ask ask);
	
	public abstract Long saveAsk(Ask ask);
	
	public abstract void updateAsk(Ask ask);
	
	public abstract PageSupport getAsk(CriteriaQuery cq);
	
 }

