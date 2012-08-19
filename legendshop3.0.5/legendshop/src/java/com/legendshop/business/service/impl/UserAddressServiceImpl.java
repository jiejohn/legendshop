/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import java.util.List;

import com.legendshop.business.dao.UserAddressDao;
import com.legendshop.business.service.UserAddressService;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.UserAddress;
import com.legendshop.util.AppUtils;

/**
 * The Class UserAddressServiceImpl.
 */
public class UserAddressServiceImpl  implements UserAddressService{
    private UserAddressDao userAddressDao;

    public void setUserAddressDao(UserAddressDao userAddressDao) {
        this.userAddressDao = userAddressDao;
    }

    public List<UserAddress> getUserAddress(String userName) {
        return userAddressDao.getUserAddress(userName);
    }

    public UserAddress getUserAddress(Long id) {
        return userAddressDao.getUserAddress(id);
    }

    public void deleteUserAddress(UserAddress userAddress) {
        userAddressDao.deleteUserAddress(userAddress);
    }

    public Long saveUserAddress(UserAddress userAddress) {
        if (!AppUtils.isBlank(userAddress.getAddrId())) {
            updateUserAddress(userAddress);
            return userAddress.getAddrId();
        }
        return (Long) userAddressDao.save(userAddress);
    }

    public void updateUserAddress(UserAddress userAddress) {
        userAddressDao.updateUserAddress(userAddress);
    }

    public PageSupport getUserAddress(CriteriaQuery cq) {
        return userAddressDao.find(cq);
    }
}

