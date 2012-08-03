/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.ws.jetty;

import com.legendshop.util.sql.ConfigCode;

/**
 * The Class PropertiesFile.
 */
public class PropertiesFile {
	/*
	private static String filePath ="config/common.properties";
	
	public static String get(String propertyName){
		return EnvironmentConfig.getInstance().getPropertyValue(filePath,propertyName);
	}


	public static int getInt(String propertyName,int defaultValue){
		int result = defaultValue;
		String value = get(propertyName);
		if(value==null){
			return defaultValue;
		}
		try {
			result = Integer.valueOf(value);
		} catch (Exception e) {
			result = defaultValue;
		}
		return result;
	}
	*/
	/**
	 * Gets the.
	 * 
	 * @param propertyName
	 *            the property name
	 * @return the string
	 */
	public static String get(String propertyName){
		return ConfigCode.getInstance().getCode(propertyName);
	}
	
	/**
	 * Gets the int.
	 * 
	 * @param propertyName
	 *            the property name
	 * @param defaultValue
	 *            the default value
	 * @return the int
	 */
	public static int getInt(String propertyName,int defaultValue){
		int result = defaultValue;
		String value = ConfigCode.getInstance().getCode(propertyName);
		if(value == null){
			return defaultValue;
		}
		try {
			result = Integer.valueOf(value);
		} catch (Exception e) {
			result = defaultValue;
		}
		return result;
	}
}
