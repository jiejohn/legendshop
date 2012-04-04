/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.impl;

import java.util.List;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.util.AppUtils;
import com.legendshop.business.dao.PartnerDao;
import com.legendshop.model.entity.Partner;
import com.legendshop.business.service.PartnerService;

/**
 * The Class PartnerServiceImpl.
 */
public class PartnerServiceImpl  implements PartnerService{
    private PartnerDao partnerDao;

    public void setPartnerDao(PartnerDao partnerDao) {
        this.partnerDao = partnerDao;
    }

    public List<Partner> getPartner(String userName) {
        return partnerDao.getPartner(userName);
    }

    public Partner getPartner(Long id) {
        return partnerDao.getPartner(id);
    }

    public void deletePartner(Partner partner) {
        partnerDao.deletePartner(partner);
    }

    public Long savePartner(Partner partner) {
        if (!AppUtils.isBlank(partner.getPartnerId())) {
            updatePartner(partner);
            return partner.getPartnerId();
        }
        return (Long) partnerDao.save(partner);
    }

    public void updatePartner(Partner partner) {
        partnerDao.updatePartner(partner);
    }

    public PageSupport getPartner(CriteriaQuery cq) {
        return partnerDao.find(cq);
    }
}

