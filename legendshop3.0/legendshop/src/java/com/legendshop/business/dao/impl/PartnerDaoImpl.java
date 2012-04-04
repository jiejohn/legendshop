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
import com.legendshop.model.entity.Partner;
import com.legendshop.business.dao.PartnerDao;
/**
 * The Class PartnerDaoImpl.
 */

public class PartnerDaoImpl extends BaseDaoImpl implements PartnerDao {
    private static Logger log = LoggerFactory.getLogger(PartnerDaoImpl.class);
     
    public List<Partner> getPartner(String userName){
   		return findByHQL("from Partner where userName = ?", userName);
    }

	public Partner getPartner(Long id){
		return get(Partner.class, id);
	}
	
    public void deletePartner(Partner partner){
    	delete(partner);
    }
	
	public Long savePartner(Partner partner){
		return (Long)save(partner);
	}
	
	public void updatePartner(Partner partner){
		 update(partner);
	}
	
	public PageSupport getPartner(CriteriaQuery cq){
		return find(cq);
	}
	
 }

