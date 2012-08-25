/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.helper;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.legendshop.core.constant.ParameterEnum;
import com.legendshop.core.helper.PropertiesUpdater;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.SystemParameter;

/**
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * ----------------------------------------------------------------------------
 * -------- 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * --------
 * ----------------------------------------------------------------------------.
 */
public class PropertiesUpdaterImpl implements PropertiesUpdater {
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(PropertiesUpdaterImpl.class);
	
	/** The parameter list. */
	private List<String> parameterList;

	/* (non-Javadoc)
	 * @see com.legendshop.core.helper.PropertiesUpdater#isSupport(com.legendshop.model.entity.SystemParameter)
	 */
	public boolean isSupport(SystemParameter systemParameter) {
		return parameterList.contains(systemParameter.getName());
	}

	/* (non-Javadoc)
	 * @see com.legendshop.core.helper.PropertiesUpdater#update(com.legendshop.model.entity.SystemParameter)
	 */
	public void update(SystemParameter systemParameter) {
		log.info("PropertiesUpdater update mail parameter {} , value {}", systemParameter.getName(), systemParameter
				.getValue());
		PropertiesUtil.setObject(ParameterEnum.MAIL_PROPERTIES_CHANGED, true);
	}

	/**
	 * Sets the parameter list.
	 * 
	 * @param parameterList
	 *            the new parameter list
	 */
	public void setParameterList(List<String> parameterList) {
		this.parameterList = parameterList;
	}

}
