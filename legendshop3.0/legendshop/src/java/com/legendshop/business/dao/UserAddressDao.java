/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.dao;
 
import com.legendshop.core.dao.BaseDao;
import java.util.List;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.UserAddress;

/**
 * The Class UserAddressDao.
 */

public interface UserAddressDao extends BaseDao {
     
    public abstract List<UserAddress> getUserAddress(String shopName);

	public abstract UserAddress getUserAddress(Long id);
	
    public abstract void deleteUserAddress(UserAddress userAddress);
	
	public abstract Long saveUserAddress(UserAddress userAddress);
	
	public abstract void updateUserAddress(UserAddress userAddress);
	
	public abstract PageSupport getUserAddress(CriteriaQuery cq);
	
 }

