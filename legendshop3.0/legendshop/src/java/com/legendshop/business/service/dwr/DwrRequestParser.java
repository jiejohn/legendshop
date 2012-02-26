/*
 * 
 * LegendShop 多用户商城系统
 * 
 *  版权所有,并保留所有权利。
 * 
 */
package com.legendshop.business.service.dwr;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.legendshop.util.AppUtils;


/**
 * 
 * LegendShop 版权所有 2009-2011,并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：在未取得LegendShop商业授权之前，您不能将本软件应用于商业用途，否则LegendShop将保留追究的权力。
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.legendesign.net
 * ----------------------------------------------------------------------------
 */
public class DwrRequestParser {
	
	/** The loger. */
	private static Logger loger = Logger.getLogger(DwrRequestParser.class) ;//Logger.get ;
	//	xml 请求对象
	/** The xmlsource. */
	private final String xmlsource ;
	
	/** The doc. */
	private Document doc = null ;
	
	/** The map. */
	private final Map map = new HashMap();

	/**
	 * Instantiates a new dwr request parser.
	 * 
	 * @param xmlsource
	 *            the xmlsource
	 */
	public DwrRequestParser(String xmlsource){
		this.xmlsource = xmlsource ;
		if(this.xmlsource != null && !this.xmlsource.trim().equals("") ){
			this.parseXml() ;
		}else{
			//loger.debug("no dwr xml request need parse") ;
		}
		
	}
	
	/**
	 * 当参数为数组时，获取参数列表.
	 * 
	 * @param paramName
	 *            the param name
	 * @return the params
	 */
	public List getParams(String paramName){
		List retList = new ArrayList() ;
		Element root = this.getRoot() ;
		List list = root.getChildren() ;
		for(int i=0 ;i<(list==null?0:list.size()) ;i++){
			Element paramElement = (Element)list.get(i) ;
			String name = paramElement.getAttributeValue("name") ;
			if(name.equals(paramName)){
				retList.add( paramElement.getAttributeValue("value") ) ;
			}
		}
		return retList ;
	}
	
	/**
	 * Sets the param.
	 * 
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 */
	public void setParam(String key,Object value){
		map.put(key, value) ;
	}
	
	/**
	 * Gets the keys.
	 * 
	 * @return the keys
	 */
	public List getKeys(){
		List retList = new ArrayList() ;
		Element root = this.getRoot() ;
		List list = root.getChildren() ;
		for(int i=0 ;i<(list==null?0:list.size()) ;i++){
			Element paramElement = (Element)list.get(i) ;
			String name = paramElement.getAttributeValue("name") ;
			if(!retList.contains(name)){
				retList.add(name) ;
			}
		}
		return retList ;
	}
	
	/**
	 * 获取参数列值.
	 * 
	 * @param paramName
	 *            the param name
	 * @return the param
	 */
	public String getParam(String paramName){
		Object obj = map.get(paramName) ;
		if(!AppUtils.isBlank(obj)){
			return obj.toString() ;
		}
		
		Element root = this.getRoot() ;
		List list = root.getChildren() ;
		for(int i=0 ;i<(list==null?0:list.size()) ;i++){
			Element paramElement = (Element)list.get(i) ;
			String name = paramElement.getAttributeValue("name") ;
			if(name.equals(paramName)){
				return  paramElement.getAttributeValue("value")  ;
			}
		}
		return null ;
	}
	
	/**
	 * 获取指定位置参数列值.
	 * 
	 * @param paramName
	 *            the param name
	 * @param index
	 *            the index
	 * @return the param
	 */
	public String getParam(String paramName,int index){
		Element root = this.getRoot() ;
		List list = root.getChildren() ;
		int flag = 0 ;
		for(int i=0 ;i<(list==null?0:list.size()) ;i++){
			Element paramElement = (Element)list.get(i) ;
			String name = paramElement.getAttributeValue("name") ;
			if(name.equals(paramName)){
				flag++ ;
				if(flag==index){
					return  paramElement.getAttributeValue("value")  ;
				}
			}
		}
		return null ;
	}
	
	/**
	 * Gets the plain param.
	 * 
	 * @return the plain param
	 */
	public String getPlainParam(){
		return this.xmlsource ;
	}
	
	/**
	 * 用SAX解析xml对象.
	 */
	private void parseXml(){
		try {
			SAXBuilder builder = new SAXBuilder();
			doc = builder.build( new StringReader( this.xmlsource ) ) ;
		} catch (JDOMException e) {
			loger.debug("解析XML发生异常") ;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the root.
	 * 
	 * @return the root
	 */
	private Element getRoot(){
		if(doc==null){ parseXml() ; }
		Element root = doc.getRootElement() ;
		return root ;
	}
	
	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args){
		String test = "<request><param name='name1' value='value1'/><param name='namen' value='valuen'/></request>" ;
		DwrRequestParser xml = new DwrRequestParser(test);
		//System.out.println( xml.getParam("name1")+"======="+xml.getParam("namen") ) ;
	}
}
