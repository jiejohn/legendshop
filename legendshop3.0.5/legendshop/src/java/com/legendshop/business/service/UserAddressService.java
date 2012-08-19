/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service;

import java.util.List;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.UserAddress;

/**
 * The Class UserAddressService.
 */
public interface UserAddressService  {

    public List<UserAddress> getUserAddress(String userName);

    public UserAddress getUserAddress(Long id);
    
    public void deleteUserAddress(UserAddress userAddress);
    
    public Long saveUserAddress(UserAddress userAddress);

    public void updateUserAddress(UserAddress userAddress);

    public PageSupport getUserAddress(CriteriaQuery cq);
}

