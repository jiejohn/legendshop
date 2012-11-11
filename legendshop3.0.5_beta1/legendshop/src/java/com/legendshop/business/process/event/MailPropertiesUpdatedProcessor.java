/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.process.event;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.event.processor.BaseProcessor;
import com.legendshop.model.entity.SystemParameter;


/**
 * 邮件相关配置更改
 * The Class MailPropertiesUpdatedProcessor.
 */
public class MailPropertiesUpdatedProcessor extends BaseProcessor<SystemParameter>{
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(MailPropertiesUpdatedProcessor.class);
	
	/** The parameter list. */
	private List<String> parameterList;

	


	/**
	 * Sets the parameter list.
	 *
	 * @param parameterList the new parameter list
	 */
	public void setParameterList(List<String> parameterList) {
		this.parameterList = parameterList;
	}

	/* (non-Javadoc)
	 * @see com.legendshop.event.processor.AbstractProcessor#isSupport(java.lang.Object)
	 */
	public boolean isSupport(SystemParameter systemParameter) {
		return parameterList.contains(systemParameter.getName());
	}


	/* (non-Javadoc)
	 * @see com.legendshop.event.processor.AbstractProcessor#process(java.lang.Object)
	 */
	@Override
	public void process(SystemParameter systemParameter) {
		log.info("PropertiesUpdater update mail parameter {} , value {}", systemParameter.getName(), systemParameter
				.getValue());
		PropertiesUtil.setObject(SysParameterEnum.MAIL_PROPERTIES_CHANGED, true);
	}


}
