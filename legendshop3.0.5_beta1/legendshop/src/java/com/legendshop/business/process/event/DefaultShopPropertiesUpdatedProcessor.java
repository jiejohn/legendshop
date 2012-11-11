/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.process.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.exception.BusinessException;
import com.legendshop.core.exception.EntityCodes;
import com.legendshop.core.service.ShopService;
import com.legendshop.event.processor.BaseProcessor;
import com.legendshop.model.entity.ShopDetailView;
import com.legendshop.model.entity.SystemParameter;
import com.legendshop.spi.constants.Constants;

/**
 * 修改默认商城逻辑
 * The Class DefaultShopPropertiesUpdatedProcessor.
 */
public class DefaultShopPropertiesUpdatedProcessor extends BaseProcessor<SystemParameter>{
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(DefaultShopPropertiesUpdatedProcessor.class);
	
	private ShopService shopService;
	
	/* (non-Javadoc)
	 * @see com.legendshop.event.processor.AbstractProcessor#isSupport(java.lang.Object)
	 */
	@Override
	public boolean isSupport(SystemParameter systemParameter) {
		boolean result = systemParameter != null && SysParameterEnum.DEFAULT_SHOP.name().equals(systemParameter.getName());
	
		return result;
	}

	/**
	 * 处理逻辑.
	 *
	 * @param systemParameter the system parameter
	 */
	@Override
	public void process(SystemParameter systemParameter) { 
		ShopDetailView shopDetail = shopService.getShopDetailView(systemParameter.getValue());
		if(shopDetail == null){
			throw new BusinessException("找不到默认商城  " + systemParameter.getValue(), EntityCodes.SHOP);
		}
		if(!Constants.ONLINE.equals(shopDetail.getStatus()) ){
			throw new BusinessException("商城 '" + systemParameter.getValue() + "'处于下线状态，不能作为默认商城", EntityCodes.SHOP);
		}
	}

	public void setShopService(ShopService shopService) {
		this.shopService = shopService;
	}


}
