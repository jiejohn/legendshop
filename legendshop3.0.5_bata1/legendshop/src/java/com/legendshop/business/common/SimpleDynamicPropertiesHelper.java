/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.common;

import com.legendshop.model.dynamic.Item;
import com.legendshop.model.dynamic.Model;

/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * 
 * ------------------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ------------------------------------------------------------------------------------
 * 
 * 官方网站：http://www.legendesign.net
 */
public class SimpleDynamicPropertiesHelper extends DynamicPropertiesHelper {
	// 生成选择框
	/* (non-Javadoc)
	 * @see com.legendshop.business.common.DynamicPropertiesHelper#generateSelect(com.legendshop.model.dynamic.Model, java.lang.StringBuffer)
	 */
	@Override
	public StringBuffer generateSelect(Model model, StringBuffer sb) {
		sb.append(model.getId()).append("&nbsp;<select id='").append(model.getId()).append("' class='attrselect'")
				.append(" name='").append(model.getId()).append("'>");
		sb.append("<option value=''>请选择</option>");
		for (Item item : model.getItems()) {
			sb.append("<option value='").append(item.getKey()).append("'>").append(item.getKey()).append("</option>");
		}
		sb.append("</select><br>");
		return sb;
	}

}
