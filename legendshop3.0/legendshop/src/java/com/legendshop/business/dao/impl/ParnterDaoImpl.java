/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao.impl;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.model.entity.Parnter;
import com.legendshop.business.dao.ParnterDao;
/**
 * The Class ParnterDaoImpl.
 */

public class ParnterDaoImpl extends BaseDaoImpl implements ParnterDao {
    private static Logger log = LoggerFactory.getLogger(ParnterDaoImpl.class);
     
    public List<Parnter> getParnter(String userName){
   		return findByHQL("from Parnter where userName = ?", userName);
    }

	public Parnter getParnter(Long id){
		return get(Parnter.class, id);
	}
	
    public void deleteParnter(Parnter parnter){
    	delete(parnter);
    }
	
	public Long saveParnter(Parnter parnter){
		return (Long)save(parnter);
	}
	
	public void updateParnter(Parnter parnter){
		 update(parnter);
	}
	
	public PageSupport getParnter(CriteriaQuery cq){
		return find(cq);
	}
	
 }

