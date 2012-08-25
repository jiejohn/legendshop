/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.displaytag.export.excel.ExcelHssfView;
/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public class SimpleChineseExcelView extends ExcelHssfView {
	
	/* (non-Javadoc)
	 * @see org.displaytag.export.excel.ExcelHssfView#getMimeType()
	 */
	public String getMimeType() {
		return "application/vnd.ms-excel;charset=UTF-8"; //$NON-NLS-1$    
	}

	/* (non-Javadoc)
	 * @see org.displaytag.export.excel.ExcelHssfView#escapeColumnValue(java.lang.Object)
	 */
	protected String escapeColumnValue(Object rawValue) {
		if (rawValue == null) {
			return null;
		}
		String returnString = ObjectUtils.toString(rawValue);
		// escape the String to get the tabs, returns, newline explicit as \t \r
		// \n
		returnString = StringEscapeUtils.escapeJava(StringUtils
				.trimToEmpty(returnString));
		// remove tabs, insert four whitespaces instead
		returnString = StringUtils.replace(StringUtils.trim(returnString),
				"\\t", "    ");
		// remove the return, only newline valid in excel
		returnString = StringUtils.replace(StringUtils.trim(returnString),
				"\\r", " ");
		// unescape so that \n gets back to newline
		returnString = StringEscapeUtils.unescapeJava(returnString);

		// return returnString;

		Pattern p = Pattern.compile("</?[div|span|a|font|b][^>]*>",
				Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(returnString);
		return m.replaceAll("");
	}
}
